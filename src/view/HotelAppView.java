package src.view;

import src.helper.Helper;

/**
 * Viewing interface for the hotel administrator
 */
public class HotelAppView extends MainView{
    /**
     * Default constructor
     */
    protected GuestView guestView;
    protected RoomView roomView;
    protected ReservationView reservationView;
    protected RoomServiceAdminView roomServiceAdminView;
    protected HandleCheckInOutView handleCheckInOutView;
    protected ManagePaymentView managePaymentView;
    protected DatabaseView databaseView;
    
    public HotelAppView() {
        super();
        guestView = new GuestView();
        roomView = new RoomView();
        reservationView = new ReservationView();
        roomServiceAdminView = new RoomServiceAdminView();
        handleCheckInOutView = new HandleCheckInOutView();
        managePaymentView = new ManagePaymentView();
        databaseView = new DatabaseView();
        
    }
    /**
     * View Menu for Admin
     */
    @Override
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Manage Guest");
        System.out.println("(2) Manage Room");
        System.out.println("(3) Manage Reservation");
        System.out.println("(4) Manage RoomService");
        System.out.println("(5) Manage Check In / Check Out");
        System.out.println("(6) Manage Payment");
        System.out.println("(7) Manage Database");
        System.out.println("(8) Exit HRPS");   
    }
    /**
     * View Application for Hotel
     */
    @Override
    public void viewapp() {
        // init views
        

        int opt = -1;
        do{
            printMenu();
            opt = Helper.readInt(1, 8);
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
                    handleCheckInOutView.viewapp();
                    break;
                case 6:
                    managePaymentView.viewapp();
                    break;
                case 7:
                    databaseView.viewapp();
                    break;
                case 8:
                    break;
                default:
                    break;
            }
        } while (opt != 8);
        
    }
    
}
