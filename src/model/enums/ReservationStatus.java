package src.model.enums;

/**
 * An Enum that corresponds to the different type of reservation status of the reservation.
 * @author Ivan
 * @version 1.0
 * @since 2022-03-30
 */
public enum ReservationStatus {
    /**
     * Reservation status corresponding to "confirmed".
     */
    CONFIRMED("Confirmed"),

    /**
     * Reservation status corresponding to "in waitlist".
     */
    IN_WAITLIST("In Waitlist"),

    /**
     * Reservation status corresponding to "checked in".
     */
    CHECKED_IN("Checked In"),

    /**
     * Reservation status corresponding to "checked out".
     */
    CHECKED_OUT("Checked Out"),

    /**
     * Reservation status corresponding to "expired".
     */
    EXPIRED("Expired"),

    /**
     * Reservation status corresponding to "cancelled".
     */
    CANCELLED("Cancelled");

    /**
     * A String value for the reservation status for retrieving purposes.
     */
    public final String reservationStatusAsStr;

    /**
     * Constructor for the ReservationStatus Enum.
     * @param reservationStatusAsStr Reservation Status as a string
     */
    private ReservationStatus(String reservationStatusAsStr) {
        this.reservationStatusAsStr = reservationStatusAsStr;
    }
}
