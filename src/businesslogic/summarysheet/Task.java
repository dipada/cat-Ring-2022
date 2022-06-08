package businesslogic.summarysheet;

import businesslogic.cook.Cook;
import businesslogic.menu.MenuItem;
import businesslogic.recipe.Recipe;
import businesslogic.shift.Shift;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Task {
    private int id;
    private Cook cook;
    private String estimatedTime;
    private String doses;
    private boolean ready;
    private Shift takesPlaceIn;
    private Recipe recipe;

    public int getId(){return this.id;}

    public Recipe getRecipe(){ return this.recipe;}

    public void setRecipe(Recipe recipe){ this.recipe = recipe;}

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setDoses(String doses) {
        this.doses = doses;
    }

    public boolean isAssigned() { //TODO
        return true;
    }

    public void removeCook(Cook cook) { //TODO
}

    @Override
    public String toString() {
        return "Task{" +
                "cook=" + cook +
                ", estimatedTime='" + estimatedTime + '\'' +
                ", doses='" + doses + '\'' +
                ", ready=" + ready +
                ", takesPlaceIn=" + takesPlaceIn +
                ", recipe=" + recipe +
                '}';
    }

    public static void saveNewTaskInSummarySheet(SummarySheet sm, Task t, int posInSummarySheet) {
        String taskInsert = "INSERT INTO catering.tasks (idrecipe, idsummarysheet, ready, positioninsummarysheet) VALUES (?, ?, ?, ?);";

        PersistenceManager.executeBatchUpdate(taskInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, t.getRecipe().getId());
                ps.setInt(2, sm.getId());
                ps.setInt(3,0);
                ps.setInt(4,posInSummarySheet);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                if (count == 0) {
                    t.id = rs.getInt(1);
                }
            }
        });

    }

    public static void deleteTaskInSummarySheet(Task t) {

        String taskDelInSummarySheet = "DELETE FROM tasks WHERE id = " + t.getId();
        PersistenceManager.executeUpdate(taskDelInSummarySheet);


    }
}
