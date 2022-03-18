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

    public void printInvoice(Reservation reservation) {
        // TODO: Collab with max
        Invoice testInvoice = new Invoice(reservation);
        System.out.println(testInvoice);
    }

    public static void main(String[] args) {
        Reservation testReservation = new Reservation("2022-03-08 12:00", "2022-03-24 12:11", 1, 1, 2);
        PaymentManager paymentManager = new PaymentManager();
        paymentManager.printInvoice(testReservation);
    }
}
