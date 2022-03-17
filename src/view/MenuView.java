package src.view;

import java.util.Scanner;

public class MenuView extends MainView{

    public MenuView(){
        MenuController menuController = new MenuController();
    }

    @Override
    public void printMenu() {
        System.out.println("***** CUSTOMIZE MENU *****");
        System.out.println("Please enter an option (1-5)");
        System.out.println("1) Add menu items");
        System.out.println("2) Remove menu items");
        System.out.println("3) Update menu items");
        System.out.println("4) Print menu items");
        System.out.println("5) Exit");
    }

    @Override
    public void viewapp() {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        String name, description;
        double price;
        do {
            printMenu();
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
//        menuController.printMenuItems();
    }

    private void addMenuItem(String name, String description, double price){
//        if (menuController.addMenuItem(name, description, price)){
//            System.out.printf("%s added SUCCESSFULLY\n", name);
//        }
//        else{
//            System.out.printf("Addition FAILED (%s already exist)\n", name);
//        }
    }

    private void removeMenuItem(String name){
//        if (menuController.removeMenuItem(name)){
//            System.out.printf("%s removed SUCCESSFULLY\n", name);
//        }
//        else{
//            System.out.printf("Removal FAILED(%s NOT FOUND)\n", name);
//        }
    }

    private void updateMenuItem(String name, String description, double price){

//        if (menuController.updateMenuItem(name, description, price)){
//            System.out.printf("%s updated SUCCESSFULLY\n", name);
//        }
//        else{
//            System.out.printf("Update FAILED(%s NOT FOUND)\n", name);
//        }
    }
}