package src.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import src.model.enums.ReservationStatus;

public class Reservation {
    private String checkedInDate;
    private String checkedOutDate;
    private String reservationDate;
    private String guestId;
    private String roomId;
    private int numberOfPax;
    private boolean isExpired;
    private ReservationStatus reservationStatus;
    private String reservationId;
    private static int Id = 1;

    public Reservation(String checkedInDate, String checkedOutDate, String guestId, String roomId, int numberOfPax){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm");
        String formattedDate = date.format(format);
        this.reservationDate = formattedDate;
        this.checkedInDate = checkedInDate;
        this.checkedOutDate = checkedOutDate;
        this.guestId = guestId;
        this.roomId = roomId;
        this.numberOfPax = numberOfPax;
        this.isExpired = false;
        this.reservationStatus = ReservationStatus.CONFIRMED;
        this.reservationId = "Re" + Id;
        Id++;
    }

    public String getCheckedInDate() {
        return checkedInDate;
    }
    public void setCheckedInDate(String checkedInDate) {
        this.checkedInDate = checkedInDate;
    }
    public String getCheckedOutDate() {
        return checkedOutDate;
    }
    public void setCheckedOutDate(String checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
    }
    public String getGuestId() {
        return guestId;
    }
    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public int getNumberOfPax() {
        return numberOfPax;
    }
    public void setNumberOfPax(int numberOfPax) {
        this.numberOfPax = numberOfPax;
    }
    public String getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm");
        String formattedDate = date.format(format);
        this.reservationDate = formattedDate;
    }
    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }
    public void setReservationStatus(int reservationStatus) {
        switch(reservationStatus){
            case 1:
                this.reservationStatus = ReservationStatus.CONFIRMED;
                break;
            case 2:
                this.reservationStatus = ReservationStatus.IN_WAITLIST;
                break;
            case 3:
                this.reservationStatus = ReservationStatus.CHECKED_IN;
                break;
            case 4:
                this.reservationStatus = ReservationStatus.EXPIRED;
                break;
            case 5:
                this.reservationStatus = ReservationStatus.CHECKED_OUT;
                break;
            case 6:
                this.reservationStatus = ReservationStatus.CANCELLED;
                break;
            default:
                System.out.println("Invalid option");
        }
    }
    public boolean getIsExpired(){
        return isExpired;
    }
    public void setIsExpired(boolean isExpired){
        this.isExpired = isExpired;
    }
    public String getReservationId() {
        return reservationId;
    }
}
