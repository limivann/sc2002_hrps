package src.model;
import src.model.enums.ReservationStatus;

import java.io.Serializable;

import src.helper.Helper;

public class Reservation implements Serializable {

    private static final long serialVersionUID = 3L;
    
    private String checkedInDate;
    private String checkedOutDate;
    private String reservationDate;
    private String guestId;
    private String roomId;
    private int numberOfPax;
    private boolean isExpired;
    private ReservationStatus reservationStatus;
    private String reservationId;

    public Reservation(String checkedInDate, String checkedOutDate, String guestId, String roomId, int numberOfPax, String reservationId){
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
        this.reservationId = reservationId;
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

    public boolean setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
        return true;
    }

    public boolean getIsExpired() {
        return isExpired;
    }
    
    public boolean setIsExpired(boolean isExpired){
        this.isExpired = isExpired;
        return true;
    }

    public String getReservationId() {
        return reservationId;
    }
    
    @Override
    public String toString() {
        String res = String.format(
                "Reservation Id: %s Guest Id: %s NumOfPax: %d Check In: %s Check Out: %s Status: %s Is Expired: %b",
                getReservationId(), getGuestId(), getNumberOfPax(), getCheckedInDate(), getCheckedOutDate(),
                getReservationStatus().reservationStatusAsStr, getIsExpired()
            );
        return res;
    }
}
