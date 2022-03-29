package src.controller;

import src.model.Reservation;
import src.model.Room;
import src.model.enums.RoomStatus;

import javax.naming.spi.ResolveResult;
import javax.print.DocFlavor.STRING;
import javax.xml.crypto.Data;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Invoice;
import src.model.Order;
/**
 * The Class that handles payment.
 * @author Lim Kang Wei
 * @version 1.0
 * @since 2020-03-29
 */
public class PaymentManager {
    public PaymentManager() {
        // 
    }
    /**
     * A method that sums the room price and order price 
     * @param roomId id of the room
     * @return subtotal of the reservation corresponding to the room id
     */
    public static double calculateSubTotal(String roomId) {
        Room room = RoomManager.searchRoom(roomId);
        double roomPrice = room.getPrice();
        Order order = RoomServiceManager.searchOrderByRoom(roomId);
        if (order == null) {
            return -1;
        }
        double orderPrice = order.getTotalBill();
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
     * A method that print out the invoice
     * @param invoice invoice of the reservation
     * @see Invoice Invoice - Details of the reservation and order
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
     * A method that handles payment and make the room vacant
     * @param reservationId id of the reservation
     * @see Reservation - Details of a reservation
     */
    public static void handlePayment(String reservationId) {
        String roomId = ReservationManager.getRoomIdFromReservationId(reservationId);
        // Set room status
        RoomManager.updateRoomStatus(roomId, RoomStatus.VACANT);

        // payment
        Invoice invoice = generateInvoice(reservationId);
        printInvoice(invoice);
    }
    /**
     * A method that generates invoice by using getter methods from model classes
     * @param reservationId id of the reservation
     * @return the invoice object
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
        Invoice invoice = new Invoice(invoiceId, guestId, roomId, reservationId, dateOfPayment, taxRate, discountRate, subTotal,
                total);

        Database.INVOICES.put(invoiceId, invoice);
        Database.saveFileIntoDatabase(FileType.INVOICES);
        return invoice;
    }

    public static void main(String[] args) {

    }
}