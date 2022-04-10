package src.view;

import src.controller.MenuManager;
import src.controller.OrderManager;
import src.controller.RoomManager;
import src.helper.Helper;
import src.model.enums.OrderStatus;
import src.model.enums.RoomStatus;

// for javadocs
import src.model.Order;
/**
 * OrderView provides the view to take user input which calls {@link OrderManager} to manage {@link Order}.
 * @author Max
 * @version 1.0
 * @since 2022-04-06
 */
public class OrderView extends MainView {
    /**
     * Default constructor for order view
     */
    public OrderView() {
        
    }
    /**
     * View Menu of the OrderView.
     */
    @Override
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Order View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Create an order");
        System.out.println("(2) Update order status");
        System.out.println("(3) Print all orders");
        System.out.println("(4) Exit");
    }
    /**
     * View Application of the OrderView. <p>
     * See {@link OrderManager} for more {@link Order} management details.
     */
    @Override
    public void viewApp() {
        int opt = -1;
        do {
            printMenu();
            opt = Helper.readInt(1, 4);
            switch (opt) {
                case 1:
                    if (!createOrder()) {
                        System.out.println("Create order unsuccessful");
                    }
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Order View > Update order status");
                    if (updateOrderStatus()) {
                        System.out.println("Update order successful");
                    } else {
                        System.out.println("Update order unsuccessful");
                    }
                    break;
                case 3:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Order View > Print all orders");
                    OrderManager.printAllOrders();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid input");
            }
            if (opt != 4) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 4);
    }

    /**
     * Update the order status of a chosen order
     * 
     * @return {@code true} if order is successfully updated. Otherwise, {@code false} if order is failed to be updated (Order id does not exist in database)
     */
    private boolean updateOrderStatus() {
        System.out.println("Please enter the order id you want to update (OXXXX): ");
        String orderIdToUpdate = Helper.readString();
        printOrderStatusMenu();
        int orderStatusOption = Helper.readInt(1,3);
        switch (orderStatusOption) {
            case 1:
                return OrderManager.updateStatus(OrderStatus.CONFIRMED, orderIdToUpdate);
            case 2:
                return OrderManager.updateStatus(OrderStatus.PREPARING, orderIdToUpdate);
            case 3:
                return OrderManager.updateStatus(OrderStatus.DELIVERED, orderIdToUpdate);
            default:
                break;
        }
        return false;
    }

    /**
     * Print the options for the create order menu.
     */
    private void printCreateOrderMenu() {
        System.out.println("Please enter an option (1-6)");
        System.out.println("(1) Print menu");
        System.out.println("(2) Add menu items");
        System.out.println("(3) Remove menu items");
        System.out.println("(4) Print order");
        System.out.println("(5) Enter remarks");
        System.out.println("(6) Checkout");
    }

    /**
     * Application for create order system
     * 
     * @return {@code true} if order creation is successfull. Otherwise, {@code false} if order creation failed (Room Id of customer does not exist in database) / room is not occupied.
     */
    private boolean createOrder() {
        System.out.println("Please enter the Room Id in this format <FloorNo><RoomNo> (eg: 0103)");
        String roomId = Helper.readString();
        if (!RoomManager.validateRoomId(roomId)) {
            System.out.println("Room does not exist");
            return false;
        }
        if (!RoomManager.checkRoomVacancy(roomId, RoomStatus.OCCUPIED)) {
            System.out.println("Room is not occupied!");
            return false;
        }
        String orderId = OrderManager.createOrder(roomId);
        String itemName;
        int itemAmount;
        int option = -1;
        do { 
            Helper.clearScreen();
            printBreadCrumbs("Hotel App View > Order View > Create order for Room " + roomId);
            printCreateOrderMenu();
            option = Helper.readInt(1, 6);
            switch (option) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Order View > Create an order for Room " + roomId + " > Print menu");
                    MenuManager.printMenu();
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Order View > Create an order for Room " + roomId + " > Add menu items");
                    System.out.println("Enter item to be added:\r");
                    itemName = Helper.readString();
                    System.out.println("Enter amount to be added:\r");
                    itemAmount = Helper.readInt();
                    addOrderItem(itemName, orderId, itemAmount);
                    break;
                case 3:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Order View > Create an order for Room " + roomId + " > Remove menu items");
                    System.out.println("Enter item to be removed:\r");
                    itemName = Helper.readString();
                    System.out.println("Enter amount to be removed:\r");
                    itemAmount = Helper.readInt();
                    removeOrderItem(itemName, orderId, itemAmount);
                    break;
                case 4:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Order View > Create an order for Room " + roomId + " > Print order");
                    OrderManager.printOrderDetails(orderId);
                    break;
                case 5:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Order View > Create an order for Room " + roomId + " > Enter remarks");
                    System.out.println("Enter remarks:\r");
                    String remarks = Helper.readString();
                    OrderManager.setRemarks(remarks, orderId);
                    System.out.println("Remarks given");
                    break;
                case 6:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Order View > Create an order for Room " + roomId + " > Checkout");
                    confirmOrder(orderId);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            if (option != 6){
                Helper.pressAnyKeyToContinue();
            }
        } while (option != 6);
        return true;
    }

    /**
     * Adds menu item of the specified quantity to the customer's order
     * 
     * @param name Name of the menu item to be added
     * @param orderId Id of the order
     * @param amount Quantity of menu item to be added
     */
    private void addOrderItem(String name, String orderId, int amount){
        if (OrderManager.addOrderItem(name, orderId, amount)){
            System.out.printf("\"%s\" added to order SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Addition to order FAILED (\"%s\" NOT FOUND in menu)\n", name);
        };
    }

    /**
     * Removes menu item of the specified quantity from the customer's order
     * 
     * @param name Name of the menu item to be removed
     * @param orderId Id of the order
     * @param amount Quantity of menu item to be removed
     */
    private void removeOrderItem(String name, String orderId, int amount){
        if (OrderManager.removeOrderItem(name, orderId, amount)){
            System.out.printf("\"%s\" removed from order SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Removal from order FAILED (\"%s\" NOT FOUND in order\\ removal quantity > current quantity)\n", name);
        };
    }

    /**
     * Confirms the finished order and prints the receipt
     * 
     * @param orderId Id of order
     */
    private void confirmOrder(String orderId) {
        System.out.println("RECEIPT:");
        OrderManager.printOrderDetails(orderId);
        System.out.println("Order Sent!!!\nThank you for ordering!!! :):):)");
        OrderManager.updateStatus(OrderStatus.CONFIRMED, orderId);
    }
    
    /**
     * Prints the options for the order status menu
     */
    private void printOrderStatusMenu() {
        System.out.println("Please enter a new status (1-3)");
        System.out.println("(1) Confirmed");
        System.out.println("(2) Preparing");
        System.out.println("(3) Delivered");
    }
    
}
