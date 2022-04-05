package src.view;

import src.controller.RoomManager;
import src.helper.Helper;
import src.model.enums.RoomStatus;
import java.util.ArrayList;
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
        super();
    }

    @Override
    /**
     * View Menu for the Room
     */
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Room View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Update room status");
        System.out.println("(2) Search room");
        System.out.println("(3) Print rooms by status");
        System.out.println("(4) Print rooms by occupancy rate");
        System.out.println("(5) Exit Room View");
    }

    @Override
    /**
     * View Application for the room
     */
    public void viewapp() {
        int opt = -1;
        do {
            printMenu();
            opt = Helper.readInt(1,5);
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
            if (opt != 5) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 5);
    }
    
    /**
     * Prompt function to search room <p>
     * See {@link RoomManager} to check for the printing room method <p>
     * @param printResults whether to print out the results or not 
     */
    public void promptSearchRoom(boolean printResults) {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Room View > Search room");
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
     * See {@link RoomManager} for update function <p>
     * @return {@code true} if update successfully. Otherwise, {@code false}
     */
    public boolean promptUpdateRoomStatus() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Room View > Update room status");
        System.out.println("Enter the floor number");
        int floor = Helper.readInt();
        System.out.println("Enter the room number");
        int room = Helper.readInt();
        printRoomStatusMenu();
        int opt = Helper.readInt(1,4);
        RoomStatus newStatus = RoomStatus.VACANT;
        Room targetRoom = RoomManager.searchRoom(floor, room);
        if (targetRoom == null) {
            System.out.println("Room does not exist");
            return false;
        }
        if (targetRoom.getRoomStatus() == RoomStatus.RESERVED || targetRoom.getRoomStatus() == RoomStatus.OCCUPIED) {
            System.out.println("Please check out the guest before changing the room status");
            return false;
        }
        ArrayList<String> guestIds = new ArrayList<String>();
        switch (opt) {
            case 1:
                newStatus = RoomStatus.VACANT;
                break;
            case 2:
                System.out.println("Error: Please make a walk in reservation to update the room to occupied");
                return false;
            case 3:
                System.out.println("Error: Please make a reservation to update the room to reserved");
                return false;
            case 4:
                newStatus = RoomStatus.UNDER_MAINTENANCE;
                break;
            default:
                break;
        }
        return RoomManager.updateRoomStatus(floor, room, newStatus, guestIds);
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
     * See {@link RoomManager} for printing function
     */
    public void printRoomByStatus() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Room View > Print rooms by status");
        RoomManager.printRoomStatus();
    }
    
    /**
     * Prompt function for printing room by occupancy rate <p>
     * See {@link RoomManager} for printing function
     */
    public void printRoomByOccupancyRate() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Room View > Print rooms by occupancy rate");
        RoomManager.printOccupancyRate(RoomStatus.VACANT);
    }
}
