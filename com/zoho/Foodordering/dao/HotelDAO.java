package com.zoho.Foodordering.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zoho.Foodordering.Models.Hotel;
import com.zoho.Foodordering.util.*;


public class HotelDAO implements IHotel {
    public HotelDAO(){

    }
 
    public boolean addHotel(Hotel hotel) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SqlQueries.INSERT_HOTEL);
            ps.setString(1, hotel.getHotelName());
            ps.setString(2, hotel.getLocation());
            ps.setString(3, hotel.getType().toString());
            ps.setInt(4, hotel.getAdminId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Hotel getHotelById(int hotelId) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SqlQueries.GET_Hotel_BY_ID);
            ps.setInt(1, hotelId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setHotelId(rs.getInt("HotelId"));
                hotel.setHotelName(rs.getString("HotelName"));
                hotel.setLocation(rs.getString("Location"));
                hotel.setType(Hotel.hotelType.valueOf(rs.getString("Hotel_Type")));
                return hotel;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Hotel> getAllHotel() {
        List<Hotel> hotels = new ArrayList<>();
        try {
            PreparedStatement ps = Database.getPreparedStatement(SqlQueries.GET_ALL_HOTELS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setHotelId(rs.getInt("HotelId"));
                hotel.setHotelName(rs.getString("Hotel_Name"));
                hotel.setLocation(rs.getString("Location"));
                hotel.setType(Hotel.hotelType.valueOf(rs.getString("Hotel_Type")));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return hotels;
    }

    public boolean updateHotel(Hotel hotel) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SqlQueries.UPDATE_HOTEL);
            ps.setString(1, hotel.getHotelName());
            ps.setString(2, hotel.getLocation());
            ps.setString(3, hotel.getType().toString());
            ps.setInt(4, hotel.getHotelId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return false;
    }

    public boolean removeHotel(int hotelId) {
        try {
            PreparedStatement ps =Database.getPreparedStatement(SqlQueries.DELETE_HOTEL);
            ps.setInt(1,hotelId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }

    public List<Hotel> getHotelByLocation(String location) {
        List<Hotel> hotels = new ArrayList<>();

        try (PreparedStatement statement = Database.getPreparedStatement(SqlQueries.GET_HOTEL_BY_LOCATION)) {

            statement.setString(1, location);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                	   int hotelId = resultSet.getInt("hotelId");
                       String hotelName = resultSet.getString("Hotel_Name");
                       
                       String hotelType = resultSet.getString("Hotel_Type");

                       Hotel hotel = new Hotel(hotelId, hotelName, location, Hotel.hotelType.valueOf(hotelType));
                       hotels.add(hotel);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hotels;
    }
    public List<Hotel> getHotelByFood(String foodName) {
    	List<Hotel> hotelList = new ArrayList<>();
    	 try (PreparedStatement statement = Database.getPreparedStatement(SqlQueries.GET_HOTEL_BY_FOOD)) {
    	statement.setString(1, foodName);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int hotelId = resultSet.getInt("HotelId");
            String hotelName = resultSet.getString("Hotel_Name");
            String location = resultSet.getString("Location");
            String hotelType = resultSet.getString("Hotel_Type");
            double price = resultSet.getDouble("Price");
            Hotel hotel = new Hotel(hotelId, hotelName, location, Hotel.hotelType.valueOf(hotelType));
            hotelList.add(hotel);

            System.out.printf("Hotel ID: %d, Name: %s, Address: %s, Price: %.2f%n",
                    hotelId, hotelName, location, price);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return hotelList;
}
    }

