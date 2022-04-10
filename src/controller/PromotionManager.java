package src.controller;

import src.database.Database;
import src.database.FileType;
import src.helper.Helper;
import src.model.PromotionDetails;

// for javadocs
import src.view.HotelAppView;
import src.view.ManagePaymentView;
import src.model.Invoice;
/**
 * PromotionManager is a controller class that acts as a "middleman" 
 * between the view classess - {@link HotelAppView} and {@link ManagePaymentView} and the model classes - {@link Invoice} and {@link PromotionDetails}. <p>
 * 
 * It can set tax rate and discount rate.
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
     * A method that returns tax rate of the hotel
     * @return tax rate of the hotel
     */
    public static double getTaxRate() {
        return Database.PRICES.getTaxRate();
    }
    
    /**
     * A method that returns discount rate of the hotel
     * @param checkInDate Date of check in
     * @return Discount rate of the hotel
     */
    public static double getDiscountRate(String checkInDate) {
        if (checkInDate == null) {
            return Database.PRICES.getDiscountRate();
        }
        if (Helper.checkIsDateWeekend(checkInDate)) {
            // no discount on weekend
            return 0;
        }
        else {
            return Database.PRICES.getDiscountRate();
        }
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
        System.out.println("Discount Rate must be positive");
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
        System.out.println("Discount Rate must be positive and less than 1");
        return false;
    }
    /**
     * A method that initializes the promotion details of the hotel
     * @return {@code true} if edited successfully.
     */
    public static boolean initializePromotionDetails() {
        PromotionDetails promotionDetails = new PromotionDetails(0.10, 0.17);
        Database.PRICES = promotionDetails;
        return true;
    }
}
