package src.view;

import src.controller.RoomManager;
import src.controller.RoomServiceManager;
import src.helper.Helper;
import src.model.enums.OrderStatus;

public class OrderView extends MainView{

    @Override
    public void printMenu() {
        System.out.println("=== Order View ===");
        System.out.println("Please enter an option (1-2)");
        System.out.println("(1) Create an Order");
        System.out.println("(2) Exit Order View");
    }

    @Override
    public void viewapp() {
        int option = 99;
        do{
            printMenu();
            option = Helper.readInt();
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
        int option = -1;
        do{
            printMenu_createOrder();
            System.out.println("Enter option: ");
            option = Helper.readInt();
            switch (option){
                case 1:
                    RoomServiceManager.printMenu();
                    break;
                case 2:
                    System.out.println("Enter item to be added:\r");
                    itemName = Helper.sc.nextLine();
                    addOrderItem(itemName, orderId);
                    break;
                case 3:
                    System.out.println("Enter item to be removed:\r");
                    itemName = Helper.sc.nextLine();
                    removeOrderItem(itemName, orderId);
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

    private void addOrderItem(String name, String orderId){
        if (RoomServiceManager.addOrderItem(name, orderId)){
            System.out.printf("\"%s\" added to order SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Addition to order FAILED (\"%s\" NOT FOUND in menu)\n", name);
        };
    }

    private void removeOrderItem(String name, String orderId){
        if (RoomServiceManager.removeOrderItem(name, orderId)){
            System.out.printf("\"%s\" removed from order SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Removal from order FAILED (\"%s\" NOT FOUND in order)\n", name);
        };
    }

    private void confirmOrder(String orderId) {
        System.out.println("RECEIPT:");
        RoomServiceManager.printOrder(orderId);
        System.out.println("Order Sent!!!\nThank you for ordering!!! :):):)");
        RoomServiceManager.updateStatus(OrderStatus.CONFIRMED, orderId);
    }
}
