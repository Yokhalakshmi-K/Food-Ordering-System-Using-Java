package com.zoho.Foodordering.util;

public class SqlQueries{
	public static final String INSERT_USER = "INSERT INTO User(UserName,Password,Contact,Email,Role,Name) VALUES (?,?,?,?,?,?)";
	public static final String GET_USER_BY_USERNAME_PASSWORD = "SELECT * FROM User WHERE Username = ? and Password = ?";
	public static final String GET_USERS = "SELECT * FROM Users";
	public static final String UPDATE_USER = "UPDATE users SET UserName = ?, Password = ?,  Contact = ?, Email = ?, Role = ? WHERE UserId = ?";
	public static final String DELETE_USER = "DELETE FROM User WHERE UserId = ?";
	public static final String INSERT_FOOD = "INSERT INTO Food(Food_Name,Description) VALUES (?,?)";
	public static final String GET_ALL_FOODS = "SELECT * FROM Food";
	public static final String UPDATE_FOOD = "UPDATE Food SET Food_Name = ?, Description = ? WHERE FoodId = ?";
	public static final String DELETE_FOOD = "DELETE  FROM Food WHERE FoodId = ?";
	public static final String INSERT_HOTEL = "INSERT INTO Hotel(Hotel_Name,Location,Hotel_Type,AdminId) values (?,?,?,?)";
	public static final String GET_Hotel_BY_ID = "SELECT Hotel_Name,Location,Hotel_Type FROM Hotel where HotelId = ?";
	public static final String GET_ALL_HOTELS = "SELECT Hotel_Name,Location,Hotel_Type FROM Hotel";
	public static final String UPDATE_HOTEL = "UPDATE Hotel SET HotelName = ?,Location = ?,Hotel_Type = ? WHERE HotelId = ?";
	public static final String DELETE_HOTEL = "DELETE FROM Hotel WHERE HotelId = ?";
	public static final String GET_HOTEL_BY_LOCATION = "SELECT * FROM Hotel WHERE Location = ?";
	public static final String GET_FOOD_BY_HOTEL = "SELECT menu.HotelId,h.Hotel_Name,h.Location,f.FoodId,f.Food_Name,f.Description,menu.Price "
			+ "FROM Hotel h"
			+ "JOIN Menu ON h.HotelId = menu.HotelId"
			+ "JOIN Food ON menu.FoodId = f.FoodId"
			+ "WHERE menu.HotelId = ?";
	public static final String INSERT_MENU = "INSERT INTO Menu(HotelId,FoodId,Price) values (?,?,?)";
	public static final String GET_HOTEL_BY_FOOD ="SELECT h.HotelId,h.Hotel_Name,h.Hotel_Type,h.Location,m.Price "
			+ "FROM Hotel h "
			+ "JOIN Menu m ON h.HotelId = m.hotelId "
			+ "JOIN Food f ON f.FoodId = m.FoodId "
			+ "WHERE f.Food_Name = ?";
	public static final String PLACE_ORDER = "INSERT INTO ORDERS(UserId,HotelId,FoodId,Quantity,total_price,order_Date) values(?,?,?,?,?,?)";
	public static final String GET_USERID = "SELECT UserId FROM User WHERE UserName = ?";
	public static final String GET_HOTELID = "SELECT HotelId FROM Hotel WHERE Hotel_Name = ?";
	public static final String GET_FOODID = "SELECT FoodId FROM Food WHERE Food_Name = ?";
	public static final String GET_PRICE = "SELECT price FROM Menu WHERE HotelId = ? AND FoodId = ?";
	public static final String CANCEL_ORDER_BY_CONTACT = "DELETE FROM Orders WHERE UserId IN (SELECT UserId FROM User    WHERE Contact = ?)";
			
	public static final String VIEW_ORDERS_BY_CONTACT = "SELECT o.OrderId, u.UserId as UserId,u.UserName,h.HotelId as HotelId,f.FoodId as FoodId ,h.Hotel_Name, f.Food_Name, o.Quantity, o.total_price, o.Order_Date "
			+ "FROM Orders o "
			+ "JOIN User u ON o.UserId = u.UserId "
			+ "JOIN Hotel h ON o.HotelId = h.HotelId "
			+ "JOIN Food f ON o.FoodId = f.FoodId "
			+ "WHERE u.Contact = ?";
	public static final String GET_HOTELNAME_BY_ID = "SELECT Hotel_Name FROM Hotel WHERE HotelId = ?";
	public static final String GET_FOODNAME_BY_ID ="SELECT Food_Name FROM Food WHERE FoodId = ?";
	public static final String CHANGE_PASSWORD = "UPDATE User SET Password = ? Where Contact = ?";
}