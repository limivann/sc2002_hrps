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
public class PaymentManager {
    public PaymentManager() {
        // 
    }

    public static double calculateSubTotal(String roomId) {
        Room room = RoomManager.searchRoom(roomId);
        double roomPrice = room.getPrice();
        Order order = RoomServiceManager.searchOrderByRoom(roomId);
        double orderPrice = 0;
        if (order != null) {
            orderPrice = order.getTotalBill();
        }
        return roomPrice + orderPrice;
    }

    public static double calculateTotal(double subTotal, double discountRate, double taxRate) {
        return subTotal * (1 + taxRate) * (1 - discountRate);
    }

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

    public static void handlePayment(String reservationId) {
        Invoice invoice = generateInvoice(reservationId);
        printInvoice(invoice);
        System.out.println("Payment successful! See you again. :)");
    }

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