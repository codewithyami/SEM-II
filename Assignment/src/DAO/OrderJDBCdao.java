package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Middleware.Services_MW;

public class OrderJDBCdao {
	public void MakeOrder(Services_MW service) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "INSERT INTO services (Service_ID, Booking_ID, Date, Item_ID, Quantity,Status) Values(?,?,?,?,?,?)";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setInt(1,service.getService_ID());
			pstat.setInt(2, service.getBooking_ID());
			pstat.setString(3, service.getService_Date());
			pstat.setInt(4, service.getItem_ID());
			pstat.setInt(5, service.getQuantity());
			pstat.setString(6, "Pending");
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
	public void ConfirmOrder(Services_MW service) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "UPDATE services set Status=? WHERE Service_ID=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1,"Complete");
			pstat.setInt(2, service.getService_ID());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
}
