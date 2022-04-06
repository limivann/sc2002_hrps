package src.model;

import src.model.enums.RoomStatus;
import src.model.enums.RoomType;

public class SingleRoom extends Room {
    private double price;
    private RoomType roomType;
    private int maxCapacity;

    public SingleRoom(String roomId, int floorNumber, int roomNumber, RoomStatus roomStatus,
            boolean isWifiEnabled, boolean isSmokingAllowed, double price) {
        super(roomId, floorNumber, roomNumber, roomStatus, isWifiEnabled, isSmokingAllowed);
        setPrice(price);
        setRoomType();
        setMaxCapacity();
    }
    
    @Override
    public boolean setPrice(double price) {
        if (price < 0) {
            return false;
        }
        this.price = price;
        return true;
    }

    @Override
    public boolean setRoomType() {
        this.roomType = RoomType.SINGLE;
        return true;
    }

    @Override
    public boolean setMaxCapacity() {
        this.maxCapacity = 1;
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

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    
}

