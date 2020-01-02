package server.integration;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
import java.util.List;

public class DBAccess{
	//String url = "jdbc:sqlite:C:\\Users\\mr_fe\\Desktop\\Natverksprogrammering\\project\\src\\server\\integration\\daycare.sqlite";
	private Connection connection;

	private String url = "jdbc:ucanaccess://C:\\Users\\mr_fe\\Desktop\\Natverksprogrammering\\project\\src\\server\\integration\\daycare.accdb";
	private String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
	//private String user = "";
	//private String password = "";


	
			
	//private PreparedStatement employeeAuthStmt;
	private PreparedStatement employeeExistStmt;
	private PreparedStatement addEmployeeStmt;
	private PreparedStatement removeEmployeeStmt;
	private PreparedStatement loginEmployeeStmt;
	private PreparedStatement addDogStmt;
	private PreparedStatement removeDogStmt;
	private PreparedStatement dogExistStmt;
	private PreparedStatement searchDogStmt;
	//private PreparedStatement allDogsOfOwnerStmt;
	private PreparedStatement allDogsStmt;
	
	public DBAccess(){
		try	{
			// register the driver with DriverManager
			Class.forName(driver);
			connection = DriverManager.getConnection(url);
			if(connection != null){
				
				// create a connection to the database
				connection = DriverManager.getConnection(url);
				// Set the auto commit of the connection to false.
				// An explicit commit will be required in order to accept
				// any changes done to the DB through this connection.
				System.out.println("howdy");
				//connection.setAutoCommit(false);
		    	//DatabaseMetaData meta = connection.getMetaData();
		 //   	System.out.println("The driver name is: " + meta.getDriverName());
		    	System.out.println("Connection to database established");
		    }
		}catch (Exception e){
			System.out.println("Something went wrong in DBAccess.java: " + e.getMessage());
		}
	}
	
//	public boolean employeeAuthentication(String uname, String pword) throws SQLException {
//		employeeAuthStmt = connection.prepareStatement("SELECT * from employees WHERE username = ? AND password = ?");
//		employeeAuthStmt.setString(1, uname);
//		employeeAuthStmt.setString(2, pword);
//		ResultSet result = employeeAuthStmt.executeQuery();
//		
//		if(result.next()) {
//			String username = result.getString("USERNAME");
//			String password = result.getString("PASSWORD");
//			if(username.equalsIgnoreCase(uname) && password.equalsIgnoreCase(pword))
//				return true;
//		}
//		return false;
//	}
	
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
	
	public boolean removeEmployee(String uname) throws SQLException {
		removeEmployeeStmt = connection.prepareStatement("DELETE FROM employees WHERE username = ?");
		removeEmployeeStmt.setString(1, uname);
		removeEmployeeStmt.executeUpdate(); 
		
		int rows = removeEmployeeStmt.executeUpdate();
        if(rows != 1)
        	return false;
		
		return true;
	}
	
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
	
	public boolean addDog(String name, String owner) throws SQLException {
		addDogStmt = connection.prepareStatement("INSERT INTO dogs (name, owner) VALUES (?, ?)");
		addDogStmt.setString(1, name);
		addDogStmt.setString(2, owner);
        
        int rows = addDogStmt.executeUpdate();
        if(rows != 1)
        	return false;
		
		return true;
	}
	
	public boolean removeDog(String name, String owner) throws SQLException {
		removeDogStmt = connection.prepareStatement("DELETE FROM dogs WHERE name = ? AND owner = ?");
		removeDogStmt.setString(1, name);
		removeDogStmt.setString(2, owner);
		
		int rows = removeDogStmt.executeUpdate();
        if(rows != 1)
        	return false; 
		
		return true;
	}
	
	public boolean dogExist(String name, String owner) throws SQLException {
		dogExistStmt = connection.prepareStatement("SELECT * from dogs WHERE name = ? AND owner = ?");
		dogExistStmt.setString(1, name);
		dogExistStmt.setString(2, owner);
		ResultSet result = dogExistStmt.executeQuery();
		
		if(result.next())
			return true;
		return false;
	}
	
	public boolean searchDog(String name, String owner) throws SQLException {
		searchDogStmt = connection.prepareStatement("SELECT * from dogs WHERE name = ? AND owner = ?");
		searchDogStmt.setString(1, name);
		searchDogStmt.setString(2, owner);
		ResultSet result = searchDogStmt.executeQuery();
		
		if(result.next())
			return true;
		return false;
	}
	
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
	
	public List<String> allDogs() throws SQLException {
		List<String> dogs = new ArrayList<>();
		
		allDogsStmt = connection.prepareStatement("SELECT * from dogs");
		ResultSet result = allDogsStmt.executeQuery();
		while(result.next()) {
			dogs.add(result.getString("name"));
		}
			
		return dogs;
	}
}