package Passes.classes;

public class Spouse extends VisitPass {
    private String NRIC;
    private Country nationality;
    private String passportNo;

    public Spouse() {
        // empty constructor
    }

    public Spouse(String applicationID, String NRIC, Country nationality, String passportNo) {
        super(applicationID);
        this.NRIC = NRIC;
        this.nationality = nationality;
        this.passportNo = passportNo;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getNRIC() {
        return NRIC;
    }

    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }
}
