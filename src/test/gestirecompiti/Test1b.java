package test.gestirecompiti;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.menu.Menu;
import businesslogic.summarysheet.SummarySheet;
import businesslogic.event.ServiceInfo;
import businesslogic.summarysheet.SummarySheetException;
import businesslogic.summarysheet.Task;
import javafx.collections.ObservableList;

public class Test1b {
    public static void main(String[] args) {
        try {
            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            System.out.println("\nTEST CREATE SummarySheet");
            ObservableList<Menu> ms = CatERing.getInstance().getMenuManager().getAllMenus();
            ServiceInfo s=CatERing.getInstance().getEventManager().getEventInfo().get(0).getServices().get(0);
            s.setMenuInUse(ms.get(0));
            SummarySheet f = CatERing.getInstance().getSummarySheetManager().generateSummarySheet(s);

            CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet().addTask(new Task());

            System.out.println(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet());

            CatERing.getInstance().getSummarySheetManager().regenerateSummarySheet(f);

            System.out.println(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet());

        }
        catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        } catch (SummarySheetException e) {
            e.printStackTrace();
        }
    }
}