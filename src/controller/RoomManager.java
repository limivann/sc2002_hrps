package src.controller;
import java.util.ArrayList;
import java.util.Collections;
import src.database.Database;
import src.database.FileType;
import src.model.DeluxeRoom;
import src.model.DoubleRoom;
import src.model.Order;
import src.model.Room;
import src.model.SingleRoom;
import src.model.VipSuite;
import src.model.enums.RoomStatus;
import src.model.enums.RoomType;

// for javadocs
import src.view.HotelAppView;
import src.view.RoomView;
/**
 * RoomManager is a controller class that acts as a "middleman"
 * between the view classes -  {@link HotelAppView} and {@link RoomView} and the model class - {@link Room}. <p>
 * 
 * It can initialize or update the room and print Room Status statistic report.
 * @author Lim Kang Wei, Zhang Kaichen, Ivan，Max
 * @version 1.0
 * @since 2022-04-04
 */
public class RoomManager {
    /**
     * Default constructor of the Room Manager
     */
    public RoomManager() {
    }

    /**
     * Method to update the Room price <p>
     * @param roomType new room type of the room
     * @param newPrice new price of the room
     * @return {@code true} if successful. Otherwise, {@code false}
     */
    public static boolean updateRoomPrice(RoomType roomType, double newPrice) {
        for (Room currentRoom : Database.ROOMS.values()) {
            if (currentRoom.getRoomType() == roomType) {
                double newRoomPrice = newPrice * (currentRoom.getIsWifiEnabled() ? 1.2 : 1);
                if (!currentRoom.setPrice(newRoomPrice)) {
                    System.out.println("Price is must be greater than 0");
                    return false;
                }
                Database.ROOMS.put(currentRoom.getRoomId(), currentRoom);
            }
        }
        Database.saveFileIntoDatabase(FileType.ROOMS);
        return true;
    }

    /**
     * Method to update the Room status by id <p>
     * @param roomId room id of the room
     * @param roomStatus room status of the room
     * @return {@code true} if successful. Otherwise, {@code false}
     */
    public static boolean updateRoomStatus(String roomId, RoomStatus roomStatus) {
        if (Database.ROOMS.containsKey(roomId)) {
            Room targetRoom = Database.ROOMS.get(roomId);
            targetRoom.setRoomStatus(roomStatus);
            Database.ROOMS.put(roomId, targetRoom);
            Database.saveFileIntoDatabase(FileType.ROOMS);
            return true;
        } else {
            System.out.println("Room id doesn't exists. ");
            return false;
        }
    }

    /**
     * Method to update the Room status by floor and room number <p>
     * @param floorNumber floor number of the room
     * @param roomNumber room number of the room
     * @param roomStatus room status of the room
     * @param guestIds Ids of the guest(s) to be added/removed from the room
     * @return {@code true} if successful. Otherwise, {@code false} if the room id not found.
     */
    public static boolean updateRoomStatus(int floorNumber, int roomNumber, RoomStatus roomStatus, ArrayList<String> guestIds) {
        String roomId = String.format("%02d%02d", floorNumber, roomNumber);
        if (Database.ROOMS.containsKey(roomId)) {
            Room targetRoom = Database.ROOMS.get(roomId);
            targetRoom.setRoomStatus(roomStatus);
            // set guest details
            targetRoom.setGuestIds(guestIds);
            Database.ROOMS.put(roomId, targetRoom);
            Database.saveFileIntoDatabase(FileType.ROOMS);
            return true;
        } else {
            System.out.println("Room id doesn't exists. ");
            return false;
        }
    }

    /**
     * Method to search a room by floor and room number of the room <p>
     * @param floor floor number of the room
     * @param room room number of the room
     * @return the room object correspond to the room id. Otherwise, {@code null} if the room is not found
     */
    public static Room searchRoom(int floor, int room) {
        String roomId = String.format("%02d%02d", floor, room);
        if (Database.ROOMS.containsKey(roomId)) {
            return Database.ROOMS.get(roomId);
        }
        return null;
    }

