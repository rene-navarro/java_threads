package mx.unison.e03;

/**
 *
 * @author rnavarro
 */
class HelloRunner implements Runnable {
    public static final int MAX = 500;
    
    private int i;

    public void run() {
        i = 0;

        while (true) {
            String name = Thread.currentThread().getName();
            System.out.printf("%s: Hello %d\n",name, i++);
            if (i == MAX) {
                break;
            }
        }
    }

    public static void main(String args[]) {
        HelloRunner runner = new HelloRunner();

        System.out.println("main: inicio");
        Thread t = new Thread( runner );
        
        t.start();
        System.out.println("main: fin");

    }
}
