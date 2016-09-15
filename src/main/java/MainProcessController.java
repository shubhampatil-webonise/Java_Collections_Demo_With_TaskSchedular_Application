import java.util.*;

/**
 * Created by webonise on 15/9/16.
 */
public class MainProcessController {

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        SchedularFactory schedularFactory = null;
        TaskSchedular taskSchedular = null;

        try{
            schedularFactory = new SchedularFactory();

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Select a schedular ...\n");
        System.out.println("1. FIFO Schedular (Enter FIFO) \n");
        System.out.println("2. LIFO Schedular (Enter LIFO) \n");
        System.out.println("3. Priority Based Schedular (Enter PRIORITY_BASED) \n");
        System.out.println("Input :");

        String typeOfSchedular = in.next();

        System.out.println("Enter Task Queue size : ");
        int queueCapacity = in.nextInt();


        SchedularType schedular = null;

        try{

            schedular = SchedularType.getSchedularBasedOnString(typeOfSchedular);

            switch (schedular){
                case FIFO:
                    taskSchedular = schedularFactory.getTaskSchedular(SchedularType.FIFO, queueCapacity);
                    break;

                case LIFO:
                    taskSchedular = schedularFactory.getTaskSchedular(SchedularType.LIFO, queueCapacity);
                    break;

                case PRIORITY_BASED:
                    taskSchedular = schedularFactory.getTaskSchedular(SchedularType.PRIORITY_BASED, queueCapacity);
                    break;

                default:
                    System.out.println("Wrong choice !\n");
                    break;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("New " + schedular.name() + " schedular created !\n");

        runFunctionalitiesOfSelectedSchedular(taskSchedular);

    }

    public static void runFunctionalitiesOfSelectedSchedular(TaskSchedular taskSchedular){

        Scanner in = new Scanner(System.in);

        while(true){


            System.out.println("1. Add new Task to queue.\n");
            System.out.println("2. Print current task queue.\n");
            System.out.println("3. Fetch task from Queue to Execute.\n");
            System.out.println("Input :");

            int choice = in.nextInt();

            try{
                switch (choice){
                    case 1 :
                        taskSchedular.addNewTaskToQueue(new Task(MainProcessController.randomPriorityGenerator()));
                        break;

                    case 2 :
                        taskSchedular.printTaskQueue();
                        break;

                    case 3 :
                        Task task = taskSchedular.fetchTaskFromQueueForExecution();
                        task.execute();
                        break;

                    default:
                        System.out.println("Wrong choice !\n");
                        break;
                }

            }catch (Exception e){
                System.out.println("Something went wrong ! May be Task Queue is empty.");
            }
        }
    }

    public static int randomPriorityGenerator(){
        Random random = new Random();

        int maxPriority = 20;
        int minPriority = 0;

        return random.nextInt((maxPriority - minPriority) + 1) + minPriority;
    }
}
