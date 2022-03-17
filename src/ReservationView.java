public class ReservationView {
    public void print(){
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
                    //createReservation();
                    break;
                case 2:
                    System.out.println("Enter Reservation Id:");
                    reservationId = sc.nextInt();
                    //updateReservation(reservationId);
                    break;
                case 3:
                    System.out.println("Enter Reservation Id:");
                    reservationId = sc.nextInt();
                    //removeReservation(reservationId);
                    break;
                case 4:
                    System.out.println("Enter Reservation Id:");
                    reservationId = sc.nextInt();
                    // searchReservation(reservationId);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }  
}
