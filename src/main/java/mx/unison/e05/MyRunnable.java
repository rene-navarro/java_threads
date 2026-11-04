package mx.unison.e05;

import java.util.logging.Logger;

/**
 *
 * @author rnavarro
 */
public class MyRunnable implements Runnable {

    Logger logger;
    public MyRunnable() {
        super();
        logger = Logger.getLogger(MyRunnable.class.getName());
    }

    private boolean doStop = false;

    public synchronized void doStop() {
        this.doStop = true;
    }

    private synchronized boolean keepRunning() {
        return this.doStop == false;
    }

    @Override
    public void run() {
        logger.info("STARTED");
        while ( keepRunning() ) {
            // keep doing what this thread should do.
            System.out.println("Running");
            try {
                // pausar 3 segundos
                logger.info("SLEEPS");
                Thread.sleep(3L * 1000L);
                logger.info("WAKES");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stopped");
        logger.info("STOPPING");
    }
}
