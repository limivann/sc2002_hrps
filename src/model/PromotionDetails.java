package src.model;

public class PromotionDetails {
    private static double discountRate;

    // Tax rate of all rooms
    private static double singleRoomTax;
    private static double doubleRoomTax;
    private static double deluxeRoomTax;
    private static double vipSuiteTax;

    // Prices of all rooms
    private static double singleRoomPrice;
    private static double doubleRoomPrice;
    private static double deluxeRoomPrice;
    private static double vipSuitePrice;


    public PromotionDetails() {
        // initalise all prices
        PromotionDetails.discountRate = 0.3;
        
        PromotionDetails.singleRoomTax = 0.1;
        PromotionDetails.doubleRoomTax = 0.2;
        PromotionDetails.deluxeRoomTax = 0.3;
        PromotionDetails.vipSuiteTax = 0.4;

        PromotionDetails.singleRoomPrice = 30;
        PromotionDetails.doubleRoomPrice = 60;
        PromotionDetails.deluxeRoomTax = 70;
        PromotionDetails.vipSuitePrice = 120;
    }

    public static boolean setDiscountRate(double discountRate) {
        if (discountRate < 0 || discountRate > 1) {
            return false;
        }
        PromotionDetails.discountRate = discountRate;
        return true;
    }

    public static boolean setSingleRoomTax(double singleRoomTax) {
        if (singleRoomTax < 0) {
            return false;
        }
        PromotionDetails.singleRoomTax = singleRoomTax;
        return true;
    }

    public static boolean setDoubleRoomTax(double doubleRoomTax) {
        if (doubleRoomTax < 0) {
            return false;
        }
        PromotionDetails.doubleRoomTax = doubleRoomTax;
        return true;
    }

    public static boolean setDeluxeRoomTax(double deluxeRoomTax) {
        if (deluxeRoomTax < 0) {
            return false;
        }
        PromotionDetails.deluxeRoomTax = deluxeRoomTax;
        return true;
    }

    public static boolean setVipSuiteTax(double vipSuiteTax) {
        if (vipSuiteTax < 0) {
            return false;
        }
        PromotionDetails.vipSuiteTax = vipSuiteTax;
        return true;
    }

    public static boolean setSingleRoomPrice(double singleRoomPrice) {
        if (singleRoomPrice <= 0) {
            return false;
        }
        PromotionDetails.singleRoomPrice = singleRoomPrice;
        return true;
    }

    public static boolean setDoubleRoomPrice(double doubleRoomPrice) {
        if (doubleRoomPrice <= 0) {
            return false;
        }
        PromotionDetails.doubleRoomPrice = doubleRoomPrice;
        return true;
    }

    public static boolean setDeluxeRoomPrice(double deluxeRoomPrice) {
        if (deluxeRoomPrice <= 0) {
            return false;
        }
        PromotionDetails.deluxeRoomPrice = deluxeRoomPrice;
        return true;
    }

    public static boolean setVipSuitePrice(double vipSuitePrice) {
        if (vipSuitePrice <= 0) {
            return false;
        }
        PromotionDetails.vipSuitePrice = vipSuitePrice;
        return true;
    }


    public static double getDiscountRate() {
        return discountRate;
    }

    public static double getSingleRoomTax() {
        return singleRoomTax;
    }

    public static double getDoubleRoomTax() {
        return doubleRoomTax;
    }

    public static double getDeluxeRoomTax() {
        return deluxeRoomTax;
    }

    public static double getVipSuiteTax() {
        return vipSuiteTax;
    }

    public static double getSingleRoomPrice() {
        return singleRoomPrice;
    }

    public static double getDoubleRoomPrice() {
        return doubleRoomPrice;
    }

    public static double getDeluxeRoomPrice() {
        return deluxeRoomPrice;
    }

    public static double getVipSuitePrice() {
        return vipSuitePrice;
    }
}
