package src.view;

import src.helper.Helper;

public class UserView extends MainView{
    /**
     * @author Lim Kang Wei
     * @version 1.0
     * @since 2022-03-30
     * Default constructor
     */
    public UserView() {
        super();
    }
    @Override
    /**
     * User View Menu
     */
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("User View");
        System.out.println("What would you like to do ?");
        System.out.println("(1) Check in / check out");
        System.out.println("(2) Call Room Service (Order)");
        System.out.println("(3) Exit User View");
    }

    @Override
    /**
     * User view Application Menu
     */
    public void viewapp() {
        HandleCheckInOutView handleCheckInOutView = new HandleCheckInOutView();
        OrderView orderView = new OrderView();
        int opt = -1;
        do{
            printMenu();
            opt = Helper.readInt(1, 3);
            switch (opt) {
                case 1:
                    handleCheckInOutView.viewapp();
                    break;
                case 2:
                    orderView.viewapp();
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        } while (opt != 3);
        
    }
    
}
