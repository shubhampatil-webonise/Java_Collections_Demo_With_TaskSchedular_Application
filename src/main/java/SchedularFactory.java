
public class SchedularFactory {

    public TaskSchedular getTaskSchedular(SchedularType schedular, int queueSize) {

        switch (schedular) {
            case FIFO:
                return new FIFOSchedular(queueSize);

            case LIFO:
                return new LIFOSchedular(queueSize);

            case PRIORITY_BASED:
                return new PriorityBasedSchedular(queueSize);

            default:
                throw new IllegalArgumentException("Invalid Schedular Type !");
        }
    }
}
