package src.database;
import java.util.HashMap;
import java.util.Map;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import src.controller.GuestManager;
import src.controller.RoomManager;
import src.controller.RoomServiceManager;
import src.model.*;
import src.model.enums.Gender;
import src.model.enums.IdentityType;

public class Database {
    private static final String folder = "data";

    // Hashmaps for data access
    public static HashMap<String, Guest> GUESTS = new HashMap<String, Guest>();
    public static HashMap<String, Room> ROOMS = new HashMap<String, Room>();
    public static HashMap<String, Reservation> RESERVATIONS = new HashMap<String, Reservation>();
    public static HashMap<String, Invoice> INVOICES = new HashMap<String, Invoice>();
    public static HashMap<String, Order> ORDERS = new HashMap<String, Order>();
    public static HashMap<String, MenuItem> MENU_ITEMS = new HashMap<String, MenuItem>();

    // Number of rooms for each room type
    public static int numOfSingleRooms = 20;
    public static int numOfDoubleRooms = 10;
    public static int numOfDeluxeRooms = 10;
    public static int numOfVipSuites = 8;
    
    public static int numOfFloors = 4;
    public static int numOfRoomPerFloor = 12;
    
    
    public Database() {
        if (!readSerializedObject(FileType.GUESTS)) {
            System.out.println("Read into Guests failed!");
        }
        if (!readSerializedObject(FileType.ROOMS)) {
            System.out.println("Read into Rooms failed!");
        }
        if (!readSerializedObject(FileType.ORDERS)) {
            System.out.println("Read into Orders failed!");
        }
        if (!readSerializedObject(FileType.MENU_ITEMS)) {
            System.out.println("Read into Menu Items failed!");
        }
        System.out.println("Database init");
    }
    
    public static void saveFileIntoDatabase(FileType fileType) {
        writeSerializedObject(fileType);
    }

    public static void saveAllFiles() {
        saveFileIntoDatabase(FileType.GUESTS);
        saveFileIntoDatabase(FileType.ROOMS);
        saveFileIntoDatabase(FileType.ORDERS);
        saveFileIntoDatabase(FileType.MENU_ITEMS);
    }

