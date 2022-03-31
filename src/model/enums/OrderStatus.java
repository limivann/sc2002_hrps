package src.model.enums;

/**
 * An Enum that corresponds to the different type of order status of the order.
 * @author Ivan
 * @version 1.0
 * @since 2022-03-30
 */
public enum OrderStatus {
    /**
     * Order status corresponding to "confirmed".
     */
    CONFIRMED("Confirmed"),

    /**
     * Order status corresponding to "preparing".
     */
    PREPARING("Preparing"),

    /**
     * Order status corresponding to "delivered".
     */
    DELIVERED("Delivered");

    /**
     * A String value for the order status for retrieving purposes.
     */
    public final String orderStatusAsStr;

    /**
     * Constructor for the OrderStatus Enum.
     * @param orderStatusAsStr Order Status as a string
     */
    private OrderStatus(String orderStatusAsStr) {
        this.orderStatusAsStr = orderStatusAsStr;
    }
}
