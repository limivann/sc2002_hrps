package src.model;

import src.model.enums.OrderStatus;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    
    private static final long serialVersionUID = 5L;

    private String orderId;
    private String dateTime;
    private double totalBill;
    private ArrayList<MenuItem> currentOrders;
    private String remarks;
    private OrderStatus status;
    private String roomId;

    public Order(String orderId, String dateTime, String roomId) {
        this.orderId = orderId;
        this.dateTime = dateTime;
        this.totalBill = 0;
        this.remarks = "No Remarks";
        this.currentOrders = new ArrayList<MenuItem>();
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

    public void addOrderItem(MenuItem menuItem){
        currentOrders.add(menuItem);
        totalBill += menuItem.getPrice();
    }

    public boolean removeOrderItem(MenuItem toBeRemoved){
        if (toBeRemoved != null){
            currentOrders.remove(toBeRemoved);
            totalBill -= toBeRemoved.getPrice();
            return true;
        }
        return false;
    }

    public void printOrder(){
        System.out.printf("Order Id: %s  Room: %s  Date/Time: %s\n", getOrderId(), getRoomId() ,dateTime);
        System.out.println("\t\t-Order-\t\t");
        for (int i = 0; i < currentOrders.size(); i++){
            MenuItem menuItem = currentOrders.get(i);
            System.out.printf("Item: %s    Price: $%.2f\n", menuItem.getName(), menuItem.getPrice());
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
        for (int i = 0; i < currentOrders.size(); i++) {
            MenuItem searchedItem = currentOrders.get(i);
            if (searchedItem.getName().equalsIgnoreCase(name)) {
                return searchedItem;
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
