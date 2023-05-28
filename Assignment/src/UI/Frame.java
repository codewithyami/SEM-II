package UI;

import javax.swing.JFrame;


public class Frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Register register;
	@SuppressWarnings("unused")
	private Customer_Dashboard customer_dashboard;
	@SuppressWarnings("unused")
	private Receptionist_Dashboard receptionist_dashboard;
	@SuppressWarnings("unused")
	private Staff_Dashboard staff_dashboard;
	@SuppressWarnings("unused")
	private Login login;
//	JFrame Window_main;
	
	private static Frame instance = null;
	
	private Frame(){		
		setTitle("Luton Hotel");
		setSize(1760,980);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static Frame getInstance() {
	      if (instance == null) {
	         instance = new Frame();
	      }
	      return instance;
	   }
	
	
	public static void main(String[] args) {
		Frame mainWindow = Frame.getInstance();
		mainWindow.setVisible(true);
	}
	
	public void switchToRegister() {
        getContentPane().removeAll();
//        getContentPane().add(register);
//        pack();
        register = new Register();
    }
	
	public void switchToCustomerdashboard(int id, String name) {
		
        getContentPane().removeAll();
       
//        getContentPane().add(register);
//        pack();
        customer_dashboard = new Customer_Dashboard(id, name);
    }
	
	public void switchToReceptionistdashboard(int id, String name) {
		
        getContentPane().removeAll();
       
//        getContentPane().add(register);
//        pack();
        receptionist_dashboard = new Receptionist_Dashboard(name);
    }
	
	public void switchToStaffdashboard(int id, String name) {
		
        getContentPane().removeAll();
       
//        getContentPane().add(register);
//        pack();
        staff_dashboard = new Staff_Dashboard(id, name);
    }
	
	public void switchToLogin() {
		
        getContentPane().removeAll();
       
//        getContentPane().add(register);
//        pack();
        login = new Login();
    }
	
}
