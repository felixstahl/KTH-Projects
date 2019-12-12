package se.felix.integration;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbHandler {
	String url = "jdbc:sqlite:C:\\Users\\mr_fe\\Desktop\\Natverksprogrammering\\task4\\src\\main\\java\\se\\felix\\integration\\homework4.sqlite";
	public PreparedStatement getRateStmt;
	private Connection connection = null;
	
	public DbHandler(){
		try	{
			connection = DriverManager.getConnection(url);
			if(connection != null){
		    	DatabaseMetaData meta = connection.getMetaData();
		    	System.out.println("The driver name is: " + meta.getDriverName());
		    	System.out.println("Connected to database");
		    }
		}catch (SQLException e){
			System.out.println("Something went wrong in DbHandler.java: " + e.getMessage());
		}
	}
	
	public double convert(String from, String to){
		double rate = 1;
		try {
			String stmt = "SELECT * from " + from + " WHERE Currency = ?";
			getRateStmt = connection.prepareStatement(stmt);
			getRateStmt.setString(1, to);
			ResultSet result = getRateStmt.executeQuery();
			
			if(result.next()) {
				rate = result.getDouble("RATE");
			}
			return rate;
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rate;
	}

}
