/**
 * Created by webonise on 14/9/16.
 */
public interface TaskSchedular {

    void addNewTaskToQueue(Task task);
    void printTaskQueue();
    Task fetchTaskFromQueueForExecution();
    int getTaskQueueCapacity();
}
