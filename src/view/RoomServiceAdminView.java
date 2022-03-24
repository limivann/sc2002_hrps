package src.view;

import src.controller.RoomManager;
import src.controller.RoomServiceManager;
import src.helper.Helper;
import src.model.enums.OrderStatus;

public class RoomServiceAdminView extends MainView{

    @Override
    public void printMenu() {
        System.out.println("=== Room Service (Admin) View ===");
        System.out.println("Please enter an option (1-3)");
        System.out.println("(1) Customize Menu");
        System.out.println("(2) Manage Orders");
        System.out.println("(3) Exit Room Service (Admin) View");
        
    }

    @Override
    public void viewapp() {
        int option = 99;
        do {
            printMenu();
            option = Helper.readInt();
            switch (option) {
                case 1:
                    customizeMenu();
                    break;
                case 2:
                    manageOrders();
                case 3:
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        } while (option != 3);

    }
    
    /* Customize Menu */

    private void printMenu_customizeMenu() {
        System.out.println("***** CUSTOMIZE MENU *****");
        System.out.println("Please enter an option (1-5)");
        System.out.println("(1) Add menu items");
        System.out.println("(2) Remove menu items");
        System.out.println("(3) Update menu items");
        System.out.println("(4) Print menu items");
        System.out.println("(5) Exit");
    }

    private void customizeMenu() {
        int option = -1;
        String name = "";
        String description = "";
        double price = 0;
        do{
            printMenu_customizeMenu();
            option = Helper.readInt(1, 5);
            switch(option){
                case 1:
                    System.out.println("Enter name of item to be added:\r");
                    name = Helper.sc.nextLine();
                    System.out.printf("Enter description of %s:\n\r", name);
                    description = Helper.sc.nextLine();
                    System.out.printf("Enter price of %s:\n\r", name);
                    price = Helper.readDouble();
                    addMenuItem(name, description, price);
                    break;
                case 2:
                    System.out.println("Enter name of item to be removed:\r");
                    name = Helper.sc.nextLine();
                    removeMenuItem(name);
                    break;
                case 3:
                    System.out.println("Enter name of item to be updated:\r");
                    name = Helper.sc.nextLine();
                    System.out.printf("Enter description of %s:\n\r", name);
                    description = Helper.sc.nextLine();
                    System.out.printf("Enter price of %s:\n\r", name);
                    price = Helper.readDouble();
                    updateMenuItem(name, description, price);
                    break;
                case 4:
                    printMenuItems();
                    break;
                case 5:
                    System.out.println("EXITED EDIT MENU");
                    break;
                default :
                    System.out.println("Invalid option");
                    break;
            }
        }while (option != 5);
    }

    private void printMenuItems(){
        RoomServiceManager.printMenu();
    }

    private void addMenuItem(String name, String description, double price){

        if (RoomServiceManager.addMenuItem(name, description, price)){
            System.out.printf("\"%s\" added to menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Addition to menu FAILED (\"%s\" is already in the menu)\n", name);
        }
    }

    private void removeMenuItem(String name){
        if (RoomServiceManager.removeMenuItem(name)){
            System.out.printf("\"%s\" removed from menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Removal from menu FAILED (\"%s\" NOT FOUND in order\\ removal quantity > current quantity)\n", name);
        }
    }

    private void updateMenuItem(String name, String description, double price) {

        if (RoomServiceManager.updateMenuItem(name, description, price)) {
            System.out.printf("%s updated in menu SUCCESSFULLY\n", name);
        } else {
            System.out.printf("Update menu FAILED (\"%s\" NOT FOUND in menu)\n", name);
        }
    }
    
    // Manage orders
    public void manageOrders() {
        int opt = -1;
        do{
            printManageOrdersMenu();
            opt = Helper.readInt(1, 3);
            switch (opt) {
                case 1:
                    if (!createOrder()) {
                        System.out.println("Create order unsuccessful");
                    }
                    break;
                case 2:
                    if (updateOrderStatus()) {
                        System.out.println("Update order successful");
                    } else {
                        System.out.println("Update order unsuccessful");
                    }
                    break;
                case 3:
                    RoomServiceManager.printAllOrders();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (opt != 3);
        
    }


    public void printManageOrdersMenu() {
        System.out.println("***** MANAGE ORDERS *****");
        System.out.println("Please select an option (1-4)");
        System.out.println("(1) Create an order");
        System.out.println("(2) Update order status");
        System.out.println("(3) Print all orders");
        System.out.println("(4) Exit");
    }

    public boolean updateOrderStatus() {
        System.out.println("Please enter the order id you want to update (OXXXX): ");
        String orderIdToUpdate = Helper.sc.nextLine();
        printOrderStatusMenu();
        int orderStatusOption = Helper.readInt();
        switch (orderStatusOption) {
            case 1:
                return RoomServiceManager.updateStatus(OrderStatus.CONFIRMED, orderIdToUpdate);
            case 2:
                return RoomServiceManager.updateStatus(OrderStatus.PREPARING, orderIdToUpdate);
            case 3:
                return RoomServiceManager.updateStatus(OrderStatus.DELIVERED, orderIdToUpdate);
            default:
                break;
        }
        // TODO: invalid input not 1-3
        return false;
    }

    /* Create Order */
    private void printMenu_createOrder() {
        System.out.println("***** ORDER MENU *****");
        System.out.println("Please enter an option (1-6)");
        System.out.println("(1) Print menu");
        System.out.println("(2) Add menu items");
        System.out.println("(3) Remove menu items");
        System.out.println("(4) Print order");
        System.out.println("(5) Enter remarks");
        System.out.println("(6) Checkout");
    }

    private boolean createOrder() {
        System.out.println("Please enter your room id in this format floor-room (Eg: 01-05):");
        String roomId = Helper.sc.nextLine();
        if (!RoomManager.validateRoomId(roomId)) {
            return false;
        }
        String orderId = RoomServiceManager.createOrder(roomId);
        String itemName;
        int itemAmount;
        int option = -1;
        do{
            printMenu_createOrder();
            System.out.println("Enter option: ");
            option = Helper.readInt(1, 6);
            switch (option){
                case 1:
                    RoomServiceManager.printMenu();
                    break;
                case 2:
                    System.out.println("Enter item to be added:\r");
                    itemName = Helper.sc.nextLine();
                    System.out.println("Enter amount to be added:\r");
                    itemAmount = Helper.sc.nextInt();
                    addOrderItem(itemName, orderId, itemAmount);
                    break;
                case 3:
                    System.out.println("Enter item to be removed:\r");
                    itemName = Helper.sc.nextLine();
                    System.out.println("Enter amount to be removed:\r");
                    itemAmount = Helper.sc.nextInt();
                    removeOrderItem(itemName, orderId, itemAmount);
                    break;
                case 4:
                    RoomServiceManager.printOrder(orderId);
                    break;
                case 5:
                    System.out.println("Enter remarks:\r");
                    String remarks = Helper.sc.nextLine();
                    RoomServiceManager.setRemarks(remarks, orderId);
                    break;
                case 6:
                    confirmOrder(orderId);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (option != 6);
        return true;
    }

    private void addOrderItem(String name, String orderId, int amount){
        if (RoomServiceManager.addOrderItem(name, orderId, amount)){
            System.out.printf("\"%s\" added to order SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Addition to order FAILED (\"%s\" NOT FOUND in menu)\n", name);
        };
    }

    private void removeOrderItem(String name, String orderId, int amount){
        if (RoomServiceManager.removeOrderItem(name, orderId, amount)){
            System.out.printf("\"%s\" removed from order SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Removal from order FAILED (\"%s\" NOT FOUND in order\\ removal quantity > current quantity)\n", name);
        };
    }

    private void confirmOrder(String orderId) {
        System.out.println("RECEIPT:");
        RoomServiceManager.printOrder(orderId);
        System.out.println("Order Sent!!!\nThank you for ordering!!! :):):)");
        RoomServiceManager.updateStatus(OrderStatus.CONFIRMED, orderId);
    }
    
    public void printOrderStatusMenu() {
        System.out.println("Please enter a new status (1-3)");
        System.out.println("(1) Confirmed");
        System.out.println("(2) Perparing");
        System.out.println("(3) Delivered");
    }
    
}
