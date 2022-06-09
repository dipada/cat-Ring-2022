package businesslogic;

import businesslogic.event.EventManager;
import businesslogic.menu.Menu;
import businesslogic.menu.MenuManager;
import businesslogic.recipe.RecipeManager;
import businesslogic.shift.ShiftManager;
import businesslogic.summarysheet.SummarySheet;
import businesslogic.summarysheet.SummarySheetManager;
import businesslogic.user.UserManager;
import persistence.MenuPersistence;
import persistence.PersistenceManager;
import persistence.SummarySheetPersistence;

public class CatERing {
    private static CatERing singleInstance;

    public static CatERing getInstance() {
        if (singleInstance == null) {
            singleInstance = new CatERing();
        }
        return singleInstance;
    }

    private MenuManager menuMgr;
    private RecipeManager recipeMgr;
    private UserManager userMgr;
    private EventManager eventMgr;
    private SummarySheetManager summarySheetMgr;
    private ShiftManager shiftMgr;
    private SummarySheetPersistence summarySheetPersistence;
    private MenuPersistence menuPersistence;

    private CatERing() {
        menuMgr = new MenuManager();
        recipeMgr = new RecipeManager();
        userMgr = new UserManager();
        eventMgr = new EventManager();
        summarySheetMgr = new SummarySheetManager();
        shiftMgr = new ShiftManager();
        menuPersistence = new MenuPersistence();
        summarySheetPersistence = new SummarySheetPersistence();
        menuMgr.addEventReceiver(menuPersistence);
        summarySheetMgr.addEventReceiver(summarySheetPersistence);
    }


    public MenuManager getMenuManager() {
        return menuMgr;
    }

    public RecipeManager getRecipeManager() {
        return recipeMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }

    public EventManager getEventManager() { return eventMgr; }

    public SummarySheetManager getSummarySheetManager(){ return summarySheetMgr; }

    public ShiftManager getShiftManager() {
        return shiftMgr;
    }

}
