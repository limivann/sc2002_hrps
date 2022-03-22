package src;
import java.util.HashMap;
import java.util.Map;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import src.model.*;

public class Database {
    private static final String folder = "data";

    // Hashmaps for data access
    protected static HashMap<String, Guest> GUESTS = new HashMap<String, Guest>();
    protected static HashMap<String, Room> ROOMS = new HashMap<String, Room>();
    protected static HashMap<String, Reservation> RESERVATIONS = new HashMap<String, Reservation>();
    protected static HashMap<String, Invoice> INVOICES = new HashMap<String, Invoice>();

    // Number of rooms for each room type
    public static int numOfSingleRooms = 20;
    public static int numOfDoubleRooms = 10;
    public static int numOfDeluxeRooms = 10;
    public static int numOfVipSuites = 8;
    
    public Database() {
        if (!readSerializedObject(FileType.GUESTS)){
            System.out.println("Read into Guest failed!");
        }
        
    }
    public static boolean readSerializedObject(FileType fileType) {
        String fileExtension = ".dat";
        String filePath = "./src/" + folder + "/" + fileType.fileName + fileExtension;
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
            }

            objectInputStream.close();
            fileInputStream.close();
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
        String filePath = "./src/" + folder + "/" + fileType.fileName + fileExtension;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            if (fileType == FileType.GUESTS) {
                objectOutputStream.writeObject(GUESTS);
            }
            objectOutputStream.close();
            fileOutputStream.close();
            return true;
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
            return false;
        }
    }

    private static boolean initializeDataBase() {
        
        return true;
    }

    public static boolean clearDatabase() {
        // Initialize empty data
        GUESTS = new HashMap<String, Guest>();
        ROOMS = new HashMap<String, Room>();
        writeSerializedObject(FileType.GUESTS);
        writeSerializedObject(FileType.ROOMS);
        return true;
    }

    private static boolean initializeRooms() {
        
        return true;
    }

    public static void main(String[] args) {
        Database.clearDatabase();
    }
}
