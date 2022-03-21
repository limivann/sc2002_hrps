package src.model;

public class PromotionDetails {
    private static double discountRate;
    // taxrate is at rooms
    public PromotionDetails() {
        this.discountRate = 0.3;
    }

    public static boolean setDiscountRate(double discountRate) {
        if (discountRate < 0 || discountRate > 1) {
            return false;
        }
        PromotionDetails.discountRate = discountRate;
        return true;
    }
    public static double getDiscountRate() {
        return discountRate;
    }
}
