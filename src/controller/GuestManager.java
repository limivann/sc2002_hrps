package src.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

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
        System.out.println("Guest created!");
    }
    
    public static void printGuests() {
        Iterator it = Database.GUESTS.entrySet().iterator();
        System.out.println("Here");
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
    public static void main(String[] args) {
        GuestManager gm = new GuestManager();
        GuestManager.printGuests();
        
    }
}