    /**
     * Method to search a room by room id <p>
     * @param roomId room id of the room
     * @return the room object correspond to the room id. Otherwise, {@code null} if the room is not found
     */
    public static Room searchRoom(String roomId) {
        if (Database.ROOMS.containsKey(roomId)) {
            return Database.ROOMS.get(roomId);
        }
        return null;
    }

    /**
     * Print out the room correspond to the room id <p>
     * @param roomId room id of the room
     */
    public static void printRoom(String roomId) {
        if (Database.ROOMS.containsKey(roomId)) {
            printRoomDetails(roomId);
        } else {
            System.out.println("Room doesn't exists.");
        }
    }

    /**
     * Print out the room correspond to the floor and room number <p>
     * @param floor the floor number of the room
     * @param room the room number of the room
     */
    public static void printRoom(int floor, int room) {
        String roomId = String.format("%02d%02d", floor, room);
        if (Database.ROOMS.containsKey(roomId)) {
            printRoomDetails(roomId);
        } else {
            System.out.println("Room doesn't exists.");
        }
    }

    /**
     * Print out the room status
     */
    public static void printRoomStatus() {
        ArrayList<Room> vacantRooms = new ArrayList<Room>();
        ArrayList<Room> occupiedRooms = new ArrayList<Room>();
        ArrayList<Room> reservedRooms = new ArrayList<Room>();
        ArrayList<Room> underMaintenanceRooms = new ArrayList<Room>();

        vacantRooms = getRoomsByStatus(RoomStatus.VACANT);
        occupiedRooms = getRoomsByStatus(RoomStatus.OCCUPIED);
        reservedRooms = getRoomsByStatus(RoomStatus.RESERVED);
        underMaintenanceRooms = getRoomsByStatus(RoomStatus.UNDER_MAINTENANCE);

        System.out.println(String.format("%-9s:", "Vacant"));
        System.out.printf("\t Rooms: ");
        for (Room r : vacantRooms) {
            System.out.printf("%s, ", r.getRoomId());
        }
        System.out.println();

        System.out.println(String.format("%-9s:", "Occupied"));
        System.out.printf("\t Rooms: ");
        for (Room r : occupiedRooms) {
            System.out.printf("%s, ", r.getRoomId());
        }
        System.out.println();

        System.out.println(String.format("%-9s:", "Reserved"));
        System.out.printf("\t Rooms: ");
        for (Room r : reservedRooms) {
            System.out.printf("%s, ", r.getRoomId());
        }
        System.out.println();

        System.out.println(String.format("%-9s:", "Under Maintenance"));
        System.out.printf("\t Rooms: ");
        for (Room r : underMaintenanceRooms) {
            System.out.printf("%s, ", r.getRoomId());
        }
        System.out.println();
    }

    /**
     * Get all the room that currently is in one room status <p>
     * @param roomStatus the room status that you want to search
     * @return an ArrayList that has all {@link Room} object under the input {@link RoomStatus}
     */
    public static ArrayList<Room> getRoomsByStatus(RoomStatus roomStatus) {
        ArrayList<Room> roomsByStatus = new ArrayList<Room>();
        for (Room room : Database.ROOMS.values()) {
            if (room.getRoomStatus() == roomStatus) {
                roomsByStatus.add(room);
            }
        }
        Collections.sort(roomsByStatus);
        return roomsByStatus;
    }

    /**
     * Get the room by room type and by room status <p>
     * @param roomType the type of room want to search
     * @param roomStatus the status of room that want to search
     * @return the ArrayList that has {@link Room} object with desired {@link RoomType} and {@link RoomStatus}
     */
    public static ArrayList<Room> getRoomsByRoomTypeAndStatus(RoomType roomType, RoomStatus roomStatus) {
        ArrayList<Room> roomsByRoomType = new ArrayList<Room>();
        for (Room room : Database.ROOMS.values()) {
            if (room.getRoomType() == roomType && room.getRoomStatus() == roomStatus) {
                roomsByRoomType.add(room);
            }
        }
        Collections.sort(roomsByRoomType);
        return roomsByRoomType;
    }

