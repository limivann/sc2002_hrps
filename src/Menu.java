package src;
import java.util.HashMap;
public class Menu {

    private HashMap<String,MenuItem> menu;

    public Menu(){ // default creation, unlike order
        menu = new HashMap<String, MenuItem>();
    }

    public boolean addMenuItem(String name, String description, double price){
        if (!menu.containsKey(name)){
            menu.put(name, new MenuItem(name, description, price));
            return true;
        }
        return false;
    }

    public boolean updateMenuItem(String name, String description, double price){
        if (menu.containsKey(name)){
            menu.replace(name, new MenuItem(name, description, price));
            return true;
        }
        return false;
    }
    public boolean removeMenuItem(String name){
        if (menu.containsKey(name)){
            menu.remove(name);
            return true;
        }
        return false;
    }

    public void printMenu(){
        System.out.println("*** Hotel Menu ***");
        for (MenuItem menuItem: menu.values()){
            System.out.printf("Item name: %s\nDescription: %s\nPrice: $%.2f\n",
                    menuItem.getName(), menuItem.getDescription(), menuItem.getPrice());
        }
    }

    public MenuItem findItem(String name){
        if (menu.containsKey(name)){
            return menu.get(name);
        }
        return null;
    }
    
}
