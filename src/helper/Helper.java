package src.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Helper {
    public static Scanner sc;

    public Helper() {
        sc = new Scanner(System.in);
    }

    public static int readInt() {
        int userInput = -1;
        userInput = sc.nextInt();
        sc.nextLine(); // Consume newline left-over
        return userInput;
    }

    public static double readDouble() {
        double userInput = -1;
        userInput = sc.nextDouble();
        sc.nextLine(); // Consume newline left-over
        return userInput;
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

    public static String setDate(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm");
        String date;
        System.out.println("Please enter the date in this format: 'yyyy-MM-DD HH:mm'");
        date = sc.nextLine();
        try{
            LocalDateTime Date = LocalDateTime.parse(date);
            date = Date.format(format);
            if(validateDate(date))
                return date;
            else{
                System.out.println("Invalid Date");
                return null;
            }
        }
        catch(DateTimeParseException e){
            System.out.println("Invalid Date format");
            return null;
        }
    } 
    public static LocalDateTime getDate(String date){
        return LocalDateTime.parse(date);
    }
    public static String getTimeNow(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm");
        LocalDateTime date = LocalDateTime.now();
        return date.format(format);
    }
    public static boolean validateDate(String date){
        LocalDateTime Date = getDate(date);
        LocalDateTime now = LocalDateTime.now();
    
        return (Date.compareTo(now)>=0?true:false);
    }
    public boolean LocalDateTimediff(String date){
        LocalDateTime from = getDate(date);
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime fromTemp = LocalDateTime.from(from);
            
        long hours = fromTemp.until(to, ChronoUnit.HOURS);
        fromTemp = fromTemp.plusHours(hours);
    
        long minutes = fromTemp.until(to, ChronoUnit.MINUTES);
        fromTemp = fromTemp.plusMinutes(minutes);
    
        if(hours>1)
            return true;
        else
            return false;
    }
}
