package test.gestirecompiti;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.menu.Menu;
import businesslogic.summarysheet.SummarySheet;
import businesslogic.event.ServiceInfo;
import businesslogic.summarysheet.SummarySheetException;
import javafx.collections.ObservableList;

public class Test1a {
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

            ServiceInfo s2=CatERing.getInstance().getEventManager().getEventInfo().get(0).getServices().get(1);
            s2.setMenuInUse(ms.get(1));
            SummarySheet f2 = CatERing.getInstance().getSummarySheetManager().generateSummarySheet(s2);

            System.out.println(f);

            CatERing.getInstance().getSummarySheetManager().openExistenceSummarySheet(f2);
            System.out.println(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet());
        }
        catch (UseCaseLogicException | SummarySheetException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
