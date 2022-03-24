package src.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.crypto.Data;

import src.model.Guest;
import src.model.Reservation;
import src.model.enums.ReservationStatus;
import src.model.enums.RoomStatus;
import src.database.Database;
import src.database.FileType;
import src.helper.Helper;

public class ReservationManager {
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

    public static boolean remove(String reservationId) {
        if (validateReservationId(reservationId)) {
            Database.RESERVATIONS.remove(reservationId);
            Database.saveFileIntoDatabase(FileType.RESERVATIONS);
            return true;
        }
        return false;
    }

    public static boolean validateReservationId(String reservationId) {
        if (Database.RESERVATIONS.containsKey(reservationId)) {
            return true;
        } else {
            System.out.println("Reservation Id not found");
            return false;
        }
    }
    
    private static Reservation search(String reservationId) {
        if (validateReservationId(reservationId)) {
            return Database.RESERVATIONS.get(reservationId);
        } else
            return null;
    }

    public static String getRoomIdFromReservationId(String reservationId) {
        if (validateReservationId(reservationId)) {
            return Database.RESERVATIONS.get(reservationId).getRoomId();
        } else
            return "";
    }
    
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

    public static void updateCheckedInDate(String reservationId, String date) {
        search(reservationId).setCheckedInDate(date);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    
    public static void updateCheckedOutDate(String reservationId, String date) {
        search(reservationId).setCheckedOutDate(date);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    
    public static void updateGuestId(String reservationId, String guestId) {
        search(reservationId).setGuestId(guestId);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    
    public static void updateRoomId(String reservationId, String roomId, ReservationStatus reservationStatus) {
        search(reservationId).setRoomId(roomId);
        search(reservationId).setReservationStatus(reservationStatus);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    
    public static void updateNumberOfPax(String reservationId, int num) {
        search(reservationId).setNumberOfPax(num);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    
    public static void updateIsExpired(String reservationId, boolean val) {
        search(reservationId).setIsExpired(val);
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }
    
    public static void updateReservationStatus(String reservationId, int status) {
        switch (status) {
            case 1:
                search(reservationId).setReservationStatus(ReservationStatus.CONFIRMED);
            case 2:
                search(reservationId).setReservationStatus(ReservationStatus.IN_WAITLIST);
            case 3:
                search(reservationId).setReservationStatus(ReservationStatus.CHECKED_IN);
            case 4:
                search(reservationId).setReservationStatus(ReservationStatus.EXPIRED);
            case 5:
                search(reservationId).setReservationStatus(ReservationStatus.CHECKED_OUT);
            case 6:
                search(reservationId).setReservationStatus(ReservationStatus.CANCELLED);
        }
        Database.saveFileIntoDatabase(FileType.RESERVATIONS);
    }

    public static void printAllReservations() {
        for (Reservation reservation : Database.RESERVATIONS.values()) {
            System.out.println(reservation);
        }
    }
   
}

  