package src.view;

import src.database.Database;
import src.helper.Helper;
/** 
 * DatabaseView provides the view to manage {@link Database}.
 * @author Max
 * @version 1.0
 * @since 2022-04-06
 */
public class DatabaseView extends MainView {
    /**
     * Default constructor of DatabaseView.
     */
    public DatabaseView() {
        super();
    }
    /**
     * View Menu of the DatabaseView.
     */
    @Override
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Database View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Initialize guests");
        System.out.println("(2) Initialize menu");
        System.out.println("(3) Reset database");
        System.out.println("(4) Exit Database View");
    }
    /**
     * View Application of the DatabaseView. <p>
     * See {@link Database} for more details.
     */
    @Override
    public void viewApp() {
        int opt = -1;
        do {
            printMenu();
            opt = Helper.readInt(1, 4);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Initialize guests");
                    if (initializeGuest()) {
                        System.out.println("Guest initialization successful");
                    } else {
                        System.out.println("Guest initialization unsuccessful");
                    }
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Database View > Initialize menu");
                    if (initializeMenu()) {
                        System.out.println("Menu initialization successful");
                    } else {
                        System.out.println("Menu initialization unsuccessful");
                    }
                    break;
                case 3:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Database View > Reset database");
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
            if (opt != 4) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 4);
    }
    /**
     * A method that initialize dummy data for Guest.
     * @return {@code true} if initialized successfully. Otherwise, {@code false} <p>
     * see {@link Database} for more initialization details.
     */
    private boolean initializeGuest() {
        return Database.initializeDummyGuests();
    }
    /**
     * A method that initialize dummy data for Menu.
     * @return {@code true} if initialized successfully. Otherwise, {@code false} <p>
     * see {@link Database} for more initialization details. 
     */
    private boolean initializeMenu() {
        return Database.initializeDummyMenu();
    }
    /**
     * A method that reset the database.
     * @return {@code true} if reset successfully. Otherwise, {@code false} <p>
     * see {@link Database} for more details.
     */
    private boolean resetDatabase() {
        if (Helper.promptConfirmation("reset the database")) {
            return Database.clearDatabase();
        } else {
            return false;
        }
    }
}
