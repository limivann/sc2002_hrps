package src.controller;

import src.model.Reservation;
import src.model.Room;

import java.util.ArrayList;

import src.database.Database;
import src.helper.Helper;
import src.model.Invoice;
import src.model.Order;

/**
 * PaymentManager is a controller class that generates {@link Invoice} and handles payment. <p>
 * 
 * Whenever a {@link Room} has checked out, it will receive information from {@link GuestManager}, {@link RoomManager}, 
 * {@link ReservationManager}, {@link PromotionManager} and {@link InvoiceManager} to generate {@link Invoice} and print it upon payment. 
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
     * A method that generates invoice of a particular reservation during checkout<p>
     * See {@link Reservation} For the details of the reservation
     * @param reservationId Id of the reservation
     * @param guestId Id of the guest
     */
    public static void handlePayment(String reservationId, String guestId) {
        Invoice invoice = generateInvoice(reservationId, guestId);
        InvoiceManager.printInvoiceDetails(invoice);
        System.out.println("Payment successful! See you again. :)");
    }
    
    /**
     * A method that generates invoice by fetching details from {@link ReservationManager}, {@link RoomManager} and {@link PromotionManager}
     * @param reservationId Id of the reservation
     * @param guestIdToPay Id of the guest to pay
     * @return {@link Invoice} object
     */
    public static Invoice generateInvoice(String reservationId, String guestIdToPay) {
        String roomId = ReservationManager.searchReservation(reservationId).getRoomId();
        String roomTypeAsStr = RoomManager.searchRoom(roomId).getRoomType().roomTypeAsStr;
        boolean isRoomWifiEnabled = RoomManager.searchRoom(roomId).getIsWifiEnabled();
        double roomPrice = RoomManager.searchRoom(roomId).getPrice();
        int nights = ReservationManager.calculateNumOfStays(reservationId);
        double taxRate = PromotionManager.getTaxRate();
        double discountRate = PromotionManager.getDiscountRate(ReservationManager.searchReservation(reservationId).getCheckedInDate());
        ArrayList<Order> orders = RoomManager.searchRoom(roomId).getOrders();
        double subTotal = calculateSubTotal(roomId, reservationId);
        double total = calculateTotal(subTotal, discountRate, taxRate);
        int iid = Helper.generateUniqueId(Database.INVOICES);
        String invoiceId = String.format("I%04d", iid);
        String dateOfPayment = Helper.getTimeNow();

        return InvoiceManager.createInvoice(invoiceId, guestIdToPay, roomTypeAsStr, roomPrice, isRoomWifiEnabled,
                reservationId, nights, dateOfPayment, taxRate, discountRate, orders, subTotal, total);
    }
}