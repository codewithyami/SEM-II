package Middleware;

public class Booking_MW {
//	model
	private int Booking_ID, Customer_ID, Room_NO, Hotel_ID, Booking_Status_NO;
	private String Book_Date, Check_IN, Check_OUT, Room_Type;
	

	public String getRoom_Type() {
		return Room_Type;
	}
	public void setRoom_Type(String room_Type) {
		Room_Type = room_Type;
	}
	//set values
	public void setBooking_ID(int Booking_ID) {
		this.Booking_ID = Booking_ID;				
	}
	public void setCustomer_ID(int Customer_ID) {
		this.Customer_ID = Customer_ID;				
	}
	public void setRoom_NO(int Room_NO) {
		this.Room_NO = Room_NO;				
	}
	public void setHotel_ID(int Hotel_ID) {
		this.Hotel_ID = Hotel_ID;				
	}
	public void setBook_Date(String Book_Date) {
		this.Book_Date = Book_Date;				
	}
	public void setCheck_IN(String Check_IN) {
		this.Check_IN = Check_IN;				
	}
	public void setCheck_OUT(String Check_OUT) {
		this.Check_OUT = Check_OUT;				
	}
	public void setBooking_Status_NO(int Booking_Status_NO) {
		this.Booking_Status_NO = Booking_Status_NO;				
	}
	
	//get valuesis
	public int getBooking_ID() {
		return Booking_ID;
	}
	public int getCustomer_ID() {
		return Customer_ID;
	}
	public int getRoom_NO() {
		return Room_NO;
	}
	public int getHotel_ID() {
		return Hotel_ID;
	}
	public String getBook_Date() {
		return Book_Date;
	}
	public String getCheck_IN() {
		return Check_IN;
	}
	public String getCheck_OUT() {
		return Check_OUT;
	}
	public int getBooking_Status_NO() {
		return Booking_Status_NO;
	}
}
