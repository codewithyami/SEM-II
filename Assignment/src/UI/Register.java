package UI;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import DAO.CRUDdao;
import DAO.registervalidation;
import Middleware.Customer_MW;

public class Register implements ActionListener{
	JPanel panel;
	Frame sharedFrame = Frame.getInstance();

	private JLabel register, Name, Address, Mobile, Email, Gender, DOB, Password;
	private JTextField First_Name_txt, Last_Name_txt;
	private JTextField Address_txt, Mobile_txt, Email_txt;
	private JButton Register_btn,Login_btn;
	private JDateChooser DOB_txt;
	private JRadioButton radioButton;
	private JPasswordField Password_txt;
	private JComboBox<String> Gender_txt;
	public static void main(String[] args) {
		 new Register();
		}
	
	public Register() {
		panel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -4045321404491759938L;

			@Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f)); // set the opacity to 60%
                g2.setColor(getBackground());
                g2.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
		}
		};
        
		panel.setLayout(null);
		panel.setBounds(500, 50, 700, 600);
		panel.setBackground(new Color(217, 217, 217)); // set the panel background color to red
        panel.setOpaque(false); // set the panel opacity to transparent
		sharedFrame.getContentPane().add(panel);
		
		register = new JLabel("Register");
		register.setFont(new Font("Arial", Font.PLAIN, 48));
		register.setBounds(260,25,180,80);
		panel.add(register);
		
		Name = new JLabel("Name");
		Name.setFont(new Font("Arial", Font.PLAIN, 20));
		Name.setBounds(130,100,120,80);
		panel.add(Name);
		
		Address = new JLabel("Address");
		Address.setFont(new Font("Arial", Font.PLAIN, 20));
		Address.setBounds(130,140,150,80);
		panel.add(Address);
		
		Mobile = new JLabel("Mobile");
		Mobile.setFont(new Font("Arial", Font.PLAIN, 20));
		Mobile.setBounds(130,180,150,80);
		panel.add(Mobile);
		
		Email = new JLabel("Email");
		Email.setFont(new Font("Arial", Font.PLAIN, 20));
		Email.setBounds(130,220,150,80);
		panel.add(Email);
		
		Gender = new JLabel("Gender");
		Gender.setFont(new Font("Arial", Font.PLAIN, 20));
		Gender.setBounds(130,260,150,80);
		panel.add(Gender);
		
		DOB = new JLabel("DOB");
		DOB.setFont(new Font("Arial", Font.PLAIN, 20));
		DOB.setBounds(130,300,150,80);
		panel.add(DOB);
		
		Password = new JLabel("Password");
		Password.setFont(new Font("Arial", Font.PLAIN, 20));
		Password.setBounds(130,340,190,80);
		panel.add(Password);
		
		
		
		First_Name_txt = new JTextField("First Name");
		First_Name_txt.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	First_Name_txt.setText("");
            }
        });
		First_Name_txt.setBounds(280,125,120,30);
		panel.add(First_Name_txt);
		
		Last_Name_txt = new JTextField("Last Name");
		Last_Name_txt.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	Last_Name_txt.setText("");
            }
        });
		Last_Name_txt.setBounds(430,125,120,30);
		panel.add(Last_Name_txt);
		
		Address_txt = new JTextField();
		Address_txt.setBounds(280,165,200,30);
		panel.add(Address_txt);
		
		Mobile_txt = new JTextField();
		Mobile_txt.setBounds(280,205,200,30);
		panel.add(Mobile_txt);
		
		Email_txt = new JTextField();
		Email_txt.setBounds(280,245,200,30);
		panel.add(Email_txt);
		
