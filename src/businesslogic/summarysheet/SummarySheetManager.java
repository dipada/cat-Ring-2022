package businesslogic.summarysheet;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.recipe.Recipe;
import businesslogic.shift.Shift;
import businesslogic.user.User;

import java.util.ArrayList;

public class SummarySheetManager {

    private SummarySheet currentSummarySheet;
    private ArrayList<SummarySheetEventReceiver> eventReceivers;

    public SummarySheetManager() {
        eventReceivers = new ArrayList<>();
    }

    public SummarySheet generateSummarySheet(ServiceInfo service) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()){
            throw new UseCaseLogicException();
        }
        SummarySheet summarySheet = new SummarySheet(service);
        this.setCurrentSummarySheet(summarySheet);
        this.notifySummarySheetAdded(summarySheet);

        return summarySheet;
    }

    public void openExistenceSummarySheet(SummarySheet summarySheet) throws UseCaseLogicException, SummarySheetException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()){
            throw new UseCaseLogicException();
        }
        if( summarySheet==null || summarySheet.inUse() ){
            throw new SummarySheetException();
        }
        this.setCurrentSummarySheet(summarySheet);

    }

    public void regenerateSummarySheet(SummarySheet summarySheet) throws UseCaseLogicException, SummarySheetException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()){
            throw new UseCaseLogicException();
        }
        if( summarySheet==null || summarySheet.inUse() ){
            throw new SummarySheetException();
        }
        summarySheet.setTasks(new ArrayList<>());
        this.notifySummarySheetRegenerated(summarySheet);
        this.setCurrentSummarySheet(summarySheet);

    }

    public void addTask(Recipe recipe) throws UseCaseLogicException {
        SummarySheet s = CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet();
        if(s == null) {
            throw new UseCaseLogicException();
        }
        Task t = s.addTaskToSummarySheet(recipe);

        this.notifyTaskAdded(t);

    }

    public void deleteTask(Task task) throws UseCaseLogicException {
        SummarySheet s = CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet();
        if(s == null || !s.hasTask(task)) {
            throw new UseCaseLogicException();
        }
        s.deleteTask(task);
        this.notifyTaskDeleted(task);
    }

    public void orderTasks(Task task, int pos) throws UseCaseLogicException {
        SummarySheet s = CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet();
        if(s == null || !s.hasTask(task)) {
            throw new UseCaseLogicException();
        }
        if(pos >= 0 && pos < s.taskSize()) {
            s.orderTask(task, pos);
            this.notifyTaskRearranged(s);
        }
    }

    public void assignTask(Task task, Shift shift, String estimatedTime, String doses) throws UseCaseLogicException, SummarySheetException {
        assignTask(task, shift, null, estimatedTime, doses);  //primo
    }

    public void assignTask(Task task, Shift shift, String doses, User cook) throws UseCaseLogicException, SummarySheetException {
        assignTask(task, shift, cook, null, doses);  //secondo
    }

    public void assignTask(Task task, Shift shift, User cook, String estimatedTime) throws UseCaseLogicException, SummarySheetException {
        assignTask(task, shift, cook, estimatedTime, null);  //terzo
    }

    public void assignTask(Task task, Shift shift, String doses) throws UseCaseLogicException, SummarySheetException {
        assignTask(task, shift, null, null, doses);  //primo - secondo
    }

    public void assignTask(Task task, String estimatedTime, Shift shift) throws UseCaseLogicException, SummarySheetException {
        assignTask(task, shift, null, estimatedTime, null);  //primo - terzo
    }

    public void assignTask(Task task, Shift shift, User cook) throws UseCaseLogicException, SummarySheetException {
        assignTask(task, shift, cook, null, null);  //secondo - terzo
    }

    public void assignTask(Task task, Shift shift) throws UseCaseLogicException, SummarySheetException {
        assignTask(task, shift, null, null, null);  //primo - secondo - terzo
    }

    public void assignTask(Task task, Shift shift, User cook, String estimatedTime, String doses) throws UseCaseLogicException, SummarySheetException {
        SummarySheet s = CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet();
        if(s == null || !s.hasTask(task) || task.isAssigned()) {
            throw new UseCaseLogicException();
        }
        if(!cook.isCook() || !cook.cookIsAvailable(shift) || s.inUse()){
            throw new SummarySheetException();
        }
        CatERing.getInstance().getShiftManager().assignTask(task,shift, cook, estimatedTime, doses);
        task.setAssigned(true);
        notifyTaskAssigned(task);

    }

    public void modifyAssigment(Task task, User cook, String estimatedTime, String doses) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, null, cook, estimatedTime, doses);  //primo
    }

    public void modifyAssigment(Task task, Shift shift, String estimatedTime, String doses) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, shift, null, estimatedTime, doses);  //secondo
    }

    public void modifyAssigment(Task task, Shift shift, User cook, String doses) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, shift, cook, null, doses);  //terzo
    }

    public void modifyAssigment(Task task, Shift shift, String estimatedTime, User cook) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, shift, cook, estimatedTime, null); //quarto
    }

    public void modifyAssigment(Task task, String estimatedTime, String doses) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, null, null, estimatedTime, doses);  //primo - secondo
    }

    public void modifyAssigment(Task task, User cook, String doses) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, null, cook, null, doses);  //primo - terzo
    }

    public void modifyAssigment(Task task, String estimatedTime, User cook) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, null, cook, estimatedTime, null);  //primo - quarto
    }

    public void modifyAssigment(Task task, Shift shift, String doses) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, shift, null, null, doses);  //secondo - terzo
    }

    public void modifyAssigment(Task task, String estimatedTime, Shift shift) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, shift, null, estimatedTime, null);  //secondo - quarto
    }

    public void modifyAssigment(Task task, Shift shift, User cook) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, shift, cook, null, null);  //terzo - quarto
    }

    public void modifyAssigment(Task task, String doses) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, null, null, null, doses);  //primo - secondo - terzo
    }

    public void modifyAssigment(String estimatedTime, Task task) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, null, null, estimatedTime, null);  //primo - secondo - quarto
    }

    public void modifyAssigment(Task task, User cook) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, null, cook, null, null);  //primo - terzo - quarto
    }

    public void modifyAssigment(Task task, Shift shift) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, shift, null, null, null);  //secondo - terzo - quarto
    }

    public void modifyAssigment(Task task) throws UseCaseLogicException, SummarySheetException {
        modifyAssigment(task, null, null, null, null);  //primo - secondo - terzo - quarto
    }

    public void modifyAssigment(Task task, Shift shift, User cook, String estimatedTime, String doses) throws UseCaseLogicException, SummarySheetException {
        SummarySheet s = CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet();
        if(s == null || !s.hasTask(task) || !task.isAssigned()) {
            throw new UseCaseLogicException();
        }
        if(cook!=null){
            if(!cook.isCook() || !cook.cookIsAvailable(shift) ){
                throw new SummarySheetException();
            }
        }
        if(s.inUse() ){
            throw new SummarySheetException();
        }
        CatERing.getInstance().getShiftManager().modifyAssigment(task,shift, cook, estimatedTime, doses);
        notifyTaskModified(task);
    }

    public void deleteAssignment(Task task) throws UseCaseLogicException, SummarySheetException {
        SummarySheet s = CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet();
        if(s == null || !s.hasTask(task) || !task.isAssigned() ) {
            throw new UseCaseLogicException();
        }
        if(s.inUse()){
            throw new SummarySheetException();
        }
        CatERing.getInstance().getShiftManager().deleteAssignment(task);
        task.setAssigned(false);
        notifyAssignmentTaskDeleted(task);
    }

    public void taskIsReady(Task task) throws UseCaseLogicException {
        SummarySheet s = CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet();
        if(s == null || !s.hasTask(task)) {
            throw new UseCaseLogicException();
        }
        task.setReady(true);

        notifyTaskIsReady(task);
    }

    private void setCurrentSummarySheet(SummarySheet summarySheet) {
        this.currentSummarySheet = summarySheet;
    }

    public SummarySheet getCurrentSummarySheet() {
        return this.currentSummarySheet;
    }

    private void notifySummarySheetAdded(SummarySheet summarySheet) {
        for(SummarySheetEventReceiver er : this.eventReceivers) {
            er.updateSummarySheetGenerated(summarySheet);
        }
    }

    private void notifySummarySheetRegenerated(SummarySheet summarySheet) {
        for(SummarySheetEventReceiver er : this.eventReceivers) {
            er.updateSummarySheetRegenerated(summarySheet);
        }
    }

    private void notifyTaskAdded(Task t) {
        for(SummarySheetEventReceiver er : this.eventReceivers) {
            er.updateTaskAdded(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet(), t);
        }
    }

    private void notifyTaskDeleted(Task task) {
        for(SummarySheetEventReceiver er : this.eventReceivers) {
            er.updateTaskDeleted(task);
        }
    }

    private void notifyTaskRearranged(SummarySheet s) {
        for(SummarySheetEventReceiver er : this.eventReceivers) {
            er.updateTasksRearranged(s);
        }
    }

    private void notifyTaskAssigned(Task task) {
        for(SummarySheetEventReceiver er : this.eventReceivers) {
            er.updateTaskAssigned(task);
        }
    }

    private void notifyTaskIsReady(Task task) {
        for(SummarySheetEventReceiver er : this.eventReceivers) {
            er.updateTaskIsReady(task);
        }
    }

    private void notifyTaskModified(Task task) {
        for(SummarySheetEventReceiver er : this.eventReceivers) {
            er.updateTaskModified(task);
        }
    }

    private void notifyAssignmentTaskDeleted(Task task) {
        for(SummarySheetEventReceiver er : this.eventReceivers) {
            er.updateAssignmentTaskDeleted(task);
        }
    }

    public void addEventReceiver(SummarySheetEventReceiver rec) {
        this.eventReceivers.add(rec);
    }
}
