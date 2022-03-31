package src.model;

import src.model.enums.OrderStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * Order represents the room servce order made either by the customer/hotel personnel
 * 
 * Each order contains an order Id, date and time, total bill, list of menu items ordered, special remarks, order status and room Id of the customer
 */
public class Order implements Serializable {
    
    private static final long serialVersionUID = 5L;

    private String orderId;
    private String dateTime;
    private double totalBill;
    private HashMap<MenuItem, Integer> currentOrders;
    private String remarks;
    private OrderStatus status;
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
     * @return returns Id of the order
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * Gets the Id of the customer's room
     * 
     * @return returns Id of the customer's room
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Gets the order status
     * 
     * @return returns order status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Gets the date and time of the order
     * 
     * @return returns date and time of order
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Gets the total bill of the order
     * 
     * @return returns total bill of the order
     */
    public double getTotalBill() {
        return totalBill;
    }

    /**
     * Adds a menu item and the specified quantity desired to be added to the current order.
     * Adds the prices of the menu items added to the total bill.
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
     * Subtracts the prices of menu items removed from total bill.
     * 
     * @param toBeRemoved Menu item to be removed from the order
     * @param amount Number of menu item to be removed from the oder
     * @return returns true if removal is successful/ returns false if removal failed (Amount entered to be removed is more than current amount in order)
     */
    public boolean removeOrderItem(MenuItem toBeRemoved, int amount){

        int currAmount = currentOrders.get(toBeRemoved);
        if (amount <= currAmount){
            if (amount == currAmount){
                currentOrders.remove(toBeRemoved);
            }
            else{
                currentOrders.put(toBeRemoved, currAmount - amount);
            }
            totalBill -= (toBeRemoved.getPrice() * amount);
            return true;
        }
        else {
            System.out.println("");
            return false;
        }
    }
    
    /**
     * Prints the order of the customer
     * Contents of the order includes: 
     * 1. Order Id
     * 2. Room Id
     * 3. Date and time of Order
     * 4. Menu items and the quantity of each Menu item
     * 5. Remarks for the order
     * 6. Total bill of the order
     */
    public void printOrder(){
        System.out.printf("Order Id: %s  Room: %s  Date/Time: %s\n", getOrderId(), getRoomId() ,dateTime);
        System.out.println("\t\t-Order-\t\t");
        for (Map.Entry<MenuItem, Integer> entry : currentOrders.entrySet()) {
            MenuItem key = entry.getKey();
            Integer value = entry.getValue();
            System.out.printf("Item: %s  x%d  Price: $%.2f\n", key.getName(), value, value * key.getPrice());
        }
        System.out.println("Remarks: " + this.remarks);
        System.out.printf("Total bill: $%.2f\n", this.totalBill);
    }

    /**
     * Sets remarks for the order
     * 
     * @param remarks Remarks for the order
     * @return returns true if setting of remarks for the order is successfull
     */
    public boolean setRemarks(String remarks) {
        this.remarks = remarks;
        return true;
    }
    
    /**
     * Sets total bill of the order (used only by admin)
     * 
     * @param totalBill total bill of the order
     * @return returns true if setting of total bill of the order is successfull
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
     * @param currentStatus status of the order
     */
    public void setStatus(OrderStatus currentStatus) {
        status = currentStatus;
    }
    
    /**
     * Sets room Id of the customer (used only by admin)
     * 
     * @param currentStatus status of the room Id of the customer
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * Use the name of a menu item to check if the menu item is currently in the current orders
     * 
     * @param name name of the menu item to find
     * @return returns the object menu item if the menu item is found
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
        String res = String.format("Order Id: %s\t Date/Time: %s\t Room Id: %s\t Order Status: %s", getOrderId(),
                getDateTime(), getRoomId(), getStatus().orderStatusAsStr);
        return res;
    }

    
}
