package src.view;

import src.controller.RoomManager;
import src.helper.Helper;

import java.util.InputMismatchException;

import src.model.enums.*;

// for javadocs
import src.model.Room;

/**
 * The Class that shows the view of {@link Room}.
 * @author Lim Kang Wei
 * @version 1.0
 * @since 2022-03-30
 */
public class RoomView extends MainView{;

    /**
     * the default constructor for the room view
     */
    public RoomView() {
        
    }

    @Override
    /**
     * View Menu for the Room
     */
    public void printMenu() {
        System.out.println("Please select an option (1-5)");
        System.out.println("(1) Update room status");
        System.out.println("(2) Search room");
        System.out.println("(3) Print rooms by status");
        System.out.println("(4) Print rooms by occupancy rate");
        System.out.println("(5) Exit");
    }

    @Override
    /**
     * View Application for the room
     */
    public void viewapp() {
        int opt = -1;
        do {
            try {
                printMenu();
                opt = Helper.readInt();
                switch (opt) {
                    case 1:
                        if (promptUpdateRoomStatus()) {
                            System.out.println("Status updated successfully.");
                        } else {
                            System.out.println("Unsuccessful status update");
                        }
                        break;
                    case 2:
                        promptSearchRoom(true);
                        break;
                    case 3:
                        printRoomByStatus();
                        break;
                    case 4:
                        printRoomByOccupancyRate();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong data type! ");
                System.out.println("________________\n");
            }
        } while (opt != 5);
    }
    
    /**
     * Prompt function to search room <p>
     * see {@link RoomManager} to check for the printing room method <p>
     * @param printResults whether to print out the results or not 
     */
    public void promptSearchRoom(boolean printResults) {
        System.out.println("Enter the floor number");
        int floor = Helper.readInt();
        System.out.println("Enter the room number");
        int room = Helper.readInt();
        if (printResults) {
            RoomManager.printRoom(floor, room);
        }
    }
    
    /**
     * Prompt function to update room status <p>
     * see {@link RoomManager} for update function <p>
     * @return {@code true} if update successfully. Otherwise, {@code false}
     */
    public boolean promptUpdateRoomStatus() {
        System.out.println("Enter the floor number");
        int floor = Helper.readInt();
        System.out.println("Enter the room number");
        int room = Helper.readInt();
        printRoomStatusMenu();
        int opt = Helper.readInt();
        RoomStatus newStatus = RoomStatus.VACANT;
        int guestId = -1;
        // TODO: Fix bug
        switch (opt) {
            case 1:
                newStatus = RoomStatus.VACANT;
                break;
            case 2:
                newStatus = RoomStatus.OCCUPIED;
                System.out.println("Please enter the guest's id");
                guestId = Helper.readInt();
                break;
            case 3:
                newStatus = RoomStatus.RESERVED;
                System.out.println("Please enter the guest's id");
                break;
            case 4:
                newStatus = RoomStatus.UNDER_MAINTENANCE;
                break;
        }
        return RoomManager.updateRoomStatus(floor, room, newStatus);
    }

    /**
     * View Menu for update room status
     */
    private void printRoomStatusMenu() {
        System.out.println("Which is the new status? (1-4)");
        System.out.println("(1) Vacant");
        System.out.println("(2) Occupied");
        System.out.println("(3) Reserved");
        System.out.println("(4) Under maintenance");
    }

    /**
     * Prompt function for printing room by status  <p>
     * see {@link RoomManager} for printing function
     */
    public void printRoomByStatus() {
        RoomManager.printRoomStatus();
    }
    
    /**
     * Prompt function for printing room by occupancy rate <p>
     * see {@link RoomManager} for printing function
     */
    public void printRoomByOccupancyRate(){
        RoomManager.printOccupancyRate(RoomStatus.VACANT);
    }
}
