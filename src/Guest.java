package src;
public class Guest {
    enum Gender {
        Male, Female
    };

    String name;
    String creditCard;
    String address;
    String country;
    Gender gender;
    String identity;
    String nationality;
    String contact;
    // Payment paymentDetails;
    // Reservation reservationDetails;
    public Guest(String name, String creditCard, String address, String country, Gender gender, String identity, String nationality, String contact){
        this.name = name;
        this.creditCard = creditCard;
        this.address = address;
        this.country = country;
        this.gender = gender;
        this.identity = identity;
        this.nationality = nationality;
        this.contact = contact;
    }
    public String getName() {
        return name;
    }
    public String getCreditCard() {
        return creditCard;
    }
    public String getContact() {
        return contact;
    }
    //make Reservation 
    public void printGuestDetails(){
        
    }
}