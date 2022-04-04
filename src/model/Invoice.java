package src.model;

import java.io.Serializable;
/**
 * A class that handles invoice model.
 * @author Max
 * @version 1.0
 * @since 2022-3-30
 */
public class Invoice implements Serializable {
    /**
     * For Java Serializable
     */
    private static final long serialVersionUID = 6L;
    /**
     * Id of the guest
     */
    private String guestId;
    /**
     * Id of the room
     */
    private String roomId;
    /**
     * Id of the reservation
     */
    private String reservationId;

    /**
     * Nights spent in the hotel
     */
    private int nights;

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
     * @param roomId Id of the room
     * @param reservationId Id of the reservation
     * @param nights Nights spent in the hotel
     * @param dateOfPayment Date which the payment is made
     * @param taxRate Tax rate of the invoice
     * @param discountRate Discount rate of the invoice
     * @param subTotal Total amount without tax rate and discount rate
     * @param total Total amount with tax rate and discount rate
     */
    public Invoice(String invoiceId, String guestId, String roomId, String reservationId, int nights, String dateOfPayment,
            double taxRate, double discountRate, double subTotal, double total) {
        // reservation will retrieve details for reservation id, guest id, room id
        // calculate sub total by searching orders
        setInvoiceId(invoiceId);
        setGuestId(guestId);
        setRoomId(roomId);
        setReservationId(reservationId);
        setNights(nights);
        setDateOfPayment(dateOfPayment);
        setTaxRate(taxRate);
        setDiscountRate(discountRate);
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
     * Sets the room Id.
     * @param roomId Id of the room
     * @return {@code true} if sets successfully
     */
    public boolean setRoomId(String roomId) {
        this.roomId = roomId;
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
     * Gets the room Id.
     * @return Id of the room
     */
    public String getRoomId() {
        return roomId;
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
    * Override toString method to show the simplified details of the invoice.
    * @return a string that contains invoice details
    */
    @Override
    public String toString() {
        String res = String.format("Invoice Id: %s, Guest Id: %s, Reservation Id: %s, Total: %f", getInvoiceId(), getGuestId(),
                getReservationId(), getTotal());
        return res;
    }
}   
