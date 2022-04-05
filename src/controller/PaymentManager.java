package src.controller;

import src.model.Reservation;
import src.model.Room;
import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Invoice;
/**
 * PaymentManager is a controller class that generates {@link Invoice} and handles payment. <p>
 * 
 * Whenever a {@link Room} has checked out, it will receive information from {@link GuestManager}, {@link RoomManager}, {@link ReservationManager}, and {@link RoomServiceManager}
 * to generate {@link Invoice} and print it upon payment. 
 * @author Lim Kang Wei, Ivan, Max
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
     * A method that calculates the subtotal of a reservation. <p>
     * Calls {@link RoomManager} to get room price, calls {@link ReservationManager} to retrieve number of stays and 
     * calls {@link RoomServiceManager} to get order price of the room. 
     * @param roomId id of the room
     * @param reservationId id of the reservation
     * @return subtotal of the reservation corresponding to the room id.
     */
    public static double calculateSubTotal(String roomId, String reservationId) {
        double roomPrice = RoomManager.getRoomPrice(roomId);
        if (roomPrice == -1) {
            return -1;
        }
        double numOfStays = ReservationManager.calculateNumOfStays(reservationId);
        if (numOfStays == -1) {
            return -1;
        }
        roomPrice *= numOfStays;
        double orderPrice = RoomServiceManager.calculateTotalOrderPrice(roomId);
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
        System.out.println(String.format("Nights: %d", invoice.getNights()));
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
    public static void handlePayment(String reservationId, String guestId) {
        Invoice invoice = generateInvoice(reservationId, guestId);
        printInvoice(invoice);
        System.out.println("Payment successful! See you again. :)");
    }
    
    /**
     * A method that generates invoice by fetching details from {@link ReservationManager} and {@link PromotionManager}
     * @param reservationId id of the reservation
     * @return {@link Invoice} object
     */
    public static Invoice generateInvoice(String reservationId, String guestId) {
        Reservation reservation = ReservationManager.search(reservationId);
        String roomId = reservation.getRoomId();
        int nights = ReservationManager.calculateNumOfStays(reservationId);
        double taxRate = PromotionManager.getTaxRate();
        double discountRate = PromotionManager.getDiscountRate();

        double subTotal = calculateSubTotal(roomId, reservationId);
        double total = calculateTotal(subTotal, discountRate, taxRate);
        int iid = Helper.generateUniqueId(Database.INVOICES);
        String invoiceId = String.format("I%04d", iid);
        String dateOfPayment = Helper.getTimeNow();
        Invoice invoice = new Invoice(invoiceId, guestId, roomId, reservationId, nights,dateOfPayment, taxRate, discountRate,
                subTotal,
                total);

        Database.INVOICES.put(invoiceId, invoice);
        Database.saveFileIntoDatabase(FileType.INVOICES);
        return invoice;
    }
}