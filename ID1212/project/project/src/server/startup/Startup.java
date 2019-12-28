package server.startup;

import java.rmi.registry.Registry;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry; 
import server.controller.Controller; 

public class Startup { 
   public static void main(String args[]) throws RemoteException {
	   Controller controller = new Controller();
	   
	   try {
		   new Startup().startRegistry();
		   Naming.rebind("DAYCARE", controller);
		   System.out.println("Server is running");
		   
       } catch(Exception e) {
    	   System.out.println("Something happened in Startup.java: ");
    	   e.printStackTrace();
       }
   }
   
   private void startRegistry() throws RemoteException {
	   try {
           LocateRegistry.getRegistry().list();
       } catch (RemoteException noRegistry) {
           LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
       }
   }
}