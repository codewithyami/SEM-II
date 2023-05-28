package Middleware;

public class ViewServices_MW {
	private int Service_ID;
	public int getService_ID() {
		return Service_ID;
	}
	public void setService_ID(int service_ID) {
		Service_ID = service_ID;
	}
	private String Item, Service_Status;
	public String getService_Status() {
		return Service_Status;
	}
	public void setService_Status(String service_Status) {
		Service_Status = service_Status;
	}
	private int Quantity, Item_ID, BID;
	public int getBID() {
		return BID;
	}
	public void setBID(int bID) {
		BID = bID;
	}
	private float Price;
	
	public int getItem_ID() {
		return Item_ID;
	}
	public void setItem_ID(int item_ID) {
		Item_ID = item_ID;
	}
	
	public String getItem() {
		return Item;
	}
	public void setItem(String item) {
		Item = item;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	
}
