package src.model;

import java.io.Serializable;

import src.model.enums.IdentityType;

public class Identity implements Serializable {
    // Dont need serialization uid
    private IdentityType type;
    private String identityNo;
    
    public Identity(IdentityType type, String identityNo) {
        setType(type);
        setIdentityNo(identityNo);
    }

    public void setType(IdentityType type) {
        this.type = type;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }
    
    public IdentityType getType() {
        return type;
    }

    public String getIdentityNo() {
        return identityNo;
    }
    
    public void printIdentity(){
        System.out.println("Identity Type: " + type.identityTypeAsStr);
        System.out.printf("Identity no: %s\n", getIdentityNo());
    }
    
}
