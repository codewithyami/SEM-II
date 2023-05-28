package Middleware;

public class Room_MW {
	
	private int Room_NO;
	private String Room_Type, Room_Status;
	private float room_price;

	public float getRoom_price() {
		return room_price;
	}

	public void setRoom_price(float room_price) {
		this.room_price = room_price;
	}

	public int getRoom_NO() {
		return Room_NO;
	}

	public void setRoom_NO(int room_NO) {
		Room_NO = room_NO;
	}

	public String getRoom_Type() {
		return Room_Type;
	}

	public void setRoom_Type(String room_type) {
		Room_Type = room_type;
	}

	public String getRoom_Status() {
		return Room_Status;
	}

	public void setRoom_Status(String room_Status) {
		Room_Status = room_Status;
	}

	

}
