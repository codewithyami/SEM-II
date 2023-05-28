package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Middleware.Booking_MW;
import Middleware.ReceptionistCheckIN_MW;
import Middleware.ReceptionistCheckOUT_MW;
import Middleware.ViewBooking_MW;

public class BookingCRUDdao {
	public void MakeBooking(Booking_MW booking) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "INSERT INTO booking (Booking_ID, Customer_ID, Room_NO, Book_date, Check_IN, Check_OUT, Booking_Status_NO, Hotel_ID, Room_Type) Values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setInt(1,booking.getBooking_ID());
			pstat.setInt(2, booking.getCustomer_ID());
			pstat.setInt(3, booking.getRoom_NO());
			pstat.setString(4, booking.getBook_Date());
			pstat.setString(5, booking.getCheck_IN());
			pstat.setString(6, booking.getCheck_OUT());
			pstat.setInt(7, booking.getBooking_Status_NO());
			pstat.setInt(8, booking.getHotel_ID());
			pstat.setString(9, booking.getRoom_Type());
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
	public void UpdateBooking(Booking_MW booking) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "Update booking set Check_IN=?, Check_OUT=? WHERE Booking_ID=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, booking.getCheck_IN());
			pstat.setString(2, booking.getCheck_OUT());
			pstat.setInt(3, booking.getBooking_ID());
			
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
	public void CancelBooking(Booking_MW booking) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "Update booking set Booking_Status_NO=4 WHERE Booking_ID=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			
//			pstat.setString(1, booking.getCheck_IN());
//			pstat.setString(2, booking.getCheck_OUT());
			pstat.setInt(1, booking.getBooking_ID());
			
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
		
			
	public List<ViewBooking_MW> ViewBooking(int id) {
	    List<ViewBooking_MW> bookList = new ArrayList<ViewBooking_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT b.*, c.First_Name, c.Last_Name, s.Status_Type FROM booking b LEFT JOIN customer c ON b.Customer_ID = c.Customer_ID LEFT JOIN bookingstatus s ON b.Booking_Status_NO=s.Status_NO WHERE b.Customer_ID=?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, id);
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	            ViewBooking_MW booking = new ViewBooking_MW();
	            int bid = rs.getInt("Booking_ID");
	            int cid = rs.getInt("Customer_ID");
	            int room_no = rs.getInt("Room_NO");
	            String book_date = rs.getString("Book_date");
	            String check_in = rs.getString("Check_IN");
	            String check_out = rs.getString("Check_OUT");
//		            int booking_status_no = rs.getInt("Booking_Status_NO");
	            String booking_status = rs.getString("Status_Type");
	            String first_name = rs.getString("First_Name");
	            String last_name = rs.getString("Last_Name");

	            booking.setBooking_ID(bid);
	            booking.setCustomer_ID(cid);
	            booking.setRoom_NO(room_no);
	            booking.setBook_Date(book_date);
	            booking.setCheck_IN(check_in);
	            booking.setCheck_OUT(check_out);
	            booking.setStatus_Type(booking_status);
	            booking.setFirst_Name(first_name);
	            booking.setLast_Name(last_name);

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
	
