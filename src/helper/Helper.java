package src.helper;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Helper {
    public static Scanner sc;

    public Helper(){
        sc = new Scanner(System.in);
    }

    public void setDate(String date){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm");
        String temp_date;
        System.out.println("Please enter the date in this format: 'yyyy-MM-DD HH:mm'");
        temp_date = sc.nextLine();
        try{
            LocalDateTime Date = LocalDateTime.parse(temp_date);
            date = Date.format(format);
        }
        catch(DateTimeParseException e){
            System.out.println("Invalid Date format");
            return;
        }
      
    }
    public LocalDateTime getDate(String date){
        return LocalDateTime.parse(date);
    }

    public boolean validateDate(String date){
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
