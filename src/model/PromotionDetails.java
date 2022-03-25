package src.model;

import java.io.Serializable;

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

    public boolean setDiscountRate(double discountRate) {
        if (discountRate < 0 || discountRate > 1) {
            return false;
        }
        this.discountRate = discountRate;
        return true;
    }

    public boolean setTaxRate(double taxRate) {
        if (taxRate < 0) {
            return false;
        }
        this.taxRate = taxRate;
        return true;
    }

    public boolean setSingleRoomPrice(double singleRoomPrice) {
        if (singleRoomPrice <= 0) {
            return false;
        }
        this.singleRoomPrice = singleRoomPrice;
        return true;
    }

    public boolean setDoubleRoomPrice(double doubleRoomPrice) {
        if (doubleRoomPrice <= 0) {
            return false;
        }
        this.doubleRoomPrice = doubleRoomPrice;
        return true;
    }

    public boolean setDeluxeRoomPrice(double deluxeRoomPrice) {
        if (deluxeRoomPrice <= 0) {
            return false;
        }
        this.deluxeRoomPrice = deluxeRoomPrice;
        return true;
    }

    public boolean setVipSuitePrice(double vipSuitePrice) {
        if (vipSuitePrice <= 0) {
            return false;
        }
        this.vipSuitePrice = vipSuitePrice;
        return true;
    }

    public boolean setWifiEnabledMultiplier(double wifiEnabledMultiplier) {
        if (wifiEnabledMultiplier < 1) {
            return false;
        }
        this.wifiEnabledMultiplier = wifiEnabledMultiplier;
        return true;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getTaxRate() {
        return taxRate;
    }


    public double getSingleRoomPrice() {
        return singleRoomPrice;
    }

    public double getDoubleRoomPrice() {
        return doubleRoomPrice;
    }

    public double getDeluxeRoomPrice() {
        return deluxeRoomPrice;
    }

    public double getVipSuitePrice() {
        return vipSuitePrice;
    }

    public double getWifiEnabledMultiplier() {
        return wifiEnabledMultiplier;
    }
}
