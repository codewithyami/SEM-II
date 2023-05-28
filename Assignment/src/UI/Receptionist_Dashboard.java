package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DAO.ActiveBookingdao;
import DAO.BookingCRUDdao;
import DAO.ItemJDBCdao;
import DAO.Paymentdao;
import DAO.RoomJDBCdao;
import Middleware.Amounts_MW;
import Middleware.Booking_MW;
import Middleware.Payment_MW;
import Middleware.ReceptionistCheckIN_MW;
import Middleware.ReceptionistCheckOUT_MW;
import Middleware.Room_MW;
import Middleware.ViewServices_MW;

public class Receptionist_Dashboard extends Dashboard_Frame implements ActionListener, MouseListener {
	JButton Check_IN_btn, Check_OUT_btn, History_btn,Logout_btn;
	JButton Done;
	JButton Conform_Check_OUT_btn;
	JLabel welcome, Welcome_name;
	JTable Check_IN_View_Table, Check_OUT_View_Table,Check_OUT_Item_Table,Room_Check, History_View_Table,History_Item_Table;
	JScrollPane scroll,scroll2;
	JButton Room_Available;
	JComboBox<String> Payment_type;
	JButton Confirm_btn, AllocateRoom_btn, generate_bill;
	DefaultTableModel tableModel,checkOuttableModel,checkOut_item_tableModel,historytableModel;
	JTextField Room_NO_txt, Name_txt;
	JTextField Checkout_Name, Discount_txt, Vat_txt, Total_price_txt, Final_price;
	Booking_MW Confirm = new Booking_MW();
	Amounts_MW amount = new Amounts_MW();
//	static 
	static int bid,pid;
//	static 
	
	public Receptionist_Dashboard(String Name) {
		
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
		
		Check_IN_btn = new JButton("Check IN");
		Check_IN_btn.addActionListener(this);
		Check_IN_btn.setBounds(50,250,180,40);
        Left_panel.add(Check_IN_btn);
        
        Check_OUT_btn = new JButton("Check OUT");
        Check_OUT_btn.addActionListener(this);
        Check_OUT_btn.setBounds(50,320,180,40);
        Left_panel.add(Check_OUT_btn);
        
        History_btn = new JButton("History");
        History_btn.setBounds(50,390,180,40);
        History_btn.addActionListener(this);
        Left_panel.add(History_btn);
        
//        About_btn = new JButton("About");
//        Profile_btn.addActionListener(this);
//        Profile_btn.setBounds(50,460,180,40);
//        Left_panel.add(Profile_btn);
        
        Logout_btn = new JButton("Logout");
        Logout_btn.addActionListener(this);
        Logout_btn.setBounds(50,460,180,40);
        Left_panel.add(Logout_btn);

        
        welcome = new JLabel("Hotel Luton");
        welcome.setFont(new Font("Arial", Font.CENTER_BASELINE, 70));	
        welcome.setForeground(Color.WHITE);
        welcome.setBounds(500,40,400,60);
        Top_panel.add(welcome);
        
	}
	
	public void Logout() {
    	sharedFrame.switchToLogin();
    	sharedFrame.repaint();
		sharedFrame.revalidate();
    }
	
