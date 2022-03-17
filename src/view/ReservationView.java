package src.view;

import java.util.Scanner;

public class ReservationView extends MainView{
    @Override
    public void viewapp(){
        int opt = -1;
        Scanner sc = new Scanner(System.in);
        do{
            printMenu();
            opt = sc.nextInt();
            switch (opt){
                case 1:
                    //TODO: Create Reservation
                    break;
                case 2:
                    //TODO: Search Reservation
                    break;
                case 3:
                    //TODO: Update Reservation
                    break;
                case 4:
                    //TODO: Remove Reservation
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }while( opt != 5 );
    }

    @Override
    public void printMenu() {
        System.out.println("Please select an option (1-5):");
        System.out.println("1: Create Reservation");
        System.out.println("2: Search Reservation");
        System.out.println("3: Update Reservation");
        System.out.println("4: Remove Reservation");
        System.out.println("5: Exit");
    }
}
