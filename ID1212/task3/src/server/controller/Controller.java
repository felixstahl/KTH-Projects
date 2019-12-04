package server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import server.integration.DBAccess;
import common.CatalogServer;
import common.ClientCallback;

public class Controller extends UnicastRemoteObject implements CatalogServer{
	private final DBAccess db;
	private List<String> usersOnline;
	private HashMap<String, ClientCallback> fileOwners;
	
	public Controller() throws RemoteException {
		db = new DBAccess();
		this.usersOnline = new ArrayList<String>();
		this.fileOwners = new HashMap<String, ClientCallback>();
	}
	
	@Override
	public void register(String uname, String pword, ClientCallback obj) throws SQLException, RemoteException {
		boolean userCreated = false;
		if(db.userExist(uname) == false){
			userCreated = db.createUser(uname, pword);
			if(userCreated == true)	{
				System.out.println("User: " + uname + ", is now registered");
			}
		}
		obj.registerCallback(userCreated, uname);
	}
	
	@Override
	public void login(String uname, String pword, ClientCallback obj) throws RemoteException {  
		boolean loggedIn = false;
		try {
			if(db.loginUser(uname, pword)) {
				if(usersOnline.contains(uname))
					loggedIn = false;
				else {
					loggedIn = usersOnline.add(uname);
					if(loggedIn)
						System.out.println("User: " + uname + ", is now logged in");
				}
			}
		}catch(Exception e) {	System.out.println("Error in login: " + e.getMessage());	}
		
		obj.loginCallback(loggedIn, uname);
	}
	
	@Override
	public void logout(String uname, ClientCallback obj) throws RemoteException {
		boolean success = false;
		if(usersOnline.contains(uname)) {
			success = usersOnline.remove(uname);
			if(success)
				System.out.println("User: " + uname + ", is now logged out");
		}
		obj.logoutCallback(success);
	}
	
	@Override
	public void upload(String[] attributes, ClientCallback obj){
		boolean upload = false;
		try {
			if(usersOnline.contains(attributes[1])) {
				if(!db.fileSearch(attributes)) {
					upload = db.uploadMetaData(attributes);
					if(upload == true) {
						System.out.println("upload was successful by user: " + attributes[1]);
						fileOwners.put(attributes[1], obj);
					}	
				}
			}
			obj.uploadCallback(upload);
			
		}catch(SQLException | RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void download(String fileName, ClientCallback obj) throws RemoteException	{
		String[] metaData = new String[4];
		metaData[0] = "0";
		
		try {
			metaData = db.downloadMetaData(fileName);
			if(fileOwners.containsKey(metaData[2]) && usersOnline.contains(metaData[2])) {
				fileOwners.get(metaData[2]).alert(metaData[1]);
			}
			
		}catch(SQLException | RemoteException e) {	e.printStackTrace();	}
		
		obj.downloadCallback(metaData);
	}
	
	@Override
	public void printDB(ClientCallback obj) {
		try {
			List<String> files = db.printFiles();
			String[] fileNames = new String[files.size()];
			int i = 0;
			
			for(String f : files) {
				fileNames[i] = f;
				i++;
			}
			
			obj.allFiles(fileNames);
			
		}catch(RemoteException e) {	e.printStackTrace();	}
	}
}
