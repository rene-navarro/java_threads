package mx.unison.e02;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Logger;

/**
 *
 * @author rnavarro
 */
public class HelloThread extends Thread {

    public static final String TAG = HelloThread.class.getSimpleName();
    public static final Logger LOG = Logger.getLogger(TAG);

    public void run() {
        // no hace mucho, nada más imprime hola
        LOG.info("Hello from a thread: " + currentThread().getName());
        System.out.printf("Hello from a thread: %s\n", currentThread().getName());
    }

    public static void main(String args[]) {
        System.out.printf("Inicio: %s\n" , currentThread().getName());
        // crear el objeto thread
        HelloThread t = new HelloThread();

        // iniciar la ejecución del thread
        t.start();
        t.run();
        System.out.printf("Fin:  %s\n" , currentThread().getName());
    }

}
