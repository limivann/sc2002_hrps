package src.controller;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.Data;

import src.database.Database;
import src.database.FileType;
import src.model.Invoice;
import src.model.MenuItem;
import src.model.Order;

public class InvoiceManager {
    public InvoiceManager() {

    }

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

    public static void printInvoice(String invoiceId) {
        if (!validateInvoiceId(invoiceId)) {
            return;
        }
        Invoice invoice = Database.INVOICES.get(invoiceId);
        printInvoiceDetails(invoice);
    }

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


    public static boolean validateInvoiceId(String invoiceId){
        if (!Database.INVOICES.containsKey(invoiceId)) {
            System.out.println("Invoice does not exist");
            return false;
        }
        return true;
    }
        

    /**
     * A method that print out the invoice <p>
     * See {@link Invoice} For the details of the reservation and order
     * @param invoice invoice of the reservation
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
        System.out.println(String.format("%55s %10s" ,"Tax Rate",taxRate));
        System.out.println(String.format("%55s %10s" ,"Discount Rate",discountRate));
        System.out.println(String.format("%55s %10s" ,"Total",total));
        System.out.println(String.format("%-66s", "").replace(" ", "-"));
    }
    
}
