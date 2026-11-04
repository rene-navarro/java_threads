package mx.unison.future_task;

public class RunnableTask implements Runnable {

    private long sleepMills;

    public RunnableTask(long sleepMills) {
        super();
        this.sleepMills = sleepMills;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " run starts.");
            Thread.sleep(sleepMills);
            System.out.println(Thread.currentThread().getName() + " run ends.");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        }

    }
}
