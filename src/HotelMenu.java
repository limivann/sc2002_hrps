package src;

public class HotelMenu {
    private String name;
    private double price;
    private String description;

    public HotelMenu(String newName, double newPrice, String newDescription) {
        this.name = newName;
        this.price = newPrice;
        this.description = newDescription;
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