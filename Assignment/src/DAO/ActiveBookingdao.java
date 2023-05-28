package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Middleware.ActiveBooking_MW;
import Middleware.Payment_MW;

public class ActiveBookingdao {
	
	public List<ActiveBooking_MW> ActiveBooking(ActiveBooking_MW active) {
	    List<ActiveBooking_MW> bookList = new ArrayList<ActiveBooking_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT * FROM booking WHERE Customer_ID=? AND Booking_Status_NO=1";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, active.getCid());
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	            int bid = rs.getInt("Booking_ID");
	            active.setBid(bid);

	            bookList.add(active);

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
	
	public List<Payment_MW> ActiveBill(Payment_MW active) {
	    List<Payment_MW> bookList = new ArrayList<Payment_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT * FROM payment WHERE Booking_ID=?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, active.getBooking_ID());
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	            int pid = rs.getInt("Payment_ID");
	            active.setPayment_ID(pid);

	            bookList.add(active);

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
	
	public boolean CheckBill(Payment_MW active) {
//	    List<Payment_MW> bookList = new ArrayList<Payment_MW>();
		boolean check = false;
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT * FROM payment WHERE Booking_ID=?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, active.getBooking_ID());
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
//	            int pid = rs.getInt("Payment_ID");
//	            active.setPayment_ID(pid);
//
//	            bookList.add(active);
	        	check = true;

	        }
	        pstat.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	    	check = false;
	        e.printStackTrace();
	        System.out.println("Error: " + e.getMessage());
	    }
	    return check;
	}

}
