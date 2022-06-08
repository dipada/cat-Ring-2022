package persistence;

import businesslogic.summarysheet.SummarySheet;
import businesslogic.summarysheet.SummarySheetEventReceiver;
import businesslogic.summarysheet.Task;

public class SummarySheetPersistence implements SummarySheetEventReceiver {
    @Override
    public void updateTaskAdded(SummarySheet sm, Task t) {
        Task.saveNewTaskInSummarySheet(sm,t, sm.getTaskPosition(t));
    }

    @Override
    public void updateSummarySheetGenerated(SummarySheet sm) {
        SummarySheet.saveNewSummarySheet(sm);
    }

    @Override
    public void updateTaskDeleted(Task t) {
        Task.deleteTaskInSummarySheet(t);
    }


    @Override
    public void updateSummarySheetRegenerated(SummarySheet sm) {
        SummarySheet.removeAllTasks(sm);
    }

    @Override
    public void updateTasksRearranged(SummarySheet sm) {

    }

    @Override
    public void updateTaskAssigned(Task t) {

    }

    @Override
    public void updateTaskModified(Task t) {

    }
}
