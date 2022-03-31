package src.model;

import src.model.enums.RoomType;

import java.io.Serializable;
import src.model.enums.RoomStatus;

/**
 * The Class that handles Room model
 * @author Zhang Kaichen
 * @version 1.0
 * @since 2022-03-30
 */
public class Room implements Serializable {
	/**
	 * serial id for the room
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
	 * The guest name in the room
	 */
	private String guestName;

	/**
	 * The id of the room
	 */
	private String roomId;

	/**
	 * The id of the guest live in the room
	 */
	private String guestId;

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
	
	/**
	 * Constructor for the room
	 * @param type Type of the room
	 * @param floorNumber Floor number of the room
	 * @param roomNumber Room number of the room
	 * @param roomStatus Status of the room
	 * @param price Price of the room
	 * @param isWifiEnabled whether the wifi is enabled in the room
	 * @param isSmokingAllowed whether the smoking is allowed in the room
	 * see {@link RoomStatus} for different status of the room
	 * see {@link RoomType} for different type of the room
	 */
	public Room(RoomType type, int floorNumber, int roomNumber, RoomStatus roomStatus, double price,
			boolean isWifiEnabled, boolean isSmokingAllowed) {
		this.type = type;
		this.floorNumber = floorNumber;
		this.roomNumber = roomNumber;
		this.roomNumberString = "0" + floorNumber + "-" + "0" + roomNumber;
		this.roomStatus = roomStatus;
		this.price = price;
		this.isWifiEnabled = isWifiEnabled;
		this.isSmokingAllowed = isSmokingAllowed;
		// this.orders = new ArrayList<OrderItem>();
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
	 * @param guestName name of the guest in the room
	 * @return {@code true} if successfully set
	 */
	public boolean setGuestName(String guestName) {
		this.guestName = guestName;
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
	 * @param guestId the guest id of the guest in the room
	 * @return {@code true} if successfully set
	 */
	public boolean setGuestId(String guestId) {
		this.guestId = guestId;
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
	 * @return the guest name in the room
	 */
	public String getGuestName() {
		return guestName;
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
	public String getGuestId() {
		return guestId;
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
	public void printRoomtype(){
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
