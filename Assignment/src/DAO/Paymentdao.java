package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Middleware.Payment_MW;

public class Paymentdao {
	public void Bill(Payment_MW payment) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "INSERT INTO payment (Payment_ID ,Booking_ID, Date, Payment_Mode, Total_Payment, Payment_Status) Values(?,?,?,?,?,?)";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setInt(1, payment.getPayment_ID());
			pstat.setInt(2, payment.getBooking_ID());
			pstat.setString(3, payment.getPayment_Date());
			pstat.setString(4, payment.getPayment_Mode());
			pstat.setDouble(5, payment.getTotal_Payment());
			pstat.setString(6, payment.getPayment_Status());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
	public void UpdateBill(Payment_MW payment) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "Update payment set Payment_Mode=?, Payment_Status=? WHERE Payment_ID=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, payment.getPayment_Mode());
			pstat.setString(2, "Paid");
			pstat.setInt(3, payment.getPayment_ID());
			
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
	public void Updateoldbill(Payment_MW payment) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "Update payment set Date=?, Total_Payment=? WHERE Payment_ID=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, payment.getPayment_Date());
			pstat.setDouble(2, payment.getTotal_Payment());
			pstat.setInt(3, payment.getPayment_ID());
			
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
	public List<Payment_MW> ViewPayment(Payment_MW payment) {
	    List<Payment_MW> bookList = new ArrayList<Payment_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT p.* FROM payment p LEFT JOIN booking b ON p.Booking_ID = b.Booking_ID WHERE p.Booking_ID=?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, payment.getBooking_ID());
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
//	        	ReceptionistCheckIN_MW booking = new ReceptionistCheckIN_MW();
	        	int pid = rs.getInt("Payment_ID");
	        	int bid = rs.getInt("Booking_ID");
	        	String date = rs.getString("Date");
	        	String mode = rs.getString("Payment_Mode");
	        	double price = rs.getDouble("Total_Payment");
	        	String status = rs.getString("Payment_Status");
	        	
	        	payment.setPayment_ID(pid);
	        	payment.setBooking_ID(bid);
	        	payment.setPayment_Date(date);
	        	payment.setPayment_Mode(mode);
	        	payment.setTotal_Payment(price);
	        	payment.setPayment_Status(status);
	        	



	            bookList.add(payment);

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
}
