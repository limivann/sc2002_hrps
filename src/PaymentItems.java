package src;

public class PaymentItems {
    private MenuItem menuItem;
    private int quantity;
    private double totalPrice;

    public PaymentItems(MenuItem menuItem, int quantity, double totalPrice) {
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
}
