package com.zoho.Foodordering.Models;


import java.util.List;
import java.util.Date;

public class Order{
	private int orderId;
    private int userId;
    private int hotelId;
    private int foodId;
    private int quantity;
    private double total;
    private Date orderDate;
    private String hotelName;
    private String foodName;
    
    public Order() {
    	
    }
    public Order(int userId,int hotelId,int foodId,int quantity, double total,Date orderDate) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.total = total;
        this.orderDate = orderDate;
    }
    public Order(int orderId, int userId,String hotelName,String foodName,int quantity, double total, Date orderDate) {
    	this.orderId = orderId;
        this.userId = userId;
        this.hotelName = hotelName;
        this.foodName =foodName ;
        this.total = total;
        this.orderDate = orderDate;
    }
    
    public Order(int orderId,int userId,int hotelId,int foodId, int quantity, double total, Date orderDate) {
    	this.orderId = orderId;
        this.userId = userId;
        this.hotelId = hotelId;
        this.foodId = foodId;
        this.total = total;
        this.orderDate = orderDate;
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getHotelId(){
		return hotelId;
	}
	public void setHotelId(int hotelId){
		this.hotelId = hotelId;
	}
	
	public int getFoodId(){
		return foodId;
	}
	public void setFoodId(int foodId){
		this.foodId = foodId;
	}
	
	public int getQuantity(){
		return quantity;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", hotelId=" + hotelId +
                ", foodId=" + foodId +
                ", quantity=" + quantity +
                ", totalPrice=" + total +
                ", orderDate=" + orderDate +
                '}';
    }
}
