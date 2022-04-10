package src.model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * The Class that handles the data of hotel's invoices.
 * @author Max
 * @version 1.0
 * @since 2022-3-30
 */
public class Invoice implements Serializable, Comparable<Invoice> {
    /**
     * For Java Serializable
     */
    private static final long serialVersionUID = 6L;
    /**
     * Id of the guest
     */
    private String guestId;
    
    /**
     * Room type of the stay
     */
    private String roomTypeAsStr;

    /**
     * Wifi enabled room 
     */
    private boolean isRoomWifiEnabled;

    /**
     * Room price of the room on the day of payment
     */
    private double roomPrice;

    /**
     * Id of the reservation
     */
    private String reservationId;

    /**
     * Nights spent in the hotel
     */
    private int nights;

    /**
     * ArrayList of {@link Order}(s) made by the guest
     */
    private ArrayList<Order> orders;
    /**
     * Total amount without tax rate and discount rate
     */
    private double subTotal;
    /**
     * Tax rate of the reservation
     */
    private double taxRate;
    /**
     * Discount rate of the reservation
     */
    private double discountRate;
    /**
     * Date which the payment is made
     */
    private String dateOfPayment;
    /**
     * Total amount with tax rate and discount rate
     */
    private double total;
    /**
     * Id of the invoice
     */
    private String invoiceId;
    
