package com.zoho.Foodordering.Controller;

import java.util.List;
import java.util.Scanner;

import com.zoho.Foodordering.Models.Food;
import com.zoho.Foodordering.Models.Hotel;
import com.zoho.Foodordering.Models.Menu;
import com.zoho.Foodordering.Models.User;
import com.zoho.Foodordering.dao.FoodDAO;
import com.zoho.Foodordering.dao.UserDAO;
import com.zoho.Foodordering.view.AdminView;
import com.zoho.Foodordering.dao.HotelDAO;
import com.zoho.Foodordering.dao.MenuDAO;

public class AdminController {
	private Scanner input = new Scanner(System.in);
	private User loggedInAdmin;
	private UserDAO userDao = new UserDAO();
	private FoodDAO foodDao = new FoodDAO();
	private HotelDAO hotelDao = new HotelDAO();
	private MenuDAO menuDao = new MenuDAO();
	private AdminView adminView = new AdminView();
	
	public AdminController() {
		
	}
	public void handleAdmin() {
		while(true) {
			if(loggedInAdmin == null) {
				showLoginMenu();
			}else {
				showAdminMenu();
			}
		}
	}
	private void showLoginMenu() {
		System.out.println("1.Login");
		System.out.println("2.Exit");
		int choice = input.nextInt();
		input.nextLine();
		switch(choice) {
			case 1:
				login();
				break;
			case 2:
				System.out.println("Thank you for using this service");
				System.exit(0);
				break;
		}
	}
	private void showAdminMenu() {
		System.out.println("1.Add Admin");
		System.out.println("2.Add Hotel Admin");
		System.out.println("3.Add Food");
		System.out.println("4.Remove Hotel");
		System.out.println("5.Remove Food");
		System.out.println("6.Map hotel and food");
		System.out.println("7.View All Hotels");
		System.out.println("8.View Hotel by location");
		System.out.println("9.Logout");
		int choice = input.nextInt();
		input.nextLine();
		switch(choice) {
			case 1:
				addAdmin();
				break;
			case 2:
				addHotelAdmin();
				break;
			case 3:
				addFood();
				break;
			case 4:
				removeHotel();
				break;
			case 5:
				removeFood();
				break;
			case 6:
				mapHotelAndFood();
				break;
			case 7:
				viewAllHotels();
				break;
			case 8:
				getHotelByLocation();
				break;
			case 9:
				logout();
				break;
			default:
				System.out.println("Invalid choice");
					
		}
	}
	private void login() {
		System.out.println("Enter the username");
		String username = input.nextLine();
		System.out.println("Enter the password");
		String password = input.nextLine();

        User user = userDao.loginUser(username, password);
        if (user != null && user.getRole().equals(User.Role.ADMIN)) {
            loggedInAdmin = user;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username, password, or role.");
        }
	}
	 private void addAdmin() {
	        System.out.println("Enter name");
	        String name = input.nextLine();
	        System.out.print("Enter username: ");
	        String username = input.nextLine();
	        System.out.print("Enter password: ");
	        String password = input.nextLine();
	        System.out.print("Enter mobile number: ");
	        String contact = input.nextLine();
	        System.out.print("Enter email ID: ");
	        String email = input.nextLine();

	        User user = new User(name, username, password, contact, email, User.Role.ADMIN);
	        if (userDao.addUser(user)) {
	            System.out.println("Admin added successfully.");
	        } else {
	            System.out.println("Failed to add admin.");
	        }
	    }
	   void addHotelAdmin() {
	        System.out.println("Enter name");
	        String name = input.nextLine();
	        System.out.print("Enter username: ");
	        String username = input.nextLine();
	        System.out.print("Enter password: ");
	        String password = input.nextLine();
	        System.out.print("Enter mobile number: ");
	        String contact = input.nextLine();
	        System.out.print("Enter email ID: ");
	        String email = input.nextLine();

	        User user = new User(name, username, password, contact, email, User.Role.HOTEL_ADMIN);
	        if (userDao.addUser(user)) {
	            System.out.println("Hotel Admin added successfully.");
	        } else {
	            System.out.println("Failed to add hotel admin.");
	        }
	    }
	   void addHotel() {
		  System.out.println("Enter the hotel name");
		  String hotelName = input.nextLine();
		  System.out.println("Enter the location");
		  String location = input.nextLine();
	      System.out.print("Enter hotel type (veg,nonveg,vegandnonveg): ");
	      Hotel.hotelType type = Hotel.hotelType.valueOf(input.nextLine().toLowerCase());
	      System.out.println("Enter the admin id:");
	      int adminId = input.nextInt();
	      Hotel hotel = new Hotel(hotelName,location,adminId,type);
	      if (hotelDao.addHotel(hotel)) {
	            System.out.println("Hotel added successfully.");
	        } else {
	            System.out.println("Failed to add Hotel.");
	        }
	  }
	   void addFood() {
		  System.out.println("Enter the food name");
		  String foodName = input.nextLine();
		  System.out.println("Enter the food description");
		  String description = input.nextLine();
		  Food food = new Food(foodName,description);
		  if(foodDao.addFood(food)) {
			  System.out.println("Food added successfully.");
		  }else {
			  System.out.println("Failed to add food.");
		  }
	  }
	   void removeHotel() {
		  System.out.println("Enter the hotel id to be deleted");
		  int hotelId = input.nextInt();
		  if(hotelDao.removeHotel(hotelId)) {
			  System.out.println("Hotel deleted");
		  }else {
			  System.out.println("Failed to deleted");
		  }
	  }
	  void removeFood() {
		  System.out.println("Enter the food id to be deleted");
		  int foodId = input.nextInt();
		  if(foodDao.removeFood(foodId)) {
			  System.out.println("Food deleted");
		  }else {
			  System.out.println("Failed to delete");
		  }
	  }
	   void mapHotelAndFood() {
		  System.out.println("Enter the hotel id");
		  int hotelId = input.nextInt();
		  System.out.println("Enter the food id");
		  int foodId = input.nextInt();
		  System.out.println("Enter the price of the food");
		  double price = input.nextDouble();
		  Menu menu = new Menu(hotelId,foodId,price);
		  if(menuDao.mapHotelAndFood(menu)) {
			  System.out.println("Hotel and foods are mapped successfully");
		  }else {
			  System.out.println("Unable to map");
		  }
	  }
	  private void viewAllHotels() {
		   List<Hotel> hotels = hotelDao.getAllHotel();
	        adminView.displayHotels(hotels);
	  }
	  private void getHotelByLocation() {
		  System.out.println("Enter the location");
		  String location = input.nextLine();
		  System.out.println(hotelDao.getHotelByLocation(location));;
	  }
	  private void logout() {
	        loggedInAdmin = null;
	        System.out.println("Logged out successfully.");
	    }
}
