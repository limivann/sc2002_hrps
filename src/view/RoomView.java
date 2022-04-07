package src.view;

import src.controller.RoomManager;
import src.helper.Helper;
import src.model.enums.RoomStatus;
import src.model.enums.RoomType;

import java.util.ArrayList;
// for javadocs
import src.model.Room;

/**
 * RoomView provides the view to take user input which calls {@link RoomManager} to manage {@link Room}.
 * @author Lim Kang Wei
 * @version 1.0
 * @since 2022-04-06
 */
public class RoomView extends MainView{;

    /**
     * Default constructor for the Roomview.
     */
    public RoomView() {
        super();
    }

    /**
     * View Menu of the RoomView.
     */
    @Override
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Room View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Update room status");
        System.out.println("(2) Search room");
        System.out.println("(3) Print rooms by status");
        System.out.println("(4) Print rooms by occupancy rate");
        System.out.println("(5) Edit Room Price");
        System.out.println("(6) Exit Room View");
    }
    /**
     * View Application of the RoomView. <p>
     * see {@link RoomManager} for more {@link Room} management details.
     */ 
    @Override
    public void viewApp() {
        int opt = -1;
        do {
            printMenu();
            opt = Helper.readInt(1,6);
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
                    if (!promptEditRoomPrice()) {
                        System.out.println("Edit room price unsuccessful");
                    } else {
                        System.out.println("Edit room price successful");
                    }
                    
                case 6:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            if (opt != 6) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 6);
    }
    
    /**
     * Prompt function to search room <p>
     * See {@link RoomManager} to check for the printing room method <p>
     * @param printResults whether to print out the results or not 
     */
    private void promptSearchRoom(boolean printResults) {
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
    private boolean promptUpdateRoomStatus() {
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
            System.out.println("Please check in / check out the guest before changing the room status");
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
    private void printRoomByStatus() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Room View > Print rooms by status");
        RoomManager.printRoomStatus();
    }
    
    /**
     * Prompt function for printing room by occupancy rate <p>
     * See {@link RoomManager} for printing function
     */
    private void printRoomByOccupancyRate() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Room View > Print rooms by occupancy rate");
        RoomManager.printOccupancyRate(RoomStatus.VACANT);
    }

    /**
     * Prompt function for editing room price
     * @return {@code true} if update of room price is successful. Otherwise, {@code false}.
     */
    private boolean promptEditRoomPrice() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Room View > Edit Room Price");
        printRoomTypeMenu();
        int roomTypeOpt = Helper.readInt(1, 4);
        System.out.println("Please enter a new price for the room");
        double newPrice = Helper.readDouble();
        switch (roomTypeOpt) {
            case 1:
                return RoomManager.updateRoomPrice(RoomType.SINGLE, newPrice);
            case 2:
                return RoomManager.updateRoomPrice(RoomType.DOUBLE, newPrice);
            case 3:
                return RoomManager.updateRoomPrice(RoomType.DELUXE, newPrice);
            case 4:
                return RoomManager.updateRoomPrice(RoomType.VIP_SUITE, newPrice);
            default:
                break;
        }
        return false;
    }

    /**
     * View Menu for room types
     */
    private void printRoomTypeMenu() {
        System.out.println("Please enter a room type (1-4)");
        System.out.println("(1) Single Room");
        System.out.println("(2) Double Room");
        System.out.println("(3) Deluxe Room");
        System.out.println("(4) Vip Suite");
    }
}
