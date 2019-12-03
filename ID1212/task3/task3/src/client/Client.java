package client;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.Scanner;

import common.CatalogServer;  

public class Client{
	private static Scanner scanner = new Scanner(System.in);
	//private static String filePath = "C:\\Users\\mr_fe\\Desktop\\Natverksprogrammering\\ID1212\\task3\\src\\client\\files\\";
	private static String filePath = "C:/Users/mr_fe/Desktop/Natverksprogrammering/task3/src/client/files/";
	
	public static void main(String[] args) {  
		try {  
			Registry registry = LocateRegistry.getRegistry(null); 
			CatalogServer stub = (CatalogServer) registry.lookup("CATALOG_SERVER"); 
             
			run(stub);
         
		} catch (Exception e) {
	         System.err.println("Client exception: " + e.getMessage()); 
	         e.printStackTrace(); 
		} 
	}

	//@Override
	public static void run(CatalogServer stub) throws RemoteException, SQLException {
		String input;
		System.out.println("Client is ready");
		
		while(true) {
			System.out.println("Enter command: ");
			
			input = scanner.nextLine();
			String[] parsed = parseInput(input);
			input = parsed[0].toUpperCase();
			
			switch(input) {
				case "REGISTER":{
					if(parsed.length == 3) {
						if(stub.register(parsed[1], parsed[2]))
							System.out.println("The user: " + parsed[1] + " is now registered");
						else
							System.out.println("The username is already taken or a server-side error occured. Try again with something else");
					}
					else
						System.out.println("Missing parameter, try again or type HELP for help");
					break;
				}
				case "LOGIN":{
					if(parsed.length == 3) {
						if(stub.login(parsed[1], parsed[2]))
							System.out.println("You are now logged in as: " + parsed[1]);
						else
							System.out.println("Server-side error. Have you already logged in?");
					}
					else
						System.out.println("Missing parameter, try again or type HELP for help");
					break;
				}
				case "LOGOUT":{
					if(parsed.length == 2){
						if(stub.logout(parsed[1]))
							System.out.println("You are now logged out");
						else
							System.out.println("Server-side error. Are you even logged in? Either way, We could not log you out");
					}
					else 
						System.out.println("Missing parameter, try again or type HELP for help");
					break;
				}
				case "UPLOAD":{
					if(parsed.length == 3){
						if(fileExist(parsed[1])) {
							if(stub.upload(fileAttr(parsed[1], parsed[2])))
								System.out.println("Upload of: " + parsed[1] + " was successful");
							else
								System.out.println("Upload of: " + parsed[1] + " was NOT successful");
						}else
							System.out.println("File: " + parsed[1] + ", does not exist in client, try again");
					}else 
						System.out.println("Missing parameter, try again or type HELP for help");
	
					break;
				}
				case "DOWNLOAD":{
					if(parsed.length == 2){
						String[] attr = new String[4];
						attr = stub.download(parsed[1]);
						if(Integer.parseInt(attr[0]) == 1) {
							System.out.println("You wish to download: " + attr[1]);
							System.out.println("It's owner is: " + attr[2] + ", and it's size is: " + attr[3]);
						}else
							System.out.println("File: " + parsed[1] + " does not exist in the database");
					}
					else 
						System.out.println("Missing parameter, try again or type HELP for help");
					break;
				}
				case "HELP":{
					System.out.println("All the different commands are entered the following way: ");
					System.out.println("register username password");
					System.out.println("login username password");
					System.out.println("logout username");
					System.out.println("upload filename owner");
					System.out.println("download filename");
					break;
				}
				case "PRINT":{
					stub.printcock();
					break;
				}
				default:{
					System.out.println("Invalid command, try again or type HELP for help");
					break;
				}
				
			}
		}
	} 
	
	private static String[] parseInput(String str) {
		String[] input = str.split(" ");
		return input;
	}
	
	private static String[] fileAttr(String fileName, String owner) {
		String[] attributes = new String[3];
		File file = new File(filePath + fileName);
		
		attributes[0] = fileName;
		attributes[1] = owner;
		attributes[2] = Long.toString(file.length());
		
		return attributes;
	}
	
	private static boolean fileExist(String fileName) {
		File file = new File(filePath + fileName);
		return file.exists();
	}
}