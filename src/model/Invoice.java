package src.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import src.controller.PaymentManager;
import src.helper.Helper;

public class Invoice implements Serializable {
    private static final long serialVersionUID = 6L;

    private String guestId;
    private String roomId;
    private String reservationId;
    private double subTotal;
    private double taxRate;
    private double discountRate;
    private String dateOfPayment;
    private double total;
    private String invoiceId;
    /**
     * Constructor of Invoice
     * @param invoiceId Id of the invoice
     * @param guestId Id of the guest
     * @param roomId Id of the room
     * @param reservationId Id of the reservation
     * @param dateOfPayment date which the payment is made
     * @param taxRate tax rate of the invoice
     * @param discountRate discount rate of the invoice
     * @param subTotal total amount without tax rate and discount rate
     * @param total total amount with tax rate and discount rate
     */
    public Invoice(String invoiceId, String guestId, String roomId, String reservationId, String dateOfPayment,
            double taxRate, double discountRate, double subTotal, double total) {
        // reservation will retrieve details for reservation id, guest id, room id
        // calculate sub total by searching orders
        setInvoiceId(invoiceId);
        setGuestId(guestId);
        setRoomId(roomId);
        setReservationId(reservationId);
        setDateOfPayment(dateOfPayment);
        setTaxRate(taxRate);
        setDiscountRate(discountRate);
        setSubTotal(subTotal);
        setTotal(total);
    }
    /**
     * A method that updates the invoice id
     * @param invoiceId Id of the invoice
     * @return true if updates successfully
     */
    public boolean setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
        return true;
    }
    /**
     * A method that updates the date of payment
     * @param dateOfPayment date which the payment is made
     * @return true if updates successfully
     */
    private boolean setDateOfPayment(String dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
        return true;
    }
    /**
     * A method that updates the tax rate
     * @param taxRate tax rate of the invoice
     * @return true if updates successfully
     */
    private boolean setTaxRate(double taxRate) {
        if (taxRate < 0) {
            return false;
        }
        this.taxRate = taxRate;
        return true;
    }
    /**
     * A method that updates the discount rate 
     * @param discountRate discount rate of the invoice
     * @return true if updates successfully
     */
    private boolean setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
        return true;
    }
    /**
     * A method that updates the subtotal
     * @param subTotal Total amount without tax rate and discount rate
     * @return true if updates successfully
     */
    private boolean setSubTotal(double subTotal) {
        this.subTotal = subTotal;
        return true;
    }
    /**
     * A method that updates the total
     * @param total Total amount with tax rate and discount rate
     * @return true if updates successfully
     */
    private boolean setTotal(double total) {
        this.total = total;
        return true;
    }
    /**
     * A method that updates the guest Id
     * @param guestId Id of the guest
     * @return true if updates successfully
     */
    public boolean setGuestId(String guestId) {
        this.guestId = guestId;
        return true;
    }
    /**
     * A method that updates the room Id
     * @param roomId Id of the room
     * @return true if updates successfully
     */
    public boolean setRoomId(String roomId) {
        this.roomId = roomId;
        return true;
    }
    /**
     * A method that updates the reservation Id
     * @param reservationId Id of the reservation
     * @return true if updates successfully
     */
    public boolean setReservationId(String reservationId) {
        this.reservationId = reservationId;
        return true;
    }
    /**
     * A method that returns total
     * @return total amount with tax rate and discount rate
     */
    public double getTotal() {
        return total;
    }
    /**
     * A method that returns date of payment
     * @return date which the payment is made
     */
    public String getDateOfPayment() {
        return dateOfPayment;
    }
    /**
     * A method that return discount rate
     * @return discount rate of the invoice
     */
    public double getDiscountRate() {
        return discountRate;
    }
    /**
     * A method that returns subtotal
     * @return total amount without tax rate and discount rate
     */
    public double getSubTotal() {
        return subTotal;
    }
    /**
     * A method that returns tax rate
     * @return tax rate of the invoice
     */
    public double getTaxRate() {
        return taxRate;
    }
    /**
     * A method that returns the guest Id
     * @return Id of the guest
     */
    public String getGuestId() {
        return guestId;
    }
    /**
     * A method that returns the room Id
     * @return Id of the room
     */
    public String getRoomId() {
        return roomId;
    }
    /**
     * A method that returns the reservation Id
     * @return Id of the reservation
     */
    public String getReservationId() {
        return reservationId;
    }
    /**
     * A method that returns the invoice Id
     * @return Id of the invoice
     */
    public String getInvoiceId() {
        return invoiceId;
    }
    /**
    * Override toString method to show the simplified details of the invoice
    * @return a string of invoice details
    */
    @Override
    public String toString() {
        String res = String.format("Invoice Id: %s, Guest Id: %s, Reservation Id: %s, Total: %f", getInvoiceId(), getGuestId(),
                getReservationId(), getTotal());
        return res;
    }
}   
