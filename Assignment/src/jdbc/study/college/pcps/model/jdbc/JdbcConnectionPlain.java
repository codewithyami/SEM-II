package jdbc.study.college.pcps.model.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcConnectionPlain {
	public static void selectQuery() {
		String dbUrl = "jdbc:mysql://localhost:3306/level4A";
		String username = "root";
		String password = "";
		String selectQuery="select name,regdno FROM students";
		try {
			Connection conn= DriverManager.getConnection(dbUrl, username, password);
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(selectQuery);
			while(rs.next()) {
				System.out.println("Students Name: "+rs.getString("name")+", RegdNo: "+rs.getString("regdno"));
			}
			st.close();
			rs.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
	}
	public static void selectQueryWithReg(String regdn) {
		String dbUrl = "jdbc:mysql://localhost:3306/level4A";
		String username = "root";
		String password = "";
		String selectQuery="select name,regdno FROM students where regdno='"+regdn+"'";
		try {
			Connection conn= DriverManager.getConnection(dbUrl, username, password);
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(selectQuery);
			while(rs.next()) {
				System.out.println("Students Name: "+rs.getString("name")+", RegdNo: "+rs.getString("regdno"));
			}
			st.close();
			rs.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
	}
	public static void insertQuery(String name,String age) {
		String dbUrl = "jdbc:mysql://localhost:3306/level4A";
		String username = "root";
		String password = "";
		String insertQuery="INSERT INTO students (name,regdno) VALUES('"+name+"', '"+age+"')";
		try {
			Connection conn= DriverManager.getConnection(dbUrl, username, password);
			Statement st=conn.createStatement();
			st.executeUpdate(insertQuery);
			st.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		
	}
	public static void updateQuery() {
		String dbUrl = "jdbc:mysql://localhost:3306/level4A";
		String username = "root";
		String password = "";
		String updateQuery="UPDATE students SET name='Sukman3344' WHERE regdno='1234'";
		try {
			Connection conn= DriverManager.getConnection(dbUrl, username, password);
			Statement st=conn.createStatement();
			st.executeUpdate(updateQuery);
			st.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		
	}
	public static void deleteQuery() {
		String dbUrl = "jdbc:mysql://localhost:3306/level4A";
		String username = "root";
		String password = "";
		String deleteQuery="DELETE FROM students WHERE regdno='123'";
		try {
			Connection conn= DriverManager.getConnection(dbUrl, username, password);
			Statement st=conn.createStatement();
			st.executeUpdate(deleteQuery);
			st.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}
		
	}

	public static void main(String[] args) {
		//insertQuery("Ishan","BA253");
		//selectQuery();
		//selectQueryWithReg("123");
		updateQuery();
	}

}
