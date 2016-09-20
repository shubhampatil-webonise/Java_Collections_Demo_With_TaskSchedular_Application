import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PriorityBasedSchedular implements TaskSchedular {

    private final List<Task> taskList;
    private final Map<Integer, TaskSet> taskGroupsBasedOnPriority;
    private final int queueCapacity;

    PriorityBasedSchedular(int queueCapacity) {
        this.taskList = new ArrayList<Task>(queueCapacity);
        this.queueCapacity = queueCapacity;
        this.taskGroupsBasedOnPriority = new HashMap<Integer, TaskSet>();
    }

    @Override
    public void addTask(Task task) {

        if (taskList.size() < queueCapacity) {
            addTaskToList(task);
            sortTaskListBasedOnPriority();
            addTaskToTaskMapBasedOnPriority(task);
            printTaskList();
        } else
            System.out.println("Error : Can't add new task. Task List is full.\n");
    }

    @Override
    public void printTaskList() {

        System.out.println("Tasks currently present in Task List :\n");

        for (Task task : taskList) {
            System.out.println("Task Id :" + String.valueOf(task.getTaskId()) + ", Priority :" + String.valueOf(task.getTaskPriority()) + "\n");
        }
    }

    @Override
    public Task fetchTaskForExecution() {

        if (!taskList.isEmpty()) {
            Task task = taskList.get(0);
            taskList.remove(0);
            return task;
        } else {
            System.out.println("Error : Can't fetch task. Task List is empty.");
        }

        throw new IllegalArgumentException("Task List is Empty");
    }


    private void sortTaskListBasedOnPriority() {
        Collections.sort(taskList);
    }

    private void addTaskToList(Task task) {
        taskList.add(task);
        System.out.println("New task added to Task List.\n");
    }

    private void addTaskToTaskMapBasedOnPriority(Task task) {

        if (taskGroupsBasedOnPriority.containsKey(task.getTaskPriority())) {
            taskGroupsBasedOnPriority.get(task.getTaskPriority()).addToTaskSet(task.getTaskId());
        } else {
            TaskSet taskSetWithSamePriority = new TaskSet();
            taskSetWithSamePriority.addToTaskSet(task.getTaskId());
            taskGroupsBasedOnPriority.put(task.getTaskPriority(), taskSetWithSamePriority);
        }
    }

    @Override
    public void printTaskGroups() {

        if (taskGroupsBasedOnPriority.isEmpty()) {
            System.out.println("Map of Task Groups is empty !\n");
            return;
        }

        Iterator<Integer> mapIterator = taskGroupsBasedOnPriority.keySet().iterator();

        while (mapIterator.hasNext()) {
            Integer taskPriority = mapIterator.next();
            System.out.println("Priority : " + taskPriority + "\n");
            taskGroupsBasedOnPriority.get(taskPriority).printTaskSet();
            System.out.println("=============================================\n\n");
        }
    }
}
