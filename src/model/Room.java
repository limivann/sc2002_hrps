package src.model;

// import java.util.ArrayList;
import src.model.enums.RoomType;

import java.io.Serializable;

import src.database.Database;
import src.model.enums.RoomStatus;

public class Room implements Serializable {
	private static final long serialVersionUID = 2L;

	private RoomType type;
	private int floorNumber;
	private int roomNumber;
	private String roomNumberString;
	private RoomStatus roomStatus;
	private double price;
	private boolean isWifiEnabled;
	private boolean isSmokingAllowed;
	private String guestName;
	private String roomId;
	private String guestId;
	// private ArrayList<OrderItem> orders;

	// For initializing room
	/**
	 * Constructor for the room
	 * @param type Type of the room
	 * @param roomId Room Id of the room
	 * @param floorNumber Floor number of the room
	 * @param roomNumber Room number of the room
	 * @param roomStatus Status of the room
	 * @param isWifiEnabled whether the wifi is enabled in the room
	 * @param isSmokingAllowed whether the smoking is allowed in the room
	 * @param price Price of the room
	 * @see RoomType For the different room type
	 * @see RoomStatus For the different status of the room
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
	 * 
	 * @param price price of the room
	 * @return true if input is valid
	 */
	public boolean setPrice(double price) {
		if (price <= 0) {
			return false;
		}
		this.price = price;
		return true;
	}

	/**
	 * 
	 * @param roomStatus the status of the room
	 * @return true if successfully set the room status
	 */
	public boolean setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
		return true;
	}

	/**
	 * 
	 * @param guestName name of the guest in the room
	 * @return true if successfully set
	 */
	public boolean setGuestName(String guestName) {
		this.guestName = guestName;
		return true;
	}

	/**
	 * 
	 * @param floorNumber the floor number of the room
	 * @return true if successfully set
	 */
	public boolean setFloorNumber(int floorNumber) {
		// input is handled at controller
		this.floorNumber = floorNumber;
		return true;
	}

	/**
	 * 
	 * @param roomNumber the room number of the room
	 * @return true if successfully set
	 */
	public boolean setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return true;
	}

	/**
	 * 
	 * @param roomId the id of the room
	 * @return true if successfully set
	 */
	public boolean setRoomId(String roomId) {
		this.roomId = roomId;
		return true;
	}

	/**
	 * 
	 * @param type the type of the room
	 * @return true if successfully set
	 */
	public boolean setType(RoomType type) {
		this.type = type;
		return true;
	}

	/**
	 * 
	 * @param isWifiEnabled is wifi enabled in the room or not
	 * @return true if successfully set
	 */
	public boolean setWifiEnabled(boolean isWifiEnabled) {
		this.isWifiEnabled = isWifiEnabled;
		return true;
	}

	/**
	 * 
	 * @param isSmokingAllowed is smoking allowed in the room or not
	 * @return true if successfully set
	 */
	public boolean setSmokingAllowed(boolean isSmokingAllowed) {
		this.isSmokingAllowed = isSmokingAllowed;
		return true;
	}

	/**
	 * 
	 * @param guestId the guest id of the guest in the room
	 * @return true if successfully set
	 */
	public boolean setGuestId(String guestId) {
		this.guestId = guestId;
		return true;
	}

	// GETTERS
	/**
	 * 
	 * @return the room number
	 */
	public String getRoomNumberString() {
		return this.roomNumberString;
	}

	/**
	 * 
	 * @return the room status of the room
	 */
	public RoomStatus getRoomStatus() {
		return this.roomStatus;
	}

	/**
	 * 
	 * @return the price of the room
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * 
	 * @return the guest name in the room
	 */
	public String getGuestName() {
		return guestName;
	}

	/**
	 * 
	 * @return the room id of the room
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * 
	 * @return the guest id in the room
	 */
	public String getGuestId() {
		return guestId;
	}

	/**
	 * 
	 * @return the floor number of the room
	 */
	public int getFloorNumber() {
		return floorNumber;
	}

	/**
	 * 
	 * @return the room number of the room
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * 
	 * @return the type of the room
	 */
	public RoomType getType() {
		return type;
	}
	
	/**
	 * 
	 * @return whether wifi is enabled or not
	 */
	public boolean getIsWifiEnabled() {
		return isWifiEnabled;
	}

	/**
	 * 
	 * @return whether smoking is allowed or not
	 */
	public boolean getIsSmokingAllowed() {
		return isSmokingAllowed;
	}
	
	// METHODS
	/**
	 * print out the room status of the room
	 */
	public void printRoomStatus() {
		System.out.println(String.format("Room status: %s", getRoomStatus().roomStatusAsStr));
	}

	/**
	 * print out the room type of the room
	 */
	public void printRoomtype() {
		System.out.println(String.format("Room type: %s", getType().roomTypeAsStr));
	}

	/**
	 * print out the complete detail of the room
	 */
	public void printRoomDetails(){
		System.out.println("----------------");
        System.out.printf("Room number: %s\n", getRoomId());
		printRoomStatus();
		printRoomtype();
		if (roomStatus == RoomStatus.OCCUPIED || roomStatus == RoomStatus.RESERVED) {
			System.out.printf("Guest Name: %s\n", getGuestName());
		}
		System.out.printf("Room price: %s\n", getPrice());
		System.out.printf("Wifi Enabled: %s\n", getIsWifiEnabled());
		System.out.printf("Smoking Allowed: %s\n", getIsSmokingAllowed());
        System.out.println("----------------");
	}
	
}
