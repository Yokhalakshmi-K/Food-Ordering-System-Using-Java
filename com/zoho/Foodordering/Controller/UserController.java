package com.zoho.Foodordering.Controller;

import java.util.List;
import java.util.Scanner;

import com.zoho.Foodordering.Models.Hotel;
import com.zoho.Foodordering.Models.User;
import com.zoho.Foodordering.dao.FoodDAO;
import com.zoho.Foodordering.dao.HotelDAO;
import com.zoho.Foodordering.dao.MenuDAO;
import com.zoho.Foodordering.dao.OrderDAO;
import com.zoho.Foodordering.dao.UserDAO;
import com.zoho.Foodordering.view.UserView;

public class UserController {
	private Scanner input = new Scanner(System.in);
	private User loggedInUser;
	private UserDAO userDao = new UserDAO();
	private FoodDAO foodDao = new FoodDAO();
	private HotelDAO hotelDao = new HotelDAO();
	private MenuDAO menuDao = new MenuDAO();
	private OrderDAO orderDao = new OrderDAO();
	private UserView userView = new UserView();
	private HotelAdminController hotel = new HotelAdminController();
	public UserController() {
		
	}
	public void handleUser() {
		while(true) {
			if(loggedInUser == null) {
				showLoginMenu();
			}else {
				showUserMenu(loggedInUser);
			}
		}
	}

    private void showLoginMenu() {
        System.out.println("1. Signup");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 3:
            	System.out.println("Thank you for using this service");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    private void showUserMenu(User user) {
        System.out.println("1. View Hotels by location");
        System.out.println("2. View Hotels by Food");
        System.out.println("3. Order Food");
        System.out.println("4. Cancel Food");
        System.out.println("5. View Orders");
        System.out.println("6. View All hotels");
        System.out.println("7. My Profile");
        System.out.println("8. Change Password");
        System.out.println("9. Logout");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
            	getHotelByLocation();
                break;
            case 2:
            	getHotelByFood();
                break;
            case 3:
                placeOrder();
                break;
            case 4:
                cancelOrder();
                break;
            case 5:
            	viewOrders();
            	break;
            case 6:
            	viewAllHotels();
            	break;
            case 7:
            	myProfile();
            	break;
            case 8:
            	hotel.changePassword(user);
            	break;
            case 9:
            	logout();
            	break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    public void register() {
        System.out.println("Enter name");
        String name = input.nextLine();
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        System.out.print("Enter mobile number: ");
        String contact = input.nextLine();
        System.out.print("Enter email ID: ");
        String email = input.nextLine().trim().toLowerCase();

        User user = new User(name, username, password, contact, email, User.Role.CUSTOMER);
        if (userDao.addUser(user)) {
            System.out.println("Register successfully.");
        } else {
            System.out.println("Try again!Failed to register.");
        }
    }
    private void login() {
		System.out.println("Enter the username");
		String username = input.nextLine();
		System.out.println("Enter the password");
		String password = input.nextLine();

        User user = userDao.loginUser(username, password);
        if (user != null && user.getRole().equals(User.Role.CUSTOMER)) {
            loggedInUser = user;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username, password, or role.");
        }
	}
    private void getHotelByLocation() {
   		  System.out.println("Enter the location");
   		  String location = input.nextLine();
   		  System.out.println(hotelDao.getHotelByLocation(location));
   	  
    }
    private void getHotelByFood() {
    	System.out.println("Enter the food name");
    	String foodName = input.nextLine();
    	hotelDao.getHotelByFood(foodName);
    }
    private void placeOrder() {
    	System.out.println("Enter the Username");
    	String username = input.nextLine();
    	System.out.println("Enter the Hotel Name");
    	String hotelName = input.nextLine();
    	System.out.println("Enter the Food name");
    	String foodName = input.nextLine();
    	System.out.println("Enter the Quantity");
    	int quantity = input.nextInt();
    	orderDao.placeOrderByNames(username, hotelName, foodName, quantity);
    }
   private void cancelOrder() {
	   System.out.println("Enter the Mobile number");
	   String contact = input.nextLine();
	   orderDao.cancelOrderByContact(contact);
   }
   private void viewOrders() {
	   System.out.println("Enter the Mobile number");
	   String contact = input.nextLine();
	   orderDao.viewOrders(contact);
   }
   private void viewAllHotels() {
	   List<Hotel> hotels = hotelDao.getAllHotel();
        userView.displayHotels(hotels);
  }
   private void myProfile() {
	   System.out.println("Enter the username");
		String username = input.nextLine();
		System.out.println("Enter the password");
		String password = input.nextLine();
		userDao.getUser(username, password);
   }
   private void logout() {
       loggedInUser = null;
       System.out.println("Logged out successfully.");
   }
}
