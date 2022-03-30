package src.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.crypto.Data;

import src.model.Guest;
import src.model.Reservation;
import src.model.Room;
import src.model.enums.ReservationStatus;
import src.model.enums.RoomStatus;
import src.database.Database;
import src.database.FileType;
import src.helper.Helper;

/**
 * The Class that manages {@link Reservation}.
 * @author Max 
 * @version 1.0
 * @since 2022-3-28
 */
public class ReservationManager {
    /**
     * A method that creates reservation.
     * @param checkedInDate the date which the guest checked in
     * @param checkedOutDate the date which the guest checked out
     * @param guestId Id of the guest
     * @param roomId Id of the room
     * @param numberOfPax number of people staying in one room
     * @param reservationStatus status of the reservation <p>
     * see {@link ReservationStatus} for different types of Reservation Status.
     */
    public static void create(String checkedInDate, String checkedOutDate, String guestId, String roomId,
            int numberOfPax, ReservationStatus reservationStatus) {
        int rid = Helper.generateUniqueId(Database.RESERVATIONS);
        String reservationId = String.format("R%04d", rid);
        Reservation newReservation = new Reservation(checkedInDate, checkedOutDate, guestId, roomId, numberOfPax,
                reservationId, reservationStatus);
        System.out.println("Reservation created. Reservation ID: " + reservationId);
        Database.RESERVATIONS.put(reservationId, newReservation);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);

