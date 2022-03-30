package src.model;

import java.io.Serializable;

import src.model.enums.IdentityType;

public class Identity implements Serializable {
    
    private IdentityType type;
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
     * @param type Type ofthe identity
     */
    public void setType(IdentityType type) {
        this.type = type;
    }

    /**
     * Setter
     * @param identityNo no of the identity
     */
    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
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
     * Print out the identity detail
     */
    public void printIdentity(){
        System.out.println("Identity Type: " + type.identityTypeAsStr);
        System.out.printf("Identity no: %s\n", getIdentityNo());
    }
    
}