    /**
     * Print out the room by status <p>
     * @param roomStatus room status that want to print
     */
    public static void printOccupancyRate(RoomStatus roomStatus) {
        ArrayList<Room> vacantSingleRooms = new ArrayList<Room>();
        ArrayList<Room> vacantDoubleRooms = new ArrayList<Room>();
        ArrayList<Room> vacantDeluxeRooms = new ArrayList<Room>();
        ArrayList<Room> vacantVipSuites = new ArrayList<Room>();

        vacantSingleRooms = getRoomsByRoomTypeAndStatus(RoomType.SINGLE, RoomStatus.VACANT);
        vacantDoubleRooms = getRoomsByRoomTypeAndStatus(RoomType.DOUBLE, RoomStatus.VACANT);
        vacantDeluxeRooms = getRoomsByRoomTypeAndStatus(RoomType.DELUXE, RoomStatus.VACANT);
        vacantVipSuites = getRoomsByRoomTypeAndStatus(RoomType.VIP_SUITE, RoomStatus.VACANT);

        // Print rooms
        System.out.println(
                String.format("Single: Number: %d out of %d", vacantSingleRooms.size(), Database.numOfSingleRooms));
        System.out.print("\t Rooms: ");
        for (Room r : vacantSingleRooms) {
            System.out.printf("%s, ", r.getRoomId());
        }
        System.out.println();

        System.out.println(
                String.format("Double: Number: %d out of %d", vacantDoubleRooms.size(), Database.numOfDoubleRooms));
        System.out.print("\t Rooms: ");
        for (Room r : vacantDoubleRooms) {
            System.out.printf("%s, ", r.getRoomId());
        }
        System.out.println();

        System.out.println(
                String.format("Deluxe: Number: %d out of %d", vacantDeluxeRooms.size(), Database.numOfDeluxeRooms));
        System.out.print("\t Rooms: ");
        for (Room r : vacantDeluxeRooms) {
            System.out.printf("%s, ", r.getRoomId());
        }
        System.out.println();

        System.out.println(
                String.format("Vip Suites: Number: %d out of %d", vacantVipSuites.size(), Database.numOfVipSuites));
        System.out.print("\t Rooms: ");
        for (Room r : vacantVipSuites) {
            System.out.printf("%s, ", r.getRoomId());
        }
        System.out.println();
    }

    /**
     * Initializer for all the rooms in the hotel
     */
    public static void initializeAllRooms() {
        // One floor 12 rooms, 4 floors in total

        int numOfSingleRooms = Database.numOfSingleRooms;
        int numOfDoubleRooms = Database.numOfDoubleRooms;
        int numOfDeluxeRooms = Database.numOfDeluxeRooms;
        int numOfVipSuites = Database.numOfVipSuites;

        // One in 4 rooms are smoking allowed
        // All vip suites are wifi enabled
        // One in 3 rooms are not wifi enabled
        int isSmokingAllowedCounter = 0;
        int nonWifiEnabledCounter = 0;

        int totalNumOfRooms = numOfSingleRooms + numOfDoubleRooms + numOfDeluxeRooms + numOfVipSuites;
        if (totalNumOfRooms != 48) {
            System.out.println("Room count is not 48");
            return;
        }

        boolean isWifiEnabled = false;
        boolean isSmokingAllowed = false;
        for (int floor = 1; floor <= 4; floor++) {
            for (int room = 1; room <= 12; room++) {
                String roomId = String.format("%02d%02d", floor, room);
                Room newRoom = null;
                isWifiEnabled = nonWifiEnabledCounter == 0 ? false : true;
                isSmokingAllowed = isSmokingAllowedCounter == 0 ? true : false;
                // Fill with single room first
                if (numOfSingleRooms > 0) {
                    newRoom = createRoom(RoomType.SINGLE, roomId, floor, room, RoomStatus.VACANT, isWifiEnabled,
                            isSmokingAllowed);
                    numOfSingleRooms--;
                } else if (numOfDoubleRooms > 0) {
                    newRoom = createRoom(RoomType.DOUBLE, roomId, floor, room, RoomStatus.VACANT, isWifiEnabled,
                            isSmokingAllowed);
                    numOfDoubleRooms--;
                } else if (numOfDeluxeRooms > 0) {
                    newRoom = createRoom(RoomType.DELUXE, roomId, floor, room, RoomStatus.VACANT, isWifiEnabled,
                            isSmokingAllowed);
                    numOfDeluxeRooms--;
                } else {
                    newRoom = createRoom(RoomType.VIP_SUITE, roomId, floor, room, RoomStatus.VACANT, true,
                            isSmokingAllowed);
                    numOfVipSuites--;
                }

                nonWifiEnabledCounter = (nonWifiEnabledCounter + 1) % 3;
                isSmokingAllowedCounter = (isSmokingAllowedCounter + 1) % 4;
                if (newRoom != null) {
                    Database.ROOMS.put(roomId, newRoom);
                }
            }
        }
        Database.saveFileIntoDatabase(FileType.ROOMS);
    }

