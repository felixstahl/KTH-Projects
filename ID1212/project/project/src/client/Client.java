package client;

import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;
import common.DayCareServer; 
import client.handler.ClientHandler;

public class Client{
	public static void main(String[] args) {  
		try {  
			Registry registry = LocateRegistry.getRegistry(null); 
			DayCareServer stub = (DayCareServer) registry.lookup("DAYCARE");
			
            ClientHandler handler = new ClientHandler(stub);
			handler.run();
         
		}catch (Exception e) {
	         System.err.println("Something happened in Client.java: "); 
	         e.printStackTrace(); 
		} 
	}
}