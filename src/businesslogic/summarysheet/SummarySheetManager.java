package businesslogic.summarysheet;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
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

    private void notifySummarySheetAdded(SummarySheet summarySheet) {
        for(SummarySheetEventReceiver er : this.eventReceivers){
            er.updateSummarySheetGenerated(summarySheet);
        }
    }

    private void setCurrentSummarySheet(SummarySheet summarySheet) {
        this.currentSummarySheet = summarySheet;
    }
}