    /**
     * Method to create rooms <p>
     * See {@link RoomType} For the different room type <p>
     * See {@link RoomStatus} For the different status of the room <p>
     * @param roomType Type of the room
     * @param roomId the room Id for the room
     * @param floorNumber Floor number of the room
     * @param roomNumber Room number of the room
     * @param roomStatus Status of the room
     * @param isWifiEnabled whether the wifi is enabled in the room
     * @param isSmokingAllowed whether the smoking is allowed in the room
     * @return {@link Room} object that is being created
     */
    public static Room createRoom(RoomType roomType, String roomId, int floorNumber, int roomNumber,
            RoomStatus roomStatus,
            boolean isWifiEnabled, boolean isSmokingAllowed) {

        double roomPrice = -1;
        double singleRoomDefaultPrice = 200;
        double doubleRoomDefaultPrice = 360;
        double deluxeRoomDefaultPrice = 400;
        double vipSuiteDefaultPrice = 1000;
        
        Room newRoom = null;
        switch (roomType) {
            case SINGLE:
                roomPrice = singleRoomDefaultPrice * (isWifiEnabled ? 1.2 : 1);
                newRoom = new SingleRoom(roomId, floorNumber, roomNumber, roomStatus, isWifiEnabled, isSmokingAllowed,
                        roomPrice);
                break;
            case DOUBLE:
                roomPrice = doubleRoomDefaultPrice * (isWifiEnabled ? 1.2 : 1);
                newRoom = new DoubleRoom(roomId, floorNumber, roomNumber, roomStatus, isWifiEnabled, isSmokingAllowed,
                        roomPrice);
                break;
            case DELUXE:
                roomPrice = deluxeRoomDefaultPrice * (isWifiEnabled ? 1.2 : 1);
                newRoom = new DeluxeRoom(roomId, floorNumber, roomNumber, roomStatus, isWifiEnabled, isSmokingAllowed,
                        roomPrice);
                break;
            case VIP_SUITE:
                roomPrice = vipSuiteDefaultPrice * (isWifiEnabled ? 1.2 : 1);
                newRoom = new VipSuite(roomId, floorNumber, roomNumber, roomStatus, isWifiEnabled, isSmokingAllowed,
                        roomPrice);
                break;
            default:
                break;
        }
        return newRoom;
    }


