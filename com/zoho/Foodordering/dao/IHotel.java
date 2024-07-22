package com.zoho.Foodordering.dao;

import com.zoho.Foodordering.Models.Hotel;
import java.util.List;
public interface IHotel {
	boolean addHotel(Hotel hotel);
    Hotel getHotelById(int hotelId);
    List<Hotel> getAllHotel();
    boolean updateHotel(Hotel hotel);
    boolean removeHotel(int hotelId);
}
