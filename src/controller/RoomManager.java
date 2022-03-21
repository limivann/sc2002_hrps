package src.controller;
import java.util.HashMap;
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
    public void printOccupancyRate(){
        int[][] list = new int [4][2];
        String[] list2 = new String[4];
        String[] list3 = {"Single", "Double", "Deluxe", "Vip suite"};

        for (Room x : RoomList.values()) {
            if (x.getType() == RoomType.SINGLE) list[0][1]++;
            if (x.getRoomStatus() == RoomStatus.VACANT){
                list[0][0]++;
                list2[0] += x.getRoomNumberString() + ", ";
            } 
            
            if (x.getType() == RoomType.DOUBLE) list[1][1]++;
            if (x.getRoomStatus() == RoomStatus.VACANT){
                list[1][0]++;
                list2[1] += x.getRoomNumberString() + ", ";
            }
            
            if (x.getType() == RoomType.DELUXE) list[2][1]++;
            if (x.getRoomStatus() == RoomStatus.VACANT) {
                list[2][0]++;
                list2[2] += x.getRoomNumberString() + ", ";
            }

            if (x.getType() == RoomType.VIP_SUITE) list[3][1]++;
            if (x.getRoomStatus() == RoomStatus.VACANT){
                list[3][0]++;
                list2[3] += x.getRoomNumberString() + ", ";
            }
        }
        for (int i = 0; i < 4; i++){
            System.out.printf("%s: Number: %d out of %d\n", list3[i], list[i][0], list[i][1]);
            System.out.printf("Rooms: %s\n", list2[i]);
        }
    }
}
