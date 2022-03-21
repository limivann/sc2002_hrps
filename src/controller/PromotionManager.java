package src.controller;

import src.model.PromotionDetails;

public class PromotionManager {
    public PromotionManager() {
        // 
    }
    
    public static boolean editDiscountRate(double newDiscountRate) {
        return PromotionDetails.setDiscountRate(newDiscountRate);
    }

    // Taxes
    public static boolean editSingleRoomTax(double newSingleRoomTax) {
        return PromotionDetails.setSingleRoomTax(newSingleRoomTax);
    }

    public static boolean editDoubleRoomTax(double newDoubleRoomTax) {
        return PromotionDetails.setDoubleRoomTax(newDoubleRoomTax);
    }

    public static boolean editDeluxeRoomTax(double newDeluxeRoomTax) {
        return PromotionDetails.setDeluxeRoomTax(newDeluxeRoomTax);
    }

    public static boolean editVipSuiteTax(double newVipSuieTax) {
        return PromotionDetails.setVipSuiteTax(newVipSuieTax);
    }

    // Prices
    public static boolean editSingleRoomPrice(double newSingleRoomPrice) {
        return PromotionDetails.setSingleRoomPrice(newSingleRoomPrice);
    }

    public static boolean editDoubleRoomPrice(double newDoubleRoomPrice) {
        return PromotionDetails.setDoubleRoomPrice(newDoubleRoomPrice);
    }

    public static boolean editDeluxeRoomPrice(double newDeluxeRoomPrice) {
        return PromotionDetails.setDeluxeRoomPrice(newDeluxeRoomPrice);
    }

    public static boolean editVipSuitePrice(double newVipSuiePrice){
        return PromotionDetails.setVipSuitePrice(newVipSuiePrice);
    }   

}
