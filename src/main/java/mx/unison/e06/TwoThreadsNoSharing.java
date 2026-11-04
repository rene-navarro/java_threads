package mx.unison.e06;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author rnavarro
 */
public class TwoThreadsNoSharing {

    public static void main(String args[]) {
        
        System.out.println("Main: inicio");

        HelloSlowRunner r1 = new HelloSlowRunner();
        Thread t1 = new Thread(r1);

        HelloSlowRunner r2 = new HelloSlowRunner();
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        System.out.println("Main: fin");
    }

}
