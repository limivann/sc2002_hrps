package src.helper;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;

public class Helper {
    public static Scanner sc;

    public Helper() {
        sc = new Scanner(System.in);
    }

    public static int readInt() {
        while (true){
            try{
                int userInput = -1;
                userInput = sc.nextInt();
                sc.nextLine(); // Consume newline left-over
                return userInput;
            } catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Invalid Input, Enter an integer!");
            }
        }
        
    }

    public static int readInt(int min, int max){
        while (true){
            try{
                int userInput = -1;
                userInput = sc.nextInt();
                sc.nextLine(); // Consume newline left-over
                if (userInput < min || userInput > max){
                    throw new OutOfRange();
                }
                else{
                    return userInput;
                }
            } catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Invalid Input, Enter an integer!");
            } catch(OutOfRange e){
                System.out.println("Input is out of allowed range");
            }
        }
    }

    public static double readDouble() {
        while(true){
            try{
                double userInput = -1;
                userInput = sc.nextDouble();
                sc.nextLine(); // Consume newline left-over
                return userInput;
            } catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Invalid Input, Enter an double!!");
            }
        }
    }

    public static <K, V> HashMap<K, V> copyHashMap(HashMap<K, V> original)
    {
        return new HashMap<>(original);
    }
    
    public static boolean promptConfirmation(String message) {
        System.out.println(String.format("Are you sure you want to %s? (yes/no)", message));
        String userInput = sc.nextLine();
        return userInput.equals("yes");
    }

    public static <K, V> int generateUniqueId(HashMap<K, V> database) {
        if (database.size() == 0) {
            return 1;
        }
        HashMap<K, V> toIterate = Helper.copyHashMap(database);
        String currentMax = "";
        Iterator it = toIterate.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Object keyObject = pair.getKey();
            if (keyObject instanceof String) {
                String currentKey = (String) keyObject;
                if (currentKey.compareTo(currentMax) > 0) {
                    currentMax = currentKey;
                }
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        String maxId = currentMax.substring(1);
        return Integer.parseInt(maxId) + 1;
    }
}
