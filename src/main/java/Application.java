import java.util.Scanner;

public class Application {

    private Scanner in;
    private SchedularFactory schedularFactory;
    private String typeOfSchedular;
    private TaskSchedular taskSchedular;
    private int queueCapacity;
    private SchedularUtilities schedularUtilities;

    Application(){
        in = new Scanner(System.in);
        schedularFactory = new SchedularFactory();
        schedularUtilities = new SchedularUtilities();
    }

    public void start(){
        displayMainMenuAndGetSchedularType();
        getInputAndSetTaskListCapacity();
        selectTaskSchedularBasedOnType();
        selectSchedularOperation();
    }

    private void displayMainMenuAndGetSchedularType(){

        System.out.println("Select a schedular ...\n");
        System.out.println("1. FIFO Schedular (Enter FIFO) \n");
        System.out.println("2. LIFO Schedular (Enter LIFO) \n");
        System.out.println("3. Priority Based Schedular (Enter PRIORITY_BASED) \n");
        System.out.println("Input :");

        typeOfSchedular = in.next();
    }

    private void getInputAndSetTaskListCapacity(){
        System.out.println("Enter Task Queue size : ");
        queueCapacity = in.nextInt();
    }

    private void selectTaskSchedularBasedOnType(){

        try{

            SchedularType schedular = SchedularType.getSchedularBasedOnString(typeOfSchedular);

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
                    throw new IllegalArgumentException("Schedular Not found !");
            }

            System.out.println("New " + schedular.name() + " schedular created !\n");


        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private void selectSchedularOperation(){

        boolean continueLooping = true;

        while(continueLooping){

            displaySchedularSpecificMenu();
            int choice = in.nextInt();

            try{
                switch (choice){
                    case 1 :
                        taskSchedular.addNewTaskToQueueAndMap(new Task(schedularUtilities.randomPriorityGenerator()));
                        break;

                    case 2 :
                        taskSchedular.printTaskQueue();
                        break;

                    case 3 :
                        Task task = taskSchedular.fetchTaskFromQueueForExecution();
                        task.execute();
                        break;

                    case 4 :
                        taskSchedular.printTaskGroupsBasedOnPriority();
                        break;

                    case 5:
                        continueLooping = false;
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid Choice");
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void displaySchedularSpecificMenu(){

        System.out.println("1. Add new Task to queue.\n");
        System.out.println("2. Print current task queue.\n");
        System.out.println("3. Fetch task from Queue to Execute.\n");
        System.out.println("4. Print task groups based on priority.\n");
        System.out.println("5. Stop Schedular.\n");
        System.out.println("Input :");
    }
}
