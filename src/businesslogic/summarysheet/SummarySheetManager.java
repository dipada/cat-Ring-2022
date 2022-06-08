package businesslogic.summarysheet;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.recipe.Recipe;
import businesslogic.shift.Shift;
import businesslogic.shift.ShiftManager;
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

    public ArrayList<Shift> getShiftBoard() {
        return ShiftManager.getShifts();
    }

    private void setCurrentSummarySheet(SummarySheet summarySheet) {
        this.currentSummarySheet = summarySheet;
    }

    public SummarySheet getCurrentSummarySheet() {
        return this.currentSummarySheet;
    }

    private void notifySummarySheetAdded(SummarySheet summarySheet) {
        for(SummarySheetEventReceiver er : this.eventReceivers){
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
            er.updateTasksRearranged(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet());
        }
    }

    public void addEventReceiver(SummarySheetEventReceiver rec) {
        this.eventReceivers.add(rec);
    }
}
