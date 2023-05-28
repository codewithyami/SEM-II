package Middleware;

public class Customer_MW {
	//model
	private int Customer_ID, UserType_ID=1;
	private String First_name, Last_name, Address, Mobile, Email, Gender, DOB, Password;
	
	//set values
	public void setCustomer_ID(int Customer_ID) {
		this.Customer_ID = Customer_ID;
	}
	public void setFirst_name(String First_name) {
		this.First_name = First_name;
	}
	public void setLast_name(String Last_name) {
		this.Last_name = Last_name;
	}
	public void setAddress(String Address) {
		this.Address = Address;
	}
	public void setMobile(String Mobile) {
		this.Mobile = Mobile;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	public void setGender(String Gender) {
		this.Gender = Gender;
	}
	public void setDOB(String DOB) {
		this.DOB = DOB;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	public void setUserType_ID(int UserType_ID) {
		this.UserType_ID = UserType_ID;
	}
	
	//get values
	public int getCustomer_ID() {
		return Customer_ID;
	}
	public String getFirst_name() {
		return First_name;
	}
	public String getLast_name() {
		return Last_name;
	}
	public String getAddress() {
		return Address;
	}
	public String getMobile() {
		return Mobile;
	}
	public String getEmail() {
		return Email;
	}
	public String getGender() {
		return Gender;
	}
	public String getDOB() {
		return DOB;
	}
	public String getPassword() {
		return Password;
	}
	public int getUserType_ID() {
		return UserType_ID;
	}
	
	
}
