package mx.unison.e06;

import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author rnavarro
 */
class HelloSlowRunner implements Runnable {
    public static final String TAG = HelloSlowRunner.class.getSimpleName();
    public static final int MAX = 50;
    public static final long WAIT_TIME = 3;

    public static final Logger LOGGER = Logger.getLogger(TAG);

    public HelloSlowRunner(int max) {
        maxValue = max;
    }

    public HelloSlowRunner() {
        maxValue = MAX;
    }

    private int i;
    private int maxValue;

    public void run() {
        i = 0;
        String name = Thread.currentThread().getName();

        long milis = 0L;
        long delta = 0L;

        while (true) {
            delta = System.currentTimeMillis();

            System.out.printf("%s: Hello %d (%d)\n", name, i++, delta - milis);
            milis = delta;

            if (i > maxValue) {
                break;
            }
            try {
                // pausar ejecución
                Thread.sleep(WAIT_TIME * 1000L);
            } catch (InterruptedException ex) {
                LOGGER.severe(ex.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        String name = Thread.currentThread().getName();
        System.out.printf("Start: %s\n" , name);
        Thread t = new Thread(new HelloSlowRunner());
        t.start();
        System.out.printf("Finish: %s\n" , name);
    }
}
