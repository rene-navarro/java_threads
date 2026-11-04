package mx.unison.e03;

/**
 *
 * @author rnavarro
 */
class HelloRunner implements Runnable {
    public static final int MAX = 50;

    // la variable contador esta accesible pora cualquier thread
    private int contador;


    HelloRunner() {
        super();
        contador = 0;
    }


    public void run() {
        System.out.printf("Inicia thread: %s\n", Thread.currentThread().getName());

        while (true) {
            String name = Thread.currentThread().getName();

            contador++;

            System.out.printf("%s: Hello %d\n",name, contador);

            if (contador == MAX) {
                // terminar el ciclo y por lo tanto terminar la ejecución del thread
                break;
            }
        }
        System.out.printf("Termina thread: %s\n", Thread.currentThread().getName());
    }

    public static void main(String args[]) {
        HelloRunner runner = new HelloRunner();

        System.out.println("main: inicio");
        Thread t = new Thread( runner );
        
        t.start();
        System.out.println("main: fin");

    }
}
