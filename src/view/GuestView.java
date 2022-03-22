package src.view;

import java.util.ArrayList;
import java.util.Scanner;
import src.helper.Helper;
import src.controller.GuestManager;
import src.model.Guest;
import src.model.Identity;
import src.model.enums.Gender;
import src.model.enums.IdentityType;
public class GuestView extends MainView{
    GuestManager g;

    public GuestView() {
        super();
        g = new GuestManager();
    }

    @Override
    public void printMenu() {
        System.out.println("=== Guest View ===");
        System.out.println("Enter your choice");
        System.out.println("(1) Create a Guest");
        System.out.println("(2) Update a Guest detail");
        System.out.println("(3) Remove a Guest");
        System.out.println("(4) Search a Guest");
        System.out.println("(5) Print all Guests");
        System.out.println("(6) Exit Guest View");
    }

    @Override
    public void viewapp() {
        int opt;
        Scanner sc = new Scanner(System.in);
        do {
            printMenu();
            opt = sc.nextInt();

            switch (opt) {
                case 1:
                    promptCreateGuest();
                    // createGuest();
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
        } while (opt != 6);
    }

    public boolean promptCreateGuest() {
        Helper.sc.nextLine();  // Consume newline left-over
        System.out.println("Please enter guest's first name: ");
        String firstName = Helper.sc.nextLine();
        System.out.println("Please enter guest's last name: ");
        String lastName = Helper.sc.nextLine();
        System.out.println("Please enter guest's credit card number: ");
        String creditCardNumber = Helper.sc.nextLine();
        System.out.println("Please enter guest's address: ");
        String address = Helper.sc.nextLine();
        System.out.println("HERE");
        Gender gender = promptGender();
        if (gender == null) {
            return false;
        }
        Identity identity = promptIdentity();
        if (identity == null) {
            return false;
        }
        Helper.sc.nextLine();  // Consume newline left-over
        System.out.println("Please enter guest's nationality");
        String nationality = Helper.sc.nextLine();
        System.out.println("Please enter guest's contact number");
        String contactNo = Helper.sc.nextLine();
        GuestManager.createGuest(firstName, lastName, creditCardNumber, address, gender, identity, nationality, contactNo);
        return true;
    }
    
    public void printGenderMenu() {
        System.out.println("Please enter the guest's gender (1-2)");
        System.out.println("(1) Male");
        System.out.println("(2) Female");
    }

    public void printIdentityMenu() {
        System.out.println("Please enter the identity type (1-2)");
        System.out.println("(1) Driving License");
        System.out.println("(2) Passport");
    }

    public Gender promptGender() {
        printGenderMenu();
        int choice = Helper.sc.nextInt();
        if (choice != 1 && choice != 2) {
            return null;
        } else {
            switch (choice) {
                case 1:
                    return Gender.MALE;
                case 2:
                    return Gender.FEMALE;
                default:
                    break;
            }
        }
        return null;
    };

    public Identity promptIdentity() {
        printIdentityMenu();
        int choice = Helper.sc.nextInt();
        if (choice != 1 && choice != 2) {
            return null;
        } else {
            Identity identity;
            String identityNo;
            switch (choice) {
                case 1:
                    System.out.println("Enter the guest's " + IdentityType.DRIVING_LICENSE.identityTypeAsStr + " number: ");
                    identityNo = Helper.sc.nextLine();
                    identity = new Identity(IdentityType.DRIVING_LICENSE, identityNo);
                    return identity;
                case 2:
                    System.out.println("Enter the guest's " + IdentityType.PASSPORT.identityTypeAsStr + " number: ");
                    identityNo = Helper.sc.nextLine();
                    identity = new Identity(IdentityType.PASSPORT, identityNo);
                    return identity;
                default:
                    break;
            }
        }
        return null;
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
