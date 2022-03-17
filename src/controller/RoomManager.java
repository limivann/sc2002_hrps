package src.controller;
import java.util.HashMap;
import src.model.Room;
public class RoomManager{
    HashMap<String, Room> RoomList = new HashMap<String, Room>();

    public void create(Room.RoomType type, int floor, int room, double price, boolean wifi, boolean smoking) {
        // Room target = searchRoom(floor, room);
        // if (target.getRoomStatus() == Room.RoomStatus.VACANT){
            String id = floor+"-"+room;
            if (!RoomList.containsKey(id)){
                Room newRoom = new Room(type, floor, room, Room.RoomStatus.VACANT, price, wifi, smoking);
                RoomList.put(id, newRoom);
                System.out.println("Room created successfully.");
            }else{
                System.out.println("Room ID already exists.");
            }
           
        // }else {
        //     // Add guest/reservation to waitlist
        // }
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
                target.setRoomStatus(Room.RoomStatus.VACANT);
            }else if (status == 2){
                target.setRoomStatus(Room.RoomStatus.OCCUPIED);
            }else if (status == 3){
                target.setRoomStatus(Room.RoomStatus.RESERVED);
            }else if (status == 4){
                target.setRoomStatus(Room.RoomStatus.UNDER_MAINTENANCE);
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
