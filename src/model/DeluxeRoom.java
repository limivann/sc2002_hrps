package src.model;

import src.model.enums.RoomStatus;
import src.model.enums.RoomType;
/**
 * The Class which handles the data of Deluxe Room.
 * @author Ivan, Max
 * @version 1.0
 * @since 2022-04-07
 */
public class DeluxeRoom extends Room {
    /**
     * The price of the room.
     */
    private double price;

    /**
     * The max capacity of the room.
     */
    private int maxCapacity;
    /**
     * Default constructor of DeluxeRoom.
     * @param roomId Id of the room.
     * @param floorNumber Floor number of the room.
     * @param roomNumber Room number of the room.
     * @param roomStatus Stasus of the room.
     * @param isWifiEnabled A boolean value which indicates whether the room is Wifi enabled.
     * @param isSmokingAllowed A boolean value which indicates whether the room is smoking allowed. 
     * @param price The price of the room.
     */
    public DeluxeRoom(String roomId, int floorNumber, int roomNumber, RoomStatus roomStatus,
            boolean isWifiEnabled, boolean isSmokingAllowed, double price) {
        super(roomId, floorNumber, roomNumber, roomStatus, isWifiEnabled, isSmokingAllowed);
        setPrice(price);
        setRoomType(RoomType.DELUXE);
        setMaxCapacity(2);
    }
    /**
     * Sets the room price.
     * @return {@code true} if sets successfully. Otherwise, {@code false}.
     */
    @Override
    public boolean setPrice(double price) {
        if (price < 0) {
            return false;
        }
        this.price = price;
        return true;
    }
    /**
     * Sets the room type.
     * @return {@code true} if sets successfully.
     */
    @Override
    public boolean setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return true;
    }
    /**
     * Sets the max capacity.
     * @return {@code true} if sets successfully.
     */
    @Override
    public boolean setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        return true;
    }

    /**
     * Gets the room price.
     * @return the price of the room.
     */
    @Override
    public double getPrice() {
        return this.price;
    }
    /**
     * Gets the room type.
     * @return the type of the room.
     */
    @Override
    public RoomType getRoomType() {
        return this.roomType;
    }
    /**
     * Gets the max capacity.
     * @return the max capacity of the room.
     */
    @Override
    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    
}
