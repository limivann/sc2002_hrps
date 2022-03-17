package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private String checkedInDate;
    private String checkedOutDate;
    private String reservationDate;
    private int guestId;
    private int roomId;
    private int numberOfPax;
    private boolean isExpired;
    private ReservationStatus reservationStatus;

    public Reservation(String checkedInDate, String checkedOutDate, int guestId, int roomId, int numberOfPax){
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
    public int getGuestId() {
        return guestId;
    }
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
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
    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
    public boolean getIsExpired(){
        return isExpired;
    }
    public void setIsExpired(boolean isExpired){
        this.isExpired = isExpired;
    }
    
}
