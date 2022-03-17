// public class UpdateReservationView {
//     public void print(){
//         int option = -1;
//         System.out.println("Enter Reservation Id:");
//         int reservationId = sc.nextInt();
//         if (reservationId)
//         Reservation reservation = findReservation(targetId);
//         if(reservation){
//             System.out.println("1: ReservationStatus");
//             System.out.println("2: CheckedInDate");
//             System.out.println("3: CheckedOutDate");
//             System.out.println("4: NumberOfPax");
//             System.out.println("5: Room");
//             System.out.println("6: Exit");
//             while(option!=6){
//                 System.out.println("Enter an option");
//                 option = sc.nextInt();
//                 switch(option){
//                     case 1:
//                         System.out.println("1: CONFIRMED");
//                         System.out.println("2: IN_WAITLIST");
//                         System.out.println("3: CHECKED_IN");
//                         System.out.println("4: EXPIRED");
//                         System.out.println("5: CHECKED_OUT");
//                         System.out.println("6: CANCELLED");
//                         int status = sc.nextInt();
//                         if(status>=1 && status<=6)
//                             reservation.setReservationStatus(ReservationStatus.values()[status]);
//                         else
//                             System.out.println("Invalid option");
//                         break;
//                     case 2:
//                         String date = setDate();
//                         reservation.setCheckedInDate(date);
//                         System.out.println("CheckedInDate has been updated");
//                         break;
//                     case 3:
//                         date = setDate();
//                         reservation.setCheckedOutDate(date);
//                         System.out.println("CheckedOutDate has been updated");
//                         break;
//                     case 4:
//                         System.out.println("Enter NumberOfPax:");
//                         int num = sc.nextInt();
//                         reservation.setNumberOfPax(num);
//                         System.out.println("NumberOfPax has been updated");
//                         break;
//                     case 5:
//                         int roomId = reservation.getRoomId();
//                         Room room = getRoom(roomId);
//                         // update room;
//                         break;
//                     case 6:
//                         break;
//                     default:
//                         System.out.println("Invalid option");
//                 }
//             }
// }
