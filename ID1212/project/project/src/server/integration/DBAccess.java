package server.integration;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBAccess{
	String url = "jdbc:sqlite:C:\\Users\\mr_fe\\Desktop\\Natverksprogrammering\\project\\src\\server\\integration\\daycare.sqlite";
	private Connection connection;
	
	private PreparedStatement employeeExistStmt;
	private PreparedStatement addEmployeeStmt;
	//private PreparedStatement removeEmployeeStmt;
	private PreparedStatement loginEmployeeStmt;
	//private PreparedStatement logoutEmployeeStmt;
	//private PreparedStatement addDogStmt;
	//private PreparedStatement removeDogStmt;
	//private PreparedStatement checkInDogStmt;
	//private PreparedStatement checkOutDogStmt;
	//private PreparedStatement searchDogStmt;
	//private PreparedStatement allDogsOfOwnerStmt;
	//private PreparedStatement allDogsStmt;
	
	public DBAccess(){
		try	{
			connection = DriverManager.getConnection(url);
			if(connection != null){
		    	DatabaseMetaData meta = connection.getMetaData();
		    	System.out.println("The driver name is: " + meta.getDriverName());
		    	System.out.println("Connection to database established");
		    }
		}catch (SQLException e){
			System.out.println("Something went wrong in DBAccess.java: " + e.getMessage());
		}
	}
	
	public boolean employeeExist(String uname) throws SQLException {
		employeeExistStmt = connection.prepareStatement("SELECT * from employees WHERE username = ?");
		employeeExistStmt.setString(1, uname);
		ResultSet result = employeeExistStmt.executeQuery();
		
		if(result.next())
			return true;
		return false;
	}
	
	public boolean addEmployee(String uname, String pword) throws SQLException {
		addEmployeeStmt = connection.prepareStatement("INSERT INTO employees (username, password) VALUES (?, ?)");
		addEmployeeStmt.setString(1, uname);
		addEmployeeStmt.setString(2, pword);
        
        int rows = addEmployeeStmt.executeUpdate();
        if(rows != 1)
        	return false;
            
		return true;
	}
	
//	public boolean removeEmployee(String uname) throws SQLException {
//		try {
//			removeEmployeeStmt = connection.prepareStatement("DELETE FROM employees WHERE username = ?");
//			removeEmployeeStmt.setString(1,uname);
//			removeEmployeeStmt.executeUpdate(); 
//			
//		} catch(Exception e) {	e.printStackTrace();	}
//		
//		return false;
//	}
	
	public boolean loginEmployee(String uname, String pword) throws SQLException {
		loginEmployeeStmt = connection.prepareStatement("SELECT * from employees WHERE username = ? AND password = ?");
		loginEmployeeStmt.setString(1, uname);
		loginEmployeeStmt.setString(2, pword);
		ResultSet result = loginEmployeeStmt.executeQuery();
		if(result.next()) {
			String username = result.getString("USERNAME");
			String password = result.getString("PASSWORD");
			if(username.equalsIgnoreCase(uname) && password.equalsIgnoreCase(pword))
				return true;
		}
		return false;
	}
//	
//	public boolean logoutEmployee(String uname, String pword) throws SQLException {
//		try {
//			
//			
//		} catch(Exception e) {	e.printStackTrace();	}
//		
//		return false;
//	}
//	
//	public boolean addDog(String name, String owner) throws SQLException {
//		try {
//			addDogStmt = connection.prepareStatement("INSERT INTO dogs (name, owner) VALUES (?, ?)");
//			addDogStmt.setString(1, name);
//			addDogStmt.setString(2, owner);
//            
//            int rows = addDogStmt.executeUpdate();
//            if(rows != 1)
//            	return false;
//            
//		}catch(SQLException e) {	e.printStackTrace();	}
//		
//		return true;
//	}
//	
//	public boolean removeDog(String name, String owner) throws SQLException {
//		try {
//			removeDogStmt = connection.prepareStatement("DELETE FROM Dogs WHERE name = ? AND owner = ?");
//			removeDogStmt.setString(1,name);
//			removeDogStmt.setString(2,owner);
//			removeDogStmt.executeUpdate(); 
//			
//		} catch(Exception e) {	e.printStackTrace();	}
//		
//		return false;
//	}
//	
//	public boolean searchDog(String name, String owner) throws SQLException {
//		searchDogStmt = connection.prepareStatement("SELECT * from dogs WHERE name = ? AND owner = ?");
//		searchDogStmt.setString(1, name);
//		searchDogStmt.setString(1, owner);
//		ResultSet result = searchDogStmt.executeQuery();
//		
//		if(result.next())
//			return true;
//		return false;
//	}
//	
//	public List<String> allDogsOfOwner(String owner) throws SQLException {
//		List<String> dogs = new ArrayList<>();
//		try {
//			allDogsOfOwnerStmt = connection.prepareStatement("SELECT * from dogs WHERE owner = ?");
//			searchDogStmt.setString(1, owner);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return dogs;
//	}
//	
//	public Map<String, String> allDogs() {
//		Map<String, String> dogs = new HashMap<>();
//		try {
//			allDogsStmt = connection.prepareStatement("SELECT * from dogs");
//			ResultSet result = allDogsStmt.executeQuery();
//			while(result.next()) {
//				dogs.put(result.getString("name"),result.getString("owner"));
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return dogs;
//	}
	

	
	
	
	
	
	
//	public boolean uploadMetaData(String[] attributes) throws SQLException {
//		try {	//see if file already exists in db
//			if(fileSearch(attributes))
//				return false;
//			else {
//				//upload file to db
//				uploadMetaDataStmt = connection.prepareStatement("INSERT INTO files (filename, owner, size) VALUES (?, ?, ?)");
//				uploadMetaDataStmt.setString(1, attributes[0]);
//				uploadMetaDataStmt.setString(2, attributes[1]);
//				uploadMetaDataStmt.setString(3, attributes[2]);
//				
//		        int rows = uploadMetaDataStmt.executeUpdate();
//		        if(rows != 1)
//		        	return false;
//			}
//			
//		}catch(SQLException e) {	e.printStackTrace();	}
//	
//		return true;
//	}
	
//	public boolean fileSearch(String[] attributes) {	//true for existing, false for not existing
//		try {
//			searchFileStmt = connection.prepareStatement("SELECT * from files WHERE filename = ?");
//			searchFileStmt.setString(1, attributes[0]);
//			ResultSet result = searchFileStmt.executeQuery();
//			return result.next();
//			
//		}catch(Exception e) {	e.printStackTrace();	}
//		
//		return false;
//	}
	
//	public String[] downloadMetaData(String fileName) throws SQLException {
//		String[] attributes = new String[4];
//		attributes[0] = "0";
//		
//		try {	//see if file already exists in db
//			if(fileSearch(attributes))
//				return attributes;
//			else {
//				//download meta data from file
//				downloadMetaDataStmt = connection.prepareStatement("SELECT * from files WHERE filename = ?");
//				downloadMetaDataStmt.setString(1, fileName);
//				ResultSet result = downloadMetaDataStmt.executeQuery(); 
//				if(result.next()) {
//					attributes[0] = "1";
//					attributes[1] = result.getString("FILENAME");
//					attributes[2] = result.getString("OWNER");
//					attributes[3] = result.getString("SIZE");
//				}
//				return attributes;
//			}
//		}catch(Exception e) {	e.printStackTrace();	}
//		
//		return attributes;
//	}
}