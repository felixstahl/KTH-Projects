package common;

import java.rmi.Remote; 
import java.rmi.RemoteException;
import java.sql.SQLException;  

public interface CatalogServer extends Remote {
	public static final String SERVER_NAME_IN_REGISTRY = "CATALOG_SERVER";
	
	void printMsg() throws RemoteException; 
	boolean register(String uname, String pword) throws RemoteException, SQLException;
	boolean login(String uname, String pword) throws RemoteException;
	boolean logout(String uname) throws RemoteException;
}