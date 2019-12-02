package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;
import java.util.Scanner;

import common.CatalogServer;  

public class Client{ 
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {  
		try {  

			Registry registry = LocateRegistry.getRegistry(null); 
         
			CatalogServer stub = (CatalogServer) registry.lookup("CATALOG_SERVER"); 
             
			run(stub);
			//stub.printMsg(); 
         
		} catch (Exception e) {
	         System.err.println("Client exception: " + e.toString()); 
	         e.printStackTrace(); 
		} 
	}

	//@Override
	public static void run(CatalogServer stub) throws RemoteException {
		String input; 
		while(true) {
			input = scanner.nextLine();
			String[] parsed = parseInput(input);
			input = parsed[0].toUpperCase();
			switch(input) {
				case "QUIT":{
					
					break;
				}
				case "REGISTER":{
					
					break;
				}
				case "LOGIN":{
					
					break;
				}
				case "LOGOUT":{
					
					break;
				}
				case "PRINT":{
					stub.printMsg();
					break;
				}
				
			}
		}
	} 
	
	private static String[] parseInput(String str) {
		String[] input = str.split(" ");
		return input;
	}
}