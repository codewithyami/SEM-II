package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import DAO.CRUDdao;
import DAO.ItemJDBCdao;
import DAO.OrderJDBCdao;
import Middleware.MealItem_MW;
import Middleware.Services_MW;
import Middleware.Staff_MW;
import Middleware.ViewServices_MW;

public class Staff_Dashboard extends Dashboard_Frame implements ActionListener,MouseListener {
	JLabel Name, Address, Mobile, Email, Gender, DOB, Password;
	JTextField First_Name_txt, Last_Name_txt;
	JTextField Address_txt, Mobile_txt, Email_txt;
	JPasswordField Password_txt;
	JDateChooser DOB_txt;
	JButton Profile_Update_btn;
	JComboBox<String> Gender_txt;
	JRadioButton Radio_button;
	
	JButton Orders_btn, Add_service_btn, Profile_btn, Logout_btn;
	JLabel welcome, Welcome_name;
	JTable Item_Table,Meal_Item_Table,Drinks_Item_Table,Other_Services,Breakfast_Item_Table, Profile_View_Table;
	DefaultTableModel Item_tableModel,Meal_Item_tableModel,Drinks_Item_tableModel,Other_Services_tabelModel,Breakfast_Item_tableModel,Profile_tableModel;
	JScrollPane itemscroll,Mealscroll,Drinkscroll,Otherscroll,Breakfastscroll,scroll;
	JTextField Item, Quantity, SID;
	JButton Order;
	static int ID;
	
	public Staff_Dashboard(int id, String Name) {
		
		JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon("D:\\Project\\Eclipse\\Assignment\\src\\UI\\6.png")); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(80, 0, size.height, size.width); //Sets the location of the image
        Left_panel.add(label); //Adds objects to the container
		
		Welcome_name = new JLabel(Name);
		Welcome_name.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
		Welcome_name.setForeground(Color.WHITE);
		Welcome_name.setBounds(25,130,280,40);
        Left_panel.add(Welcome_name);
		
		ID = id;
		Orders_btn = new JButton("Orders");
		Orders_btn.addActionListener(this);
		Orders_btn.setBounds(50,250,180,40);
        Left_panel.add(Orders_btn);
        
        Add_service_btn = new JButton("Add Service");
        Add_service_btn.addActionListener(this);
        Add_service_btn.setBounds(50,320,180,40);
        Left_panel.add(Add_service_btn);
        
        Profile_btn = new JButton("Profile");
        Profile_btn.addActionListener(this);
        Profile_btn.setBounds(50,390,180,40);
        Left_panel.add(Profile_btn);
        
        welcome = new JLabel("Hotel Luton");
        welcome.setFont(new Font("Arial", Font.CENTER_BASELINE, 70));	
        welcome.setForeground(Color.WHITE);
        welcome.setBounds(500,40,400,60);
        
