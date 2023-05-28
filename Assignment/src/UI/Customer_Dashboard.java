package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import DAO.ActiveBookingdao;
import DAO.BookingCRUDdao;
import DAO.CRUDdao;
import DAO.ItemJDBCdao;
import DAO.OrderJDBCdao;
import DAO.Paymentdao;
import Middleware.ActiveBooking_MW;
import Middleware.Booking_MW;
import Middleware.Customer_MW;
import Middleware.MealItem_MW;
import Middleware.Payment_MW;
import Middleware.Services_MW;
import Middleware.ViewBooking_MW;
import Middleware.ViewServices_MW;

public class Customer_Dashboard extends Dashboard_Frame implements ActionListener,MouseListener{
	JLabel Name, Address, Mobile, Email, Gender, DOB, Password;
	JTextField First_Name_txt, Last_Name_txt;
	JTextField Address_txt, Mobile_txt, Email_txt;
	JPasswordField Password_txt;
	JDateChooser DOB_txt;
	JButton Profile_Update_btn;
	JComboBox<String> Gender_txt;
	JRadioButton Radio_button;
	
	JButton Update, Cancel,Order, Reserve_btn;
	JTextField Booking_ID_txt,Total_price_txt;
	JTextField Item, Quantity, SN;
	JButton Booking_btn, Services_btn, Payment_btn, Profile_btn, About_Us_btn, Logout_btn,Done;
	JButton Make_Booking, View_Booking;
	JTable Booking_View_Table,View_Table1,Profile_View_Table,Make_Booking_View_Table,Item_Table,Meal_Item_Table,Drinks_Item_Table,Other_Services,Breakfast_Item_Table,Payment_View_Table;
	JScrollPane scroll,itemscroll,Mealscroll,Drinkscroll,Otherscroll,Breakfastscroll,payment_scroll;
	JLabel welcome, Booking_ID_lbl, Welcome_name ;
	JDateChooser Check_IN, Check_OUT, Make_Check_IN, Make_Check_OUT;
	JComboBox<String> Room_type, Payment_type;
	DefaultTableModel tableModel,Item_tableModel,Meal_Item_tableModel,Drinks_Item_tableModel,Other_Services_tabelModel,Breakfast_Item_tableModel,Profile_tableModel;
	DefaultTableModel tableModel2,paymenttableModel;
//	Frame sharedFrame = Frame.getInstance();
	
	static int ID, BID,pid;
	static String NName;
	
	
	public Customer_Dashboard(int id, String name) {
		NName = name;
		ID = id;            
		
		JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon("D:\\Project\\Eclipse\\Assignment\\src\\UI\\6.png")); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(80, 0, size.height, size.width); //Sets the location of the image
        Left_panel.add(label); //Adds objects to the container
		
		Welcome_name = new JLabel(NName);
		Welcome_name.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
		Welcome_name.setForeground(Color.WHITE);
		Welcome_name.setBounds(25,130,250,40);
        Left_panel.add(Welcome_name);
		
        Booking_btn = new JButton("Booking");
        Booking_btn.addActionListener(this);
        Booking_btn.setBounds(50,250,180,40);
        Left_panel.add(Booking_btn);
        
        Services_btn = new JButton("Services");
        Services_btn.setBounds(50,320,180,40);
        Services_btn.addActionListener(this);
        Left_panel.add(Services_btn);
        
        Payment_btn = new JButton("Payment");
        Payment_btn.setBounds(50,390,180,40);
        Payment_btn.addActionListener(this);
        Left_panel.add(Payment_btn);
        
