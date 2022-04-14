package src.controller;

import java.util.ArrayList;
import java.util.Collections;

import src.model.Guest;
import src.model.Reservation;
import src.model.enums.ReservationStatus;
import src.model.enums.RoomStatus;
import src.database.Database;
import src.database.FileType;
import src.helper.Helper;

// for javadocs
import src.view.HotelAppView;
import src.view.ReservationView;
import src.model.Room;
/**
 * RservationManager is a controller class that acts as a "middleman"
 * between the view classes - {@link HotelAppView} and {@link ReservationView} and the model classes - {@link Reservation}, {@link Guest} and {@link Room}. <p>
 * 
 * It can create and update reservation with the help of {@link GuestManager} and {@link RoomManager}.
 * @author Max, Ivan
 * @version 1.0
 * @since 2022-04-04
 */
public class ReservationManager {
    /**
     * Default constructor for Reservation Manager
     */
    public ReservationManager() {
        
    }
    /**
     * A method that creates reservation. <p>
     * See {@link ReservationStatus} for different types of Reservation Status. <p>
     * See {@link RoomStatus} for different types of Room Status.
     * @param checkedInDate the date which the guest checked in
     * @param checkedOutDate the date which the guest checked out
     * @param guestIds Ids of the guests
     * @param roomId Id of the room
     * @param numberOfPax number of people staying in one room
     * @param reservationStatus status of the reservation
     * @param roomStatus room status of the reserved room
     */
    public static void createReservation(String checkedInDate, String checkedOutDate, ArrayList<String> guestIds, String roomId,
    int numberOfPax, ReservationStatus reservationStatus, RoomStatus roomStatus) {
        int rid = Helper.generateUniqueId(Database.RESERVATIONS);
        String reservationId = String.format("R%04d", rid);
        String reservationDate = Helper.getTimeNow();
        Reservation newReservation = new Reservation(checkedInDate, checkedOutDate, reservationDate, guestIds, roomId, numberOfPax,
                reservationId, reservationStatus);
        Database.RESERVATIONS.put(reservationId, newReservation);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
        
        // Edit rooms status to reserved
        RoomManager.updateRoomStatus(roomId, roomStatus);
        RoomManager.updateRoomGuestDetails(roomId, guestIds);
        System.out.println("Reservation created. Reservation details: ");
        printReservationDetails(reservationId);
    }
    /**
     * A method that removes reservation from database. <p>
     * See {@link RoomManager} for more room management details. <p>
     * See {@link Database} for more details about database.
     * @param reservationId Id of the reservation
     * @return {@code true} is removes successfully. Otherwise, {@code false} if reservation id does not exist.
     */
    public static boolean removeReservation(String reservationId) {
        if (validateReservationId(reservationId)) {
            Database.RESERVATIONS.remove(reservationId);
            // shift waitlist up
            ArrayList<Reservation> candidates = getWaitlistedReservation(getRoomIdFromReservationId(reservationId));
            if (candidates.size() > 0) {
                Reservation target = candidates.get(0);
                target.setReservationStatus(ReservationStatus.CONFIRMED);
                // Set room status
            } else {
                RoomManager.updateRoomStatus(getRoomIdFromReservationId(reservationId), RoomStatus.VACANT);
            }
            Database.saveFileIntoDatabase(FileType.RESERVATIONS);
            return true;
        }
        return false;
    }
    /**
     * A method that validates reservation by reservation Id.
     * @param reservationId Id of the reservation
     * @return {@code true} if reservation is found in database. Otherwise, {@code false} if reservation id does not exist.
     */
    public static boolean validateReservationId(String reservationId) {
        if (Database.RESERVATIONS.containsKey(reservationId)) {
            return true;
        } else {
            System.out.println("Reservation Id not found");
            return false;
        }
    }
    /**
     * A method that returns reservation by reservation Id. <p>
     * See {@link Reservation} for more Reservation details.
     * @param reservationId Id of the reservation
     * @return Reservation if successfully found in database.
     */
    public static Reservation searchReservation(String reservationId) {
        if (validateReservationId(reservationId)) {
            return Database.RESERVATIONS.get(reservationId);
        } else
            return null;
    }
    /**
     * A method that returns room Id by reservation Id from database.
     * @param reservationId Id of the reservation
     * @return room Id of the reservation.
     */
    public static String getRoomIdFromReservationId(String reservationId) {
        if (validateReservationId(reservationId)) {
            return Database.RESERVATIONS.get(reservationId).getRoomId();
        } else
            return "";
    }
    /**
     * A method that prints out the details of a specified reservation. <p>
     * See {@link Reservation} for more details about Reservation.
     * @param reservationId Id of the reservation
     */
    public static void printReservationDetails(String reservationId){
        if(validateReservationId(reservationId)){
            Reservation reservation = Database.RESERVATIONS.get(reservationId);
            System.out.println(String.format("%-50s", "").replace(" ", "-"));
            System.out.println(String.format("%-30s: %s", "Reservation Id", reservation.getReservationId()));
            ArrayList<String> guestIds = reservation.getGuestIds();
            String guestIdsAsStr = "";
            for (String guestId : guestIds) {
                guestIdsAsStr += guestId + " ";
            }
            System.out.println(String.format("%-30s: %s","Guest Ids" ,guestIdsAsStr));
            System.out.println(String.format("%-30s: %s", "Room Id", reservation.getRoomId()));
            System.out.println(String.format("%-30s: %s","Checked In Date",reservation.getCheckedInDate()));
            System.out.println(String.format("%-30s: %s", "Checked out Date",reservation.getCheckedOutDate()));
            System.out.println(String.format("%-30s: %s", "Reservation Created Date",reservation.getReservationDate()));
            System.out.println(String.format("%-30s: %s", "Number of Pax",String.valueOf(reservation.getNumberOfPax())));
            System.out.println(String.format("%-30s: %s", "Reservation Status",reservation.getReservationStatus()));
            System.out.println(String.format("%-50s", "").replace(" ", "-"));
        }
    }
    
