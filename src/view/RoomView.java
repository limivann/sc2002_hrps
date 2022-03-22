package src.view;

import src.model.Room;
import src.controller.RoomManager;
import src.helper.Helper;

import java.util.InputMismatchException;

import src.model.enums.*;

public class RoomView extends MainView{
    private RoomManager a = new RoomManager();   

    public void printMenu() {
        System.out.println("Please select an option (1-7)");
        System.out.println("1. Create room");
        System.out.println("2. Print room");
        System.out.println("3. Update room");
        System.out.println("4. Print room by status");
        System.out.println("5. Print room by occupancy rate");
        System.out.println("6. Remove room");
        System.out.println("7. Exit");
    }

    @Override
    public void viewapp() {
        int opt = -1;
        do{
            try{
                printMenu();
                opt = Helper.readInt();
                switch (opt){
                    case 1:
                        RoomManager.printAllRooms();;
                        break;
                    case 2:
                        printRoom();
                        break;
                    case 3:
                        updateRoom();
                        break;
                    case 4:
                        printRoomByStatus();
                        break;
                    case 5:
                        printRoomByOccupancyRate();
                        break;
                    case 6:
                        removeRoom();
                        break;
                    case 7:
                        break; 
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                    
                }                
            }catch (InputMismatchException e){
                System.out.println("Wrong data type! ");
                System.out.println("________________\n");
            }
        }while (opt != 7 || opt < 0 || opt > 7);
    }
    public void createRoom(){
        int floor = 0, room = 0, opt = 0;
        double price = 0;
        boolean wifi = true, smoking = false;

        do{
            try{
                System.out.println("Enter the floor number (integer): ");
                floor = Helper.readInt();
                System.out.println("Enter the room number (integer): ");
                room = Helper.readInt();
                System.out.println("Enter the price (double): ");
                price = Helper.readDouble(); 
                System.out.println("Is this room wifi-enabled? (type true or false)");
                wifi = Helper.sc.nextBoolean();
                System.out.println("Is smoking allowed in this room? (type true or false)");
                smoking = Helper.sc.nextBoolean();       
            }catch (InputMismatchException e){
                System.out.println("Wrong data type! ");
                System.out.println("________________\n");
            }
        }while (floor <= 0 || room <= 0 || price <= 0);

        do{
            try{
                System.out.println("Enter choice of room type: ");
                System.out.println("1 for Single room");
                System.out.println("2 for Double room");
                System.out.println("3 for Deluxe room");
                System.out.println("4 for VIP suite");
                opt = Helper.readInt();          
                switch (opt){
                    case 1:
                    a.create(RoomType.SINGLE, floor, room,  price, wifi, smoking);
                    break;
                    case 2:
                    a.create(RoomType.DOUBLE, floor, room,  price, wifi, smoking);
                    break;
                    case 3:
                    a.create(RoomType.DELUXE, floor, room,  price, wifi, smoking);
                    break;
                    case 4:
                    a.create(RoomType.VIP_SUITE, floor, room,  price, wifi, smoking);
                    break;
                    default: 
                    System.out.println("Please enter number from 1 to 4 only.");
                    break;
                }                    
            }catch (InputMismatchException e){
                System.out.println("Wrong data type!");
                System.out.println("________________\n");
            }
        }while (opt < 1 && opt > 4);
    }

    public void printRoom() {
        int floor = 0, room = 0;
        do {
            try {
                System.out.println("Enter the floor: ");
                floor = Helper.readInt();
                System.out.println("Enter the room: ");
                room = Helper.readInt();
                RoomManager.printRoom(floor, room);
            } catch (InputMismatchException e) {
                System.out.println("Wrong data type!");
            }
        } while (floor <= 0 || room <= 0);

    }
    
    public void updateRoom(){
        int floor = 0, room = 0, status = 0, opt = 0;
        double price = 0;

        do {
            try{
                System.out.println("Which room do you want to update?");
                System.out.println("Enter floor:");
                floor = Helper.readInt();
                System.out.println("Enter room:");
                room = Helper.readInt();
                System.out.println("What do you wish to update?");
                do{
                    System.out.println("1 for status");
                    System.out.println("2 for price");
                    opt = Helper.readInt();      
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
                                    status = Helper.readInt();
                                }else{
                                    System.out.println("Please enter number from 1 to 4 only.");
                                }
                            }while (status > 4 && status < 1);
                            a.updateStatus(floor, room, status);  
                            break;
                        case 2:
                            System.out.println("Enter the new price?");
                            price = Helper.readDouble();
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
        }while (floor <= 0 || room <= 0 || status <= 0 || status > 4 || price <= 0);
        
    }

    public void removeRoom(){
        int floor = 0, room = 0;
        do{
            try{
                System.out.println("Enter the floor: ");
                floor = Helper.readInt();
                System.out.println("Enter the room: ");
                room = Helper.readInt();
                a.remove(floor, room);                
            }catch (InputMismatchException e){
                System.out.println("Wrong data type!");
            }
        }while (floor <= 0 || room <= 0);
    }
    
    public void printRoomByStatus(){
        a.printStatus();
    }
    public void printRoomByOccupancyRate(){
        a.printOccupancyRate();
    }
}
