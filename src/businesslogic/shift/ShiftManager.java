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


    //******modifyAssigment*********
    public void modifyAssigment(Task task, Shift shift, User cook, String estimatedTime, String doses) {
        //TODO implementazione
    }

    public void modifyAssigment(Task task, User cook, String estimatedTime, String doses) {
        modifyAssigment(task, null, cook, estimatedTime, doses);  //primo
    }

    public void modifyAssigment(Task task, Shift shift, String estimatedTime, String doses) {
        modifyAssigment(task, shift, null, estimatedTime, doses);  //secondo
    }

    public void modifyAssigment(Task task, Shift shift, User cook, String doses) {
        modifyAssigment(task, shift, cook, null, doses);  //terzo
    }

    public void modifyAssigment(Task task, Shift shift, String estimatedTime, User cook) {
        modifyAssigment(task, shift, cook, estimatedTime, null); //quarto
    }

    public void modifyAssigment(Task task, String estimatedTime, String doses) {
        modifyAssigment(task, null, null, estimatedTime, doses);  //primo - secondo
    }

    public void modifyAssigment(Task task, User cook, String doses) {
        modifyAssigment(task, null, cook, null, doses);  //primo - terzo
    }

    public void modifyAssigment(Task task, String estimatedTime, User cook) {
        modifyAssigment(task, null, cook, estimatedTime, null);  //primo - quarto
    }

    public void modifyAssigment(Task task, Shift shift, String doses) {
        modifyAssigment(task, shift, null, null, doses);  //secondo - terzo
    }

    public void modifyAssigment(Task task, String estimatedTime, Shift shift) {
        modifyAssigment(task, shift, null, estimatedTime, null);  //secondo - quarto
    }

    public void modifyAssigment(Task task, Shift shift, User cook) {
        modifyAssigment(task, shift, cook, null, null);  //terzo - quarto
    }

    public void modifyAssigment(Task task, String doses) {
        modifyAssigment(task, null, null, null, doses);  //primo - secondo - terzo
    }

    public void modifyAssigment(String estimatedTime, Task task) {
        modifyAssigment(task, null, null, estimatedTime, null);  //primo - secondo - quarto
    }

    public void modifyAssigment(Task task, User cook) {
        modifyAssigment(task, null, cook, null, null);  //primo - terzo - quarto
    }

    public void modifyAssigment(Task task, Shift shift) {
        modifyAssigment(task, shift, null, null, null);  //secondo - terzo - quarto
    }

    public void modifyAssigment(Task task) {
        modifyAssigment(task, null, null, null, null);  //primo - secondo - terzo - quarto
    }
    //******************************
}