    public static boolean readSerializedObject(FileType fileType) {
        String fileExtension = ".dat";
        String filePath = "./src/database/" + folder + "/" + fileType.fileName + fileExtension;
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            if (!(object instanceof HashMap)) {
                objectInputStream.close();
                return false;
            }
            // Read into database
            if (fileType == FileType.GUESTS) {
                GUESTS = (HashMap<String, Guest>) object;
            } else if (fileType == FileType.ROOMS) {
                ROOMS = (HashMap<String, Room>) object;
            } else if (fileType == FileType.ORDERS) {
                ORDERS = (HashMap<String, Order>) object;
            } else if (fileType == FileType.MENU_ITEMS) {
                MENU_ITEMS = (HashMap<String, MenuItem>) object;
            }

            objectInputStream.close();
            fileInputStream.close();
        } catch (EOFException err) {
            System.out.println("Warning: " + err.getMessage());
            if (fileType == FileType.GUESTS) {
                GUESTS = new HashMap<String, Guest>();
            } else if (fileType == FileType.ROOMS) {
                ROOMS = new HashMap<String, Room>();
            } else if (fileType == FileType.ORDERS) {
                ORDERS = new HashMap<String, Order>();
            } else if (fileType == FileType.MENU_ITEMS) {
                MENU_ITEMS = new HashMap<String, MenuItem>();
            }
        } catch (IOException err) {
            err.printStackTrace();
            return false;
        } catch (ClassNotFoundException err) {
            err.printStackTrace();
            return false;
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
            return false;
        }
        return true;
    }
    
    public static boolean writeSerializedObject(FileType fileType) {
        String fileExtension = ".dat";
        String filePath = "./src/database/" + folder + "/" + fileType.fileName + fileExtension;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            if (fileType == FileType.GUESTS) {
                objectOutputStream.writeObject(GUESTS);
            }else if (fileType == FileType.ROOMS) {
                objectOutputStream.writeObject(ROOMS);
            } else if (fileType == FileType.ORDERS) {
                objectOutputStream.writeObject(ORDERS);
            } else if (fileType == FileType.MENU_ITEMS) {
                objectOutputStream.writeObject(MENU_ITEMS);
            }
            objectOutputStream.close();
            fileOutputStream.close();
            return true;
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
            return false;
        }
    }


    public static boolean clearDatabase() {
        // Initialize empty data
        GUESTS = new HashMap<String, Guest>();
        writeSerializedObject(FileType.GUESTS);
        
        ROOMS = new HashMap<String, Room>();
        Database.initializeRooms();
        writeSerializedObject(FileType.ROOMS);

        ORDERS = new HashMap<String, Order>();
        writeSerializedObject(FileType.ORDERS);
        
        MENU_ITEMS = new HashMap<String, MenuItem>();
        writeSerializedObject(FileType.MENU_ITEMS);
        return true;
    }

    public static boolean initalizeDummyGuests() {
        if (GUESTS.size() != 0) {
            System.out.println("The database already has guests. Reset database first to initialize guests");
            return false;
        }
        Identity identity1 = new Identity(IdentityType.DRIVING_LICENSE, "G2121722W");
        GuestManager.createGuest("Aaron", "Lim", "12127387136", "Hall 14", Gender.MALE, identity1, "Malaysian",
                "82712251");
        Identity identity2 = new Identity(IdentityType.PASSPORT, "A812812B");
        GuestManager.createGuest("Max", "Tan", "271271282", "Outside NTU", Gender.MALE, identity2, "Malaysian",
                "85261210");
        Identity identity3 = new Identity(IdentityType.DRIVING_LICENSE, "F2912712C");
        GuestManager.createGuest("Hill", "Seah", "12127387136", "Hall 14", Gender.MALE, identity3, "Singaporean",
                "82712251");
        Identity identity4 = new Identity(IdentityType.PASSPORT, "A0021273C");
        GuestManager.createGuest("Kaichen", "Zhang", "998262712", "Hall 2", Gender.MALE, identity4, "Chinese",
                "97126172");
        Identity identity5 = new Identity(IdentityType.DRIVING_LICENSE, "G2121722W");
        GuestManager.createGuest("Yuan Ren", "Loke", "212171612", "NTU", Gender.MALE, identity5, "Programmer",
                "92512512");
        Identity identity6 = new Identity(IdentityType.PASSPORT, "A9728172D");
        GuestManager.createGuest("Fang", "Li", "73232733", "SCSE", Gender.FEMALE, identity6, "Chinese", "96252552");
        return true;
    }
    
    public static boolean initializeDummyMenu() {
        if (MENU_ITEMS.size() != 0) {
            System.out.println("The database already has some menu items. Reset database first to initialize menu items");
            return false;
        }
        RoomServiceManager.addMenuItem("Mee Goreng",
                "chicken, wok-fried yellow noodles, spicy shrimp paste, egg, chye sim", 24);
        RoomServiceManager.addMenuItem("Yang Chow Fried Rice",
                "Chinese sausage, barbecue pork, shrimp, fried egg", 24);
        RoomServiceManager.addMenuItem("Singapore Laksa Lemak",
                "rice noodles, quail egg, bean curd, fish cake, spicy coconut gravy with prawns", 25);
        RoomServiceManager.addMenuItem("Chicken Curry", "coconut gravy, achar, steamed rice", 26);
        RoomServiceManager.addMenuItem("Char Kway Teow",
                "wok-fried rice & egg noodles, prawns, Chinese sausage, squid, fish cake, bean sprouts, black soy sauce",
                26);
        RoomServiceManager.addMenuItem("Hokkien Mee", "rice & egg noodles, prawns, squid, pork belly, bean sprouts",
                26);
        RoomServiceManager.addMenuItem("Hainanese Chicken Rice", "chicken broth, ginger, chilli, dark soy sauce", 26);
        RoomServiceManager.addMenuItem("Nasi Goreng",
                "Indonesian-style fried rice, sunny side-up egg, achar, chicken satay, chicken drumstick, peanut sauce, prawn crackers",
                28);
        return true;
    }

    private static boolean initializeRooms() {
        RoomManager.initializeAllRooms();
        return true;
    }

    public static void main(String[] args) {
        Database.clearDatabase();

    }
}
