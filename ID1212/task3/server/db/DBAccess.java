package server.db;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.DriverManager;
//import java.sql.SQLException;

public class DBAccess {
  public DBAccess(){
    String url = "jdbc:sqlite:C:/Users/mr_fe/Desktop/Natverksprogrammering/ID1212/task3/server/db/database.db";

    try(Connection conn = DriverManager.getConnection(url)){
      if(conn != null){
        DatabaseMetaData meta = conn.getMetaData();
        System.out.println("The driver name is " + meta.getDriverName());
        System.out.println("A new database has been created.");
      }
    }catch (SQLException e){
      System.out.println("bajs " + e.getMessage());
    }
  }
}

// java -classpath ".;sqlite-jdbc-3.27.2.1.jar" net.sqlitetutorial.Connect
//java -classpath ".;sqlite-jdbc-3.27.2.1.jar" Main
