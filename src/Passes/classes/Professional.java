package Passes.classes;

public class Professional extends VisitPass {
    private String companyID;
    private Category category;
    private String companyName;

    public Professional() {
        // empty constructor
    }

    public Professional(String applicationID, String companyID, Category category, String companyName) {
        super(applicationID);
        this.companyID = companyID;
        this.category = category;
        this.companyName = companyName;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
