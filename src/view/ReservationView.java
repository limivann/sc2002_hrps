package src.view;

import src.controller.ReservationManager;
import src.helper.Helper;
public class ReservationView extends MainView{
    @Override
    public void viewapp(){
        int opt = -1;
        String reservationId;
        ReservationManager manager = new ReservationManager();
        printMenu();
        System.out.println("Enter option:");
        opt = Helper.sc.nextInt();
        do{
            switch (opt){
                case 1:
                    String checkedInDate;
                    String checkedOutDate;
                    String guestId;
                    String roomId;
                    int numberOfPax;
                    System.out.println("Enter Room Id");
                    roomId = Helper.sc.nextLine();
                    //validate roomId
                    System.out.println("Enter Guest Id");
                    guestId = Helper.sc.nextLine();
                    //validate guestId
                    System.out.println("Enter Check In Date");
                    checkedInDate = Helper.sc.nextLine();
                    //validate date
                    System.out.println("Enter Check Out Date");
                    checkedOutDate = Helper.sc.nextLine();
                    //validate date
                    System.out.println("Enter number of pax");
                    numberOfPax = Helper.sc.nextInt();
                    manager.create(checkedInDate, checkedOutDate, guestId, roomId, numberOfPax);
                    break;
                case 2:
                    System.out.println("Enter Reservation Id");
                    reservationId = Helper.sc.nextLine();
                    manager.print(reservationId);
                    break;
                case 3:
                    System.out.println("Enter Reservation Id");
                    reservationId = Helper.sc.nextLine();
                    updateReservation(reservationId);
                    break;
                case 4:
                    System.out.println("Enter Reservation Id");
                    reservationId = Helper.sc.nextLine();
                    manager.remove(reservationId);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println("Enter option:");
            opt = Helper.sc.nextInt();
        }while( opt != 5);
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

    public void updateReservation(String reservationId){
        ReservationManager manager = new ReservationManager();
        if(manager.validate(reservationId)){
            int opt = -1;
            String Date;
            String guestId;
            String roomId;
            int numberOfPax;
            int isExpired;
            int reservationStatus;   
            System.out.println("1: checkedInDate");
            System.out.println("2: checkedOutDate");
            System.out.println("3: guestId");
            System.out.println("4: roomId");
            System.out.println("5: numberOfPax");
            System.out.println("6: isExpired");
            System.out.println("7: reservationStatus");
            System.out.println("8: Exit");
            do{
                opt = Helper.sc.nextInt();
                switch(opt){
                    case 1:
                        System.out.println("Enter Date: (yyyy-MM-DD HH-mm");
                        Helper.sc.nextLine();
                        Date = Helper.sc.nextLine();
                        manager.updateCheckedInDate(reservationId, Date);
                        break;
                    case 2:
                        System.out.println("Enter Date: (yyyy-MM-DD HH-mm");
                        Helper.sc.nextLine();
                        Date = Helper.sc.nextLine();
                        manager.updateCheckedOutDate(reservationId, Date);
                        break;
                    case 3:
                        System.out.println("Enter guest Id");
                        Helper.sc.nextLine();
                        guestId = Helper.sc.nextLine();
                        //validate guestId
                        manager.updateGuestId(reservationId, guestId);
                        break;
                    case 4:
                        System.out.println("Enter room Id");
                        Helper.sc.nextLine();
                        roomId = Helper.sc.nextLine();
                        //validate roomId
                        manager.updateRoomId(reservationId, roomId);
                        break;
                    case 5:
                        System.out.println("Enter number of pax");
                        numberOfPax = Helper.sc.nextInt();
                        manager.updateNumberOfPax(reservationId, numberOfPax);
                        break;
                    case 6:
                        System.out.println("1: Expired\n2: Not Expired\n3: Return");
                        isExpired = Helper.sc.nextInt();
                        if(isExpired==1){
                            manager.updateIsExpired(reservationId, true);
                        }
                        else if(isExpired==2) {
                            manager.updateIsExpired(reservationId, false);
                        }
                        else if(isExpired==3){
                            return;
                        }
                        else System.out.println("Invalid option. Please try again");
                        break;
                    case 7:
                        System.out.println("Enter reservation status");
                        System.out.println("1: CONFIRMED");
                        System.out.println("2: IN_WAITLIST");
                        System.out.println("3: CHECKED_IN");
                        System.out.println("4: EXPIRED");
                        System.out.println("5: CHECKED_OUT");
                        System.out.println("6: CANCELLED");
                        System.out.println("7: Return");
                        reservationStatus = Helper.sc.nextInt();
                        if(reservationStatus==7) return;
                        manager.updateReservationStatus(reservationId, reservationStatus);
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println("Invalid option. Please try again");
                }
            } while (opt != 8);
        }
        else return;        
    }
}
