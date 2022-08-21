package Passes.classes;

public class VisitPass {
    protected String applicationID;

    public VisitPass () {
        // empty constructor
    }
    public VisitPass(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }
}
