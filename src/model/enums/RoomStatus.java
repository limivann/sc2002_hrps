package src.model.enums;

public enum RoomStatus {
    VACANT("Vacant"),
    OCCUPIED("Occupied"),
    RESERVED("Reserved"),
    UNDER_MAINTENANCE("Under maintenance");

    public final String roomStatusAsStr;
    
    private RoomStatus(String roomStatusAsStr) {
        this.roomStatusAsStr = roomStatusAsStr;
    }
}