package src.controller;
import src.*;
import src.model.enums.*;
import java.util.HashMap;

public class RoomServiceManager {
    private Menu menu;
    private HashMap<String,Order> order;
    private String currId;

    public RoomServiceManager(){
        menu = new Menu();
        order = new HashMap<String, Order>();
    }

    /* Create Order methods */
    public void createOrder(String date, String time){
        currId = "O" + order.size();
        Order newOrder = new Order(currId , date, time);
        order.put(currId, newOrder);
    }

    public boolean addOrderItem(String name){
        MenuItem target = findMenuItem(name);
        if(target != null){
            order.get(currId).addOrderItem(target);
            return true;
        }
        return false;
    }

    public boolean removeOrderItem(String name){
        return order.get(currId).removeOrderItem(name);
    }

    public void printOrder(){
        order.get(currId).printOrder();
    }

    public void setRemarks(String remarks){
        order.get(currId).setRemarks(remarks);
    }

    public void updateStatus(OrderStatus currentStatus){
        order.get(currId).updateStatus(OrderStatus.CONFIRMED);
    }

    /* Customize Menu methods */
    public boolean addMenuItem(String name, String description, double price){
        return menu.addMenuItem(name, description, price);
    }

    public boolean updateMenuItem(String name, String description, double price){
        return menu.updateMenuItem(name, description, price);
    }
    public boolean removeMenuItem(String name){
        return menu.removeMenuItem(name);
    }

    public void printMenu(){
        menu.printMenu();
    }

    private MenuItem findMenuItem(String name){
        return menu.findItem(name);
    }
    
}
