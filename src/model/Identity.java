package src.model;

import src.model.enums.IdentityType;
public class Identity {
    private IdentityType type;
    private String identity_no;


    public Identity(){
        
    }


    public IdentityType getType() {
        return type;
    }
    public void setType(IdentityType type) {
        this.type = type;
    }
    public String getIdentity_no() {
        return identity_no;
    }
    public void setIdentity_no(String identity_no) {
        this.identity_no = identity_no;
    }

    public void printIdentity(){
        if(this.type == IdentityType.DRIVING_LICENSE){
            System.out.println("Identity type: Driving License");
        }
        else{
            System.out.println("Identity type: Passport");
        }

        System.out.printf("Identity no: %s\n", identity_no);
    }
    
}
