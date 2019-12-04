package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote{
	
	void alert(String fileName) throws RemoteException;
	void registerCallback(boolean success, String uname) throws RemoteException;
	void loginCallback(boolean success, String uname) throws RemoteException;
	void logoutCallback(boolean success) throws RemoteException;
	void uploadCallback(boolean success) throws RemoteException;
	void downloadCallback(String[] metaData) throws RemoteException;
	void allFiles(String[] files) throws RemoteException;
}
