package src.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import javax.xml.crypto.Data;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Guest;
import src.model.Identity;
import src.model.enums.Gender;

public class GuestManager {
    public GuestManager() {

    }
    
    public static void createGuest(String firstName, String lastName, String creditCardNumber, String address,
            Gender gender, Identity identity, String nationality, String contactNo) {
        String name = firstName + " " + lastName;
        int gid = Database.GUESTS.size() + 1;
        String guestId = String.format("G%04d", gid);
        Guest newGuest = new Guest(name, firstName, lastName, creditCardNumber, address, gender, identity, nationality,
                contactNo, guestId);
        Database.GUESTS.put(guestId, newGuest);
        Database.saveFileIntoDatabase(FileType.GUESTS);
        System.out.println("Guest created! Guest ID: " + guestId);
    }

    // All update guest helpers
    // For updating one value only
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
    public static ArrayList<Guest> searchGuest(String guestId) {
        ArrayList<Guest> searchList = searchGuestById(guestId);
        for (Guest guest : searchList) {
            guest.printGuestDetails();
        }
        return searchList;
    }

    public static ArrayList<Guest> searchGuestById(String guestId) {
        ArrayList<Guest> searchList = new ArrayList<Guest>();
        Guest searchedGuest = Database.GUESTS.get(guestId);
        if (searchedGuest != null) {
            searchList.add(searchedGuest);
        }
        return searchList;
    }

    public static void removeGuest(String guestId) {
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
                // cancel delete
            }
        }
        Database.saveFileIntoDatabase(FileType.GUESTS);
    }
    
    public static void printAllGuests() {
        HashMap<String, Guest> toIterate = Helper.copyHashMap(Database.GUESTS);
        Iterator it = toIterate.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
    public static void main(String[] args) {
        GuestManager gm = new GuestManager();
        GuestManager.printAllGuests();
        
    }
}