	public List<ReceptionistCheckIN_MW> ViewPendingBooking() {
	    List<ReceptionistCheckIN_MW> bookList = new ArrayList<ReceptionistCheckIN_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT b.*, c.First_Name, c.Last_Name, s.Status_Type FROM booking b LEFT JOIN customer c ON b.Customer_ID = c.Customer_ID LEFT JOIN bookingstatus s ON b.Booking_Status_NO=s.Status_NO WHERE s.Status_Type IN (?,?)";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setString(1, "Pending");
	        pstat.setString(2, "Booked");
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	ReceptionistCheckIN_MW booking = new ReceptionistCheckIN_MW();
	            int bid = rs.getInt("Booking_ID");
	            int cid = rs.getInt("Customer_ID");
	            int room_no = rs.getInt("Room_NO");
	            String book_date = rs.getString("Book_date");
	            String check_in = rs.getString("Check_IN");
	            String check_out = rs.getString("Check_OUT");
		        String room_type = rs.getString("Room_Type");
	            String booking_status = rs.getString("Status_Type");
	            String first_name = rs.getString("First_Name");
	            String last_name = rs.getString("Last_Name");

	            booking.setBooking_ID(bid);
	            booking.setCustomer_ID(cid);
	            booking.setRoom_NO(room_no);
	            booking.setBook_Date(book_date);
	            booking.setCheck_IN(check_in);
	            booking.setCheck_OUT(check_out);
	            booking.setRoom_Type(room_type);
	            booking.setStatus_Type(booking_status);
	            booking.setFirst_Name(first_name);
	            booking.setLast_Name(last_name);

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
	
	public void AllocateRoom(Booking_MW booking) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "Update booking set Room_NO=?, Booking_Status_NO=? WHERE Booking_ID=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, booking.getRoom_NO());
			pstat.setInt(2, 5);
			pstat.setInt(3, booking.getBooking_ID());
			
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
	public void ConfirmBooking(Booking_MW booking) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "Update booking set Room_NO=?, Booking_Status_NO=? WHERE Booking_ID=?";
			PreparedStatement pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, booking.getRoom_NO());
			pstat.setInt(2, 1);
			pstat.setInt(3, booking.getBooking_ID());
			
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
	public void Ckeck_OUTBooking(Booking_MW booking) {
		try {
			Connection conn = new Connect().getConnection();
			String sql = "Update booking set Booking_Status_NO=? WHERE Booking_ID=?";
			PreparedStatement pstat = conn.prepareStatement(sql);

			pstat.setInt(1, 3);
			pstat.setInt(2, booking.getBooking_ID());
			
			pstat.executeUpdate();
			pstat.close();
			conn.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());			
		}
	}
	
	public List<ReceptionistCheckOUT_MW> ViewActiveBooking() {
	    List<ReceptionistCheckOUT_MW> bookList = new ArrayList<ReceptionistCheckOUT_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT b.*, c.First_Name, c.Last_Name, s.Status_Type FROM booking b LEFT JOIN customer c ON b.Customer_ID = c.Customer_ID LEFT JOIN bookingstatus s ON b.Booking_Status_NO=s.Status_NO WHERE s.Status_Type=?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setString(1, "Active");
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	ReceptionistCheckOUT_MW booking = new ReceptionistCheckOUT_MW();
	            int bid = rs.getInt("Booking_ID");
	            int cid = rs.getInt("Customer_ID");
	            int room_no = rs.getInt("Room_NO");
	            String book_date = rs.getString("Book_date");
	            String check_in = rs.getString("Check_IN");
	            String check_out = rs.getString("Check_OUT");
		        String room_type = rs.getString("Room_Type");
	            String booking_status = rs.getString("Status_Type");
	            String first_name = rs.getString("First_Name");
	            String last_name = rs.getString("Last_Name");

	            booking.setBooking_ID(bid);
	            booking.setCustomer_ID(cid);
	            booking.setRoom_NO(room_no);
	            booking.setBook_Date(book_date);
	            booking.setCheck_IN(check_in);
	            booking.setCheck_OUT(check_out);
	            booking.setRoom_Type(room_type);
	            booking.setStatus_Type(booking_status);
	            booking.setFirst_Name(first_name);
	            booking.setLast_Name(last_name);

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
	
	public List<ReceptionistCheckOUT_MW> ViewAllBooking() {
	    List<ReceptionistCheckOUT_MW> bookList = new ArrayList<ReceptionistCheckOUT_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT b.*, c.First_Name, c.Last_Name, s.Status_Type FROM booking b LEFT JOIN customer c ON b.Customer_ID = c.Customer_ID LEFT JOIN bookingstatus s ON b.Booking_Status_NO=s.Status_NO WHERE s.Status_Type IN (?,?)";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setString(1, "Cancel");
	        pstat.setString(2, "COMPLETE");
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	ReceptionistCheckOUT_MW booking = new ReceptionistCheckOUT_MW();
	            int bid = rs.getInt("Booking_ID");
	            int cid = rs.getInt("Customer_ID");
	            int room_no = rs.getInt("Room_NO");
	            String book_date = rs.getString("Book_date");
	            String check_in = rs.getString("Check_IN");
	            String check_out = rs.getString("Check_OUT");
		        String room_type = rs.getString("Room_Type");
	            String booking_status = rs.getString("Status_Type");
	            String first_name = rs.getString("First_Name");
	            String last_name = rs.getString("Last_Name");

	            booking.setBooking_ID(bid);
	            booking.setCustomer_ID(cid);
	            booking.setRoom_NO(room_no);
	            booking.setBook_Date(book_date);
	            booking.setCheck_IN(check_in);
	            booking.setCheck_OUT(check_out);
	            booking.setRoom_Type(room_type);
	            booking.setStatus_Type(booking_status);
	            booking.setFirst_Name(first_name);
	            booking.setLast_Name(last_name);

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
}
