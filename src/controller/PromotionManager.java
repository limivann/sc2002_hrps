package src.controller;

import src.model.PromotionDetails;

public class PromotionManager {
    public PromotionManager() {
        // 
    }
    
    public static boolean editDiscountRate(double newDiscountRate) {
        return PromotionDetails.setDiscountRate(newDiscountRate);
    }
}
