package src.controller;

import src.model.Reservation;
import src.model.Room;
import src.model.enums.RoomType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.Invoice;
import src.model.MenuItem;
import src.model.Order;
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
        ArrayList<Order> orders = RoomManager.searchRoom(roomId).getOrders();
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
     * A method that generates invoice by fetching details from {@link ReservationManager}, {@link RoomManager} and {@link PromotionManager}
     * @param reservationId id of the reservation
     * @return {@link Invoice} object
     */
    public static Invoice generateInvoice(String reservationId, String guestIdToPay) {
        Reservation reservation = ReservationManager.search(reservationId);
        String roomId = reservation.getRoomId();
        String roomTypeAsStr = RoomManager.searchRoom(roomId).getType().roomTypeAsStr;
        boolean isRoomWifiEnabled = RoomManager.searchRoom(roomId).getIsWifiEnabled();
        double roomPrice = RoomManager.searchRoom(roomId).getPrice();
        int nights = ReservationManager.calculateNumOfStays(reservationId);
        double taxRate = PromotionManager.getTaxRate();
        double discountRate = PromotionManager.getDiscountRate();
        ArrayList<Order> orders = RoomManager.searchRoom(roomId).getOrders();
        double subTotal = calculateSubTotal(roomId, reservationId);
        double total = calculateTotal(subTotal, discountRate, taxRate);
        int iid = Helper.generateUniqueId(Database.INVOICES);
        String invoiceId = String.format("I%04d", iid);
        String dateOfPayment = Helper.getTimeNow();

        Invoice invoice = new Invoice(invoiceId, guestIdToPay, roomTypeAsStr, roomPrice, isRoomWifiEnabled,
                reservationId, nights, dateOfPayment, taxRate,
                discountRate, orders, subTotal, total);

        Database.INVOICES.put(invoiceId, invoice);
        Database.saveFileIntoDatabase(FileType.INVOICES);
        return invoice;
    }
}