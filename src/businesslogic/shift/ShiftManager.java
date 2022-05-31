package businesslogic.shift;

import businesslogic.cook.Cook;
import businesslogic.summarysheet.Task;

import java.util.ArrayList;

public class ShiftManager {

    private ArrayList<Shift> shifts;

    public ArrayList<Shift> getShifts() { //TODO?
        return Shift.loadAllShifts();
    }

    //********assignTask*********
    public void assignTask(Task task, Shift shift, String estimatedTime, String quantity) {
        assignTask(task, shift, null, estimatedTime, quantity);  //primo
    }

    public void assignTask(Task task, Shift shift, String quantity, Cook cook) {
        assignTask(task, shift, cook, null, quantity);  //secondo
    }

    public void assignTask(Task task, Shift shift, Cook cook, String estimatedTime){
        assignTask(task, shift, cook, estimatedTime, null);  //terzo
    }

    public void assignTask(Task task, Shift shift, String quantity) {
        assignTask(task, shift, null, null, quantity);  //primo - secondo
    }

    public void assignTask(Task task, String estimatedTime, Shift shift){
        assignTask(task, shift, null, estimatedTime, null);  //primo - terzo
    }

    public void assignTask(Task task, Shift shift, Cook cook) {
        assignTask(task, shift, cook, null, null);  //secondo - terzo
    }

    public void assignTask(Task task, Shift shift) {
        assignTask(task, shift, null, null, null);  //primo - secondo - terzo
    }

    public void assignTask(Task task, Shift shift, Cook cook, String estimatedTime, String quantity) {
        //TODO implementazione
    }
    //******************************


    //******modifyAssigment*********
    public void modifyAssigment(Task task, Shift shift, Cook cook, String estimatedTime, String doses) {
        //TODO implementazione
    }

    public void modifyAssigment(Task task, Cook cook, String estimatedTime, String doses) {
        modifyAssigment(task, null, cook, estimatedTime, doses);  //primo
    }

    public void modifyAssigment(Task task, Shift shift, String estimatedTime, String doses) {
        modifyAssigment(task, shift, null, estimatedTime, doses);  //secondo
    }

    public void modifyAssigment(Task task, Shift shift, Cook cook, String doses) {
        modifyAssigment(task, shift, cook, null, doses);  //terzo
    }

    public void modifyAssigment(Task task, Shift shift, String estimatedTime, Cook cook) {
        modifyAssigment(task, shift, cook, estimatedTime, null); //quarto
    }

    public void modifyAssigment(Task task, String estimatedTime, String doses) {
        modifyAssigment(task, null, null, estimatedTime, doses);  //primo - secondo
    }

    public void modifyAssigment(Task task, Cook cook, String doses) {
        modifyAssigment(task, null, cook, null, doses);  //primo - terzo
    }

    public void modifyAssigment(Task task, String estimatedTime, Cook cook) {
        modifyAssigment(task, null, cook, estimatedTime, null);  //primo - quarto
    }

    public void modifyAssigment(Task task, Shift shift, String doses) {
        modifyAssigment(task, shift, null, null, doses);  //secondo - terzo
    }

    public void modifyAssigment(Task task, String estimatedTime, Shift shift) {
        modifyAssigment(task, shift, null, estimatedTime, null);  //secondo - quarto
    }

    public void modifyAssigment(Task task, Shift shift, Cook cook) {
        modifyAssigment(task, shift, cook, null, null);  //terzo - quarto
    }

    public void modifyAssigment(Task task, String doses) {
        modifyAssigment(task, null, null, null, doses);  //primo - secondo - terzo
    }

    public void modifyAssigment(String estimatedTime, Task task) {
        modifyAssigment(task, null, null, estimatedTime, null);  //primo - secondo - quarto
    }

    public void modifyAssigment(Task task, Cook cook) {
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
