package src.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import src.controller.PaymentManager;

public class Invoice {
    private String guestId;
    private String roomId;
    private String reservationId;
    private double subTotal;
    private double taxRate;
    private double discountRate;
    private String dateOfPayment;
    private double total;

    public Invoice(Reservation reservationDetails) {
        // reservation will retrieve details for reservation id, guest id, room id
        // calculate sub total by searching orders
        setGuestId(reservationDetails.getGuestId());
        setRoomId(reservationDetails.getRoomId());
        setReservationId(reservationDetails.getReservationId());
        setDateOfPayment();
        setTaxRate(0.2);
        setDiscountRate();
        setSubTotal(reservationDetails.getRoomId());
        setTotal();
    }

    private boolean setDateOfPayment() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm");
        String formattedDate = date.format(format);
        this.dateOfPayment = formattedDate;
        return true;
    }
    
    private boolean setTaxRate(double taxRate) {
        if (taxRate < 0) {
            return false;
        }
        this.taxRate = taxRate;
        return true;
    }
    
    private boolean setDiscountRate() {
        PromotionDetails promotionDetails = new PromotionDetails();
        this.discountRate = PromotionDetails.getDiscountRate();
        return true;
    }

    private boolean setSubTotal(String roomId) {
        double caculatedSubTotal = PaymentManager.calculateSubTotal(roomId);
        this.subTotal = caculatedSubTotal;
        return true;
    }

    private boolean setTotal() {
        // TODO: Calculate total
        double calculateTotal = PaymentManager.calculateTotal(this.subTotal, this.discountRate, this.taxRate);
        this.total = calculateTotal;
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
}   
