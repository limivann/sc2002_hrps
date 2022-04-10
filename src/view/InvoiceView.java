package src.view;

import src.controller.InvoiceManager;
import src.helper.Helper;

//  for javadocs
import src.model.Invoice;
/**
 * InvoiceView provides the view to take user input which calls {@link InvoiceManager} to search or print {@link Invoice}.
 * @author Ivan, Max
 * @version 1.0
 * @since 2022-04-07
 */
public class InvoiceView extends MainView {
    /**
     * Default constructor of the InvoiceView.
     */
    public InvoiceView() {
        super();
    }
    /**
     * View Menu of the InvoiceView.
     */
    @Override
    public void printMenu() {
        Helper.clearScreen();
        printBreadCrumbs("Hotel App View > Invoice View");
        System.out.println("What do you like to do ?");
        System.out.println("(1) Search Invoice");
        System.out.println("(2) Print All Invoice");
        System.out.println("(3) Exit Invoice View");
    }
    /**
     * View Application of the InvoiceView. <p>
     * see {@link InvoiceManager} for more {@link Invoice} management details.
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
    /**
     * The function that receives input which searches and prints a {@link Invoice} through {@link InvoiceManager}.
     */
    private void promptSearchInvoice() {
        System.out.println("Enter Invoice Id to search (IXXXX)");
        String invoiceId = Helper.readString();
        if (!InvoiceManager.validateInvoiceId(invoiceId)) {
            return;
        }
        InvoiceManager.printInvoice(invoiceId);
    }
}
