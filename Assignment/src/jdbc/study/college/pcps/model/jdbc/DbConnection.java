package jdbc.study.college.pcps.model.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	String dbUrl = "jdbc:mysql://localhost:3306/level4A";
	String username = "root";
	String password = "";
	Connection conn = null;

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(dbUrl, username, password);

		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		return conn;
	}
}
