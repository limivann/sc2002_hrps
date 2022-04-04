package src.controller;

import src.database.Database;
import src.database.FileType;
import src.model.PromotionDetails;
import src.model.enums.RoomType;

// for javadocs
import src.view.AdminView;
import src.view.ManagePaymentView;
/**
 * PromotionManager is a controller class that acts as a "middleman" 
 * between the view classess - {@link AdminView} and {@link ManagePaymentView} and the model classes - {@link Invoice} and {@link PromotionDetails}. <p>
 * 
 * It can set tax rate and discount rate. <p>
 * It can update room prices with the help of {@link RoomManager}.
 * @author Lim Kang Wei, Ivan, Max
 * @version 1.0
 * @since 2022-04-04
 */
public class PromotionManager {
    /**
     * Default constructor of the PromotionManager
     */
    public PromotionManager() {

    }
    
    /**
     * A method that calculates the room's price by retrieving {@link PromotionDetails} object from the {@link Database} <p>
     * See {@link RoomType} for the type of the room
     * @param roomType the room type
     * @param isWifiEnabled whether wifi is enabled or not
     * @return price of the room
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
     * @return {@code true} if edited successfully. Otherwise, {@code false} if editing tax rate is unsuccessful
     */
    public static boolean editTaxRate(double newTaxRate) {
        if (Database.PRICES.setTaxRate(newTaxRate)) {
            Database.saveFileIntoDatabase(FileType.PRICES);
            return true;
        }
        return false;
    }
    
    /**
     * A method that edit the price of a room type <p>
     * Calls {@link RoomManager} to update room price of every single room that is affected.
     * @param roomType room type of the room to be update
     * @param newRoomPrice new price for the room
     * @return {@code true} if edited successfully. Otherwise, {@code false} if editing room price is unsuccessful
     */
    public static boolean editRoomPrice(RoomType roomType, double newRoomPrice) {
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
        Database.saveFileIntoDatabase(FileType.PRICES);
        // Cant find
        return false;
    }

    /**
     * A method that edit the discount rate of the hotel
     * @param newDiscountRate new discount rate of the hotel
     * @return {@code true} if edited successfully. Otherwise, {@code false} if editing discount rate is unsuccessful
     */
    public static boolean editDiscountRate(double newDiscountRate) {
        if (Database.PRICES.setDiscountRate(newDiscountRate)) {
            Database.saveFileIntoDatabase(FileType.PRICES);
            return true;
        }
        return false;
    }
    /**
     * A method that initializes the promotion details of the hotel
     * @return {@code true} if edited successfully.
     */
    public static boolean initializePromotionDetails() {
        PromotionDetails promotionDetails = new PromotionDetails(0.05, 0.17, 200, 360, 400, 1000, 1.2);
        Database.PRICES = promotionDetails;
        return true;
    }
}
