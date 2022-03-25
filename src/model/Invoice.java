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
    
    public boolean setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
        return true;
    }

    private boolean setDateOfPayment(String dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
        return true;
    }
    
    private boolean setTaxRate(double taxRate) {
        if (taxRate < 0) {
            return false;
        }
        this.taxRate = taxRate;
        return true;
    }
    
    private boolean setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
        return true;
    }

    private boolean setSubTotal(double subTotal) {
        this.subTotal = subTotal;
        return true;
    }

    private boolean setTotal(double total) {
        this.total = total;
        return true;
    }

    public boolean setGuestId(String guestId) {
        this.guestId = guestId;
        return true;
    }

    public boolean setRoomId(String roomId) {
        this.roomId = roomId;
        return true;
    }

    public boolean setReservationId(String reservationId) {
        this.reservationId = reservationId;
        return true;
    }

    public double getTotal() {
        return total;
    }

    public String getDateOfPayment() {
        return dateOfPayment;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getSubTotal() {
        return subTotal;
    }
    
    public double getTaxRate() {
        return taxRate;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }
    
    @Override
    public String toString() {
        return "r";
    }
}   
