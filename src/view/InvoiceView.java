package src.view;

import src.controller.InvoiceManager;
import src.helper.Helper;

public class InvoiceView extends MainView {
    
    public InvoiceView() {
        super();
    }

    @Override
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Invoice View");
        System.out.println("What do you like to do ?");
        System.out.println("(1) Search Invoice");
        System.out.println("(2) Print All Invoice");
        System.out.println("(3) Exit Invoice View");
    }

    @Override
    public void viewapp() {
        int opt = -1;
        do {
            printMenu();
            opt = Helper.readInt(1, 3);
            switch (opt) {
                case 1:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Invoice View > Search Invoice");
                    promptSearchInvoice();
                    break;
                case 2:
                    Helper.clearScreen();
                    printBreadCrumbs("Hotel App View > Invoice View > Print All Invoice");
                    InvoiceManager.printAllInvoices();
                    break;
                default:
                    break;
            }
            if (opt != 3) {
                Helper.pressAnyKeyToContinue();
            }

        } while (opt != 3);
    }
    
    public void promptSearchInvoice() {
        System.out.println("Enter Invoice Id to search (IXXXX)");
        String invoiceId = Helper.sc.nextLine();
        if (!InvoiceManager.validateInvoiceId(invoiceId)) {
            return;
        }
        InvoiceManager.printInvoice(invoiceId);
    }
}
