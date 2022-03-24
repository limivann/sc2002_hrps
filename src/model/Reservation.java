package src.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import src.model.enums.ReservationStatus;
import src.helper.*;

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

    public Reservation(String checkedInDate, String checkedOutDate, String guestId, String roomId, int numberOfPax){
        this.reservationDate = Helper.getTimeNow();
        if(checkedInDate==null){
            this.checkedInDate = this.reservationDate;
        }
        else{
            this.checkedInDate = checkedInDate;
        }
        this.checkedOutDate = checkedOutDate;
        this.guestId = guestId;
        this.roomId = roomId;
        this.numberOfPax = numberOfPax;
        this.isExpired = false;
        this.reservationStatus = ReservationStatus.CONFIRMED;
        this.reservationId = Id;
    }

    public String getCheckedInDate() {
        return checkedInDate;
    }
    public boolean setCheckedInDate(String checkedInDate) {
        this.checkedInDate = checkedInDate;
        return true;
    }
    public String getCheckedOutDate() {
        return checkedOutDate;
    }
    public boolean setCheckedOutDate(String checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
        return true;
    }
    public String getGuestId() {
        return guestId;
    }
    public boolean setGuestId(String guestId) {
        this.guestId = guestId;
        return true;
    }
    public String getRoomId() {
        return roomId;
    }
    public boolean setRoomId(String roomId) {
        this.roomId = roomId;
        return true;
    }
    public int getNumberOfPax() {
        return numberOfPax;
    }
    public boolean setNumberOfPax(int numberOfPax) {
        this.numberOfPax = numberOfPax;
        return true;
    }
    public String getReservationDate() {
        return reservationDate;
    }
 
    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }
    public boolean setReservationStatus(int reservationStatus) {
        switch(reservationStatus){
            case 1:
                this.reservationStatus = ReservationStatus.CONFIRMED;
                return true;
            case 2:
                this.reservationStatus = ReservationStatus.IN_WAITLIST;
                return true;
            case 3:
                this.reservationStatus = ReservationStatus.CHECKED_IN;
                return true;
            case 4:
                this.reservationStatus = ReservationStatus.EXPIRED;
                return true;
            case 5:
                this.reservationStatus = ReservationStatus.CHECKED_OUT;
                return true;
            case 6:
                this.reservationStatus = ReservationStatus.CANCELLED;
                return true;
            default:
                System.out.println("Invalid option");
                return false;
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
