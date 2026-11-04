package mx.unison.future_task;

import java.util.concurrent.Callable;

public class CallableCountTask implements Callable<Long> {
    private CountService countService = new CountService();
    private long divisor;
    private long first;
    private long last;

    public CallableCountTask(long first, long last, long divisor) {
        this.first = first;
        this.last = last;
        this.divisor = divisor;
    }

    @Override
    public Long call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " call starts.");
        long ret = countService.countNumberOfDivisible(first, last, divisor);
        System.out.println(Thread.currentThread().getName() + " call ends.");
        return ret;
    }
}
