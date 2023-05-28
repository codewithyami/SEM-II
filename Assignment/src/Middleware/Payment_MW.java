package Middleware;

public class Payment_MW {
	
	private int Payment_ID, Booking_ID;
	private String Payment_Date, Payment_Mode,Payment_Status, Name;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPayment_Status() {
		return Payment_Status;
	}
	public void setPayment_Status(String payment_Status) {
		Payment_Status = payment_Status;
	}
	private double Total_Payment;
	public int getPayment_ID() {
		return Payment_ID;
	}
	public void setPayment_ID(int payment_ID) {
		Payment_ID = payment_ID;
	}
	public int getBooking_ID() {
		return Booking_ID;
	}
	public void setBooking_ID(int booking_ID) {
		Booking_ID = booking_ID;
	}
	public String getPayment_Date() {
		return Payment_Date;
	}
	public void setPayment_Date(String payment_Date) {
		Payment_Date = payment_Date;
	}
	public String getPayment_Mode() {
		return Payment_Mode;
	}
	public void setPayment_Mode(String payment_Mode) {
		Payment_Mode = payment_Mode;
	}
	public double getTotal_Payment() {
		return Total_Payment;
	}
	public void setTotal_Payment(double totalprice) {
		Total_Payment = totalprice;
	}
	
	

}
