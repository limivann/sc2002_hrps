package src.controller;
import src.*;
import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Menu;
import src.model.MenuItem;
import src.model.Order;
import src.model.enums.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Iterator;
import javax.xml.crypto.Data;
public class RoomServiceManager {
    private HashMap<String,Order> order;
    private String currId;

    public RoomServiceManager(){
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

    public void printMenu(){
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
