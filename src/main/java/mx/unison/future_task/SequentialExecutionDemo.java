package mx.unison.future_task;

import java.time.Duration;
import java.time.Instant;
public class SequentialExecutionDemo {
    public static void main(String[] args) {
        // Completed in 46805 ms.
        Instant begin = Instant.now();
        long counts = (new CountService()).countNumberOfDivisible(0, CountTaskData.MAX_NUMBER, CountTaskData.DIVISOR);

        Instant end = Instant.now();
        System.out.println("Result: " + counts + " time=" + Duration.between(begin, end).toMillis() + " ms.");
    }
}
