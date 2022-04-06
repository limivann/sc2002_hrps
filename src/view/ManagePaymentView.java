package src.view;

import src.helper.Helper;
import src.model.enums.RoomType;
import src.controller.PromotionManager;
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
        System.out.println("Please select an option (1-4)");
        System.out.println("(1) Manage Room Price");
        System.out.println("(2) Manage Tax Rate");
        System.out.println("(3) Manage Discount Rate");
        System.out.println("(4) Exit Payment View");
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
            opt = Helper.readInt(1, 4);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Payment View > Manage Room Price");
                    if (promptEditRoomPrice()) {
                        System.out.println("Edit Room Price Successful!");
                    } else {
                        System.out.println("Edit Room Price Unsuccessful!");
                    }
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Payment View > Manage Tax Rate");
                    if (promptEditTaxRate()) {
                        System.out.println("Edit Tax Rate Successful!");
                    } else {
                        System.out.println("Edit Tax Rate Unsuccessful!");
                    }
                    break;
                case 3:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Payment View > Manage Discount Rate");
                    if (promptEditDiscountRate()) {
                        System.out.println("Edit Discount Rate Successful!");
                    } else {
                        System.out.println("Edit Discount Rate Unsuccessful!");
                    }
                    break;
                case 4:
                    break;
            }
            if (opt != 4) {
                Helper.pressAnyKeyToContinue();
            }
        } while (opt != 4);

    }
    /**
     * View Menu for editing room price.
     * @return {@code true} if updates successfully. Otherwise, {@code false}.
     */
    public boolean promptEditRoomPrice() {
        int opt = -1;
        printEditRoomPriceMenu();
        opt = Helper.readInt(1, 4);
        double newPrice = -1;
        switch (opt) {
            case 1:
                printOldPrice(RoomType.SINGLE);
                newPrice = promptNewPrice(RoomType.SINGLE);
                return PromotionManager.editRoomPrice(RoomType.SINGLE, newPrice);
            case 2:
                printOldPrice(RoomType.DOUBLE);
                newPrice = promptNewPrice(RoomType.DOUBLE);
                return PromotionManager.editRoomPrice(RoomType.DOUBLE, newPrice);
            case 3:
                printOldPrice(RoomType.DELUXE);
                newPrice = promptNewPrice(RoomType.DELUXE);
                return PromotionManager.editRoomPrice(RoomType.DELUXE, newPrice);
            case 4:
                printOldPrice(RoomType.VIP_SUITE);
                newPrice = promptNewPrice(RoomType.VIP_SUITE);
                return PromotionManager.editRoomPrice(RoomType.VIP_SUITE, newPrice);
        }
        return false;
    }
    /**
     * View Menu for editing tax rate.
     * @return {@code true} if updates successfully. Otherwise, {@code false}.
     */
    public boolean promptEditTaxRate() {
        printOldTaxRate();
        double newTaxRate = -1;
        System.out.println("Please enter a new tax rate (0-1)");
        newTaxRate = Helper.readDouble();
        return PromotionManager.editTaxRate(newTaxRate);
    }
    /**
     * View Menu for editing discount rate.
     * @return {@code true} if updates successfully. Otherwise, {@code false}.
     */
    public boolean promptEditDiscountRate() {
        printOldDiscountRate();
        double newDiscountRate = -1;
        System.out.println("Please enter a new discount rate (0-1)");
        newDiscountRate = Helper.readDouble();
        return PromotionManager.editDiscountRate(newDiscountRate);
    }
    /**
     * View Menu for updating new room price of a certain type of room.
     * @param roomType The type of the room <p>
     * See {@link RoomType} for different types of room.
     * @return the new price of the room.
     */
    public double promptNewPrice(RoomType roomType) {
        System.out.println("Please enter a new price for " + roomType.roomTypeAsStr);
        double newPrice = -1;
        newPrice = Helper.readDouble();
        return newPrice;
    }
    /**
     * View Menu for updating new tax rate of a certain type of room.
     * @param roomType The type of the room <p>
     * See {@link RoomType} for different types of room.
     * @return the new tax rate of the room.
     */
    public double promptNewTaxRate(RoomType roomType) {
        System.out.println("Please enter a new tax rate for " + roomType.roomTypeAsStr);
        double newTaxRate = -1;
        newTaxRate = Helper.readDouble();
        return newTaxRate;
    }
    
    /**
     * Prints the old room price of a certain type of room.
     * @param roomType the type of the room <p>
     * See {@link RoomType} for different types of room.
     */
    public void printOldPrice(RoomType roomType) {
        System.out.println(String.format("The old price of %s is %.2f", roomType.roomTypeAsStr,
                PromotionManager.getRoomPrice(roomType, false)));
    }
    /**
     * Prints the old tax rate.
     */
    public void printOldTaxRate() {
        System.out.println(String.format("The old tax rate is %.2f", PromotionManager.getTaxRate()));
    }
    /**
     * Prints the old discount rate.
     */
    public void printOldDiscountRate() {
        System.out.println(String.format("The old discount rate is %.2f", PromotionManager.getDiscountRate()));
    }
    /**
     * View Menu for choosing the type of room to edit room price. 
     */
    public void printEditRoomPriceMenu() {
        System.out.println("Please select a room to edit its price (1-4)");
        System.out.println("(1) Single Room");
        System.out.println("(2) Double Room");
        System.out.println("(3) Deluxe Room");
        System.out.println("(4) Vip Suite");
    }
}
