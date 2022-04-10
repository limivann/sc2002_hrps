package src.view;

import java.util.ArrayList;
import src.controller.GuestManager;
import src.controller.OrderManager;
import src.controller.PaymentManager;
import src.controller.ReservationManager;
import src.controller.RoomManager;
import src.helper.Helper;

// for javadocs
import src.model.Room;
/**
 * HandleCheckInCheckOutView provides the view to take user input which check in or check out {@link Room}.
 * @author Lim Kang Wei, Max
 * @version 1.0
 * @since 2020-04-06
 */
public class HandleCheckInOutView extends MainView {
    /**
     * Default constructor for the HandleCheckInCheckOutView.
     */
    public HandleCheckInOutView() {
        super();
    }
    /**
     * View Menu of the HandleCheckInCheckOutView.
     */
    @Override
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Check In / Check Out View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Check In Room");
        System.out.println("(2) Check Out Room");
        System.out.println("(3) Exit Check In / Check Out View");
    }
    /**
     * View Application of the HandleCheckInCheckOutView. 
     */
    @Override
    public void viewApp() {  
        int opt = -1;
        do{
            printMenu();
            opt = Helper.readInt(1,3);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Check In / Check Out View > Check In Room");
                    checkIn();
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Check In / Check Out View > Check Out Room");
                    checkOut();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice ... Please try again");
                    break;
            }
            if (opt != 3) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 3);
        
    }
    /**
     * View for Check In reservation.
     * see {@link ReservationManager} for more reservation management details.
     */
    private void checkIn() {
        ReservationManager.checkReservationStatus();
        System.out.println("Please enter reservation id (RXXXX)");
        String reservationId = Helper.readString();
        if (!ReservationManager.validateReservationId(reservationId)) {
            return;
        }
        if (ReservationManager.checkInReservation(reservationId)) {
            System.out.println(String.format("Check in complete for reservation id: %s", reservationId));
        }
    }
    /**
     * View for Check Out reservation.
     * see {@link ReservationManager} for more reservation management details. 
     */
    private void checkOut() {
        System.out.println("Please enter reservation id (RXXXX)");
        String reservationId = Helper.readString();
        if (!ReservationManager.validateReservationId(reservationId)) {
            return;
        }
        if (ReservationManager.checkOutReservation(reservationId)) {
            System.out.println(String.format("Check out complete for reservation id: %s", reservationId));
        } else {
            return;
        }
        String paymentOptStr = promptPayment() == 1 ? "Cash" : "Credit Card";
        System.out.println("You have chosen to pay by " + paymentOptStr);

        ArrayList<String> guestsToPay = ReservationManager.searchReservation(reservationId).getGuestIds();
        System.out.println("Please select which guest to make the payment ");
        for (int i = 1; i <= guestsToPay.size(); i++) {
            System.out.println(String.format("(%d) %s: %s", i, guestsToPay.get(i - 1),
                    GuestManager.searchGuestById(guestsToPay.get(i - 1)).get(0).getName()));
        }
        if (guestsToPay.size() == 0) {
            System.out.println("This reservation has no guest");
            return;
        }
        int opt = Helper.readInt(1, guestsToPay.size());

        PaymentManager.handlePayment(reservationId, guestsToPay.get(opt-1));

        //  remove all order details of that room
        RoomManager.updateRoomOrders(ReservationManager.getRoomIdFromReservationId(reservationId), null, true);

        // in orders, update all orders made by that room to be delivered
        OrderManager.updateAllRoomOrderToDelivered(ReservationManager.getRoomIdFromReservationId(reservationId));
    }
    /**
     * Prompt Payment option 
     * @return choice of payment method 
     */
    private int promptPayment() {
        System.out.println("Please select a payment method (1-2)");
        System.out.println("(1) Cash");
        System.out.println("(2) Credit Card");
        int opt = Helper.readInt();
        return opt;
    }
}
