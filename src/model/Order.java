package src.model;

import java.util.HashMap;

import src.model.enums.*;
import java.util.ArrayList;

public class Order {
    private String orderId;
    private String date;
    private String time;
    private static double totalBill;
    private ArrayList<MenuItem> currentOrders;
    private String remarks;
    private OrderStatus status;

    public Order(String orderId ,String date, String time){
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        totalBill = 0;
        this.remarks = "No Remarks";
        this.currentOrders = new ArrayList<MenuItem>();
    }

    public void addOrderItem(MenuItem menuItem){
        currentOrders.add(menuItem);
        totalBill += menuItem.getPrice();
    }

    public boolean removeOrderItem(String name){
        MenuItem toBeRemoved = findOrderItem(name);
        if (toBeRemoved != null){
            currentOrders.remove(toBeRemoved);
            totalBill -= toBeRemoved.getPrice();
            return true;
        }
        return false;
    }

    public void printOrder(){

        System.out.printf("Order Id: %s    Date: %s    Time: %s\n", orderId, date, time);
        System.out.println("\t-Order-\t");
        for (int i = 0; i < currentOrders.size(); i++){
            MenuItem menuItem = currentOrders.get(i);
            System.out.printf("Item: %s    Price: $%.2f\n", menuItem.getName(), menuItem.getPrice());
        }
        System.out.println("Remarks: " + this.remarks);
        System.out.printf("Total bill: $%.2f\n", totalBill);
    }

    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public void updateStatus(OrderStatus currentStatus){
        status = currentStatus;
    }

    public MenuItem findOrderItem(String name){

        for (int i = 0; i < currentOrders.size(); i++){
            MenuItem searchedItem = currentOrders.get(i);
            if (searchedItem.getName().equalsIgnoreCase(name)){
                return searchedItem;
            }
        }
        return null;
    }

    public String getOrderId() {
        return this.orderId;
    }
}
