package src.controller;
import java.util.HashMap;
import java.util.Scanner;
import src.model.Room;
import src.model.enums.*;
public class RoomManager{
    HashMap<String, Room> RoomList = new HashMap<String, Room>();

    public void create(RoomType type, int floor, int room, double price, boolean wifi, boolean smoking) {
        String id = floor+"-"+room;
        Room newRoom = new Room(type, floor, room, RoomStatus.VACANT, price, wifi, smoking);
        RoomList.put(id, newRoom);
    }

    public void updatePrice(int floor, int room, double price){
        Room target = searchRoom(floor, room);
        target.setPrice(price);
    }

    public void updateStatus(int floor, int room, int status){
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
    }

    public void remove(int floor, int room) {
        String id = floor+"-"+room;
        RoomList.remove(id);
    }

    public Room searchRoom(int floor, int room){
        String id = floor+"-"+room;
        return RoomList.get(id);
    }
    public void  printRoom(int floor, int room){
        Room target = searchRoom(floor, room);
        target.printRoom();
    }
    
    public void printStatus(int floor, int room){
        Room target = searchRoom(floor, room);
        target.printRoomStatus();
    }
    
}
