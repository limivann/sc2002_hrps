package src.controller;

import src.model.Reservation;
import src.model.Room;
import src.model.enums.RoomStatus;

import javax.print.DocFlavor.STRING;
import javax.xml.crypto.Data;

import src.database.Database;
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
        if (order == null) {
            return -1;
        }
        double orderPrice = order.getTotalBill();
        return roomPrice + orderPrice;
    }

    public static double calculateTotal(double subTotal, double discountRate, double taxRate) {
        return subTotal * (1 + taxRate) * (1 - discountRate);
    }

    public static void printInvoice(Invoice inovice) {
        // String receipt = "";
        // receipt += String.format("Guest ID: %s\n", "G0001");
        // receipt += String.format("receiptervation ID: %s\n", "R0001");
        // receipt += String.format("Room ID: %s\n", "01-02");
        // receipt += String.format("Sub-total: %,.2f\n", testInvoice.getSubTotal());
        // receipt += String.format("Tax Rate: %,.2f\n", testInvoice.getTaxRate());
        // receipt += String.format("Discount Rate: %,.2f\n", testInvoice.getDiscountRate());
        // receipt += String.format("Total: %,.2f\n", testInvoice.getTotal());
        // System.out.println(receipt);
    }

    public static void handlePayment(String reservationId) {
        String roomId = ReservationManager.getRoomIdFromReservationId(reservationId);
        // Set room status
        RoomManager.updateRoomStatus(roomId, RoomStatus.VACANT);

        // payment
        Invoice invoice = generateInvoice(reservationId);
    }

    public static Invoice generateInvoice(String reservationId) {
        String guestId = ReservationManager.search(reservationId).getGuestId();
        return null;
    }

    public static void main(String[] args) {

    }
}

// public Invoice(String guestId, String roomId, String reservationId, String dateOfPayment, double taxRate, double discountRate, double subTotal, double total) {