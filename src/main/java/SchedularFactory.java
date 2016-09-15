/**
 * Created by webonise on 15/9/16.
 */
public class SchedularFactory {

    public TaskSchedular getTaskSchedular(SchedularType schedular, int queueSize){

        try{
            switch (schedular){
                case FIFO:
                    return new FIFOSchedular(queueSize);

                case LIFO:
                    return new LIFOSchedular(queueSize);

                case PRIORITY_BASED:
                    return new PriorityBasedSchedular(queueSize);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