//		Gender_txt = new JTextField();
//		Gender_txt.setBounds(280,285,200,30);
//		panel.add(Gender_txt);
		
		String[] items = {"Male","Female"};
		Gender_txt = new JComboBox<>(items);
		Gender_txt.setSelectedItem("Green");
        Gender_txt.setBounds(280,285,200,30);
		panel.add(Gender_txt);
			
		DOB_txt = new JDateChooser();
		DOB_txt.setDateFormatString("yyyy-MM-dd");
		DOB_txt.setFont(new Font("Verdana",Font.PLAIN,18));
		DOB_txt.setBounds(280,325,200,30);
		panel.add(DOB_txt);
		
		Password_txt = new JPasswordField();
		Password_txt.setBounds(280,365,200,30);
		panel.add(Password_txt);
		
		Register_btn = new JButton("Register");
		Register_btn.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));	
		Register_btn.addActionListener(this);
		Register_btn.setBounds(260,450,115,30);
		panel.add(Register_btn);
		
		radioButton = new JRadioButton("Show");
		radioButton.setBounds(280, 400, 80, 25);
		radioButton.setFont(new Font("Arial", Font.PLAIN, 20));
		panel.add(radioButton);
		radioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				// show password chars
				if (radioButton.isSelected()) {
					Password_txt.setEchoChar((char) 0);
				}
				// hide password chars
				else {
					Password_txt.setEchoChar('.');
				}
			}
		});
		
		Login_btn = new JButton("LOGIN");
		Login_btn.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));		
		Login_btn.setBounds(550,50,115,30);
		panel.add(Login_btn);
		Login_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	sharedFrame.dispose();
            	sharedFrame.switchToLogin();
                    	
            }
        });
		

		Container c = sharedFrame.getContentPane(); //Gets the content layer
        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon("D:\\Project\\Eclipse\\Assignment\\src\\UI\\4.jpg")); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(0, 0, size.width, size.height); //Sets the location of the image
 
        c.add(label); //Adds objects to the container
        sharedFrame.setVisible(true); // Exhibit the frame
        sharedFrame.getContentPane().setBackground(Color.LIGHT_GRAY);

	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Register_btn) {
			
	    	String first_name = First_Name_txt.getText();
	        String last_name = Last_Name_txt.getText();
	        String address = Address_txt.getText();
	        String mobile = Mobile_txt.getText();
	        String email = Email_txt.getText();
	        String gender = Gender_txt.getSelectedItem().toString();
	        String dob = ((JTextField) DOB_txt.getDateEditor().getUiComponent()).getText();
	        
	        @SuppressWarnings("deprecation")
			String password = Password_txt.getText();
	        
//	        boolean validFirstname = true;
//	        boolean validEmail = false;
//	        
//	        String logMessage ="  ";
	        
	        
	        registervalidation val = new registervalidation();
	
				
				boolean resultFName = val.First_Name(first_name);
				if (resultFName == true) {
					
					
					boolean Gender1 = val.Gender(gender);
					if (Gender1 == true) {
						
						
						boolean result = val.Mobile(mobile);
						if (result == true) {
							
							
							boolean emailresult = val.Email(email);
							if (emailresult == true) {
								
									
									boolean resultpassword=val.Password(password);
									if(resultpassword==true) {
										
										Customer_MW cust = new Customer_MW();
									      cust.setFirst_name(first_name);
									      cust.setLast_name(last_name);
									      cust.setAddress(address);
									      cust.setMobile(mobile);
									      cust.setEmail(email);
									      cust.setGender(gender);
									      cust.setDOB(dob);
									      cust.setPassword(password);
									      
									      JOptionPane.showMessageDialog(Register_btn, "Register Successfully");    
								  	CRUDdao reg = new CRUDdao();
								  	reg.RegisterCustomer(cust);
										
										
									}
									else {
										JOptionPane.showMessageDialog(null, "Please enter proper password!");
									}
									
								
							}
							else {
								JOptionPane.showMessageDialog(null, "Invalid Email");
							}
							
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Invalid Mobile Number");
						}
	
	
				}
					else {
						JOptionPane.showMessageDialog(null, "Invalid Gender");
					}
	
				
	
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid Name");
				}
				
				
	
			}

			
    }
}
