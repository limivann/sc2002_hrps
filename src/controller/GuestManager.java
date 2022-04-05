package src.controller;

import java.util.ArrayList;
import java.util.Collections;
import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Guest;
import src.model.Identity;
import src.model.enums.Gender;
import src.model.enums.IdentityType;

// for javadocs
import src.view.HotelAppView;
import src.view.GuestView;
/** GuestManager is a controller class that acts as a "middleman"
 * between the view classes - {@link HotelAppView} and {@link GuestView} and the model class - {@link Guest}. <p>
 * 
 * It can initialize, create, update or search {@link Guest} details.
 * @author Zhang Kaichen, Max
 * @version 1.0
 * @since 2022-04-05
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
        System.out.println("Guest Created! Guest Details: ");
        printGuestDetails(newGuest);
    }

    // All update guest helpers
    // For updating one value only
    /**
     * The function to update the detail of the guest and save the file into database <p>
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
     * @return ArrayList of {@link Guest} object that correspond to the guest id
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
     * Search method search a Guest by keyword <p>
     * @param keyword any part of the guest name
     * @return ArrayList of {@link Guest} object that correspond to the keyword
     */
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
     * @return {@code true} if remove successfully. Otherwise, {@code false} if guest id is not found
     */
    public static boolean removeGuest(String guestId) {
        ArrayList<Guest> removeList = GuestManager.searchGuestById(guestId);
        if (removeList.size() == 0) {
            // not found
            return false;
        }
        for (Guest guest : removeList) {
           printGuestDetails(guest);
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
     * See {@link Guest#toString()} for the printing format of guest
     * @param byId {@code true} if print by id. Otherwise, {@code false} if print by guest name.
     */
    public static void printAllGuests(boolean byId) {
        ArrayList<Guest> sortedList = new ArrayList<Guest>();
        //  copy
        for (Guest guest : Database.GUESTS.values()) {
            sortedList.add(guest);
        }
        if (byId) {
            for (int index = 1; index < sortedList.size(); index++) {
                Guest currentGuest = sortedList.get(index);
                int gid = Integer.parseInt(currentGuest.getGuestId().substring(1));
                int position = index;
                while (position > 0 && gid < Integer.parseInt(sortedList.get(position-1).getGuestId().substring(1))) {
                    sortedList.set(position, sortedList.get(position - 1));
                    position--;
                }
                sortedList.set(position, currentGuest);
            }
            
        } else {
            //  print by name
            Collections.sort(sortedList);
        }
        // print
        for (Guest guest : sortedList) {
            System.out.println(guest.getGuestId() + " = " + guest);
        }
        
    }

    /**
     * validate if the database has this guest according to guest id <p>
     * @param guestId the guest of the guest that you want to validate
     * @return {@code true} if guest id exist. Otherwise, {@code false} if guest id does not exist
     */
    public static boolean validateGuestId(String guestId) {
        if (Database.GUESTS.containsKey(guestId)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Function to print guest details given a particular guest id <p>
     * @param keyword keyword to find guest, can be guest id or substring of guest name
     * @param byId {@code true} if find by guest id. Otherwise, {@code false} if find by guest name.
     * @return {@code true} if guest id exist. Otherwise, {@code false} if guest id does not exist
     */
    public static boolean findGuestDetails(String keyword, boolean byId) {
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
            printGuestDetails(guest);
        }
        return true;
    }

    /**
     * Initializer for dummy guests in the hotel. 
     */
    public static void initializeDummyGuests() {
        Identity identity1 = new Identity(IdentityType.DRIVING_LICENSE, "G2121722W");
        GuestManager.createGuest("Aaron", "Lim", "12127387136", "Hall 14", Gender.MALE, identity1, "Malaysian",
                "82712251");
        Identity identity2 = new Identity(IdentityType.PASSPORT, "A812812B");
        GuestManager.createGuest("Max", "Tan", "271271282", "Outside NTU", Gender.MALE, identity2, "Malaysian",
                "85261210");
        Identity identity3 = new Identity(IdentityType.DRIVING_LICENSE, "F2912712C");
        GuestManager.createGuest("Hill", "Seah", "12127387136", "Hall 14", Gender.MALE, identity3, "Singaporean",
                "82712251");
        Identity identity4 = new Identity(IdentityType.PASSPORT, "A0021273C");
        GuestManager.createGuest("Kaichen", "Zhang", "998262712", "Hall 2", Gender.MALE, identity4, "Chinese",
                "97126172");
        Identity identity5 = new Identity(IdentityType.DRIVING_LICENSE, "G2121722W");
        GuestManager.createGuest("Yuan Ren", "Loke", "212171612", "NTU", Gender.MALE, identity5, "Programmer",
                "92512512");
        Identity identity6 = new Identity(IdentityType.PASSPORT, "A9728172D");
        GuestManager.createGuest("Fang", "Li", "73232733", "SCSE", Gender.FEMALE, identity6, "Chinese", "96252552");
    }

    /**
     * Print the complete details of the guest
     * @param guest {@link Guest} object to print 
     */
    public static void printGuestDetails(Guest guest) {
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println(String.format("%-20s: %s", "Guest ID", guest.getGuestId()));
        System.out.println(String.format("%-20s: %s", "Name", guest.getName()));
        System.out.println(String.format("%-20s: %s", "Credit Card", guest.getCreditCard()));
        System.out.println(String.format("%-20s: %s", "Address", guest.getAddress()));
        System.out.println(String.format("%-20s: %s", "Gender", guest.getGender().genderAsStr));
        System.out.println(String.format("%-20s: %s", "Identity Type", guest.getIdentity().getType().identityTypeAsStr));
        System.out.println(String.format("%-20s: %s", "Identity No.", guest.getIdentity().getIdentityNo()));
        System.out.println(String.format("%-20s: %s", "Nationality", guest.getNationality()));
        System.out.println(String.format("%-20s: %s", "Contact No.", guest.getContact()));
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
    }
}
