package server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import server.integration.DBAccess;
import common.DayCareServer;
import common.ClientCallback;

public class Controller extends UnicastRemoteObject implements DayCareServer{
	private final DBAccess db;
	private List<String> employeesOnline;
	//private List<String> ownersOnline;
	
	public Controller() throws RemoteException {
		db = new DBAccess();
		this.employeesOnline = new ArrayList<String>();
		//this.ownersOnline = new ArrayList<String>();
	}
	
	@Override
	public void addEmployee(String adder, String addee, String addeePword, ClientCallback obj) throws RemoteException {
		boolean employeeAdded = false;
		
		try {
			if(employeesOnline.contains(adder)) {
				if(db.employeeExist(addee) == false){
					employeeAdded = db.addEmployee(addee, addeePword);
					if(employeeAdded){
						System.out.println("User: " + addee + ", is now registered by: " + adder);
					}
				}
			}
		} catch(Exception e) {	e.printStackTrace();	}
		
		obj.addEmployeeCallback(employeeAdded, adder, addee);
	}
	
//	@Override
//	public void removeEmployee(String remover, String pword, String removee, ClientCallback obj) throws SQLException, RemoteException {
//		boolean employeeRemoved = false;
//		
//		if(db.employeeExist(removee) == true){
//			employeeRemoved = db.removeEmployee(removee, pword);
//			if(employeeRemoved == true){
//				System.out.println("User: " + removee + ", is now removed by: " + remover);
//			}
//		}
//		obj.addEmployeeCallback(employeeRemoved, remover, removee);
//	}
	
	@Override
	public void loginEmployee(String uname, String pword, ClientCallback obj) throws RemoteException {  
		boolean loggedIn = false;
		
		try {
			if(db.loginEmployee(uname, pword)) {
				if(!employeesOnline.contains(uname))
					loggedIn = employeesOnline.add(uname);
					if(loggedIn)
						System.out.println("User: " + uname + ", is now logged in");
				}
		}catch(Exception e) {	e.printStackTrace();	}
		
		obj.loginEmployeeCallback(loggedIn, uname);
	}
//	
//	@Override
//	public void logoutEmployee(String uname, ClientCallback obj) throws RemoteException {
//		boolean success = false;
//		if(usersOnline.contains(uname)) {
//			success = usersOnline.remove(uname);
//			if(success)
//				System.out.println("User: " + uname + ", is now logged out");
//		}
//		obj.logoutCallback(success);
//	}
//	
//	@Override
//	public void addDog(String uname, String pword, ClientCallback obj) throws SQLException, RemoteException {
//		
//	}
//	
//	@Override
//	public void removeDog(String uname, String pword, ClientCallback obj) throws SQLException, RemoteException {
//		
//	}
//	
//	@Override
//	public void checkInDog(String uname, String pword, ClientCallback obj) throws SQLException, RemoteException {
//		
//	}
//	
//	@Override
//	public void checkOutDog(String uname, String pword, ClientCallback obj) throws SQLException, RemoteException {
//		
//	}
//	
//	@Override
//	public void searchDog(String uname, String pword, ClientCallback obj) throws SQLException, RemoteException {
//		
//	}
//	
//	@Override
//	public void allDogsOfOwner(String uname, String pword, ClientCallback obj) throws SQLException, RemoteException {
//		
//	}
//	
//	@Override
//	public void allDogs(ClientCallback obj) {
//		try {
//			List<String> files = db.allDogs();
//			String[] fileNames = new String[files.size()];
//			int i = 0;
//			
//			for(String f : files) {
//				fileNames[i] = f;
//				i++;
//			}
//			
//			obj.allFiles(fileNames);
//			
//		}catch(RemoteException e) {	e.printStackTrace();	}
//	}
	
	
	
	
//	@Override
//	public void upload(String[] attributes, ClientCallback obj){
//		boolean upload = false;
//		try {
//			if(usersOnline.contains(attributes[1])) {
//				if(!db.fileSearch(attributes)) {
//					upload = db.uploadMetaData(attributes);
//					if(upload == true) {
//						System.out.println("upload was successful by user: " + attributes[1]);
//						fileOwners.put(attributes[1], obj);
//					}	
//				}
//			}
//			obj.uploadCallback(upload);
//			
//		}catch(SQLException | RemoteException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Override
//	public void download(String fileName, ClientCallback obj) throws RemoteException	{
//		String[] metaData = new String[4];
//		metaData[0] = "0";
//		
//		try {
//			metaData = db.downloadMetaData(fileName);
//			if(fileOwners.containsKey(metaData[2]) && usersOnline.contains(metaData[2])) {
//				fileOwners.get(metaData[2]).alert(metaData[1]);
//			}
//			
//		}catch(SQLException | RemoteException e) {	e.printStackTrace();	}
//		
//		obj.downloadCallback(metaData);
//	}
}
