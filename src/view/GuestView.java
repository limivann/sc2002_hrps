package src.view;

import java.util.ArrayList;
import java.util.Scanner;

import src.controller.GuestManager;
import src.model.Guest;

public class GuestView extends MainView{
    GuestManager g;
    public GuestView() {
        g = new GuestManager();
    }

    @Override
    public void printMenu() {
        System.out.println("----------------");
        System.out.println("Enter your choice");
        System.out.println("(1) Create a Guest");
        System.out.println("(2) Update a Guest detail");
        System.out.println("(3) Remove a Guest");
        System.out.println("(4) Search a Guest");
        System.out.println("(5) Print all Guest");
        System.out.println("(6) Exit Guest View");
        
    }

    @Override
    public void viewapp() {
        int opt;
        Scanner sc = new Scanner(System.in);
        do{
            printMenu();
            opt = sc.nextInt();
            
            switch (opt) {
                case 1:
                    createGuest();
                    break;
                case 2:
                    updateGuest();
                    break;
                case 3:
                    removeGuest();
                    break;
                case 4:
                    searchGuest();
                    break;
                case 5:
                    printallguest();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid Choice");

            }            
        }
        while(opt != 6);
    }

    public void createGuest(){
        g.create();
    }

    public void updateGuest(){
        g.update();
    }
    
    public void removeGuest(){
        g.remove();
    }

    public void searchGuest(){
        ArrayList<Guest> searchprint = g.search();
        for (Guest guest : searchprint) {
            guest.printGuestDetails();
        }
    }

    public void printallguest(){
        g.printallguest();
    }

    public static void main(String[] args) {
        GuestView gv = new GuestView();
        gv.viewapp();
    }
}
