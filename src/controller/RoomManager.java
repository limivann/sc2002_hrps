package src.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Room;
import src.model.enums.*;

/**
 * The Class that manages {@link Room}.
 * @author Lim Kang Wei, Zhang Kaichen, Ivan
 * @version 1.0
 * @since 30-03-2022
 */
public class RoomManager {
    /**
     * The HashMap to store all the room detail
     */
    HashMap<String, Room> RoomList = new HashMap<String, Room>();

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
        HashMap<String, Room> toIterate = Helper.copyHashMap(Database.ROOMS);
        Iterator it = toIterate.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Object currentValue = pair.getValue();
            if (!(currentValue instanceof Room)) {
                return false;
            }
            Room currentRoom = (Room) currentValue;
            if (currentRoom.getType() == roomType) {
                double newRoomPrice = calculateRoomPrice(roomType, currentRoom.getIsWifiEnabled());
                if (!currentRoom.setPrice(newRoomPrice)) {
                    return false;
                }
                Database.ROOMS.put(currentRoom.getRoomId(), currentRoom);
            }
            it.remove(); // avoids a ConcurrentModificationException
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
            // TODO: Throw exception
            System.out.println("Room id doesn't exists. ");
            return false;
        }
    }

    /**
     * Method to update the Room status by floor and room number <p>
     * @param floorNumber floor number of the room
     * @param roomNumber room number of the room
     * @param roomStatus room status of the room
     * @return {@code true} if successful. Otherwise, {@code false} if the room id not found.
     */
    public static boolean updateRoomStatus(int floorNumber, int roomNumber, RoomStatus roomStatus) {
        String roomId = String.format("%02d-%02d", floorNumber, roomNumber);
        if (Database.ROOMS.containsKey(roomId)) {
            Room targetRoom = Database.ROOMS.get(roomId);
            targetRoom.setRoomStatus(roomStatus);
            Database.ROOMS.put(roomId, targetRoom);
            Database.saveFileIntoDatabase(FileType.ROOMS);
            return true;
        } else {
            // TODO: Throw exception
            System.out.println("Room id doesn't exists. ");
            return false;
        }
    }

    /**
     * Method to search a room by floor and room number of the room <p>
     * @param floor floor number of the room
     * @param room room number of the room
     * @return the room object correspond to the room id
     */
    public static Room searchRoom(int floor, int room) {
        String roomId = String.format("%02d-%02d", floor, room);
        return Database.ROOMS.get(roomId);
    }

    /**
     * Method to search a room by room id <p>
     * @param roomId room id of the room
     * @return the room object correspond to the room id
     */
    public static Room searchRoom(String roomId) {
        return Database.ROOMS.get(roomId);
    }

    /**
     * Print out the room correspond to the room id <p>
     * @param roomId room id of the room
     */
    public static void printRoom(String roomId) {
        if (Database.ROOMS.containsKey(roomId)) {
            Room target = searchRoom(roomId);
            target.printRoomDetails();
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
        String roomId = String.format("%02d-%02d", floor, room);
        if (Database.ROOMS.containsKey(roomId)) {
            Room target = searchRoom(floor, room);
            target.printRoomDetails();
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
     * @return an ArrayList that has all Room object under the input roomStatus
     */
    public static ArrayList<Room> getRoomsByStatus(RoomStatus roomStatus) {
        ArrayList<Room> roomsByStatus = new ArrayList<Room>();
        HashMap<String, Room> toIterate = Helper.copyHashMap(Database.ROOMS);
        Iterator it = toIterate.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Object currentValue = pair.getValue();
            if (!(currentValue instanceof Room)) {
                // pass
            } else {
                Room currentRoom = (Room) currentValue;
                if (currentRoom.getRoomStatus() == roomStatus) {
                    roomsByStatus.add(currentRoom);
                }
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        return roomsByStatus;
    }

    /**
     * Get the room by room type and by room status <p>
     * @param roomType the type of room want to search
     * @param roomStatus the status of room that want to search
     * @return the ArrayList that has the input room type and status
     */
    public static ArrayList<Room> getRoomsByRoomTypeAndStatus(RoomType roomType, RoomStatus roomStatus) {
        ArrayList<Room> roomsByRoomType = new ArrayList<Room>();
        HashMap<String, Room> toIterate = Helper.copyHashMap(Database.ROOMS);
        Iterator it = toIterate.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Object currentValue = pair.getValue();
            if (!(currentValue instanceof Room)) {
                // pass
            } else {
                Room currentRoom = (Room) currentValue;
                if (currentRoom.getType() == roomType && currentRoom.getRoomStatus() == roomStatus) {
                    roomsByRoomType.add(currentRoom);
                }
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        return roomsByRoomType;
    }

    /**
     * Print out the room by status <p>
     * @param roomStatus room status that want to print
     */
    public static void printOccupancyRate(RoomStatus roomStatus) {
        // TODO: Make this reusable on other room statuses
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
     * Print out all the room details <p>
     * see {@link Room#toString()} to see the toString method
     */
    public static void printAllRooms() {
        HashMap<String, Room> toIterate = Helper.copyHashMap(Database.ROOMS);
        Iterator it = toIterate.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    /**
     * Initializer for all the room in the hotel
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
            // TODO: Throw error
            System.out.println("Room count is not 48");
            return;
        }

        boolean isWifiEnabled = false;
        boolean isSmokingAllowed = false;
        for (int floor = 1; floor <= 4; floor++) {
            for (int room = 1; room <= 12; room++) {
                String roomId = String.format("%02d-%02d", floor, room);
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
     * constructor of the room <p>
     * see {@link RoomType} For the different room type <p>
     * see {@link RoomStatus} For the different status of the room <p>
     * @param roomType Type of the room
     * @param roomId the room Id for the room
     * @param floorNumber Floor number of the room
     * @param roomNumber Room number of the room
     * @param roomStatus Status of the room
     * @param isWifiEnabled whether the wifi is enabled in the room
     * @param isSmokingAllowed whether the smoking is allowed in the room
     * @return the new Room object
     */
    public static Room createRoom(RoomType roomType, String roomId, int floorNumber, int roomNumber,
            RoomStatus roomStatus,
            boolean isWifiEnabled, boolean isSmokingAllowed) {

        double roomPrice = calculateRoomPrice(roomType, isWifiEnabled);
        Room newRoom = new Room(roomType, roomId, floorNumber, roomNumber, roomStatus, isWifiEnabled, isSmokingAllowed,
                roomPrice);
        return newRoom;
    }

    /**
     * Calculate the price of the room
     * see {@link PromotionManager} for the formula for price
     * @param roomType Type of the room
     * @param isWifiEnabled whether the wifi is enabled or not
     * @return the price of the room
     */
    public static double calculateRoomPrice(RoomType roomType, boolean isWifiEnabled) {
        return PromotionManager.getRoomPrice(roomType, isWifiEnabled);
    }

    /**
     * Validate the hotel has this room id or not
     * @param roomId room id of the room you want to check
     * @return {@code true} if contains this room. Otherwise, {@code false}
     */
    public static boolean validateRoomId(String roomId) {
        if (Database.ROOMS.containsKey(roomId)) {
            return true;
        } else {
            // TODO: Throw Exception
            return false;
        }
    }

    /**
     * Check if the room has vacancy <p>
     * See {@link RoomStatus} for the different types of room status
     * @param roomId room id of the room you want to check
     * @param roomStatus room status of the room to compare
     * @return {@code true} if this room has vacancy. Otherwise, {@code false}
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
     * @return {@code true} if the number of pax does not exceed the room capacity. Otherwise, {@code false}
     */
    public static boolean validateNumOfPax(String roomId, int numOfPax) {
        if (numOfPax <= 0) {
            return false;
        }
        if (validateRoomId(roomId)) {
            Room room = searchRoom(roomId);
            return numOfPax <= room.getType().maxCapacity;
        }
        return false;
    }
}
