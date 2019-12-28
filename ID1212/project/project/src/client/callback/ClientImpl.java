package client.callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import common.ClientCallback;

public class ClientImpl extends UnicastRemoteObject implements ClientCallback{
	public ClientImpl() throws RemoteException{}

//	@Override
//	public void alert(String fileName) throws RemoteException {
//		System.out.println("File: " + fileName + " was accessed");
//	}
	@Override
	public void addEmployeeCallback(boolean success, String adder, String addee) throws RemoteException {
		if(success)
			System.out.println("The user: " + addee + " is now registered by: " + adder);
		else
			System.out.println("The username is already taken. Try again with something else");
	}
	@Override
	public void loginEmployeeCallback(boolean success, String uname) throws RemoteException {
		if(success)
			System.out.println("The user: " + uname + " is now logged in");
		else
			System.out.println("Server-side error while logging in.");
	}
//	@Override
//	public void logoutCallback(boolean success) throws RemoteException {
//		if(success)
//			System.out.println("You are now logged out");
//		else
//			System.out.println("Server-side error while logging out.");
//	}
//	@Override
//	public void uploadCallback(boolean success) throws RemoteException {
//		if(success)
//			System.out.println("File was uploaded successfully");
//		else
//			System.out.println("The file was not uploaded.");
//	}
//	@Override
//	public void downloadCallback(String[] metaData) throws RemoteException {
//		if(metaData[0].equalsIgnoreCase("1"))
//			System.out.println("File: " + metaData[1] + ", is owned by:" + metaData[2] + ", and the size is: " + metaData[3]);
//		else
//			System.out.println("Server-side error. File doe not exist");
//	}
//	@Override
//	public void allFiles(String[] files) throws RemoteException {
//		for(String f : files) {
//			System.out.println(f);
//		}
//	}
}
