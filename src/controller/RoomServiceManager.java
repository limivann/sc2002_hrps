package src.controller;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.MenuItem;
import src.model.Order;
import src.model.enums.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Iterator;
import javax.xml.crypto.Data;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
public class RoomServiceManager {
    public RoomServiceManager() {
        
    }

    /* Create Order methods */
    public static String createOrder(String roomId) {
        int oid = Helper.generateUniqueId(Database.ORDERS);
        String orderId = String.format("O%04d", oid);
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        // LocalDateTime now = LocalDateTime.now();  
        Order newOrder = new Order(orderId , Helper.getTimeNow(), roomId);
        Database.ORDERS.put(orderId, newOrder);
        return orderId;
    }

    public static boolean addOrderItem(String name, String orderId) {
        if (orderId.equals("")) {
            return false;
        }
        String formattedName = name.toUpperCase();
        String menuIdOfOrder = getMenuIdFromName(formattedName);
        if (menuIdOfOrder.equals("")) {
            // no menu item found
            return false;
        }
        MenuItem menuItemToAdd = Database.MENU_ITEMS.get(menuIdOfOrder);
        Order currentOrder = Database.ORDERS.get(orderId);
        currentOrder.addOrderItem(menuItemToAdd);
        return true;
    }

    public static boolean removeOrderItem(String name, String orderId) {
        if (orderId.equals("")) {
            return false;
        }
        String formattedName = name.toUpperCase();
        String menuIdOfOrder = getMenuIdFromName(formattedName);
        if (menuIdOfOrder.equals("")) {
            // no menu item found
            return false;
        }
        MenuItem menuItemToDelete = Database.MENU_ITEMS.get(menuIdOfOrder);
        Order currentOrder = Database.ORDERS.get(orderId);
        return currentOrder.removeOrderItem(menuItemToDelete);
    }

    public static void printOrder(String orderId){
        Order currentOrder = Database.ORDERS.get(orderId);
        currentOrder.printOrder();
    }

    public static void setRemarks(String remarks, String orderId){
        Order currentOrder = Database.ORDERS.get(orderId);
        currentOrder.setRemarks(remarks);
    }

    public static boolean updateStatus(OrderStatus newOrderStatus, String orderId) {
        if (!validateOrderId(orderId)) {
            // TODO: Exception
            System.out.println("Order id not found");
            return false;
        }
        Order currentOrder = Database.ORDERS.get(orderId);
        currentOrder.setStatus(newOrderStatus);
        Database.saveFileIntoDatabase(FileType.ORDERS);
        return true;
    }
    
    public static boolean validateOrderId(String orderId) {
        return Database.ORDERS.containsKey(orderId);
    }

    public static void printAllOrders() {
        for (Order order : Database.ORDERS.values()) {
            System.out.println(order);
        }
    }

    public static Order searchOrderByRoom(String roomId) {
        for (Order o : Database.ORDERS.values()) {
            if (o.getRoomId().equals(roomId)){
                return o;
            }
        }
        return null;
    }
        
    /* Customize Menu methods */
    public static boolean addMenuItem(String name, String description, double price) {
        String formattedName = name.toUpperCase();
        String menuIdToUpdate = getMenuIdFromName(formattedName);
        if (!menuIdToUpdate.equals("")) {
            // means theres a duplicate 
            return false;
        }
        int mid = Helper.generateUniqueId(Database.MENU_ITEMS);
        String menuItemId = String.format("M%04d", mid);
        MenuItem newMenuItem = new MenuItem(menuItemId, formattedName, description, price);
        Database.MENU_ITEMS.put(menuItemId, newMenuItem);
        Database.saveFileIntoDatabase(FileType.MENU_ITEMS);
        return true;
    }

    public static boolean updateMenuItem(String name, String description, double price){
        String formattedName = name.toUpperCase();
        String menuIdToUpdate = getMenuIdFromName(formattedName);
        if (menuIdToUpdate.equals("")) {
            return false;
        }
        MenuItem menuItemToUpdate = Database.MENU_ITEMS.get(menuIdToUpdate);
        menuItemToUpdate.setName(formattedName);
        menuItemToUpdate.setDescription(description);
        menuItemToUpdate.setPrice(price);
        Database.saveFileIntoDatabase(FileType.MENU_ITEMS);
        return true;
    }

    public static boolean removeMenuItem(String name) {
        String formattedName = name.toUpperCase();
        String menuIdToDelete = getMenuIdFromName(formattedName);
        if (menuIdToDelete.equals("")) {
            // not found
            return false;
        }
        Database.MENU_ITEMS.remove(menuIdToDelete);
        Database.saveFileIntoDatabase(FileType.MENU_ITEMS);
        return true;
    }

    public static String getMenuIdFromName(String name) {
        HashMap<String, MenuItem> toIterate = Helper.copyHashMap(Database.MENU_ITEMS);
        Iterator it = toIterate.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Object currentObject = pair.getValue();
            if (!(currentObject instanceof Object)) {
                // pass
            } else {
                MenuItem currentMenuItem = (MenuItem) currentObject;
                if (currentMenuItem.getName().equals(name)) {
                    return currentMenuItem.getMenuItemId();
                }
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        return "";
    }

    public static void printMenu(){
        System.out.println("*** Hotel Menu ***");
        for (MenuItem menuItem: Database.MENU_ITEMS.values()){
            System.out.printf("Item name: %s\nDescription: %s\nPrice: $%.2f\n",
                    menuItem.getName(), menuItem.getDescription(), menuItem.getPrice());
        }
    }

    private MenuItem findMenuItem(String name){
        String formattedName = name.toUpperCase(Locale.ROOT);
        String menuIdToSearch = getMenuIdFromName(formattedName);
        if (menuIdToSearch.equals("")) {
            return null;
        }
        return Database.MENU_ITEMS.get(menuIdToSearch);
    }
    
}
