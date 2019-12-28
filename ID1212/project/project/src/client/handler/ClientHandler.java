package client.handler;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Scanner;
import client.callback.ClientImpl;
import common.ClientCallback;
import common.DayCareServer;

public class ClientHandler {
	private DayCareServer stub;
	private Scanner scanner = new Scanner(System.in);
	private InputCustomization customInput;
	
	public ClientHandler(DayCareServer stub) throws RemoteException{
		this.stub = stub;
		this.customInput = new InputCustomization();
	}
	
	public void run() throws RemoteException, SQLException {

		System.out.println("Client is ready");
		ClientCallback callback = new ClientImpl();
		
		
		while(true) {
			System.out.print("Enter command: ");
			
			String[] input = customInput.parseInput(scanner.nextLine());
			
			switch(input[0]) {
				case "ADDEMP":{
					if(input.length == 4)
						stub.addEmployee(input[1], input[2], input[3], callback);
					else
						System.out.println("Missing parameter, try again or type HELP for help");
					
					break;
				}
//				case "REMOVEEMP":{
//					if(input.length == 3)
//						stub.removeEmployee(input[1], input[2], callback);
//					else
//						System.out.println("Missing parameter, try again or type HELP for help");
//					
//					break;
//				}
				case "LOGINEMP":{
					if(input.length == 3)
						stub.loginEmployee(input[1], input[2], callback);
					else
						System.out.println("Missing parameter, try again or type HELP for help");
					
					break;
				}
//				case "LOGOUTEMPLOYEE":{
//					if(parsed.length == 2){
//						stub.logoutEmployee(parsed[1], callback);
//					}
//					else 
//						System.out.println("Missing parameter, try again or type HELP for help");
//					
//					break;
//				}
//				case "UPLOAD":{
//					if(parsed.length == 3){
//						if(custom.fileExist(parsed[1]))
//							stub.upload(custom.fileAttr(parsed[1], parsed[2]), callback);
//						else
//							System.out.println("File: " + parsed[1] + ", does not exist in client folder, try again");
//					}else
//						System.out.println("Missing parameter, try again or type HELP for help");
//
//					break;
//				}
//				case "DOWNLOAD":{
//					if(parsed.length == 2)
//						stub.download(parsed[1], callback);
//					else 
//						System.out.println("Missing parameter, try again or type HELP for help");
//					
//					break;
//				}
//				case "ALLFILES":{
//					stub.printDB(callback);
//					break;
//				}
				case "HELP":{
					System.out.println("All the different commands are entered the following way: ");
					System.out.println("addEmp adder addee addee-password");
					System.out.println("-- removeEmp removER removEE");
					System.out.println("-- loginEmp username password");
					System.out.println("-- logoutEmp username password");
					System.out.println("-- checkInDog username password");
					System.out.println("-- logoutEmp username password");
					System.out.println("-- allDogs");
					break;
				}
				default:{
					System.out.println("Invalid command, type HELP for help");
					break;
				}
			}
		}
	}
}
