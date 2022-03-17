package src.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import src.model.Guest;

public class GuestManager extends MainManager{
    HashMap<String, Guest> GuestList =  new HashMap<String, Guest>();
    @Override
    public void create() {
        // TODO Auto-generated method stub
        Guest new_Guest = new Guest();
        new_Guest.add_personal_detail();
        GuestList.put(new_Guest.getguest_id(), new_Guest);
        //GuestList.get(new_Guest.getguest_id()).printGuestDetails();
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub
        System.out.println("Search for guest that you want to remove:");
        ArrayList<Guest> removelist = search();
        System.out.println("The remove Guest information:");
        for (Guest guest : removelist) {
            GuestList.remove(guest.getguest_id()).printGuestDetails();;
        }
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        System.out.println("Search for guest that you want to update");
        ArrayList<Guest> updateList = search();
        for (Guest guest: updateList) {
            GuestList.get(guest.getguest_id()).update_detail();
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

    public void printallguest(){
        System.out.println("-------------");
        System.out.println("Guest List:");
        for (Guest g : GuestList.values()) {
            System.out.printf("Guest Name: %s\n", g.getName());
            System.out.printf("Guest ID: %s\n", g.getguest_id());
        }
    }
    
}
