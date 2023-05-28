package UI;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import DAO.Logindao;
import Middleware.Login_MW;




public class Login implements ActionListener{
	private JLabel Login_lbl, Username_lbl, Password_lbl;
	private JTextField Username_txt;
	private JPasswordField Password_txt;
	private JButton Login_btn, Register_btn;
	private JRadioButton radioButton;

	private JPanel panel;
	Frame sharedFrame = Frame.getInstance();
	
	public static void main(String[] args) {
	 new Login();
	}

	public Login() {
		panel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f)); // set the opacity to 90%
                g2.setColor(getBackground());
                g2.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
		}
		};
        // add components to panel here

		panel.setLayout(null);
		panel.setBounds(500, 50, 700, 600);
		panel.setBackground(new Color(217, 217, 217)); // set the panel background color to red
        panel.setOpaque(false); // set the panel opacity to transparent
		sharedFrame.getContentPane().add(panel);
		
	
		Login_lbl = new JLabel("Login");
		Username_lbl = new JLabel("Username");
		Password_lbl = new JLabel("Password");
		
		Login_lbl.setFont(new Font("Arial", Font.ITALIC, 48));
		Username_lbl.setFont(new Font("Arial", Font.PLAIN, 26));
		Password_lbl.setFont(new Font("Arial", Font.PLAIN, 26));
		
		Login_lbl.setOpaque(false);
		Username_lbl.setOpaque(false);
		Username_lbl.setBackground( new Color(217, 217, 217, 50) );
		
		
		Login_lbl.setBounds(265,55,180,80);
		Username_lbl.setBounds(150,200,120,80);
		Password_lbl.setBounds(150,250,120,80);
		
		panel.add(Login_lbl);
		panel.add(Username_lbl);
		panel.add(Password_lbl);
		
		Username_txt = new JTextField();
		Password_txt = new JPasswordField();
		
		Username_txt.setBounds(320,230,200,30);
		Password_txt.setBounds(320,280,200,30);
		
		panel.add(Username_txt);
		panel.add(Password_txt);		
		
		Login_btn = new JButton("Login");
		Login_btn.setFont(new Font("Arial", Font.CENTER_BASELINE, 26));		
		Login_btn.setBounds(265,450,120,40);
		panel.add(Login_btn);
		
		radioButton = new JRadioButton("Show");
		radioButton.setBounds(320, 320, 80, 25);
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
		
		Register_btn = new JButton("Register");
		Register_btn.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));		
		Register_btn.setBounds(550,50,115,30);
		Register_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	sharedFrame.dispose();
            	sharedFrame.switchToRegister();
                    	
            }
        });
		
		panel.add(Register_btn);

		sharedFrame.add(panel);
		sharedFrame.setVisible(true);
		
        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon("D:\\Project\\Eclipse\\Assignment\\src\\UI\\4.jpg")); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(0, 0, size.width, size.height); //Sets the location of the image
 
        sharedFrame.add(label); //Adds objects to the container
        sharedFrame.setVisible(true); // Exhibit the frame
        sharedFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        Login_btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==Login_btn) {
			String Username = Username_txt.getText();
	        @SuppressWarnings("deprecation")
			String Password = Password_txt.getText();
			
			Logindao log = new Logindao();
//	        CheckAuth log = new CheckAuth();
			Login_MW lg = new Login_MW();
			lg.setMobile(Username);
			lg.setPassword(Password);
			
//			boolean result = log.CustomerAuth(lg);
			
			boolean result = log.checkUserAuth(lg);
			boolean empresult = log.checkStaffAuth(lg);
			
//			System.out.println(lg.getNewPassword());
			if(lg.getNewPassword().equals(Password) && result == true) {
				String name = lg.getName();
				JOptionPane.showMessageDialog(Login_btn, "Welcome!"+' '+name);
				int id = lg.getId();
				System.out.println(id);
				sharedFrame.switchToCustomerdashboard(id, name);
				sharedFrame.repaint();
				sharedFrame.revalidate();
			}
			else if(lg.getNewPassword().equals(Password) && empresult == true) {
				String name = lg.getName();
				JOptionPane.showMessageDialog(Login_btn, "Welcome!"+' '+name);
				int id = lg.getId();
				int role = lg.getRole();
//				String name = lg.getName();
//				System.out.println(id);
				if(role==2) {
					sharedFrame.switchToReceptionistdashboard(id,name);
					sharedFrame.repaint();
					sharedFrame.revalidate();
				}
				else if(role==3) {
					sharedFrame.switchToStaffdashboard(id, name);
					sharedFrame.repaint();
					sharedFrame.revalidate();
				}
			}
			else {
				JOptionPane.showMessageDialog(Login_btn, "Error to Login!");
			}
		}
	}

	
}