package src;

import java.util.ArrayList;

public class Payment {
    private double amountToPay;
    private double taxRate;
    private double discountRate;
    private ArrayList<PaymentItems> paymentItems;

    public Payment(double taxRate, double discountRate, int roomId) {

    }
    
    public double getAmountToPay() {
        return amountToPay;
    }

    public double getTaxRate() {
        return taxRate;
    }
    
    public double getDiscountRate() {
        return discountRate;
    }

    
}
