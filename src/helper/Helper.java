package src.helper;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
/**
 * Helper class to provide support functions for other classes
 * @author Ivan, Max
 * @version 1.0
 * @since 2022-04-04
 */
public class Helper {
    /**
     * Scanner object for taking user input
     */
    public static final Scanner sc = new Scanner(System.in);
    /**
     * Default constructor for initializing Scanner object
     */
    public Helper() {

    }

    /**
     * Function to read an integer value from terminal <p>
     * 
     * Repeatedly tries to read an integer until an integer is actually being read. Keeps catching the exception {@link InputMismatchException} when invalid characters are entered
     * @return The read integer entered in the terminal.
     */
    public static int readInt() {
        while (true) {
            try {
                int userInput = -1;
                userInput = sc.nextInt();
                sc.nextLine(); // Consume newline left-over
                return userInput;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid Input, Enter an integer!");
            }
        }
    }

    /**
     * Function to read an integer value from terminal that within the specified minimum and maximum arguments. <p>
     * 
     * Repeatedly tries to read an integer until an integer within the specified range is actually being read. <p>
     * Keeps catching the exception {@link InputMismatchException} when invalid characters are entered. <p>
     * Keeps catching the exception {@link OutOfRange} when an integer entered is lesser than the minimum or greater 
     * than the maximum value specified as arguments.
     * 
     * @param min minimum valid value that will be read and returned.
     * @param max maximum valid value that will be read and returned.
     * @return The read integer entered in the terminal.
     */
    public static int readInt(int min, int max) {
       
        while (true){
            try{
                int userInput = -1;
                userInput = sc.nextInt();
                sc.nextLine(); // Consume newline left-over
                if (userInput < min || userInput > max) {
                    throw new OutOfRange();
                }
                else {
                    return userInput;
                }
            } catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Invalid Input, Enter an integer!");
            } catch (OutOfRange e) {
                System.out.println("Input is out of allowed range");
            }
        }
    }

    /**
     * Function to read a double value from terminal. <p>
     * 
     * Repeatedly tries to read a double until a double is actually being read. Keeps catching the exception {@link InputMismatchException} when invalid characters are entered
     * @return returns the read double entered in the terminal.
     */
    public static double readDouble() {
       
        while (true) {
            try {
                double userInput = -1;
                userInput = sc.nextDouble();
                sc.nextLine(); // Consume newline left-over
                return userInput;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid Input, Enter an double!!");
            }
        }
    }

    /**
     * Reads a new line of string 
     * @return user input as string
     */
    public static String readString() {
       
        String userInput = sc.nextLine();
        return userInput;
    }
    
    /**
     * Method to prompt confirmation from the user. Usually for confirmation of removing data
     * @param message Message for confirmation prompt. 
     * @return {@code true} if user input 'yes'. Otherwise, {@code false}.
     */
    public static boolean promptConfirmation(String message) {
       
        System.out.println(String.format("Are you sure you want to %s? (yes/no)", message));
        String userInput = sc.nextLine();
        return userInput.equals("yes");
    }

    /**
     * Method to generate unique id for hashMap key
     * @param <K> Generic type for the key of the HashMap
     * @param <V> Generic type for the value of the HashMap
     * @param database Hashmap object to reference
     * @return A unique id for the database
     */
    public static <K, V> int generateUniqueId(HashMap<K, V> database) {
        if (database.size() == 0) {
            return 1;
        }
        String currentMax = "";
        for (K key : database.keySet()) {
            if (key instanceof String) {
                String currentKey = (String) key;
                if (currentKey.compareTo(currentMax) > 0) {
                    currentMax = currentKey;
                }
            }
        }
        String maxId = currentMax.substring(1);
        return Integer.parseInt(maxId) + 1;
    }

    /**
     * Method to set the date for either current date or user input date
     * @param now {@code true} to return the current time. Otherwise, {@code false} to prompt user for new time.
     * @return String object for the date in the format "yyyy-MM-dd HH:mm"
     */
    public static String setDate(boolean now) {
       
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (now) {
            return getTimeNow();
        }
        System.out.println("Please enter the date in this format: 'yyyy-MM-dd HH:mm'");
        String date = sc.nextLine();
        try {
            LocalDateTime Date = LocalDateTime.parse(date, format);
            date = format.format(Date);
            if (validateDate(date, format)) {
                return date;

            } else {
                System.out.println("Invalid Date");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date format");
        }
        return "";
    }

    /**
     * Method to parse a string date in a format
     * @param date Date in string
     * @param format {@link DateTimeFormatter} object for formatting of dates
     * @return {@link LocalDateTime} object after parsing the string date with the formatter
     */
    public static LocalDateTime getDate(String date, DateTimeFormatter format) {
        return LocalDateTime.parse(date, format);
    }
    
    /**
     * Method to get current date and time
     * @return String object for the date in the format "yyyy-MM-dd HH:mm"
     */
    public static String getTimeNow() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.now();
        return date.format(format);
    }

    /**
     * Method to validate date
     * @param date Date in string
     * @param format {@link DateTimeFormatter} object for formatting of dates
     * @return {@code true} if date is valid. Otherwise, {@code false} if the date is invalid (date is in the past)
     */
    public static boolean validateDate(String date, DateTimeFormatter format) {
        LocalDateTime Date = getDate(date, format);
        LocalDateTime now = LocalDateTime.now();

        return (Date.compareTo(now) >= 0 ? true : false);
    }

    /**
     * Method to check if the time difference of the input date and current time exceeds 1 hour (Hotel check in / check out checking)
     * @param date Date in string
     * @return {@code true} if the date does not exceed 1 hour. Otherwise, {@code false}.
     */
    public static boolean LocalDateTimediff(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
    
    /**
     * Method to calculate the days elapsed between two dates.
     * @param fromDate From date in string.
     * @param toDate To date in string. 
     * @return Days difference of the two dates. 
     */
    public static long calculateDaysElapsed(String fromDate, String toDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime from = getDate(fromDate, format);
        LocalDateTime to = getDate(toDate, format);
        long daysBetween = from.until(to, ChronoUnit.DAYS);
        return daysBetween + 1;
    }

    /**
     * Method to check if fromDate is earlier than toDate
     * @param fromDate From date in string.
     * @param toDate To date in string. 
     * @return {@code true} if the fromDate is earlier than toDate. Otherwise, {@code false}
     */
    public static boolean validateTwoDates(String fromDate, String toDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime from = getDate(fromDate, format);
        LocalDateTime to = getDate(toDate, format);
        return (to.compareTo(from) >= 0 ? true : false);
    }

    /**
     * Method to check if the date is weekend
     * @param dateToCheck Date to check in String
     * @return {@code true} if the date to check is weekend. Otherwise, {@code false}.
     */
    public static boolean checkIsDateWeekend(String dateToCheck) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = getDate(dateToCheck, format);
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return true;
        }
        return false;
    }

    /**
     * Method to pause the application and prompt user to press the ENTER key to continue using the app.
     */
    public static void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    /**
     * Method to clear the screen of the terminal for user experience and neat interface.
     */
    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception err) {

        }
    }
}
