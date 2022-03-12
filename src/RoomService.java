package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RoomService {

    private enum OrderStatus{
        NONE,
        CONFIRMED,
        PREPARING,
        DELIVERED,
    }
    private String date;
    private String time;
    private ArrayList<MenuItem> menu;
    private static double totalBill = 0.0;
    private ArrayList<MenuItem> currentOrders;
    private String remarks;
    private OrderStatus status;



    public RoomService(String date, String time){
        this.date = date;
        this.time = time;
        this.remarks = "No remarks";
        this.status = OrderStatus.NONE;
        this.menu = new ArrayList<MenuItem>();
        this.currentOrders = new ArrayList<MenuItem>();
    }

    /* Menu editing methods*/

    public void editMenu(){
        Scanner sc = new Scanner(System.in);
        int option = 99;
        String name, description;
        double price;
        printEditMenu();
        do{
            System.out.println("Enter an option:\r");
            option = getInt();
            switch(option){
                case 1:
                    System.out.println("Enter name of item to be added:\r");
                    name = sc.nextLine();
                    System.out.printf("Enter description of %s:\n\r", name);
                    description = sc.nextLine();
                    System.out.printf("Enter price of %s:\n\r", name);
                    price = getDouble();
                    if (addMenuItem(name, description, price)){
                        System.out.printf("%s added SUCCESSFULLY\n", name);
                    }
                    else{
                        System.out.printf("Addition FAILED (%s already exist)\n", name);
                    };
                    break;
                case 2:
                    System.out.println("Enter name of item to be removed:\r");
                    name = sc.nextLine();
                    if (removeMenuItem(name)){
                        System.out.printf("%s removed SUCCESSFULLY\n", name);
                    }
                    else{
                        System.out.printf("Removal FAILED(%s NOT FOUND)\n", name);
                    }
                    break;
                case 3:
                    System.out.println("Enter name of item to be updated:\r");
                    name = sc.nextLine();
                    System.out.printf("Enter description of %s:\n\r", name);
                    description = sc.nextLine();
                    System.out.printf("Enter price of %s:\n\r", name);
                    price = getDouble();
                    if (updateMenuItem(name, description, price)){
                        System.out.printf("%s updated SUCCESSFULLY\n", name);
                    }
                    else{
                        System.out.printf("Update FAILED(%s NOT FOUND)\n", name);
                    }
                    break;
                case 4:
                    printMenu();
                    break;
                case 5:
                    System.out.println("EXITED EDIT MENU");
                    break;
                case 6:
                    System.out.println("Invalid option");
                    break;
            }
        }while (option != 5);
    }
    private boolean addMenuItem(String name, String description, double price){

        if (findMenuItem(name) == null){
           return menu.add(new MenuItem(name, description, price));
        }
        return false;
    }

    private boolean removeMenuItem(String name){
        MenuItem target = findMenuItem(name);

        if (target != null){
            return menu.remove(target);
        }
        return false;
    }

    private boolean updateMenuItem(String name, String description, double price){
        MenuItem target = findMenuItem(name);
        if (target != null){
            menu.set(menu.indexOf(target), new MenuItem(name, description, price));
            return true;
        }
        return false;
    }


    private MenuItem findMenuItem(String name){

        for (int i = 0; i < menu.size(); i++){
            MenuItem searchedItem = menu.get(i);
            if (searchedItem.getName().equalsIgnoreCase(name)){
                return searchedItem;
            }
        }
        return null;
    }

    /* Order Methods*/
    public void createOrder(){
        Scanner sc = new Scanner(System.in);
        int option = 99;
        printCreateOrderMenu();
        do{
            System.out.println("Enter option");
            option = getInt();
            switch (option){
                case 1:
                    printMenu();
                    break;
                case 2:
                    System.out.println("Enter item number to be added:\r");
                    option = getInt();
                    if (addMenuItem(option - 1)){
                        System.out.printf("%s added SUCCESSFULLY\n", menu.get(option - 1).getName());
                    }
                    else{
                        System.out.println("Entered option out of range");
                    };
                    break;
                case 3:
                    System.out.println("Enter item number to be removed:\r");
                    option = getInt();
                    if (removeMenuItem(option - 1)){
                        System.out.printf("%s removed SUCCESSFULLY\n", menu.get(option - 1).getName());
                    }
                    else{
                        System.out.println("Entered option not in current order");
                    };
                    break;
                case 4:
                    printOrder();
                    break;
                case 5:
                    System.out.println("Enter remarks:\r");
                    setRemarks(sc.nextLine());
                    break;
                case 6:
                    printFinalBill();
                    System.out.println("Order Sent!!!\nThank you for ordering!!! :):):)");
                    updateStatus(OrderStatus.CONFIRMED);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (option != 6);
    }

    private boolean addMenuItem(int option){
        if (validChoice(option)){
            MenuItem toBeAdded = menu.get(option);
            totalBill += toBeAdded.getPrice();
            return currentOrders.add(toBeAdded);
        }
        return false;
    }

    private boolean removeMenuItem(int option){
        if (validChoice(option) && currentOrders.contains(menu.get(option))){
            MenuItem toBeRemoved = menu.get(option);
            totalBill -= toBeRemoved.getPrice();
            return currentOrders.remove(toBeRemoved);
        }
        return false;
    }

    private boolean validChoice(int option){
        return option >= 0 && option < menu.size();
    }

    private void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public void updateStatus(OrderStatus currentStatus){
        status = currentStatus;
    }

    /* Others */
    private void printMenu(){
        System.out.println("***** MENU ITEMS  *****");

        for (int i = 0; i < menu.size(); i++){
            MenuItem curr = menu.get(i);
            System.out.printf("(%d) %s\n", i+1, curr.getName());
            System.out.println("Description: " + curr.getDescription());
            System.out.printf("Price: $%.2f\n", curr.getPrice());
        }
    }
    private void printEditMenu(){
        System.out.println("***** CUSTOMIZE MENU *****\n1.) Add menu items\n2.) Remove menu items\n" +
                "3.) Update menu items\n4.) Print menu items\n5.) Exit");
    }

    private void printCreateOrderMenu(){
        System.out.println("***** ORDER MENU *****\n1.) Print menu\n2.) Add menu items\n3.) Remove menu items\n" +
                "4.) Print order\n5.) Enter remarks\n6.) Checkout");
    }

    private void printOrder(){
        System.out.println("Current order:");
        for(int i = 0; i < currentOrders.size(); i++){
            MenuItem curr = currentOrders.get(i);
            System.out.printf("%s          $%.2f\n", curr.getName(), curr.getPrice());
        }
        System.out.printf("Total Bill: $%.2f\n", totalBill);
    }

    private void printFinalBill(){
        System.out.println("Final Bill:");
        printOrder();
        System.out.println("Remarks: " + remarks);
    }

    /* Exception Handling */
    private int getInt(){
        Scanner sc = new Scanner(System.in);
        while (true){
            try{
                return sc.nextInt();
            } catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Not an integer\nTry Again:\r");
            }
        }
    }
    private double getDouble(){
        Scanner sc = new Scanner(System.in);
        while (true){
            try{
                return sc.nextDouble();
            } catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Not a double\nTry Again:\r");
            }
        }
    }
}
