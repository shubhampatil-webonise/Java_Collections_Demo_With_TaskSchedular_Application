import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;

public class TaskSet {
    private final HashSet<UUID> setOfTasks;

    TaskSet() {
        setOfTasks = new HashSet<>();
    }

    public void addToTaskSet(UUID taskId) {
        setOfTasks.add(taskId);
    }

    public void printTaskSet() {
        Iterator<UUID> setIterator = setOfTasks.iterator();

        while (setIterator.hasNext()) {
            UUID taskId = setIterator.next();
            System.out.println(" Task Id : " + taskId + "\n");
        }
    }
}
