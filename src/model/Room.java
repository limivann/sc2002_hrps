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
	public boolean setPrice(double price) {
		if (price <= 0) {
			return false;
		}
		this.price = price;
		return true;
	}

	public boolean setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
		return true;
	}

	public boolean setGuestName(String guestName) {
		this.guestName = guestName;
		return true;
	}

	public boolean setFloorNumber(int floorNumber) {
		// input is handled at controller
		this.floorNumber = floorNumber;
		return true;
	}

	public boolean setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
		return true;
	}

	public boolean setRoomId(String roomId) {
		this.roomId = roomId;
		return true;
	}

	public boolean setType(RoomType type) {
		this.type = type;
		return true;
	}

	public boolean setWifiEnabled(boolean isWifiEnabled) {
		this.isWifiEnabled = isWifiEnabled;
		return true;
	}

	public boolean setSmokingAllowed(boolean isSmokingAllowed) {
		this.isSmokingAllowed = isSmokingAllowed;
		return true;
	}

	public boolean setGuestId(String guestId) {
		this.guestId = guestId;
		return true;
	}

	// GETTERS
	public String getRoomNumberString() {
		return this.roomNumberString;
	}

	public RoomStatus getRoomStatus() {
		return this.roomStatus;
	}

	public double getPrice() {
		return this.price;
	}

	public String getGuestName() {
		return guestName;
	}

	public String getRoomId() {
		return roomId;
	}

	public String getGuestId() {
		return guestId;
	}

	// METHODS
	public void printRoomStatus() {
		switch(roomStatus) {
		case VACANT:
			System.out.println("Vacant");
			break;
		case OCCUPIED:
			System.out.println("Occupied");
			break;
		case RESERVED:
			System.out.println("Reserved");
			break;
		case UNDER_MAINTENANCE:
			System.out.println("Under UNDER_MAINTENANCE");
			break;
		}
	}

	public void printRoomtype(){
		switch(type){
			case SINGLE:
			System.out.println("Type: Single Room");
			break;
			case DOUBLE:
			System.out.println("Type: Double Room");
			break;
			case DELUXE:
			System.out.println("Type: Deluxe Room");
			case VIP_SUITE:
			System.out.println("Type: VIP Suite");
		}
	}

	public void printRoomDetails(){
		System.out.println("----------------");
        System.out.printf("Room number: %s\n", roomId);
		System.out.printf("Room status: %s\n", roomStatus);
		if (roomStatus == RoomStatus.OCCUPIED || roomStatus == RoomStatus.RESERVED) {
			System.out.printf("Guest Name: %s\n", guestName);
		}
		System.out.printf("Room price: %s\n", price);
		System.out.printf("Wifi Enabled: %s\n", isWifiEnabled);
		System.out.printf("Smoking Allowed: %s\n", isSmokingAllowed);
        System.out.println("----------------");
	}
	public int getFloorNumber() {
		return floorNumber;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public RoomType getType() {
		return type;
	}
}
