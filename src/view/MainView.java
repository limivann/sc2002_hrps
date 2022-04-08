package src.view;
/**
 * MainView is an abstract class which is inherited by every view class.
 * @author Max
 * @version 1.0
 * @since 2022-04-06
 */


/**
 * Abstract class for view classes
 */
public abstract class MainView {
    /**
     * Abstract method for view menu
     */
    protected abstract void printMenu();

    /**
     * Abstract method for view app
     */
    public abstract void viewApp();

    /**
     * Default constructor for main view
     */
    public MainView() {
  
    }

    /**
     * Method to print breadcrumbs for navigation purposes
     * @param breadcrumb breadcumbs description
     */
    protected void printBreadCrumbs(String breadcrumb) {
        String spaces = String.format("%" + (105 - breadcrumb.length()) + "s", "");
        System.out.println(
                "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ " + breadcrumb + spaces + "║");
        System.out.println(
                "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }
    
}