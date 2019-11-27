import java.rmi.*;
import java.net.*;
import java.sql.*;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Startup{
  public Startup(){}
  public static void main(String[] args){
    try{
      Controller controller = new Controller();

      CatalogServer stub = (CatalogServer) UnicastRemoteObject.exportObject(controller, 0);

      Registry registry = LocateRegistry.getRegistry();

      registry.bind("Hello", stub);
      System.err.println("Server ready");


    }catch(Exception e){  System.out.println("Something is wrong in startup"); }
  }
}
