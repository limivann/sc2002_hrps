package src.controller;

import src.model.Reservation;
import src.model.Room;

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

    public static void printInvoice(Reservation reservation) {
        // TODO: Collab with max
        Invoice testInvoice = new Invoice(reservation);
        String receipt = "";
        receipt += String.format("Guest ID: %s\n", "G0001");
        receipt += String.format("receiptervation ID: %s\n", "R0001");
        receipt += String.format("Room ID: %s\n", "01-02");
        receipt += String.format("Sub-total: %,.2f\n", testInvoice.getSubTotal());
        receipt += String.format("Tax Rate: %,.2f\n", testInvoice.getTaxRate());
        receipt += String.format("Discount Rate: %,.2f\n", testInvoice.getDiscountRate());
        receipt += String.format("Total: %,.2f\n", testInvoice.getTotal());
        System.out.println(receipt);
    }

    public static void main(String[] args) {
        // Reservation testReservation = new Reservation("2022-03-08 12:00", "2022-03-24 12:11", 1, 1, 2);
        // PaymentManager.printInvoice(testReservation);
    }
}
