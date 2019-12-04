package client.handler;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Scanner;
import client.callback.ClientImpl;
import common.CatalogServer;
import common.ClientCallback;

public class ClientHdlr {
	private Scanner scanner = new Scanner(System.in);
	private CatalogServer stub;
	private InputCustomization custom;
	//private ClientImpl clientImpl;
	
	public ClientHdlr(CatalogServer stub) throws RemoteException{
		this.stub = stub;
		this.custom = new InputCustomization();
		//this.clientImpl = new ClientImpl();
	}
	
	public void run() throws RemoteException, SQLException {
		String input;
		System.out.println("Client is ready");
		ClientCallback callback = new ClientImpl();
		
		
		while(true) {
			System.out.println("Enter command: ");
			
			input = scanner.nextLine();
			String[] parsed = custom.parseInput(input);
			input = parsed[0].toUpperCase();
			
			switch(input) {
				case "REGISTER":{
					if(parsed.length == 3)
						stub.register(parsed[1], parsed[2], callback);
					else
						System.out.println("Missing parameter, try again or type HELP for help");
					
					break;
				}
				case "LOGIN":{
					if(parsed.length == 3)
						stub.login(parsed[1], parsed[2], callback);
					else
						System.out.println("Missing parameter, try again or type HELP for help");
					
					break;
				}
				case "LOGOUT":{
					if(parsed.length == 2){
						stub.logout(parsed[1], callback);
					}
					else 
						System.out.println("Missing parameter, try again or type HELP for help");
					
					break;
				}
				case "UPLOAD":{
					if(parsed.length == 3){
						if(custom.fileExist(parsed[1]))
							stub.upload(custom.fileAttr(parsed[1], parsed[2]), callback);
						else
							System.out.println("File: " + parsed[1] + ", does not exist in client folder, try again");
					}else
						System.out.println("Missing parameter, try again or type HELP for help");

					break;
				}
				case "DOWNLOAD":{
					if(parsed.length == 2)
						stub.download(parsed[1], callback);
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
					stub.printDB();
					break;
				}
				default:{
					System.out.println("Invalid command, try again or type HELP for help");
					break;
				}
				
			}
		}
	}
}
