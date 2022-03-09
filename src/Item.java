package src;

public class Item extends HotelMenu {
    enum OrderStatus {
        CONFIRMED, PREPARING, DELIVERED
    };

    private OrderStatus orderStatus;
    private String remarks;

    public Item(String itemName, double itemPrice, String itemDescription) {
        super(itemName, itemPrice, itemDescription);
        this.orderStatus = OrderStatus.CONFIRMED;
    }
    
    public Item(String itemName, double itemPrice, String itemDescription, String remarks) {
        super(itemName, itemPrice, itemDescription);
        this.orderStatus = OrderStatus.CONFIRMED;
        this.remarks = remarks;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}