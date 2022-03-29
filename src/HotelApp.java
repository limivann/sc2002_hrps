package src;

import src.database.Database;
import src.helper.Helper;
import src.view.*;
/**
 * The starting point of the application.
 * @author Lim Kang Wei
 * @version 1.0
 * @since 2020-03-29
 */
public class HotelApp {
    public static void main(String[] args) {
        // Intialize helpers and view apps
        AdminView adminView = new AdminView();
        UserView userView = new UserView();

        // TODO: Initialize Database
        Database database = new Database();
        // Initialize room

        System.out.println("Welcome to Hotel Reservation and Payment System");
        int opt = -1;
        do{
            printMainMenu();
            opt = Helper.readInt();
            switch (opt) {
                case 1:
                    adminView.viewapp();
                    break;
                case 2:
                    userView.viewapp();
                    break;
                case 3:
                    break;
                default:
                    // TODO: Throw Exception
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        } while (opt != 3);
        Database.saveAllFiles();
        System.out.println("Program closing ... Thank you for using HRPS!");
    }
    /**
     * Menu for views
     */
    public static void printMainMenu() {
        System.out.println("Please select a view option (1-3)");
        System.out.println("(1) Admin View");
        System.out.println("(2) User View");
        System.out.println("(3) Exit Program");
    }
}
