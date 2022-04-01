package src.view;

import src.controller.RoomManager;
import src.controller.RoomServiceManager;
import src.helper.Helper;
import src.model.enums.OrderStatus;
import src.model.enums.RoomStatus;

/**
 * The order viewing system for the customer <p>
 * Only allows for order creation
 * @author Hill Seah
 * @version 1.0
 * @since 2022-03-31
 */
public class OrderView extends MainView{

    /**
     * Prints the options for the order view menu.
     */
    @Override
    public void printMenu() {
        System.out.println("=== Order View ===");
        System.out.println("Please enter an option (1-2)");
        System.out.println("(1) Create an Order");
        System.out.println("(2) Exit Order View");
    }

    /**
     * Application for the order view system.
     */
    @Override
    public void viewapp() {
        int option = 99;
        do{
            printMenu();
            option = Helper.readInt(1, 2);
            switch(option){
                case 1:
                    if (!createOrder()) {
                        System.out.println("Create order unsuccessful");
                    }
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        } while (option != 2);
        
    }

    /**
     * Prints the option for the create order menu.
     */
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

    /**
     * Application for the create order system.
     * 
     * @return {@code true} if order creation is successful. Otherwise, {@code false} if order creation failed(Room Id does not exist in database) / room is not occupied.
     */
    private boolean createOrder() {
        System.out.println("Please enter your room id in this format floor-room (Eg: 01-05):");
        String roomId = Helper.sc.nextLine();
        if (!RoomManager.validateRoomId(roomId)) {
            System.out.println("Room does not exist");
            return false;
        }
        if (!RoomManager.checkRoomVacancy(roomId, RoomStatus.OCCUPIED)) {
            System.out.println("Room is not occupied!");
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

    /**
     * Adds menu item of the specified quantity to the customer's order.
     * 
     * @param name Name of the menu item to be added
     * @param orderId Id of the order
     * @param amount Quantity of menu item to be added
     */
    private void addOrderItem(String name, String orderId, int amount){
        if (RoomServiceManager.addOrderItem(name, orderId, amount)){
            System.out.printf("\"%s\" added to order SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Addition to order FAILED (\"%s\" NOT FOUND in menu)\n", name);
        };
    }

    /**
     * Removes menu item of the specified quantity from the customer's order.
     * 
     * @param name Name of the menu item to be removed
     * @param orderId Id of the order
     * @param amount Quantity of menu item to be removed
     */
    private void removeOrderItem(String name, String orderId, int amount){
        if (RoomServiceManager.removeOrderItem(name, orderId, amount)){
            System.out.printf("\"%s\" removed from order SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Removal from order FAILED (\"%s\" NOT FOUND in order\\ removal quantity > current quantity)\n", name);
        };
    }

    /**
     * Confirms the finished order and prints the receipt.
     * 
     * @param orderId Id of order
     */
    private void confirmOrder(String orderId) {
        System.out.println("RECEIPT:");
        RoomServiceManager.printOrder(orderId);
        System.out.println("Order Sent!!!\nThank you for ordering!!! :):):)");
        RoomServiceManager.updateStatus(OrderStatus.CONFIRMED, orderId);
    }
}