    /**
     * A method that updates checked in date of reservation. 
     * @param reservationId Id of the reservtion
     * @param date the date which the guest checked in
     * @return {@code true} if updating of check in date is successful. Otherwise, {@code false} if the check in date is later than the check out date / reservation not found
     */
    public static boolean updateCheckedInDate(String reservationId, String date) {
        if (!validateReservationId(reservationId)) {
            return false;
        }
        Reservation reservationToUpdate = searchReservation(reservationId);
        if (!Helper.validateTwoDates(date, reservationToUpdate.getCheckedOutDate())) {
            System.out.println("Check in date cannot be later than check out date!");
            return false;
        }
        reservationToUpdate.setCheckedInDate(date);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
        return true;
    }
    
    /**
     * A method that updates checked out date of reservation.
     * @param reservationId Id of the reservation
     * @param date the date which the guest checked out
     * @return {@code true} if updating of check out date is successful. Otherwise, {@code false} if the check out date is earlier than the check in date.
     */
    public static boolean updateCheckedOutDate(String reservationId, String date) {
        if (!validateReservationId(reservationId)) {
            return false;
        }
        Reservation reservationToUpdate = searchReservation(reservationId);
        if (!Helper.validateTwoDates(reservationToUpdate.getCheckedInDate(), date)){
            System.out.println("Check out date cannot be earlier than check in date!");
            return false;
        }
        reservationToUpdate.setCheckedOutDate(date);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
        return true;
    }
    
    /**
     * A method that updates guest id of reservation.
     * @param reservationId Id of the reservation
     * @param guestIds Ids of the guests
     * @return {@code true} if updating of guest id(s) is successful. Otherwise, {@code false} if the reservation id is not found.
     */
    public static boolean updateGuestIds(String reservationId, ArrayList<String> guestIds) {
        if (!validateReservationId(reservationId)) {
            return false;
        }
        searchReservation(reservationId).setGuestIds(guestIds);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
        return true;
    }
    
    /**
     * A method that updates the status of reservation.<p>
     * See {@link ReservationStatus} for different types of Reservation Status.
     * @param reservationId Id of the reservation
     * @param roomId Id of the room 
     * @param reservationStatus status of reservation <p>
     * @return {@code true} if updating of room id is successful. Otherwise, {@code false} if the reservation id is not found.
     */
    public static boolean updateRoomId(String reservationId, String roomId, ReservationStatus reservationStatus) {
        if (!validateReservationId(reservationId)) {
            return false;
        }
        // shift waitlist up
        ArrayList<Reservation> candidates = getWaitlistedReservation(roomId);
        if (candidates.size() > 0) {
            Reservation target = candidates.get(0);
            target.setReservationStatus(ReservationStatus.CONFIRMED);
            // Set room status
        } else {
            RoomManager.updateRoomStatus(roomId, RoomStatus.VACANT);
        }
        searchReservation(reservationId).setRoomId(roomId);
        searchReservation(reservationId).setReservationStatus(reservationStatus);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
        return true;
    }
    
