package src.model.enums;

public enum ReservationStatus {
    CONFIRMED("Confirmed"),
    IN_WAITLIST("In Waitlist"),
    CHECKED_IN("Checked In"),
    EXPIRED("Expired"),
    CHECKED_OUT("Checked Out"),
    CANCELLED("Cancelled");

    public final String reservationStatusAsStr;

    private ReservationStatus(String reservationStatusAsStr) {
        this.reservationStatusAsStr = reservationStatusAsStr;
    }
}
