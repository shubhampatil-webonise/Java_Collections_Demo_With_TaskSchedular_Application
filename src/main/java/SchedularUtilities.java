import java.util.Random;

public class SchedularUtilities {

    public int randomPriorityGenerator(){
        Random random = new Random();

        int maxPriority = 20;
        int minPriority = 0;

        return random.nextInt((maxPriority - minPriority) + 1) + minPriority;
    }
}
