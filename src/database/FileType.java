package src.database;

/**
 * An Enum that corresponds to the different file types that the database will read from and write to.
 * @author Ivan
 * @version 1.0
 * @since 2022-03-30
 */
public enum FileType {
    /**
     * File type corresponding to the Guests file.
     */
    GUESTS("Guests"),

    /**
     * File type corresponding to the Rooms file.
     */
    ROOMS("Rooms"),

    /**
     * File type corresponding to the Reservation file.
     */
    RESERVATIONS("Reservations"),

    /**
     * File type corresponding to the MenuItems file.
     */
    MENU_ITEMS("MenuItems"),

    /**
     * File type corresponding to the Orders file.
     */
    ORDERS("Orders"),

    /**
     * File type corresponding to the Invoices file.
     */
    INVOICES("Invoices"),

    /**
     * File type corresponding to the Prices file.
     */
    PRICES("Prices");

    /**
     * A String value for the FileType for retrieving purposes.
     */
    public final String fileName;

    /**
     * Constructor for the FileType Enum.
     * @param fileName File name of the file
     */
    private FileType(String fileName) {
        this.fileName = fileName;
    }
}
