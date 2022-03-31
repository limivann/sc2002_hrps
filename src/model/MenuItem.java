package src.model;

import java.io.Serializable;

/**
 * MenuItem represents the menu item in the customisable menu
 * 
 * Each menu item contains a menu item id, the name of the item, the description of the item and the price of the item
 */
public class MenuItem implements Serializable {
    private static final long serialVersionUID = 4L;

    private String menuItemId;
    private String name;
    private String description;
    private double price;

    /**
     * Constructs and initialises the Id, name, description and price of the menu item respectively
     * 
     * @param menuItemId Id of the menu item
     * @param name Name of the menu item
     * @param description Description of the preparation methods of the menu item
     * @param price Price of the menu item
     */
    public MenuItem(String menuItemId, String name, String description, double price) {
        setMenuItemId(menuItemId);
        setName(name);
        setDescription(description);
        setPrice(price);
    }
    
    /**
     * Sets the name of the menu item
     * 
     * @param name Name of menu item
     * @return returns true after setting the name successfully
     */
    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    /**
     * Sets the description of the menu item
     * 
     * @param description Description of menu item
     * @return returns true after setting the description successfully
     */
    public boolean setDescription(String description) {
        this.description = description;
        return true;
    }

    /**
     * Sets the price of the menu item
     * 
     * @param price Price of menu item
     * @return returns true after setting the price successfully, returns false if failed to set price when a negative number is passed as an argument
     */
    public boolean setPrice(double price) {
        if (price < 0) {
            return false;
        }
        this.price = price;
        return true;
    }

    /**
     * Sets the Id of the menu item
     * 
     * @param menuItemId Id of the menu item
     * @return returns true after setting the Id successfully
     */
    public boolean setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
        return true;
    }

    /**
     * Gets the name of the menu item
     * 
     * @return returns name of the menu item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the menu item
     * 
     * @return returns description of the menu item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the price of the menu item
     * 
     * @return returns price of the menu item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the Id of the menu item
     * 
     * @return returns Id of the menu item
     */
    public String getMenuItemId() {
        return menuItemId;
    }
}
