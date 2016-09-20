import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;

public class TaskSet {
    private final HashSet<UUID> setOfTasks;

    TaskSet(){
        setOfTasks = new HashSet<>();
    }

    public void addToTaskSet(UUID taskId){
        setOfTasks.add(taskId);
    }

    public Iterator getIterator(){
        return setOfTasks.iterator();
    }
}
