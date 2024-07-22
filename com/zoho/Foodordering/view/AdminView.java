package com.zoho.Foodordering.view;

import java.util.List;
import com.zoho.Foodordering.Models.Hotel;

public class AdminView {
	  public void displayHotels(List<Hotel> hotels) {
	        System.out.println("All Hotels:");
	        for (Hotel hotel : hotels) {
	            System.out.println(hotel);
	        }
	    }
}
