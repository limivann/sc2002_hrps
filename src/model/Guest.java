package src.model;

import java.io.Serializable;
import java.util.Scanner;

import src.model.enums.Gender;
import src.model.enums.IdentityType;

public class Guest implements Serializable{

    private String name;
    private String firstName;
    private String lastName;
    private String creditCard;
    private String address;
    private Gender gender;
    private Identity identity;
    private String nationality;
    private String contact;
    private String guestId;
    
    // Payment paymentDetails;
    // Reservation reservationDetails;

    public Guest() {

    }

    public Guest(String name, String firstName, String lastName, String creditCard, String address, Gender gender,
            Identity identity, String nationality, String contact, String guestId) {
        setName(name);
        setFirstName(firstName);
        setLastName(lastName);
        setCreditCard(creditCard);
        setAddress(address);
        setGender(gender);
        setIdentity(identity);
        setNationality(nationality);
        setContact(contact);
        setGuestId(guestId);
    }
    
    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }
    
    // SETTERS AND GETTERS
    public String getName() {
        return name;
    }
    public String getCreditCard() {
        return creditCard;
    }

    public String getContact() {
        return contact;
    }

    public String getGuestId(){
        return guestId;
    }
    
    public void add_personal_detail(){
        Scanner sc = new Scanner(System.in);
        System.out.printf("Please enter your first name: ");
        firstName = sc.nextLine();
        System.out.printf("Please enter your last name: ");
        lastName = sc.nextLine();
        name = firstName + " " + lastName;
        System.out.printf("Please enter your credit card number: ");
        creditCard = sc.nextLine();
        System.out.printf("Please enter your address: ");
        address = sc.nextLine();
        setgender();
        setidentity();
        System.out.printf("Please enter your nationality: ");
        nationality = sc.nextLine();
        System.out.printf("Please enter your contact number: ");
        contact = sc.nextLine();
<<<<<<< HEAD
        guestId = lastName + 1;
        System.out.printf("Your Guest ID is: %s\n", guestId);

=======
        guest_id = last_name + id;
        System.out.printf("Your Guest ID is: %s\n", guest_id);
        id++;
>>>>>>> 3223624ee033f385c7ecbf7e063c4cbb103e2763
    }
    public void update_detail(){
        System.out.println("Please choose the information that you want to update");
        System.out.println("(1) Name");
        System.out.println("(2) Credit Card");
        System.out.println("(3) Address");
        System.out.println("(4) Gender");
        System.out.println("(5) Identity");
        System.out.println("(6) Nationality");
        System.out.println("(7) Contact Number");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice){
            case 1:
            System.out.println("Please enter your new first name:");
            firstName = sc.nextLine();
            lastName = sc.nextLine();
            name = firstName + " " + lastName;
            break;

            case 2:
            System.out.println("Please enter your new credit card number:");
            creditCard = sc.nextLine();
            break;

            case 3:
            System.out.println("Please enter your new address:");
            address = sc.nextLine();
            break;

            case 4:
            setgender();
            break;

            case 5:
            setidentity();
            break;

            case 6:
            System.out.println("Please enter your nationality:");
            nationality = sc.nextLine();
            break;

            case 7:
            System.out.println("Please enter your contact number:");
            contact = sc.nextLine();
            break;

            default:
                try {
                    throw new Exception("Invalid choice");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

        }
    }

    public void setgender(){
        boolean wrong_input = true;
        while(wrong_input){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Please choose your gender");
                System.out.println("(1) Male");
                System.out.println("(2) Female");
                int choice = sc.nextInt();
                sc.nextLine();
                if(choice != 1 && choice != 2){
                    throw new Exception("Please choose between Male and Female");
                }

                if (choice == 1) {
                    gender = Gender.MALE;
                }
                else{
                    gender = Gender.FEMALE;
                }
                wrong_input = false;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
    }

    public void setidentity(){
        identity = new Identity();
        boolean wrong_input = true;
        while(wrong_input){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Please choose your identity type");
                System.out.println("(1) Driving License");
                System.out.println("(2) Passport");
                int choice = sc.nextInt();
                sc.nextLine();
                if(choice != 1 && choice != 2){
                    throw new Exception("Please choose between Driving License and Passport");
                }

                if(choice == 1){
                    identity.setType(IdentityType.DRIVING_LICENSE);
                }
                else{
                    identity.setType(IdentityType.PASSPORT);
                }

                System.out.println("Enter your identity no: ");
                String indentityNo = sc.nextLine();
                identity.setIdentityNo(indentityNo);
                wrong_input = false;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void printGuestDetails(){
        System.out.println("----------------");
        System.out.printf("Name: %s\n", name);
        System.out.printf("Guest ID: %s\n", guestId);
        System.out.printf("Credit Card No: %s\n", creditCard);
        System.out.printf("Address: %s\n", address);
        if(gender == Gender.MALE){
            System.out.println("Gender: Male");
        }
        else{
            System.out.println("Gender: Female");
        }
        identity.printIdentity();
        System.out.printf("Nationality: %s\n", nationality);
        System.out.printf("Contact No: %s\n", contact);
        System.out.println("----------------");
    }

}