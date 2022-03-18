package src;

import java.util.HashMap;
import src.model.enums.*;

public class Order {
    private String orderId;
    private String date;
    private String time;
    private static double totalBill;
    private HashMap<String,MenuItem> currentOrders;
    private String remarks;
    private OrderStatus status;

    public Order(String date, String time){
        this.date = date;
        this.time = time;
        totalBill = 0;
        this.remarks = "No Remarks";
        currentOrders = new HashMap<String, MenuItem>();
    }

    public void addOrderItem(MenuItem menuItem){
        currentOrders.put(menuItem.getName(), menuItem);
        totalBill += menuItem.getPrice();
    }

    public boolean removeOrderItem(MenuItem menuItem){
        String key = menuItem.getName();
        if (currentOrders.containsKey(key)){
            currentOrders.remove(key);
            totalBill -= menuItem.getPrice();
            return true;
        }
        return false;
    }

    public void printOrder(){
        System.out.println("-Order-");
        for (MenuItem menuItem: currentOrders.values()){
            System.out.printf("Item name: %s\tPrice: $%.2f\n",
                    menuItem.getName(), menuItem.getPrice());
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
        if(currentOrders.containsKey(name)){
            return currentOrders.get(name);
        }
        return null;
    }
}
