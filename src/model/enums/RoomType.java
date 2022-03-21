package src.model.enums;

public enum RoomType {
    SINGLE("Single Room"),
    DOUBLE("Double Room"),
    DELUXE("Deluxe Room"),
    VIP_SUITE("Vip suite");

    public final String roomTypeAsStr;

    private RoomType(String roomTypeAsStr) {
        this.roomTypeAsStr = roomTypeAsStr;
    }
}
