package src.view;

import src.controller.ReservationManager;
import src.helper.Helper;
import src.controller.RoomManager;

import javax.xml.crypto.Data;

import src.controller.GuestManager;

public class ReservationView extends MainView {
    @Override
    public void printMenu() {
        System.out.println("Please select an option (1-6):");
        System.out.println("(1) Create Reservation");
        System.out.println("(2) Search Reservation");
        System.out.println("(3) Update Reservation");
        System.out.println("(4) Remove Reservation");
        System.out.println("(5) Print All Reservations");
        System.out.println("(6) Exit");
    }

    @Override
    public void viewapp(){
        int opt = -1;
        String reservationId;
        do {
            printMenu();
            System.out.println("Enter option:");
            opt = Helper.readInt();
            switch (opt){
                case 1:
                    if (createReservation()) {
                        System.out.println("Create reservation successful");
                    } else {
                        System.out.println("Create reservation unsuccessful");
                    }
                    break;
                case 2:
                    System.out.println("Enter Reservation Id");
                    reservationId = Helper.sc.nextLine();
                    ReservationManager.print(reservationId);
                    break;
                case 3:
                    System.out.println("Enter Reservation Id");
                    reservationId = Helper.sc.nextLine();
                    updateReservation(reservationId);
                    break;
                case 4:
                    System.out.println("Enter Reservation Id");
                    reservationId = Helper.sc.nextLine();
                    if (ReservationManager.remove(reservationId)) {
                        System.out.println("Remove reservation successful");
                    } else {
                        System.out.println("Remove reservation unsuccessful");
                    }
                    break;
                case 5:
                    ReservationManager.printAllReservations();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }while( opt != 6);
    }

    public boolean createReservation() {
        System.out.println("--- Create Reservation ---");
        System.out.println("Please enter an option (1-3)");
        System.out.println("(1) Walk-In");
        System.out.println("(2) Reserve");
        System.out.println("(3) Exit");
        int choice = Helper.readInt();
        if (choice == 3) {
            System.out.println("Exited");
            return false;
        }
        if (choice != 1 && choice != 2) {
            System.out.println("Invalid option");
            return false;
        }

        String checkedInDate;
        String checkedOutDate;
        String guestId;
        String roomId;
        int numberOfPax;
        System.out.println("Enter Room Id (eg: 01-05)");
        roomId = Helper.sc.nextLine();
        if (!RoomManager.checkRoomVacancy(roomId)) {
            System.out.println("Room is not Vacant! Please try again");
            return false;
        }
        RoomManager.printRoom(roomId);
        System.out.println("Enter Guest Id (GXXXX)");
        guestId = Helper.sc.nextLine();
        if (!GuestManager.validateGuestId(guestId)) {
            System.out.println("Guest id not found. Please try again");
            return false;
        }

        System.out.println("Enter number of pax");
        numberOfPax = Helper.readInt();
        if (!RoomManager.validateNumOfPax(roomId, numberOfPax)) {
            System.out.println("Number of Pax exceeds room capacity. Please try again");
            return false;
        }

        if (choice == 2) {
            System.out.println("Enter Check In Date");
            checkedInDate = Helper.setDate(false);
            System.out.println("Enter Check Out Date");
            checkedOutDate = Helper.setDate(false);
            if (checkedInDate.equals("") || checkedOutDate.equals("")) {
                return false;
            }
            ReservationManager.create(checkedInDate, checkedOutDate, guestId, roomId, numberOfPax);
        } else {
            System.out.println("Enter Check Out Date");
            checkedOutDate = Helper.setDate(false);
            checkedInDate = Helper.setDate(true);
            if (checkedInDate.equals("") || checkedOutDate.equals("")) {
                return false;
            }
            ReservationManager.create(checkedInDate, checkedOutDate, guestId, roomId, numberOfPax);
        }

        return true;
    }

    public void printReservationStatusMenu() {
        System.out.println("Enter new reservation status (1-6)");
        System.out.println("(1) Confirmed");
        System.out.println("(2) In Waitlist");
        System.out.println("(3) Checked In");
        System.out.println("(4) Expired");
        System.out.println("(5) Checked Out");
        System.out.println("(6) Cancelled");
    }

    public void printUpdateReservationMenu() {
        System.out.println("--- Update Reservation ---");
        System.out.println("Please enter an option(1-8)");
        System.out.println("(1) Update Checked In Date");
        System.out.println("(2) Update Checked Out Date");
        System.out.println("(3) Update Guest Id");
        System.out.println("(4) Update Room Id");
        System.out.println("(5) Update Number Of Pax");
        System.out.println("(6) Update Is Expired");
        System.out.println("(7) Update Reservation Status");
        System.out.println("(8) Exit");
    }

    public void printExpiredMenu() {
        System.out.println("Please select an option (1-3");
        System.out.println("(1) Expired");
        System.out.println("(2) Not Expired");
        System.out.println("(3) Back");
    }
    
    public void updateReservation(String reservationId){
        if (ReservationManager.validateReservationId(reservationId)) {
            int opt = -1;
            String Date;
            String guestId;
            String roomId;
            int numberOfPax;
            int isExpired;
            int reservationStatus;
            do {
                printUpdateReservationMenu();
                opt = Helper.readInt();
                switch (opt) {
                    case 1:
                        Date = Helper.setDate(false);
                        if (Date == null) {
                            break;
                        }
                        ReservationManager.updateCheckedInDate(reservationId, Date);
                        break;
                    case 2:
                        Date = Helper.setDate(false);
                        if (Date == null) {
                            break;
                        }
                        ReservationManager.updateCheckedOutDate(reservationId, Date);
                        break;
                    case 3:
                        System.out.println("Enter guest Id");
                        guestId = Helper.sc.nextLine();
                        if (!GuestManager.validateGuestId(guestId)) {
                            System.out.println("Guest id not found. Please try again");
                            break;
                        }
                        ReservationManager.updateGuestId(reservationId, guestId);
                        break;
                    case 4:
                        System.out.println("Enter room Id");
                        roomId = Helper.sc.nextLine();
                        if (!RoomManager.checkRoomVacancy(roomId)) {
                            System.out.println("Room is not Vacant! Please try again");
                            break;
                        }
                        ReservationManager.updateRoomId(reservationId, roomId);
                        break;
                    case 5:
                        System.out.println("Enter number of pax");
                        numberOfPax = Helper.readInt();
                        if (!RoomManager.validateNumOfPax(ReservationManager.getRoomIdFromReservationId(reservationId), numberOfPax)) {
                            System.out.println("Number of Pax exceeds room capacity. Please try again");
                            break;
                        }
                        ReservationManager.updateNumberOfPax(reservationId, numberOfPax);
                        break;
                    case 6:
                        printExpiredMenu();
                        isExpired = Helper.readInt();
                        switch (isExpired) {
                            case 1:
                                ReservationManager.updateIsExpired(reservationId, true);
                                break;
                            case 2:
                                ReservationManager.updateIsExpired(reservationId, false);
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Invalid option. Please try again");
                                break;
                        }
                    case 7:
                        printReservationStatusMenu();
                        reservationStatus = Helper.readInt();
                        if (reservationStatus >= 1 && reservationStatus <= 6) {
                            ReservationManager.updateReservationStatus(reservationId, reservationStatus);
                        }
                        System.out.println("Invalid option. Please try again");
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println("Invalid option. Please try again");
                        break;
                }
            } while (opt != 8);
        } else {
            // Do nothing
        }
    }
}
