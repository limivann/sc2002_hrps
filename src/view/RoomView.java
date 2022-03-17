package src.view;
import java.util.Scanner;
import src.model.Room;
import src.controller.RoomManager;

public class RoomView extends MainView{
    private RoomManager a = new RoomManager();   
    public void printMenu(){
        System.out.println("\n1. Create room");
        System.out.println("2. Print room");
        System.out.println("3. Update room");
        System.out.println("4. Print room status");
        System.out.println("5. Remove room");
        System.out.println("6. Exit");
    }

    @Override
    public void viewapp() {
        Scanner sc = new Scanner(System.in);
        int opt = -1;
        do{
            printMenu();
            opt = sc.nextInt();
            switch (opt){
                case 1:
                    createRoom();
                    break;
                case 2:
                    printRoom();
                    break;
                case 3:
                    updateRoom();
                    break;
                case 4:
                    printRoomStatus();
                    break;
                case 5:
                    removeRoom();
                    break;
                case 6:
                    break;
                default:
                    // TODO: Handle exception
                    System.out.println("Invalid choice. Please try again.");
                    break;
                
            }
        }while (opt != 6);
    }
    public void createRoom(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the floor number: ");
        int floor = sc.nextInt();
        System.out.println("Enter the room number: ");
        int room = sc.nextInt();
        System.out.println("Enter the price: ");
        double price = sc.nextDouble();
        System.out.println("Is this room wifi-enabled?");
        boolean wifi = sc.nextBoolean();
        System.out.println("Is smoking allowed in this room?");
        boolean smoking = sc.nextBoolean();

        System.out.println("Enter choice of room type: ");
        System.out.println("1 for Single room");
        System.out.println("2 for Double room");
        System.out.println("3 for Deluxe room");
        System.out.println("4 for VIP suite");
        int opt = sc.nextInt();
        
        switch (opt){
            case 1:
            a.create(Room.RoomType.SINGLE, floor, room,  price, wifi, smoking);
            break;
            case 2:
            a.create(Room.RoomType.DOUBLE, floor, room,  price, wifi, smoking);
            break;
            case 3:
            a.create(Room.RoomType.DELUXE, floor, room,  price, wifi, smoking);
            break;
            case 4:
            a.create(Room.RoomType.VIP_SUITE, floor, room,  price, wifi, smoking);
            break;
        }     
    }
    public void printRoom(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the floor: ");
        int floor = sc.nextInt();
        System.out.println("Enter the room: ");
        int room = sc.nextInt();
        a.printRoom(floor, room);
    }
    public void updateRoom(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Which room do you want to update?");
        System.out.println("Enter floor:");
        int floor = sc.nextInt();
        System.out.println("Enter room:");
        int room = sc.nextInt();

        System.out.println("What do you wish to update?");
        System.out.println("1 for status");
        System.out.println("2 for price");
        int opt = sc.nextInt();
        if (opt == 1){
            System.out.println("Which is the new status?");
            System.out.println("1 for vacant");
            System.out.println("2 for occupied");
            System.out.println("3 for reserved");
            System.out.println("4 for under maintenance");
            int status = sc.nextInt();
            a.updateStatus(floor, room, status);
        }else if (opt == 2){
            System.out.println("Enter the new price?");
            double price = sc.nextDouble();
            a.updatePrice(floor, room, price);
        }
    }
    public void removeRoom(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter floor:");
        int floor = sc.nextInt();
        System.out.println("Enter room:");
        int room = sc.nextInt();
        a.remove(floor, room);
    }
    public void printRoomStatus(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter floor:");
        int floor = sc.nextInt();
        System.out.println("Enter room:");
        int room = sc.nextInt();
        a.printStatus(floor, room);
    }
}
