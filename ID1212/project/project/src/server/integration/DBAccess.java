package server.integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBAccess{
	private Connection connection;

	private String url = "jdbc:ucanaccess://C:\\Users\\mr_fe\\Desktop\\Natverksprogrammering\\project\\src\\server\\integration\\daycare.accdb";
	private String driver = "net.ucanaccess.jdbc.UcanaccessDriver";

	private PreparedStatement employeeExistStmt;
	private PreparedStatement addEmployeeStmt;
	private PreparedStatement removeEmployeeStmt;
	private PreparedStatement loginEmployeeStmt;
	
	private PreparedStatement searchOwnerStmt;
	private PreparedStatement addOwnerStmt;
	private PreparedStatement removeOwnerStmt;
	
	private PreparedStatement addDogStmt;
	private PreparedStatement removeDogStmt;
	private PreparedStatement dogExistStmt;
	private PreparedStatement searchDogStmt;
	private PreparedStatement allDogsStmt;
	
	public DBAccess(){
		try	{

			Class.forName(driver);
			connection = DriverManager.getConnection(url);
			if(connection != null){
				
				connection = DriverManager.getConnection(url);

		    	System.out.println("Connection to database established");
		    }
		}catch (Exception e){
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
	
	public boolean searchOwner(String name, String address) throws SQLException {
		searchOwnerStmt = connection.prepareStatement("SELECT * from owners WHERE ownerName = ? AND ownerAddress = ?");
		searchOwnerStmt.setString(1, name);
		searchOwnerStmt.setString(2, address);
		ResultSet result = searchOwnerStmt.executeQuery();
		
		if(result.next())
			return true;
		return false;
	}
	
	public boolean addOwner(String name, String address) throws SQLException {
		addOwnerStmt = connection.prepareStatement("INSERT INTO owners (ownerName, ownerAddress) VALUES (?, ?)");
		addOwnerStmt.setString(1, name);
		addOwnerStmt.setString(2, address);
        
        int rows = addOwnerStmt.executeUpdate();
        if(rows != 1)
        	return false;
            
		return true;
	}
	
	public boolean removeOwner(String name, String address) throws SQLException {
		removeOwnerStmt = connection.prepareStatement("DELETE FROM owners WHERE ownerName = ? AND ownerAddress = ?");
		removeOwnerStmt.setString(1, name);
		removeOwnerStmt.setString(2, address);
		
		int rows = removeOwnerStmt.executeUpdate();
        if(rows != 1)
        	return false; 
		
		return true;
	}
	
	public boolean addDog(String name, String address) throws SQLException {
		addDogStmt = connection.prepareStatement("INSERT INTO dogs (dogName, ownerAddress) VALUES (?, ?)");
		addDogStmt.setString(1, name);
		addDogStmt.setString(2, address);
        
        int rows = addDogStmt.executeUpdate();
        if(rows != 1)
        	return false;
		
		return true;
	}
	
	public boolean removeDog(String name, String address) throws SQLException {
		removeDogStmt = connection.prepareStatement("DELETE FROM dogs WHERE dogName = ? AND ownerAddress = ?");
		removeDogStmt.setString(1, name);
		removeDogStmt.setString(2, address);
		
		int rows = removeDogStmt.executeUpdate();
        if(rows != 1)
        	return false; 
		
		return true;
	}
	
	public boolean dogExist(String name, String address) throws SQLException {
		dogExistStmt = connection.prepareStatement("SELECT * from dogs WHERE dogName = ? AND ownerAddress = ?");
		dogExistStmt.setString(1, name);
		dogExistStmt.setString(2, address);
		ResultSet result = dogExistStmt.executeQuery();
		
		if(result.next())
			return true;
		return false;
	}
	
	public boolean searchDog(String name, String address) throws SQLException {
		searchDogStmt = connection.prepareStatement("SELECT * from dogs WHERE dogName = ? AND ownerAddress = ?");
		searchDogStmt.setString(1, name);
		searchDogStmt.setString(2, address);
		ResultSet result = searchDogStmt.executeQuery();
		
		if(result.next())
			return true;
		return false;
	}
	
	public List<String> allDogs() throws SQLException {
		List<String> dogs = new ArrayList<>();
		
		allDogsStmt = connection.prepareStatement("SELECT * from dogs");
		ResultSet result = allDogsStmt.executeQuery();
		while(result.next()) {
			dogs.add(result.getString("dogName"));
		}
			
		return dogs;
	}
}