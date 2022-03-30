package src.view;

import src.helper.Helper;

public class AdminView extends MainView{
    /**
     * Default constructor
     */
    public AdminView() {
        super();
    }
    /**
     * View Menu for Admin
     */
    @Override
    /**
     * Admin view menu
     */
    public void printMenu() {
        System.out.println("=== Admin View ===");
        System.out.println("Enter your choice (1-7)");
        System.out.println("(1) Manage Guest");
        System.out.println("(2) Manage Room");
        System.out.println("(3) Manage Reservation");
        System.out.println("(4) Manage RoomService (Customize Menu)");
        System.out.println("(5) Manage Promotion Details");
        System.out.println("(6) Manage Database");
        System.out.println("(7) Exit Admin View");   
    }
    /**
     * View Application for Admin
     */
    @Override
    /**
     * Admin View Application Menu
     */
    public void viewapp() {
        // init views
        GuestView guestView = new GuestView();
        RoomView roomView = new RoomView();
        ReservationView reservationView = new ReservationView();
        RoomServiceAdminView roomServiceAdminView = new RoomServiceAdminView();
        ManagePaymentView managePaymentView = new ManagePaymentView();
        DatabaseView databaseView = new DatabaseView();

        int opt = -1;
        do{
            printMenu();
            opt = Helper.readInt();
            switch (opt) {
                case 1:
                    guestView.viewapp();
                    break;
                case 2:
                    roomView.viewapp();
                    break;
                case 3:
                    reservationView.viewapp();
                    break;
                case 4:
                    roomServiceAdminView.viewapp();
                    break;
                case 5:
                    managePaymentView.viewapp();
                    break;
                case 6:
                    databaseView.viewapp();
                    break;
                case 7:
                    break;
                default:
                    // TODO: Throw Exception
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        } while (opt != 7);
        
    }
    
}
