package test.gestirecompiti;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.menu.Menu;
import businesslogic.summarysheet.SummarySheet;
import javafx.collections.ObservableList;

public class Test2 {
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

            System.out.println(f);

            System.out.println("\nTEST add task SummarySheet");
            CatERing.getInstance().getSummarySheetManager().addTask(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet().getRecipes().get(0));
            System.out.println(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet());

            System.out.println("\nTEST delete task SummarySheet");
            CatERing.getInstance().getSummarySheetManager().deleteTask(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet().getTasks().get(0));
            System.out.println(CatERing.getInstance().getSummarySheetManager().getCurrentSummarySheet());

        }
        catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
