package src;

public enum FileType {
    GUESTS("Guests"),
    ROOMS("Rooms"),
    RESERVATIONS("Reservations"),
    MENU("Menu"),
    ORDERS("Orders"),
    INVOICES("Invoices");

    public final String fileName;

    private FileType(String fileName) {
        this.fileName = fileName;
    }
}
