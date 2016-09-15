import java.util.UUID;


/**
 * Created by webonise on 15/9/16.
 */
public class Task implements Comparable<Task> {

    final UUID taskId;
    int taskPriority;

    Task(int taskPriority){
        this.taskId = UUID.randomUUID();
        this.taskPriority = taskPriority;
    }

    public UUID getTaskId(){
        return taskId;
    }

    public int getTaskPriority(){
        return taskPriority;
    }

    public void execute(){
        System.out.println("Executing Task : " + String.valueOf(taskId));
    }

    @Override
    public int compareTo(Task task) {

        if(this.getTaskPriority() > task.getTaskPriority()){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object taskToCompare) {
        if (this == taskToCompare) return true;
        if (!(taskToCompare instanceof Task)) return false;

        Task task = (Task) taskToCompare;


        if (taskPriority != task.taskPriority) return false;
        return taskId.equals(task.taskId);

    }

    @Override
    public int hashCode() {
        int result = taskId.hashCode();
        result = 31 * result + taskPriority;
        return result;
    }
}