        Profile_btn = new JButton("Profile");
        Profile_btn.addActionListener(this);
        Profile_btn.setBounds(50,460,180,40);
        Left_panel.add(Profile_btn);
        
        
        Logout_btn = new JButton("Logout");
        Logout_btn.addActionListener(this);
        Logout_btn.setBounds(50,530,180,40);
        Left_panel.add(Logout_btn);

        
        welcome = new JLabel("Hotel Luton");
        welcome.setFont(new Font("Arial", Font.CENTER_BASELINE, 70));	
        welcome.setForeground(Color.WHITE);
        welcome.setBounds(500,40,400,60);
        Top_panel.add(welcome);
        
	}

	public void Booking() {
		Make_Booking = new JButton("Make Booking");
		Make_Booking.setBounds(10, 150, 130, 35);
		Make_Booking.addActionListener(this);
		Top_panel.add(Make_Booking);
		
		View_Booking = new JButton("View Booking");
		View_Booking.setBounds(150,150, 130, 35);
		View_Booking.addActionListener(this);
		Top_panel.add(View_Booking);
	}
	
	
	public void Table() {
		JLabel Headline = new JLabel("Update-Cancel Your Booking");
		Headline.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));	
        Headline.setForeground(Color.BLACK);
        Headline.setBounds(300, 30, 450, 30);
        Center_panel.add(Headline);
        
        JLabel Footline = new JLabel("Note: In order to change the room you have to cancel and re-book your desire room");
		Footline.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));	
        Footline.setForeground(Color.RED);
        Footline.setBounds(100, 600, 650, 30);
        Center_panel.add(Footline);
        
		String[] Column = {"BID","Room NO","Name","Book Date","Check IN","Check OUT","Status"};
		
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(Column);
		Booking_View_Table = new JTable();
		Booking_View_Table.setModel(tableModel);
		Booking_View_Table.addMouseListener(this);
		Booking_View_Table.getTableHeader().setReorderingAllowed(false);
		Booking_View_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Booking_View_Table.setFillsViewportHeight(true);
		Booking_View_Table.setVisible(true);
		scroll = new JScrollPane(Booking_View_Table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(280, 100, 750, 200);
		Center_panel.add(scroll);
		
		Update = new JButton("Update");
		Update.setBounds(520,500,120,50);
		Update.addActionListener(this);
//		tableModel.fireTableDataChanged();
		Center_panel.add(Update);
		
		Cancel = new JButton("Cancel");
		Cancel.setBounds(670,500,120,50);
		Cancel.addActionListener(this);
		Center_panel.add(Cancel);
		
		Booking_ID_lbl = new JLabel("Booking ID");
		Booking_ID_lbl.setBounds(440, 320, 80, 30);
		Center_panel.add(Booking_ID_lbl);
		
		Booking_ID_txt = new JTextField();
		Booking_ID_txt.setBounds(520,320,200,30);
		Center_panel.add(Booking_ID_txt);

		
		JLabel Check_IN_lbl, Check_OUT_lbl;
		
		Check_IN_lbl = new JLabel("Check IN");
		Check_IN_lbl.setBounds(440, 370, 80, 30);
		Center_panel.add(Check_IN_lbl);
		
		Check_OUT_lbl = new JLabel("Check OUT");
		Check_OUT_lbl.setBounds(440, 430, 80, 30);
		Center_panel.add(Check_OUT_lbl);


		Date date=new Date();		
		Check_IN = new JDateChooser();
		Check_IN.setMinSelectableDate(date);
		Check_IN.setDateFormatString("yyyy-MM-dd");
		Check_IN.setFont(new Font("Verdana",Font.PLAIN,18));
		Check_IN.setBounds(520,370,200,30);
		Center_panel.add(Check_IN);
		
		Date date2=new Date();		
		Check_OUT = new JDateChooser();
		Check_OUT.setMinSelectableDate(date2);
		Check_OUT.setDateFormatString("yyyy-MM-dd");
		Check_OUT.setFont(new Font("Verdana",Font.PLAIN,18));
		Check_OUT.setBounds(520,430,200,30);
		Center_panel.add(Check_OUT);	
	}
	
	
	
	public void MakeBooking() {
		JLabel Room_type_lbl = new JLabel("Room Type");
		Room_type_lbl.setBounds(920, 470, 80, 30);
		Center_panel.add(Room_type_lbl);
		
		String[] items = {"Single","Twin","Double"};
        Room_type = new JComboBox<>(items);
        Room_type.setSelectedItem("Green");
        Room_type.setBounds(1000, 470, 200, 30);
        Center_panel.add(Room_type);
		
		
		JLabel Check_IN_lbl, Check_OUT_lbl;
		
		Check_IN_lbl = new JLabel("Check IN");
		Check_IN_lbl.setBounds(920, 530, 80, 30);
		Center_panel.add(Check_IN_lbl);
		
		Check_OUT_lbl = new JLabel("Check OUT");
		Check_OUT_lbl.setBounds(920, 590, 80, 30);
		Center_panel.add(Check_OUT_lbl);
		
		Date date=new Date();		
		Make_Check_IN = new JDateChooser();
		Make_Check_IN.setMinSelectableDate(date);
		Make_Check_IN.setDateFormatString("yyyy-MM-dd");
		Make_Check_IN.setFont(new Font("Verdana",Font.PLAIN,18));
		Make_Check_IN.setBounds(1000,530,200,30);
		Center_panel.add(Make_Check_IN);
		
		Date date2=new Date();		
		Make_Check_OUT = new JDateChooser();
		Make_Check_OUT.setMinSelectableDate(date2);
		Make_Check_OUT.setDateFormatString("yyyy-MM-dd");
		Make_Check_OUT.setFont(new Font("Verdana",Font.PLAIN,18));
		Make_Check_OUT.setBounds(1000,590,200,30);
		Center_panel.add(Make_Check_OUT);
		
		Reserve_btn = new JButton("Reserve");
		Reserve_btn.setBounds(1150, 650, 150, 30);
		Reserve_btn.addActionListener(this);
		Center_panel.add(Reserve_btn);
		
	}
	
	
	public void room() {
		JLabel single_label = new JLabel(); //JLabel Creation
		single_label.setIcon(new ImageIcon("D:\\Project\\Eclipse\\Assignment\\src\\UI\\Single.jpg")); //Sets the image to be displayed as an icon
        Dimension size = single_label.getPreferredSize(); //Gets the size of the image
        single_label.setBounds(80, 20, size.width, size.height); //Sets the location of the image
        Center_panel.add(single_label);
        
        JLabel Twin_label = new JLabel(); //JLabel Creation
        Twin_label.setIcon(new ImageIcon("D:\\Project\\Eclipse\\Assignment\\src\\UI\\Twin.jpg")); //Sets the image to be displayed as an icon
        Dimension size1 = Twin_label.getPreferredSize(); //Gets the size of the image
        Twin_label.setBounds(540, 20, size1.width, size1.height); //Sets the location of the image
        Center_panel.add(Twin_label);
        
        JLabel Double_label = new JLabel(); //JLabel Creation
        Double_label.setIcon(new ImageIcon("D:\\Project\\Eclipse\\Assignment\\src\\UI\\Double.jpg")); //Sets the image to be displayed as an icon
        Dimension size2 = Double_label.getPreferredSize(); //Gets the size of the image
        Double_label.setBounds(1000, 20, size2.width, size2.height); //Sets the location of the image
        Center_panel.add(Double_label);
        
        
        
        JLabel Single_room_lbl = new JLabel("Single Room");
        Single_room_lbl.setBounds(180, 340, 150, 30);
        Center_panel.add(Single_room_lbl);
        
        JLabel Twin_room_lbl = new JLabel("Twin Room");
        Twin_room_lbl.setBounds(650, 340, 150, 30);
        Center_panel.add(Twin_room_lbl);
        
        JLabel Double_room_lbl = new JLabel("Double Room");
        Double_room_lbl.setBounds(1120, 340, 150, 30);
        Center_panel.add(Double_room_lbl);
        

        
        JTextArea Single_room_btn = new JTextArea("         A/C. \n     1 Bathroom");
        Single_room_btn.setBackground(new Color(234,214,214));
        Single_room_btn.setBounds(175, 380, 90, 30);
        Center_panel.add(Single_room_btn);
        
        JTextArea Twin_room_btn = new JTextArea("         A/C. \n     2 Bathroom");
        Twin_room_btn.setBackground(new Color(234,214,214));
        Twin_room_btn.setBounds(635, 380, 90, 30);
        Center_panel.add(Twin_room_btn);
        
        JTextArea Double_room_btn = new JTextArea("         A/C. \n     2 Bathroom");
        Double_room_btn.setBackground(new Color(234,214,214));
        Double_room_btn.setBounds(1120, 380, 90, 30);
        Center_panel.add(Double_room_btn);
        
        String[] Columns = {"Type","Price","Status"};
		
		tableModel2 = new DefaultTableModel();
		tableModel2.setColumnIdentifiers(Columns);
		Make_Booking_View_Table = new JTable();
		
		Make_Booking_View_Table.setModel(tableModel2);
		Make_Booking_View_Table.addMouseListener(this);
		
		tableModel2.insertRow(0, new Object[] { "Single","2600","Available" });
	    tableModel2.insertRow(0, new Object[] { "Twin","4600","Available" });
	    tableModel2.insertRow(0, new Object[] { "Double","6600","Available" });
	    
	    Make_Booking_View_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    Make_Booking_View_Table.getTableHeader().setReorderingAllowed(false);
	    Make_Booking_View_Table.setFillsViewportHeight(true);
	    Make_Booking_View_Table.setVisible(true);
	
		scroll = new JScrollPane(Make_Booking_View_Table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(80, 450, 700, 200);
		Center_panel.add(scroll); 
        
	}
	
	public void Services() {
		JLabel YourOrders_lbl = new JLabel("Your Ordered Items");
		YourOrders_lbl.setBounds(1020, 20, 150, 20);
		Center_panel.add(YourOrders_lbl);		
		String[] Column2 = {"Item","Qty","Price","Status"};
		
		Item_tableModel = new DefaultTableModel();
		Item_tableModel.setColumnIdentifiers(Column2);
		Item_Table = new JTable();
		Item_Table.setModel(Item_tableModel);
		Item_Table.getTableHeader().setReorderingAllowed(false);
		Item_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Item_Table.setFillsViewportHeight(true);
		Item_Table.setVisible(true);
		itemscroll = new JScrollPane(Item_Table);
		itemscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		itemscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		itemscroll.setBounds(1020, 50, 300, 200);
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
//		Meal_Item_Table.set
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
		
		JLabel SN_lbl = new JLabel("SN");
		SN_lbl.setBounds(920, 400, 80, 40);
		Center_panel.add(SN_lbl);
		JLabel Item_lbl = new JLabel("Item");
		Item_lbl.setBounds(920, 460, 80, 40);
		Center_panel.add(Item_lbl);
		JLabel Quantity_lbl = new JLabel("Quantity");
		Quantity_lbl.setBounds(920, 520, 80, 40);
		Center_panel.add(Quantity_lbl);
		
		SN = new JTextField();
		SN.setBounds(1000, 400, 100, 30);
		Center_panel.add(SN);
		
		Item = new JTextField();
		Item.setBounds(1000, 460, 100, 30);
		Center_panel.add(Item);
		
		Quantity = new JTextField();
		Quantity.setBounds(1000, 520, 100, 30);
		Center_panel.add(Quantity);
		
		Order = new JButton("Order");
		Order.setBounds(1050, 620, 100, 30);
		Order.addActionListener(this);
		Center_panel.add(Order);
		
	}
	
	public void payment() {
		String[] Column = {"Payment ID","Booking ID","Date","Payment Mode","Total Payment","Payment Status"};
		
		paymenttableModel = new DefaultTableModel();
		paymenttableModel.setColumnIdentifiers(Column);
		Payment_View_Table = new JTable();
		Payment_View_Table.setModel(paymenttableModel);
		Payment_View_Table.getTableHeader().setReorderingAllowed(false);
		Payment_View_Table.addMouseListener(this);
		Payment_View_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Payment_View_Table.setFillsViewportHeight(true);
		Payment_View_Table.setVisible(true);
		payment_scroll = new JScrollPane(Payment_View_Table);
		payment_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		payment_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		payment_scroll.setBounds(100, 80, 950, 200);
		Center_panel.add(payment_scroll);
		
		JLabel Total_price = new JLabel("Total Price:");
		Total_price.setFont(new Font("Arial", Font.PLAIN, 20));
		Total_price.setBounds(200, 400, 130, 30);
		Center_panel.add(Total_price);
		
		Total_price_txt = new JTextField();
//		Name_txt.setFont(new Font("Arial", Font.PLAIN, 20));
		Total_price_txt.setBounds(320, 400, 150, 30);
		Center_panel.add(Total_price_txt);
		
		String[] items = {"Card","Cash","Online"};
		Payment_type = new JComboBox<>(items);
		Payment_type.setSelectedItem("Green");
		Payment_type.setBounds(320, 450, 150, 30);
        Center_panel.add(Payment_type);
        
        Done = new JButton("Payed");
		Done.addActionListener(this);
		Done.setBounds(400, 500, 80, 30);
		Center_panel.add(Done);
	}
    
    public void profile() {
    	JLabel Title = new JLabel("Update Profile");
    	Title.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));	
    	Title.setForeground(Color.BLACK);
    	Title.setBounds(500,40,400,60);
        Center_panel.add(Title);
    	
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
    
    public void Logout() {
    	sharedFrame.switchToLogin();
    	sharedFrame.repaint();
		sharedFrame.revalidate();
    }
    
    public void mouseClicked(MouseEvent e) {
    	if(e.getSource()==Make_Booking_View_Table) {
		try {

	        // get the index of the selected row
			int rows = Make_Booking_View_Table.getSelectedRow();

	        // get the table model
			TableModel model1 = Make_Booking_View_Table.getModel();

	        // set the values of the text fields to the values from the selected row
			String value = (String) model1.getValueAt(rows, 0);
			Room_type.setSelectedItem(value);

		} catch (Exception ex) {
			System.out.println("Error" + ex.getMessage());
		}
    	}
		
    	if(e.getSource()==Booking_View_Table) {
		try {

	        // get the index of the selected row
			int rows = Booking_View_Table.getSelectedRow();

	        // get the table model
			TableModel model = Booking_View_Table.getModel();
			
			int id = (int) model.getValueAt(rows, 0);
			Booking_ID_txt.setText(Integer.toString(id));
			

	        // set the values of the text fields to the values from the selected row
//			String check_in = model1.getValueAt(rows, 0).toString();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(rows, 4));
			Check_IN.setDate(date);
			
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(rows, 5));
			Check_OUT.setDate(date2);

		} catch (Exception ex) {
			System.out.println("Error" + ex.getMessage());
		}
    	}
    	
    	if(e.getSource()==Meal_Item_Table) {
    		try {

    	        // get the index of the selected row
    			int rows = Meal_Item_Table.getSelectedRow();

    	        // get the table model
    			TableModel model = Meal_Item_Table.getModel();
    			
    			// set the values of the text fields to the values from the selected row
    			int sn = (int) model.getValueAt(rows, 0);
    			SN.setText(Integer.toString(sn));
    			
    			String item = (String) model.getValueAt(rows, 1);
    			Item.setText(item);
    		} catch (Exception ex) {
    			System.out.println("Error" + ex.getMessage());
    		}
        	}
    	
    	if(e.getSource()==Breakfast_Item_Table) {
    		try {

    	        // get the index of the selected row
    			int rows = Breakfast_Item_Table.getSelectedRow();

    	        // get the table model
    			TableModel model = Breakfast_Item_Table.getModel();
    			
    			// set the values of the text fields to the values from the selected row
    			int sn = (int) model.getValueAt(rows, 0);
    			SN.setText(Integer.toString(sn));
    			
    			String item = (String) model.getValueAt(rows, 1);
    			Item.setText(item);
    		} catch (Exception ex) {
    			System.out.println("Error" + ex.getMessage());
    		}
        	}
    	
    	if(e.getSource()==Drinks_Item_Table) {
    		try {

    	        // get the index of the selected row
    			int rows = Drinks_Item_Table.getSelectedRow();

    	        // get the table model
    			TableModel model = Drinks_Item_Table.getModel();
    			
    			// set the values of the text fields to the values from the selected row
    			int sn = (int) model.getValueAt(rows, 0);
    			SN.setText(Integer.toString(sn));
    			
    			String item = (String) model.getValueAt(rows, 1);
    			Item.setText(item);
    		} catch (Exception ex) {
    			System.out.println("Error" + ex.getMessage());
    		}
        	}
    	
    	if(e.getSource()==Other_Services) {
    		try {

    	        // get the index of the selected row
    			int rows = Other_Services.getSelectedRow();

    	        // get the table model
    			TableModel model = Other_Services.getModel();
    			
    			// set the values of the text fields to the values from the selected row
    			int sn = (int) model.getValueAt(rows, 0);
    			SN.setText(Integer.toString(sn));
    			
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
//    	        String gender = (String) model.getValueAt(rows, 6);
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
    	if(e.getSource()==Payment_View_Table) {
			
			try {
				
				
		        // get the index of the selected row
				int rows = Payment_View_Table.getSelectedRow();

		        // get the table model
				TableModel model = Payment_View_Table.getModel();

		        // set the values of the text fields to the values from the selected row				
				int payid = (int) model.getValueAt(rows, 1);
				pid=payid;
				
				double price = (double) model.getValueAt(rows, 4);
				Total_price_txt.setText(Double.toString(price));
				
			}
			catch (Exception ex) {
				System.out.println("Error" + ex.getMessage());
			}
    	}
    	
	}
 // override the other methods of the MouseListener interface
 	@Override
 	public void mousePressed(MouseEvent e) {
 	}

 	@Override
 	public void mouseReleased(MouseEvent e) {
 	}

 	@Override
 	public void mouseEntered(MouseEvent e) {
 	}

 	@Override
 	public void mouseExited(MouseEvent e) {
 	}
 	    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==Booking_btn) {
			Center_panel.removeAll();
			Center_panel.repaint();
			Top_panel.repaint();
			Top_panel.revalidate();
			Center_panel.revalidate();
			Left_panel.repaint();
			Left_panel.revalidate();
			room();
			MakeBooking();
			Booking();
		}
		if(e.getSource()==View_Booking){
			Center_panel.removeAll();
			Center_panel.repaint();
			Center_panel.revalidate();
			
			Table();
		}
		if(e.getSource()==Make_Booking){
			Center_panel.removeAll();
			Center_panel.repaint();
			Center_panel.revalidate();
			room();
			MakeBooking();
		}
		if(e.getSource()==Profile_btn){
			Top_panel.removeAll();
			Top_panel.revalidate();
			Top_panel.repaint();
			Center_panel.removeAll();
			Center_panel.repaint();
			Center_panel.revalidate();
			profile();
			Top_panel.add(welcome);

		}
		if(e.getSource()==Reserve_btn) {
			Booking_MW book = new Booking_MW();
			LocalDate currentDate = LocalDate.now();
			
			
			String checkin = ((JTextField) Make_Check_IN.getDateEditor().getUiComponent()).getText();
			String checkout = ((JTextField) Make_Check_OUT.getDateEditor().getUiComponent()).getText();
			String room = (String) Room_type.getSelectedItem();
			
			book.setCustomer_ID(ID);
			book.setRoom_Type(room);
			book.setBook_Date(currentDate.toString());
			book.setCheck_IN(checkin);
			book.setCheck_OUT(checkout);
			book.setBooking_Status_NO(2);
			book.setHotel_ID(1);
			
			BookingCRUDdao booking = new BookingCRUDdao();
			booking.MakeBooking(book);
			
			JOptionPane.showMessageDialog(Reserve_btn, "Booked");
			
		}
		
		
		if(e.getSource()==View_Booking) {			
			
			List<ViewBooking_MW> bookList=new BookingCRUDdao().ViewBooking(ID);
			for(ViewBooking_MW book:bookList) {
				tableModel.insertRow(0, new Object[] { book.getBooking_ID(),book.getRoom_NO(),book.getFirst_Name()+" "+book.getLast_Name(),book.getBook_Date(),book.getCheck_IN(),book.getCheck_OUT(),book.getStatus_Type() });		
		}
		}
		
		if(e.getSource()==Update) {
			Booking_MW update = new Booking_MW();
			
			int id = Integer.parseInt(Booking_ID_txt.getText());
			String checkin = ((JTextField) Check_IN.getDateEditor().getUiComponent()).getText();
			String checkout = ((JTextField) Check_OUT.getDateEditor().getUiComponent()).getText();
			
			update.setBooking_ID(id);
			update.setCheck_IN(checkin);
			update.setCheck_OUT(checkout);
			
			BookingCRUDdao UPDATE = new BookingCRUDdao();
			UPDATE.UpdateBooking(update);
			
			DefaultTableModel model = (DefaultTableModel)Booking_View_Table.getModel();
			model.setRowCount(0);
			
			List<ViewBooking_MW> bookList=new BookingCRUDdao().ViewBooking(ID);
			for(ViewBooking_MW book:bookList) {
				tableModel.insertRow(0, new Object[] { book.getBooking_ID(),book.getRoom_NO(),book.getFirst_Name()+" "+book.getLast_Name(),book.getBook_Date(),book.getCheck_IN(),book.getCheck_OUT(),book.getStatus_Type() });		
		}
			
			JOptionPane.showMessageDialog(Update, "Update Successfully");    
		}
		
		if(e.getSource()==Cancel) {
			
			
			// Show confirmation dialog
	        int result = JOptionPane.showConfirmDialog(sharedFrame, "Are you sure you want to continue?", "Confirmation", JOptionPane.YES_NO_OPTION);

	        if (result == JOptionPane.YES_OPTION) {
	        	Booking_MW cancel = new Booking_MW();
				
				int id = Integer.parseInt(Booking_ID_txt.getText());
				
				cancel.setBooking_ID(id);
				
				BookingCRUDdao CANCEL = new BookingCRUDdao();
				CANCEL.CancelBooking(cancel);
				
				tableModel.fireTableDataChanged();
				
				DefaultTableModel model = (DefaultTableModel)Booking_View_Table.getModel();
				model.setRowCount(0);
				
				List<ViewBooking_MW> bookList=new BookingCRUDdao().ViewBooking(ID);
				for(ViewBooking_MW book:bookList) {
					tableModel.insertRow(0, new Object[] { book.getBooking_ID(),book.getRoom_NO(),book.getFirst_Name()+" "+book.getLast_Name(),book.getBook_Date(),book.getCheck_IN(),book.getCheck_OUT(),book.getStatus_Type() });		
			}
				
//				JOptionPane.showConfirmDialog(Cancel, "Do you really want to calcel");
				
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
		
		if(e.getSource()==Services_btn) {
			Top_panel.removeAll();
			Top_panel.revalidate();
			Top_panel.repaint();
			Center_panel.removeAll();
			Center_panel.repaint();
			Center_panel.revalidate();
			Top_panel.add(welcome);
			Services();
			
			ActiveBooking_MW active = new ActiveBooking_MW();
			active.setCid(ID);
			ActiveBookingdao act = new ActiveBookingdao();
			act.ActiveBooking(active);
			BID = active.getBid();			
			
			List<ViewServices_MW> itemList=new ItemJDBCdao().Viewitem(BID);
			for(ViewServices_MW item:itemList) {
				Item_tableModel.insertRow(0, new Object[] { item.getItem(),item.getQuantity(),item.getPrice(),item.getService_Status()});		
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
			service.setBooking_ID(BID);
			int itemid = Integer.parseInt(SN.getText());
			service.setItem_ID(itemid);
			LocalDate currentDate = LocalDate.now();
			service.setService_Date(currentDate.toString());
			int quantity = Integer.parseInt(Quantity.getText());
			service.setQuantity(quantity);
			
			OrderJDBCdao order = new OrderJDBCdao();
			order.MakeOrder(service);
			
			DefaultTableModel model = (DefaultTableModel)Item_Table.getModel();
			model.setRowCount(0);
			
			List<ViewServices_MW> itemList=new ItemJDBCdao().Viewitem(BID);
			for(ViewServices_MW item:itemList) {
				Item_tableModel.insertRow(0, new Object[] { item.getItem(),item.getQuantity(),item.getPrice(),item.getService_Status()});
			}
			JOptionPane.showMessageDialog(Order, "Order Successfull!");
			
		}
		
		if(e.getSource()==Payment_btn) {
			Center_panel.removeAll();
			Center_panel.repaint();
			Center_panel.revalidate();
			payment();
			
			
			Payment_MW pay = new Payment_MW();
			ActiveBookingdao active = new ActiveBookingdao();
			
			ActiveBooking_MW Activebooking = new ActiveBooking_MW();
			Activebooking.setCid(ID);
			active.ActiveBooking(Activebooking);
			
			pay.setBooking_ID(Activebooking.getBid());
			System.out.println(Activebooking.getBid());

//			Paymentdao paying = new Paymentdao();
//			paying.ViewPayment(pay);
			
			List<Payment_MW> List=new Paymentdao().ViewPayment(pay);
			for(Payment_MW paylist:List) {
				paymenttableModel.insertRow(0, new Object[] { paylist.getPayment_ID(),paylist.getBooking_ID(),paylist.getPayment_Date(),paylist.getPayment_Mode(),paylist.getTotal_Payment(),paylist.getPayment_Status()});		
			}
		}
		
		if(e.getSource()==Done) {
			Payment_MW pay = new Payment_MW();
			
			String type = (String) Payment_type.getSelectedItem();
			pay.setPayment_Mode(type);
			pay.setPayment_ID(pid);
//			System.out.println(pid);
			
			Paymentdao bill = new Paymentdao();
			bill.UpdateBill(pay);
			
			JOptionPane.showMessageDialog(Done, "Payment Successful!");
		}
		
		if(e.getSource()==Profile_btn) {
			List<Customer_MW> List=new CRUDdao().Viewprofile(ID);
			for(Customer_MW customer:List) {
				Profile_tableModel.insertRow(0, new Object[] { customer.getCustomer_ID(),customer.getFirst_name(),customer.getLast_name(),customer.getAddress(),customer.getMobile(),customer.getEmail(),customer.getGender(),customer.getDOB(),customer.getPassword()});		
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

	        Customer_MW cust = new Customer_MW();
		      cust.setFirst_name(first_name);
		      cust.setLast_name(last_name);
		      cust.setAddress(address);
		      cust.setMobile(mobile);
		      cust.setEmail(email);
		      cust.setGender(gender);
		      cust.setDOB(dob);
		      cust.setPassword(password);
		      cust.setCustomer_ID(ID);
		      
	    	CRUDdao reg = new CRUDdao();
	    	reg.CustomerUpdate(cust);
	    	JOptionPane.showMessageDialog(Profile_Update_btn, "Update Successfull!");
	    	DefaultTableModel model = (DefaultTableModel)Profile_View_Table.getModel();
			model.setRowCount(0);
	    	
	    	List<Customer_MW> List=new CRUDdao().Viewprofile(ID);
			for(Customer_MW customer:List) {
				Profile_tableModel.insertRow(0, new Object[] { customer.getCustomer_ID(),customer.getFirst_name(),customer.getLast_name(),customer.getAddress(),customer.getMobile(),customer.getEmail(),customer.getGender(),customer.getDOB(),customer.getPassword()});		
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
			
	}
	


}
