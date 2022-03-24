package src.controller;

import java.util.HashMap;
import src.model.Reservation;
import src.database.Database;
import src.helper.Helper;

public class ReservationManager {
    static HashMap<String, Reservation> ReservationList = new HashMap<String, Reservation>();

    public static void create(String checkedInDate, String checkedOutDate, String guestId, String roomId, int numberOfPax){
        int rid = Helper.generateUniqueId(Database.RESERVATIONS);
        String reservationId = String.format("R%04d", rid);
        Reservation new_reservation = new Reservation(checkedInDate, checkedOutDate, guestId, roomId, numberOfPax, reservationId);
        ReservationList.put(new_reservation.getReservationId(), new_reservation);
    }
    public static void remove(String reservationId){
        if(validate(reservationId)){
            ReservationList.remove(reservationId);
        }
    }
    public static boolean validate(String reservationId){
        if(ReservationList.containsKey(reservationId)){
            return true;
        }
        else{
            System.out.println("ReservationId not found");
            return false;
        }
    }
    private static Reservation search(String reservationId){
        if(validate(reservationId)){
            return ReservationList.get(reservationId);
        }
        else return null;
    }
    public static void print(String reservationId){
        if(validate(reservationId)){
            Reservation reservation = ReservationList.get(reservationId);
            System.out.println("----------------");
            System.out.println(String.format("reservationId:\t%s", reservation.getReservationId()));
            System.out.println(String.format("guestId:\t%s", reservation.getGuestId()));
            System.out.println(String.format("roomId:\t%s", reservation.getRoomId()));
            System.out.println(String.format("checkedInDate:\t%s", reservation.getCheckedInDate()));
            System.out.println(String.format("checkedOutDate:\t%s", reservation.getCheckedOutDate()));
            System.out.println(String.format("reservationOutDate:\t%s", reservation.getReservationDate()));
            System.out.println(String.format("numberOfPax:\t%d", reservation.getNumberOfPax()));
            System.out.println(String.format("reservationStatus:\t%s", reservation.getReservationStatus()));
            System.out.println("----------------");
        }
    }
    public static void updateCheckedInDate(String reservationId, String date){
        search(reservationId).setCheckedInDate(date);
    }
    public static void updateCheckedOutDate(String reservationId, String date){
        search(reservationId).setCheckedOutDate(date);
    }
    public static void updateGuestId(String reservationId, String guestId){
        search(reservationId).setGuestId(guestId);
    }
    public static void updateRoomId(String reservationId, String roomId){
        search(reservationId).setRoomId(roomId);
    }
    public static void updateNumberOfPax(String reservationId, int num){
        search(reservationId).setNumberOfPax(num);
    }
    public static void updateIsExpired(String reservationId, boolean val){
        search(reservationId).setIsExpired(val);
    }
    public static void updateReservationStatus(String reservationId, int status){
        search(reservationId).setReservationStatus(status);
    }
   
}

  