package src.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import javax.xml.crypto.Data;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Guest;
import src.model.Identity;
import src.model.enums.Gender;

// for javadocs
import src.view.GuestView;

/**
 * @author Zhang Kaichen
 * @version 1.0.0
 * @since 2022-03-30
 */

public class GuestManager {
    /**
     * Default constructor of the GuestManager
     */
    public GuestManager() {

    }
    
    /**
     * Constructor for the guest in the GuestManager
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
     * The function to update the detail of the guest and save the file into database
     * @param guestId the guest id of the guest
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param newValue the new detail value for the guest
     * see {@link GuestView#printUpdateGuestMenu()} for the menu of the attribute code
     */
    public static void updateGuest(String guestId, int attributeCode, String newValue) {
        ArrayList<Guest> updateList = searchGuestById(guestId);
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
    }

    // For updating more than one value (first name, last name)
    /**
     * Overloading method of updateGuest that update the first name and last name of the guest
     * @param guestId the guest id of the guest
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param newValue1 the new value for the first name
     * @param newValue2 the new value for the last name
     * see {@link GuestView#printUpdateGuestMenu()} for the menu of the attribute code
     */
    public static void updateGuest(String guestId, int attributeCode, String newValue1, String newValue2) {
        ArrayList<Guest> updateList = searchGuestById(guestId);
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
    }
    
    /**
     * Overloading method of updateGuest that update the gender of the guest
     * @param guestId the guest id of the guest
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param gender the new gender of the guest
     * see {@link GuestView#printUpdateGuestMenu()} for the menu of the attribute code
     */
    public static void updateGuest(String guestId, int attributeCode, Gender gender) {
        ArrayList<Guest> updateList = searchGuestById(guestId);
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
    }
    
    /**
     * Overloading method of updateGuest that update the identity of the guest
     * @param guestId the guest id of the guest
     * @param attributeCode the attribute code for the detail that user choose to update
     * @param identity the new identity for the guest
     * see {@link GuestView#printUpdateGuestMenu()} for the menu of the attribute code
     */
    public static void updateGuest(String guestId, int attributeCode, Identity identity) {
        ArrayList<Guest> updateList = searchGuestById(guestId);
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
    }

    // Search Guest
    /**
     * Search method to search a Guest by guest id
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
    
    /**
     * Function to remove guest from the database
     * @param guestId the guest id of the guest that the user want to remove
     * @return true if remove successfully else return false
     */
    public static boolean removeGuest(String guestId) {
        ArrayList<Guest> removeList = GuestManager.searchGuestById(guestId);
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
     * Function to print all guest in the database that show the guest id and the Guest
     * see {@link Guest#toString()}
     */
    public static void printAllGuests() {
        HashMap<String, Guest> toIterate = Helper.copyHashMap(Database.GUESTS);
        Iterator it = toIterate.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    /**
     * validate if the database has this guest according to guest id
     * @param guestId the guest of the guest that you want to validate
     * @return true if exist false if not exist
     */
    public static boolean validateGuestId(String guestId) {
        if (Database.GUESTS.containsKey(guestId)) {
            return true;
        } else {
            // TODO: Throw Exception
            return false;
        }
    }
}