        Logout_btn = new JButton("Logout");
        Logout_btn.addActionListener(this);
        Logout_btn.setBounds(50,460,180,40);
        Left_panel.add(Logout_btn);
        Top_panel.add(welcome);
	}
	
	public void Logout() {
    	sharedFrame.switchToLogin();
    	sharedFrame.repaint();
		sharedFrame.revalidate();
    }
	
	public void Service() {
		JLabel YourOrders_lbl = new JLabel("Ordered Items");
		YourOrders_lbl.setBounds(1020, 20, 150, 20);
		Center_panel.add(YourOrders_lbl);		
		String[] Column2 = {"BID","Item","Qty","Price","Status"};
		
		Item_tableModel = new DefaultTableModel();
		Item_tableModel.setColumnIdentifiers(Column2);
		Item_Table = new JTable();
		Item_Table.setModel(Item_tableModel);
		Item_Table.getTableHeader().setReorderingAllowed(false);
		Item_Table.addMouseListener(this);
		Item_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Item_Table.setFillsViewportHeight(true);
		Item_Table.setVisible(true);
		itemscroll = new JScrollPane(Item_Table);
		itemscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		itemscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		itemscroll.setBounds(1020, 50, 350, 200);
		Center_panel.add(itemscroll);
		
		JLabel Meal_lbl = new JLabel("Meal Items");
		Meal_lbl.setBounds(20, 20, 80, 20);
		Center_panel.add(Meal_lbl);
		String[] Meal = {"SN","Item","Price"};
		
		Meal_Item_tableModel = new DefaultTableModel();
		Meal_Item_tableModel.setColumnIdentifiers(Meal);
		Meal_Item_Table = new JTable();
		Meal_Item_Table.setModel(Meal_Item_tableModel);
		Meal_Item_Table.getTableHeader().setReorderingAllowed(false);
		Meal_Item_Table.addMouseListener(this);
		Meal_Item_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Meal_Item_Table.setFillsViewportHeight(true);
		Meal_Item_Table.setVisible(true);
		Mealscroll = new JScrollPane(Meal_Item_Table);
		Mealscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		Mealscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		Mealscroll.setBounds(20, 50, 300, 200);
		Center_panel.add(Mealscroll);
		
		JLabel Breakfast_lbl = new JLabel("Breakfast Items");
		Breakfast_lbl.setBounds(400, 20, 100, 20);
		Center_panel.add(Breakfast_lbl);
		String[] Breakfast = {"SN","Item","Price"};
		
		Breakfast_Item_tableModel = new DefaultTableModel();
		Breakfast_Item_tableModel.setColumnIdentifiers(Breakfast);
		Breakfast_Item_Table = new JTable();
		Breakfast_Item_Table.setModel(Breakfast_Item_tableModel);
		Breakfast_Item_Table.getTableHeader().setReorderingAllowed(false);
		Breakfast_Item_Table.addMouseListener(this);
		Breakfast_Item_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Breakfast_Item_Table.setFillsViewportHeight(true);
		Breakfast_Item_Table.setVisible(true);
		Breakfastscroll = new JScrollPane(Breakfast_Item_Table);
		Breakfastscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		Breakfastscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		Breakfastscroll.setBounds(400, 50, 300, 200);
		Center_panel.add(Breakfastscroll);
		
		JLabel Drinks_lbl = new JLabel("Drinks Items");
		Drinks_lbl.setBounds(20, 320, 80, 20);
		Center_panel.add(Drinks_lbl);
		String[] Drinks = {"SN","Item","Price"};
		
		Drinks_Item_tableModel = new DefaultTableModel();
		Drinks_Item_tableModel.setColumnIdentifiers(Drinks);
		Drinks_Item_Table = new JTable();
		Drinks_Item_Table.setModel(Drinks_Item_tableModel);
		Drinks_Item_Table.getTableHeader().setReorderingAllowed(false);
		Drinks_Item_Table.addMouseListener(this);
		Drinks_Item_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Drinks_Item_Table.setFillsViewportHeight(true);
		Drinks_Item_Table.setVisible(true);
		Drinkscroll = new JScrollPane(Drinks_Item_Table);
		Drinkscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		Drinkscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		Drinkscroll.setBounds(20, 350, 300, 200);
		Center_panel.add(Drinkscroll);
		
		JLabel Other_lbl = new JLabel("Others Items");
		Other_lbl.setBounds(400, 320, 80, 20);
		Center_panel.add(Other_lbl);
		String[] other = {"SN","Item","Price"};
		
		Other_Services_tabelModel = new DefaultTableModel();
		Other_Services_tabelModel.setColumnIdentifiers(other);
		Other_Services = new JTable();
		Other_Services.setModel(		Other_Services_tabelModel);
		Other_Services.getTableHeader().setReorderingAllowed(false);
		Other_Services.addMouseListener(this);
		Other_Services.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Other_Services.setFillsViewportHeight(true);
		Other_Services.setVisible(true);
		Otherscroll = new JScrollPane(Other_Services);
		Otherscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		Otherscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		Otherscroll.setBounds(400, 350, 300, 200);
		Center_panel.add(Otherscroll);
		
		JLabel SN_lbl = new JLabel("BID");
		SN_lbl.setBounds(920, 400, 80, 40);
		Center_panel.add(SN_lbl);
		JLabel Item_lbl = new JLabel("Item");
		Item_lbl.setBounds(920, 460, 80, 40);
		Center_panel.add(Item_lbl);
		JLabel Quantity_lbl = new JLabel("Quantity");
		Quantity_lbl.setBounds(920, 520, 80, 40);
		Center_panel.add(Quantity_lbl);
		
		SID = new JTextField();
		SID.setBounds(1000, 400, 100, 30);
		Center_panel.add(SID);
		
		Item = new JTextField();
		Item.setBounds(1000, 460, 100, 30);
		Center_panel.add(Item);
		
		Quantity = new JTextField();
		Quantity.setBounds(1000, 520, 100, 30);
		Center_panel.add(Quantity);
		
		Order = new JButton("Confirm");
		Order.setBounds(1050, 620, 100, 30);
		Order.addActionListener(this);
		Center_panel.add(Order);
	}

