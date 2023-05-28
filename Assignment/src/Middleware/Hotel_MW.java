package Middleware;

public class Hotel_MW {
	//model
	private int Hotel_ID;
	private float Star_Rating;
	private String Hotel_Name, Hotel_Address, Hotel_Phone, Country;
	
	//set values
	public void setHotel_ID(int Hotel_ID) {
		this.Hotel_ID = Hotel_ID;
	}
	public void setStar_Rating(float Star_Rating) {
		this.Star_Rating = Star_Rating;
	}
	public void setHotel_Name(String Hotel_Name) {
		this.Hotel_Name = Hotel_Name;
	}
	public void setHotel_Address(String Hotel_Address) {
		this.Hotel_Address = Hotel_Address;
	}
	public void setHotel_Phone(String Hotel_Phone) {
		this.Hotel_Phone = Hotel_Phone;
	}
	public void setCountry(String Country) {
		this.Country = Country;
	}
	
	//get values
	public int getHotel_ID() {
		return Hotel_ID;
	}
	public float getStar_Rating() {
		return Star_Rating;
	}
	public String getHotel_Name() {
		return Hotel_Name;
	}
	public String getHotel_Address() {
		return Hotel_Address;
	}
	public String getHotel_Phone() {
		return Hotel_Phone;
	}
	public String getCountry() {
		return Country;
	}
}
