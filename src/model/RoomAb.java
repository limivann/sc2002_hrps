package src.model;

import src.model.enums.RoomType;
import src.model.enums.RoomStatus;

public abstract class RoomAb {
    private RoomType type;
    private int floorNumber;
    private int roomNumber;
    private String roomNumberString;
    private RoomStatus roomStatus;
    private double price;
	private boolean isWifiEnabled;
	private boolean isSmokingAllowed;
	private String guestName;
}
