package src.view;
import java.util.Scanner;
import src.helper.Helper;
import src.view.*;
public class HRPSApp {
    public static void main(String[] args) {
        // Intialize helpers and view apps
        Helper helper = new Helper();
        GuestView guestView = new GuestView();
        RoomView roomView = new RoomView();
        ReservationView reservationView = new ReservationView();
        RoomServiceView roomServiceView = new RoomServiceView();
        HandleCheckInOutView handleCheckInOutView = new HandleCheckInOutView();

        System.out.println("Welcome to Hotel Reservation and Payment System");
        int opt = -1;
        do{
            printMainMenu();
            opt = helper.sc.nextInt();
            switch (opt) {
                case 1:
                    guestView.viewapp();
                    break;
                case 2:
                    roomView.viewapp();
                    break;
                case 3:
                    reservationView.viewapp();
                    break;
                case 4:
                    roomServiceView.viewapp();
                    break;
                case 5:
                    handleCheckInOutView.viewapp();
                    break;
                case 6:
                    break;
                default:
                    // TODO: Throw Exception
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        } while (opt != 6);
        System.out.println("Program closing ... Thank you for using HRPS!");
    }
    
    public static void printMainMenu() {
        System.out.println("Please select an option 1-6");
        System.out.println("(1) Manage Guests");
        System.out.println("(2) Manage Rooms");
        System.out.println("(3) Manage Reservations");
        System.out.println("(4) Manage Room Service (Customize Menu/ Create New Order)");
        System.out.println("(5) Manage check in / check out");
        System.out.println("(6) Quit");
    }
}
