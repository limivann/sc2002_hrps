package src.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Guest;
import src.model.Identity;
import src.model.enums.Gender;

// for javadocs
import src.view.GuestView;
/** The Class that manages {@link Guest}.
 * @author Zhang Kaichen
 * @version 1.0
 * @since 2022-03-30
 */

public class GuestManager {
    /**
     * Default constructor of the GuestManager
     */
    public GuestManager() {

    }
    
    /**
     * Constructor for the guest in the GuestManager <p>
     * @param firstName the first name of the guest
     * @param lastName the last name of the guest
     * @param creditCardNumber the credit card number of the guest
     * @param address the address of the guest
     * @param gender the gender of the guest
     * @param identity the identity of the guest
     * @param nationality the nationality of the guest
     * @param contactNo the contact number of the guest
     */
    public static void createGuest(String firstName, String lastName, String creditCardNumber, String address,
            Gender gender, Identity identity, String nationality, String contactNo) {
        String name = firstName + " " + lastName;
        int gid = Helper.generateUniqueId(Database.GUESTS);
        String guestId = String.format("G%04d", gid);
        Guest newGuest = new Guest(name, firstName, lastName, creditCardNumber, address, gender, identity, nationality,
                contactNo, guestId);
        Database.GUESTS.put(guestId, newGuest);
        Database.saveFileIntoDatabase(FileType.GUESTS);
        System.out.println(String.format("Guest Created! Guest ID: %s, Name: %s", guestId, newGuest.getName()));
    }

    // All update guest helpers
    // For updating one value only
    /**
     * The function to update the detail of the guest and save the file into database <p>
     * see {@link GuestView#printUpdateGuestMenu()} for the menu of the attribute code <p>
     * @param guestId the guest id of the guest
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param newValue the new detail value for the guest
     * @return {@code true} if updating of guest is successful. Otherwise, {@code false} if the guest id is not found.
     */
    public static boolean updateGuest(String guestId, int attributeCode, String newValue) {
        ArrayList<Guest> updateList = searchGuestById(guestId);
        if (updateList.size() == 0) {
            // guest not found
            return false;
        }
        for (Guest guest : updateList) {
            Guest guestToUpdate = Database.GUESTS.get(guestId);;
            switch (attributeCode) {
                case 2:
                    guestToUpdate.setCreditCard(newValue);
                    Database.GUESTS.put(guest.getGuestId(), guestToUpdate);
                    break;
                case 3:
                    guestToUpdate.setAddress(newValue);
                    Database.GUESTS.put(guest.getGuestId(), guestToUpdate);
                    break;
                case 6:
                    guestToUpdate.setNationality(newValue);
                    Database.GUESTS.put(guest.getGuestId(), guestToUpdate);
                    break;
                case 7:
                    guestToUpdate.setContact(newValue);
                    Database.GUESTS.put(guest.getGuestId(), guestToUpdate);
                    break;
                default:
                    break;
            }
        }
        Database.saveFileIntoDatabase(FileType.GUESTS);
        return true;
    }

    // For updating more than one value (first name, last name)
    /**
     * Overloading method of updateGuest that update the first name and last name of the guest <p>
     * see {@link GuestView#printUpdateGuestMenu()} for the menu of the attribute code <p>
     * @param guestId the guest id of the guest
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param newValue1 the new value for the first name
     * @param newValue2 the new value for the last name
     * @return {@code true} if updating of guest is successful. Otherwise, {@code false} if the guest id is not found.
     */
    public static boolean updateGuest(String guestId, int attributeCode, String newValue1, String newValue2) {
        ArrayList<Guest> updateList = searchGuestById(guestId);
        if (updateList.size() == 0) {
            // guest not found
            return false;
        }
        for (Guest guest : updateList) {
            Guest guestToUpdate;
            switch (attributeCode) {
                case 1:
                    guestToUpdate = Database.GUESTS.get(guestId);
                    String newName = newValue1 + " " + newValue2;
                    guestToUpdate.setFirstName(newValue1);
                    guestToUpdate.setLastName(newValue2);
                    guestToUpdate.setName(newName);
                    Database.GUESTS.put(guest.getGuestId(), guestToUpdate);
                    break;
                default:
                    break;
            }
        }
        Database.saveFileIntoDatabase(FileType.GUESTS);
        return true;
    }
    
    /**
     * Overloading method of updateGuest that update the gender of the guest <p>
     * see {@link GuestView#printUpdateGuestMenu()} for the menu of the attribute code <p>
     * @param guestId the guest id of the guest
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param gender the new gender of the guest
     * @return {@code true} if updating of guest is successful. Otherwise, {@code false} if the guest id is not found.
     */
    public static boolean updateGuest(String guestId, int attributeCode, Gender gender) {
        ArrayList<Guest> updateList = searchGuestById(guestId);
        if (updateList.size() == 0) {
            // guest not found
            return false;
        }
        for (Guest guest : updateList) {
            Guest guestToUpdate;
            switch (attributeCode) {
                case 4:
                    guestToUpdate = Database.GUESTS.get(guestId);
                    guestToUpdate.setGender(gender);
                    Database.GUESTS.put(guest.getGuestId(), guestToUpdate);
                    break;
                default:
                    break;
            }
        }
        Database.saveFileIntoDatabase(FileType.GUESTS);
        return true;
    }
    
