package src.model;

import java.io.Serializable;

import src.model.enums.IdentityType;

/**
 * The Class for identity details of the guest
 * @author Zhang Kaichen
 * @version 1.0
 * @since 2022-03-30
 */
public class Identity implements Serializable {
    
    /**
     * The Type of the identity
     * Driving license or Passport
     */
    private IdentityType type;

    /**
     * The number of the identity
     */
    private String identityNo;
    /**
     * Constructor of the identity
     * @param type type of the identiy
     * @param identityNo number of the identity
     */
    public Identity(IdentityType type, String identityNo) {
        setType(type);
        setIdentityNo(identityNo);
    }
    
    /**
     * Setter
     * @param type Type of the identity
     * @return {@code true} if set successfully
     */
    public boolean setType(IdentityType type) {
        this.type = type;
        return true;
    }

    /**
     * Setter
     * @param identityNo No. of the identity
     * @return {@code true} if set successfully
     */
    public boolean setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
        return true;
    }
    
    /**
     * Getter
     * @return type of the identity
     */
    public IdentityType getType() {
        return type;
    }

    /**
     * Getter
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
