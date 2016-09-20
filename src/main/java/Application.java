import java.util.Scanner;

public class Application {

    private final Scanner in;
    private final SchedularFactory schedularFactory;
    private final PriorityGenerator priorityGenerator;

    private String typeOfSchedular;
    private TaskSchedular taskSchedular;
    private int queueCapacity;

    Application() {
        in = new Scanner(System.in);
        schedularFactory = new SchedularFactory();
        priorityGenerator = new PriorityGenerator();
    }

    public void start() {
        try {
            displayMainMenuAndGetSchedularType();
            getInputAndSetTaskListCapacity();
            selectTaskSchedularBasedOnType();
            selectSchedularOperation();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayMainMenuAndGetSchedularType() {

        System.out.println("Select a schedular ...\n");
        System.out.println("1. FIFO Schedular (Enter FIFO) \n");
        System.out.println("2. LIFO Schedular (Enter LIFO) \n");
        System.out.println("3. Priority Based Schedular (Enter PRIORITY_BASED) \n");
        System.out.println("Input :");

        typeOfSchedular = in.next();
    }

    private void getInputAndSetTaskListCapacity() {
        System.out.println("Enter Task Queue size : ");
        queueCapacity = in.nextInt();
    }

    private void selectTaskSchedularBasedOnType() {
        try {
            SchedularType schedular = SchedularType.getSchedularBasedOnString(typeOfSchedular);
            taskSchedular = schedularFactory.getTaskSchedular(schedular, queueCapacity);
            System.out.println("New " + schedular.name() + " schedular created !\n");

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void selectSchedularOperation() {

        boolean continueLooping = true;

        while (continueLooping) {

            displaySchedularSpecificMenu();
            int choice = in.nextInt();

            try {
                switch (choice) {
                    case 1:
                        taskSchedular.addTask(new Task(priorityGenerator.generatePriority()));
                        break;

                    case 2:
                        taskSchedular.printTaskList();
                        break;

                    case 3:
                        Task task = taskSchedular.fetchTaskForExecution();
                        task.execute();
                        break;

                    case 4:
                        taskSchedular.printTaskGroups();
                        break;

                    case 5:
                        continueLooping = false;
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid Choice");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void displaySchedularSpecificMenu() {
        System.out.println("1. Add new Task to queue.\n");
        System.out.println("2. Print current task queue.\n");
        System.out.println("3. Fetch task from Queue to Execute.\n");
        System.out.println("4. Print task groups based on priority.\n");
        System.out.println("5. Stop Schedular.\n");
        System.out.println("Input :");
    }
}
