package src.model;

import java.io.Serializable;

/**
 * MenuItem represents the menu item in the customisable menu. <p>
 * 
 * Each menu item contains a menu item id, the name of the item, the description of the item and the price of the item.
 * @author Hill Seah
 * @version 1.0
 * @since 2022-03-31
 */
public class MenuItem implements Serializable {
    /**
     * For Java Serializable
     */
    private static final long serialVersionUID = 4L;

    /**
     * Id of the menu item
     */
    private String menuItemId;
    /**
     * Name of the menu item
     */
    private String name;
    /**
     * Description of the preparation methods of the menu item
     */
    private String description;
    /**
     * Price of the menu item
     */
    private double price;

    /**
     * Constructs and initialises the Id, name, description and price of the menu item respectively.
     * 
     * @param menuItemId Id of the menu item.
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
     * @return {@code true} if sets successfully
     */
    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    /**
     * Sets the description of the menu item
     * 
     * @param description Description of menu item
     * @return {@code true} if sets successfully
     */
    public boolean setDescription(String description) {
        this.description = description;
        return true;
    }

    /**
     * Sets the price of the menu item
     * 
     * @param price Price of menu item
     * @return {@code true} if sets successfully. Otherwise, {@code false} if failed to set price when a negative number is passed as an argument
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
     * @return {@code true} if sets successfully
     */
    public boolean setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
        return true;
    }

    /**
     * Gets the name of the menu item
     * 
     * @return Mame of the menu item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the menu item
     * 
     * @return Description of the menu item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the price of the menu item
     * 
     * @return Price of the menu item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the Id of the menu item
     * 
     * @return Id of the menu item
     */
    public String getMenuItemId() {
        return menuItemId;
    }
}
