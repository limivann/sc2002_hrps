package src.view;

import java.util.Scanner;

public class HandleCheckInOutView extends MainView {

    @Override
    public void printMenu() {
        System.out.println("===================");
        System.out.println("1. Check In");
        System.out.println("2. Check Out");
    }

    @Override
    public void viewapp() {
        int opt = -1;
        Scanner sc = new Scanner(System.in);
        printMenu();
        opt = sc.nextInt();
        switch (opt) {
            case 1:
                checkin();
                break;
            case 2:
                checkout();
                break;
            default:
                break;
        }
    }
    
    public void checkin() {
        // handle check in
        System.out.println("Check in complete");
    }

    public void checkout() {
        // handle check out
        System.out.println("Check out complete");
    }  
}
