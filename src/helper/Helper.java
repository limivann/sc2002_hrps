package src.helper;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import src.controller.ReservationManager;
import src.database.Database;
import src.model.Reservation;

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

    public static String setDate(boolean now){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (now) {
            return getTimeNow();  
        }
        System.out.println("Please enter the date in this format: 'yyyy-MM-dd HH:mm'");
        String date = sc.nextLine();
        try {
            LocalDateTime Date = LocalDateTime.parse(date, format);
            date = format.format(Date);
            if(validateDate(date, format))
                return date;
            else{
                System.out.println("Invalid Date");
            }
        }
        catch (DateTimeParseException e) {
            System.out.println("Invalid Date format");
        }
        return "";
    } 
    public static LocalDateTime getDate(String date, DateTimeFormatter format){
        return LocalDateTime.parse(date, format);
    }
    public static String getTimeNow(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.now();
        return date.format(format);
    }

    public static boolean validateDate(String date, DateTimeFormatter format) {
        LocalDateTime Date = getDate(date, format);
        LocalDateTime now = LocalDateTime.now();
    
        return (Date.compareTo(now)>=0?true:false);
    }

    public static boolean LocalDateTimediff(String date, DateTimeFormatter format) {
        LocalDateTime from = getDate(date, format);
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime fromTemp = LocalDateTime.from(from);

        long hours = fromTemp.until(to, ChronoUnit.HOURS);
        fromTemp = fromTemp.plusHours(hours);

        long minutes = fromTemp.until(to, ChronoUnit.MINUTES);
        fromTemp = fromTemp.plusMinutes(minutes);

        if (hours > 1)
            return true;
        else
            return false;
    }
    
    public static long calculateDayDiff(String checkInDate, String checkOutDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime from = getDate(checkInDate, format);
        LocalDateTime to = getDate(checkInDate, format);
        long daysBetween = ChronoUnit.DAYS.between(from, to);
        return daysBetween + 1;
    }

    public static void checkReservationStatus() {
        for (Reservation reservation : Database.RESERVATIONS.values()) {
            String date = reservation.getCheckedInDate();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            if (!LocalDateTimediff(date, format)) {
                ReservationManager.updateIsExpired(reservation.getReservationId(), false);
            }
        }
    }

    public static void main(String[] args) {
        Helper helper = new Helper();
        System.out.println(calculateDayDiff("2022-03-25 16:12", "2022-03-25 22:12"));
    }
}
