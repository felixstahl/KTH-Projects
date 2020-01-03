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

		System.out.println("Client is ready, type HELP for help");
		ClientCallback callback = new ClientImpl();
		
		
		while(true) {
			System.out.print("Enter command: ");
			
			String[] input = customInput.parseInput(scanner.nextLine());
			
			switch(input[0]) {
				case "ADDEMP":{
					if(input.length == 4)
						stub.addEmployee(input[1], input[2], input[3], callback);
					else
						System.out.println("Wrong parameters, type HELP for help");
					
					break;
				}
				case "REMOVEEMP":{
					if(input.length == 3)
						stub.removeEmployee(input[1], input[2], callback);
					else
						System.out.println("Wrong parameters, type HELP for help");
					
					break;
				}
				case "LOGINEMP":{
					if(input.length == 3) {
						//if(callback.getLoggedIn() == false)
							stub.loginEmployee(input[1], input[2], callback);
						//else
							//System.out.println("Already logged in. Type HELP for help");
					}
					else
						System.out.println("Wrong parameters, type HELP for help");
					
					break;
				}
				case "LOGOUTEMP":{
					if(input.length == 2){
						//if(callback.getLoggedIn() == true)
						//	System.out.println("Already logged in. Type HELP for help");
						//else
							stub.logoutEmployee(input[1], callback);
					}
					else 
						System.out.println("Wrong parameters, type HELP for help");
					
					break;
				}
				case "SEARCHOWNER":{
					if(input.length == 3)
						stub.searchOwner(input[1], input[2], callback);
					else
						System.out.println("Wrong parameters, type HELP for help");
					
					break;
				}
				case "ADDOWNER":{
					if(input.length == 3)
						stub.addOwner(input[1], input[2], callback);
					else
						System.out.println("Wrong parameters, type HELP for help");
					
					break;
				}
				case "REMOVEOWNER":{
					if(input.length == 3)
						stub.removeOwner(input[1], input[2], callback);
					else
						System.out.println("Wrong parameters, type HELP for help");
					
					break;
				}
				case "SEARCHDOG":{
					if(input.length == 3)
						stub.searchDog(input[1], input[2], callback);
					else
						System.out.println("Wrong parameters, type HELP for help");
					
					break;
				}
				case "ADDDOG":{
					if(input.length == 4)
						stub.addDog(input[1], input[2], input[3], callback);
					else
						System.out.println("Wrong parameters, type HELP for help");
					
					break;
				}
				case "REMOVEDOG":{
					if(input.length == 3)
						stub.removeDog(input[1], input[2], callback);
					else
						System.out.println("Wrong parameters, type HELP for help");
					
					break;
				}
				case "CHECKIN":{
					if(input.length == 3)
						stub.checkInDog(input[1], input[2], callback);
					else
						System.out.println("Wrong parameters, type HELP for help");
					
					break;
				}
				case "CHECKOUT":{
					if(input.length == 3){
						stub.checkOutDog(input[1], input[2], callback);
					}
					else 
						System.out.println("Wrong parameters, type HELP for help");
					
					break;
				}
				case "PRESENTDOGS":{
					stub.presentDogs(callback);
					break;
				}
				case "ALLDOGS":{
					stub.allDogs(callback);
					break;
				}
				case "HELP":{
					System.out.println("All the different commands are entered the following way: ");
					System.out.println("   ------   ");
					System.out.println("- addEmp adder addee addee-password");
					System.out.println("- removeEmp removER removEE removEEPword");
					System.out.println("- loginEmp username password");
					System.out.println("- logoutEmp username");
					System.out.println("---");
					System.out.println("- searchOwner username address");
					System.out.println("- addOwner username address");
					System.out.println("- removeOwner username address");
					System.out.println("---");
					System.out.println("- searchDog name address");
					System.out.println("- addDog dogName ownerName address");
					System.out.println("- removeDog dogName address");
					System.out.println("- checkIn name address");
					System.out.println("- checkOut name address");
					System.out.println("- presentdogs");
					System.out.println("- allDogs");
					System.out.println("   ------   ");
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