    /**
     * A method that updates the number of pax of reservation.
     * @param reservationId Id of the reservation
     * @param num number of people staying in one room
     * @return {@code true} if updating of the number of pax is successful. Otherwise, {@code false} if the reservation id does not exist.
     */
    public static boolean updateNumberOfPax(String reservationId, int num) {
        if (!validateReservationId(reservationId)) {
            return false;
        }
        searchReservation(reservationId).setNumberOfPax(num);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
        return true;
    }
    
    /**
     * A method that updates the expiration status of reservation.
     * @param reservationId Id of the reservation
     * @param val {@code true} if the reservation is expired. Otherwise, {@code false}
     * @return {@code true} if updating of the expiry status is successful. Otherwise, {@code false} if the reservation id does not exist.
     */
    public static boolean updateIsExpired(String reservationId, boolean val) {
        if (!validateReservationId(reservationId)) {
            return false;
        }
        searchReservation(reservationId).setIsExpired(val);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
        return true;
    }
    
    /**
     * A method that updates the status of reservation. <p>
     * see {@link ReservationStatus} for different types of Reservation Status.
     * @param reservationId Id of the reservation
     * @param status status of the reservation
     * @return {@code true} if updating of the reservation status is successful. Otherwise, {@code false} if the reservation id does not exist.
     */
    public static boolean updateReservationStatus(String reservationId, int status) {
        if (!validateReservationId(reservationId)) {
            return false;
        }
        if (searchReservation(reservationId).getReservationStatus() == ReservationStatus.CHECKED_IN
                || searchReservation(reservationId).getReservationStatus() == ReservationStatus.CHECKED_OUT) {
            System.out.println("Please check in using check in / check out view");
            return false;
        }
        switch (status) {
            case 1:
                if (searchReservation(reservationId).getReservationStatus() == ReservationStatus.CONFIRMED) {
                    return true;
                }
                if (searchReservation(reservationId).getReservationStatus() == ReservationStatus.CHECKED_OUT
                        || searchReservation(reservationId).getReservationStatus() == ReservationStatus.EXPIRED
                        || searchReservation(reservationId).getReservationStatus() == ReservationStatus.CANCELLED) {
                    System.out.println("Unable to confirm an already checked out/expired/cancelled reservation");
                    return false;
                }
                searchReservation(reservationId).setReservationStatus(ReservationStatus.CONFIRMED);
                break;
            case 2:
                if (searchReservation(reservationId).getReservationStatus() == ReservationStatus.IN_WAITLIST) {
                    return true;
                }
                if (searchReservation(reservationId).getReservationStatus() == ReservationStatus.CHECKED_OUT
                        || searchReservation(reservationId).getReservationStatus() == ReservationStatus.EXPIRED
                        || searchReservation(reservationId).getReservationStatus() == ReservationStatus.CANCELLED) {
                    System.out.println("Unable to confirm an already checked out/expired/cancelled reservation");
                    return false;
                }
                searchReservation(reservationId).setReservationStatus(ReservationStatus.IN_WAITLIST);
                break;
            case 3:
                System.out.println("Please check in using check in / check out view");
                return false;
            case 4:
                if (searchReservation(reservationId).getReservationStatus() == ReservationStatus.EXPIRED) {
                    return true;
                }
                if (searchReservation(reservationId).getReservationStatus() == ReservationStatus.CHECKED_IN) {
                    System.out.println("Please check in before changing the status to expired");
                    return false;
                }
                searchReservation(reservationId).setReservationStatus(ReservationStatus.EXPIRED);
                break;
            case 5:
                System.out.println("Please check out using check in / check out view");
                return false;
            case 6:
                if (searchReservation(reservationId).getReservationStatus() == ReservationStatus.CANCELLED) {
                    return true;
                }
                searchReservation(reservationId).setReservationStatus(ReservationStatus.CANCELLED);
                break;
        }
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
        return false;
    }
    /**
     * A method that prints out all the reservations in database.
     */
    public static void printAllReservations() {
        ArrayList<Reservation> sortedList = new ArrayList<Reservation>();
        // copy
        for (Reservation reservation : Database.RESERVATIONS.values()) {
            sortedList.add(reservation);
        }
        Collections.sort(sortedList);
        for (Reservation reservation : sortedList) {
            System.out.println(reservation);
        }
    }
    /**
     * A method that checks in reservation.
     * @param reservationId - Id of the reservation
     * @return {@code true} checks in successfully. Otherwise, {@code false} if reservation id does not exist/the guest had already checked in.
     */
    public static boolean checkInReservation(String reservationId) {
        if (!validateReservationId(reservationId)) {
            return false;
        }
        Reservation reservation = searchReservation(reservationId);
        ReservationStatus reservationStatus = reservation.getReservationStatus();
        
        if (reservationStatus == ReservationStatus.CHECKED_IN) {
            System.out.println("You have already checked in");
            return false;
        }
        if (!Helper.validateTwoDates(reservation.getCheckedInDate(), Helper.getTimeNow())) {
            System.out.println("Please check in after " + reservation.getCheckedInDate());
            return false;
        }
        if (reservationStatus != ReservationStatus.CONFIRMED) {
            System.out.println("This reservation is " + reservationStatus.reservationStatusAsStr);
            return false;
        }
        //  manually check in 
        searchReservation(reservationId).setReservationStatus(ReservationStatus.CHECKED_IN);
        
        RoomManager.updateRoomStatus(getRoomIdFromReservationId(reservationId), RoomStatus.OCCUPIED);
        return true;
    }
    /**
     * A method that checks out reservation.
     * @param reservationId Id of the reservation
     * @return {@code true} is checks out successfully. Otherwise, {@code false} if reservation id does not exist/the guest had already checked out.
     */
    public static boolean checkOutReservation(String reservationId) {
        if (!validateReservationId(reservationId)) {
            return false;
        }
        ReservationStatus reservationStatus = searchReservation(reservationId).getReservationStatus();
        if (reservationStatus != ReservationStatus.CHECKED_IN) {
            System.out.println("This reservation is " + reservationStatus.reservationStatusAsStr);
            return false;
        }
        // manually check out
        searchReservation(reservationId).setReservationStatus(ReservationStatus.CHECKED_OUT);

        // shift waitlist up
        ArrayList<Reservation> candidates = getWaitlistedReservation(getRoomIdFromReservationId(reservationId));
        if (candidates.size() > 0) {
            Collections.sort(candidates);
            Reservation target = candidates.get(0);
            target.setReservationStatus(ReservationStatus.CONFIRMED);
            RoomManager.updateRoomStatus(getRoomIdFromReservationId(reservationId), RoomStatus.RESERVED);
            // Set room status
        } else {
            RoomManager.updateRoomStatus(getRoomIdFromReservationId(reservationId), RoomStatus.VACANT);
        }
        Database.saveAllFiles();
        return true;
    }
    
