package com.zoho.Foodordering.Controller;

import java.util.Scanner;

import com.zoho.Foodordering.Models.User;
import com.zoho.Foodordering.dao.FoodDAO;
import com.zoho.Foodordering.dao.HotelDAO;
import com.zoho.Foodordering.dao.MenuDAO;
import com.zoho.Foodordering.dao.UserDAO;


public class HotelAdminController {
	private Scanner input = new Scanner(System.in);
	private User loggedInHotelAdmin;
	
	private UserDAO userDao = new UserDAO();
	private FoodDAO foodDao = new FoodDAO();
	private HotelDAO hotelDao = new HotelDAO();
	private MenuDAO menuDao = new MenuDAO();
	private AdminController admin = new AdminController();
	
	public HotelAdminController() {
			
		}
		public void handleHotelAdmin() {
			while(true) {
				if(loggedInHotelAdmin == null) {
					showLoginMenu();
				}else {
					showHotelAdminMenu(loggedInHotelAdmin);
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
		private void login() {
			System.out.println("Enter the username");
			String username = input.nextLine();
			System.out.println("Enter the password");
			String password = input.nextLine();

	        User user = userDao.loginUser(username, password);
	        if (user != null && user.getRole().equals(User.Role.HOTEL_ADMIN)) {
	        	loggedInHotelAdmin = user;
	            System.out.println("Login successful.");
	        } else {
	            System.out.println("Invalid username, password, or role.");
	        }
		}
		private void showHotelAdminMenu(User user) {
			System.out.println("1.Add Hotel Admin");
			System.out.println("2.Add Hotel");
			System.out.println("3.Add Food");
			System.out.println("4.Remove Hotel");
			System.out.println("5.Remove Food");
			System.out.println("6.Map hotel and food");
			System.out.println("7.Change Password");
			System.out.println("8.Logout");
			int choice = input.nextInt();
			input.nextLine();
			switch(choice) {
				case 1:
					admin.addHotelAdmin();
					break;
				case 2:
					admin.addHotel();
					break;
				case 3:
					admin.addFood();
					break;
				case 4:
					admin.removeHotel();
					break;
				case 5:
					admin.removeFood();
					break;
				case 6:
					admin.mapHotelAndFood();
					break;
				case 7:
					changePassword(user);
					break;
				case 8:
					logout();
					break;
				default:
					System.out.println("Invalid choice");
						
			}
		}
		public void changePassword(User user) {
			System.out.print("Enter new password: ");
            String newPassword = input.nextLine();
            if(newPassword == null || newPassword.isEmpty()) {
            	System.out.println("Password cannot be empty");
            	return;
            }
            userDao.changePassword(user.getContact(), newPassword);
            System.out.println("Password changed!!");
		}
		private void logout() {
			// TODO Auto-generated method stub
			loggedInHotelAdmin = null;
		        System.out.println("Logged out successfully.");
		}
}