        // Edit rooms status to reserved
        RoomManager.updateRoomStatus(roomId, RoomStatus.RESERVED);
    }
    /**
     * A method that removes reservation from database.
     * @param reservationId Id of the reservation
     * @return true is removes successfully. Otherwise, false. <p>
     * see {@RoomManager} for more room management details. <p>
     * see {@Database} for more details about database.
     */
    public static boolean remove(String reservationId) {
        if (validateReservationId(reservationId)) {
            System.out.println("HERE");
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
     * @return true if reservation is found in database. Otherwise, false.
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
     * A method that returns reservation by reservation Id.
     * @param reservationId Id of the reservation
     * @return Reservation if successfully found in database. <p>
     * see {@link Reservation} for more Reservation details.
     */
    public static Reservation search(String reservationId) {
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
     * A method that prints out the details of a specified reservation.
     * @param reservationId Id of the reservation <p>
     * see {@link Reservation} for more details about Reservation.
     */
    public static void printReservationDetails(String reservationId){
        if(validateReservationId(reservationId)){
            Reservation reservation = Database.RESERVATIONS.get(reservationId);
            System.out.println("----------------");
            System.out.println(String.format("Reservation Id:\t%s", reservation.getReservationId()));
            ArrayList<Guest> guests = GuestManager.searchGuestById(reservation.getGuestId());
            String guestName = guests.size() > 0 ? guests.get(0).getName() : "Guest not found";
            System.out.println(String.format("Guest Name:\t%s", guestName));
            System.out.println(String.format("Room Id:\t%s", reservation.getRoomId()));
            System.out.println(String.format("Checked In Date:\t%s", reservation.getCheckedInDate()));
            System.out.println(String.format("Checked Out Date:\t%s", reservation.getCheckedOutDate()));
            System.out.println(String.format("Reservation Out Date:\t%s", reservation.getReservationDate()));
            System.out.println(String.format("Number Of Pax:\t%d", reservation.getNumberOfPax()));
            System.out.println(String.format("Reservation Status:\t%s", reservation.getReservationStatus()));
            System.out.println("----------------");
        }
    }
    /**
     * A method that updates checked in date of reservation. 
     * @param reservationId Id of the reservtion
     * @param date the date which the guest checked in
     */
    public static void updateCheckedInDate(String reservationId, String date) {
        search(reservationId).setCheckedInDate(date);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    /**
     * A method that updates checked out date of reservation.
     * @param reservationId Id of the reservation
     * @param date the date which the guest checked out
     */
    public static void updateCheckedOutDate(String reservationId, String date) {
        search(reservationId).setCheckedOutDate(date);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    /**
     * A method that updates guest id of reservation.
     * @param reservationId Id of the reservation
     * @param guestId Id of the guest 
     */
    public static void updateGuestId(String reservationId, String guestId) {
        search(reservationId).setGuestId(guestId);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    /**
     * A method that updates the status of reservation.
     * @param reservationId Id of the reservation
     * @param roomId Id of the room 
     * @param reservationStatus status of reservation <p>
     * see {@link ReservationStatus} for different types of Reservation Status.
     */
    public static void updateRoomId(String reservationId, String roomId, ReservationStatus reservationStatus) {
        // shift waitlist up
        ArrayList<Reservation> candidates = getWaitlistedReservation(roomId);
        if (candidates.size() > 0) {
            Reservation target = candidates.get(0);
            target.setReservationStatus(ReservationStatus.CONFIRMED);
            // Set room status
        } else {
            RoomManager.updateRoomStatus(roomId, RoomStatus.VACANT);
        }
        search(reservationId).setRoomId(roomId);
        search(reservationId).setReservationStatus(reservationStatus);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    /**
     * A method that updates the number of pax of reservation.
     * @param reservationId Id of the reservation
     * @param num number of people staying in one room
     */
    public static void updateNumberOfPax(String reservationId, int num) {
        search(reservationId).setNumberOfPax(num);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    /**
     * A method that updates the expiration status of reservation.
     * @param reservationId Id of the reservation
     * @param val true if the reservation is expired. Otherwise, false
     */
    public static void updateIsExpired(String reservationId, boolean val) {
        search(reservationId).setIsExpired(val);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    /**
     * A method that updates the status of reservation.
     * @param reservationId Id of the reservation
     * @param status status of the reservation <p>
     * see {@link ReservationStatus} for different types of Reservation Status.
     */
    public static void updateReservationStatus(String reservationId, int status) {
        switch (status) {
            case 1:
                search(reservationId).setReservationStatus(ReservationStatus.CONFIRMED);
                break;
            case 2:
                search(reservationId).setReservationStatus(ReservationStatus.IN_WAITLIST);
                break;
            case 3:
                search(reservationId).setReservationStatus(ReservationStatus.CHECKED_IN);
                break;
            case 4:
                search(reservationId).setReservationStatus(ReservationStatus.EXPIRED);
                break;
            case 5:
                search(reservationId).setReservationStatus(ReservationStatus.CHECKED_OUT);
                break;
            case 6:
                search(reservationId).setReservationStatus(ReservationStatus.CANCELLED);
                break;
        }
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    /**
     * A method that prints out all the reservations in database.
     */
    public static void printAllReservations() {
        for (Reservation reservation : Database.RESERVATIONS.values()) {
            System.out.println(reservation);
        }
    }
    /**
     * A method that checks in reservation.
     * @param reservationId - Id of the reservation
     * @return true checks in successfully. Otherwise, false.
     */
    public static boolean checkInReservation(String reservationId) {
        if (!validateReservationId(reservationId)) {
            return false;
        }
        ReservationStatus reservationStatus = search(reservationId).getReservationStatus();
        if (reservationStatus == ReservationStatus.CHECKED_IN) {
            System.out.println("You have already checked in");
            return false;
        }
        updateReservationStatus(reservationId, 3);
        return true;
    }
    /**
     * A method that checks out reservation.
     * @param reservationId Id of the reservation
     * @return true is checks out successfully. Otherwise, false.
     */
    public static boolean checkOutReservation(String reservationId) {
        if (!validateReservationId(reservationId)) {
            return false;
        }
        ReservationStatus reservationStatus = search(reservationId).getReservationStatus();
        if (reservationStatus == ReservationStatus.CHECKED_OUT) {
            System.out.println("You have already checked out");
            return false;
        }
        updateReservationStatus(reservationId, 5);

        // shift waitlist up
        ArrayList<Reservation> candidates = getWaitlistedReservation(getRoomIdFromReservationId(reservationId));
        if (candidates.size() > 0) {
            Reservation target = candidates.get(0);
            target.setReservationStatus(ReservationStatus.CONFIRMED);
            // Set room status
        } else {
            RoomManager.updateRoomStatus(getRoomIdFromReservationId(reservationId), RoomStatus.VACANT);
        }
        return true;
    }
    
    /**
     * A method that gets reservation in the waitlist.
     * @param roomId Id of the room
     * @return the earliest reservation in the waitlist for a specified room.
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
}

  