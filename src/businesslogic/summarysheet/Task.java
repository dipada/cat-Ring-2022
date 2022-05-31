package businesslogic.summarysheet;

import businesslogic.cook.Cook;
import businesslogic.shift.Shift;

public class Task {
    private Cook cook;
    private String estimatedTime;
    private String doses;
    private boolean ready;
    private Shift takesPlaceIn;

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setDoses(String doses) {
        this.doses = doses;
    }

    public boolean isAssigned() { //TODO
        return true;
    }

    public void removeCook(Cook cook) { //TODO

    }
}
