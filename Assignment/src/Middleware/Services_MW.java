package Middleware;

public class Services_MW {
	private int Service_ID, Item_ID, Booking_ID, Quantity=1;
	private String Service_Status;
	
	public String getService_Status() {
		return Service_Status;
	}
	public void setService_Status(String service_Status) {
		Service_Status = service_Status;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	private String Service_Date;
	public int getService_ID() {
		return Service_ID;
	}
	public void setService_ID(int service_ID) {
		Service_ID = service_ID;
	}
	public int getItem_ID() {
		return Item_ID;
	}
	public void setItem_ID(int item_ID) {
		Item_ID = item_ID;
	}
	public int getBooking_ID() {
		return Booking_ID;
	}
	public void setBooking_ID(int booking_ID) {
		Booking_ID = booking_ID;
	}
	public String getService_Date() {
		return Service_Date;
	}
	public void setService_Date(String service_Date) {
		Service_Date = service_Date;
	}
	
	
}
