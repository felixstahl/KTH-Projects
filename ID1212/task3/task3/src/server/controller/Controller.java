package server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import server.integration.DBAccess;

import common.CatalogServer;

public class Controller extends UnicastRemoteObject implements CatalogServer {
	private final DBAccess db;
	private List<String> usersOnline;
	
	public Controller() throws RemoteException {
		db = new DBAccess();
	}
	
	@Override
	public void printMsg() {  
		System.out.println("This is an example RMI program");  
	}
	
	@Override
	public boolean register(String uname, String pword) throws SQLException {
		boolean userCreated = false;
		if(db.userExist(uname) == false){
			userCreated = db.createUser(uname, pword);
		}
		return userCreated;
	}
	
	@Override
	public boolean login(String uname, String pword) {  
		try {
			if(db.loginUser(uname, pword)) {
				if(usersOnline.contains(uname))
					return false;
				else	
					return usersOnline.add(uname);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean logout(String uname) {
		return usersOnline.remove(uname);
	}
}
