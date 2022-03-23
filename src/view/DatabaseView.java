package src.view;

import src.database.Database;
import src.helper.Helper;

public class DatabaseView extends MainView {

    public DatabaseView() {
        super();
    }

    @Override
    public void printMenu() {
        System.out.println("=== Manage Database View ===");
        System.out.println("Please select an option (1-4)");
        System.out.println("(1) Initialize guests");
        System.out.println("(2) Initialize menu");
        System.out.println("(3) Reset database");
        System.out.println("(4) Exit Manage Database View");
    }

    @Override
    public void viewapp() {
        int opt = -1;
        do {
            printMenu();
            opt = Helper.readInt();
            switch (opt) {
                case 1:
                    // Init guests
                    if (initializeGuest()) {
                        System.out.println("Guest initialization successful");
                    } else {
                        System.out.println("Guest initialization unsuccessful");
                    }
                    break;
                case 2:
                    // init menu
                case 3:
                    if (resetDatabase()) {
                        System.out.println("Database cleared");
                    }
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (opt != 4);
    }
    
    public boolean initializeGuest() {
        return Database.initalizeDummyGuests();
    }
    
    public boolean resetDatabase() {
        if (Helper.promptConfirmation("reset the database")) {
            return Database.clearDatabase();
        } else {
            return false;
        }
    }
}
