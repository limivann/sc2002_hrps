package src.view;

import java.util.Scanner;

public class ReservationView extends MainView{
    @Override
    public void viewapp(){
        printMenu();
        Scanner sc = new Scanner(System.in);
        int opt = -1;
        System.out.println("Enter option:");
        opt = sc.nextInt();
        do{
            switch (opt){
                case 1:
                    //Create Reservation
                    break;
                case 2:
                    //Search Reservation
                    break;
                case 3:
                    // Update Reservation
                    break;
                case 4:
                    //Remove Reservation
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invaid option");
            }
            System.out.println("Enter opinion");
            opt = sc.nextInt();
        }while(opt!=5);
    }

    @Override
    public void printMenu(){
        System.out.println("1: Create Reservation");
        System.out.println("2: Search Reservation");
        System.out.println("3: Update Reservation");
        System.out.println("4: Remove Reservation");
        System.out.println("5: Exit");
    }
}
