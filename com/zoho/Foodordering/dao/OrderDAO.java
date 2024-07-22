package com.zoho.Foodordering.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zoho.Foodordering.Models.Order;
import com.zoho.Foodordering.Models.User;
import com.zoho.Foodordering.util.Database;
import com.zoho.Foodordering.util.SqlQueries;

public class OrderDAO {
	public OrderDAO(){

    }
	public void placeOrderByNames(String username,String hotelName,String foodName,int quantity) {
		        try (Connection connection = Database.getConnection()) {
		            int userId = getUserIdByUsername(username, connection);
		            int hotelId = getHotelIdByName(hotelName, connection);
		            int foodId = getFoodIdByName(foodName, connection);
		            double price = getPriceByMenu(hotelId, foodId, connection);
		            double totalPrice = price * quantity;
		            Date orderDate = new Date(System.currentTimeMillis());
		            
		            if (userId > 0 && hotelId > 0 && foodId > 0) {
		                placeOrder(new Order(userId, hotelId, foodId,quantity, totalPrice,orderDate), connection);
		            } else {
		                System.out.println("Invalid hotel name, food name, or username.");
		            }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	  private static int getUserIdByUsername(String username, Connection connection) throws SQLException {
	       
	        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_USERID)) {
	            statement.setString(1, username);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                return resultSet.getInt("UserId");
	            }
	        }
	        return -1;
	    }
	  private static int getHotelIdByName(String hotelName, Connection connection) throws SQLException {
	      
	        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_HOTELID)) {
	            statement.setString(1, hotelName);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                return resultSet.getInt("HotelId");
	            }
	        }
	        return -1;
	    }
	  private static int getFoodIdByName(String foodName, Connection connection) throws SQLException {
	        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_FOODID)) {
	            statement.setString(1, foodName);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                return resultSet.getInt("FoodId");
	            }
	        }
	        return -1;
	    }
	  private static double getPriceByMenu(int hotelId, int foodId, Connection connection) throws SQLException {
	      
	        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_PRICE)) {
	            statement.setInt(1, hotelId);
	            statement.setInt(2, foodId);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                return resultSet.getDouble("Price");
	            }
	        }
	        return -1;
	    }
	  private static void placeOrder(Order order, Connection connection) throws SQLException {
	     
	        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.PLACE_ORDER)) {
	            statement.setInt(1, order.getUserId());
	            statement.setInt(2, order.getHotelId());
	            statement.setInt(3, order.getFoodId());
	            statement.setInt(4, order.getQuantity());
	            statement.setDouble(5, order.getTotal());
	            statement.setDate(6, new Date(order.getOrderDate().getTime()));
	            
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Order placed successfully!");
	            }
	        }
	    }
	  public void cancelOrderByContact(String contactNumber) {
	        try (Connection connection = Database.getConnection()) {
	            cancelOrderByContact(contactNumber, connection);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	  private static void cancelOrderByContact(String contactNumber, Connection connection) throws SQLException {
	        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.CANCEL_ORDER_BY_CONTACT)) {
	            statement.setString(1, contactNumber);

	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Order cancelled successfully!");
	            } else {
	                System.out.println("Order not found.");
	            }
	        }
	    }
	  public void viewOrders(String contactNumber) {
	        try (Connection connection = Database.getConnection()) {
	        	System.out.println(viewOrders(contactNumber, connection));
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	  private static List<Order> viewOrders(String contact, Connection connection) throws SQLException {
		    List<Order> orders = new ArrayList<>();
		    try (PreparedStatement statement = connection.prepareStatement(SqlQueries.VIEW_ORDERS_BY_CONTACT)) {
		        statement.setString(1, contact);
		        try (ResultSet resultSet = statement.executeQuery()) {
		            while (resultSet.next()) {
		                int orderId = resultSet.getInt("OrderId");
		                int userId = resultSet.getInt("UserId");
		                int hotelId = resultSet.getInt("HotelId");
		                int foodId = resultSet.getInt("FoodId");
		                int quantity = resultSet.getInt("Quantity");
		                double total = resultSet.getDouble("total_price");
		                java.sql.Date orderDate = resultSet.getDate("Order_Date");
		                System.out.println(quantity);
		                String hotelName = getHotelNameById(hotelId, connection);
		                String foodName = getFoodNameById(foodId, connection);
		              
		                Order order = new Order(orderId, userId, hotelName, foodName, quantity, total, new java.util.Date(orderDate.getTime()));
		                order.setHotelId(hotelId);
		                order.setFoodId(foodId);
		                order.setQuantity(quantity);
		                orders.add(order);
		                
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return orders;
		}
private static String getHotelNameById(int hotelId, Connection connection) throws SQLException {
    
    try (PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_HOTELNAME_BY_ID)) {
        statement.setInt(1, hotelId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("Hotel_Name");
        }
    }
    return null;
}
private static String getFoodNameById(int foodId, Connection connection) throws SQLException {
   
    try (PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_FOODNAME_BY_ID)) {
        statement.setInt(1, foodId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("food_name");
        }
    }
    return null;
}
}
