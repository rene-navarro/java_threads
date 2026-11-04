package mx.unison.future_task;
import java.util.ArrayList;
import java.util.List;
public class CountTaskData {
    // DIVISOR to be used in calculation
    public static final long DIVISOR = 3;

    // Maximum number to check
    public static final long MAX_NUMBER = 3000000000l;

    public static List<CountTaskData> createTwoTasks() {
        List<CountTaskData> tasks = new ArrayList<>();

        tasks.add(new CountTaskData(0, MAX_NUMBER / 2));
        tasks.add(new CountTaskData(MAX_NUMBER / 2 + 1, MAX_NUMBER));

        return tasks;
    }

    private long beginNumber;
    private long endNumber;

    public CountTaskData(long beginNumber, long endNumber) {
        super();
        this.beginNumber = beginNumber;
        this.endNumber = endNumber;
    }

    public long getBeginNumber() {
        return beginNumber;
    }

    public long getEndNumber() {
        return endNumber;
    }
}
