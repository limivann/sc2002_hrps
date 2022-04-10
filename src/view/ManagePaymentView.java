package src.view;

import src.helper.Helper;
import src.controller.PromotionManager;

// for javadocs
import src.model.PromotionDetails;

/**
 * ManagePaymentView provides the view to take user input which calls {@link PromotionManager} to manage {@link PromotionDetails}.
 * @author Max
 * @version 1.0
 * @since 2022-04-06
 */
public class ManagePaymentView extends MainView {

    /**
     * Default constructor for the ManagePaymentView.
     */
    public ManagePaymentView() {
        super();
    }
    /**
     * View Menu of the ManagePaymentView.
     */
    @Override
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Payment View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Manage Tax Rate");
        System.out.println("(2) Manage Discount Rate");
        System.out.println("(3) Exit Payment View");
    }
    /**
     * View Application for the ManagePaymentView. <p>
     * see {@link PromotionManager} for more {@link PromotionDetails} management details.
     */
    @Override
    public void viewApp() {
        int opt = -1;
        do {
            printMenu();
            opt = Helper.readInt(1, 3);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Payment View > Manage Tax Rate");
                    if (promptEditTaxRate()) {
                        System.out.println("Edit Tax Rate Successful!");
                    } else {
                        System.out.println("Edit Tax Rate Unsuccessful!");
                    }
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Payment View > Manage Discount Rate");
                    if (promptEditDiscountRate()) {
                        System.out.println("Edit Discount Rate Successful!");
                    } else {
                        System.out.println("Edit Discount Rate Unsuccessful!");
                    }
                    break;
                case 3:
                    break;
            }
            if (opt != 3) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 3);

    }
    
    /**
     * Prompt for editing tax rate.
     * @return {@code true} if updates successfully. Otherwise, {@code false}.
     */
    private boolean promptEditTaxRate() {
        printOldTaxRate();
        double newTaxRate = -1;
        System.out.println("Please enter a new tax rate (> 0)");
        newTaxRate = Helper.readDouble();
        return PromotionManager.editTaxRate(newTaxRate);
    }
    /**
     * Prompt for editing discount rate.
     * @return {@code true} if updates successfully. Otherwise, {@code false}.
     */
    private boolean promptEditDiscountRate() {
        printOldDiscountRate();
        double newDiscountRate = -1;
        System.out.println("Please enter a new discount rate (> 0)");
        newDiscountRate = Helper.readDouble();
        return PromotionManager.editDiscountRate(newDiscountRate);
    }
    
    /**
     * Prints the old tax rate.
     */
    private void printOldTaxRate() {
        System.out.println(String.format("The old tax rate is %.2f", PromotionManager.getTaxRate()));
    }
    /**
     * Prints the old discount rate.
     */
    private void printOldDiscountRate() {
        System.out.println(String.format("The old discount rate is %.2f", PromotionManager.getDiscountRate(null)));
    }
}