    /**
     * A method that gets reservation in the waitlist.
     * @param roomId Id of the room
     * @return the earliest {@link Reservation} object in the waitlist for a specified room.
     */
    public static ArrayList<Reservation> getWaitlistedReservation(String roomId) {
        ArrayList<Reservation> candidates = new ArrayList<Reservation>();
        for (Reservation reservation : Database.RESERVATIONS.values()) {
            if (reservation.getReservationStatus() == ReservationStatus.IN_WAITLIST
                    && reservation.getRoomId().equals(roomId)) {
                candidates.add(reservation);
            }
        }
        return candidates;
    }

    /**
     * A method that check if any 'confirmed' reservation has expired
     */
    public static void checkReservationStatus() {
        for (Reservation reservation : Database.RESERVATIONS.values()) {
            if (reservation.getReservationStatus() != ReservationStatus.CONFIRMED) {
                continue;
            }   
            String date = reservation.getCheckedInDate();
            if (!Helper.validateTwoDates(Helper.getTimeNow(), date) && Helper.LocalDateTimediff(date)) {
                updateIsExpired(reservation.getReservationId(), true);
                updateReservationStatus(reservation.getReservationId(), 4);
            }
        }
    }

    /**
     * Method to calculate the number of stays of the given
     * @param reservationId Id of the reservation
     * @return Number of days elapsed from check in date to check out date.
     */
    public static int calculateNumOfStays(String reservationId) {
        Reservation reservation = searchReservation(reservationId);
        if (reservation == null) {
            return -1;
        }
        return (int) Helper.calculateDaysElapsed(reservation.getCheckedInDate(), Helper.getTimeNow());
    }
}

  