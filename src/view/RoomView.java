package src.view;
import java.util.Scanner;
import src.model.Room;
import src.controller.RoomManager;
import java.util.InputMismatchException;

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
        printMenu();
        int opt = sc.nextInt();
        do{
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
            }
            printMenu();
            opt = sc.nextInt();
        }while (opt < 6);
    }
    public void createRoom(){
        int floor = 0, room = 0, opt = 0;
        double price = 0;
        boolean wifi = true, smoking = false;

        Scanner sc = new Scanner(System.in);
        do{
            try{
                System.out.println("Enter the floor number (integer): ");
                floor = sc.nextInt();
                System.out.println("Enter the room number (integer): ");
                room = sc.nextInt();
                System.out.println("Enter the price (double): ");
                price = sc.nextDouble();  
                System.out.println("Is this room wifi-enabled? (type true or false)");
                wifi = sc.nextBoolean();
                System.out.println("Is smoking allowed in this room? (type true or false)");
                smoking = sc.nextBoolean();          
            }catch (InputMismatchException e){
                System.out.println("Wrong data type! ");
                System.out.println("________________\n");
            }
            sc.nextLine();
        }while (floor <= 0 || room <= 0 || price <= 0);

        do{
            try{
                System.out.println("Enter choice of room type: ");
                System.out.println("1 for Single room");
                System.out.println("2 for Double room");
                System.out.println("3 for Deluxe room");
                System.out.println("4 for VIP suite");
                opt = sc.nextInt();            
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
                    default: 
                    System.out.println("Please enter number from 1 to 4 only.");
                    break;
                }                    
            }catch (InputMismatchException e){
                System.out.println("Wrong data type!");
            }
             sc.nextLine();
        }while (opt < 1 && opt > 4);

    }
    private boolean isBoolean(String str) {
        if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")) {
            return true;
        }
        return false;
    }

    public void printRoom(){
        Scanner sc = new Scanner(System.in);
        int floor = 0, room = 0;
        do{
            try{
                System.out.println("Enter the floor: ");
                floor = sc.nextInt();
                System.out.println("Enter the room: ");
                room = sc.nextInt();
                a.printRoom(floor, room);                
            }catch (InputMismatchException e){
                System.out.println("Wrong data type!");
            }
            sc.nextLine();
        }while (floor <= 0 || room <= 0);

    }
    public void updateRoom(){
        Scanner sc = new Scanner(System.in);
        int floor = 0, room = 0, status = 0, opt = 0;
        double price = 0;

        do {
            try{
                System.out.println("Which room do you want to update?");
                System.out.println("Enter floor:");
                floor = sc.nextInt();
                System.out.println("Enter room:");
                room = sc.nextInt();
                System.out.println("What do you wish to update?");
                do{
                    System.out.println("1 for status");
                    System.out.println("2 for price");
                    opt = sc.nextInt();       
                    switch (opt){
                        case 1:
                            status = -1; //temp value is -1
                            do{
                                System.out.println("Which is the new status?");
                                System.out.println("1 for vacant");
                                System.out.println("2 for occupied");
                                System.out.println("3 for reserved");
                                System.out.println("4 for under maintenance");
                                if (status <= 4 && status >= 1){
                                    status = sc.nextInt();
                                }else{
                                    System.out.println("Please enter number from 1 to 4 only.");
                                }
                            }while (status > 4 && status < 1);
                            a.updateStatus(floor, room, status);  
                            break;
                        case 2:
                            System.out.println("Enter the new price?");
                            price = sc.nextDouble();
                            a.updatePrice(floor, room, price);                
                            break;
                        default:
                            System.out.println("Please enter number from 1 or 2 only.");  
                            break;                
                    }     
                }while (opt < 1 && opt > 2);
            }catch (InputMismatchException e){
                System.out.println("Wrong data type!");
            }
            sc.nextLine();
        }while (floor <= 0 || room <= 0 || status <= 0 || status > 4 || price <= 0);
        
    }

    public void removeRoom(){
        Scanner sc = new Scanner(System.in);

        int floor = 0, room = 0;
        do{
            try{
                System.out.println("Enter the floor: ");
                floor = sc.nextInt();
                System.out.println("Enter the room: ");
                room = sc.nextInt();
                a.remove(floor, room);                
            }catch (InputMismatchException e){
                System.out.println("Wrong data type!");
            }
            sc.nextLine();
        }while (floor <= 0 || room <= 0);
    }

    public void printRoomStatus(){
        Scanner sc = new Scanner(System.in);

        int floor = 0, room = 0;
        do{
            try{
                System.out.println("Enter the floor: ");
                floor = sc.nextInt();
                System.out.println("Enter the room: ");
                room = sc.nextInt();
                a.printStatus(floor, room);                
            }catch (InputMismatchException e){
                System.out.println("Wrong data type!");
            }
            sc.nextLine();
        }while (floor <= 0 || room <= 0);
    }
}
