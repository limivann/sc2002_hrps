package src.view;

import java.util.Scanner;
import java.lang.Thread;
import src.helper.Helper;

public class HandleCheckInOutView extends MainView {
    private Helper helper;
    public HandleCheckInOutView() {
        helper = new Helper();
    }
    @Override
    public void printMenu() {
        System.out.println("===================");
        System.out.println("1. Check In");
        System.out.println("2. Check Out");
    }

    @Override
    public void viewapp() {
        int opt = -1;
        printMenu();
        opt = Helper.sc.nextInt();
        switch (opt) {
            case 1:
                checkin();
                break;
            case 2:
                checkout();
                break;
            default:
                break;
        }
    }
    
    public void checkin() {
        System.out.println("Please enter reservation id: ");
        String reservationId = Helper.sc.nextLine();
        // TODO: Call ReservationManager to handle check in
        System.out.println(String.format("Check in complete for reservation id: %s", reservationId));
    }

    public void checkout() {
        System.out.println("Please enter room id: ");
        String roomId = Helper.sc.nextLine();
        System.out.println(String.format("Check out complete for room id: %s", roomId));
        int paymentOpt = promptPayment();
        try{
            handlePayment(paymentOpt, roomId);
            printInvoice(roomId); 
        } catch (InterruptedException err) {
            System.out.println("Error: " + err.getMessage());
        }
    }
    
    public int promptPayment() {
        System.out.println("Please select a payment method");
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
