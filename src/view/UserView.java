package src.view;

import src.helper.Helper;

public class UserView extends MainView{
    public UserView() {
        super();
    }
    @Override
    public void printMenu() {
        System.out.println("=== User View ===");
        System.out.println("Enter your choice (1-4)");
        System.out.println("(1) Check in / check out");
        System.out.println("(2) Make Reservation");
        System.out.println("(3) Call Room Service (Order)");
        System.out.println("(4) Exit User View");
    }

    @Override
    public void viewapp() {
        HandleCheckInOutView handleCheckInOutView = new HandleCheckInOutView();
        ReservationView reservationView = new ReservationView();
        OrderView orderView = new OrderView();
        int opt = -1;
        do{
            printMenu();
            opt = Helper.readInt();
            switch (opt) {
                case 1:
                    handleCheckInOutView.viewapp();
                    break;
                case 2:
                    reservationView.viewapp();
                    break;
                case 3:
                    orderView.viewapp();
                    break;
                case 4:
                    break;
                default:
                    // TODO: Throw Exception
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        } while (opt != 4);
        
    }
    
}
