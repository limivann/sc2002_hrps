package src.model.enums;

/**
 * An Enum that corresponds to the different type of room status of the room.
 * @author Ivan
 * @version 1.0
 * @since 2022-03-30
 */
public enum RoomStatus {
    /**
     * Room status corresponding to "vacant".
     */
    VACANT("Vacant"),

    /**
     * Room status corresponding to "occupied".
     */
    OCCUPIED("Occupied"),

    /**
     * Room status corresponding to "reserved".
     */
    RESERVED("Reserved"),

    /**
     * Room status corresponding to "under maintenance".
     */
    UNDER_MAINTENANCE("Under maintenance");

    /**
     * A String value for the room status for retrieving purposes.
     */
    public final String roomStatusAsStr;
    
    /**
     * Constructor for the RoomStatus Enum.
     * @param roomStatusAsStr Room Status as a string
     */
    private RoomStatus(String roomStatusAsStr) {
        this.roomStatusAsStr = roomStatusAsStr;
    }
}