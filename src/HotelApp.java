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
        HotelAppView hotelAppView = new HotelAppView();
        Database database = new Database();
        Helper.clearScreen();
        printHRPSTitle();
        Helper.pressAnyKeyToContinue();
        hotelAppView.viewApp();
        Database.saveAllFiles();
        System.out.println("Program closing ... Thank you for using HRPS!");
    }
    /**
     * Prints the HRPS title.
     */
    private static void printHRPSTitle() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           __    __    _______     _______      ______                               ║");
        System.out.println("║                          /  |  /  |  /       \\   /       \\    /      \\                              ║");
        System.out.println("║                          ▐▐ |  ▐▐ |  ▐▐▐▐▐▐▐  |  ▐▐▐▐▐▐▐  |  /▐▐▐▐▐▐  |                             ║");
        System.out.println("║                          ▐▐ |__▐▐ |  ▐▐ |__▐▐ |  ▐▐ |__▐▐ |  ▐▐ \\__▐▐/                              ║");
        System.out.println("║                          ▐▐    ▐▐ |  ▐▐    ▐▐<   ▐▐    ▐▐/   ▐▐      \\                              ║");
        System.out.println("║                          ▐▐▐▐▐▐▐▐ |  ▐▐▐▐▐▐▐  |  ▐▐▐▐▐▐▐/     ▐▐▐▐▐▐  |                             ║");
        System.out.println("║                          ▐▐ |  ▐▐ |  ▐▐ |  ▐▐ |  ▐▐ |        /  \\__▐▐ |                             ║");
        System.out.println("║                          ▐▐ |  ▐▐ |  ▐▐ |  ▐▐ |  ▐▐ |        ▐▐    ▐▐/                              ║");
        System.out.println("║                          ▐▐/   ▐▐/   ▐▐/   ▐▐/   ▐▐/          ▐▐▐▐▐▐/                               ║");
        System.out.println("║                                                                                                     ║");
        System.out.println("║                         Welcome to Hotel Reservation and Payment System                             ║");
        System.out.println("║                                                                                                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }
}
