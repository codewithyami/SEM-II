package DAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registervalidation {
	
	public boolean First_Name(String firstname) {
		boolean result = false;

		String regex = "^[A-Z][a-z]{2,10}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(firstname);
		result = m.matches();

		return result;
	}
	
	public boolean Last_Name(String lastname) {
		boolean result = false;

		String regex = "^[A-Z][a-z]{2,10}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(lastname);
		result = m.matches();

		return result;
	}	
	
	
	public boolean Gender(String gender) {
		boolean result = false;
		
		if (gender.equals("Male")||gender.equals("Female")||gender.equals("Other")) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}
	
	public boolean Mobile(String mobile) {
		boolean result = false;
		
		String regex = "^[9]{1}[678]{1}[0-9]{8}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mobile);
		result = m.matches();

		return result;
	}

	public boolean Email(String st) {
		boolean result = false;
		
		String regex = "^[a-z]{1}[a-z0-9_.]{5,20}[@]{1}[a-z]{5,10}[.]{1}[com]{2,3}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(st);
		result = m.matches();

		return result;
	}
	
	public boolean UserName(String st) {
		boolean result = false;
		
		String regex = "^[A-Z]{1}[a-z]{2,10}[0-9\\_\\.]{1,20}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(st);
		result = m.matches();

		return result;
	}
	
	
	public boolean Password(String st) {
		boolean result = false;
		
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(st);
		result = m.matches();

		return result;
	}
	
	
}
