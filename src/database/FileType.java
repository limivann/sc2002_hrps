package src.database;

public enum FileType {
    GUESTS("Guests"),
    ROOMS("Rooms"),
    RESERVATIONS("Reservations"),
    MENU_ITEMS("MenuItems"),
    ORDERS("Orders"),
    INVOICES("Invoices"),
    PRICES("Prices");

    public final String fileName;

    private FileType(String fileName) {
        this.fileName = fileName;
    }
}
