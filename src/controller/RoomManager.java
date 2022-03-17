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
    
    public void printStatus(int floor, int room){
        Room target = searchRoom(floor, room);
        target.printRoomStatus();
    }
    
}
