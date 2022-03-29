package src.controller;

import src.database.Database;
import src.database.FileType;
import src.model.PromotionDetails;
import src.model.Room;
import src.model.enums.RoomType;
/**
 * The Class that interact with the rates of the hotel.
 * @author Lim Kang Wei
 * @version 1.0
 * @since 2020-03-29
 */
public class PromotionManager {
    public PromotionManager() {

    }
    /**
     * A method that calculates the room's price with the wifi-enabled multiplier
     * @param roomType the room type
     * @param isWifiEnabled whether wifi is enabled or not
     * @return price of the room
     * @see RoomType RoomType - type of the room
     */
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

    // GETTERS
    /**
     * A method that returns tax rate of the hotel
     * @return tax rate of the hotel
     */
    public static double getTaxRate() {
        return Database.PRICES.getTaxRate();
    }
    /**
     * A method that returns discount rate of the hotel
     * @return discount rate of the hotel
     */
    public static double getDiscountRate() {
        return Database.PRICES.getDiscountRate();
    }
    
    // SETTERS
    /**
     * A method that edit the tax rate of the hotel
     * @param newTaxRate new tax rate of the hotel
     * @return true if edited successfully
     */
    public static boolean editTaxRate(double newTaxRate) {
        return Database.PRICES.setTaxRate(newTaxRate);
    }
    /**
     * A method that edit the price of a room type
     * @param roomType room type
     * @param newRoomPrice new price for the room type
     * @return true if edited successfully
     */
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

    /**
     * A method that edit the discount rate of the hotel
     * @param newDiscountRate new discount rate of the hotel
     * @return true if edited successfully
     */
    public static boolean editDiscountRate(double newDiscountRate) {
        return Database.PRICES.setDiscountRate(newDiscountRate);
    }
    /**
     * A method that initializes the promotion details of the hotel
     * @return true if edited successfully
     */
    public static boolean initializePromotionDetails() {
        PromotionDetails promotionDetails = new PromotionDetails(0.05, 0.17, 200, 360, 400, 1000, 1.2);
        Database.PRICES = promotionDetails;
        return true;
    }
}
