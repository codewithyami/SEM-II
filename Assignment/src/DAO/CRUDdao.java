package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Middleware.Customer_MW;
import Middleware.Staff_MW;

public class CRUDdao {
	
	public void RegisterCustomer(Customer_MW customer) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "INSERT INTO Customer (Customer_ID,First_name, Last_name, Address, Mobile, Email, Gender, DOB, Password,UserType_ID) Values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setInt(1,customer.getCustomer_ID());
			pstat.setString(2, customer.getFirst_name());
			pstat.setString(3, customer.getLast_name());
			pstat.setString(4, customer.getAddress());
			pstat.setString(5, customer.getMobile());
			pstat.setString(6, customer.getEmail());
			pstat.setString(7, customer.getGender());
			pstat.setString(8, customer.getDOB());
			pstat.setString(9, customer.getPassword());
			pstat.setInt(10, customer.getUserType_ID());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
	public void CustomerUpdate(Customer_MW customer) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "Update customer set First_name=?, Last_name=?, Address=?, Mobile=?, Email=?, Gender=?, DOB=?, Password=? WHERE Customer_ID=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, customer.getFirst_name());
			pstat.setString(2, customer.getLast_name());
			pstat.setString(3, customer.getAddress());
			pstat.setString(4, customer.getMobile());
			pstat.setString(5, customer.getEmail());
			pstat.setString(6, customer.getGender());
			pstat.setString(7, customer.getDOB());
			pstat.setString(8, customer.getPassword());
			pstat.setInt(9, customer.getCustomer_ID());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
	public List<Customer_MW> Viewprofile(int id) {
	    List<Customer_MW> bookList = new ArrayList<Customer_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT * FROM customer WHERE Customer_ID=?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, id);
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	Customer_MW booking = new Customer_MW();
	
	            int cid = rs.getInt("Customer_ID");
	            String first_name = rs.getString("First_Name");
	            String last_name = rs.getString("Last_Name");
	            String address = rs.getString("Address");
	            String mobile = rs.getString("Mobile");
		        String email = rs.getString("Email");
	            String gender = rs.getString("Gender");
	            String dob = rs.getString("DOB");
	            String password = rs.getString("Password");
	            


	            booking.setCustomer_ID(cid);
	            booking.setFirst_name(first_name);
	            booking.setLast_name(last_name);
	            booking.setAddress(address);
	            booking.setMobile(mobile);
	            booking.setEmail(email);
	            booking.setGender(gender);
	            booking.setDOB(dob);
	            booking.setPassword(password);

	            bookList.add(booking);

	        }
	        pstat.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error: " + e.getMessage());
	    }
	    return bookList;
	}

	public List<Staff_MW> StaffViewprofile(int id) {
	    List<Staff_MW> bookList = new ArrayList<Staff_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT * FROM staff WHERE Staff_ID=?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, id);
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	Staff_MW booking = new Staff_MW();
	
	            int cid = rs.getInt("Staff_ID");
	            String first_name = rs.getString("Staff_First_Name");
	            String last_name = rs.getString("Staff_Last_Name");
	            String address = rs.getString("Staff_Address");
	            String mobile = rs.getString("Staff_Mobile");
		        String email = rs.getString("Staff_Email");
	            String gender = rs.getString("Staff_Gender");
	            String dob = rs.getString("Staff_DOB");
	            String password = rs.getString("Staff_Password");
	            


	            booking.setStaff_ID(id);
	            booking.setStaff_First_name(first_name);
	            booking.setStaff_Last_name(last_name);
	            booking.setStaff_Address(address);
	            booking.setStaff_Mobile(mobile);
	            booking.setStaff_Email(email);
	            booking.setStaff_Gender(gender);
	            booking.setStaff_DOB(dob);
	            booking.setStaff_Password(password);

	            bookList.add(booking);

	        }
	        pstat.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error: " + e.getMessage());
	    }
	    return bookList;
	}
	
	public void StaffUpdate(Staff_MW staff) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "Update Staff set Staff_First_name=?, Staff_Last_name=?, Staff_Address=?, Staff_Mobile=?, Staff_Email=?, Staff_Gender=?, Staff_DOB=?, Staff_Password=? WHERE Staff_ID=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, staff.getStaff_First_name());
			pstat.setString(2, staff.getStaff_Last_name());
			pstat.setString(3, staff.getStaff_Address());
			pstat.setString(4, staff.getStaff_Mobile());
			pstat.setString(5, staff.getStaff_Email());
			pstat.setString(6, staff.getStaff_Gender());
			pstat.setString(7, staff.getStaff_DOB());
			pstat.setString(8, staff.getStaff_Password());
			pstat.setInt(9, staff.getStaff_ID());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
}
