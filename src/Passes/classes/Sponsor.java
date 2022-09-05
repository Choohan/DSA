package Passes.classes;

import java.time.LocalDateTime;

public class Sponsor extends VisitPass {
    private String IDNo;
    private Country nationality;

    public Sponsor() {
        // empty constructor
    }

    public Sponsor(String applicationID, String IDNo, Country nationality, LocalDateTime applyTimestamp) {
        super(applicationID, applyTimestamp);
        this.IDNo = IDNo;
        this.nationality = nationality;
    }

    public String getIDNo() {
        return IDNo;
    }

    public void setIDNo(String IDNo) {
        this.IDNo = IDNo;
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
                Nationality     : %s
                """, applicationID, IDNo, nationality.getName());
    }

    @Override
    public String getTitle() {
        return "Social Visit Pass (Sponsor)";
    }
}
