package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Middleware.MealItem_MW;
import Middleware.ViewServices_MW;

public class ItemJDBCdao {
	public List<ViewServices_MW> Viewitem(int id) {
	    List<ViewServices_MW> itemList = new ArrayList<ViewServices_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT s.*, i.Item, i.Price FROM services s LEFT JOIN itemtype i ON s.Item_ID = i.Item_ID WHERE s.Booking_ID=?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setInt(1, id);
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	ViewServices_MW item = new ViewServices_MW();
	            String itemname = rs.getString("Item");
	            String status = rs.getString("Status");
	            int quantity = rs.getInt("Quantity");
	            float price = rs.getFloat("Price");


	            item.setItem(itemname);
	            item.setQuantity(quantity);
	            item.setPrice(price);
	            item.setService_Status(status);

	            itemList.add(item);

	        }
	        pstat.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error: " + e.getMessage());
	    }
	    return itemList;
	}
	
	public List<MealItem_MW> Mealitem() {
	    List<MealItem_MW> MealList = new ArrayList<MealItem_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT Item, Price, Item_ID FROM itemtype WHERE Item_Type = ?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setString(1, "Meals");
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	MealItem_MW meal = new MealItem_MW();
	        	
	            String itemname = rs.getString("Item");
	            float price = rs.getFloat("Price");
	            int item_id = rs.getInt("Item_ID");
	            
	            meal.setItem(itemname);
	            meal.setPrice(price);
	            meal.setItem_ID(item_id);

	            MealList.add(meal);

	        }
	        pstat.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error: " + e.getMessage());
	    }
	    return MealList;
	}
	
	public List<MealItem_MW> Breakfastitem() {
	    List<MealItem_MW> MealList = new ArrayList<MealItem_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT Item, Price, Item_ID FROM itemtype WHERE Item_Type = ?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setString(1, "Breakfast");
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	MealItem_MW meal = new MealItem_MW();
	        	
	            String itemname = rs.getString("Item");
	            float price = rs.getFloat("Price");
	            int item_id = rs.getInt("Item_ID");
	            
	            meal.setItem(itemname);
	            meal.setPrice(price);
	            meal.setItem_ID(item_id);

	            MealList.add(meal);

	        }
	        pstat.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error: " + e.getMessage());
	    }
	    return MealList;
	}
	
	public List<MealItem_MW> Drinkitem() {
	    List<MealItem_MW> MealList = new ArrayList<MealItem_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT Item, Price, Item_ID FROM itemtype WHERE Item_Type = ?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setString(1, "Drinks");
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	MealItem_MW meal = new MealItem_MW();
	        	
	            String itemname = rs.getString("Item");
	            float price = rs.getFloat("Price");
	            int item_id = rs.getInt("Item_ID");
	            
	            meal.setItem(itemname);
	            meal.setPrice(price);
	            meal.setItem_ID(item_id);

	            MealList.add(meal);

	        }
	        pstat.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error: " + e.getMessage());
	    }
	    return MealList;
	}
	
	public List<MealItem_MW> Otheritem() {
	    List<MealItem_MW> MealList = new ArrayList<MealItem_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT Item, Price, Item_ID FROM itemtype WHERE Item_Type = ?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setString(1, "amenities");
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	MealItem_MW meal = new MealItem_MW();
	        	
	            String itemname = rs.getString("Item");
	            float price = rs.getFloat("Price");
	            int item_id = rs.getInt("Item_ID");
	            
	            meal.setItem(itemname);
	            meal.setPrice(price);
	            meal.setItem_ID(item_id);

	            MealList.add(meal);

	        }
	        pstat.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error: " + e.getMessage());
	    }
	    return MealList;
	}
	
	public List<ViewServices_MW> ViewAllitem() {
	    List<ViewServices_MW> itemList = new ArrayList<ViewServices_MW>();
	    try {

	        Connection conn = new Connect().getConnection();
	        String sql = "SELECT s.*, i.Item, i.Price FROM services s LEFT JOIN itemtype i ON s.Item_ID = i.Item_ID WHERE Status=?";
	        PreparedStatement pstat = conn.prepareStatement(sql);
	        pstat.setString(1, "PENDING");
	        ResultSet rs = pstat.executeQuery();
	        while (rs.next()) {
	        	ViewServices_MW item = new ViewServices_MW();
	            String itemname = rs.getString("Item");
	            int quantity = rs.getInt("Quantity");
	            int sid = rs.getInt("Service_ID");
	            float price = rs.getFloat("Price");
	            String status = rs.getString("Status");


	            item.setItem(itemname);
	            item.setQuantity(quantity);
	            item.setPrice(price);
	            item.setService_Status(status);
	            item.setService_ID(sid);
	            
	            itemList.add(item);

	        }
	        pstat.close();
	        rs.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error: " + e.getMessage());
	    }
	    return itemList;
	}
}
