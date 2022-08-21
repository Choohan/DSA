package Passes.classes;

public class Individual extends VisitPass {
    private String passportNo;
    private Country nationality;

    public Individual() {
        // empty constructor
    }

    public Individual(String applicationID, String passportNo, Country nationality) {
        super(applicationID);
        this.passportNo = passportNo;
        this.nationality = nationality;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }
}
