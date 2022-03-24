package src.model.enums;

public enum RoomType {
    SINGLE("Single Room", 1),
    DOUBLE("Double Room", 2),
    DELUXE("Deluxe Room", 2),
    VIP_SUITE("Vip suite", 4);

    public final String roomTypeAsStr;
    public final int maxCapacity;

    private RoomType(String roomTypeAsStr, int maxCapacity) {
        this.roomTypeAsStr = roomTypeAsStr;
        this.maxCapacity = maxCapacity;
    }
}
