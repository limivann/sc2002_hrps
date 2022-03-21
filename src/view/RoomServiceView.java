package src.view;
import src.controller.*;
import src.*;
import src.model.enums.*;
import java.util.Scanner;

public class RoomServiceView extends MainView{

    RoomServiceManager roomServiceManager= new RoomServiceManager();

    @Override
    public void printMenu() {
        System.out.println("*** Room Service App ***");
        System.out.println("Please enter an option (1-3)");
        System.out.println("1.) Customize Menu");
        System.out.println("2.) Create an Order");
        System.out.println("3.) Exit");
    }

    @Override
    public void viewapp() {
        Scanner sc = new Scanner(System.in);
        int option = 99;
        do{
            printMenu();
            option = sc.nextInt();
            sc.nextLine();
            switch(option){
                case 1:
                    customizeMenu();
                    break;
                case 2:
                    createOrder();
                    break;
                case 3:
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        } while (option != 3);
    }

    /* Create Order */
    private void printMenu_createOrder() {
        System.out.println("***** ORDER MENU *****");
        System.out.println("Please enter an option (1-6)");
        System.out.println("1) Print menu");
        System.out.println("2) Add menu items");
        System.out.println("3) Remove menu items");
        System.out.println("4) Print order");
        System.out.println("5) Enter remarks");
        System.out.println("6) Checkout");
    }

    private void createOrder(){
        Scanner sc = new Scanner(System.in);
        String itemName;
        System.out.println("Enter date: ");
        String date = sc.nextLine();
        System.out.println("Enter time: ");
        String time = sc.nextLine();
        roomServiceManager.createOrder(date, time);
        int option = 99;
        do{
            printMenu_createOrder();
            System.out.println("Enter option");
            option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1:
                    roomServiceManager.printMenu();
                    break;
                case 2:
                    System.out.println("Enter item to be added:\r");
                    itemName = sc.nextLine();
                    addOrderItem(itemName);
                    break;
                case 3:
                    System.out.println("Enter item to be removed:\r");
                    itemName = sc.nextLine();
                    removeOrderItem(itemName);
                    break;
                case 4:
                    roomServiceManager.printOrder();
                    break;
                case 5:
                    System.out.println("Enter remarks:\r");
                    roomServiceManager.setRemarks(sc.nextLine());
                    break;
                case 6:
                    confirmOrder();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (option != 6);
    }

    private void addOrderItem(String name){
        if (roomServiceManager.addOrderItem(name)){
            System.out.printf("\"%s\" added to order SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Addition to order FAILED (\"%s\" NOT FOUND in menu)\n", name);
        };
    }

    private void removeOrderItem(String name){
        if (roomServiceManager.removeOrderItem(name)){
            System.out.printf("\"%s\" removed from order SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Removal from order FAILED (\"%s\" NOT FOUND in order)\n", name);
        };
    }

    private void confirmOrder() {
        System.out.println("RECEIPT:");
        roomServiceManager.printOrder();
        System.out.println("Order Sent!!!\nThank you for ordering!!! :):):)");
        roomServiceManager.updateStatus(OrderStatus.CONFIRMED);
    }

    /* Customize Menu */

    private void printMenu_customizeMenu() {
        System.out.println("***** CUSTOMIZE MENU *****");
        System.out.println("Please enter an option (1-5)");
        System.out.println("1) Add menu items");
        System.out.println("2) Remove menu items");
        System.out.println("3) Update menu items");
        System.out.println("4) Print menu items");
        System.out.println("5) Exit");
    }

    private void customizeMenu() {
        Scanner sc = new Scanner(System.in);
        int option = 99;
        String name, description;
        double price;
        do{
            printMenu_customizeMenu();
            option = sc.nextInt();
            sc.nextLine();
            switch(option){
                case 1:
                    System.out.println("Enter name of item to be added:\r");
                    name = sc.nextLine();
                    System.out.printf("Enter description of %s:\n\r", name);
                    description = sc.nextLine();
                    System.out.printf("Enter price of %s:\n\r", name);
                    price = sc.nextDouble();
                    sc.nextLine();
                    addMenuItem(name, description, price);
                    break;
                case 2:
                    System.out.println("Enter name of item to be removed:\r");
                    name = sc.nextLine();
                    removeMenuItem(name);
                    break;
                case 3:
                    System.out.println("Enter name of item to be updated:\r");
                    name = sc.nextLine();
                    System.out.printf("Enter description of %s:\n\r", name);
                    description = sc.nextLine();
                    System.out.printf("Enter price of %s:\n\r", name);
                    price = sc.nextDouble();
                    sc.nextLine();
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
        roomServiceManager.printMenu();
    }

    private void addMenuItem(String name, String description, double price){

        if (roomServiceManager.addMenuItem(name, description, price)){
            System.out.printf("\"%s\" added to menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Addition to menu FAILED (\"%s\" is already in the menu)\n", name);
        }
    }

    private void removeMenuItem(String name){
        if (roomServiceManager.removeMenuItem(name)){
            System.out.printf("\"%s\" removed from menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Removal from menu FAILED (\"%s\" NOT FOUND in menu)\n", name);
        }
    }

    private void updateMenuItem(String name, String description, double price){

        if (roomServiceManager.updateMenuItem(name, description, price)){
            System.out.printf("%s updated in menu SUCCESSFULLY\n", name);
        }
        else{
            System.out.printf("Update menu FAILED (\"%s\" NOT FOUND in menu)\n", name);
        }
    }
}