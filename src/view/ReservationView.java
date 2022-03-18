package src.view;

import java.util.Scanner;

public class ReservationView extends MainView{
    @Override
    public void viewapp(){
        printMenu();
        Scanner sc = new Scanner(System.in);
        int opt = -1;
        int reservationId;
        System.out.println("Enter option:");
        opt = sc.nextInt();
        do{
            switch (opt){
                case 1:
                    String checkedInDate;
                    String checkedOutDate;
                    String reservationDate;
                    int guestId;
                    int roomId;
                    int numberOfPax;
                    System.out.println("Enter Room Id");
                    roomId = sc.nextInt();
                    //validate roomId
                    System.out.println("Enter Guest Id");
                    guestId = sc.nextInt();
                    //validate geustId
                    System.out.println("Enter Check In Date");
                    sc.nextLine();
                    checkedInDate = sc.nextLine();
                    //validate date
                    System.out.println("Enter Check Out Date");
                    checkedOutDate = sc.nextLine();
                    //validate date
                    System.out.println("Enter number of pax");
                    numberOfPax = sc.nextInt();
                    //Create Reservation
                    break;
                case 2:
                    System.out.println("Enter Reservation Id");
                    reservationId = sc.nextInt();
                    //Search Reservation
                    break;
                case 3:
                    System.out.println("Enter Reservation Id");
                    reservationId = sc.nextInt();
                    // Update Reservation
                    break;
                case 4:
                    System.out.println("Enter Reservation Id");
                    reservationId = sc.nextInt();
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
