package src.view;

import src.helper.Helper;
// for javadocs
import src.model.Guest;
import src.model.Room;
import src.model.Reservation;
import src.model.PromotionDetails;
import src.database.Database;
import src.model.MenuItem;
import src.model.Order;
import src.model.Invoice;

 /**
 * Viewing interface for the hotel administrator
 * @author Max, Ivan
 * @version 1.0
 * @since 2020-04-10
 */
public class HotelAppView extends MainView{
    /**
     * View Menu for managing {@link Guest}.
     */
    protected GuestView guestView;
    /**
     * View Menu for managing {@link Room}.
     */
    protected RoomView roomView;
    /**
     * View Menu for managing {@link Reservation}.
     */
    protected ReservationView reservationView;
    /**
     * View Menu for handling Check In and Check Out.
     */
    protected HandleCheckInOutView handleCheckInOutView;
    /**
     * View Menu for managing {@link PromotionDetails}.
     */
    protected ManagePaymentView managePaymentView;
    /**
     * View Menu for managing {@link Database}.
     */
    protected DatabaseView databaseView;
    /**
     * View Menu for managing {@link MenuItem}.
     */
    protected MenuView menuView;
    /**
     * View Menu for managing {@link Order}.
     */
    protected OrderView orderView;
    
    /**
     * View Menu for managing {@link Invoice}.
     */
    protected InvoiceView invoiceView;
    
    /**
     * Default constructor for the HotelAppView.
     */
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
        invoiceView = new InvoiceView();
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
        System.out.println("(7) Manage Promotion Details");
        System.out.println("(8) Manage Invoices");
        System.out.println("(9) Manage Database");
        System.out.println("(10) Exit HRPS");   
    }
    /**
     * View Application for Hotel
     */
    @Override
    public void viewApp() {
        // init views
        
        int opt = -1;
        do{
            printMenu();
            opt = Helper.readInt(1, 10);
            switch (opt) {
                case 1:
                    guestView.viewApp();
                    break;
                case 2:
                    roomView.viewApp();
                    break;
                case 3:
                    reservationView.viewApp();
                    break;
                case 4:
                    orderView.viewApp();
                    break;
                case 5:
                    menuView.viewApp();
                    break;
                case 6:
                    handleCheckInOutView.viewApp();
                    break;
                case 7:
                    managePaymentView.viewApp();
                    break;
                case 8:
                    invoiceView.viewApp();
                    break;
                case 9:
                    databaseView.viewApp();
                    break;
                case 10:
                    break;
                default:
                    break;
            }
        } while (opt != 10);
        
    }
    
}
