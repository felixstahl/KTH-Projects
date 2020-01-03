package common;

import java.rmi.Remote; 
import java.rmi.RemoteException;

public interface DayCareServer extends Remote {
	
	void addEmployee(String adder, String addee, String addeePword, ClientCallback obj) throws RemoteException;
	void removeEmployee(String remover, String addee, ClientCallback obj) throws RemoteException;
	void loginEmployee(String uname, String pword, ClientCallback obj) throws RemoteException;
	void logoutEmployee(String uname, ClientCallback obj) throws RemoteException;
	
	void searchOwner(String name, String address, ClientCallback obj) throws RemoteException;
	void addOwner(String name, String address, ClientCallback obj) throws RemoteException;
	void removeOwner(String name, String address, ClientCallback obj) throws RemoteException;
	
	void addDog(String name, String owner, String address, ClientCallback obj) throws RemoteException;
	void removeDog(String name, String address, ClientCallback obj) throws RemoteException;
	void checkInDog(String name, String owner, ClientCallback obj) throws RemoteException;
	void checkOutDog(String name, String owner, ClientCallback obj) throws RemoteException;
	void searchDog(String name, String address, ClientCallback obj) throws RemoteException;
	void presentDogs(ClientCallback obj) throws RemoteException;
	void allDogs(ClientCallback obj) throws RemoteException;
}