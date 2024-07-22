package com.zoho.Foodordering.Models;

public class Menu{
	private int hotelId;
	private int foodId;
	private double price;
	private boolean availability; 
	public Menu(int hotelId, int foodId, double price) {
        this.hotelId = hotelId;
        this.foodId = foodId;
		this.price = price;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
	public double getPrice(){
		return price;
	}
	public void setPrice(){
		this.price = price;
	}
}