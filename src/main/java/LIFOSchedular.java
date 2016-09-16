import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class LIFOSchedular implements TaskSchedular {

    List<Task> taskList;
    Map<Integer, HashSet<UUID>> taskGroupsBasedOnPriority;
    final int queueCapacity;

    LIFOSchedular(int queueCapacity){
        this.taskList = new ArrayList<Task>(queueCapacity);
        this.queueCapacity = queueCapacity;
        this.taskGroupsBasedOnPriority = new HashMap<Integer, HashSet<UUID>>();
    }

    public void addNewTaskToQueueAndMap(Task task) {

        if( taskList.size() < queueCapacity) {
            taskList.add(task);

            addTaskToTaskMapBasedOnPriority(task);

            System.out.println("New task added to Task List.\n");
            printTaskQueue();
        }
        else
            System.out.println("Error : Can't add new task. Task List is full.\n");
    }

    public void printTaskQueue() {

        System.out.println("Tasks currently present in Task List :\n");

        for (Task task : taskList) {
            System.out.println("Task Id :" + String.valueOf(task.getTaskId()) + ", Priority :" + String.valueOf(task.getTaskPriority())+"\n");
        }
    }

    public Task fetchTaskFromQueueForExecution() {

        if(!taskList.isEmpty()){
            Task task = taskList.get(taskList.size() - 1);
            taskList.remove(taskList.size() - 1);
            return task;
        }

        throw new IllegalArgumentException("Task List is Empty");
    }


    public void addTaskToTaskMapBasedOnPriority(Task task){

        if(taskGroupsBasedOnPriority.containsKey(task.getTaskPriority())){
            taskGroupsBasedOnPriority.get(task.getTaskPriority()).add(task.getTaskId());
        }else{

            HashSet<UUID> taskSetWithSamePriority = new HashSet<UUID>();
            taskSetWithSamePriority.add(task.getTaskId());
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

            Iterator<UUID> setIterator = taskGroupsBasedOnPriority.get(taskPriority).iterator();

            while (setIterator.hasNext()){
                UUID taskId = setIterator.next();
                System.out.println(" Task Id : " + taskId + "\n");
            }

            System.out.println("=============================================\n\n");
        }
    }
}
