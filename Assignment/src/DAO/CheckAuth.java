package DAO;

import Middleware.Login_MW;

public class CheckAuth {
	
	public CheckAuth() {
		
	}
	public boolean CustomerAuth(Login_MW userAuth) {
		boolean auth = false;
		Logindao log = new Logindao();
//		String username = userAuth.getMobile();
//		String pass = userAuth.getPassword()
;		boolean result = log.checkUserAuth(userAuth);
		
			
		return auth;
	}
	

	

}
