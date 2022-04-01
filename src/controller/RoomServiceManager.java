package src.controller;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.MenuItem;
import src.model.Order;
import src.model.enums.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Iterator;

// for javadocs
import src.view.AdminView;
import src.view.OrderView;
/**
 * RoomServiceManager is a controller class that acts as a "middleman"  
 * between the view classeses - {@link AdminView} and {@link OrderView} and the model classes - {@link MenuItem} and {@link Order}. <p>
 * 
 * It can create an order or customize the menu. <p>
 * Both functionalities are included in the same class {@link RoomServiceManager} to ensure that any changes to the menu will be reflected during
 * order creations (When ordering, the updated menu will be reflected)
 * @author Hill Seah
 * @version 1.0
 * @since 2022-03-31
 * 
 */
public class RoomServiceManager {

    /* Create Order methods */

    /**
     * Creates a new order for the customer of the room Id specified as the argument
     * 
     * @param roomId Room Id of customer ordering
     * @return Order Id of the new order
     */
    public static String createOrder(String roomId) {
        int oid = Helper.generateUniqueId(Database.ORDERS);
        String orderId = String.format("O%04d", oid);
        Order newOrder = new Order(orderId , Helper.getTimeNow(), roomId);
        Database.ORDERS.put(orderId, newOrder);
        return orderId;
    }

    /**
     * Adds menu item of the specified quantity to the customer's order
     * 
     * @param name Name of the menu item to be added
     * @param orderId Id of the order
     * @param amount Quantity of menu item to be added
     * @return {@code true} if menu item is successfully added to the order. Otherwise, {@code false} if menu item fails to be added (order Id is invalid/ menu item name entered does not exist in menu database)
     */
    public static boolean addOrderItem(String name, String orderId, int amount) {
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
        currentOrder.addOrderItem(menuItemToAdd, amount);
        return true;
    }

    /**
     * Removes menu item of the specified quantity from the customer's order
     * 
     * @param name Name of the menu item to be removed
     * @param orderId Id of the order
     * @param amount Quantity of menu item to be removed
     * @return {@code true} if menu item is successfully removed from the order. Otherwise, {@code false} if menu item fails to be removed (order Id is invalid/ menu item name entered does not exist in order or menu database)
     */
    public static boolean removeOrderItem(String name, String orderId, int amount) {
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
        return currentOrder.removeOrderItem(menuItemToDelete, amount);
    }

    /**
     * Prints the order of the specified order Id
     * 
     * @param orderId Order Id of the order
     */
    public static void printOrder(String orderId){
        Order currentOrder = Database.ORDERS.get(orderId);
        currentOrder.printOrder();
    }

    /**
     * Sets the remarks for the order of the specified order Id
     * 
     * @param remarks Remarks for the order
     * @param orderId Order Id of the order
     */
    public static void setRemarks(String remarks, String orderId){
        Order currentOrder = Database.ORDERS.get(orderId);
        currentOrder.setRemarks(remarks);
    }

    /**
     * Update the status of the order of the specified order Id
     * 
     * @param newOrderStatus New order status to be used to update the order status
     * @param orderId Order Id of the order
     * @return {@code true} if updating of order is successfull. Otherwise, {@code false} if id updating of order fails (Order Id is not in database).
     */
    public static boolean updateStatus(OrderStatus newOrderStatus, String orderId) {
        if (!validateOrderId(orderId)) {
            System.out.println("Order id not found");
            return false;
        }
        Order currentOrder = Database.ORDERS.get(orderId);
        currentOrder.setStatus(newOrderStatus);
        Database.saveFileIntoDatabase(FileType.ORDERS);
        return true;
    }

    /**
     * Clears the entire order history of the room after the user has checked out
     * @param roomId Id of the room
     * @return {@code true} if removal of order is successful. Otherwise, {@code false} if room id not found.
     */
    public static boolean removeEntireOrderOfRoom(String roomId) {
        if (!RoomManager.validateRoomId(roomId)) {
            return false;
        }
        ArrayList<Order> orderToClear = searchOrderByRoom(roomId);
        for (Order order : orderToClear) {
            Database.ORDERS.remove(order.getOrderId());    
        }
        return true;
    }
    
    /**
     * Checks if order Id is in the database for validation
     * 
     * @param orderId Order Id of the order
     * @return {@code true} if order Id is found in the database. Otherwise, {@code false} if order Id is not found in database
     */
    public static boolean validateOrderId(String orderId) {
        return Database.ORDERS.containsKey(orderId);
    }

    /**
     * Prints all the orders in the database
     */
    public static void printAllOrders() {
        for (Order order : Database.ORDERS.values()) {
            System.out.println(order);
        }
    }

    /**
     * Searches the orders made by customer residing in room of the specified room Id
     * 
     * @param roomId Room Id of the customer's room
     * @return ArrayList of {@link Order} object of all the orders made by that room.
     */
    public static ArrayList<Order> searchOrderByRoom(String roomId) {
        ArrayList<Order> orders = new ArrayList<Order>();
        for (Order o : Database.ORDERS.values()) {
            if (o.getRoomId().equals(roomId)){
                orders.add(o);
            }
        }
        return orders;
    }
        
    /* Customize Menu methods */

    /**
     * Adds a new menu item, with details of its name, preparation description and price to the menu
     * 
     * @param name Name of the menu item to be added
     * @param description Description of the menu item to be added
     * @param price Price of the menu item to be added
     * @return {@code true} if menu item is added successfully. Otherwise, {@code false} if menu item fails to be added (Duplicated menu item)
     */
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

    /**
     * Updates an existing menu item, with details of its name, preparation description and price to the menu
     * 
     * @param name Name of the menu item to be updated
     * @param description Description of the updated menu item
     * @param price Price of the updated menu item
     * @return {@code true} if menu item is updated successfully. Otherwise, {@code false} if menu item fails to be updated (menu item not found in database)
     */
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

    /**
     * Removes menu item from the menu
     * 
     * @param name Name of the menu item to be removed
     * @return {@code true} if menu item is removed successfully. Otherwise, {@code false} if menu item fails to be removed (menu item not found in database)
     */
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

    /**
     * Gets the menu item id from the specified menu item name.
     * 
     * @param name Name of the menu item
     * @return The menu item id if menu item found. Otherwise, {@code ""} if menu item not found
     */
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

    /**
     * Prints the menu items in the menu with details of each menu item.
     */
    public static void printMenu(){
        System.out.println("*** Hotel Menu ***");
        for (MenuItem menuItem: Database.MENU_ITEMS.values()){
            System.out.printf("Item name: %s\nDescription: %s\nPrice: $%.2f\n",
                    menuItem.getName(), menuItem.getDescription(), menuItem.getPrice());
        }
    }
}
