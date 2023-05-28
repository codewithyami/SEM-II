package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	String dbURL = "jdbc:mysql://localhost:3306/hotelbookingsystem";
	String username = "root";
	String password = "";
	Connection conn = null;
	
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(dbURL,username,password);		
		}
		catch(Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		return conn;
	}
	

}
