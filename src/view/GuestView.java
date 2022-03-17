package src.view;

import java.util.Scanner;

public class GuestView {
    public GuestView() {

    }

    public static void run() {
        int opt = printMenu();

    }

    public static int printMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.");
        System.out.println("2.");
        System.out.println("3.");
        System.out.println("4.");
        int opt = sc.nextInt();
        return opt;
    }
}
