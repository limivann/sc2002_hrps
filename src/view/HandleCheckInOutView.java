package src.view;

import java.lang.Thread;

import javax.naming.spi.ResolveResult;

import src.controller.PaymentManager;
import src.controller.ReservationManager;
import src.helper.Helper;

public class HandleCheckInOutView extends MainView {
    public HandleCheckInOutView() {
        super();
    }
    @Override
    public void printMenu() {
        System.out.println("=== Handle Check In / Check Out View === ");
        System.out.println("Please select an option (1-3)");
        System.out.println("1. Check In Room");
        System.out.println("2. Check Out Room");
        System.out.println("3. Exit Handle Check In / Check Out View");
    }

    @Override
    public void viewapp() {  
        int opt = -1;
        do{
            printMenu();
            opt = Helper.readInt(1,3);
            switch (opt) {
                case 1:
                    checkin();
                    break;
                case 2:
                    checkout();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice ... Please try again");
                    break;
            }
        } while (opt != 3);
        
    }
    
    public void checkin() {
        Helper.checkReservationStatus();
        System.out.println("--- Check In Room---");
        System.out.println("Please enter reservation id (RXXXX)");
        String reservationId = Helper.sc.nextLine();
        if (!ReservationManager.validateReservationId(reservationId)) {
            return;
        }
        ReservationManager.checkInReservation(reservationId);
        System.out.println(String.format("Check in complete for reservation id: %s", reservationId));
    }

    public void checkout() {
        System.out.println("--- Check Out Room---");
        System.out.println("Please enter reservation id (RXXXX)");
        String reservationId = Helper.sc.nextLine();
        if (!ReservationManager.validateReservationId(reservationId)) {
            return;
        }
        if (ReservationManager.checkOutReservation(reservationId)) {
            System.out.println(String.format("Check out complete for reservation id: %s", reservationId));
        } else {
            return;
        }
        PaymentManager.handlePayment(reservationId);
        // int paymentOpt = promptPayment();
        // try{
        //     handlePayment(paymentOpt, roomId);
        //     printInvoice(roomId); 
        // } catch (InterruptedException err) {
        //     System.out.println("Error: " + err.getMessage());
        // }
    }
    
    public int promptPayment() {
        System.out.println("Please select a payment method (1-2)");
        System.out.println("(1) Cash");
        System.out.println("(2) Credit Card");
        int opt = Helper.sc.nextInt();
        return opt;
    }

    public void handlePayment(int paymentOpt, String roomId) throws InterruptedException {
        // assume payment is successful all the time
        String paymentOptStr = paymentOpt == 1 ? "Cash" : "Credit Card";
        System.out.println("You have chosen to pay by " + paymentOptStr);
        // TODO: Call PaymentManager to calculate the total payment
        System.out.println("Total amount to pay: $" + 1000);
        Thread.sleep(3000); 
        System.out.println("Payment sucessful!");
    }
    
    public void printInvoice(String roomId) {
        // Print Invoice
        
    }
}
