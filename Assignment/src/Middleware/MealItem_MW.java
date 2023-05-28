package Middleware;

public class MealItem_MW {
	private int Item_ID;
	private String Item, Item_Type;
	private float Price;
	
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
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
	public String getItem_Type() {
		return Item_Type;
	}
	public void setItem_Type(String item_Type) {
		Item_Type = item_Type;
	}
}
