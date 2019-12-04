package common;

import java.rmi.Remote; 
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface CatalogServer extends Remote {

	void register(String uname, String pword, ClientCallback obj) throws RemoteException, SQLException;
	void login(String uname, String pword, ClientCallback obj) throws RemoteException;
	void logout(String uname, ClientCallback obj) throws RemoteException;
	void upload(String[] attributes, ClientCallback obj) throws RemoteException;
	void download(String fileName, ClientCallback obj) throws RemoteException;
	
	// the following was just to test the db
	void printDB() throws RemoteException;
}