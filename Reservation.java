public class Reservation{ 
    enum ReservationStatus {CONFIRMED, IN_WAITLIST, CHECKED_IN, EXPIRED, CHECKED_OUT, CANCELLED};
    private String checkedInDate;
    private String checkedOutDate;
    private String reservationDate;
    private int roomId;
    private int guestId;
    private int reservationId;
    private int numberOfPax;
    private boolean isExpired;
    private ReservationStatus reservationStatus;

    public Reservation(int roomId, int guestId, String checkedInDate, String checkedOutDate, String reservationDate, int numberOfPax, int reservationId){
        reservationStatus = ReservationStatus.CONFIRMED;
        this.checkedInDate = checkedInDate;
        this.checkedOutDate = checkedOutDate;
        this.reservationDate = reservationDate;
        this.roomId = roomId;
        this.guestId = guestId;
        this.numberOfPax = numberOfPax;
        this.reservationId = reservationId;
        this.isExpired = false;
        printReservation();
        System.out.println("Reservation has been created");
    }

    public int getReservationId(){
        return this.reservationId;
    }
    public int getRoomId(){
        return this.roomId;
    }
    public int getGuestId(){
        return this.guestId;
    }
    public void setCheckedInDate(String date){
        this.checkedInDate = date;
    }
    public void setCheckedOutDate(String date){
        this.checkedOutDate = date;
    }
    public void setNumberOfPax(int num){
        this.numberOfPax = num;
    }
    public void setReservationStatus(ReservationStatus status){
        if(status == ReservationStatus.EXPIRED)
            isExpired = true;
        this.reservationStatus = status;
    }
    public void removeReservation(){
        Room room = getRoom(roomId);
        room.setRoomStatus(Room.RoomStatus.VACANT);
        reservationStatus = ReservationStatus.CANCELLED;
    }

    
}


