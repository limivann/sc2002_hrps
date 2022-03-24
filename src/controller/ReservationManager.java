package src.controller;

import java.util.HashMap;
import src.model.Reservation;
import src.model.Invoice;

public class ReservationManager {
    HashMap<String, Reservation> ReservationList = new HashMap<String, Reservation>();
    
    public void create(String checkedInDate, String checkedOutDate, String guestId, String roomId, int numberOfPax){
        Reservation new_reservation = new Reservation(checkedInDate, checkedOutDate, guestId, roomId, numberOfPax);
        ReservationList.put(new_reservation.getReservationId(), new_reservation);
        Invoice new_invoice = new Invoice(new_reservation);
    }
    public void remove(String reservationId){
        if(validate(reservationId)){
            ReservationList.remove(reservationId);
        }
    }
    public boolean validate(String reservationId){
        if(ReservationList.containsKey(reservationId)){
            return true;
        }
        else{
            System.out.println("ReservationId not found");
            return false;
        }
    }
    private Reservation search(String reservationId){
        if(validate(reservationId)){
            return ReservationList.get(reservationId);
        }
        else return null;
    }
    public void print(String reservationId){
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
            System.out.println("----------------");
        }
    }
    public void updateCheckedInDate(String reservationId, String date){
        search(reservationId).setCheckedInDate(date);
    }
    public void updateCheckedOutDate(String reservationId, String date){
        search(reservationId).setCheckedOutDate(date);
    }
    public void updateGuestId(String reservationId, String guestId){
        search(reservationId).setGuestId(guestId);
    }
    public void updateRoomId(String reservationId, String roomId){
        search(reservationId).setRoomId(roomId);
    }
    public void updateNumberOfPax(String reservationId, int num){
        search(reservationId).setNumberOfPax(num);
    }
    public void updateIsExpired(String reservationId, boolean val){
        search(reservationId).setIsExpired(val);
    }
    public void updateReservationStatus(String reservationId, int status){
        search(reservationId).setReservationStatus(status);
    }
   
}

  