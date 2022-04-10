package src.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import src.database.Database;
import src.database.FileType;
import src.model.Invoice;
import src.model.MenuItem;
import src.model.Order;

// for javadocs
import src.view.InvoiceView;
import src.view.HandleCheckInOutView;

/**
 * InvoiceManager is a controller class that acts as a "middleman"
 * between the view classes - {@link InvoiceView} and {@link HandleCheckInOutView} and the model class - {@link Invoice}. <p>
 * 
 * It can create and print {@link Invoice}. <p>
 * @author Ivan, Max
 * @version 1.0
 * @since 2022-04-07
 */
public class InvoiceManager {
    /**
     * Default constructor of InvoiceManager.
     */
    public InvoiceManager() {

    }
    /**
     * Creates an {@link Invoice}.
     * @param invoiceId Id of the invoice.
     * @param guestIdToPay Id of the guest who makes payment.
     * @param roomTypeAsStr The type of the room.
     * @param roomPrice The price of the room.
     * @param isRoomWifiEnabled A boolean value which indicates whether the room is Wifi enabled.
     * @param reservationId Id of the reservation.
     * @param nights Numbers of nights spent.
     * @param dateOfPayment The date which the payment is made.
     * @param taxRate The rate of tax.
     * @param discountRate The rate of discount. 
     * @param orders Orders of the room.
     * @param subTotal Total amount without discount rate and tax rate.
     * @param total Total amount with discount rate and tax rate.
     * @return The created invoice.
     */
    public static Invoice createInvoice(String invoiceId, String guestIdToPay, String roomTypeAsStr, double roomPrice,
            boolean isRoomWifiEnabled, String reservationId, int nights, String dateOfPayment, double taxRate,
            double discountRate, ArrayList<Order> orders, double subTotal, double total) {
        Invoice invoice = new Invoice(invoiceId, guestIdToPay, roomTypeAsStr, roomPrice, isRoomWifiEnabled,
                reservationId, nights, dateOfPayment, taxRate,
                discountRate, orders, subTotal, total);
        Database.INVOICES.put(invoiceId, invoice);
        Database.saveFileIntoDatabase(FileType.INVOICES);
        return invoice;
    }
    /**
     * Prints out a specified invoice by invoice Id.
     * @param invoiceId Id of the Invoice.
     */
    public static void printInvoice(String invoiceId) {
        if (!validateInvoiceId(invoiceId)) {
            return;
        }
        Invoice invoice = Database.INVOICES.get(invoiceId);
        printInvoiceDetails(invoice);
    }
    /**
     * Prints out all the invoices.
     */
    public static void printAllInvoices() {
        ArrayList<Invoice> sortedList = new ArrayList<Invoice>();
        for (Invoice invoice : Database.INVOICES.values()) {
            sortedList.add(invoice);
        }
        Collections.sort(sortedList);
        for (Invoice invoice : sortedList) {
            System.out.println(invoice);
        }
    }

    /**
     * Validates an invoice by invoice Id.
     * @param invoiceId Id of the invoice.
     * @return {@code true} if the invoice Id is valid. Otherwise, {@code false}.
     */
    public static boolean validateInvoiceId(String invoiceId){
        if (!Database.INVOICES.containsKey(invoiceId)) {
            System.out.println("Invoice does not exist");
            return false;
        }
        return true;
    }
        

    /**
     * A method that print out the invoice <p>
     * See {@link Invoice} For the details of the reservation and order.
     * @param invoice invoice of the reservation.
     */
    public static void printInvoiceDetails(Invoice invoice) {
        System.out.println("INVOICE: ");
        System.out.println(String.format("%-66s", "").replace(" ", "-"));
        System.out.println(
                String.format("%-15s: %s %18sDate Of Issue: %s", "Invoice Id" ,invoice.getInvoiceId(), "" ,invoice.getDateOfPayment().substring(0, 11)));
        String guestName = GuestManager.searchGuestById(invoice.getGuestId()).get(0).getName();
        System.out.println(String.format("%-15s: %s","Name" ,guestName));
        System.out.println(String.format("%-15s: %s", "Reservation Id", invoice.getReservationId()));
        System.out.println(String.format("%-15s: %d", "Night(s)",invoice.getNights()));
        System.out.println();
        System.out.println();
        HashMap<MenuItem, Integer> ordersMade = new HashMap<MenuItem, Integer>();
        for (Order order : invoice.getOrders()) {
            HashMap<MenuItem, Integer> currentOrders = order.getCurrentOrders();
            for (Map.Entry<MenuItem, Integer> currentEntry : currentOrders.entrySet()) {
                if (!ordersMade.containsKey(currentEntry.getKey())) {
                    ordersMade.put(currentEntry.getKey(), 0);
                }
                ordersMade.put(currentEntry.getKey(), ordersMade.get(currentEntry.getKey()) + currentEntry.getValue());
            }
        }

        System.out.println(String.format("%-40s %5s %5s %8s", "Description", "Unit Price", "Qty", "Amount"));
        System.out.println(String.format("%-66s", "").replace(" ", "─"));

        // print room types
        String roomTypeWithWifiStr = invoice.getRoomTypeAsStr();
        if (invoice.getIsRoomWifiEnabled()) {
            roomTypeWithWifiStr += " with wifi";
        } else {
            roomTypeWithWifiStr += " w/o wifi";
        }

        System.out.println(String.format("%-44s %5s %5s %9s", roomTypeWithWifiStr.toUpperCase(), invoice.getRoomPrice(),
                invoice.getNights(), invoice.getRoomPrice() * invoice.getNights()));
        

        for (Map.Entry<MenuItem, Integer> orderMade : ordersMade.entrySet()) {
            System.out.println(
                    String.format("%-44s %5s %5s %9s", orderMade.getKey().getName(), orderMade.getKey().getPrice(),
                            orderMade.getValue(), orderMade.getKey().getPrice() * orderMade.getValue()));
        }

        System.out.println();
        System.out.println();

        String subTotal = String.format("$%.2f", invoice.getSubTotal());
        String taxRate = String.format("%.0f%%", invoice.getTaxRate() * 100);
        String discountRate = String.format("%.0f%%", invoice.getDiscountRate() * 100);
        String total = String.format("$%.2f", invoice.getTotal());
        
        System.out.println(String.format("%55s %10s" ,"SubTotal",subTotal));
        System.out.println(String.format("%55s %10s", "Tax Rate", taxRate));
        String discountRateStr = ((invoice.getDiscountRate() == 0) ? "Weekend " : "Weekday ") + "Discount Rate";
        System.out.println(String.format("%55s %10s" , discountRateStr , discountRate));
        System.out.println(String.format("%55s %10s" ,"Total",total));
        System.out.println(String.format("%-66s", "").replace(" ", "-"));
    }
    
}
