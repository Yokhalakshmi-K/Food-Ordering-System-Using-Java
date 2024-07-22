import java.util.Scanner;

import com.zoho.Foodordering.Controller.AdminController;
import com.zoho.Foodordering.Controller.HotelAdminController;
import com.zoho.Foodordering.Controller.UserController;

public class Main {

		 private static Scanner input = new Scanner(System.in);
		    private static AdminController adminController = new AdminController();
		    private static UserController userController = new UserController();
		    private static HotelAdminController hoteladmin = new HotelAdminController();
		    public static void main(String[] args) {
		        start();
		    }
		    public static void start(){
		        while(true){
		            displayMainmenu();
		            int choice =input.nextInt();
		            switch (choice){
		                case 1 :
		                    adminController.handleAdmin();
		                    break;
		                case 2:
		                	hoteladmin.handleHotelAdmin();
		                case 3 :
		                    userController.handleUser();
		                    break;

		                case 4 :
		                	System.out.println("Thank you for using this service");
		                    System.exit(0);
		                    break;
		                default:
		                    System.out.println("Invalid choice , Please try Again");
		            }
		        }
		    }
		    private static void displayMainmenu() {
		        System.out.println("Enter the choice ");
		        System.out.println("1. Admin");
		        System.out.println("2. Hotel Admin");
		        System.out.println("3. User");
		        System.out.println("4. Exit");

		    }

}
