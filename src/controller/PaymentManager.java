package src.controller;

import src.model.Reservation;
import src.model.Invoice;
public class PaymentManager {
    public PaymentManager() {
        // 
    }

    public static double calculateSubTotal(Reservation reservationDetails) {
        // TODO: Search for room 
        // double subTotal = reservationDetails.getSubTotal();
        return 100;
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
