package src.model;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private static final long serialVersionUID = 4L;
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public boolean setDescription(String description) {
        this.description = description;
        return true;
    }

    public boolean setPrice(double price) {
        if (price < 0) {
            return false;
        }
        this.price = price;
        return true;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
