package mx.unison.e01;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Logger;

/**
 *
 * @author rnavarro
 */
public class SimpleThread implements Runnable {
    public static final String TAG = SimpleThread.class.getSimpleName();
    public static final Logger LOGGER = Logger.getLogger(TAG);

    public void run() {
        String name = Thread.currentThread().getName();
        LOGGER.info( name );
        System.out.printf("Hello from a thread: %s" , name);
    }

    public static void main(String args[]) {

        System.out.printf("main: inicio %s\n", Thread.currentThread().getName());
        // crear el objeto thread
        Thread t = new Thread( new SimpleThread(),"Hello" );
        // iniciar la ejecución del thread
        t.start();
        System.out.printf("main: fin %s\n", Thread.currentThread().getName());
    }
}
