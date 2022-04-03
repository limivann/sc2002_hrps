package src;

import src.database.Database;
import src.helper.Helper;
import src.view.*;
/**
 * The starting point of the application.
 * @author Lim Kang Wei, Ivan, Max, Hill, Kaichen
 * @version 1.0
 * @since 2020-03-29
 */
public class HotelApp {
    /**
     * Main function that is the starting point of the application.
     * @param args Arguments passed to the app
     */
    public static void main(String[] args) {
        // Intialize helpers and view apps
        AdminView adminView = new AdminView();
        UserView userView = new UserView();

        Database database = new Database();
        int opt = -1;
        do{
            printMainMenu();
            opt = Helper.readInt(1,3);
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
        Helper.clearScreen();
        System.out.println("Welcome to Hotel Reservation and Payment System");
        System.out.println("Enter user domain");
        System.out.println("(1) Admin View");
        System.out.println("(2) User View");
        System.out.println("(3) Exit Program");
    }
}
