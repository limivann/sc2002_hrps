package src.model;

import src.model.enums.OrderStatus;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Order represents the room servce order made either by the customer/hotel personnel. <p>
 * Each order contains an order Id, date and time, total bill, list of menu items ordered, special remarks, order status and room Id of the customer. <p>
 * @author Hill Seah
 * @version 1.0
 * @since 2022-03-31
 */
public class Order implements Serializable, Comparable<Order> {
    /**
     * For Java Serializable.
     */
    private static final long serialVersionUID = 5L;

    /**
     * Id of the order.
     */
    private String orderId;
    /**
     * Date and time of the order.
     */
    private String dateTime;
    /**
     * Total bill of the order.
     */
    private double totalBill;

    /**
     * Current Orders in HashMap with {@link MenuItem}: quantity.
     */
    private HashMap<MenuItem, Integer> currentOrders;
    /**
     * Special remarks of the order.
     */
    private String remarks;
    /**
     * Order status of the order. <p>
     * See {@link OrderStatus} for different types of order status.
     */
    private OrderStatus status;

    /**
     * Room id of the order.
     */
    private String roomId;

    /**
     * Constructs and initialises the order Id, date and time of order and customer's Id respectively
     * 
     * @param orderId Id of the order
     * @param dateTime Date and time of the order
     * @param roomId Id of the customer's room
     */
    public Order(String orderId, String dateTime, String roomId) {
        this.orderId = orderId;
        this.dateTime = dateTime;
        this.totalBill = 0;
        this.remarks = "No Remarks";
        this.currentOrders = new HashMap<MenuItem, Integer>();
        setRoomId(roomId);
    }
    
    /**
     * Gets the Id of the order
     * 
     * @return Id of the order
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * Gets the Id of the customer's room
     * 
     * @return Id of the customer's room
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Gets the order status
     * 
     * @return {@link OrderStatus} object 
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Gets the date and time of the order
     * 
     * @return Date and time of order
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Gets the total bill of the order
     * 
     * @return Total bill of the order
     */
    public double getTotalBill() {
        return totalBill;
    }

    /**
     * Get the special remarks of the order.
     * @return The special remarks of the order.
     */
    public String getRemarks() {
        return remarks;
    }


    /**
     * Adds a menu item and the specified quantity desired to be added to the current order.
     * At the same time, this method adds the prices of the menu items added to the total bill.
     * 
     * @param menuItem Menu item to be added to order
     * @param amount Number of menu item to be added to order
     */
    public void addOrderItem(MenuItem menuItem, int amount){
        if (currentOrders.containsKey(menuItem)){
            int currAmount = currentOrders.get(menuItem);
            currentOrders.put(menuItem, currAmount + amount);
        }
        else{
            currentOrders.put(menuItem, amount);
        }
        totalBill += (menuItem.getPrice() * amount);
    }

    /**
     * Removes a menu item and the specified quantity desired to be removed from the current order.
     * At the same time, this method subtracts the prices of menu items removed from total bill.
     * 
     * @param toBeRemoved Menu item to be removed from the order
     * @param amount Number of menu item to be removed from the oder
     * @return {@code true} if removal is successful. Otherwise, {@code false} if removal failed (Amount entered to be removed is more than current amount in order)
     */
    public boolean removeOrderItem(MenuItem toBeRemoved, int amount) {

        int currAmount = currentOrders.get(toBeRemoved);
        if (amount <= currAmount) {
            if (amount == currAmount) {
                currentOrders.remove(toBeRemoved);
            } else {
                currentOrders.put(toBeRemoved, currAmount - amount);
            }
            totalBill -= (toBeRemoved.getPrice() * amount);
            return true;
        } else {
            System.out.println("");
            return false;
        }
    }
    
    public HashMap<MenuItem, Integer> getCurrentOrders() {
        return currentOrders;
    }


    /**
     * Sets remarks for the order
     * 
     * @param remarks Remarks for the order
     * @return {@code true} if sets successfully.
     */
    public boolean setRemarks(String remarks) {
        this.remarks = remarks;
        return true;
    }
    
    /**
     * Sets total bill of the order (used only by admin)
     * 
     * @param totalBill total bill of the order
     * @return {@code true} if sets successfully. Otherwise, {@code false} if the total bill is negative.
     */
    public boolean setTotalBill(double totalBill) {
        if (totalBill < 0) {
            return false;
        }
        this.totalBill = totalBill;
        return true;
    }

    /**
     * Sets status of the order (used only by admin)
     * 
     * @param currentStatus {@link OrderStatus} of the order
     * @return {@code true} if sets successfully.
     */
    public boolean setStatus(OrderStatus currentStatus) {
        status = currentStatus;
        return true;
    }
    
    /**
     * Sets room Id of the customer (used only by admin)
     * 
     * @param roomId status of the room Id of the customer
     * @return {@code true} if sets successfully.
     */
    public boolean setRoomId(String roomId) {
        this.roomId = roomId;
        return true;
    }

    /**
     * Use the name of a menu item to check if the menu item is currently in the current orders
     * 
     * @param name name of the menu item to find
     * @return {@link MenuItem} object if the menu item is found. Otherwise, {@code null}.
     */
    public MenuItem findOrderItem(String name) {
        for (MenuItem menuItem : currentOrders.keySet()) {
            if (menuItem.getName().equalsIgnoreCase(name)){
                return menuItem;
            }
        }
        return null;
    }
    
    /**
     * Translate the order object and its fields to a string
     */
    @Override
    public String toString() {
        String res = String.format("Order Id: %s\tRoom Id: %s\t\tDate/Time: %s\t\tOrder Status: %s", getOrderId(),
                getRoomId(), getDateTime(), getStatus().orderStatusAsStr);
        
        return res;
    }

    /**
     * Override compareTo method to compare different Order objects according to order id
     */
    @Override
    public int compareTo(Order order) {
        if (this == order) {
            return 0;
        }
        int thisOrderId = Integer.parseInt(this.getOrderId().substring(1));
        int thatOrderId = Integer.parseInt(order.getOrderId().substring(1));

        return thisOrderId - thatOrderId;
    }
    
}
