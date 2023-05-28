package jdbc.study.college.pcps.model.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.study.college.pcps.model.Student;
import jdbc.study.college.pcps.model.jdbc.DbConnection;
import jdbc.study.college.pcps.model.jdbc.StudentJDBCMODEL;

public class StudentDao {
	public void insertStudentDetailsUsingPreparedStatement() {
		try {
			Connection conn = new DbConnection().getConnection();
			String sql = "INSERT INTO students (name,regdno) VALUES(?, ?)";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, "Alif Gurung");
			pstat.setString(2, "123");
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			// TODO: handle exception
		}
	}
	public void insertStudentDetailsUsingPreparedStatement(Student std) {
		try {
			Connection conn = new DbConnection().getConnection();
			String sql = "INSERT INTO students_new (name,regdno,section,age) VALUES(?, ?, ?, ?)";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, std.getName());
			pstat.setString(2, std.getRegdNo());
			pstat.setString(3, std.getSection());
			pstat.setInt(4, std.getAge());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
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
	
	public List<Student> getAllStudentDetail() {
		List<Student> stdList = new ArrayList<Student>();
		try {
			Connection conn = new DbConnection().getConnection();
			String sql = "SELECT * FROM students_new";
			PreparedStatement pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				Student std = new Student();
				std.setStudentId(rs.getInt("id"));
				std.setName(rs.getString("name"));
				std.setRegdNo(rs.getString("regdno"));
				std.setSection(rs.getString("section"));
				std.setAge(rs.getInt("age"));
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

	public void updateStudent() {
		try {
			Connection conn = new DbConnection().getConnection();
			String sql = "UPDATE students SET name=? WHERE regdno=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, "New Name");
			pstat.setString(2, "123");
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void deleteStudentRecord() {
		try {
			Connection conn = new DbConnection().getConnection();
			String sql = "DELETE FROM students WHERE pid=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, "123");
			pstat.executeUpdate();
			pstat.close();
			conn.close();// Close Connection
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
	}

}
