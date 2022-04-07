package src.controller;
import src.database.Database;
import src.database.FileType;
import src.model.MenuItem;
import src.helper.Helper;

// for javadocs
import src.view.MenuView;
/**
 * MenuManager is a controller class that acts as a "middleman"
 * between the view class - {@link MenuView} and the model classes - {@link MenuItem}. <p>
 *
 * It can customize the menu. <p>
 * @author Hill Seah
 * @version 1.0
 * @since 2022-03-31
 *
 */
public class MenuManager {
    /**
     * Default constructor for Menu Manager
     */
    public MenuManager() {
        
    }
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
     * @param name Name of the menu item
     * @return The menu item id if menu item found. Otherwise, {@code ""} if menu item not found
     */
    public static String getMenuIdFromName(String name) {
        for (MenuItem currentMenuItem : Database.MENU_ITEMS.values()) {
            if (currentMenuItem.getName().equals(name)) {
                return currentMenuItem.getMenuItemId();
            }
        }
        return "";
    }

    /**
     * Initializer for dummy menu items in the hotel. 
     */
    public static void initializeDummyMenuItems() {
        addMenuItem("Mee Goreng",
                "chicken, wok-fried yellow noodles, spicy shrimp paste, egg, chye sim", 24);
        addMenuItem("Yang Chow Fried Rice",
                "Chinese sausage, barbecue pork, shrimp, fried egg", 24);
        addMenuItem("Singapore Laksa Lemak",
                "rice noodles, quail egg, bean curd, fish cake, spicy coconut gravy with prawns", 25);
        addMenuItem("Chicken Curry", "coconut gravy, achar, steamed rice", 26);
        addMenuItem("Char Kway Teow",
                "wok-fried rice & egg noodles, prawns, Chinese sausage, squid, fish cake, bean sprouts, black soy sauce",
                26);
        addMenuItem("Hokkien Mee", "rice & egg noodles, prawns, squid, pork belly, bean sprouts",
                26);
        addMenuItem("Hainanese Chicken Rice", "chicken broth, ginger, chilli, dark soy sauce", 26);
        addMenuItem("Nasi Goreng",
                "Indonesian-style fried rice, sunny side-up egg, achar, chicken satay, chicken drumstick, peanut sauce, prawn crackers",
                28);
    }

    /**
     * Prints the menu items in the menu with details of each menu item.
     */
    public static void printMenu() {
        for (MenuItem menuItem : Database.MENU_ITEMS.values()) {
            System.out.println("Item name: " + menuItem.getName());
            System.out.println("Description: " + menuItem.getDescription());
            System.out.println(String.format("Price: $%.2f", menuItem.getPrice()));
        }
    }

}
