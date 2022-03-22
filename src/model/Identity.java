package src.model;

import java.io.Serializable;

import src.model.enums.IdentityType;
public class Identity implements Serializable{
    private IdentityType type;
    private String indentityNo;


    public Identity() {

    }
    
    public Identity(IdentityType type, String indentityNo) {
        this.type = type;
        this.indentityNo = indentityNo;
    }


    public IdentityType getType() {
        return type;
    }
    public void setType(IdentityType type) {
        this.type = type;
    }
    public String getindentityNo() {
        return indentityNo;
    }
    public void setIdentityNo(String indentityNo) {
        this.indentityNo = indentityNo;
    }

    public void printIdentity(){
        if(this.type == IdentityType.DRIVING_LICENSE){
            System.out.println("Identity type: Driving License");
        }
        else{
            System.out.println("Identity type: Passport");
        }

        System.out.printf("Identity no: %s\n", indentityNo);
    }
    
}
