package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote{
	
	void addEmployeeCallback(boolean success, String adder, String addee) throws RemoteException;
	void removeEmployeeCallback(boolean success, String remover, String removee) throws RemoteException;
	void loginEmployeeCallback(boolean success, String uname) throws RemoteException;
	void logoutEmployeeCallback(boolean success) throws RemoteException;
	void addDogCallback(boolean success, String name, String owner) throws RemoteException;
	void removeDogCallback(boolean success, String name, String owner) throws RemoteException;
	void checkInDogCallback(boolean success, String name, String owner) throws RemoteException;
	void checkOutDogCallback(boolean success, String name, String owner) throws RemoteException;
	void presentDogsCallback(String[] names) throws RemoteException;
	void searchDogCallback(boolean success, String name, String owner) throws RemoteException;
	void allDogsCallback(String[] names) throws RemoteException;
}
