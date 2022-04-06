package src.view;
/**
 * MainView is an abstract class which is inherited by every view class.
 * @author Max
 * @version 1.0
 * @since 2022-04-06
 */
import src.helper.Helper;
public abstract class MainView {
    public abstract void printMenu();
    public abstract void viewApp();
    
    public Helper helper;

    public MainView() {
        helper = new Helper();
    }

    protected void printBreadCrumbs(String breadcrumb) {
        String spaces = String.format("%" + (105 - breadcrumb.length()) + "s", "");
        System.out.println(
                "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ " + breadcrumb + spaces + "║");
        System.out.println(
                "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }
    
}


// __    __    _______     _______      ______  
// /  |  /  |  /       \   /       \    /      \ 
// $$ |  $$ |  $$$$$$$  |  $$$$$$$  |  /$$$$$$  |
// $$ |__$$ |  $$ |__$$ |  $$ |__$$ |  $$ \__$$/ 
// $$    $$ |  $$    $$<   $$    $$/   $$      \ 
// $$$$$$$$ |  $$$$$$$  |  $$$$$$$/     $$$$$$  |
// $$ |  $$ |  $$ |  $$ |  $$ |        /  \__$$ |
// $$ |  $$ |  $$ |  $$ |  $$ |        $$    $$/ 
// $$/   $$/   $$/   $$/   $$/          $$$$$$/  
                                        
                                        
                                        
