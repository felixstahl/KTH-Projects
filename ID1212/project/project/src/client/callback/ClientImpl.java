package client.callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import common.ClientCallback;

public class ClientImpl extends UnicastRemoteObject implements ClientCallback{
	public ClientImpl() throws RemoteException{}

	@Override
	public void addEmployeeCallback(boolean success, String adder, String addee) throws RemoteException {
		if(success)
			System.out.println("The user: " + addee + " is now registered by: " + adder);
		else
			System.out.println("Error adding employee. Try again or type HELP");
	}
	@Override
	public void removeEmployeeCallback(boolean success, String remover, String removee) throws RemoteException {
		if(success)
			System.out.println("The user: " + removee + " is now removed");
		else
			System.out.println("Error removing employee. Try again or type HELP");
	}
	@Override
	public void loginEmployeeCallback(boolean success, String uname) throws RemoteException {
		if(success)
			System.out.println("The user: " + uname + " is now logged in");
		else
			System.out.println("Error logging in. Try again or type HELP");
	}
	@Override
	public void logoutEmployeeCallback(boolean success) throws RemoteException {
		if(success)
			System.out.println("You are now logged out");
		else
			System.out.println("Error logging out. Try again or type HELP");
	}
	@Override
	public void addDogCallback(boolean success, String name, String owner) throws RemoteException {
		if(success)
			System.out.println("The dog: " + name + " has been registered by owner: " + owner);
		else
			System.out.println("Error registering dog. Try again or type HELP");
	}
	@Override
	public void removeDogCallback(boolean success, String name, String owner) throws RemoteException {
		if(success)
			System.out.println("The dog: " + name + " has been removed from the database");
		else
			System.out.println("Error removing dog. Try again or type HELP");
	}
	@Override
	public void checkInDogCallback(boolean success, String name, String owner) throws RemoteException {
		if(success)
			System.out.println("The dog: " + name + " is now checked in by: " + owner);
		else
			System.out.println("Error while checking in. Try again or type HELP");
	}
	@Override
	public void checkOutDogCallback(boolean success, String name, String owner) throws RemoteException {
		if(success)
			System.out.println("The dog: " + name + " is now checked out by: " + owner);
		else
			System.out.println("Error while checking out. Try again or type HELP");
	}
	@Override
	public void presentDogsCallback(String[] names) throws RemoteException {
		if(names.length == 0) 
			System.out.println("No dogs present as of now. Try again or type HELP");
		else {
			System.out.println("All the dogs that is present are:");
			for(String dog : names) {
				System.out.println(dog);
			}
		}
	}
	@Override
	public void searchDogCallback(boolean success, String name, String owner) throws RemoteException {
		if(success) 
			System.out.println("The owner" + owner + " that owns the dog called: " + name + " does exist in the database");
		else {
			System.out.println("No sush dog in the register. Try again or type HELP");
		}
	}
	@Override
	public void allDogsCallback(String[] names) throws RemoteException {
		if(names.length == 0) 
			System.out.println("No dogs in the register. Try again or type HELP");
		else {
			System.out.println("All the dogs registered in the system are:");
			for(String dog : names) {
				System.out.println(dog);
			}
		}
	}
}
