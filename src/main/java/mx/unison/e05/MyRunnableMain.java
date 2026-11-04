package mx.unison.e05;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Logger;

/**
 *
 * @author rnavarro
 */
public class MyRunnableMain {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(MyRunnableMain.class.getName());

        logger.info("Main start");

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);

        thread.start();
        logger.info("Runnable started");
        try {
            logger.info("Main sleeps");
            // pausar main 15 segundos
            Thread.sleep(15L * 1000L);
            logger.info("Main wakes");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myRunnable.doStop();
        logger.info("Runnable stopped");
    }
}
