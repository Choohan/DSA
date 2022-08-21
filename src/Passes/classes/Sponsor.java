package Passes.classes;

public class Sponsor extends VisitPass {
    private String IDNo;
    private Country nationality;

    public Sponsor() {
        // empty constructor
    }

    public Sponsor(String applicationID, String IDNo, Country nationality) {
        super(applicationID);
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
}
