package src.model.enums;

/**
 * An Enum that corresponds to the different type of identities of the guest.
 * @author Ivan
 * @version 1.0
 * @since 2022-03-30
 */
public enum IdentityType {
    /**
     * Identity type corresponding to driving license.
     */
    DRIVING_LICENSE("Driving License"),

    /**
     * Identity type corresponding to passport.
     */
    PASSPORT("Passport");

    /**
     * A String value for the identity type for retrieving purposes.
     */
    public final String identityTypeAsStr;

    /**
     * Constructor for the IdentityType Enum.
     * @param identityTypeAsStr Identity type as a string
     */
    private IdentityType(String identityTypeAsStr) {
        this.identityTypeAsStr = identityTypeAsStr;  
    }
}
