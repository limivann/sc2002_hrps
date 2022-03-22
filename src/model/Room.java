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
	
	public Room(RoomType type, int floorNumber, int roomNumber, RoomStatus roomStatus, double price, boolean isWifiEnabled, boolean isSmokingAllowed) {
		this.type = type;
		this.floorNumber = floorNumber;
		this.roomNumber = roomNumber;
		this.roomNumberString = "0"+floorNumber+"-"+"0"+roomNumber;
		this.roomStatus = roomStatus;
		this.price = price;
		this.isWifiEnabled = isWifiEnabled;
		this.isSmokingAllowed = isSmokingAllowed;
		// this.orders = new ArrayList<OrderItem>();
	}

	public String getRoomNumberString() {
		return this.roomNumberString;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	public RoomStatus getRoomStatus() {
		return this.roomStatus;
	}


	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}


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
