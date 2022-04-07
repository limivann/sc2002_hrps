package src.model;

import java.io.Serializable;
/**
 * The Class that handles the discount rate and tax rate of the hotel.
 * @author Lim Kang Wei, Ivan
 * @version 1.0
 * @since 2020-04-04
 */
public class PromotionDetails implements Serializable {
    /**
     * For Java Serializable
     */
    private static final long serialVersionUID = 7L;

    /**
     * Discount rate of the hotel
     */
    private double discountRate;

    /**
     * Tax rate of the hotel
     */
    private double taxRate;


    /**
     * Default constructor of the class
     */
    public PromotionDetails() {
        // do nothing
    }
    
    /**
     * Constructor of the class
     * @param discountRate discount rate of the hotel
     * @param taxRate tax rate of the hotel
     */
    public PromotionDetails(double discountRate, double taxRate) {
        // initalise all prices
        setDiscountRate(discountRate);
        setTaxRate(taxRate);
    }
    //SETTERS
    /**
     * Sets the discount rate of the hotel
     * @param discountRate discount rate of the hotel
     * @return true if updates successfully
     */
    public boolean setDiscountRate(double discountRate) {
        if (discountRate <= 0 || discountRate > 1) {
            return false;
        }
        this.discountRate = discountRate;
        return true;
    }
    /**
     * Sets the tax rate of the hotel
     * @param taxRate tax rate of the hotel
     * @return true if updated successfully
     */
    public boolean setTaxRate(double taxRate) {
        if (taxRate < 0 || taxRate > 1) {
            return false;
        }
        this.taxRate = taxRate;
        return true;
    }
    
    /**
     * Gets the discount rate of the hotel
     * @return discount rate of the hotel
     */
    public double getDiscountRate() {
        return discountRate;
    }
    /**
     * Gets the tax rate of the hotel
     * @return tax rate of the hotel
     */
    public double getTaxRate() {
        return taxRate;
    }
}
