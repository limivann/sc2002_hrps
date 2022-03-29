package src.model;

import java.io.Serializable;
/**
 * The Class that store the rates of the hotel.
 * @author Lim Kang Wei
 * @version 1.0
 * @since 2020-03-29
 */
public class PromotionDetails implements Serializable {
    private static final long serialVersionUID = 7L;
    private double discountRate;

    // Tax rate 
    private double taxRate;

    // Prices of all rooms
    private double singleRoomPrice;
    private double doubleRoomPrice;
    private double deluxeRoomPrice;
    private double vipSuitePrice;

    // Multiplier for wifi enabled room
    private  double wifiEnabledMultiplier;

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
     * A method that updates the discount rate of the hotel
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
     * A method that updates the tax rate of the hotel
     * @param taxRate tax rate of the hotel
     * @return true if updated successfully
     */
    public boolean setTaxRate(double taxRate) {
        if (taxRate < 0) {
            return false;
        }
        this.taxRate = taxRate;
        return true;
    }
    /**
     * A method that updates the price of a single room
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
     * A method that updates the price of a double room
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
     * A method that updates the price of a deluxe room
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
     * A method that updates the price of a vip suite 
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
     * A method that updates the wifi-enabled multiplier
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
     * A method that returns the discount rate of the hotel
     * @return discount rate of the hotel
     */
    public double getDiscountRate() {
        return discountRate;
    }
    /**
     * A method that returns the tax rate of the hotel
     * @return tax rate of the hotel
     */
    public double getTaxRate() {
        return taxRate;
    }
    /**
     * A method that returns the price of a single room
     * @return price of a single room
     */
    public double getSingleRoomPrice() {
        return singleRoomPrice;
    }
    /**
     * A method that returns the price of a double room
     * @return price of a double room
     */
    public double getDoubleRoomPrice() {
        return doubleRoomPrice;
    }
    /**
     * A method that returns the price of a deluxe room
     * @return price of a deluxe room
     */
    public double getDeluxeRoomPrice() {
        return deluxeRoomPrice;
    }
    /**
     * A method that returns the price of a vip suite
     * @return price of a vip suite
     */
    public double getVipSuitePrice() {
        return vipSuitePrice;
    }
    /**
     * A method that returns the wifi-enabled multiplier
     * @return wifi-enabled multiplier
     */
    public double getWifiEnabledMultiplier() {
        return wifiEnabledMultiplier;
    }
}
