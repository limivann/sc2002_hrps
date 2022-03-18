package src.controller;
import src.*;
import src.model.enums.*;

public class RoomServiceManager {
    private Menu menu;
    private Order order;

    public RoomServiceManager(){ // created by default, unlike order. Menu is necessary
        menu = new Menu();
    }

    /* Create Order methods */
    public void createOrder(String date, String time){
        this.order = new Order(date, time);
    }

    public boolean addOrderItem(String name){
        MenuItem target = findMenuItem(name);
        if(target != null){
            order.addOrderItem(target);
            return true;
        }
        return false;
    }

    public boolean removeOrderItem(String name){
        MenuItem target = findOrderItem(name);
        if (target != null){
            order.removeOrderItem(target);
            return true;
        }
        return false;
    }

    public void printOrder(){
        order.printOrder();
    }

    public void setRemarks(String remarks){
        order.setRemarks(remarks);
    }

    public void updateStatus(OrderStatus currentStatus){
        order.updateStatus(OrderStatus.CONFIRMED);
    }

    private MenuItem findOrderItem(String name){
        return order.findOrderItem(name);
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
