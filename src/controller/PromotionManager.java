package src.controller;

import src.model.PromotionDetails;
import src.model.enums.RoomType;

public class PromotionManager {
    public PromotionManager() {
        // 
    }
    
    public static double getRoomPrice(RoomType roomType) {
        switch (roomType) {
            case SINGLE:
                return PromotionDetails.getSingleRoomPrice();
            case DOUBLE:
                return PromotionDetails.getDoubleRoomPrice();
            case DELUXE:
                return PromotionDetails.getDeluxeRoomPrice();
            case VIP_SUITE:
                return PromotionDetails.getVipSuitePrice();
        }
        // Cant find
        return -1;
    }

    public static double getRoomTaxRate(RoomType roomType) {
        switch (roomType) {
            case SINGLE:
                return PromotionDetails.getSingleRoomTax();
            case DOUBLE:
                return PromotionDetails.getDoubleRoomTax();
            case DELUXE:
                return PromotionDetails.getDeluxeRoomTax();
            case VIP_SUITE:
                return PromotionDetails.getVipSuiteTax();
        }
        // Cant find
        return -1;
    }

    public static double getDiscountRate() {
        return PromotionDetails.getDiscountRate();
    }
    
    // EDITORS
    public static boolean editTaxRate(RoomType roomType, double newTaxRate) {
        switch (roomType) {
            case SINGLE:
                return PromotionDetails.setSingleRoomTax(newTaxRate);
            case DOUBLE:
                return PromotionDetails.setDoubleRoomTax(newTaxRate);
            case DELUXE:
                return PromotionDetails.setDeluxeRoomTax(newTaxRate);
            case VIP_SUITE:
                return PromotionDetails.setVipSuiteTax(newTaxRate);
        }
        // Cant find
        return false;
    }

    public static boolean editRoomPrice(RoomType roomType, double newRoomPrice) {
        switch (roomType) {
            case SINGLE:
                return PromotionDetails.setSingleRoomPrice(newRoomPrice);
            case DOUBLE:
                return PromotionDetails.setDoubleRoomPrice(newRoomPrice);
            case DELUXE:
                return PromotionDetails.setDeluxeRoomPrice(newRoomPrice);
            case VIP_SUITE:
                return PromotionDetails.setVipSuitePrice(newRoomPrice);
        }
        // Cant find
        return false;
    }


    public static boolean editDiscountRate(double newDiscountRate) {
        return PromotionDetails.setDiscountRate(newDiscountRate);
    }

}
