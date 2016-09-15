import java.util.ArrayList;
import java.util.List;

/**
 * Created by webonise on 15/9/16.
 */
public class FIFOSchedular implements TaskSchedular {

    List<Task> taskQueue;
    final int QUEUE_CAPACITY;

    FIFOSchedular(int queueCapacity){
        this.taskQueue = new ArrayList<Task>(queueCapacity);
        this.QUEUE_CAPACITY = queueCapacity;
    }

    public void addNewTaskToQueue(Task task) {

        if( taskQueue.size() < QUEUE_CAPACITY){
            taskQueue.add(task);
            System.out.println("New task added to Task Queue.\n");
            printTaskQueue();
        }
        else
            System.out.println("Error : Can't add new task. Task Queue is full.\n");


    }

    public void printTaskQueue() {

        System.out.println("Tasks currently present in Task Queue :\n");

        for (Task task : taskQueue) {
            System.out.println("Task Id :" + String.valueOf(task.getTaskId()) + ", Priority :" + String.valueOf(task.getTaskPriority())+"\n");
        }
    }

    public Task fetchTaskFromQueueForExecution() {

        try{

            if(!taskQueue.isEmpty()){
                Task task = taskQueue.get(0);
                taskQueue.remove(0);
                return task;
            }else {
                System.out.println("Error : Can't fetch task. Task Queue is empty.");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public int getTaskQueueCapacity() {
        return QUEUE_CAPACITY;
    }
}
