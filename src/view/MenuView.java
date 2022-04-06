package src.view;

import src.controller.MenuManager;
import src.helper.Helper;

public class MenuView extends MainView{

    /**
     * Prints the menu for customizing the hotel menu
     */
    @Override
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Menu View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Add menu items");
        System.out.println("(2) Remove menu items");
        System.out.println("(3) Update menu items");
        System.out.println("(4) Print menu items");
        System.out.println("(5) Exit");
    }

    /**
     * Application for the customize menu system
     */
    @Override
    public void viewapp() {
        int opt = -1;
        String name = "";
        String description = "";
        double price = 0;
        do {
            printMenu();
            opt = Helper.readInt(1, 5);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Add menu items");
                    System.out.println("Enter name of item to be added:\r");
                    name = Helper.sc.nextLine();
                    System.out.printf("Enter description of %s:\n\r", name);
                    description = Helper.sc.nextLine();
                    System.out.printf("Enter price of %s:\n\r", name);
                    price = Helper.readDouble();
                    addMenuItem(name, description, price);
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Remove menu items");
                    System.out.println("Enter name of item to be removed:\r");
                    name = Helper.sc.nextLine();
                    removeMenuItem(name);
                    break;
                case 3:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Update menu items");
                    System.out.println("Enter name of item to be updated:\r");
                    name = Helper.sc.nextLine();
                    System.out.printf("Enter description of %s:\n\r", name);
                    description = Helper.sc.nextLine();
                    System.out.printf("Enter price of %s:\n\r", name);
                    price = Helper.readDouble();
                    updateMenuItem(name, description, price);
                    break;
                case 4:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Menu View > Print menu items");
                    printMenuItems();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            if (opt != 5) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 5);
    }
    /**
     * Prints the available menu items in the hotel menu
     */
    private void printMenuItems(){
        MenuManager.printMenu();
    }

    /**
     * Adds a new menu item, with details of its name, preparation description and price to the menu
     * Prints addition success message/addition failure message
     * 
     * @param name Name of the menu item to be added
     * @param description Description of the menu item to be added
     * @param price Price of the menu item to be added
     */
    private void addMenuItem(String name, String description, double price){

        if (MenuManager.addMenuItem(name, description, price)){
            System.out.printf("\"%s\" added to menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Addition to menu FAILED (\"%s\" is already in the menu)\n", name);
        }
    }

    /**
     * Removes menu item from the menu
     * Prints removal success message/ removal failure message
     * 
     * @param name Name of the menu item to be removed
     */
    private void removeMenuItem(String name){
        if (MenuManager.removeMenuItem(name)){
            System.out.printf("\"%s\" removed from menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Removal from menu FAILED (\"%s\" NOT FOUND in order\\ removal quantity > current quantity)\n", name);
        }
    }

    /**
     * Updates an existing menu item, with details of its name, preparation description and price to the menu
     * Prints update success message/ update failure message
     * 
     * @param name Name of the menu item to be updated
     * @param description Description of the updated menu item
     * @param price Price of the updated menu item
     */
    private void updateMenuItem(String name, String description, double price) {

        if (MenuManager.updateMenuItem(name, description, price)) {
            System.out.printf("%s updated in menu SUCCESSFULLY\n", name);
        } else {
            System.out.printf("Update menu FAILED (\"%s\" NOT FOUND in menu)\n", name);
        }
    }
    
}
