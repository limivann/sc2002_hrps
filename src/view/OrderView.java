package src.view;

import java.util.Scanner;

public class OrderView extends MainView{

    public OrderView(){
        OrderController orderController = new OrderController();
    }

    @Override
    public void printMenu() {
        System.out.println("***** ORDER MENU *****");
        System.out.println("Please enter an option (1-6)");
        System.out.println("1) Print menu");
        System.out.println("2) Add menu items");
        System.out.println("3) Remove menu items");
        System.out.println("4) Print order");
        System.out.println("5) Enter remarks");
        System.out.println("6) Checkout");
    }

    @Override
    public void viewapp() {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        do{
            printMenu();
            option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1:
                    printMenu();
                    break;
                case 2:
                    System.out.println("Enter item number to be added:\r");
                    option = sc.nextInt();
                    sc.nextLine();
                    addMenuItem(option - 1);
                    break;
                case 3:
                    System.out.println("Enter item number to be removed:\r");
                    option = sc.nextInt();
                    sc.nextLine();
                    removeMenuItem(option - 1);
                    break;
                case 4:
                    printOrder();
                    break;
                case 5:
                    System.out.println("Enter remarks:\r");
                    setRemarks(sc.nextLine());
                    break;
                case 6:
                    printOrder();
                    confirmOrder();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (option != 6);
    }

    private void printMenuItems(){
//        menuController.printMenuItems();
    }

    private void addMenuItem(int option){
//        if (OrderController.addMenuItem(option)){
//            System.out.printf("Item added SUCCESSFULLY\n");
//        }
//        else{
//            System.out.println("Entered option out of range");
//        };
    }

    private void removeMenuItem(int option){
//        if (OrderController.removeMenuItem(option)){
//            System.out.printf("Item removed SUCCESSFULLY\n");
//        }
//        else{
//            System.out.println("Entered option not in current order");
//        };
    }

    private void printOrder(){
//        OrderController.printOrder;
    }

    private void setRemarks(String remarks){
//        OrderController.setRemarks(remarks);
    }

    private void confirmOrder() {
//        System.out.println("Order Sent!!!\nThank you for ordering!!! :):):)");
//        OrderController.updateStatus(OrderStatus.CONFIRMED);
    }
}