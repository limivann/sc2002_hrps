package src.view;

import src.controller.ReservationManager;
import src.helper.Helper;
import src.model.enums.ReservationStatus;
import src.controller.RoomManager;

import javax.xml.crypto.Data;

import src.controller.GuestManager;

public class ReservationView extends MainView {
    /**
     * View Menu for the Reservation
     */
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
    /**
     * View Application for the Reservation
     * @see ReservationManager ReservationManager - Details of Reservation Manager
     */
    @Override
    public void viewapp(){
        int opt = -1;
        String reservationId;
        do {
            printMenu();
            opt = Helper.readInt(1,6);
            switch (opt){
                case 1:
                    if (createReservation()) {
                        System.out.println("Create reservation successful");
                    } else {
                        System.out.println("Create reservation unsuccessful");
                    }
                    break;
                case 2:
                    System.out.println("Enter Reservation Id (RXXXX)");
                    reservationId = Helper.sc.nextLine();
                    ReservationManager.printReservationDetails(reservationId);
                    break;
                case 3:
                    System.out.println("Enter Reservation Id (RXXXX)");
                    reservationId = Helper.sc.nextLine();
                    updateReservation(reservationId);
                    break;
                case 4:
                    System.out.println("Enter Reservation Id (RXXXX)");
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
    /**
     * View Menu for creating reservation
     * @see ReservationManager ReservationManager - Details of Reservation Manager
     * @return true if reservation is created successfully. Otherwise, false
     */
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
        boolean inWaitlist = false;
        System.out.println("Enter Room Id (eg: 01-05)");
        roomId = Helper.sc.nextLine();
        if (!RoomManager.checkRoomVacancy(roomId)) {
            System.out.println("Room is not Vacant! Guest will be moved to waitlist");
            inWaitlist = true;
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
            if (checkedInDate.equals("")){
                return false;
            }
            System.out.println("Enter Check Out Date");
            checkedOutDate = Helper.setDate(false);
            if (checkedOutDate.equals("")){
                return false;
            }
            if (inWaitlist) {
                ReservationManager.create(checkedInDate, checkedOutDate, guestId, roomId, numberOfPax, ReservationStatus.IN_WAITLIST);
            } else {
                ReservationManager.create(checkedInDate, checkedOutDate, guestId, roomId, numberOfPax, ReservationStatus.CONFIRMED);
            }
        } else {
            System.out.println("Enter Check Out Date");
            checkedOutDate = Helper.setDate(false);
            checkedInDate = Helper.setDate(true);
            if (checkedInDate.equals("") || checkedOutDate.equals("")) {
                return false;
            }
            ReservationManager.create(checkedInDate, checkedOutDate, guestId, roomId, numberOfPax, ReservationStatus.CHECKED_IN);
        }

        return true;
    }
    /**
     * View Menu for Reservation Status
     */
    public void printReservationStatusMenu() {
        System.out.println("Enter new reservation status (1-6)");
        System.out.println("(1) Confirmed");
        System.out.println("(2) In Waitlist");
        System.out.println("(3) Checked In");
        System.out.println("(4) Expired");
        System.out.println("(5) Checked Out");
        System.out.println("(6) Cancelled");
    }
    /**
     * View Menu for Reservation Update
     */
    public void printUpdateReservationMenu() {
        System.out.println("--- Update Reservation ---");
        System.out.println("Please enter an option (1-8)");
        System.out.println("(1) Update Checked In Date");
        System.out.println("(2) Update Checked Out Date");
        System.out.println("(3) Update Guest Id");
        System.out.println("(4) Update Room Id");
        System.out.println("(5) Update Number Of Pax");
        System.out.println("(6) Update Is Expired");
        System.out.println("(7) Update Reservation Status");
        System.out.println("(8) Exit");
    }
    /**
     * View Menu for Expire Status of Reservation
     */
    public void printExpiredMenu() {
        System.out.println("Please select an option (1-3");
        System.out.println("(1) Expired");
        System.out.println("(2) Not Expired");
        System.out.println("(3) Back");
    }
    /**
     * View Application for Reservation Update
     * @param reservationId Id of the reservation
     * @see ReservationManager ReservationManager - Details of Reservation Manager
     * @see ReservationStatus ReservationStatus - Different status of reservation
     */
    public void updateReservation(String reservationId){
        if (ReservationManager.validateReservationId(reservationId)) {
            int opt = -1;
            String Date;
            String guestId;
            String roomId;
            int numberOfPax;
            int isExpired;
            int reservationStatus;
            boolean isUpdateSuccessful = false;
            do {
                printUpdateReservationMenu();
                opt = Helper.readInt();
                switch (opt) {
                    case 1:
                        Date = Helper.setDate(false);
                        if (Date == null) {
                            isUpdateSuccessful = false;
                            break;
                        }
                        ReservationManager.updateCheckedInDate(reservationId, Date);
                        isUpdateSuccessful = true;
                        break;
                    case 2:
                        Date = Helper.setDate(false);
                        if (Date == null) {
                            isUpdateSuccessful = false;
                            break;
                        }
                        ReservationManager.updateCheckedOutDate(reservationId, Date);
                        isUpdateSuccessful = true;
                        break;
                    case 3:
                        System.out.println("Enter guest Id");
                        guestId = Helper.sc.nextLine();
                        if (!GuestManager.validateGuestId(guestId)) {
                            System.out.println("Guest id not found. Please try again");
                            isUpdateSuccessful = false;
                            break;
                        }
                        ReservationManager.updateGuestId(reservationId, guestId);
                        isUpdateSuccessful = true;
                        break;
                    case 4:
                        System.out.println("Enter room Id");
                        roomId = Helper.sc.nextLine();
                        if (!RoomManager.checkRoomVacancy(roomId)) {
                            System.out.println("Room is not Vacant! Guest will be move to waitlist");
                            ReservationManager.updateRoomId(reservationId, roomId, ReservationStatus.IN_WAITLIST);
                            isUpdateSuccessful = true;
                            break;
                        } else {
                            ReservationManager.updateRoomId(reservationId, roomId, ReservationStatus.CONFIRMED);
                            isUpdateSuccessful = true;
                        }
                        break;
                    case 5:
                        System.out.println("Enter number of pax");
                        numberOfPax = Helper.readInt();
                        if (!RoomManager.validateNumOfPax(ReservationManager.getRoomIdFromReservationId(reservationId),
                                numberOfPax)) {
                            System.out.println("Number of Pax exceeds room capacity. Please try again");
                            isUpdateSuccessful = false;
                            break;
                        }
                        ReservationManager.updateNumberOfPax(reservationId, numberOfPax);
                        isUpdateSuccessful = true;
                        break;
                    case 6:
                        printExpiredMenu();
                        isExpired = Helper.readInt(1,3);
                        switch (isExpired) {
                            case 1:
                                ReservationManager.updateIsExpired(reservationId, true);
                                isUpdateSuccessful = true;
                                break;
                            case 2:
                                ReservationManager.updateIsExpired(reservationId, false);
                                isUpdateSuccessful = true;
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Invalid option. Please try again");
                                break;
                        }
                    case 7:
                        printReservationStatusMenu();
                        reservationStatus = Helper.readInt(1, 6);
                        if (reservationStatus >= 1 && reservationStatus <= 6) {
                            ReservationManager.updateReservationStatus(reservationId, reservationStatus);
                            isUpdateSuccessful = true;
                        } else {
                            System.out.println("Invalid option. Please try again");
                        }
                        break;
                    case 8:

                        break;
                    default:
                        System.out.println("Invalid option. Please try again");
                        break;
                }
                if (isUpdateSuccessful) {
                    System.out.println("Update Reservation Successful");
                }
            } while (opt != 8);
        } else {
            System.out.println("Update Reservation Unsuccessful");
        }
    }
}
