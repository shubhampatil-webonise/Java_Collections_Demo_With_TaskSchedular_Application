/**
 * Created by webonise on 15/9/16.
 */
public enum SchedularType {

    FIFO, LIFO, PRIORITY_BASED;

    static SchedularType getSchedularBasedOnString(String choice){

        for (SchedularType schedular : SchedularType.values()){
            if(schedular.name().equalsIgnoreCase(choice)) return schedular;
        }
        throw new IllegalArgumentException("Schedular Not found !");
    }
}
