
public interface TaskSchedular {
    void addNewTaskToQueueAndMap(Task task);
    void printTaskQueue();
    Task fetchTaskFromQueueForExecution();
    void printTaskGroupsBasedOnPriority();
}
