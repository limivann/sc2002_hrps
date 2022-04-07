package src.model;

import java.io.Serializable;
import src.model.enums.Gender;
import src.helper.Helper;


/**
 * The Class that handles the data of hotel's guests. 
 * @author Zhang Kaichen
 * @version 1.0
 * @since 30-03-2022
 */
public class Guest implements Serializable, Comparable<Guest> {

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
     * @param name full name of the guest
     * @param firstName first name of the guest
     * @param lastName last name of the guest
     * @param creditCard credit card number of the guest
     * @param address mailing address of the guest
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
     * Sets the full name of the guest
     * @param name full name of the guest
     * @return {@code true} if sets successfully
     */
    public boolean setName(String name) {
        this.name = name;
        return true;
    }
    /**
     * Sets the first name of the guest
     * @param firstName first name of the guest
     * @return {@code true} if sets successfully
     */
    public boolean setFirstName(String firstName) {
        this.firstName = firstName;
        return true;
    }
    /**
     * Sets the last name of the guest
     * @param lastName last name of the guest
     * @return {@code true} if sets successfully
     */
    public boolean setLastName(String lastName) {
        this.lastName = lastName;
        return true;
    }

    /**
     * Sets the credit card of the guest
     * @param creditCard credit card of the guest
     * @return {@code true} if sets successfully
     */
    public boolean setCreditCard(String creditCard) {
        this.creditCard = creditCard;
        return true;
    }

    /**
     * Sets the mailing address of the guest
     * @param address mailing address of the guest
     * @return {@code true} if sets successfully
     */
    public boolean setAddress(String address) {
        this.address = address;
        return true;
    }
    /**
     * Sets the gender of the guest
     * @param gender gender of the guest
     * @return {@code true} if sets successfully
     */
    public boolean setGender(Gender gender) {
        this.gender = gender;
        return true;
    }
    
    /**
     * Sets the identity of the guest <p>
     * see {@link Identity} for the detail of identification
     * @param identity identity of the guest
     * @return {@code true} if sets successfully
     */
    public boolean setIdentity(Identity identity) {
        this.identity = identity;
        return true;
    }
    /**
     * Sets the nationality of the guest
     * @param nationality nationality of the guest
     * @return {@code true} if sets successfully
     */
    public boolean setNationality(String nationality) {
        this.nationality = nationality;
        return true;
    }
    /**
     * Sets the contact number of the guest
     * @param contact contact number of the guest
     * @return {@code true} if sets successfully
     */
    public boolean setContact(String contact) {
        this.contact = contact;
        return true;
    }
    /**
     * Sets the guest id of the guest
     * @param guestId guest id of the guest
     * @return {@code true} if sets successfully
     */
    public boolean setGuestId(String guestId) {
        this.guestId = guestId;
        return true;
    }
    
    // GETTERS

    /**
     * Gets the full name of the guest
     * @return the full name of the guest
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the first name of the guest
     * @return the first name of the guest
     */
    public String getFirstName(){
        return firstName;
    }
    /**
     * Gets the last name of the guest
     * @return the last name of the guest
     */
    public String getLastName(){
        return lastName;
    }
    /**
     * Gets the credit card of the guest
     * @return the credit card number of guest
     */
    public String getCreditCard() {
        return creditCard;
    }
    /**
     * Gets the mailing address of the guest
     * @return the mailing address of the guest
     */
    public String getAddress() {
        return address;
    }
    /**
     * Gets the contact number of the guest
     * @return the contact number of the guest
     */
    public String getContact() {
        return contact;
    }

    /**
     * Gets the gender of the guest
     * @return the gender of the guest
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets the nationality of the guest
     * @return the nationality of the guest
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Gets the identity of the guest
     * @return the identity of the guest
     */
    public Identity getIdentity() {
        return identity;
    }

    /**
     * Gets the guest id of the guest
     * @return the guest id of the guest
     */
    public String getGuestId() {
        return guestId;
    }
    
    /**
     * Override toString method to show the simplified details of the guest
     * @return a string of guest details
     */
    @Override
    public String toString() {
        return String.format("Guest Name: %s, Contact No: %s", getName(), getContact());
    }
    
    /**
     * Override compareTo method to compare different guest objects according to full name
     */
    @Override
    public int compareTo(Guest guest) {
        if (this == guest) {
            return 0;
        }
        return this.getName().compareTo(guest.getName());
    }
}