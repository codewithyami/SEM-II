package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Middleware.Booking_MW;
import Middleware.Room_MW;

public class RoomJDBCdao {
	public List<Room_MW> ViewAvailableBooking() {
	    List<Room_MW> bookList = new ArrayList<Room_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT r.*, s.Room_Status FROM room r LEFT JOIN room_status s ON r.Status=s.Status_NO";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	Room_MW booking = new Room_MW();
	        	int room_no = rs.getInt("Room_NO");
		        String room_type = rs.getString("Room_Type");
	            String room_status = rs.getString("Room_Status");

	            booking.setRoom_NO(room_no);
	            
	            booking.setRoom_Type(room_type);
	            booking.setRoom_Status(room_status);


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
	
	public void ClosedRoomStatus(Booking_MW booking) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "Update room set Status=? WHERE Room_NO=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, 3);
			pstat.setInt(2, booking.getRoom_NO());
			
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
	public List<Room_MW> ViewRoomPrice(int roomno) {
	    List<Room_MW> bookList = new ArrayList<Room_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT r.*, s.Price, b.Room_Status FROM room r LEFT JOIN roomtype s ON r.Room_Type=s.Room_Type LEFT JOIN room_status b ON r.Status=b.Status_NO WHERE Room_NO=?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, roomno);
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	Room_MW booking = new Room_MW();
	        	int room_no = rs.getInt("Room_NO");
		        String room_type = rs.getString("Room_Type");
	            String room_status = rs.getString("Room_Status");
	            float room_price = rs.getFloat("Price");

	            booking.setRoom_NO(room_no);
	            
	            booking.setRoom_Type(room_type);
	            booking.setRoom_Status(room_status);
	            booking.setRoom_price(room_price);

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
	
	public void OpenRoomStatus(Booking_MW booking) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "Update room set Status=? WHERE Room_NO=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, 1);
			pstat.setInt(2, booking.getRoom_NO());
			
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
}
