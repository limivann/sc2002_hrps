package src.model;
import src.model.enums.ReservationStatus;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Class that handles the data of hotel's reservations.
 * @author Max 
 * @version 1.0
 * @since 2022-3-28
 */
public class Reservation implements Serializable, Comparable<Reservation> {
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
    private ArrayList<String> guestIds;
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
     * @param reservationDate the date which the reservation is made
     * @param guestIds Ids of the guest(s)
     * @param roomId Id of the room
     * @param numberOfPax number of people staying in one room
     * @param reservationId Id of the reservation
     * @param reservationStatus status of the reservation
     * {@link ReservationStatus} 
     */
    public Reservation(String checkedInDate, String checkedOutDate, String reservationDate, ArrayList<String> guestIds, String roomId, int numberOfPax, String reservationId, ReservationStatus reservationStatus){
        this.reservationDate = reservationDate;
        if(checkedInDate==null){
            this.checkedInDate = this.reservationDate;
        }
        else{
            this.checkedInDate = checkedInDate;
        }
        this.checkedOutDate = checkedOutDate;
        this.guestIds = guestIds;
        this.roomId = roomId;
        this.numberOfPax = numberOfPax;
        this.isExpired = false;
        this.reservationStatus = reservationStatus;
        this.reservationId = reservationId;
    }
    
    /**
     * Sets the checked in date
     * @param checkedInDate the date which the guest checked in
     * @return {@code true} if sets successfully
     */
    public boolean setCheckedInDate(String checkedInDate) {
        this.checkedInDate = checkedInDate;
        return true;
    }

    /**
     * Sets the checked out date
     * @param checkedOutDate the date which the guest checked out
     * @return {@code true} if sets successfully
     */
    public boolean setCheckedOutDate(String checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
        return true;
    }

    /**
     * Sets guest Id
     * @param guestIds Ids of the guest(s)
     * @return {@code true} if sets successfully
     */
    public boolean setGuestIds(ArrayList<String> guestIds) {
        this.guestIds = guestIds;
        return true;
    }

    /**
     * Sets the room Id
     * @param roomId Id of the room
     * @return {@code true} if sets successfully
     */
    public boolean setRoomId(String roomId) {
        this.roomId = roomId;
        return true;
    }
    
    /**
     * Sets the reservation Id
     * @param reservationId Id of the reservation
     * @return {@code true} if sets successfully
     */
    public boolean setReservationId(String reservationId) {
        this.reservationId = reservationId;
        return true;
    }

    /**
     * Sets the reservation date
     * @param reservationDate Date of the reservation
     * @return {@code true} if sets successfully
     */
    public boolean setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
        return true;
    }

    /**
     * Sets the numberOfPax
     * @param numberOfPax number of people staying in one room
     * @return {@code true} if sets successfully
     */
    public boolean setNumberOfPax(int numberOfPax) {
        this.numberOfPax = numberOfPax;
        return true;
    }

    /**
     * Sets reservation status
     * @param reservationStatus status of the reservation
     * @return {@code true} if sets successfully
     * {@link ReservationStatus} 
     */
    public boolean setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
        return true;
    }

    /**
     * Sets the expire status of reservation
     * @param isExpired A boolean value indicates whether the reservation is expired or not
     * @return {@code true} if sets successfully
     */
    public boolean setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
        return true;
    }
    
    /**
     * Gets the checked in date
     * @return the date which the guest checked in
     */
    public String getCheckedInDate() {
        return checkedInDate;
    }

    /**
     * Gets the checked out date
     * @return the date which the guest checked out
     */
    public String getCheckedOutDate() {
        return checkedOutDate;
    }
    
    /**
     * Gets the guest Id
     * @return Id of the guest
     */
    public ArrayList<String> getGuestIds() {
        return guestIds;
    }
    
    /**
     * Gets the room Id
     * @return Id of the room
     */
    public String getRoomId() {
        return roomId;
    }
    
    /**
     * Gets the number of pax
     * @return number of people staying in one room
     */
    public int getNumberOfPax() {
        return numberOfPax;
    }
    
    /**
     * Gets the reservation date
     * @return the date which the reservation is made
     */
    public String getReservationDate() {
        return reservationDate;
    }
    /**
     * Gets the reservation status
     * @return status of the reservation
     * {@link ReservationStatus} 
     */
    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }
    
    /**
     * Gets the expire status of reservation
     * @return {@code true} if the reservation is expired. Otherwise, {@code false}
     */
    public boolean getIsExpired() {
        return isExpired;
    }
    
    /**
     * Gets the reservation Id
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
        String guests = "";
        if (getGuestIds().size() != 0) {
            for (String guestId : getGuestIds()) {
                guests += guestId + " ";
            }
        }
        String res = String.format(
                "Reservation Id: %s Guest Id: %s Room Id: %s NumOfPax: %d Check In: %s Check Out: %s Status: %s Is Expired: %b",
                getReservationId(), guests, getRoomId(), getNumberOfPax(), getCheckedInDate(), getCheckedOutDate(),
                getReservationStatus().reservationStatusAsStr, getIsExpired());
        return res;
    }

    /**
     * Override compareTo method to compare different reservation objects according to reservation id
     */
    @Override
    public int compareTo(Reservation reservation) {
        if (this == reservation) {
            return 0;
        }
        int thisReservationId = Integer.parseInt(this.getReservationId().substring(1));
        int thatReservationId = Integer.parseInt(reservation.getReservationId().substring(1));

        return thisReservationId - thatReservationId;
    }
}
