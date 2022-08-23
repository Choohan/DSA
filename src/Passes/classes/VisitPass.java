package Passes.classes;

import java.time.LocalDateTime;

public class VisitPass {
    protected String applicationID;
    protected LocalDateTime applyTimestamp;

    public VisitPass () {
        // empty constructor
    }
    public VisitPass(String applicationID, LocalDateTime applyTimestamp) {
        this.applicationID = applicationID;
        this.applyTimestamp = applyTimestamp;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public LocalDateTime getApplyTimestamp() {
        return applyTimestamp;
    }

    public void setApplyTimestamp(LocalDateTime applyTimestamp) {
        this.applyTimestamp = applyTimestamp;
    }

    @Override
    public String toString() {
        return String.format("""
                         Pass
                ----------------------
                Application ID :  %s
                """, applicationID);
    }

    public String getTitle() {
        return "Visit Pass";
    }
}
