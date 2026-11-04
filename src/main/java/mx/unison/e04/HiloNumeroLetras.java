package mx.unison.e04;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rnavarro
 */
public class HiloNumeroLetras implements Runnable {
    
    private int tipo;
   
    public HiloNumeroLetras(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public void run() {
        //ciclo infinito
        while (true) {
            //Segun el tipo hace una u otra cosa
            switch (tipo) {
                case 1:
                    //numeros
                    for (int i = 1; i < 30; i++) {
                        System.out.println(i);
                    }
                    break;
                case 2:
                    //letras
                    for (char c = 'a'; c < 'z'; c++) {
                        System.out.println(c);
                    }
                    break;
            }
        }
    }
    
    public static void main(String args[]) {
        HiloNumeroLetras numeros = new HiloNumeroLetras(1);
        HiloNumeroLetras letras = new HiloNumeroLetras(2);

        Thread t1 =  new Thread( numeros );
        Thread t2  = new Thread ( letras );
        
        t1.start();
        t2.start();
    }
}