    /**
     * Overloading method of updateGuest that update the identity of the guest <p>
     * see {@link GuestView#printUpdateGuestMenu()} for the menu of the attribute code <p>
     * @param guestId the guest id of the guest
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param identity the new identity for the guest
     * @return {@code true} if updating of guest is successful. Otherwise, {@code false} if the guest id is not found.
     */
    public static boolean updateGuest(String guestId, int attributeCode, Identity identity) {
        ArrayList<Guest> updateList = searchGuestById(guestId);
        if (updateList.size() == 0) {
            // guest not found
            return false;
        }
        for (Guest guest : updateList) {
            Guest guestToUpdate;
            switch (attributeCode) {
                case 5:
                    guestToUpdate = Database.GUESTS.get(guestId);
                    guestToUpdate.setIdentity(identity);
                    Database.GUESTS.put(guest.getGuestId(), guestToUpdate);
                    break;
                default:
                    break;
            }
        }
        Database.saveFileIntoDatabase(FileType.GUESTS);
        return true;
    }

    // Search Guest
    /**
     * Search method to search a Guest by guest id <p>
     * @param guestId the guest id that want to search
     * @return the guest that correspond to the guest id store in an ArrayList
     */
    public static ArrayList<Guest> searchGuestById(String guestId) {
        ArrayList<Guest> searchList = new ArrayList<Guest>();
        if (Database.GUESTS.containsKey(guestId)) {
            Guest searchedGuest = Database.GUESTS.get(guestId);
            searchList.add(searchedGuest);
        }
        return searchList;
    }

    public static ArrayList<Guest> searchGuestByKeywords(String keyword) {
        ArrayList<Guest> searchList = new ArrayList<Guest>();
        for (Guest guest : Database.GUESTS.values()) {
            String currentGuestName = guest.getName().toLowerCase();
            if (currentGuestName.contains(keyword.toLowerCase())) {
                searchList.add(guest);
            }
        }
        return searchList;
    }
    
    /**
     * Function to remove guest from the database <p>
     * @param guestId the guest id of the guest that the user want to remove
     * @return {@code true} if remove successfully. Otherwise, {@code false}
     */
    public static boolean removeGuest(String guestId) {
        ArrayList<Guest> removeList = GuestManager.searchGuestById(guestId);
        if (removeList.size() == 0) {
            // not found
            return false;
        }
        for (Guest guest : removeList) {
            guest.printGuestDetails();
            if (Helper.promptConfirmation("remove this guest")) {
                Database.GUESTS.remove(guestId);
            } else {
                return false;
            }
        }
        Database.saveFileIntoDatabase(FileType.GUESTS);
        return true;
    }
    
    /**
     * Function to print all guest in the database that show the guest id and the Guest <p>
     * see {@link Guest#toString()}
     */
    public static void printAllGuests(boolean byId) {
        if (byId) {
            for (Guest guest : Database.GUESTS.values()) {
                System.out.println(guest.getGuestId() + " = " + guest);
            }
            return;
        } else {
            //  print by name
            ArrayList<Guest> sortedList = new ArrayList<Guest>();
            //  copy
            for (Guest guest : Database.GUESTS.values()) {
                sortedList.add(guest);
            }
            Collections.sort(sortedList);

            // print
            for (Guest guest : sortedList) {
                System.out.println(guest.getGuestId() + " = " + guest);
            }
        }
        
    }

    /**
     * validate if the database has this guest according to guest id <p>
     * @param guestId the guest of the guest that you want to validate
     * @return {@code true} if guest id exist. Otherwise, {@code false}
     */
    public static boolean validateGuestId(String guestId) {
        if (Database.GUESTS.containsKey(guestId)) {
            return true;
        } else {
            // TODO: Throw Exception
            return false;
        }
    }

    /**
     * Function to print guest details given a particular guest id <p>
     * @param guestId the guest of the guest that you want to print its details
     * @return {@code true} if guest id exist. Otherwise, {@code false}
     */
    public static boolean printGuestDetails(String keyword, boolean byId) {
        ArrayList<Guest> printList = new ArrayList<Guest>();
        if (byId) {
            printList = searchGuestById(keyword);
        } else {
            // search by keywords
            printList = searchGuestByKeywords(keyword);
        }
        if (printList.size() == 0) {
            return false;
        }
        for (Guest guest : printList) {
            guest.printGuestDetails();
        }
        return true;
    }
}
