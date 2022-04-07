package src.model;

import java.io.Serializable;

import src.model.enums.IdentityType;

/**
 * The Class that handles the identity details of the guests.
 * @author Zhang Kaichen
 * @version 1.0
 * @since 2022-03-30
 */
public class Identity implements Serializable {
    
    /**
     * The Type of the identity. <p>
     * See {@link IdentityType} for different types of identity.
     */
    private IdentityType type;

    /**
     * The number of the identity
     */
    private String identityNo;
    /**
     * Constructor of the identity
     * @param type type of the identiy
     * @param identityNo No. of the identity
     */
    public Identity(IdentityType type, String identityNo) {
        setType(type);
        setIdentityNo(identityNo);
    }
    
    /**
     * Sets the type of the identity.
     * @param type Type of the identity
     * @return {@code true} if sets successfully
     */
    public boolean setType(IdentityType type) {
        this.type = type;
        return true;
    }

    /**
     * Sets the No. of the identity.
     * @param identityNo No. of the identity
     * @return {@code true} if sets successfully
     */
    public boolean setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
        return true;
    }
    
    /**
     * Gets the type of the identity.
     * @return type of the identity
     */
    public IdentityType getType() {
        return type;
    }

    /**
     * Gets the No. of the identity.
     * @return identity number
     */
    public String getIdentityNo() {
        return identityNo;
    }
    
    /**
     * Override toString method to show the identity details
     * @return a string of identity details
     */
    @Override
    public String toString() {
        String res = "";
        res += "Identity Type: " + type.identityTypeAsStr + "\n";
        res += "Identity no: " + this.getIdentityNo();
        return res;
    }
    
}
