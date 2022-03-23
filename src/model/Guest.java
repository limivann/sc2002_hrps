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
    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public boolean setFirstName(String firstName) {
        this.firstName = firstName;
        return true;
    }

    public boolean setLastName(String lastName) {
        this.lastName = lastName;
        return true;
    }

    public boolean setCreditCard(String creditCard) {
        this.creditCard = creditCard;
        return true;
    }

    public boolean setAddress(String address) {
        this.address = address;
        return true;
    }

    public boolean setGender(Gender gender) {
        this.gender = gender;
        return true;
    }

    public boolean setIdentity(Identity identity) {
        this.identity = identity;
        return true;
    }

    public boolean setNationality(String nationality) {
        this.nationality = nationality;
        return true;
    }

    public boolean setContact(String contact) {
        this.contact = contact;
        return true;
    }

    public boolean setGuestId(String guestId) {
        this.guestId = guestId;
        return true;
    }
    
    // GETTERS
    public String getName() {
        return name;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }
    public Gender getGender() {
        return gender;
    }
    public String getNationality() {
        return nationality;
    }
    public String getGuestId(){
        return guestId;
    }
    
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
    public String toString() {
        return String.format("Guest Name: %s, Contact No: %s", getName(), getContact());
    }
}