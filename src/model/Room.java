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
public class Room implements Serializable {
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
	 * The room Number in String format
	 */
	private String roomNumberString;

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
	 * The id of the guest live in the room
	 */
	private ArrayList<String> guestIds;

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
	}	
	
	// SETTERS
	/**
	 * Setter
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
	 * Setter
	 * @param roomStatus the status of the room
	 * @return {@code true} if successfully set
	 */
	public boolean setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
		return true;
	}

	/**
	 * Setter
	 * @param floorNumber the floor number of the room
	 * @return {@code true} if successfully set
	 */
	public boolean setFloorNumber(int floorNumber) {
		// input is handled at controller
		this.floorNumber = floorNumber;
		return true;
	}

	/**
	 * Setter
	 * @param roomNumber the room number of the room
	 * @return {@code true} if successfully set
	 */
	public boolean setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return true;
	}

	/**
	 * Setter
	 * @param roomId the id of the room
	 * @return {@code true} if successfully set
	 */
	public boolean setRoomId(String roomId) {
		this.roomId = roomId;
		return true;
	}

	/**
	 * Setter
	 * @param type the type of the room
	 * @return {@code true} if successfully set
	 */
	public boolean setType(RoomType type) {
		this.type = type;
		return true;
	}

	/**
	 * Setter
	 * @param isWifiEnabled is wifi enabled in the room or not
	 * @return {@code true} if successfully set
	 */
	public boolean setWifiEnabled(boolean isWifiEnabled) {
		this.isWifiEnabled = isWifiEnabled;
		return true;
	}

	/**
	 * Setter
	 * @param isSmokingAllowed is smoking allowed in the room or not
	 * @return {@code true} if successfully set
	 */
	public boolean setSmokingAllowed(boolean isSmokingAllowed) {
		this.isSmokingAllowed = isSmokingAllowed;
		return true;
	}

	/**
	 * Setter
	 * @param guestIds the guest ids of the guests in the room
	 * @return {@code true} if successfully set
	 */
	public boolean setGuestIds(ArrayList<String> guestIds) {
		this.guestIds = guestIds;
		return true;
	}

	// GETTERS
	/**
	 * Getter
	 * @return the room number
	 */
	public String getRoomNumberString() {
		return this.roomNumberString;
	}

	/**
	 * Getter
	 * @return the room status of the room
	 */
	public RoomStatus getRoomStatus() {
		return this.roomStatus;
	}

	/**
	 * Getter
	 * @return the price of the room
	 */
	public double getPrice() {
		return this.price;
	}


	/**
	 * Getter
	 * @return the room id of the room
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * Getter
	 * @return the guest id in the room
	 */
	public ArrayList<String> getGuestIds() {
		return guestIds;
	}

	/**
	 * Getter
	 * @return the floor number of the room
	 */
	public int getFloorNumber() {
		return floorNumber;
	}

	/**
	 * Getter
	 * @return the room number of the room
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * Getter
	 * @return the type of the room
	 */
	public RoomType getType() {
		return type;
	}
	
	/**
	 * Getter
	 * @return whether wifi is enabled or not
	 */
	public boolean getIsWifiEnabled() {
		return isWifiEnabled;
	}

	/**
	 * Getter
	 * @return whether smoking is allowed or not
	 */
	public boolean getIsSmokingAllowed() {
		return isSmokingAllowed;
	}
	
	/**
	 * Print out the room status of the room
	 */
	public void printRoomStatus() {
		System.out.println(String.format("Room status: %s", getRoomStatus().roomStatusAsStr));
	}

	/**
	 * Print out the room type of the room
	 */
	public void printRoomtype() {
		System.out.println(String.format("Room type: %s", getType().roomTypeAsStr));
	}
	
	/**
     * Override toString method to show the simplified details of the room
     * @return a string of room details
     */
	@Override
	public String toString(){
		String res = "";
		res += String.format("Room ID: %s, Room Type: %s, Room Status: %s", getRoomId(), getType().roomTypeAsStr,
			getRoomStatus().roomStatusAsStr);
		return res;
	}
}
