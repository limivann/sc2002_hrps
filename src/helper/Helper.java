package src.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Helper {
    public static Scanner sc;

    public Helper() {
        sc = new Scanner(System.in);
    }

    public static int readInt() {
        int userInput = sc.nextInt();
        sc.nextLine(); // Consume newline left-over
        return userInput;
    }

    public static double readDouble() {
        double userInput = sc.nextDouble();
        sc.nextLine(); // Consume newline left-over
        return userInput;
    }

    public static <K, V> HashMap<K, V> copyHashMap(HashMap<K, V> original)
    {
        return new HashMap<>(original);
    }
    
}
