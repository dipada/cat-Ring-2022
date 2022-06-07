package businesslogic.summarysheet;

import businesslogic.event.ServiceInfo;
import businesslogic.recipe.Recipe;
import java.util.ArrayList;

public class SummarySheet {
    private boolean inUse;
    private ArrayList<Task> tasks;
    private ArrayList<Recipe> recipes;
    private ServiceInfo service;

    public SummarySheet(ServiceInfo service){
        this.service = service;
        recipes = new ArrayList<>();
        recipes.addAll(this.service.getMenuInUse().getAllMenuRecipes());
    }

    public String toString() {
        return "SummarySheet:\n"+ service.toString() + (inUse ? " " : " non ") +
                "in uso.\nRecipes: " +recipes.toString();
    }
}
