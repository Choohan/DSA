package Passes.classes;

public class SecondHome extends VisitPass {
    private String passportNo;
    private String oldPassportNo;
    private Country nationality;

    public SecondHome() {
        // empty constructor
    }

    public SecondHome(String applicationID, String passportNo, String oldPassportNo, Country nationality) {
        super(applicationID);
        this.passportNo = passportNo;
        this.oldPassportNo = oldPassportNo;
        this.nationality = nationality;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getOldPassportNo() {
        return oldPassportNo;
    }

    public void setOldPassportNo(String oldPassportNo) {
        this.oldPassportNo = oldPassportNo;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }
}
