package src.controller;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.MenuItem;
import src.model.Order;
import src.model.enums.OrderStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

// for javadocs
import src.view.OrderView;

/**
 * OrderManager is a controller class that acts as a "middleman"
 * between the view classes -  {@link OrderView} and the model classes - {@link Order}. <p>
 *
 * @author Hill Seah, Max
 * @version 1.0
 * @since 2022-04-06
 *
 */
public class OrderManager {
    /**
     * Creates a new order for the customer of the room Id specified as the argument. <p>
     * Calls {@link RoomManager} to update room orders
     * @param roomId Room Id of customer ordering
     * @return Order Id of the new order
     */
    public static String createOrder(String roomId) {
        int oid = Helper.generateUniqueId(Database.ORDERS);
        String orderId = String.format("O%04d", oid);
        Order newOrder = new Order(orderId, Helper.getTimeNow(), roomId);
        
        // update room's order
        RoomManager.updateRoomOrders(roomId, newOrder, false);

        Database.ORDERS.put(orderId, newOrder);
        return orderId;
    }

    /**
     * Adds menu item of the specified quantity to the customer's order
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
        String menuIdOfOrder = MenuManager.getMenuIdFromName(formattedName);
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
        String menuIdOfOrder = MenuManager.getMenuIdFromName(formattedName);
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
     * @param orderId Order Id of the order
     */
    public static void printOrderDetails(String orderId){
        Order currentOrder = Database.ORDERS.get(orderId);
        System.out.println(String.format("%-58s", "").replace(" ", "-"));
        System.out.printf("Order Id: %s  Room: %s  Date/Time: %s\n", currentOrder.getOrderId(),
                currentOrder.getRoomId(), currentOrder.getDateTime());
        System.out.println();
        System.out.println(String.format("%-30s %10s %15s", "Item", "Qty", "Price"));
        System.out.println(String.format("%-58s", "").replace(" ", "─"));
        for (Map.Entry<MenuItem, Integer> entry : currentOrder.getCurrentOrders().entrySet()) {
            MenuItem key = entry.getKey();
            Integer value = entry.getValue();
            System.out.printf("%-30s %10d %12s$%.0f\n", key.getName().toString(), value, "", value * key.getPrice());
        }
        System.out.println();
        System.out.println();
        System.out.println(String.format("%-11s: %s", "Remarks", currentOrder.getRemarks()));
        System.out.println(String.format("%-11s: $%.2f", "Total bill", currentOrder.getTotalBill()));
        System.out.println(String.format("%-58s", "").replace(" ", "-"));
    }
    
    /**
     * Sets the remarks for the order of the specified order Id
     * @param remarks Remarks for the order
     * @param orderId Order Id of the order
     */
    public static void setRemarks(String remarks, String orderId){
        Order currentOrder = Database.ORDERS.get(orderId);
        currentOrder.setRemarks(remarks);
    }

    /**
     * Update the status of the order of the specified order Id
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
     * Update all room order to delivered after the user has checked out <p>
     * Calls {@link RoomManager} to validate room id.
     * @param roomId Id of the room
     * @return {@code true} if update of room orders is successful. Otherwise, {@code false} if room id not found.
     */
    public static boolean updateAllRoomOrderToDelivered(String roomId) {
        if (!RoomManager.validateRoomId(roomId)) {
            return false;
        }
        ArrayList<Order> orderToUpdate = searchOrderByRoom(roomId);
        for (Order order : orderToUpdate) {
            if (order.getStatus() == OrderStatus.CONFIRMED || order.getStatus() == OrderStatus.PREPARING){
                updateStatus(OrderStatus.DELIVERED, order.getOrderId());    
            }
        }
        return true;
    }
    
    /**
     * Checks if order Id is in the database for validation
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
        ArrayList<Order> sortedList = new ArrayList<Order>();

        // copy
        for (Order order : Database.ORDERS.values()) {
            sortedList.add(order);
        }
        Collections.sort(sortedList);
        for (Order order : sortedList) {
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
        for (Order order : Database.ORDERS.values()) {
            if (order.getRoomId().equals(roomId)){
                orders.add(order);
            }
        }
        return orders;
    }

    /**
     * Method to calculate the total order price of a room
     * @param roomId Id of the room to calculate
     * @return total order price of the room.
     */
    public static double calculateTotalOrderPrice(String roomId) {
        ArrayList<Order> orders = RoomManager.searchRoom(roomId).getOrders();
        if (orders == null) {
            return 0;
        }
        double total = 0;
        for (Order order : orders) {
            total += order.getTotalBill();
        }
        return total;
    }
        
}
