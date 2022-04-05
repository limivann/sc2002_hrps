package src.model;

import src.model.enums.RoomType;

import java.io.Serializable;
import java.util.ArrayList;

import src.model.enums.RoomStatus;

/**
 * The Class that handles Room model
 * @author Zhang Kaichen
 * @version 1.0
 * @since 2022-03-30
 */
public class Room implements Serializable, Comparable<Room> {
	/**
	 * For java serializable
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * The type of the room <p>
	 * See {@link RoomType} for different types of room.
	 */
	private RoomType type;

	/**
	 * Floor number of the room
	 */
	private int floorNumber;

	/**
	 * Room number of the room
	 */
	private int roomNumber;

	/**
	 * The Status of the room <p>
	 * See {@link RoomStatus} for different status of the room.
	 */
	private RoomStatus roomStatus;

	/**
	 * price for the room
	 */
	private double price;

	/**
	 * whether the wifi is enabled or not
	 */
	private boolean isWifiEnabled;

	/**
	 * whether the smoking is allowed
	 */
	private boolean isSmokingAllowed;

	/**
	 * The id of the room
	 */
	private String roomId;

	/**
	 * The id of the guests that booked the room
	 */
	private ArrayList<String> guestIds;

	/**
	 * ArrayList of {@link Order} objects made by the room.
	 */
	private ArrayList<Order> orders;

	/**
	 * The constructor for the room
	 * @param type the type of the room
	 * @param roomId id of the room
	 * @param floorNumber floor number of the room
	 * @param roomNumber room number of the room
	 * @param roomStatus status of the room
	 * @param isWifiEnabled whether is wifi enabled
	 * @param isSmokingAllowed whether smoking is allowed
	 * @param price the price of the room
	 * see {@link RoomStatus} for different status of the room
	 * see {@link RoomType} for different type of the room
	 */
	public Room(RoomType type, String roomId, int floorNumber, int roomNumber, RoomStatus roomStatus, boolean isWifiEnabled,
			boolean isSmokingAllowed, double price) {
		setType(type);
		setRoomId(roomId);
		setFloorNumber(floorNumber);
		setRoomNumber(roomNumber);
		setRoomStatus(roomStatus);
		setWifiEnabled(isWifiEnabled);
		setSmokingAllowed(isSmokingAllowed);
		setPrice(price);
		setOrders(new ArrayList<Order>());
	}	
	
	// SETTERS
	/**
	 * Sets the price of the room
	 * @param price price of the room
	 * @return {@code true} if input is valid
	 */
	public boolean setPrice(double price) {
		if (price <= 0) {
			return false;
		}
		this.price = price;
		return true;
	}

	/**
	 * Sets the status of the room
	 * @param roomStatus the status of the room
	 * @return {@code true} if successfully set
	 */
	public boolean setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
		return true;
	}

	/**
	 * Sets the floor number of the room
	 * @param floorNumber the floor number of the room
	 * @return {@code true} if successfully set
	 */
	public boolean setFloorNumber(int floorNumber) {
		// input is handled at controller
		this.floorNumber = floorNumber;
		return true;
	}

	/**
	 * Sets the room number of the room
	 * @param roomNumber the room number of the room
	 * @return {@code true} if successfully set
	 */
	public boolean setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return true;
	}

	/**
	 * Sets the id of the room
	 * @param roomId the id of the room
	 * @return {@code true} if successfully set
	 */
	public boolean setRoomId(String roomId) {
		this.roomId = roomId;
		return true;
	}

	/**
	 * Sets the type of the room
	 * @param type the type of the room
	 * @return {@code true} if successfully set
	 */
	public boolean setType(RoomType type) {
		this.type = type;
		return true;
	}

	/**
	 * Sets the wifi enable feature of the room
	 * @param isWifiEnabled {@code true} if wifi is enabled in the room. Otherwise, {@code false}.
	 * @return {@code true} if successfully set
	 */
	public boolean setWifiEnabled(boolean isWifiEnabled) {
		this.isWifiEnabled = isWifiEnabled;
		return true;
	}

	/**
	 * Sets the smoking allow feature of the room
	 * @param isSmokingAllowed {@code true} if smoking allowed is in the room. Otherwise, {@code false}.
	 * @return {@code true} if successfully set
	 */
	public boolean setSmokingAllowed(boolean isSmokingAllowed) {
		this.isSmokingAllowed = isSmokingAllowed;
		return true;
	}

	/**
	 * Sets the guest ids of the guests in the room
	 * @param guestIds the guest ids of the guests in the room
	 * @return {@code true} if successfully set
	 */
	public boolean setGuestIds(ArrayList<String> guestIds) {
		this.guestIds = guestIds;
		return true;
	}

	/**
	 * Sets the orders made by the room
	 * @param orders ArrayList of {@link Order}(s) made by the room
	 * @return {@code true} if successfully set
	 */
	public boolean setOrders(ArrayList<Order> orders) {
		this.orders = orders;
		return true;
	}

	// GETTERS

	/**
	 * Gets the status of the room
	 * @return the room status of the room
	 */
	public RoomStatus getRoomStatus() {
		return this.roomStatus;
	}

	/**
	 * Sets the price of the room
	 * @return the price of the room
	 */
	public double getPrice() {
		return this.price;
	}


	/**
	 * Gets the id of the room
	 * @return the room id of the room
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * Gets the guest ids of the guests in the room
	 * @return ArrayList of guestIds in the room
	 */
	public ArrayList<String> getGuestIds() {
		return guestIds;
	}

	/**
	 * Gets the floor number of the room
	 * @return the floor number of the room
	 */
	public int getFloorNumber() {
		return floorNumber;
	}

	/**
	 * Gets the room number of the room
	 * @return the room number of the room
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * Gets the type of the room
	 * @return the {@link RoomType} of the room
	 */
	public RoomType getType() {
		return type;
	}
	
	/**
	 * Gets the wifi enable feature of the room
	 * @return {@code true} if wifi is enabled in the room. Otherwise, {@code false}.
	 */
	public boolean getIsWifiEnabled() {
		return isWifiEnabled;
	}

	/**
	 * Gets the smoking allow feature of the room
	 * @return {@code true} if smoking allowed is in the room. Otherwise, {@code false}.
	 */
	public boolean getIsSmokingAllowed() {
		return isSmokingAllowed;
	}


	/**
	 * Gets the orders made by the room
	 * @return ArrayList of {@link Order}(s) made by the room
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}
	
	/**
     * Override toString method to show the simplified details of the room
     * @return a string of room details
     */
	@Override
	public String toString() {
		String res = "";
		res += String.format("Room ID: %s, Room Type: %s, Room Status: %s", getRoomId(), getType().roomTypeAsStr,
				getRoomStatus().roomStatusAsStr);
		return res;
	}

	/**
     * Override compareTo method to compare different room objects according to floor number and room number
     */
	@Override
	public int compareTo(Room room) {
		if (this == room) {
			return 0;
		}
		int thisFloorNumber = this.getFloorNumber();
		int thatFloorNumber = room.getFloorNumber();
		
		int thisRoomIdInt = thisFloorNumber * 100 + this.getRoomNumber();
		int thatRoomIdInt = thatFloorNumber * 100 + room.getRoomNumber(); 
		return thisRoomIdInt - thatRoomIdInt;
	}
}
