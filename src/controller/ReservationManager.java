package src.controller;
import java.util.Scanner;

import src.model.Reservation;

public class ReservationManager {
    Scanner sc = new Scanner(System.in);

    public void createReservation(String checkedInDate, String checkedOutDate, int guestId, int roomId, int numberOfPax){
        //Reservation reservation = new Reservation(checkedInDate, checkedOutDate, guestId, roomId, numberOfPax);
        //store reservation in HashMap
    }
    public void updateReservation(){
        //get Reservation
        int opt = -1;
        String Date;
        int guestId;
        int roomId;
        int numberOfPax;
        int isExpired;
        ReservationStatus reservationStatus;   
        System.out.println("1: checkedInDate");
        System.out.println("2: checkedOutDate");
        System.out.println("3: guestId");
        System.out.println("4: roomId");
        System.out.println("5: numberOfPax");
        System.out.println("6: isExpired");
        System.out.println("7: reservationStatus");
        System.out.println("8: Exit");
        do{
            opt = sc.nextInt();
            switch(opt){
                case 1:
                    System.out.println("Enter Date: (yyyy-MM-DD HH-mm");
                    sc.nextLine();
                    Date = sc.nextLine();
                    //set reservation check in date
                    break;
                case 2:
                    System.out.println("Enter Date: (yyyy-MM-DD HH-mm");
                    sc.nextLine();
                    Date = sc.nextLine();
                    //set reservation check out date
                    break;
                case 3:
                    System.out.println("Enter guest Id");
                    guestId = sc.nextInt();
                    //set guestId
                    break;
                case 4:
                    System.out.println("Enter room Id");
                    roomId = sc.nextInt();
                    break;
                case 5:
                    System.out.println("Enter number of pax");
                    numberOfPax = sc.nextInt();
                    //set number of pax
                    break;
                case 6:
                    System.out.println("1: Expired\n 2:Not Expired");
                    isExpired = sc.nextInt();
                    if(isExpired==1){
                        //set expired
                    }
                    else if(isExpired==2) {
                        //set not expired
                    }
                    else System.out.println("Invalid option");
                    break;
                case 7:
                    System.out.println("Enter reservation status");
            }
        }
    }
}

  