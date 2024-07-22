package com.zoho.Foodordering.dao;

import com.zoho.Foodordering.Models.User;
import com.zoho.Foodordering.util.SqlQueries; 
import com.zoho.Foodordering.util.Database; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUser{
	public UserDAO(){
		
	}
	public boolean addUser(User user){
		try{
			PreparedStatement ps = Database.getPreparedStatement(SqlQueries.INSERT_USER);
			
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getContact());
			ps.setString(4,user.getEmail());
			ps.setString(5,user.getRole().toString());
			ps.setString(6,user.getName());
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;
	}
	public User getUser(String username,String password){
		try{
			PreparedStatement ps = Database.getPreparedStatement(SqlQueries.GET_USER_BY_USERNAME_PASSWORD);
			ps.setString(1,username);
			ps.setString(2,password);
			ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("UserId"));
               
                user.setUsername(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
				user.setContact(rs.getString("Contact"));
                user.setEmail(rs.getString("Email"));
                user.setRole(User.Role.valueOf(rs.getString("Role").toUpperCase()));
                user.setName(rs.getString("Name"));
                System.out.println("User found: " + user);
                return user;
            }else {
                System.out.println("No user found with the given username and password.");
            }
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	 public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = Database.getPreparedStatement(SqlQueries.GET_USERS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email_id"));
                user.setContact(rs.getString("mobile_number"));
                user.setRole(User.Role.valueOf(rs.getString("role")));
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
  public boolean updateUser(User user) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SqlQueries.UPDATE_USER);
            ps.setInt(1, user.getUserId());
            ps.setString(2,user.getName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getContact());
            ps.setString(7, user.getRole().toString());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
	 public boolean deleteUser(int userId) {
        try {

            PreparedStatement ps = Database.getPreparedStatement(SqlQueries.DELETE_USER);
            ps.setInt(1, userId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
	 public  void changePassword(String contact,String newPassword) {
		 try {
			 PreparedStatement ps = Database.getPreparedStatement(SqlQueries.CHANGE_PASSWORD);
			 ps.setString(1, newPassword);
			 ps.setString(2, contact);
			 ps.executeUpdate();
		 } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	 }
	public User loginUser(String username, String password) {
        return getUser(username,password);
    }
	
}