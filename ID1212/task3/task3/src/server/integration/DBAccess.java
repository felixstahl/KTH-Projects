package server.integration;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.DriverManager;
//import java.sql.SQLException;

public class DBAccess{
	String url = "jdbc:sqlite:C:\\Users\\mr_fe\\Desktop\\Natverksprogrammering\\task3\\src\\server\\integration\\filecatalog.sqlite";
	private Connection connection;
	private PreparedStatement createUserStmt;
    private PreparedStatement findUserStmt;
    private PreparedStatement loginUserStmt;
    
    //private PreparedStatement findAllAccountsStmt;
    //private PreparedStatement checkFileStmt;
    //private PreparedStatement storeFileStmt;
    //private PreparedStatement updateFileStmt;
    //private PreparedStatement findAllFilesStmt;
    //private PreparedStatement deleteFileStmt;
	
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
            
            if (rows != 1)
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
			if(username.equals(uname) && password.equals(pword)) {
				return true;
			}
		}
		return false;
	}
}