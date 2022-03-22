package src.model.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    public final String genderAsStr;

    private Gender(String genderAsStr) {
        this.genderAsStr = genderAsStr;
    }
}
