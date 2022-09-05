package Passes.classes;

import java.time.LocalDateTime;

public class SecondHome extends VisitPass {
    private String passportNo;
    private String oldPassportNo;
    private Country nationality;

    public SecondHome() {
        // empty constructor
    }

    public SecondHome(String applicationID, String passportNo, String oldPassportNo, Country nationality, LocalDateTime applyTimestamp) {
        super(applicationID, applyTimestamp);
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

    @Override
    public String toString() {
        return String.format("""
                    Individual Social Visit Pass
                ------------------------------------
                Application ID  : %s
                Passport No     : %s
                Old Passport No : %s
                Nationality     : %s
                """, applicationID, passportNo, oldPassportNo, nationality);
    }

    @Override
    public String getTitle() {
        return "Malaysia Second Home Programme Pass";
    }
}
