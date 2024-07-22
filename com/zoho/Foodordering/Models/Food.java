package com.zoho.Foodordering.Models;

public class Food{
	private int foodId;
	private String foodName;
	private String description;
	public Food(){
		
	}
	public Food(String foodName,String description){
		this.foodName = foodName;
		this.description = description;
	}
	public Food(int foodId,String foodName,String description){
		this(foodName,description);
		this.foodId = foodId;
	}
	public int getFoodId(){
		return foodId;
	}
	public void setFoodId(int foodId){
		this.foodId = foodId;
	}
	public String getFoodName(){
		return foodName;
	}
	public void setFoodName(String foodName){
		this.foodName = foodName;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	 public String toString() {
        return "Food{" +
                "FoodId=" + foodId +
                ", Food name='" +foodName + '\'' +
                ", Description=" + description +
                '}';
    }
}