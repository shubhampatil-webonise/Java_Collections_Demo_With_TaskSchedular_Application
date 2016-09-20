
public interface TaskSchedular {
    void addTask(Task task);

    void printTaskList();

    Task fetchTaskForExecution();

    void printTaskGroups();
}
