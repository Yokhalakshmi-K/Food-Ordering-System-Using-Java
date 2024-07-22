package com.zoho.Foodordering.dao;

import com.zoho.Foodordering.Models.Food;
import java.util.List;

public interface IFood{
	boolean addFood(Food food);
    List<Food> getAllFoods();
    boolean updateFood(Food food);
    boolean removeFood(int FoodId);
}