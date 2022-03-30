package src.model;
import src.model.enums.ReservationStatus;

import java.io.Serializable;

import src.helper.Helper;
/**
 * The Class that handles Reservation model
 * @author Max 
 * @version 1.0
 * @since 2022-3-28
 */
public class Reservation implements Serializable {
    /**
     * For Java Serializable
     */
    private static final long serialVersionUID = 3L;
    /**
     * The date which the guest checked in
     */
    private String checkedInDate;
    /**
     * The date which the guest checked out
     */
    private String checkedOutDate;
    /**
     * The date which the reservation is made
     */
    private String reservationDate;
    /**
     * Id of the guest
     */
    private String guestId;
    /**
     * Id of the room
     */
    private String roomId;
    /**
     * Number of people staying in one room
     */
    private int numberOfPax;
    /**
     *  A boolean value indicates whether the reservation is expired or not
     */
    private boolean isExpired;
    /**
     * status of the reservation <p>
     * {@link ReservationStatus} 
     */
    private ReservationStatus reservationStatus;
    /**
     * Id of the reservation
     */
    private String reservationId;
    
    /**
     * Constructor of Reservation
     * @param checkedInDate the date which the guest checked in
     * @param checkedOutDate the date which the guest checked out
     * @param guestId Id of the guest
     * @param roomId Id of the room
     * @param numberOfPax number of people staying in one room
     * @param reservationId Id of the reservation
     * @param reservationStatus status of the reservation
     * {@link ReservationStatus} 
     */
    public Reservation(String checkedInDate, String checkedOutDate, String guestId, String roomId, int numberOfPax, String reservationId, ReservationStatus reservationStatus){
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
        this.reservationStatus = reservationStatus;
        this.reservationId = reservationId;
    }
    /**
     * A method that returns checked in date
     * @return the date which the guest checked in
     */
    public String getCheckedInDate() {
        return checkedInDate;
    }
    /**
     * A method that updates the checked in date
     * @param checkedInDate the date which the guest checked in
     * @return {@code true} if updates successfully
     */
    public boolean setCheckedInDate(String checkedInDate) {
        this.checkedInDate = checkedInDate;
        return true;
    }
    /**
     * A method that returns checked out date
     * @return the date which the guest checked out
     */
    public String getCheckedOutDate() {
        return checkedOutDate;
    }
    /**
     * A method that updates the checked out date
     * @param checkedOutDate the date which the guest checked out
     * @return {@code true} if updates successfully
     */
    public boolean setCheckedOutDate(String checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
        return true;
    }
    /**
     * A method that returns guestId
     * @return Id of the guest
     */
    public String getGuestId() {
        return guestId;
    }
    /**
     * A method that updates guestId
     * @param guestId Id of the guest
     * @return {@code true} if updates successfully
     */
    public boolean setGuestId(String guestId) {
        this.guestId = guestId;
        return true;
    }
    /**
     * A method that returns roomId
     * @return Id of the room
     */
    public String getRoomId() {
        return roomId;
    }
    /**
     * A method that updates the roomId
     * @param roomId Id of the room
     * @return {@code true} if updates successfully
     */
    public boolean setRoomId(String roomId) {
        this.roomId = roomId;
        return true;
    }
    /**
     * A method that returns the number of pax
     * @return number of people staying in one room
     */
    public int getNumberOfPax() {
        return numberOfPax;
    }
    /**
     * A method that updates the numberOfPax
     * @param numberOfPax number of people staying in one room
     * @return {@code true} if updates successfully
     */
    public boolean setNumberOfPax(int numberOfPax) {
        this.numberOfPax = numberOfPax;
        return true;
    }
    /**
     * A method that returns reservation date
     * @return the date which the reservation is made
     */
    public String getReservationDate() {
        return reservationDate;
    }
    /**
     * A method that returns reservation status
     * @return status of the reservation
     * {@link ReservationStatus} 
     */
    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }
    /**
     * A method that updates reservation status
     * @param reservationStatus status of the reservation
     * @return {@code true} if updates successfully
     * {@link ReservationStatus} 
     */
    public boolean setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
        return true;
    }
    /**
     * A method that checks whether the reservation is expired or not
     * @return {@code true} if the reservation is expired. Otherwise, {@code false}
     */
    public boolean getIsExpired() {
        return isExpired;
    }
    /**
     * A method that updates the expire status of reservation
     * @param isExpired A boolean value indicates whether the reservation is expired or not
     * @return {@code true} if updates successfully
     */
    public boolean setIsExpired(boolean isExpired){
        this.isExpired = isExpired;
        return true;
    }
    /**
     * A method that returns reservationId
     * @return Id of the reservation
     */
    public String getReservationId() {
        return reservationId;
    }
    /**
    * Override toString method to show the simplified details of the reservation
    * @return a string of reservation details
    */
    @Override    
    public String toString() {
        String res = String.format(
                "Reservation Id: %s Guest Id: %s Room Id: %s NumOfPax: %d Check In: %s Check Out: %s Status: %s Is Expired: %b",
                getReservationId(), getGuestId(), getRoomId(), getNumberOfPax(), getCheckedInDate(), getCheckedOutDate(),
                getReservationStatus().reservationStatusAsStr, getIsExpired()
            );
        return res;
    }
}
