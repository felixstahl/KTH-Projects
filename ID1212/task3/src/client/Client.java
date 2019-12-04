package client;

import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;
import common.CatalogServer; 
import client.handler.ClientHdlr;

public class Client{
	public static void main(String[] args) {  
		try {  
			Registry registry = LocateRegistry.getRegistry(null); 
			CatalogServer stub = (CatalogServer) registry.lookup("CATALOG_SERVER"); 
            ClientHdlr handler = new ClientHdlr(stub);
			handler.run();
         
		}catch (Exception e) {
	         System.err.println("Client exception: "); 
	         e.printStackTrace(); 
		} 
	}
}