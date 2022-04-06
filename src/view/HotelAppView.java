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
    protected HandleCheckInOutView handleCheckInOutView;
    protected ManagePaymentView managePaymentView;
    protected DatabaseView databaseView;
    protected MenuView menuView;
    protected OrderView orderView;
    
    public HotelAppView() {
        super();
        guestView = new GuestView();
        roomView = new RoomView();
        reservationView = new ReservationView();
        handleCheckInOutView = new HandleCheckInOutView();
        managePaymentView = new ManagePaymentView();
        databaseView = new DatabaseView();
        menuView = new MenuView();
        orderView = new OrderView();
        
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
        System.out.println("(4) Manage Orders");
        System.out.println("(5) Manage Hotel Menu");
        System.out.println("(6) Manage Check In / Check Out");
        System.out.println("(7) Manage Payment");
        System.out.println("(8) Manage Database");
        System.out.println("(9) Exit HRPS");   
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
            opt = Helper.readInt(1, 9);
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
                    orderView.viewapp();
                    break;
                case 5:
                    menuView.viewapp();
                    break;
                case 6:
                    handleCheckInOutView.viewapp();
                    break;
                case 7:
                    managePaymentView.viewapp();
                    break;
                case 8:
                    databaseView.viewapp();
                    break;
                case 9:
                    break;
                default:
                    break;
            }
        } while (opt != 9);
        
    }
    
}
