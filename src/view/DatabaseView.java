package src.view;

import src.database.Database;
import src.helper.Helper;
/** 
 * Class to update {@link Database} 
 * @author Max
 * @version 1.0
 * @since 2022-03-29
 */
public class DatabaseView extends MainView {
    /**
     * Default constructor of Database.
     */
    public DatabaseView() {
        super();
    }
    /**
     * View Menu for Database.
     */
    @Override
    public void printMenu() {
        System.out.println("=== Manage Database View ===");
        System.out.println("Please select an option (1-4)");
        System.out.println("(1) Initialize guests");
        System.out.println("(2) Initialize menu");
        System.out.println("(3) Reset database");
        System.out.println("(4) Exit Manage Database View");
    }
    /**
     * View Application for Database.
     */
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
                    if (initializeMenu()) {
                        System.out.println("Menu initialization successful");
                    } else {
                        System.out.println("Menu initialization unsuccessful");
                    }
                    break;
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
    /**
     * A method that initialize dummy data for Guest.
     * @return true if initialized successfully <p>
     * see {@link Database} for more initialization details.
     */
    public boolean initializeGuest() {
        return Database.initializeDummyGuests();
    }
    /**
     * A method that initialize dummy data for Menu.
     * @return true if initialized successfully <p>
     * see {@link Database} for more initialization details. 
     */
    public boolean initializeMenu() {
        return Database.initializeDummyMenu();
    }
    /**
     * A method that reset the database.
     * @return true if reset successfully <p>
     * see {@link Database} for more details.
     */
    public boolean resetDatabase() {
        if (Helper.promptConfirmation("reset the database")) {
            return Database.clearDatabase();
        } else {
            return false;
        }
    }
}
