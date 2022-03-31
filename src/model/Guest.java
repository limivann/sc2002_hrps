package src.model;

import java.io.Serializable;
import src.model.enums.Gender;
import src.helper.Helper;


/**
 * The Class that handles Guest model
 * @author Zhang Kaichen
 * @version 1.0
 * @since 30-03-2022
 */
public class Guest implements Serializable {

    /**
     * For Java Serializable
     */
    private static final long serialVersionUID = 1L;

    /**
     * The name of the Guest
     */
    private String name;

    /**
     * The first name of the Guest
     */
    private String firstName;

    /**
     * The last name of the Guest
     */
    private String lastName;

    /**
     * The credit card number of the Guest
     */
    private String creditCard;

    /**
     * The address of the guest
     */
    private String address;

    /**
     * The Gender of the guest
     * {@link Gender}
     */
    private Gender gender;

    /**
     * The identity of the Guest
     * {@link Identity}
     */
    private Identity identity;

    /**
     * The nationality of the Guest
     */
    private String nationality;
    /**
     * Contact number of the Guest
     */
    private String contact;

    /**
     * Automate generated id for the guest
     * {@link Helper} to see how the guest id is generated
     */
    private String guestId;
    
    // Payment paymentDetails;
    // Reservation reservationDetails;

    /**
     * Constructor of the guest
     * @param name name of the guest
     * @param firstName first name of the guest
     * @param lastName last name of the guest
     * @param creditCard credit card number of the guest
     * @param address address of the guest
     * @param gender gender of the guest
     * @param identity identity of the guest
     * @param nationality nationality of the guest
     * @param contact contact no of the guest
     * @param guestId autogenerate guest id
     * see {@link Helper} for the generation of guest id
     * see {@link Identity} for the detail of identification
     */
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
    /**
     * Setter
     * @param name name of the guest
     * @return {@code true} if set successfully
     */
    public boolean setName(String name) {
        this.name = name;
        return true;
    }
    /**
     * Setter
     * @param firstName first name of the guest
     * @return {@code true} if set successfully
     */
    public boolean setFirstName(String firstName) {
        this.firstName = firstName;
        return true;
    }
    /**
     * Setter
     * @param lastName last name of the guest
     * @return {@code true} if set successfully
     */
    public boolean setLastName(String lastName) {
        this.lastName = lastName;
        return true;
    }

    /**
     * Setter
     * @param creditCard credit card of the guest
     * @return {@code true} if set successfully
     */
    public boolean setCreditCard(String creditCard) {
        this.creditCard = creditCard;
        return true;
    }

    /**
     * Setter
     * @param address mailing address of the guest
     * @return {@code true} if set successfully
     */
    public boolean setAddress(String address) {
        this.address = address;
        return true;
    }
    /**
     * Setter
     * @param gender gender of the guest
     * @return {@code true} if set successfully
     */
    public boolean setGender(Gender gender) {
        this.gender = gender;
        return true;
    }
    
    /**
     * Setter
     * @param identity identity of the guest
     * @return {@code true} if set successfully
     * see {@link Identity} for the detail of identification
     */
    public boolean setIdentity(Identity identity) {
        this.identity = identity;
        return true;
    }
    /**
     * Setter
     * @param nationality nationality of the guest
     * @return {@code true} if set successfully
     */
    public boolean setNationality(String nationality) {
        this.nationality = nationality;
        return true;
    }
    /**
     * Setter
     * @param contact contact number of the guest
     * @return {@code true} if set successfully
     */
    public boolean setContact(String contact) {
        this.contact = contact;
        return true;
    }
    /**
     * Setter
     * @param guestId guest id of the guest
     * @return {@code true} if set successfully
     */
    public boolean setGuestId(String guestId) {
        this.guestId = guestId;
        return true;
    }
    
    // GETTERS

    /**
     * Getter
     * @return the name of the guest
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     * @return the credit card number of guest
     */
    public String getCreditCard() {
        return creditCard;
    }
    /**
     * Getter
     * @return the address of the guest
     */
    public String getAddress() {
        return address;
    }
    /**
     * Getter
     * @return the contact number of the guest
     */
    public String getContact() {
        return contact;
    }

    /**
     * Getter
     * @return the gender of the guest
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Getter
     * @return the nationality of the guest
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Getter
     * @return the guest id of the guest
     */
    public String getGuestId(){
        return guestId;
    }
    
    /**
     * Print the complete details of the guest
     */
    public void printGuestDetails() {
        System.out.printf("Guest ID: %s\n", getGuestId());
        System.out.println("----------------");
        System.out.printf("Name: %s\n", getName());
        System.out.printf("Credit Card No: %s\n", getCreditCard());
        System.out.printf("Address: %s\n", getAddress());
        System.out.println("Gender: " + getGender().genderAsStr);
        identity.printIdentity();
        System.out.printf("Nationality: %s\n", getNationality());
        System.out.printf("Contact No: %s\n", getContact());
        System.out.println("----------------");
    }
    /**
     * Override toString method to show the simplified details of the guest
     * @return a string of guest details
     */
    @Override
    public String toString() {
        return String.format("Guest Name: %s, Contact No: %s", getName(), getContact());
    }
}