package jdbc.study.college.pcps.model.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.study.college.pcps.model.jdbc.DbConnection;
import jdbc.study.college.pcps.model.jdbc.StudentJDBCMODEL;

public class StudentDaoDirect {
	public void insertStudentDetailsUsingPreparedStatement() {
		try {
			Connection conn = new DbConnection().getConnection();
			String sql = "INSERT INTO students (name,regdno) VALUES(?, ?)";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, "xyz Gurung");
			pstat.setString(2, "1345");
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			// TODO: handle exception
		}
	}
	public List<StudentJDBCMODEL> getAllStudentDetails() {
		List<StudentJDBCMODEL> stdList = new ArrayList<StudentJDBCMODEL>();
		try {
			Connection conn = new DbConnection().getConnection();
			String sql = "SELECT name,regdno FROM students";
			PreparedStatement pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				StudentJDBCMODEL std = new StudentJDBCMODEL();
				std.setName(rs.getString("name"));
				std.setRegdNo(rs.getString("regdno"));
				stdList.add(std);
			}
			pstat.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());

		}
		return stdList;

	}
	public static void main(String[] args) {
	//	new StudentDaoDirect().insertStudentDetailsUsingPreparedStatement();
		List<StudentJDBCMODEL> stdList=new StudentDaoDirect().getAllStudentDetails();
		for(StudentJDBCMODEL std:stdList) {
			System.out.println("Name"+std.getName());
			System.out.println("Name"+std.getRegdNo());
		}
		
	}
}
