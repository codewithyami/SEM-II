package jdbc.study.college.pcps.model.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.study.college.pcps.model.UserAuth;
import jdbc.study.college.pcps.model.jdbc.DbConnection;

public class UserDao {
	public boolean checkUserAuth(UserAuth userAuth) {
		boolean autherize = false;
		try {
			Connection conn = new DbConnection().getConnection();
			String sql = "SELECT * FROM users where username=? and password=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, userAuth.getUser());
			pstat.setString(2, userAuth.getPass());
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				autherize = true;
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
