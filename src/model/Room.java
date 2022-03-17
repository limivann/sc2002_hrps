package src.model;

// import java.util.ArrayList;
import src.model.enums.RoomType;
import src.model.enums.RoomStatus;

public class Room {
	private RoomType type;
	private int floorNumber;
	private int roomNumber;
	private String roomNumberString;
	private RoomStatus roomStatus;
	private double price;
	private boolean isWifiEnabled;
	private boolean isSmokingAllowed;
	private String guestName;
	// private ArrayList<OrderItem> orders;
	
	/**
	 * The constructor of a Room
	 * @param type The type of the room
	 * @param floorNumber The floor number of the room
	 * @param roomNumber The room number of the room
	 * @param roomStatus the status of the room
	 * @param price The price of the room
	 * @param isWifiEnabled whether the room has wifi or not
	 * @param isSmokingAllowed whether you can smoke in the room
	 * @see RoomType
	 * @see RoomStatus
	 */
	public Room(RoomType type, int floorNumber, int roomNumber, RoomStatus roomStatus, double price, boolean isWifiEnabled, boolean isSmokingAllowed) {
		this.type = type;
		this.floorNumber = floorNumber;
		this.roomNumber = roomNumber;
		this.roomNumberString = floorNumber+"-"+roomNumber;
		this.roomStatus = roomStatus;
		this.price = price;
		this.isWifiEnabled = isWifiEnabled;
		this.isSmokingAllowed = isSmokingAllowed;
		// this.orders = new ArrayList<OrderItem>();
	}

	/**
	 * Getter method of room number
	 * @return number of the room
	 */
	public String getRoomNumberString() {
		return this.roomNumberString;
	}
	/**
	 * Setter method for room status
	 * @param roomStatus Occupied or not
	 * @see RoomStatus
	 */
	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}
	/**
	 * Getter method of room status
	 * @return status of the room
	 */
	public RoomStatus getRoomStatus() {
		return this.roomStatus;
	}

	/**
	 * Getter method of room price
	 * @return price of the room
	 */
	public double getPrice() {
		return this.price;
	}
	/**
	 * Setter for guest name
	 * @param guestName The name of the guest in the room
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * Getter method of guest's name
	 * @return guest name of the room
	 */
	public String getGuestName() {
		return guestName;
	}
	/**
	 * Setter for guest name
	 * @param guestName The name of the guest in the room
	 */
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	/**
	 * Print out the room status of the room
	 * @param roomStatus the status of the room
	 */
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

	/**
	 * This is the function that print
	 * the room type of the room
	 */
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
	/**
	 * This is the function that print out the room detail
	 */
	public void printRoom(){
		System.out.println("----------------");
        System.out.printf("Room number: %s\n", roomNumberString);
		System.out.printf("Room status: %s\n", roomStatus);
		if (roomStatus == RoomStatus.OCCUPIED || roomStatus == RoomStatus.RESERVED) {
			System.out.printf("Guest Name: %s\n", guestName);
		}
		System.out.printf("Room price: %s\n", price);
		System.out.printf("Wifi Enabled: %s\n", isWifiEnabled);
		System.out.printf("Smoking Allowed: %s\n", isSmokingAllowed);
        System.out.println("----------------");
	}
}
