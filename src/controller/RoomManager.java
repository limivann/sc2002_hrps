package src.controller;
import java.util.HashMap;

import src.database.Database;
import src.database.FileType;
import src.model.PromotionDetails;
import src.model.Room;
import src.model.enums.*;
public class RoomManager{
    HashMap<String, Room> RoomList = new HashMap<String, Room>();

    public void create(RoomType type, int floor, int room, double price, boolean wifi, boolean smoking) {
        String id = floor+"-"+room;
        if (!RoomList.containsKey(id)){
            Room newRoom = new Room(type, floor, room, RoomStatus.VACANT, price, wifi, smoking);
            RoomList.put(id, newRoom);
            System.out.println("Room created successfully.");
        }else{
            System.out.println("Room ID already exists.");
        }
    }

    public void updatePrice(int floor, int room, double price){
        String id = floor+"-"+room;
        if (RoomList.containsKey(id)){
            Room target = searchRoom(floor, room);
            target.setPrice(price);
            System.out.println("Price updated successfully.");
        }else{
            System.out.println("Room id doesn't exists.");
        }
        
    }

    public void updateStatus(int floor, int room, int status){
        String id = floor+"-"+room;
        if (RoomList.containsKey(id)){
            Room target = searchRoom(floor, room);
            if (status == 1){
                target.setRoomStatus(RoomStatus.VACANT);
            }else if (status == 2){
                target.setRoomStatus(RoomStatus.OCCUPIED);
            }else if (status == 3){
                target.setRoomStatus(RoomStatus.RESERVED);
            }else if (status == 4){
                target.setRoomStatus(RoomStatus.UNDER_MAINTENANCE);
            }    
            System.out.println("Status updated successfully.");        
        }else{
            System.out.println("Room id doesn't exists. ");
        }

    }

    public void remove(int floor, int room) {
        String id = floor+"-"+room;
        if (RoomList.containsKey(id)){
            RoomList.remove(id);
            System.out.println("Room removed successfully.");
        }else{
            System.out.println("Room doesn't exists.");
        }
        
    }

    public Room searchRoom(int floor, int room){
        String id = floor+"-"+room;
        return RoomList.get(id);
    }
    
    public void printRoom(int floor, int room){
        String id = floor+"-"+room;
        if (RoomList.containsKey(id)){
            Room target = searchRoom(floor, room);
            target.printRoom();            
        }else{
            System.out.println("Room doesn't exists.");
        }
    }
    
    public void printStatus(){
        System.out.println("Vacant: ");
        System.out.printf("\t Rooms: ");
        for (Room x : RoomList.values()) {
            if (x.getRoomStatus() == RoomStatus.VACANT){
                System.out.printf("0%d-0%d, ", x.getFloorNumber(), x.getRoomNumber());
            }
            System.out.println();
        }
        System.out.println("Occupied: ");
        System.out.printf("\t Rooms: ");
        for (Room x : RoomList.values()) {
            if (x.getRoomStatus() == RoomStatus.OCCUPIED){
                System.out.printf("0%d-0%d, ", x.getFloorNumber(), x.getRoomNumber());
            }
            System.out.println();
        }
        System.out.println("Reserved: ");
        System.out.printf("\t Rooms: ");
        for (Room x : RoomList.values()) {
            if (x.getRoomStatus() == RoomStatus.RESERVED){
                System.out.printf("0%d-0%d, ", x.getFloorNumber(), x.getRoomNumber());
            }
            System.out.println();
        }
        System.out.println("Under maintenance: ");
        System.out.printf("\t Rooms: ");
        for (Room x : RoomList.values()) {
            if (x.getRoomStatus() == RoomStatus.UNDER_MAINTENANCE){
                System.out.printf("0%d-0%d, ", x.getFloorNumber(), x.getRoomNumber());
            }
            System.out.println();
        }
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
        Room newRoom = new Room(roomType, roomId, floorNumber, roomNumber, roomStatus, isWifiEnabled, isSmokingAllowed, roomPrice);
        return newRoom;
    }
    
    public static double calculateRoomPrice(RoomType roomType, boolean isWifiEnabled) {
        return PromotionManager.getRoomPrice(roomType, isWifiEnabled);
    }

    public static void main(String[] args) {
        initializeAllRooms();
    }
}
