package mx.unison.future_task;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ParallelExecutionDemo {
    public static void main(String[] args) {
        Instant begin = Instant.now();
        System.out.println("Starting parallel execution ....");

        // Create a new ExecutorService with 2 thread to execute and store the Futures
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<FutureTask<Long>> tasks = new ArrayList<>();

        long resultFuture = 0;
        switch (args[0]) {
            case "executeViaFutureTask":
                resultFuture = executeViaFutureTask(executor, tasks);
                break;
            case "submitViaFutureTask":
                resultFuture = submitViaFutureTask(executor, tasks);
                break;
            case "submitViaCallableTask":
                resultFuture = submitViaCallableTask(executor);
                break;
        }

        // Shutdown the ExecutorService
        executor.shutdown();
        Instant end = Instant.now();

        System.out.println(
                "Result (Future): " + resultFuture + " time= " + Duration.between(begin, end).toMillis() + " ms");

    }

    /**
     * Result (Future): 1000000001 time= 45612 ms Result (Future): 1000000001 time=
     * 35592 ms
     */
    private static long executeViaFutureTask(ExecutorService executor, List<FutureTask<Long>> tasks) {
        for (CountTaskData td : CountTaskData.createTwoTasks()) {
            FutureTask<Long> futureTask = new FutureTask<>(
                    new CallableCountTask(td.getBeginNumber(), td.getEndNumber(), CountTaskData.DIVISOR));
            tasks.add(futureTask);
            executor.execute(futureTask);
        }

        return getConcurrentResult(tasks);
    }

    /**
     * Result (Future): 1000000001 time= 33320 ms
     */
    private static long submitViaFutureTask(ExecutorService executor, List<FutureTask<Long>> tasks) {
        for (CountTaskData td : CountTaskData.createTwoTasks()) {
            FutureTask<Long> futureTask = new FutureTask<>(
                    new CallableCountTask(td.getBeginNumber(), td.getEndNumber(), CountTaskData.DIVISOR));
            tasks.add(futureTask);
            executor.submit(futureTask);
        }

        return getConcurrentResult(tasks);
    }

    private static long getConcurrentResult(List<FutureTask<Long>> tasks) {
        long resultFuture = 0;

        // Wait until all results are available and combine them at the same time
        for (FutureTask<Long> futureTask : tasks) {
            try {
                resultFuture += futureTask.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return resultFuture;
    }

    /**
     * Result (Future): 1000000001 time= 32490 ms
     */
    private static long submitViaCallableTask(ExecutorService executor) {
        long resultFuture = 0;
        List<Future<Long>> taskList = new ArrayList<>();

        for (CountTaskData td : CountTaskData.createTwoTasks()) {
            Future<Long> ret = executor
                    .submit(new CallableCountTask(td.getBeginNumber(), td.getEndNumber(), CountTaskData.DIVISOR));
            taskList.add(ret);
        }

        // Wait until all results are available and combine them at the same time
        for (Future<Long> futureTask : taskList) {
            try {
                resultFuture += futureTask.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return resultFuture;
    }
}
