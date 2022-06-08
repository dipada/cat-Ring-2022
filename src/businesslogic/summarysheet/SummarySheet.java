package businesslogic.summarysheet;

import businesslogic.event.ServiceInfo;
import businesslogic.menu.MenuItem;
import businesslogic.menu.Section;
import businesslogic.recipe.Recipe;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SummarySheet {

    private int id;
    private boolean inUse;
    private ArrayList<Task> tasks;
    private ArrayList<Recipe> recipes;
    private ServiceInfo service;

    public SummarySheet(ServiceInfo service){
        this.service = service;
        tasks = new ArrayList<>();
        recipes = new ArrayList<>();
        recipes.addAll(this.service.getMenuInUse().getAllMenuRecipes());
    }

    public String toString() {
        return "SummarySheet:\n"+ service.toString() + (inUse ? " " : " non ") +
                "in uso.\nRecipes: " +recipes.toString() + "Tasks: " + tasks.toString();
    }

    public boolean inUse() {
        return this.inUse;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task tasks){
        this.tasks.add(tasks);
    }

    public Task addTaskToSummarySheet(Recipe recipe) {
        Task t = new Task();
        t.setRecipe(recipe);
        this.addTask(t);
        return t;
    }

    public void deleteTask(Task task){
        this.tasks.remove(task);
    }

    public boolean hasTask(Task task) {
        return this.tasks.contains(task);
    }

    public int getTaskPosition(Task t) {
        return this.tasks.indexOf(t);
    }

    // STATIC METHODS FOR PERSISTENCE

    public static void saveNewSummarySheet(SummarySheet sm) {
        String summarySheetInsert = "INSERT INTO catering.summarysheet (serviceid) VALUES (?);";

        PersistenceManager.executeBatchUpdate(summarySheetInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, sm.service.getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                if (count == 0) {
                    sm.id = rs.getInt(1);
                }
            }
        });
    }

    public static void removeAllTasks(SummarySheet sm) {
        // delete tasks
        String delTasks = "DELETE FROM summarysheettasks WHERE idsummarysheet = " + sm.id;
        PersistenceManager.executeUpdate(delTasks);
    }

    public int taskSize() {
        return this.tasks.size();
    }

    public void orderTask(Task task, int pos) {
        this.tasks.remove(task);
        this.tasks.add(pos, task);
    }
}
