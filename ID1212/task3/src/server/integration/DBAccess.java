package server.integration;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAccess{
	String url = "jdbc:sqlite:C:\\Users\\mr_fe\\Desktop\\Natverksprogrammering\\task3\\src\\server\\integration\\filecatalog.sqlite";
	private Connection connection;
	private PreparedStatement createUserStmt;
    private PreparedStatement findUserStmt;
    private PreparedStatement loginUserStmt;
    private PreparedStatement uploadMetaDataStmt;
    private PreparedStatement downloadMetaDataStmt;
    private PreparedStatement searchFileStmt;
    private PreparedStatement printStmt;
	
	public DBAccess(){
		try	{
			connection = DriverManager.getConnection(url);
			if(connection != null){
		    	DatabaseMetaData meta = connection.getMetaData();
		    	System.out.println("The driver name is: " + meta.getDriverName());
		    	System.out.println("Connected to database");
		    }
		}catch (SQLException e){
			System.out.println("Something went wrong in DBAccess.java: " + e.getMessage());
		}
	}
	
	public boolean createUser(String uname, String pword) throws SQLException {
		try {
			createUserStmt = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
			createUserStmt.setString(1, uname);
            createUserStmt.setString(2, pword);
            
            int rows = createUserStmt.executeUpdate();
            if(rows != 1)
            	return false;
            
		}catch(SQLException e) {	System.out.println(e.getMessage());	}
		
		return true;
	}
	
	public boolean userExist(String uname) throws SQLException {
		findUserStmt = connection.prepareStatement("SELECT * from users WHERE username = ?");
		findUserStmt.setString(1, uname);
		ResultSet result = findUserStmt.executeQuery();
		
		if(result.next())
			return true;
		return false;
	}
	
	public boolean loginUser(String uname, String pword) throws SQLException {
		loginUserStmt = connection.prepareStatement("SELECT * from users WHERE username = ? AND password = ?");
		loginUserStmt.setString(1, uname);
		loginUserStmt.setString(2, pword);
		ResultSet result = loginUserStmt.executeQuery();
		if(result.next()) {
			String username = result.getString("USERNAME");
			String password = result.getString("PASSWORD");
			if(username.equalsIgnoreCase(uname) && password.equalsIgnoreCase(pword))
				return true;
		}
		return false;
	}
	
	public boolean uploadMetaData(String[] attributes) throws SQLException {
		try {	//see if file already exists in db
			if(fileSearch(attributes))
				return false;
			else {
				//upload file to db
				uploadMetaDataStmt = connection.prepareStatement("INSERT INTO files (filename, owner, size) VALUES (?, ?, ?)");
				uploadMetaDataStmt.setString(1, attributes[0]);
				uploadMetaDataStmt.setString(2, attributes[1]);
				uploadMetaDataStmt.setString(3, attributes[2]);
				
		        int rows = uploadMetaDataStmt.executeUpdate();
		        if(rows != 1)
		        	return false;
			}
			
		}catch(SQLException e) {	System.out.println("error catched in upload" + e.getMessage());	}
	
		return true;
	}
	
	public boolean fileSearch(String[] attributes) {	//true for existing, false for not existing
		try {
			searchFileStmt = connection.prepareStatement("SELECT * from files WHERE filename = ?");
			searchFileStmt.setString(1, attributes[0]);
			ResultSet result = searchFileStmt.executeQuery();
			return result.next();
			
		}catch(Exception e) {	System.out.println("Error catched in flieSearch");	}
		
		return false;
	}
	
	public String[] downloadMetaData(String fileName) throws SQLException {
		String[] attributes = new String[4];
		attributes[0] = "0";
		
		try {	//see if file already exists in db
			if(fileSearch(attributes))
				return attributes;
			else {
				//download meta data from file
				downloadMetaDataStmt = connection.prepareStatement("SELECT * from files WHERE filename = ?");
				downloadMetaDataStmt.setString(1, fileName);
				ResultSet result = downloadMetaDataStmt.executeQuery(); 
				if(result.next()) {
					attributes[0] = "1";
					attributes[1] = result.getString("FILENAME");
					attributes[2] = result.getString("OWNER");
					attributes[3] = result.getString("SIZE");
				}
				return attributes;
			}
		}catch(Exception e) {	System.out.println(e.getMessage());	}
		
		return attributes;
	}
	
	public void printFiles() {
		try {
			printStmt = connection.prepareStatement("SELECT * from files");
			ResultSet result = printStmt.executeQuery();
			while(result.next()) {
				System.out.println("This file is in the DB: " + result.getString("FILENAME"));
			}
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
}