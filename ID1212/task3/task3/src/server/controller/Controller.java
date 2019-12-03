package server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.integration.DBAccess;

import common.CatalogServer;

public class Controller extends UnicastRemoteObject implements CatalogServer {
	private final DBAccess db;
	private List<String> usersOnline;
	
	public Controller() throws RemoteException {
		db = new DBAccess();
		this.usersOnline = new ArrayList<String>();
	}
	
	@Override
	public boolean register(String uname, String pword) throws SQLException {
		boolean userCreated = false;
		if(db.userExist(uname) == false){
			userCreated = db.createUser(uname, pword);
			System.out.println("User: " + uname + ", is now registered");
		}
		return userCreated;
	}
	
	@Override
	public boolean login(String uname, String pword) {  
		try {
			if(db.loginUser(uname, pword)) {
				if(usersOnline.contains(uname))
					return false;
				else {
					System.out.println("User: " + uname + ", is now logged in");
					return usersOnline.add(uname);
				}
			}
		}catch(Exception e) {
			System.out.println("Error in login: " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean logout(String uname) {
		if(usersOnline.contains(uname)) {
			usersOnline.remove(uname);
			System.out.println("User: " + uname + ", is now logged out");
			return true;
		}
		return false;
	}
	
	@Override
	public boolean upload(String[] attributes) {
		if(usersOnline.contains(attributes[1])) {
			try {
				if(!db.fileSearch(attributes))
					return db.uploadMetaData(attributes);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public String[] download(String fileName) {
		String[] metaData = new String[4];
		metaData[0] = "0";
		
		try {
			metaData = db.downloadMetaData(fileName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return metaData;
	}
	
	@Override
	public void printcock() {
		db.printbruh();
	}

}
