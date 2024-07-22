package com.zoho.Foodordering.dao;

import com.zoho.Foodordering.Models.Food;
import com.zoho.Foodordering.util.*;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class FoodDAO implements IFood {
	 public FoodDAO(){

	    }
	  
	    public boolean addFood(Food food) {
	        try {
	            PreparedStatement ps = Database.getPreparedStatement(SqlQueries.INSERT_FOOD);
	            ps.setString(1, food.getFoodName());
	            ps.setString(2, food.getDescription());
	            int rowsAffected = ps.executeUpdate();
	            return rowsAffected > 0;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    public List<Food> getAllFoods() {
	        List<Food> foods = new ArrayList<>();
	        try {
	            PreparedStatement ps = Database.getPreparedStatement(SqlQueries.GET_ALL_FOODS);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Food food = new Food();
	                food.setFoodName("Food_Name");
	                food.setDescription("Description");
	                foods.add(food);
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return foods;
	    }

	    public boolean updateFood(Food food) {
	        try {
	            PreparedStatement ps = Database.getPreparedStatement(SqlQueries.UPDATE_FOOD);
	            ps.setString(1, food.getFoodName());
	            ps.setString(2,food.getDescription());
	            ps.setInt(3, food.getFoodId());
	            int rowsAffected = ps.executeUpdate();
	            return rowsAffected > 0;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }
	    public boolean removeFood(int foodId) {
	        try {
	            PreparedStatement ps = Database.getPreparedStatement(SqlQueries.DELETE_FOOD);
	            ps.setInt(1, foodId);
	            int rowsAffected = ps.executeUpdate();
	            return rowsAffected > 0;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	  

	   
	    public List<Food> getFoodsByHotel(int hotelId) {
	        List<Food> foods = new ArrayList<>();
	        try (PreparedStatement statement = Database.getPreparedStatement(SqlQueries.GET_FOOD_BY_HOTEL)) {
	            statement.setInt(1, hotelId);
	            ResultSet rs = statement.executeQuery();
	            while (rs.next()) {
	                int foodId = rs.getInt("FoodId");
	                String foodName = rs.getString("Food_Name");
	                String Description = rs.getString("Description");

	              Food food = new Food(foodId, foodName, Description);
	                foods.add(food);
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return foods;
	    }
}
