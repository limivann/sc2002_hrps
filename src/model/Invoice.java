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
        setDiscountRate(0.2);
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
    
    private void setDiscountRate(double discountRate) {
        if (discountRate < 0 || discountRate > 1) {
            // TODO: Throw error
        }
        this.discountRate = discountRate;
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

    @Override
    public String toString() {
        // TODO: get reservation details
        String res = "";
        res += String.format("Guest ID: %s\n", "G0001");
        res += String.format("Reservation ID: %s\n", "R0001");
        res += String.format("Room ID: %s\n", "01-02");
        res += String.format("Sub-total: %,.2f\n", getSubTotal());
        res += String.format("Tax Rate: %,.2f\n", getTaxRate());
        res += String.format("Discount Rate: %,.2f\n", getDiscountRate());
        res += String.format("Total: %,.2f\n", getTotal());

        return res;
    }
}   
