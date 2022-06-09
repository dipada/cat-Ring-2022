package businesslogic.summarysheet;

public interface SummarySheetEventReceiver {
    public void updateTaskAdded(SummarySheet sm, Task t);
    public void updateSummarySheetGenerated(SummarySheet sm);
    public void updateTaskDeleted(Task t);
    public void updateSummarySheetRegenerated(SummarySheet sm);
    public void updateTasksRearranged(SummarySheet sm);
    public void updateTaskAssigned(Task t);
    public void updateTaskModified(Task t);
    public void updateTaskIsReady(Task task);
}