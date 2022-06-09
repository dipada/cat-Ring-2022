package businesslogic.shift;

import businesslogic.summarysheet.Task;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Shift {

    private int id;

    public Shift(int id) {
        this.id=id;
    }

    public static ArrayList<Shift> shifts = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                '}';
    }


// STATIC METHODS FOR PERSISTENCE

    public static void loadAllShifts() {
        String query = "SELECT * FROM shifts";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                shifts.add(new Shift(id));
            }
        });

    }

    public static ArrayList<Shift> getShifts() {
        return shifts;
    }

}