    /**
     * Validate the hotel has this room id or not
     * @param roomId room id of the room you want to check
     * @return {@code true} if contains this room. Otherwise, {@code false} if room id does not exist.
     */
    public static boolean validateRoomId(String roomId) {
        if (Database.ROOMS.containsKey(roomId)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the room has vacancy <p>
     * See {@link RoomStatus} for the different types of room status
     * @param roomId room id of the room you want to check
     * @param roomStatus room status of the room to compare
     * @return {@code true} if this room has vacancy. Otherwise, {@code false} if room id does not exist
     */
    public static boolean checkRoomVacancy(String roomId, RoomStatus roomStatus) {
        if (validateRoomId(roomId)) {
            return Database.ROOMS.get(roomId).getRoomStatus() == roomStatus;
        }
        return false;
    }

    /**
     * Validate if the number of pax exceeds the maximum capacity of the room or not.
     * @param roomId room id of the room you want to check
     * @param numOfPax the number of pax of the room
     * @return {@code true} if the number of pax does not exceed the room capacity. Otherwise, {@code false} if number of pax is not valid.
     */
    public static boolean validateNumOfPax(String roomId, int numOfPax) {
        if (numOfPax <= 0) {
            return false;
        }
        if (validateRoomId(roomId)) {
            Room room = searchRoom(roomId);
            return numOfPax <= room.getMaxCapacity();
        }
        return false;
    }

    /**
     * Method to update the room's guest details. <p>
     * @param roomId room id of the room you want to update
     * @param guestIds Ids of the guest(s)
     * @return {@code true} if updating of guest details is successful. Otherwise, {@code false} if guest id does not exist.
     */
    public static boolean updateRoomGuestDetails(String roomId, ArrayList<String> guestIds) {
        Room roomToUpdate = searchRoom(roomId);
        return roomToUpdate.setGuestIds(guestIds);
    }

    /**
     * Method to get room price of given room id
     * @param roomId Id of the room
     * @return Room price in double if room id exist. Otherwise, -1. 
     */
    public static double getRoomPrice(String roomId) {
        if (validateRoomId(roomId)) {
            return Database.ROOMS.get(roomId).getPrice();
        }
        return -1;
    }

    /**
     * Print out the complete detail of the room.
     * @param roomId Id of the room
     */
    public static void printRoomDetails(String roomId) {
        Room target = searchRoom(roomId);
        String guestIds = "";
        if (target.getGuestIds() != null) {
            for (String guestId : target.getGuestIds()) {
                guestIds += guestId + " ";
            }
        }
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
        System.out.println(String.format("%-20s: %s", "Room Number", target.getRoomId()));
        System.out.println(String.format("%-20s: %s", "Room Status", target.getRoomStatus().roomStatusAsStr));
        System.out.println(String.format("%-20s: %s", "Room Type", target.getRoomType().roomTypeAsStr));
        if (target.getRoomStatus() == RoomStatus.OCCUPIED || target.getRoomStatus() == RoomStatus.RESERVED) {
            System.out.println(String.format("%-20s: %s", "Guest(s)", guestIds));
        }
        System.out.println(String.format("%-20s: %s", "Room Price", target.getPrice()));
        System.out.println(String.format("%-20s: %s", "Wifi Enabled", target.getIsWifiEnabled()));
        System.out.println(String.format("%-20s: %s", "Smoking Allowed", target.getIsSmokingAllowed()));
        System.out.println(String.format("%-40s", "").replace(" ", "-"));
    }
    
    /**
     * Method to update orders made by the room
     * @param roomId Id of room to update
     * @param order {@link Order} of the room had made
     * @param clearRoom {@code true} if we want to clear the room. Otherwise, {@code false}.
     * @return {@code true} if update orders of the room is successful. Otherwise, {@code false} if room does not exist.
     */
    public static boolean updateRoomOrders(String roomId, Order order, boolean clearRoom) {
        Room targetRoom = searchRoom(roomId);
        if (targetRoom == null) {
            return false;
        }
        if (clearRoom) {
            ArrayList<Order> emptyOrder = new ArrayList<Order>();
            targetRoom.setOrders(emptyOrder);
            return true;
        } else {
            targetRoom.getOrders().add(order);
        }
        Database.saveFileIntoDatabase(FileType.ROOMS);
        return true;
    }
}
