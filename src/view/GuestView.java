package src.view;

import src.helper.Helper;
import src.controller.GuestManager;
import src.model.Identity;
import src.model.enums.Gender;
import src.model.enums.IdentityType;

// for javadocs
import src.model.Guest;
/**
 * GuestView provides the view to take user input which calls {@link GuestManager} to manage {@link Guest}.
 * @author Zhang Kaichen
 * @version 1.0
 * @since 2022-04-06
 */
public class GuestView extends MainView{
    /**
     * Default constructor of the GuestView.
     */
    public GuestView() {
        super();
    }
    /**
     * View Menu of the GuestView.
     */
    @Override
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Guest View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Create a Guest");
        System.out.println("(2) Update a Guest detail");
        System.out.println("(3) Remove a Guest");
        System.out.println("(4) Search a Guest");
        System.out.println("(5) Print all Guests");
        System.out.println("(6) Exit Guest View");
    }
    /**
     * View Application of the GuestView. <p>
     * see {@link GuestManager} for more {@link Guest} management details.
     */
    @Override
    public void viewApp() {
        int opt;
        do {
            printMenu();
            opt = Helper.readInt(1, 6);
            switch (opt) {
                case 1:
                    if (!promptCreateGuest()) {
                        System.out.println("Create guest unsuccessful");
                    }
                    ;
                    break;
                case 2:
                    if (!promptUpdateGuest()) {
                        System.out.println("Update guest unsuccessful");
                    } else {
                        System.out.println("Update guest successful");
                    }
                    ;
                    break;
                case 3:
                    if (!promptRemoveGuest()) {
                        System.out.println("Remove guest unsuccessful");
                    } else {
                        System.out.println("Remove guest successful");
                    }
                    ;
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
                    System.out.println("Invalid Choice");
            }
            if (opt != 6) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 6);
    }
    // Create Guest
    /**
     * The function that receives input and creates a {@link Guest} through Guest Manager <p>
     * see {@link GuestManager}
     * @return {@code true} if successfully create a {@link Guest}. Otherwise, {@code false}.
     */
    private boolean promptCreateGuest() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Guest View > Create a Guest");
        System.out.println("Please enter guest's first name: ");
        String firstName = Helper.readString();
        System.out.println("Please enter guest's last name: ");
        String lastName = Helper.readString();
        System.out.println("Please enter guest's credit card number: ");
        String creditCardNumber = Helper.readString();
        System.out.println("Please enter guest's address: ");
        String address = Helper.readString();
        Gender gender = promptGender();
        if (gender == null) {
            return false;
        }
        Identity identity = promptIdentity();
        if (identity == null) {
            return false;
        }
        System.out.println("Please enter guest's nationality");
        String nationality = Helper.readString();
        System.out.println("Please enter guest's contact number");
        String contactNo = Helper.readString();
        GuestManager.createGuest(firstName, lastName, creditCardNumber, address, gender, identity, nationality,
                contactNo);
        return true;
    }
    
    /**
     * View Menu for Gender
     */
    private void printGenderMenu() {
        System.out.println("Please enter the guest's gender (1-2)");
        System.out.println("(1) Male");
        System.out.println("(2) Female");
    }

    /**
     * View Menu for Identity
     */
    private void printIdentityMenu() {
        System.out.println("Please enter the identity type (1-2)");
        System.out.println("(1) Driving License");
        System.out.println("(2) Passport");
    }

    /**
     * Function that generate the input for the gender for the guest <p>
     * @return the gender type that is chosen if choice is valid
     */
    private Gender promptGender() {
        printGenderMenu();
        int choice = Helper.readInt(1, 2);
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

    /**
     * Function that generate the input for the identity for the guest <p>
     * @return {@link Identity} object that is chosen if choice is valid. Otherwise, {@code null}.
     */
    private Identity promptIdentity() {
        printIdentityMenu();
        int choice = Helper.readInt(1, 2);
        Identity identity;
        String identityNo;
        switch (choice) {
            case 1:
                System.out.println(
                        "Enter the guest's " + IdentityType.DRIVING_LICENSE.identityTypeAsStr + " number: ");
                identityNo = Helper.readString();
                identity = new Identity(IdentityType.DRIVING_LICENSE, identityNo);
                return identity;
            case 2:
                System.out.println("Enter the guest's " + IdentityType.PASSPORT.identityTypeAsStr + " number: ");
                identityNo = Helper.readString();
                identity = new Identity(IdentityType.PASSPORT, identityNo);
                return identity;
            default:
                break;
        }
        return null;
    }
    
    // Update Guest
    /**
     * Prompt to update Guest <p>
     * @return {@code true} if update successfully. Otherwise, {@code false}
     */
    private boolean promptUpdateGuest() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Guest View > Update a Guest Detail");
        System.out.println("Enter the guest that you want to update (GXXXX): ");
        String guestId = Helper.readString();
        if (GuestManager.searchGuestById(guestId).size() == 0) {
            System.out.println("Guest not found!");
            return false;
        }
        printUpdateGuestMenu();
        int opt = -1;
        opt = Helper.readInt(1, 7);
        switch (opt) {
            case 1:
                System.out.println("Please enter the guest's new first name:");
                String firstName = Helper.readString();
                System.out.println("Please enter the guest's new last name:");
                String lastName = Helper.readString();
                GuestManager.updateGuest(guestId, 1, firstName, lastName);
                return true;
            case 2:
                System.out.println("Please enter the guest's new credit card number:");
                String creditCardNo = Helper.readString();
                GuestManager.updateGuest(guestId, 2, creditCardNo);
                return true;
            case 3:
                System.out.println("Please enter the guest's new address:");
                String address = Helper.readString();
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
                String nationality = Helper.readString();
                GuestManager.updateGuest(guestId, 3, nationality);
                return true;
            case 7:
                System.out.println("Please enter your contact number:");
                String contactNo = Helper.readString();
                GuestManager.updateGuest(guestId, 7, contactNo);
                return true;
            default:
                break;
        }
        return false;
    }

    /**
     * View Menu for update
     */
    private void printUpdateGuestMenu() {
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
    /**
     * Prompt function to search a guest
     */
    private void promptSearchGuest() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Guest View > Search a Guest");
        System.out.println("Please enter an option on how to search the guests (1-2)");
        System.out.println("(1) Search by guest id");
        System.out.println("(2) Search by keyword (name)");
        int opt = Helper.readInt(1, 2);
        switch (opt) {
            case 1:
                System.out.println("Enter the guest id you want to search (GXXXX): ");
                String guestId = Helper.readString();
                if (!GuestManager.findGuestDetails(guestId, true)) {
                    System.out.println("Guest not found!");
                }
                break;
            case 2:
                System.out.println("Please enter a keyword for the guest to search: ");
                String keyword = Helper.readString();
                if (!GuestManager.findGuestDetails(keyword, false)) {
                    System.out.println("Guest not found!");
                }
                break;
            default:
                break;
        }
    }

    // Remove guest
    /**
     * Prompt function to remove a guest <p>
     * see {@link GuestManager} For the remove function detail <p>
     * @return {@code true} if remove successfully, Otherwise, {@code false} 
     */
    private boolean promptRemoveGuest() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Guest View > Remove a guest");
        System.out.println("Enter the guest id you want to remove (GXXXX): ");
        String guestId = Helper.readString();
        if (!GuestManager.removeGuest(guestId)) {
            System.out.println("Guest not found!");
            return false;
        }
        return true;
    }

    // Print all guest
    /**
     * Prompt function to print all guest <p>
     * see {@link GuestManager} For the printing detail
     */
    private void printGuests() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Guest View > Print All Guests");
        System.out.println("Please select an option of printing the guest (1-2)");
        System.out.println("(1) Print in order of Guest ID");
        System.out.println("(2) Print in order of Guest name");
        int opt = Helper.readInt(1, 2);
        switch (opt) {
            case 1:
                GuestManager.printAllGuests(true);
                break;
            case 2:
                GuestManager.printAllGuests(false);
                break;
            default:
                break;
        }
    }
}
