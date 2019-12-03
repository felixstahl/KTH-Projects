package common;

import java.rmi.Remote; 
import java.rmi.RemoteException;
import java.sql.SQLException;  

public interface CatalogServer extends Remote {

	boolean register(String uname, String pword) throws RemoteException, SQLException;
	boolean login(String uname, String pword) throws RemoteException;
	boolean logout(String uname) throws RemoteException;
	boolean upload(String[] attributes) throws RemoteException;
	String[] download(String fileName) throws RemoteException;
	void printcock() throws RemoteException;
}