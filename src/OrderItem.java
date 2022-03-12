package src;

public class OrderItem extends MenuItem {
    enum OrderStatus {
        CONFIRMED, PREPARING, DELIVERED
    };

    private OrderStatus orderStatus;
    private String remarks;

    public OrderItem(String itemName, double itemPrice, String itemDescription) {
        super(itemName, itemDescription, itemPrice);
        this.orderStatus = OrderStatus.CONFIRMED;
    }
    
    public OrderItem(String itemName, double itemPrice, String itemDescription, String remarks) {
        super(itemName, itemDescription, itemPrice);
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