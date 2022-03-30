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
    SINGLE("Single Room", 1),

    /**
     * Room type corresponding to double rooms.
     */
    DOUBLE("Double Room", 2),

    /**
     * Room type corresponding to deluxe rooms.
     */
    DELUXE("Deluxe Room", 2),

    /**
     * Room type corresponding to vip suites.
     */
    VIP_SUITE("Vip suite", 4);

    /**
     * A String value for the room type for retrieving purposes.
     */
    public final String roomTypeAsStr;

    /**
     * The maximum capacity of the room.
     */
    public final int maxCapacity;

    /**
     * Constructor for the RoomType Enum.
     * @param roomTypeAsStr Room type as a string.
     * @param maxCapacity Maximum capacity of the room.
     */
    private RoomType(String roomTypeAsStr, int maxCapacity) {
        this.roomTypeAsStr = roomTypeAsStr;
        this.maxCapacity = maxCapacity;
    }
}
