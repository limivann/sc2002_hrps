package src.model.enums;

public enum OrderStatus {
    CONFIRMED("Confirmed"),
    PREPARING("Preparing"),
    DELIVERED("Delivered");

    public final String orderStatusAsStr;

    private OrderStatus(String orderStatusAsStr) {
        this.orderStatusAsStr = orderStatusAsStr;
    }
}
