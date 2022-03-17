package src;

import java.util.Scanner;
import javax.print.DocFlavor.STRING;
import javax.sound.sampled.ReverbType;
import Reservation.ReservationStatus;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

public class ReservationUtil extends Reservation{
    private ArrayList<Reservation> Reservation;
    private static int count = 0;
    private String reservationDate;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");  
    Scanner sc = new Scanner(System.in);

    public class ReservationView {
        public void Reservation(){
            int option = -1;
            int reservationId;
            System.out.println("1: Create Reservation");
            System.out.println("2: Update Reservation");
            System.out.println("3: Remove Reservation");
            System.out.println("4: Search Reservation");
            System.out.println("5: Exit");
            while(option!=5){
                option = sc.nextInt();
                switch(option){
                    case 1:
                        createReservation();
                        break;
                    case 2:
                        System.out.println("Enter Reservation Id:");
                        reservationId = sc.nextInt();
                        updateReservation(reservationId);
                        break;
                    case 3:
                        System.out.println("Enter Reservation Id:");
                        reservationId = sc.nextInt();
                        removeReservation(reservationId);
                        break;
                    case 4:
                        System.out.println("Enter Reservation Id:");
                        reservationId = sc.nextInt();
                        searchReservation(reservationId);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }
        }  
    }

    private boolean createReservation(){
        Guest guest = createGuest();
        System.out.println("Enter roomId");
        int roomId = sc.nextInt();
        Room room = getRoom(roomId);
        if(room.getRoomStatus()==Room.RoomStatus.OCCUPIED || room.getRoomStatus()==Room.RoomStatus.RESERVED){
            System.out.println("This room is occupied already.");
            return false;
        }
        else if(room.getRoomStatus()==Room.RoomStatus.UNDER_MAINTENANCE){
            System.out.println("This room is under maintenance.");
            return false;
        }
        else if(room.getRoomStatus()==Room.RoomStatus.VACANT){
            System.out.println("CheckedInDate:");
            String checkedInDate = setDate();
            System.out.println("CheckedOutDate:");
            String checkedOutDate = setDate();
            LocalDateTime time = LocalDateTime.now();
            String reservationDate = time.format(dtf);
            System.out.println("NumberOfPax:");
            int NumberOfPax = sc.nextInt();
            count++;
            int guestId = guest.getId();
            room.setRoomStatus(Room.RoomStatus.RESERVED);
            return Reservation.add(new Reservation(roomId, guestId, checkedInDate, checkedOutDate, reservationDate, NumberOfPax, count));
        }
        else return false;
    }
    private boolean updateReservation(int targetId){
        int option = -1;
        Reservation reservation = findReservation(targetId);
        if(reservation){
            System.out.println("1: ReservationStatus");
            System.out.println("2: CheckedInDate");
            System.out.println("3: CheckedOutDate");
            System.out.println("4: NumberOfPax");
            System.out.println("5: Room");
            System.out.println("6: Exit");
            while(option!=6){
                System.out.println("Enter an option");
                option = sc.nextInt();
                switch(option){
                    case 1:
                        System.out.println("1: CONFIRMED");
                        System.out.println("2: IN_WAITLIST");
                        System.out.println("3: CHECKED_IN");
                        System.out.println("4: EXPIRED");
                        System.out.println("5: CHECKED_OUT");
                        System.out.println("6: CANCELLED");
                        int status = sc.nextInt();
                        if(status>=1 && status<=6)
                            reservation.setReservationStatus(ReservationStatus.values()[status]);
                        else
                            System.out.println("Invalid option");
                        break;
                    case 2:
                        String date = setDate();
                        reservation.setCheckedInDate(date);
                        System.out.println("CheckedInDate has been updated");
                        break;
                    case 3:
                        date = setDate();
                        reservation.setCheckedOutDate(date);
                        System.out.println("CheckedOutDate has been updated");
                        break;
                    case 4:
                        System.out.println("Enter NumberOfPax:");
                        int num = sc.nextInt();
                        reservation.setNumberOfPax(num);
                        System.out.println("NumberOfPax has been updated");
                        break;
                    case 5:
                        int roomId = reservation.getRoomId();
                        Room room = getRoom(roomId);
                        // update room;
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }
            return true;
        }
        else return false;
    }
    private boolean removeReservation(int targetId){
        Reservation reservation = findReservation(targetId);
        if(reservation){
            reservation.removeReservation();
            System.out.println("Reservation has been removed");
            return true;
        }
        else return false;
    }
    private void searchReservation(int targetId){
        Reservation reservation = findReservation(targetId);
        if(reservation){
            reservation.printReservation();
        }
        else return;
    }
    private String setDate(){
        System.out.println("Please enter the date in this format: yyyy/MM/dd HH:mm");
        return sc.nextLine();
    }
    private Reservation findReservation(int targetId){
        for (int i = Reservation.size(); i>0 ; i--){
            Reservation searchedItem = Reservation.get(i);
            if(searchedItem.getReservationId()==targetId)
                return searchedItem;
        }
        System.out.println("Reservation not found");
        return null;
    }
    public void printReservation(){
        Room room = getRoom(roomId);
        System.out.println("Reservation Details");
        System.out.println("Reservation ID"+'\t'+reservationId);
        System.out.println("Guest ID"+'\t'+guestId);
        System.out.println("Check-in"+'\t'+checkedInDate);
        System.out.println("Check-out"+'\t'+checkedOutDate);
        System.out.println("NumberOfPax"+'\t'+numberOfPax);
        System.out.println("RoomType"+'\t'+room.getRoomType());
        System.out.println("RoomNumber"+'\t'+room.getRoomNumberString());
        System.out.println("Reserved"+'\t'+reservationDate);
        System.out.println("Status"+'\t'+reservationStatus);
}
}