public void profile() {

    	
    	String[] Column = {"CID","First Name","Last Name","Address","Mobile","Email","Gender","DOB"};
		
		Profile_tableModel = new DefaultTableModel();
		Profile_tableModel.setColumnIdentifiers(Column);
		Profile_View_Table = new JTable();
		Profile_View_Table.setModel(Profile_tableModel);	    
		Profile_View_Table.getTableHeader().setReorderingAllowed(false);
		Profile_View_Table.addMouseListener(this);
		Profile_View_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Profile_View_Table.setFillsViewportHeight(true);
		Profile_View_Table.setVisible(true);
		scroll = new JScrollPane(Profile_View_Table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(580, 120, 750, 200);
		Center_panel.add(scroll);
		
		Name = new JLabel("Name");
		Name.setFont(new Font("Arial", Font.PLAIN, 20));
		Name.setBounds(130,100,120,80);
		Center_panel.add(Name);
		
		Address = new JLabel("Address");
		Address.setFont(new Font("Arial", Font.PLAIN, 20));
		Address.setBounds(130,140,150,80);
		Center_panel.add(Address);
		
		Mobile = new JLabel("Mobile");
		Mobile.setFont(new Font("Arial", Font.PLAIN, 20));
		Mobile.setBounds(130,180,150,80);
		Center_panel.add(Mobile);
		
		Email = new JLabel("Email");
		Email.setFont(new Font("Arial", Font.PLAIN, 20));
		Email.setBounds(130,220,150,80);
		Center_panel.add(Email);
		
		Gender = new JLabel("Gender");
		Gender.setFont(new Font("Arial", Font.PLAIN, 20));
		Gender.setBounds(130,260,150,80);
		Center_panel.add(Gender);
		
		DOB = new JLabel("DOB");
		DOB.setFont(new Font("Arial", Font.PLAIN, 20));
		DOB.setBounds(130,300,150,80);
		Center_panel.add(DOB);
		
		Password = new JLabel("Password");
		Password.setFont(new Font("Arial", Font.PLAIN, 20));
		Password.setBounds(130,340,190,80);
		Center_panel.add(Password);
		
		
		
		First_Name_txt = new JTextField();
		First_Name_txt.setBounds(280,125,120,30);
		Center_panel.add(First_Name_txt);
		
		Last_Name_txt = new JTextField();
		Last_Name_txt.setBounds(430,125,120,30);
		Center_panel.add(Last_Name_txt);
		
		Address_txt = new JTextField();
		Address_txt.setBounds(280,165,200,30);
		Center_panel.add(Address_txt);
		
		Mobile_txt = new JTextField();
		Mobile_txt.setBounds(280,205,200,30);
		Center_panel.add(Mobile_txt);
		
		Email_txt = new JTextField();
		Email_txt.setBounds(280,245,200,30);
		Center_panel.add(Email_txt);
		
		String[] items = {"Male","Female"};
		Gender_txt = new JComboBox<>(items);
		Gender_txt.setSelectedItem("Green");
        Gender_txt.setBounds(280,285,200,30);
        Center_panel.add(Gender_txt);
			
		DOB_txt = new JDateChooser();
		DOB_txt.setDateFormatString("yyyy-MM-dd");
		DOB_txt.setFont(new Font("Verdana",Font.PLAIN,18));
		DOB_txt.setBounds(280,325,200,30);
		Center_panel.add(DOB_txt);
		
		Password_txt = new JPasswordField();
		Password_txt.setBounds(280,365,200,30);
		Center_panel.add(Password_txt);
		
		Radio_button = new JRadioButton("Show");
		Radio_button.setBounds(280, 400, 80, 25);
		Radio_button.setFont(new Font("Arial", Font.PLAIN, 20));
		Center_panel.add(Radio_button);
		Radio_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				// show password chars
				if (Radio_button.isSelected()) {
					Password_txt.setEchoChar((char) 0);
				}
				// hide password chars
				else {
					Password_txt.setEchoChar('.');
				}
			}
		});
		
		Profile_Update_btn = new JButton("Update");
		Profile_Update_btn.addActionListener(this);
		Profile_Update_btn.setBounds(320,500,120,50);
		Center_panel.add(Profile_Update_btn);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==Orders_btn) {
			Top_panel.removeAll();
			Top_panel.revalidate();
			Top_panel.repaint();
			Center_panel.removeAll();
			Center_panel.repaint();
			Center_panel.revalidate();
			Top_panel.add(welcome);
			Service();
			
			
			List<ViewServices_MW> itemList=new ItemJDBCdao().ViewAllitem();
			for(ViewServices_MW item:itemList) {
				Item_tableModel.insertRow(0, new Object[] { item.getService_ID(),item.getItem(),item.getQuantity(),item.getPrice(),item.getService_Status()});		
		}
			

			List<MealItem_MW> MealList=new ItemJDBCdao().Mealitem();
			for(MealItem_MW Meal:MealList) {
				Meal_Item_tableModel.insertRow(0, new Object[] { Meal.getItem_ID(),Meal.getItem(),Meal.getPrice()});		
		}
			
			List<MealItem_MW> BreakfastList=new ItemJDBCdao().Breakfastitem();
			for(MealItem_MW breakfast:BreakfastList) {
				Breakfast_Item_tableModel.insertRow(0, new Object[] { breakfast.getItem_ID(),breakfast.getItem(),breakfast.getPrice()});		
		}
			
			List<MealItem_MW> DrinksList=new ItemJDBCdao().Drinkitem();
			for(MealItem_MW drink:DrinksList) {
				Drinks_Item_tableModel.insertRow(0, new Object[] { drink.getItem_ID(),drink.getItem(),drink.getPrice()});		
		}
			
			List<MealItem_MW> OtherList=new ItemJDBCdao().Otheritem();
			for(MealItem_MW other:OtherList) {
				Other_Services_tabelModel.insertRow(0, new Object[] { other.getItem_ID(),other.getItem(),other.getPrice()});		
		}
		}
		
		if(e.getSource()==Order) {
			Services_MW service = new Services_MW();
			int sid = Integer.parseInt(SID.getText());
			service.setService_ID(sid);
			
			OrderJDBCdao confirm = new OrderJDBCdao();
			confirm.ConfirmOrder(service);
			
			DefaultTableModel model = (DefaultTableModel)Item_Table.getModel();
			model.setRowCount(0);
			
			List<ViewServices_MW> itemList=new ItemJDBCdao().ViewAllitem();
			for(ViewServices_MW item:itemList) {
				Item_tableModel.insertRow(0, new Object[] { item.getService_ID(),item.getItem(),item.getQuantity(),item.getPrice(),item.getService_Status()});		
			}
		}
		
		if(e.getSource()==Logout_btn) {
			int result = JOptionPane.showConfirmDialog(sharedFrame, "Are you sure you want to Confirm the Room?", "Confirmation", JOptionPane.YES_NO_OPTION);

	        if (result == JOptionPane.YES_OPTION) {
	        	Logout();
				
	            System.out.println("User clicked Yes");
	            // Perform action for Yes option
	        } else if (result == JOptionPane.NO_OPTION) {
	            System.out.println("User clicked No");
	            // Perform action for No option
	        } else if (result == JOptionPane.CANCEL_OPTION) {
	            System.out.println("User clicked Cancel");
	            // Perform action for Cancel option
	        }
    		
    	}
		if(e.getSource()==Profile_btn) {
			Top_panel.removeAll();
			Top_panel.revalidate();
			Top_panel.repaint();
			Center_panel.removeAll();
			Center_panel.repaint();
			Center_panel.revalidate();
			profile();
			Top_panel.add(welcome);
			
			List<Staff_MW> List=new CRUDdao().StaffViewprofile(ID);
			for(Staff_MW staff:List) {
				Profile_tableModel.insertRow(0, new Object[] { staff.getStaff_ID(),staff.getStaff_First_name(),staff.getStaff_Last_name(),staff.getStaff_Address(),staff.getStaff_Mobile(),staff.getStaff_Email(),staff.getStaff_Gender(),staff.getStaff_DOB(),staff.getStaff_Password()});		
			}
		}
		
		if(e.getSource()==Profile_Update_btn) {
			String first_name = First_Name_txt.getText();
	        String last_name = Last_Name_txt.getText();
	        String address = Address_txt.getText();
	        String mobile = Mobile_txt.getText();
	        String email = Email_txt.getText();
	        String gender = Gender_txt.getSelectedItem().toString();
	        String dob = ((JTextField) DOB_txt.getDateEditor().getUiComponent()).getText();
	        
	        @SuppressWarnings("deprecation")
			String password = Password_txt.getText();

	        Staff_MW cust = new Staff_MW();
		      cust.setStaff_First_name(first_name);
		      cust.setStaff_Last_name(last_name);
		      cust.setStaff_Address(address);
		      cust.setStaff_Mobile(mobile);
		      cust.setStaff_Email(email);
		      cust.setStaff_Gender(gender);
		      cust.setStaff_DOB(dob);
		      cust.setStaff_Password(password);
		      cust.setStaff_ID(ID);
		      
	    	CRUDdao upd = new CRUDdao();
	    	upd.StaffUpdate(cust);
	    	JOptionPane.showMessageDialog(Profile_Update_btn, "Update Successfull!");
	    	DefaultTableModel model = (DefaultTableModel)Profile_View_Table.getModel();
			model.setRowCount(0);
	    	
			List<Staff_MW> List=new CRUDdao().StaffViewprofile(ID);
			for(Staff_MW staff:List) {
				Profile_tableModel.insertRow(0, new Object[] { staff.getStaff_ID(),staff.getStaff_First_name(),staff.getStaff_Last_name(),staff.getStaff_Address(),staff.getStaff_Mobile(),staff.getStaff_Email(),staff.getStaff_Gender(),staff.getStaff_DOB(),staff.getStaff_Password()});		
			}
			
		}
		
	}
	
