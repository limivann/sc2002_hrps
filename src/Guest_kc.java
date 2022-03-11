package src;

import java.util.Scanner;

/**
 * Class that represent the guest
 * @author Zhang Kaichen
 */
public class Guest {
    /**
     * This enum class define the gender of the Guest
     */
    enum Gender {
        Male, Female
    };

    private String name;
    private String creditCard;
    private String address;
    private Gender gender;
    private Identity identity;
    private String nationality;
    private String contact;
    
    // Payment paymentDetails;
    // Reservation reservationDetails;

    /**
     * This is the default constructor for the guest
     * It construct a blank Guest through input
     */

    public Guest(){
        
    }
    

    /**
     * This is the function that add personal detail to a Guest object
     * @exception Exception when input is not Male and Female
     * @exception Exception when user does not choose between Drving License or Passport
     * @see Identity
     */
    public void add_personal_detail(){
        Scanner sc = new Scanner(System.in);
        System.out.printf("Please enter your name: ");
        name = sc.nextLine();
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


    }

    /**
     * This is the function that can update the personal detail
     * of a Guest object according to the user's choice
     * @throws Exception when choice is invalid
     */
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
            System.out.println("Please enter your new name:");
            name = sc.nextLine();
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
            case 6:
            System.out.println("Please enter your nationality:");
            nationality = sc.nextLine();
            case 7:
            System.out.println("Please enter your contact number:");
            contact = sc.nextLine();
            default:
                try {
                    throw new Exception("Invalid choice");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

        }
    }

    /**
     * This method is used to set the gender
     * @exception Exception when input is not Male and Female
     */
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
                    gender = Gender.Male;
                }
                else{
                    gender = Gender.Female;
                }
                wrong_input = false;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
    }

    /**
     * This method is used to set the identity
     * @exception Exception when user does not choose between Drving License or Passport
     */
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
                String identity_no = sc.nextLine();
                identity.setIdentity_no(identity_no);
                wrong_input = false;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    //make Reservation 
    public void printGuestDetails(){
        System.out.println("----------------");
        System.out.printf("Name: %s\n", name);
        System.out.printf("Credit Card No: %s\n", creditCard);
        System.out.printf("Address: %s\n", address);
        if(gender == Gender.Male){
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