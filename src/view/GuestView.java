package src.view;

import java.util.ArrayList;
import java.util.Scanner;
import src.helper.Helper;
import src.controller.GuestManager;
import src.database.Database;
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
        do {
            printMenu();
            opt = Helper.readInt();
            switch (opt) {
                case 1:
                    if (!promptCreateGuest()) {
                        System.out.println("Create guest unsuccessful");
                    };
                    break;
                case 2:
                    if (!promptUpdateGuest()) {
                        System.out.println("Update guest unsuccessful");
                    } else {
                        System.out.println("Update guest successful");
                    };
                    break;
                case 3:
                    if (!promptUpdateGuest()) {
                        System.out.println("Remove guest unsuccessful");
                    } else {
                        System.out.println("Remove guest successful");
                    };
                    break;
                case 4:
                    promptSearchGuest();
                    break;
                case 5:
                    printGuests();
                    break;
                case 6:
                    break;
                default:
                    // TODO: Throw Exception
                    System.out.println("Invalid Choice");

            }
        } while (opt != 6);
    }
    // Create Guest
    public boolean promptCreateGuest() {
        System.out.println("Please enter guest's first name: ");
        String firstName = Helper.sc.nextLine();
        System.out.println("Please enter guest's last name: ");
        String lastName = Helper.sc.nextLine();
        System.out.println("Please enter guest's credit card number: ");
        String creditCardNumber = Helper.sc.nextLine();
        System.out.println("Please enter guest's address: ");
        String address = Helper.sc.nextLine();
        Gender gender = promptGender();
        if (gender == null) {
            return false;
        }
        Identity identity = promptIdentity();
        if (identity == null) {
            return false;
        }
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
        int choice = Helper.readInt();
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
        int choice = Helper.readInt();
        if (choice != 1 && choice != 2) {
            return null;
        } else {
            Identity identity;
            String identityNo;
            switch (choice) {
                case 1:
                    System.out.println(
                            "Enter the guest's " + IdentityType.DRIVING_LICENSE.identityTypeAsStr + " number: ");
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
    
    // Update Guest
    public boolean promptUpdateGuest() {
        System.out.println("Enter the guest that you want to update (GXXXX): ");
        String guestId = Helper.sc.nextLine();
        if (GuestManager.searchGuestById(guestId) == null) {
            // TODO: Exception
            System.out.println("Guest not found!");
            return false;
        }
        printUpdateGuestMenu();
        int opt = -1;
        opt = Helper.readInt();
        switch (opt) {
            case 1:
                System.out.println("Please enter the guest's new first name:");
                String firstName = Helper.sc.nextLine();
                System.out.println("Please enter the guest's new last name:");
                String lastName = Helper.sc.nextLine();
                GuestManager.updateGuest(guestId, 1, firstName, lastName);
                return true;
            case 2:
                System.out.println("Please enter the guest's new credit card number:");
                String creditCardNo = Helper.sc.nextLine();
                GuestManager.updateGuest(guestId, 2, creditCardNo);
                return true;
            case 3:
                System.out.println("Please enter the guest's new address:");
                String address = Helper.sc.nextLine();
                GuestManager.updateGuest(guestId, 3, address);
                return true;
            case 4:
                Gender gender = promptGender();
                if (gender == null) {
                    return false;
                }
                GuestManager.updateGuest(guestId, 4, gender);
                return true;
            case 5:
                Identity identity = promptIdentity();
                if (identity == null) {
                    return false;
                }
                GuestManager.updateGuest(guestId, 5, identity);
                return true;
            case 6:
                System.out.println("Please enter your nationality:");
                String nationality = Helper.sc.nextLine();
                GuestManager.updateGuest(guestId, 3, nationality);
                return true;
            case 7:
                System.out.println("Please enter your contact number:");
                String contactNo = Helper.sc.nextLine();
                GuestManager.updateGuest(guestId, 7, contactNo);
                return true;
            default:
                break;
        }
        return false;
    }

    public void printUpdateGuestMenu() {
        System.out.println("Please choose the information that you want to update (1-7)");
        System.out.println("(1) Name");
        System.out.println("(2) Credit Card");
        System.out.println("(3) Address");
        System.out.println("(4) Gender");
        System.out.println("(5) Identity");
        System.out.println("(6) Nationality");
        System.out.println("(7) Contact Number");
    }

    // Search Guest
    public void promptSearchGuest() {
        System.out.println("Enter the guest id you want to search (GXXXX): ");
        String guestId = Helper.sc.nextLine();
        GuestManager.searchGuest(guestId);
    }

    // Remove guest
    public boolean promptRemoveGuest() {
        System.out.println("Enter the guest id you want to remove (GXXXX): ");
        String guestId = Helper.sc.nextLine();
        if (GuestManager.searchGuestById(guestId) == null) {
            // TODO: Exception
            System.out.println("Guest not found!");
            return false;
        }
        ArrayList<Guest> removeList = GuestManager.searchGuestById(guestId);
        for (Guest guest : removeList) {
            String userInput = "";
            System.out.println("Are you sure you want to remove this guest?");
            guest.printGuestDetails();
            userInput = Helper.sc.nextLine();
            if (userInput == "n") {
                // remove
                Database.GUESTS.remove(guestId);
            } else {
                return false;
            }
        }
        return true;
    }

    // Print all guest
    public void printGuests() {
        GuestManager.printAllGuests();
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
