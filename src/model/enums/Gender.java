package src.model.enums;

/**
 * An Enum that corresponds to the different genders of the guest.
 * @author Ivan
 * @version 1.0
 * @since 2022-03-30
 */
public enum Gender {
    /**
     * Gender type corresponding to male.
     */
    MALE("Male"),
    /**
     * Gender type corresponding to female.
     */
    FEMALE("Female");

    /**
     * A String value for the gender for retrieving purposes.
     */
    public final String genderAsStr;

    /**
     * Constructor for the Gender Enum.
     * @param genderAsStr Gender as a string
     */
    private Gender(String genderAsStr) {
        this.genderAsStr = genderAsStr;
    }
}
