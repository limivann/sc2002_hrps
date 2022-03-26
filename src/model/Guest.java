package src.model;

import java.io.Serializable;
import java.util.Scanner;

import src.model.enums.Gender;
import src.model.enums.IdentityType;

public class Guest implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 
     * @param name new name of the guest
     * @return true if set successfully
     */
    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    /**
     * 
     * @param firstName new first name of the guest
     * @return true if set successfully
     */
    public boolean setFirstName(String firstName) {
        this.firstName = firstName;
        return true;
    }

    /**
     * 
     * @param lastName new last name of the guest
     * @return true if set successfully
     */
    public boolean setLastName(String lastName) {
        this.lastName = lastName;
        return true;
    }

    /**
     * 
     * @param creditCard new credit card number of the guest
     * @return true if set successfully
     */
    public boolean setCreditCard(String creditCard) {
        this.creditCard = creditCard;
        return true;
    }

    /**
     * 
     * @param address new address of the guest
     * @return true if set successfully
     */
    public boolean setAddress(String address) {
        this.address = address;
        return true;
    }

    /**
     * 
     * @param gender new gender of the guest
     * @return true if set successfully
     */
    public boolean setGender(Gender gender) {
        this.gender = gender;
        return true;
    }

    /**
     * 
     * @param identity new identity of the guest
     * @return true if set successfully
     */
    public boolean setIdentity(Identity identity) {
        this.identity = identity;
        return true;
    }

    /**
     * 
     * @param nationality new nationality of the guest
     * @return true if set successfully
     */
    public boolean setNationality(String nationality) {
        this.nationality = nationality;
        return true;
    }

    /**
     * 
     * @param contact new contact number of the guest
     * @return true if set successfully
     */
    public boolean setContact(String contact) {
        this.contact = contact;
        return true;
    }

    /**
     * 
     * @param guestId new guest id of the guest
     * @return true if set successfully
     */
    public boolean setGuestId(String guestId) {
        this.guestId = guestId;
        return true;
    }
    
    // GETTERS
    /**
     * 
     * @return the name of the guest
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return the credit card number of guest
     */
    public String getCreditCard() {
        return creditCard;
    }

    /**
     * 
     * @return the address of the guest
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @return the contact number of the guest
     */
    public String getContact() {
        return contact;
    }

    /**
     * 
     * @return the gender of the guest
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * 
     * @return the nationality of the guest
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * 
     * @return the guest id of the guest
     */
    public String getGuestId() {
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

    @Override
    /**
     * Override toString method to show the simplified details of the guest
     */
    public String toString() {
        return String.format("Guest Name: %s, Contact No: %s", getName(), getContact());
    }
}