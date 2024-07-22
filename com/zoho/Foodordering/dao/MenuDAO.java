package com.zoho.Foodordering.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.zoho.Foodordering.Models.Menu;
import com.zoho.Foodordering.util.Database;
import com.zoho.Foodordering.util.SqlQueries;

public class MenuDAO implements IMenu {
	public MenuDAO() {
		
	}
	public boolean mapHotelAndFood(Menu menu) {
		try{
			PreparedStatement ps = Database.getPreparedStatement(SqlQueries.INSERT_MENU);
			ps.setInt(1, menu.getHotelId());
			ps.setInt(2, menu.getFoodId());
			ps.setDouble(3, menu.getPrice());
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	}

