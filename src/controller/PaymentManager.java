package src.controller;

import src.model.Reservation;
import src.model.Room;
import java.util.ArrayList;
import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Invoice;
import src.model.Order;
/**
 * The Class that handles payment details.
 * @author Lim Kang Wei, Ivan
 * @version 1.0
 * @since 2022-04-04
 */
public class PaymentManager {
    
    /**
     * Default constructor of the PaymentManager
     */
    public PaymentManager() {
        
    }
    
    /**
     * A method that sums the room price and order price. <p>
     * Calls {@link RoomManager} to search for room and calls {@link RoomServiceManager} to get all orders of the room. 
     * @param roomId id of the room
     * @return subtotal of the reservation corresponding to the room id.
     */
    public static double calculateSubTotal(String roomId) {
        Room room = RoomManager.searchRoom(roomId);
        double roomPrice = room.getPrice();
        ArrayList<Order> orders = RoomServiceManager.searchOrderByRoom(roomId);
        double orderPrice = 0;
        for (Order order : orders) {
            orderPrice += order.getTotalBill();
        }
        return roomPrice + orderPrice;
    }
    /**
     * A method that sums the subtotal and the discount rate and tax rate
     * @param subTotal subtotal of the reservation 
     * @param discountRate discount rate of the hotel
     * @param taxRate tax rate of the hotel
     * @return total of the reservation
     */
    public static double calculateTotal(double subTotal, double discountRate, double taxRate) {
        return subTotal * (1 + taxRate) * (1 - discountRate);
    }
    /**
     * A method that print out the invoice <p>
     * See {@link Invoice} For the details of the reservation and order
     * @param invoice invoice of the reservation
     */
    public static void printInvoice(Invoice invoice) {
        System.out.println(
                String.format("Invoice Id: %s\t Date: %s", invoice.getInvoiceId(), invoice.getDateOfPayment()));
        String guestName = GuestManager.searchGuestById(invoice.getGuestId()).get(0).getName();
        System.out.println(String.format("Name: %s\n\nReservation Id: %s", guestName, invoice.getReservationId()));
        System.out.println(String.format("SubTotal %.2f", invoice.getSubTotal()));
        System.out.println(String.format("Tax Rate %.0f%%", invoice.getTaxRate() * 100));
        System.out.println(String.format("Discount Rate: %.0f%%", invoice.getDiscountRate() * 100));
        System.out.println(String.format("Total: %.2f", invoice.getTotal()));
    }

    /**
     * A method that generates invoice of a particular reservation during checkout<p>
     * See {@link Reservation} For the details of the reservation
     * @param reservationId id of the reservation
     */
    public static void handlePayment(String reservationId) {
        Invoice invoice = generateInvoice(reservationId);
        printInvoice(invoice);
        System.out.println("Payment successful! See you again. :)");
    }
    
    /**
     * A method that generates invoice by fetching details from {@link ReservationManager} and {@link PromotionManager}
     * @param reservationId id of the reservation
     * @return {@link Invoice} object
     */
    public static Invoice generateInvoice(String reservationId) {
        Reservation reservation = ReservationManager.search(reservationId);
        String guestId = reservation.getGuestId();
        String roomId = reservation.getRoomId();
        double taxRate = PromotionManager.getTaxRate();
        double discountRate = PromotionManager.getDiscountRate();

        double subTotal = calculateSubTotal(roomId);
        double total = calculateTotal(subTotal, discountRate, taxRate);
        int iid = Helper.generateUniqueId(Database.INVOICES);
        String invoiceId = String.format("I%04d", iid);
        String dateOfPayment = Helper.getTimeNow();
        Invoice invoice = new Invoice(invoiceId, guestId, roomId, reservationId, dateOfPayment, taxRate, discountRate,
                subTotal,
                total);

        Database.INVOICES.put(invoiceId, invoice);
        Database.saveFileIntoDatabase(FileType.INVOICES);
        return invoice;
    }
}