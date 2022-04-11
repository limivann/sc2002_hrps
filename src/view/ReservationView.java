package src.view;

import src.controller.ReservationManager;

import java.util.ArrayList;

import src.controller.GuestManager;
import src.helper.Helper;
import src.model.enums.ReservationStatus;
import src.model.enums.RoomStatus;
import src.controller.RoomManager;

// for javadocs
import src.model.Reservation;
/**
 * ReservationView provides the view to take user input which calls {@link ReservationManager} to manage {@link Reservation}.
 * @author Max 
 * @version 1.0
 * @since 2022-4-06
 */
public class ReservationView extends MainView {
    /**
     * Default constructor for Reservation View
     */
    public ReservationView() {
        
    }
    /**
     * View Menu of the ReservationView.
     */
    @Override
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Reservation View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Create Reservation");
        System.out.println("(2) Search Reservation");
        System.out.println("(3) Update Reservation");
        System.out.println("(4) Remove Reservation");
        System.out.println("(5) Print All Reservations");
        System.out.println("(6) Exit Reservation View");
    }
    /**
     * View Application for the ReservationView. <p>
     * see {@link ReservationManager} for more {@link Reservation} management details.
     */
    @Override
    public void viewApp(){
        int opt = -1;
        String reservationId; 
        ReservationManager.checkReservationStatus(); // check is any reservation is expired
        do {
            printMenu();
            opt = Helper.readInt(1,6);
            switch (opt) {
                case 1:
                    if (createReservation()) {
                        System.out.println("Create reservation successful");
                    } else {
                        System.out.println("Create reservation unsuccessful");
                    }
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Reservation View > Search Reservation");
                    System.out.println("Enter Reservation Id to search (RXXXX)");
                    reservationId = Helper.readString();
                    ReservationManager.printReservationDetails(reservationId);
                    break;
                case 3:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Reservation View > Update Reservation");
                    System.out.println("Enter Reservation Id to update (RXXXX)");
                    reservationId = Helper.readString();
                    updateReservation(reservationId);
                    break;
                case 4:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Reservation View > Remove Reservation");
                    System.out.println("Enter Reservation Id to remove (RXXXX)");
                    reservationId = Helper.readString();
                    if (ReservationManager.removeReservation(reservationId)) {
                        System.out.println("Remove reservation successful");
                    } else {
                        System.out.println("Remove reservation unsuccessful");
                    }
                    break;
                case 5:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Reservation View > Print All Reservations");
                    ReservationManager.printAllReservations();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            if (opt != 6) {
                Helper.pressAnyKeyToContinue();
            }
        }while( opt != 6);
    }
    /**
     * View Menu for creating reservation. <p>
     * {@link ReservationManager} for more reservation management details. <p>
     * @return {@code true} if reservation is created successfully. Otherwise, {@code false}.
     */
    private boolean createReservation() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Reservation View > Create Reservation");
        System.out.println("Please enter an option (1-2)");
        System.out.println("(1) Walk-In");
        System.out.println("(2) Reserve");
        int choice = Helper.readInt(1, 2);

        String checkedInDate;
        String checkedOutDate;
        String guestId;
        String roomId;
        int numberOfPax;
        boolean inWaitlist = false;
        System.out.println("Please enter the Room Id in this format <FloorNo><RoomNo> (eg: 0103)");
        roomId = Helper.readString();
        if (!RoomManager.validateRoomId(roomId)) {
            System.out.println("Room does not exist");
            return false;
        }
        if (!RoomManager.checkRoomVacancy(roomId, RoomStatus.VACANT)) {
            if (choice == 1) {
                System.out.println("Unable to walk in an occupied room");
                return false;
            } else {
                System.out.println("Room is not Vacant! Guest will be moved to waitlist");
                inWaitlist = true;
            }
        }
        RoomManager.printRoom(roomId);
        
        System.out.println("Enter number of pax");
        numberOfPax = Helper.readInt();
        if (!RoomManager.validateNumOfPax(roomId, numberOfPax)) {
            System.out.println("Number of Pax exceeds room capacity. Please try again");
            return false;
        }

        ArrayList<String> guestIds = new ArrayList<String>();
        for (int guestNo = 1; guestNo <= numberOfPax; guestNo++) {
            System.out.println("Enter Guest Id (GXXXX) for guest " + guestNo + ":");
            guestId = Helper.readString();
            if (!GuestManager.validateGuestId(guestId)) {
                System.out.println("Guest Id not found. Please try again");
                return false;
            }
            if (guestIds.contains(guestId)) {
                System.out.println("Duplicate guest id. Please try again");
                return false;
            }
            guestIds.add(guestId);
        }
        

        if (choice == 2) {
            System.out.println("Enter Check In Date");
            checkedInDate = Helper.setDate(false);
            if (checkedInDate.equals("")){
                return false;
            }
            System.out.println("Enter Check Out Date");
            checkedOutDate = Helper.setDate(false);
            if (checkedOutDate.equals("")) {
                return false;
            }
            // check if check out date is later than check in date
            if (!Helper.validateTwoDates(checkedInDate, checkedOutDate)) {
                System.out.println("Check out date cannot be earlier than check in date!");
                return false;
            }
            if (inWaitlist) {
                ReservationManager.createReservation(checkedInDate, checkedOutDate, guestIds, roomId, numberOfPax, ReservationStatus.IN_WAITLIST, RoomStatus.RESERVED);
            } else {
                ReservationManager.createReservation(checkedInDate, checkedOutDate, guestIds, roomId, numberOfPax, ReservationStatus.CONFIRMED, RoomStatus.RESERVED);
            }
        } else {
            System.out.println("Enter Check Out Date");
            checkedInDate = Helper.setDate(true);
            checkedOutDate = Helper.setDate(false);
            if (checkedInDate.equals("") || checkedOutDate.equals("")) {
                return false;
            }
            // check if check out date is later than check in date
            if (!Helper.validateTwoDates(checkedInDate, checkedOutDate)) {
                System.out.println("Check out date cannot be earlier than check in date!");
                return false;
            }
            ReservationManager.createReservation(checkedInDate, checkedOutDate, guestIds, roomId, numberOfPax, ReservationStatus.CHECKED_IN, RoomStatus.OCCUPIED);
        }
        return true;
    }
    /**
     * View Menu for Reservation Status. <p>
     * {@link ReservationStatus}
     */
    private void printReservationStatusMenu() {
        System.out.println("Enter new reservation status (1-6)");
        System.out.println("(1) Confirmed");
        System.out.println("(2) In Waitlist");
        System.out.println("(3) Checked In");
        System.out.println("(4) Expired");
        System.out.println("(5) Checked Out");
        System.out.println("(6) Cancelled");
    }
    /**
     * View Menu for Reservation Update.
     */
    private void printUpdateReservationMenu() {
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
     * View Menu for Expiration Status of Reservation.
     */
    private void printExpiredMenu() {
        System.out.println("Please select an option (1-3");
        System.out.println("(1) Expired");
        System.out.println("(2) Not Expired");
        System.out.println("(3) Back");
    }
    /**
     * View Application for Reservation Update.
     * @param reservationId Id of the reservation <p>
     * see {@link ReservationStatus} for different types of Reservation Status. <p>
     * see {@link ReservationManager} for more reservation management details.
     */
    private void updateReservation(String reservationId){
        if (ReservationManager.validateReservationId(reservationId)) {
            int opt = -1;
            String Date;
            String guestId;
            String roomId;
            int numberOfPax;
            int isExpired;
            int reservationStatus;
            do {
                boolean isUpdateSuccessful = false;
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
                        Reservation reservation = ReservationManager.searchReservation(reservationId);
                        ArrayList<String> guestIds = new ArrayList<String>();
                        isUpdateSuccessful = false;
                        for (int guestNo = 1; guestNo <= reservation.getNumberOfPax(); guestNo++) {
                            System.out.println("Enter guest Id for " + guestNo);
                            guestId = Helper.readString();
                            if (!GuestManager.validateGuestId(guestId)) {
                                System.out.println("Guest id not found. Please try again");
                                break;
                            }
                            guestIds.add(guestId);
                            if (guestNo == reservation.getNumberOfPax()) {
                                isUpdateSuccessful = true;
                            }
                        }
                        if (!isUpdateSuccessful) {
                            break;
                        } 
                        ReservationManager.updateGuestIds(reservationId, guestIds);
                        break;
                    case 4:
                        System.out.println("Please enter the Room Id in this format <FloorNo><RoomNo> (eg: 0103)");
                        roomId = Helper.readString();
                        if (!RoomManager.checkRoomVacancy(roomId, RoomStatus.VACANT)) {
                            if (RoomManager.validateRoomId(roomId)) {
                                System.out.println("Room is not Vacant! Guest will be move to waitlist");
                                ReservationManager.updateRoomId(reservationId, roomId, ReservationStatus.IN_WAITLIST);
                                isUpdateSuccessful = true;
                            } else {
                                System.out.println("Room id not found.");
                            }
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
                        if (ReservationManager.updateReservationStatus(reservationId, reservationStatus)) {
                            isUpdateSuccessful = true;
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
