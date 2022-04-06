package src.model;

import src.model.enums.RoomStatus;
import src.model.enums.RoomType;

public class SingleRoom extends Room {
    private double price;
    private RoomType roomType;

    public SingleRoom(String roomId, int floorNumber, int roomNumber, RoomStatus roomStatus,
            boolean isWifiEnabled, boolean isSmokingAllowed, double price) {
        super(roomId, floorNumber, roomNumber, roomStatus, isWifiEnabled, isSmokingAllowed);
        setPrice(price);
        setRoomType();
    }
    
    @Override
    public boolean setPrice(double price) {
        if (price < 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean setRoomType() {
        roomType = RoomType.SINGLE;
        return true;
    }


    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    
}

