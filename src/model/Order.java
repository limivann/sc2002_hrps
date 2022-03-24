package src.model;

import src.model.enums.OrderStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable {
    
    private static final long serialVersionUID = 5L;

    private String orderId;
    private String dateTime;
    private double totalBill;
    private HashMap<MenuItem, Integer> currentOrders;
    private String remarks;
    private OrderStatus status;
    private String roomId;

    public Order(String orderId, String dateTime, String roomId) {
        this.orderId = orderId;
        this.dateTime = dateTime;
        this.totalBill = 0;
        this.remarks = "No Remarks";
        this.currentOrders = new HashMap<MenuItem, Integer>();
        setRoomId(roomId);
    }
    
    // GETTERS
    public String getOrderId() {
        return this.orderId;
    }

    public String getRoomId() {
        return roomId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getDateTime() {
        return dateTime;
    }

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

    public boolean setRemarks(String remarks) {
        this.remarks = remarks;
        return true;
    }
    
    public boolean setTotalBill(double totalBill) {
        if (totalBill < 0) {
            return false;
        }
        this.totalBill = totalBill;
        return true;
    }

    public void setStatus(OrderStatus currentStatus) {
        status = currentStatus;
    }
    
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public MenuItem findOrderItem(String name) {
        for (MenuItem menuItem : currentOrders.keySet()) {
            if (menuItem.getName().equalsIgnoreCase(name)){
                return menuItem;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        String res = String.format("Order Id: %s\t Date/Time: %s\t Room Id: %s\t Order Status: %s", getOrderId(),
                getDateTime(), getRoomId(), getStatus().orderStatusAsStr);
        return res;
    }

    
}
