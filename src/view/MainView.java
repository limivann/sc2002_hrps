package src.view;

import java.util.Scanner;

import javax.swing.text.Style;

import src.helper.Helper;
public abstract class MainView {
    public abstract void printMenu();
    public abstract void viewapp();
    
    public Helper helper;

    public MainView() {
        helper = new Helper();
    }
}
