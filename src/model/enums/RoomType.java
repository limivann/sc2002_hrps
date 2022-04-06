package src.model.enums;

/**
 * An Enum that corresponds to the different room types.
 * @author Ivan
 * @version 1.0
 * @since 2022-03-30
 */
public enum RoomType {
    /**
     * Room type corresponding to single rooms.
     */
    SINGLE("Single Room"),

    /**
     * Room type corresponding to double rooms.
     */
    DOUBLE("Double Room"),

    /**
     * Room type corresponding to deluxe rooms.
     */
    DELUXE("Deluxe Room"),

    /**
     * Room type corresponding to vip suites.
     */
    VIP_SUITE("Vip suite");

    /**
     * A String value for the room type for retrieving purposes.
     */
    public final String roomTypeAsStr;
    
    /**
     * Constructor for the RoomType Enum.
     * @param roomTypeAsStr Room type as a string.
     * 
     */
    private RoomType(String roomTypeAsStr) {
        this.roomTypeAsStr = roomTypeAsStr;
    }
}
