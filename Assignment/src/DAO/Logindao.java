package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Middleware.Login_MW;

public class Logindao {
	
	public void Login() {
		
	}
	
	public boolean checkUserAuth(Login_MW userAuth) {
		boolean autherize = false;
		try {

			Connection conn = new Connect().getConnection();
			String sql = "SELECT * FROM customer where Mobile=? and Password=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, userAuth.getMobile());
			pstat.setString(2, userAuth.getPassword());
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				autherize = true;
				int id = rs.getInt("Customer_ID");
				String Name = rs.getString("First_Name");
				String LastName = rs.getString("Last_Name");
				String password = rs.getString("Password");
				userAuth.setId(id);
				userAuth.setNewPassword(password);
				userAuth.setName(Name+" "+LastName);
			}
			pstat.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			autherize = false;
		}
		return autherize;
	}
	
	public boolean checkStaffAuth(Login_MW userAuth) {
		boolean autherize = false;
		try {

			Connection conn = new Connect().getConnection();
			String sql = "SELECT * FROM staff where Staff_Mobile=? and Staff_Password LIKE BINARY ?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, userAuth.getMobile());
			pstat.setString(2, userAuth.getPassword());
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				autherize = true;
				int id = rs.getInt("Staff_ID");
				int role = rs.getInt("Role_NO");
//				Login_MW lg = new Login_MW();
				userAuth.setId(id);
				userAuth.setRole(role);
				String Name = rs.getString("Staff_First_Name");
				String LastName = rs.getString("Staff_Last_Name");
				String password = rs.getString("Staff_Password");
				userAuth.setName(Name+" "+LastName);
				userAuth.setNewPassword(password);
//				System.out.println(userAuth.getId());
//				System.out.println(Name);
			}
			pstat.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			autherize = false;
		}
		return autherize;
	}

}
