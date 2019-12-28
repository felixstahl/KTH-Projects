package common;

import java.rmi.Remote; 
import java.rmi.RemoteException;

public interface DayCareServer extends Remote {

	void addEmployee(String adder, String addee, String addeePword, ClientCallback obj) throws RemoteException;
	//void removeEmployee(String remover, String removee, ClientCallback obj) throws RemoteException, SQLException;
	void loginEmployee(String uname, String pword, ClientCallback obj) throws RemoteException;
//	void logout(String uname, ClientCallback obj) throws RemoteException;
//	void upload(String[] attributes, ClientCallback obj) throws RemoteException;
//	void download(String fileName, ClientCallback obj) throws RemoteException;
//	void printDB(ClientCallback obj) throws RemoteException;
}