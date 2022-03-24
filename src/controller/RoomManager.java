package src.controller;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import javax.xml.crypto.Data;

import java.util.Iterator;
import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Room;
import src.model.enums.*;
public class RoomManager{
    HashMap<String, Room> RoomList = new HashMap<String, Room>();

    public RoomManager() {
        PromotionManager promotionManager = new PromotionManager();
    }
    
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

    public static boolean updateRoomStatus(int floorNumber, int roomNumber, RoomStatus roomStatus) {
        String roomId = String.format("%02d-%02d", floorNumber, roomNumber);
        if (Database.ROOMS.containsKey(roomId)){
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

    public static Room searchRoom(int floor, int room) {
        String roomId = String.format("%02d-%02d", floor, room);
        return Database.ROOMS.get(roomId);
    }

    public static Room searchRoom(String roomId) {
        return Database.ROOMS.get(roomId);
    }
    
    public static void printRoom(int floor, int room) {
        String roomId = String.format("%02d-%02d", floor, room);
        if (Database.ROOMS.containsKey(roomId)) {
            Room target = searchRoom(floor, room);
            target.printRoomDetails();
        } else {
            System.out.println("Room doesn't exists.");
        }
    }
    
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
    
    public void printOccupancyRate() {
        int[][] list = new int[4][2];
        String[] list2 = new String[4];
        String[] list3 = { "Single", "Double", "Deluxe", "Vip suite" };

        for (Room x : RoomList.values()) {
            if (x.getType() == RoomType.SINGLE)
                list[0][1]++;
            if (x.getRoomStatus() == RoomStatus.VACANT) {
                list[0][0]++;
                list2[0] += x.getRoomNumberString() + ", ";
            }

            if (x.getType() == RoomType.DOUBLE)
                list[1][1]++;
            if (x.getRoomStatus() == RoomStatus.VACANT) {
                list[1][0]++;
                list2[1] += x.getRoomNumberString() + ", ";
            }

            if (x.getType() == RoomType.DELUXE)
                list[2][1]++;
            if (x.getRoomStatus() == RoomStatus.VACANT) {
                list[2][0]++;
                list2[2] += x.getRoomNumberString() + ", ";
            }

            if (x.getType() == RoomType.VIP_SUITE)
                list[3][1]++;
            if (x.getRoomStatus() == RoomStatus.VACANT) {
                list[3][0]++;
                list2[3] += x.getRoomNumberString() + ", ";
            }
        }
        for (int i = 0; i < 4; i++) {
            System.out.printf("%s: Number: %d out of %d\n", list3[i], list[i][0], list[i][1]);
            System.out.printf("Rooms: %s\n", list2[i]);
        }
    }

    public static void printAllRooms() {
        HashMap<String, Room> toIterate = Helper.copyHashMap(Database.ROOMS);
        Iterator it = toIterate.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
    
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
    
    public static Room createRoom(RoomType roomType, String roomId, int floorNumber, int roomNumber, RoomStatus roomStatus,
            boolean isWifiEnabled, boolean isSmokingAllowed) {

        double roomPrice = calculateRoomPrice(roomType, isWifiEnabled);
        Room newRoom = new Room(roomType, roomId, floorNumber, roomNumber, roomStatus, isWifiEnabled, isSmokingAllowed,
                roomPrice);
        return newRoom;
    }
    
    public static double calculateRoomPrice(RoomType roomType, boolean isWifiEnabled) {
        return PromotionManager.getRoomPrice(roomType, isWifiEnabled);
    }

    public static boolean validateRoomId(String roomId) {
        if (Database.ROOMS.containsKey(roomId)) {
            return true;
        } else {
            // TODO: Throw Exception
            return false;
        }
    }
    
    public static boolean checkRoomVacancy(String roomId) {
        if (validateRoomId(roomId)) {
            return Database.ROOMS.get(roomId).getRoomStatus() == RoomStatus.VACANT;
        }   
        return false;
    }

    

    public static void main(String[] args) {
        initializeAllRooms();
    }
}