    /**
     * Constructor of Invoice
     * @param invoiceId Id of the invoice
     * @param guestId Id of the guest
     * @param roomTypeAsStr Room type of the stay
     * @param roomPrice Room price of the room
     * @param isRoomWifiEnabled Wifi enabled room 
     * @param reservationId Id of the reservation
     * @param nights Nights spent in the hotel
     * @param dateOfPayment Date which the payment is made
     * @param taxRate Tax rate of the invoice
     * @param discountRate Discount rate of the invoice
     * @param orders ArrayList of {@link Order} object that the room had made
     * @param subTotal Total amount without tax rate and discount rate
     * @param total Total amount with tax rate and discount rate
     */
    public Invoice(String invoiceId, String guestId, String roomTypeAsStr, double roomPrice, boolean isRoomWifiEnabled, String reservationId, int nights, String dateOfPayment,
            double taxRate, double discountRate, ArrayList<Order> orders ,double subTotal, double total) {
        // reservation will retrieve details for reservation id, guest id, room id
        // calculate sub total by searching orders
        setInvoiceId(invoiceId);
        setGuestId(guestId);
        setRoomTypeAsStr(roomTypeAsStr);
        setRoomPrice(roomPrice);
        setReservationId(reservationId);
        setNights(nights);
        setDateOfPayment(dateOfPayment);
        setTaxRate(taxRate);
        setDiscountRate(discountRate);
        setOrders(orders);
        setSubTotal(subTotal);
        setTotal(total);
    }
    /**
     * Sets the invoice id.
     * @param invoiceId Id of the invoice
     * @return {@code true} if sets successfully.
     */
    public boolean setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
        return true;
    }
    /**
     * Sets the date of payment.
     * @param dateOfPayment Date which the payment is made
     * @return {@code true} if sets successfully
     */
    private boolean setDateOfPayment(String dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
        return true;
    }
    /**
     * Sets the tax rate.
     * @param taxRate Tax rate of the invoice
     * @return {@code true} if sets successfully
     */
    private boolean setTaxRate(double taxRate) {
        if (taxRate < 0) {
            return false;
        }
        this.taxRate = taxRate;
        return true;
    }
    /**
     * Sets the discount rate. 
     * @param discountRate Discount rate of the invoice
     * @return {@code true} if sets successfully
     */
    private boolean setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
        return true;
    }
    /**
     * Sets the subtotal.
     * @param subTotal Total amount without tax rate and discount rate
     * @return {@code true} if sets successfully
     */
    private boolean setSubTotal(double subTotal) {
        this.subTotal = subTotal;
        return true;
    }
    /**
     * Sets the nights spent in the hotel
     * @param nights Nights spent in the hotel
     * @return {@code true} if sets successfully. Otherwise, {@code false} if the nights spend is negative
     */
    public boolean setNights(int nights) {
        if (nights < 0) {
            return false;
        }
        this.nights = nights;
        return true;
    }
    /**
     * Sets the total.
     * @param total Total amount with tax rate and discount rate
     * @return {@code true} if sets successfully
     */
    private boolean setTotal(double total) {
        this.total = total;
        return true;
    }
    /**
     * Sets the guest Id.
     * @param guestId Id of the guest
     * @return {@code true} if sets successfully
     */
    public boolean setGuestId(String guestId) {
        this.guestId = guestId;
        return true;
    }
    
    /**
     * Sets the room type of the stay
     * @param roomTypeAsStr Room type of the stay
     * @return {@code true} if sets successfully
     */
    public boolean setRoomTypeAsStr(String roomTypeAsStr) {
        this.roomTypeAsStr = roomTypeAsStr;
        return true;
    }

    /**
     * Sets if the room is wifi enabled 
     * @param isRoomWifiEnabled {@code true} if the room is wifi enabled. Otherwise, {@code false}.
     * @return {@code true} if the room is wifi enabled. Otherwise, {@code false}.
     */
    public boolean setRoomWifiEnabled(boolean isRoomWifiEnabled) {
        this.isRoomWifiEnabled = isRoomWifiEnabled;
        return true;
    }
    
    /**
     * Sets the room price of the room on the day of payment
     * @param roomPrice Room price of the room on the day of payment
     * @return {@code true} if sets successfully
     */
    public boolean setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
        return true;
    }

    /**
     * Sets the reservation Id.
     * @param reservationId Id of the reservation
     * @return {@code true} if sets successfully
     */
    public boolean setReservationId(String reservationId) {
        this.reservationId = reservationId;
        return true;
    }

    /**
     * Sets the orders made by the guest
     * @param orders ArrayList of {@link Order}(s) made by the guest
     * @return {@code true} if sets successfully
     */
    public boolean setOrders(ArrayList<Order> orders) {
        this.orders = orders;
        return true;
    }

    /**
     * Gets the total.
     * @return total amount with tax rate and discount rate
     */
    public double getTotal() {
        return total;
    }
    /**
     * Gets the date of payment.
     * @return date which the payment is made
     */
    public String getDateOfPayment() {
        return dateOfPayment;
    }
    /**
     * Gets the discount rate. 
     * @return discount rate of the invoice
     */
    public double getDiscountRate() {
        return discountRate;
    }
    /**
     * Gets the subtotal.
     * @return total amount without tax rate and discount rate.
     */
    public double getSubTotal() {
        return subTotal;
    }
    /**
     * Gets the tax rate.
     * @return tax rate of the invoice
     */
    public double getTaxRate() {
        return taxRate;
    }
    /**
     * Gets the guest Id.
     * @return Id of the guest
     */
    public String getGuestId() {
        return guestId;
    }

    /**
     * Gets the room type of the stay
     * @return Room type of the stay
     */
    public String getRoomTypeAsStr() {
        return roomTypeAsStr;
    }

    /**
     * Gets the room price of the room on the day of payment
     * @return Room price of the room on the day of payment
     */
    public double getRoomPrice() {
        return roomPrice;
    }

    /**
     * Gets if the room is wifi enabled
     * @return {@code true} if the room is wifi enabled. Otherwise, {@code false}.
     */
    public boolean getIsRoomWifiEnabled() {
        return isRoomWifiEnabled;
    }
    
    /**
     * Gets the nights spent in the hotel
     * @return Nights spent
     */
    public int getNights() {
        return nights;
    }
    /**
     * Gets the reservation Id.
     * @return Id of the reservation
     */
    public String getReservationId() {
        return reservationId;
    }
    /**
     * Gets the invoice Id.
     * @return Id of the invoice
     */
    public String getInvoiceId() {
        return invoiceId;
    }

    /**
     * Gets the orders made by the guest
     * @return ArrayList of {@link Order}(s) made by the guest
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }
    /**
    * Override toString method to show the simplified details of the invoice.
    * @return a string that contains invoice details
    */
    @Override
    public String toString() {
        String res = String.format("Invoice Id: %s\t\tDate Of Issue: %s\t\tTotal: %.2f", getInvoiceId(), getDateOfPayment(),getTotal());
        return res;
    }
    /**
     * Override compareTo method to compare different invoice objects according to invoice id.
     */
    @Override
    public int compareTo(Invoice invoice) {
        if (this == invoice) {
            return 0;
        }

        int thisInvoiceId = Integer.parseInt(this.getInvoiceId().substring(1));
        int thatInvoiceId = Integer.parseInt(invoice.getInvoiceId().substring(1));

        return thisInvoiceId - thatInvoiceId;
    }
}   
