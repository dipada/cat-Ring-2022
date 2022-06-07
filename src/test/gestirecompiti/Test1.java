package test.gestirecompiti;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.menu.Menu;
import businesslogic.summarysheet.SummarySheet;
import businesslogic.event.ServiceInfo;

public class Test1 {
    public static void main(String[] args) {
        try {
            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Marinella");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            System.out.println("\nTEST CREATE SummarySheet");
            Menu m = CatERing.getInstance().getMenuManager().createMenu("Menu Pinco Pallino");
            ServiceInfo s=new ServiceInfo("Coffee break mattino");
            s.setMenuInUse(m);
            SummarySheet f = CatERing.getInstance().getSummarySheetManager().generateSummarySheet(s);

            System.out.println(f);
        }
        catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