//	public static void main(String[] args) {
//		new Staff_Dashboard();
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==Item_Table) {
    		try {

    	        // get the index of the selected row
    			int rows = Item_Table.getSelectedRow();

    	        // get the table model
    			TableModel model = Item_Table.getModel();
    			
    			// set the values of the text fields to the values from the selected row
    			int sid = (int) model.getValueAt(rows, 0);
    			SID.setText(Integer.toString(sid));
    			
    			String item = (String) model.getValueAt(rows, 1);
    			Item.setText(item);
    		} catch (Exception ex) {
    			System.out.println("Error" + ex.getMessage());
    		}
        	}
	
	
	if(e.getSource()==Profile_View_Table) {
		try {

	        // get the index of the selected row
			int rows = Profile_View_Table.getSelectedRow();

	        // get the table model
			TableModel model = Profile_View_Table.getModel();
			
			// set the values of the text fields to the values from the selected row
			String first_name = (String) model.getValueAt(rows, 1);
	        String last_name = (String) model.getValueAt(rows, 2);
	        String address = (String) model.getValueAt(rows, 3);
	        String mobile = (String) model.getValueAt(rows, 4);
	        String email = (String) model.getValueAt(rows, 5);
//	        String gender = (String) model.getValueAt(rows, 6);
	        Date dob  = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(rows, 7));
	        
	        First_Name_txt.setText(first_name);
	        Last_Name_txt.setText(last_name);
	    	Address_txt.setText(address);
	    	Mobile_txt.setText(mobile);
	    	Email_txt.setText(email);
	    	DOB_txt.setDate(dob);
			
		} catch (Exception ex) {
			System.out.println("Error" + ex.getMessage());
		}
	
	}
	}
}
