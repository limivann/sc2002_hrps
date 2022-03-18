package src.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import src.controller.PaymentManager;

public class Invoice {
    private Reservation reservationDetails;
    private double subTotal;
    private double taxRate;
    private double discountRate;
    private String dateOfPayment;
    private double total;

    public Invoice(Reservation reservationDetails) {
        // reservation will retrieve details for reservation id, guest id, room id
        // calculate sub total by searching orders
        this.reservationDetails = reservationDetails;
        setDateOfPayment();
        setTaxRate(0.2);
        setDiscountRate();
        setSubTotal(reservationDetails);
        setTotal();
    }

    private void setDateOfPayment() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm");
        String formattedDate = date.format(format);
        this.dateOfPayment = formattedDate;
    }
    
    private void setTaxRate(double taxRate) {
        if (taxRate < 0) {
            // TODO: Throw error
        }
        this.taxRate = taxRate;
    }
    
    private void setDiscountRate() {
        PromotionDetails promotionDetails = new PromotionDetails();
        this.discountRate = PromotionDetails.getDiscountRate();
    }

    private void setSubTotal(Reservation reservationDetails) {
        double caculatedSubTotal = PaymentManager.calculateSubTotal(reservationDetails);
        this.subTotal = caculatedSubTotal;
    }

    private void setTotal() {
        // TODO: Calculate total
        double calculateTotal = PaymentManager.calculateTotal(this.subTotal, this.discountRate, this.taxRate);
        this.total = calculateTotal;
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

    public Reservation getReservationDetails() {
        return reservationDetails;
    }

    public double getSubTotal() {
        return subTotal;
    }
    
    public double getTaxRate() {
        return taxRate;
    }
}   
