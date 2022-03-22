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
        System.out.println("----------------");
        System.out.printf("Name: %s\n", getName());
        System.out.printf("Guest ID: %s\n", getCreditCard());
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