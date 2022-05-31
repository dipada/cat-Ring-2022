package businesslogic.summarysheet;

import businesslogic.recipe.Recipe;
import businesslogic.service.Service;

import javax.sound.midi.Receiver;
import java.rmi.server.ServerCloneException;
import java.util.ArrayList;

public class SummarySheet {
    private boolean inUse;
    private ArrayList<Task> tasks;
    private ArrayList<Recipe> recipes;
    private Service service;

    public SummarySheet(Service service){
        this.service = service;
        recipes = new ArrayList<>();
        recipes.addAll(this.service.getMenuInUse().getAllMenuRecipes());
    }
}