	public void Check_IN() {
		
		JLabel Room_NO, Name, Heading;		
		
//		Heading = new JLabel("Check IN after conforming and allocating available room as per customer requested");
		Heading = new JLabel("Confirm & Allocate Room");
		Heading.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
		Heading.setBounds(120, 40, 1000, 30);
		Center_panel.add(Heading);
		
		
		String[] Column = {"BID","Name","Book Date","Check IN","Check OUT","Room No","Room Type","Booking Status"};
		
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(Column);
		Check_IN_View_Table = new JTable();
		Check_IN_View_Table.setModel(tableModel);
		Check_IN_View_Table.getTableHeader().setReorderingAllowed(false);
		Check_IN_View_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Check_IN_View_Table.setFillsViewportHeight(true);
		Check_IN_View_Table.setVisible(true);
		Check_IN_View_Table.addMouseListener(this);
		
		scroll = new JScrollPane(Check_IN_View_Table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(240, 120, 950, 200);
		Center_panel.add(scroll);
		
		Room_Available = new JButton("Check Room");
		Room_Available.setBounds(500, 380, 150, 30);
		Room_Available.addActionListener(this);
		Center_panel.add(Room_Available);
		
		Name = new JLabel("Name:");
		Name.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		Name.setBounds(500, 450, 150, 30);
		Center_panel.add(Name);
		
		Name_txt = new JTextField();
		Name_txt.setBounds(610, 450, 150, 30);
		Center_panel.add(Name_txt);
		
		Room_NO = new JLabel("Room NO:");
		Room_NO.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		Room_NO.setBounds(500, 510, 150, 30);
		Center_panel.add(Room_NO);
		
		Room_NO_txt = new JTextField();
		Room_NO_txt.setBounds(610, 510, 150, 30);
		Center_panel.add(Room_NO_txt);
		
		AllocateRoom_btn = new JButton("Allocate Room");
		AllocateRoom_btn.setBounds(700,600,180,30);
		AllocateRoom_btn.addActionListener(this);
		Center_panel.add(AllocateRoom_btn);
		
		Confirm_btn = new JButton("Check IN");
		Confirm_btn.setBounds(900,600,150,30);
		Confirm_btn.addActionListener(this);
		Center_panel.add(Confirm_btn);
		
		timer.start();
	}
	
	public void Check_OUT() {
		JLabel Name, Discount, Vat, Total_price ;

		String[] Column = {"BID","Name","Check IN","Check OUT","Room No","Room Type","Booking Status"};
		
		checkOuttableModel = new DefaultTableModel();
		checkOuttableModel.setColumnIdentifiers(Column);
		Check_OUT_View_Table = new JTable();
		Check_OUT_View_Table.setModel(checkOuttableModel);
		Check_OUT_View_Table.getTableHeader().setReorderingAllowed(false);
		Check_OUT_View_Table.addMouseListener(this);
		Check_OUT_View_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Check_OUT_View_Table.setFillsViewportHeight(true);
		Check_OUT_View_Table.setVisible(true);
		scroll = new JScrollPane(Check_OUT_View_Table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(20, 20, 950, 200);
		Center_panel.add(scroll);
		
		String[] Column2 = {"Item","Qty","Price","Status"};
		
		checkOut_item_tableModel = new DefaultTableModel();
		checkOut_item_tableModel.setColumnIdentifiers(Column2);
		Check_OUT_Item_Table = new JTable();
		Check_OUT_Item_Table.setModel(checkOut_item_tableModel);
		Check_OUT_Item_Table.getTableHeader().setReorderingAllowed(false);
		Check_OUT_Item_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Check_OUT_Item_Table.setFillsViewportHeight(true);
		Check_OUT_Item_Table.setVisible(true);
		scroll2 = new JScrollPane(Check_OUT_Item_Table);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll2.setBounds(1020, 20, 400, 200);
		Center_panel.add(scroll2);
		
		Name = new JLabel("Name:");
		Name.setFont(new Font("Arial", Font.PLAIN, 20));
		Name.setBounds(200, 300, 80, 30);
		Center_panel.add(Name);
		
		Vat = new JLabel("Vat:");
		Vat.setFont(new Font("Arial", Font.PLAIN, 20));
		Vat.setBounds(200, 350, 80, 30);
		Center_panel.add(Vat);
		
		Total_price = new JLabel("Total Price:");
		Total_price.setFont(new Font("Arial", Font.PLAIN, 20));
		Total_price.setBounds(200, 400, 130, 30);
		Center_panel.add(Total_price);
		
		Discount = new JLabel("Discount:");
		Discount.setFont(new Font("Arial", Font.PLAIN, 20));
		Discount.setBounds(200, 450, 100, 30);
		Center_panel.add(Discount);
		
		Checkout_Name = new JTextField();
//		Name_txt.setFont(new Font("Arial", Font.PLAIN, 20));
		Checkout_Name.setBounds(320, 300, 150, 30);
		Center_panel.add(Checkout_Name);
		
		Vat_txt = new JTextField("13%");
//		Name_txt.setFont(new Font("Arial", Font.PLAIN, 20));
		Vat_txt.setEditable(false);
		Vat_txt.setBounds(320, 350, 150, 30);
		Center_panel.add(Vat_txt);
		
		Total_price_txt = new JTextField();
//		Name_txt.setFont(new Font("Arial", Font.PLAIN, 20));
		Total_price_txt.setBounds(320, 400, 150, 30);
		Center_panel.add(Total_price_txt);
		
		Discount_txt = new JTextField();
//		Name_txt.setFont(new Font("Arial", Font.PLAIN, 20));
		Discount_txt.setBounds(320, 450, 150, 30);
		Center_panel.add(Discount_txt);
		
		Final_price = new JTextField();
//		Name_txt.setFont(new Font("Arial", Font.PLAIN, 20));
		Final_price.setBounds(320, 500, 150, 30);
		Center_panel.add(Final_price);
		
		String[] items = {"Card","Cash","Online"};
		Payment_type = new JComboBox<>(items);
		Payment_type.setSelectedItem("Green");
		Payment_type.setBounds(320, 550, 150, 30);
        Center_panel.add(Payment_type);
		
		
		generate_bill = new JButton("Generate Bill");
		generate_bill.addActionListener(this);
		generate_bill.setBounds(480, 500, 130, 30);
		Center_panel.add(generate_bill);
		
		Done = new JButton("Payed");
		Done.addActionListener(this);
		Done.setBounds(400, 600, 80, 30);
		Center_panel.add(Done);
		
		Conform_Check_OUT_btn = new JButton("Check OUT");
		Conform_Check_OUT_btn.addActionListener(this);
		Conform_Check_OUT_btn.setBounds(1150,500,150,30);
		Center_panel.add(Conform_Check_OUT_btn);
	}
	
	public void History() {
		String[] Column = {"BID","Name","Booked Date","Check IN","Check OUT","Room No","Room Type","Booking Status"};
//		DefaultTableModel tableModel;
		
		historytableModel = new DefaultTableModel();
		historytableModel.setColumnIdentifiers(Column);
		History_View_Table = new JTable();
		History_View_Table.setModel(historytableModel);
		History_View_Table.getTableHeader().setReorderingAllowed(false);
		History_View_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		History_View_Table.setFillsViewportHeight(true);
		History_View_Table.setVisible(true);
		scroll = new JScrollPane(History_View_Table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(20, 20, 950, 200);
		Center_panel.add(scroll);
		
		String[] Column2 = {"Item","Qty","Price"};
		DefaultTableModel tableModel2;
		
		tableModel2 = new DefaultTableModel();
		tableModel2.setColumnIdentifiers(Column2);
		History_Item_Table = new JTable();
		History_Item_Table.setModel(tableModel2);
		History_Item_Table.getTableHeader().setReorderingAllowed(false);
		History_Item_Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		History_Item_Table.setFillsViewportHeight(true);
		History_Item_Table.setVisible(true);
		scroll2 = new JScrollPane(History_Item_Table);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll2.setBounds(1020, 20, 250, 200);
		Center_panel.add(scroll2);
	}
	

	public void Room_Check(JFrame frame) {
		frame = new JFrame();
		frame.setTitle("Luton Hotel");
		frame.setSize(400,200);
//		getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setLayout(null);
		frame.setVisible(true);
		
		frame.setPreferredSize(new Dimension(400, 300));

        // Pack the components in the JFrame
        frame.pack();

        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the coordinates to center the JFrame
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;

        // Set the location of the JFrame to the center of the screen
        frame.setLocation(x, y);
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        String[] Column = {"Room No","Room Type","Room Status"};
		DefaultTableModel tableModel;
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(Column);
		Room_Check = new JTable();
		Room_Check.setModel(tableModel);
		Room_Check.getTableHeader().setReorderingAllowed(false);
		Room_Check.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Room_Check.setFillsViewportHeight(true);
		Room_Check.setVisible(true);
		
		scroll = new JScrollPane(Room_Check);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(0, 0, 400, 300);
		frame.add(scroll);
		
		
		List<Room_MW> roomList=new RoomJDBCdao().ViewAvailableBooking();
		for(Room_MW room:roomList) {
			tableModel.insertRow(0, new Object[] { room.getRoom_NO(),room.getRoom_Type(),room.getRoom_Status()});		
	}
	}
	
	Timer timer = new Timer(2000, new ActionListener() {
		@Override
      public void actionPerformed(ActionEvent e) {
			DefaultTableModel model = (DefaultTableModel)Check_IN_View_Table.getModel();
			model.setRowCount(0);
			
			List<ReceptionistCheckIN_MW> bookList=new BookingCRUDdao().ViewPendingBooking();
			for(ReceptionistCheckIN_MW book:bookList) {
				tableModel.insertRow(0, new Object[] { book.getBooking_ID(),book.getFirst_Name()+" "+book.getLast_Name(),book.getBook_Date(),book.getCheck_IN(),book.getCheck_OUT(),book.getRoom_NO(),book.getRoom_Type(),book.getStatus_Type() });		
		}	
		}
	}
);
	
	
//	public static void main(String[] args) {
//		new Receptionist_Dashboard();
//	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==Check_IN_btn) {
			Center_panel.removeAll();
			Center_panel.repaint();
			Center_panel.revalidate();
			Check_IN();
			
			List<ReceptionistCheckIN_MW> bookList=new BookingCRUDdao().ViewPendingBooking();
			for(ReceptionistCheckIN_MW book:bookList) {
				tableModel.insertRow(0, new Object[] { book.getBooking_ID(),book.getFirst_Name()+" "+book.getLast_Name(),book.getBook_Date(),book.getCheck_IN(),book.getCheck_OUT(),book.getRoom_NO(),book.getRoom_Type(),book.getStatus_Type() });		
		}
		}
		
		if(ae.getSource()==Check_OUT_btn) {
			Center_panel.removeAll();
			Center_panel.repaint();
			Center_panel.revalidate();
			Check_OUT();
			List<ReceptionistCheckOUT_MW> bookList=new BookingCRUDdao().ViewActiveBooking();
			for(ReceptionistCheckOUT_MW book:bookList) {
				checkOuttableModel.insertRow(0, new Object[] { book.getBooking_ID(),book.getFirst_Name()+" "+book.getLast_Name(),book.getCheck_IN(),book.getCheck_OUT(),book.getRoom_NO(),book.getRoom_Type(),book.getStatus_Type() });		
		}	
		}
		
		if(ae.getSource()==History_btn) {
			Center_panel.removeAll();
			Center_panel.repaint();
			Center_panel.revalidate();			
			History();
			List<ReceptionistCheckOUT_MW> bookList=new BookingCRUDdao().ViewAllBooking();
			for(ReceptionistCheckOUT_MW book:bookList) {
				historytableModel.insertRow(0, new Object[] { book.getBooking_ID(),book.getFirst_Name()+" "+book.getLast_Name(),book.getBook_Date(), book.getCheck_IN(),book.getCheck_OUT(),book.getRoom_NO(),book.getRoom_Type(),book.getStatus_Type() });		
		}	
		}
		
		if(ae.getSource()==Room_Available) {
			Room_Check(sharedFrame);
		}
		
		if(ae.getSource()==AllocateRoom_btn) {
			int roomno = Integer.parseInt(Room_NO_txt.getText());
			Confirm.setRoom_NO(roomno);
			
			
	        int result = JOptionPane.showConfirmDialog(sharedFrame, "Are you sure you want to Confirm the Room?", "Confirmation", JOptionPane.YES_NO_OPTION);

	        if (result == JOptionPane.YES_OPTION) {
	        	BookingCRUDdao checkin = new BookingCRUDdao();
	        	checkin.AllocateRoom(Confirm);
	        	
	        	RoomJDBCdao room = new RoomJDBCdao();
	        	room.ClosedRoomStatus(Confirm);
				
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
		
		if(ae.getSource()==Confirm_btn) {
//			Booking_MW Confirm = new Booking_MW();
			int roomno = Integer.parseInt(Room_NO_txt.getText());
			Confirm.setRoom_NO(roomno);
			
			
	        int result = JOptionPane.showConfirmDialog(sharedFrame, "Are you sure you want to Check IN?", "Confirmation", JOptionPane.YES_NO_OPTION);

	        if (result == JOptionPane.YES_OPTION) {
	        	BookingCRUDdao checkin = new BookingCRUDdao();
	        	checkin.ConfirmBooking(Confirm);
	        	
	        	RoomJDBCdao room = new RoomJDBCdao();
	        	room.ClosedRoomStatus(Confirm);
				
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
		
		
		if(ae.getSource()==Logout_btn) {
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
		
		if(ae.getSource()==generate_bill) {
			ActiveBookingdao check = new ActiveBookingdao();
			Payment_MW pay = new Payment_MW();
			pay.setBooking_ID(Confirm.getBooking_ID());
//			pay.setBooking_ID(bid);
			boolean result = check.CheckBill(pay);
//			Payment_MW pay = new Payment_MW();
			
			if(result==true) {
				check.ActiveBill(pay);
				
				pid = pay.getPayment_ID();
				
				pay.setPayment_ID(pid);
				LocalDate currentDate = LocalDate.now();
				pay.setPayment_Date(currentDate.toString());
				pay.setTotal_Payment(amount.getTotal_price());
				
				Paymentdao bill = new Paymentdao();
				bill.Updateoldbill(pay);
				System.out.println(amount.getTotal_price());
				
				JOptionPane.showMessageDialog(generate_bill, "Bill updated!");
				
			}
			else {
				pay.setBooking_ID(Confirm.getBooking_ID());
				LocalDate currentDate = LocalDate.now();
				pay.setPayment_Date(currentDate.toString());
				pay.setPayment_Mode(null);
				pay.setTotal_Payment(amount.getTotal_price());
				pay.setPayment_Status("Unpaid");
				
				Paymentdao bill = new Paymentdao();
				bill.Bill(pay);
				
				JOptionPane.showMessageDialog(generate_bill, "Bill Generated!");
			}
		}
		if(ae.getSource()==Done) {
			Payment_MW pay = new Payment_MW();
			
			String type = (String) Payment_type.getSelectedItem();
			pay.setPayment_Mode(type);
			pay.setPayment_ID(pid);
//			System.out.println(pid);
			
			Paymentdao bill = new Paymentdao();
			bill.UpdateBill(pay);
			
			JOptionPane.showMessageDialog(Done, "Payment Successful!");
		}
		
		if(ae.getSource()==Conform_Check_OUT_btn) {
			
			
			int result = JOptionPane.showConfirmDialog(sharedFrame, "Are you sure you want to Confirm", "Confirmation", JOptionPane.YES_NO_OPTION);

	        if (result == JOptionPane.YES_OPTION) {
	        	BookingCRUDdao checkout = new BookingCRUDdao();
				checkout.Ckeck_OUTBooking(Confirm);
				
				RoomJDBCdao open = new RoomJDBCdao();
				open.OpenRoomStatus(Confirm);
				
				DefaultTableModel model = (DefaultTableModel)Check_OUT_View_Table.getModel();
				model.setRowCount(0);
				
				List<ReceptionistCheckOUT_MW> bookList=new BookingCRUDdao().ViewActiveBooking();
				for(ReceptionistCheckOUT_MW book:bookList) {
					checkOuttableModel.insertRow(0, new Object[] { book.getBooking_ID(),book.getFirst_Name()+" "+book.getLast_Name(),book.getCheck_IN(),book.getCheck_OUT(),book.getRoom_NO(),book.getRoom_Type(),book.getStatus_Type() });		
			}	
				
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
	
	
	
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==Check_IN_View_Table) {
			try {
				
				
		        // get the index of the selected row
				int rows = Check_IN_View_Table.getSelectedRow();

		        // get the table model
				TableModel model1 = Check_IN_View_Table.getModel();

		        // set the values of the text fields to the values from the selected row
				String value = (String) model1.getValueAt(rows, 1);
				Name_txt.setText(value);
				
				int room = (int) model1.getValueAt(rows, 5);
				Room_NO_txt.setText(Integer.toString(room));
				
				int bid = (int) model1.getValueAt(rows, 0);
				Confirm.setBooking_ID(bid);

			} catch (Exception ex) {
				System.out.println("Error" + ex.getMessage());
			}
		}
		if(e.getSource()==Check_OUT_View_Table) {
			
			try {
				
				
		        // get the index of the selected row
				int rows = Check_OUT_View_Table.getSelectedRow();

		        // get the table model
				TableModel model = Check_OUT_View_Table.getModel();

		        // set the values of the text fields to the values from the selected row				
				int bid = (int) model.getValueAt(rows, 0);
				Confirm.setBooking_ID(bid);
				
				int roomno = (int) model.getValueAt(rows, 4);
				Confirm.setRoom_NO(roomno);
				
				String name = (String) model.getValueAt(rows, 1);
				Checkout_Name.setText(name);
				
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(rows, 2));
				Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(rows, 3));
				
				String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
				String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
				// Calculate the number of stay days
				LocalDate startdate = LocalDate.parse(dateString);
				LocalDate enddate = LocalDate.parse(dateString2);

				Long Stay = ChronoUnit.DAYS.between(startdate, enddate);
				
				DefaultTableModel clearmodel = (DefaultTableModel)Check_OUT_Item_Table.getModel();
				clearmodel.setRowCount(0);
								
				List<ViewServices_MW> itemList=new ItemJDBCdao().Viewitem(bid);
				for(ViewServices_MW item:itemList) {
					
					checkOut_item_tableModel.insertRow(0, new Object[] { item.getItem(),item.getQuantity(),item.getQuantity()*item.getPrice(),item.getService_Status()});		
			}
				
				List<Room_MW> List=new RoomJDBCdao().ViewRoomPrice(roomno);
				for(Room_MW room:List) {
					float price;
					checkOut_item_tableModel.insertRow(0, new Object[] { room.getRoom_Type()+' '+"Room",Stay+" "+"Days",Stay*room.getRoom_price(),room.getRoom_Status()});	
					price = Stay*room.getRoom_price();
					amount.setPrice(price);
					
					
			}
			
				List<ViewServices_MW> total=new ItemJDBCdao().Viewitem(bid);
				double totalprice = 0.0;
				double vatprice;
				for(ViewServices_MW item:total) {
					
//					checkOut_item_tableModel.insertRow(0, new Object[] { item.getItem(),item.getQuantity(),item.getPrice(),item.getService_Status()});	
					item.getPrice();
					totalprice +=item.getQuantity()*item.getPrice();
					amount.setTotal_price(totalprice);
//					System.out.println(totalprice);
					
//					totalprice = totalprice+price;
			}
			double total_price = amount.getTotal_price();
			float price = amount.getPrice();
				
				total_price = total_price+price;
				vatprice = total_price*0.13;
				total_price = total_price+vatprice;
//				
				amount.setTotal_price(total_price);
				
			
				Total_price_txt.setText(Double.toString(total_price));
			
			

			} catch (Exception ex) {
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
}
