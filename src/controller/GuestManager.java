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
import src.model.Guest;
import src.model.Identity;
import src.model.enums.Gender;

public class GuestManager extends MainManager {
    public GuestManager() {
        Database db = new Database();
    }
    HashMap<String, Guest> GuestList =  new HashMap<String, Guest>();
    @Override
    public void create() {
        Guest new_Guest = new Guest();
        new_Guest.add_personal_detail();
        GuestList.put(new_Guest.getGuestId(), new_Guest);
        //GuestList.get(new_Guest.getguest_id()).printGuestDetails();
    }

    @Override
    public void remove() {
        System.out.println("Search for guest that you want to remove:");
        ArrayList<Guest> removelist = search();
        System.out.println("The remove Guest information:");
        for (Guest guest : removelist) {
            GuestList.remove(guest.getGuestId()).printGuestDetails();;
        }
        
    }

    @Override
    public void update() {
        System.out.println("Search for guest that you want to update");
        ArrayList<Guest> updateList = search();
        for (Guest guest: updateList) {
            GuestList.get(guest.getGuestId()).update_detail();
        }
        
    }

    public ArrayList<Guest> search(){
        ArrayList<Guest> searchlist = new ArrayList<Guest>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the guest id that you want to search: ");
        String guest_id = sc.nextLine();
        searchlist.add(GuestList.get(guest_id));
        return searchlist;
    }

    public void printallguest() {
        System.out.println("-------------");
        System.out.println("Guest List:");
        for (Guest g : GuestList.values()) {
            System.out.printf("Guest Name: %s\n", g.getName());
            System.out.printf("Guest ID: %s\n", g.getGuestId());
        }
    }
    
    public static void createGuest(String firstName, String lastName, String creditCardNumber, String address,
            Gender gender, Identity identity, String nationality, String contactNo) {
        String name = firstName + " " + lastName;
        int gid = Database.GUESTS.size() + 1;
        String guestId = String.format("G%04d", gid);
        Guest newGuest = new Guest(name, firstName, lastName, creditCardNumber, address, gender, identity, nationality,
                contactNo, guestId);
        Database.GUESTS.put(guestId, newGuest);
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
    }
    
    public static void updateGuest(String guestId, int attributeCode, Identity identity) {
        ArrayList<Guest> updateList = searchGuestById(guestId);
        for (Guest guest : updateList) {
            Guest guestToUpdate;
            switch (attributeCode) {
                case 5:
                    guestToUpdate = Database.GUESTS.get(guestId);
                    guestToUpdate.setIdentity(identity);;
                    Database.GUESTS.put(guest.getGuestId(), guestToUpdate);
                    break;
                default:
                    break;
            }
        }
    }

    // Search Guest
    public static void searchGuest(String guestId) {
        ArrayList<Guest> searchList = searchGuestById(guestId);
        System.out.println("Whats");
        for (Guest guest : searchList) {
            guest.printGuestDetails();
        }
    }

    public static ArrayList<Guest> searchGuestById(String guestId) {
        ArrayList<Guest> searchList = new ArrayList<Guest>();
        Guest searchedGuest = Database.GUESTS.get(guestId);
        if (searchedGuest != null) {
            searchList.add(searchedGuest);
        }
        return searchList;
    }
    
    public static void printAllGuests() {
        Iterator it = Database.GUESTS.entrySet().iterator();
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
