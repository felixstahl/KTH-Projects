package server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import server.integration.DBAccess;
import common.DayCareServer;
import common.ClientCallback;

public class Controller extends UnicastRemoteObject implements DayCareServer{
	private final DBAccess db;
	private List<String> employeesOnline;
	private List<String> dogsCheckedIn;
	
	public Controller() throws RemoteException {
		db = new DBAccess();
		this.employeesOnline = new ArrayList<String>();
		this.dogsCheckedIn = new ArrayList<String>();
	}
	
	@Override
	public void addEmployee(String adder, String addee, String addeePword, ClientCallback obj) throws RemoteException {
		boolean employeeAdded = false;
		
		try {
			if(employeesOnline.contains(adder)) {
				if(db.employeeExist(addee) == false){
					employeeAdded = db.addEmployee(addee, addeePword);
					if(employeeAdded){
						employeeAdded = true;
						System.out.println("Employee: " + addee + ", is now registered by: " + adder);
					}
				}
			}
		} catch(Exception e) {	e.printStackTrace();	}

		obj.addEmployeeCallback(employeeAdded, adder, addee);
	}
	
	@Override
	public void removeEmployee(String remover, String removee, ClientCallback obj) throws RemoteException {
		boolean employeeRemoved = false;
		
		try {
			if(db.employeeExist(removee)){
				if(employeesOnline.contains(removee))
					employeesOnline.remove(removee);
				
				employeeRemoved = db.removeEmployee(removee);
				if(employeeRemoved){
					System.out.println("Employee: " + removee + ", is now removed by: " + remover);
				}
			}
		} catch(Exception e) {	e.printStackTrace();	}
		
		obj.removeEmployeeCallback(employeeRemoved, remover, removee);
	}
	
	@Override
	public void loginEmployee(String uname, String pword, ClientCallback obj) throws RemoteException {  
		boolean loggedIn = false;
		
		try {
			if(db.loginEmployee(uname, pword)) {
				if(!employeesOnline.contains(uname))
					loggedIn = employeesOnline.add(uname);
					if(loggedIn)
						System.out.println("Employee: " + uname + ", is now logged in");
				}
		}catch(Exception e) {	e.printStackTrace();	}
		
		obj.loginEmployeeCallback(loggedIn, uname);
	}
	
	@Override
	public void logoutEmployee(String uname, ClientCallback obj) throws RemoteException {
		boolean success = false;
		
		if(employeesOnline.contains(uname)) {
			success = employeesOnline.remove(uname);
			if(success)
				System.out.println("Employee: " + uname + ", is now logged out");
		}
		obj.logoutEmployeeCallback(success);
	}
	
	@Override
	public void searchOwner(String name, String address, ClientCallback obj) throws RemoteException {
		boolean success = false;
		
		try {
			if(db.searchOwner(name, address))
				success = true;
			
		}catch(Exception e) {	e.printStackTrace();	}
		
		obj.searchOwnerCallback(success, name, address);
	}
	@Override
	public void addOwner(String name, String address, ClientCallback obj) throws RemoteException {
		boolean ownerAdded = false;
		
		try {
			ownerAdded = db.addOwner(name, address);
			if(ownerAdded){
				System.out.println("Owner: " + name + ", is now registered");
			}

		} catch(Exception e) {	e.printStackTrace();	}
		
		obj.addOwnerCallback(ownerAdded, name, address);
	}
	@Override
	public void removeOwner(String name, String address, ClientCallback obj) throws RemoteException {
		boolean ownerRemoved = false;
		
		try {
			ownerRemoved = db.removeOwner(name, address);
			if(ownerRemoved){
				System.out.println("Owner: " + name + ", is now removed");
			}
		} catch(Exception e) {	e.printStackTrace();	}
		
		obj.removeOwnerCallback(ownerRemoved, name, address);
	}
	
	@Override
	public void addDog(String name, String owner, String address, ClientCallback obj) throws RemoteException {
		boolean dogAdded = false;
		
		try {
			if(db.searchOwner(owner, address)) {
				dogAdded = db.addDog(name, address);
				if(dogAdded){
					System.out.println("Dog: " + name + ", is now registered, owner is: " + owner);
				}
			}

		} catch(Exception e) {	e.printStackTrace();	}
		
		obj.addDogCallback(dogAdded, name, owner);
	}
	
	@Override
	public void removeDog(String name, String address, ClientCallback obj) throws RemoteException {
		boolean dogRemoved = false;
		
		try {
			if(dogsCheckedIn.contains(name))
				dogsCheckedIn.remove(name);
			
			dogRemoved = db.removeDog(name, address);
			if(dogRemoved){
				System.out.println("Dog: " + name + ", is now removed");
			}
		} catch(Exception e) {	e.printStackTrace();	}
		
		obj.removeDogCallback(dogRemoved, name, address);
	}
	
	@Override
	public void checkInDog(String name, String address, ClientCallback obj) throws RemoteException {
		boolean checkedIn = false;
	
		try {
			if(db.dogExist(name, address)) {
				checkedIn = dogsCheckedIn.add(name);
				if(checkedIn)
					System.out.println("Dog: " + name + ", is now dropped ");
			}
				
		}catch(Exception e) {	e.printStackTrace();	}
		
		obj.checkInDogCallback(checkedIn, name);
	}
	
	@Override
	public void checkOutDog(String name, String address, ClientCallback obj) throws RemoteException {
		boolean checkedOut = false;
	
		if(dogsCheckedIn.contains(name)) {
			checkedOut = dogsCheckedIn.remove(name);
			if(checkedOut)
				System.out.println("Dog: " + name + ", is now picked up");
		}
		obj.checkOutDogCallback(checkedOut, name);
	}
	
	@Override
	public void searchDog(String name, String address, ClientCallback obj) throws RemoteException {
		boolean success = false;
		
		try {
			if(db.searchDog(name, address))
				success = true;
			
		}catch(Exception e) {	e.printStackTrace();	}
		
		obj.searchDogCallback(success, name, address);
	}
	@Override
	public void presentDogs(ClientCallback obj) {
		try {
			String[] presentDogs = new String[dogsCheckedIn.size()];
			int i = 0;
			
			for(String f : dogsCheckedIn) {
				presentDogs[i] = f;
				i++;
			}
			obj.presentDogsCallback(presentDogs);
		
		}catch(Exception e) {	e.printStackTrace();	}
	}
	
	@Override
	public void allDogs(ClientCallback obj) {
		try {
			List<String> dogs = db.allDogs();
			String[] allDogNames = new String[dogs.size()];
			int i = 0;
			
			for(String f : dogs) {
				allDogNames[i] = f;
				i++;
			}
			
			obj.allDogsCallback(allDogNames);
			
		}catch(Exception e) {	e.printStackTrace();	}
	}
}
