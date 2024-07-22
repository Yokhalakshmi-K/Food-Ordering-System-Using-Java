package com.zoho.Foodordering.dao;

import com.zoho.Foodordering.Models.User;
import java.util.List;

public interface IUser {
     boolean addUser(User user);
    //User getUserById(int userId);
    User getUser(String username,String password);
    List<User> getAllUsers();
    boolean updateUser(User user);
    boolean deleteUser(int userId);
}