import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PriorityBasedSchedular implements TaskSchedular {

    private final List<Task> taskList;
    private final Map<Integer, TaskSet> taskGroupsBasedOnPriority;
    private final int queueCapacity;

    PriorityBasedSchedular(int queueCapacity){
        this.taskList = new ArrayList<Task>(queueCapacity);
        this.queueCapacity = queueCapacity;
        this.taskGroupsBasedOnPriority = new HashMap<Integer, TaskSet>();
    }

    @Override
    public void addNewTaskToQueueAndMap(Task task) {

        if( taskList.size() < queueCapacity){
            taskList.add(task);
            sortTaskQueueBasedOnPriority();
            addTaskToTaskMapBasedOnPriority(task);
            System.out.println("New task added to Task List.\n");
            printTaskQueue();
        }
        else
            System.out.println("Error : Can't add new task. Task List is full.\n");
    }

    @Override
    public void printTaskQueue() {

        System.out.println("Tasks currently present in Task List :\n");

        for (Task task : taskList) {
            System.out.println("Task Id :" + String.valueOf(task.getTaskId()) + ", Priority :" + String.valueOf(task.getTaskPriority())+"\n");
        }
    }

    @Override
    public Task fetchTaskFromQueueForExecution() {

        if(!taskList.isEmpty()){
            Task task = taskList.get(0);
            taskList.remove(0);
            return task;
        }else {
            System.out.println("Error : Can't fetch task. Task List is empty.");
        }

        throw new IllegalArgumentException("Task List is Empty");
    }


    private void sortTaskQueueBasedOnPriority(){
        Collections.sort(taskList);
    }

    private void addTaskToTaskMapBasedOnPriority(Task task){

        if(taskGroupsBasedOnPriority.containsKey(task.getTaskPriority())){
            taskGroupsBasedOnPriority.get(task.getTaskPriority()).addToTaskSet(task.getTaskId());
        }else{
            TaskSet taskSetWithSamePriority = new TaskSet();
            taskSetWithSamePriority.addToTaskSet(task.getTaskId());
            taskGroupsBasedOnPriority.put(task.getTaskPriority(), taskSetWithSamePriority);
        }
    }

    @Override
    public void printTaskGroupsBasedOnPriority() {

        if(taskGroupsBasedOnPriority.isEmpty()){
            System.out.println("Map of Task Groups is empty !\n");
            return;
        }

        Iterator<Integer> mapIterator = taskGroupsBasedOnPriority.keySet().iterator();

        while (mapIterator.hasNext()){

            Integer taskPriority = mapIterator.next();
            System.out.println("Priority : " +  taskPriority + "\n");

            Iterator<UUID> setIterator = taskGroupsBasedOnPriority.get(taskPriority).getIterator();

            while (setIterator.hasNext()){
                UUID taskId = setIterator.next();
                System.out.println(" Task Id : " + taskId + "\n");
            }

            System.out.println("=============================================\n\n");
        }
    }
}
