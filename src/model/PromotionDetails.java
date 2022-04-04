package src.model;

import java.io.Serializable;
/**
 * The Class that store the rates of the hotel.
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
     * Price of a single room for 1 night
     */
    private double singleRoomPrice;

    /**
     * Price of a double room for 1 night
     */
    private double doubleRoomPrice;

    /**
     * Price of a deluxe room for 1 night
     */
    private double deluxeRoomPrice;

    /**
     * Price of a vip suite for 1 night
     */
    private double vipSuitePrice;

    /**
     * Multiplier for wifi enabled room
     */
    private double wifiEnabledMultiplier;

    /**
     * Default constructor of the class
     */
    public PromotionDetails() {
        // do nothing
    }
    
    /**
     * 
     * @param discountRate discount rate of the hotel
     * @param taxRate tax rate of the hotel
     * @param singleRoomPrice single room price
     * @param doubleRoomPrice double room price
     * @param deluxeRoomPrice deluxe room price
     * @param vipSuitePrice vip suite price
     * @param wifiEnabledMultiplier wifi enabled multiplier
     */
    public PromotionDetails(double discountRate, double taxRate, double singleRoomPrice, double doubleRoomPrice, double deluxeRoomPrice, double vipSuitePrice, double wifiEnabledMultiplier) {
        // initalise all prices
        setDiscountRate(discountRate);
        setTaxRate(taxRate);
        setSingleRoomPrice(singleRoomPrice);
        setDoubleRoomPrice(doubleRoomPrice);
        setDeluxeRoomPrice(deluxeRoomPrice);
        setVipSuitePrice(vipSuitePrice);
        setWifiEnabledMultiplier(wifiEnabledMultiplier);
    }
    //SETTERS
    /**
     * Sets the discount rate of the hotel
     * @param discountRate discount rate of the hotel
     * @return true if updates successfully
     */
    public boolean setDiscountRate(double discountRate) {
        if (discountRate < 0 || discountRate > 1) {
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
     * Sets the price of a single room
     * @param singleRoomPrice price of a single room
     * @return true if updated successfully
     */
    public boolean setSingleRoomPrice(double singleRoomPrice) {
        if (singleRoomPrice <= 0) {
            return false;
        }
        this.singleRoomPrice = singleRoomPrice;
        return true;
    }
    /**
     * Sets the price of a double room
     * @param doubleRoomPrice price of a double room
     * @return true if updated successfully
     */
    public boolean setDoubleRoomPrice(double doubleRoomPrice) {
        if (doubleRoomPrice <= 0) {
            return false;
        }
        this.doubleRoomPrice = doubleRoomPrice;
        return true;
    }
    /**
     * Sets the price of a deluxe room
     * @param deluxeRoomPrice price of a deluxe room
     * @return true if updated successfully
     */
    public boolean setDeluxeRoomPrice(double deluxeRoomPrice) {
        if (deluxeRoomPrice <= 0) {
            return false;
        }
        this.deluxeRoomPrice = deluxeRoomPrice;
        return true;
    }
    /**
     * Sets the price of a vip suite 
     * @param vipSuitePrice price of a vip suite 
     * @return true if updated successfully
     */
    public boolean setVipSuitePrice(double vipSuitePrice) {
        if (vipSuitePrice <= 0) {
            return false;
        }
        this.vipSuitePrice = vipSuitePrice;
        return true;
    }
    /**
     * Sets the wifi-enabled multiplier
     * @param wifiEnabledMultiplier wifi enabled multiplier
     * @return true if updated successfully
     */
    public boolean setWifiEnabledMultiplier(double wifiEnabledMultiplier) {
        if (wifiEnabledMultiplier < 1) {
            return false;
        }
        this.wifiEnabledMultiplier = wifiEnabledMultiplier;
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
    /**
     * Gets the price of a single room
     * @return price of a single room
     */
    public double getSingleRoomPrice() {
        return singleRoomPrice;
    }
    /**
     * Gets the price of a double room
     * @return price of a double room
     */
    public double getDoubleRoomPrice() {
        return doubleRoomPrice;
    }
    /**
     * Gets the price of a deluxe room
     * @return price of a deluxe room
     */
    public double getDeluxeRoomPrice() {
        return deluxeRoomPrice;
    }
    /**
     * Gets the price of a vip suite
     * @return price of a vip suite
     */
    public double getVipSuitePrice() {
        return vipSuitePrice;
    }
    /**
     * Gets the wifi-enabled multiplier
     * @return wifi-enabled multiplier
     */
    public double getWifiEnabledMultiplier() {
        return wifiEnabledMultiplier;
    }
}
