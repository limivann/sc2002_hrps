package src.model.enums;

public enum IdentityType {
    DRIVING_LICENSE("Driving License"),
    PASSPORT("Passport");

    public final String identityTypeAsStr;

    private IdentityType(String identityTypeAsStr) {
        this.identityTypeAsStr = identityTypeAsStr;  
    }
}
