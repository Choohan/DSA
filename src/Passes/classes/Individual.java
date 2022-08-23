package Passes.classes;

import java.time.LocalDateTime;

public class Individual extends VisitPass {
    private String passportNo;
    private Country nationality;

    public Individual() {
        // empty constructor
    }

    public Individual(String applicationID, String passportNo, Country nationality, LocalDateTime applyTimestamp) {
        super(applicationID, applyTimestamp);
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

    @Override
    public String toString() {
        return String.format("""
                    Individual Social Visit Pass
                ------------------------------------
                Application ID  : %s
                Passport No     : %s
                Nationality     : %s
                """, applicationID, passportNo, nationality.getName());
    }

    @Override
    public String getTitle() {
        return "Social Visit Pass (Individual)";
    }
}
