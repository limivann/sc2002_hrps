package src;

public class Room {
	enum RoomStatus {VACANT, OCCUPIED, RESERVED, UNDER_MAINTENANCE};
	enum RoomType {SINGLE, DOUBLE, DELUXE, VIP_SUITE}
	RoomType type;
	int floorNumber;
	int roomNumber;
	String roomNumberString;
	RoomStatus roomStatus;
	double price;
	boolean isWifiEnabled;
	boolean isSmokingAllowed;
	String guestName;
	
	Room(RoomType type, int floorNumber, int roomNumber, RoomStatus roomStatus, double price, boolean isWifiEnabled, boolean isSmokingAllowed) {
		this.type = type;
		this.roomNumberString = floorNumber+"-"+roomNumber;
		this.roomStatus = roomStatus;
		this.price = price;
		this.isWifiEnabled = isWifiEnabled;
		this.isSmokingAllowed = isSmokingAllowed;
	}
	
	String getRoomNumberString() {
		return this.roomNumberString;
	}
	void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}
	RoomStatus getRoomStatus() {
		return this.roomStatus;
	}
	double getPrice() {
		return this.price;
	}
	String getGuestName() {
		return guestName;
	}
	void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	void printRoomStatus(RoomStatus roomStatus) {
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
}
