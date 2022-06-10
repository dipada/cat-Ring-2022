package businesslogic.shift;

import businesslogic.summarysheet.Task;
import businesslogic.user.User;

import java.util.ArrayList;

public class ShiftManager {

    public ShiftManager() {
         Shift.loadAllShifts();
    }

    public ArrayList<Shift> getShifts(){
        return Shift.getShifts();
    }

    public void assignTask(Task task, Shift shift, User cook, String estimatedTime, String doses) {

        shift.getTasks().add(task);
        task.setTakesPlaceIn(shift);
        task.setCook(cook);
        task.setDoses(doses);
        task.setEstimatedTime(estimatedTime);

    }

    public void modifyAssigment(Task task, Shift shift, User cook, String estimatedTime, String doses) {
        if(shift!=null){
            shift.getTasks().add(task);
            task.setTakesPlaceIn(shift);
        }
        if(cook!=null){
            task.setCook(cook);
        }
        if(doses!=null){
           task.setDoses(doses);
        }
        if(estimatedTime!=null){
            task.setEstimatedTime(estimatedTime);
        }

    }

    public void deleteAssignment(Task task) {
        task.getTakesPlaceIn().getTasks().remove(task);
        task.setTakesPlaceIn(null);
        task.setCook(null);
        task.setDoses(null);
        task.setEstimatedTime(null);
    }
}
