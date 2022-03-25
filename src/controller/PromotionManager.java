package src.controller;

import src.database.Database;
import src.database.FileType;
import src.model.PromotionDetails;
import src.model.Room;
import src.model.enums.RoomType;

public class PromotionManager {
    public PromotionManager() {

    }
    
    public static double getRoomPrice(RoomType roomType, boolean isWifiEnabled) {
        switch (roomType) {
            case SINGLE:
                return Database.PRICES.getSingleRoomPrice() * (isWifiEnabled ? Database.PRICES.getWifiEnabledMultiplier() : 1);
            case DOUBLE:
                return Database.PRICES.getDoubleRoomPrice() * (isWifiEnabled ? Database.PRICES.getWifiEnabledMultiplier() : 1);
            case DELUXE:
                return Database.PRICES.getDeluxeRoomPrice() * (isWifiEnabled ? Database.PRICES.getWifiEnabledMultiplier() : 1);
            case VIP_SUITE:
                return Database.PRICES.getVipSuitePrice() * (isWifiEnabled ? Database.PRICES.getWifiEnabledMultiplier() : 1);
        }
        // Cant find
        
        return -1;
    }

    public static double getTaxRate() {
        return Database.PRICES.getTaxRate();
    }

    public static double getDiscountRate() {
        return Database.PRICES.getDiscountRate();
    }
    
    // EDITORS
    public static boolean editTaxRate(double newTaxRate) {
        return Database.PRICES.setTaxRate(newTaxRate);
    }

    public static boolean editRoomPrice(RoomType roomType, double newRoomPrice) {
        // TODO: Change all room price not just the promotion details
        switch (roomType) {
            case SINGLE:
                if (Database.PRICES.setSingleRoomPrice(newRoomPrice)) {
                    return RoomManager.updateRoomPrice(RoomType.SINGLE, newRoomPrice);
                }
                break;
            case DOUBLE:
                if (Database.PRICES.setDoubleRoomPrice(newRoomPrice)) {
                    return RoomManager.updateRoomPrice(RoomType.DOUBLE, newRoomPrice);
                }
                break;
            case DELUXE:
                if (Database.PRICES.setDeluxeRoomPrice(newRoomPrice)) {
                    return RoomManager.updateRoomPrice(RoomType.DELUXE, newRoomPrice);
                }
                break;
            case VIP_SUITE:
                if (Database.PRICES.setVipSuitePrice(newRoomPrice)) {
                    return RoomManager.updateRoomPrice(RoomType.VIP_SUITE, newRoomPrice);
                }
                break;
        }
        // Cant find
        return false;
    }


    public static boolean editDiscountRate(double newDiscountRate) {
        return Database.PRICES.setDiscountRate(newDiscountRate);
    }

    public static boolean initializePromotionDetails() {
        PromotionDetails promotionDetails = new PromotionDetails(0.05, 0.17, 200, 360, 400, 1000, 1.2);
        Database.PRICES = promotionDetails;
        return true;
    }
}
