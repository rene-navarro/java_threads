package mx.unison.prioridades;

public class NumberThread
{
    private static class Number extends Thread
    {
        int num;

        public Number ( int n )
        {
            num = n;
        }

        public void run ()
        {
            for ( int k = 0; k < 200; k++ )
                System.out.print ( num );
        }
    }

    public static void main ( String[] args )
    {
        Number number1 = new Number(1); number1.setPriority(1);
        Number number2 = new Number(2); number2.setPriority(10);
        Number number3 = new Number(3); number3.setPriority(3);
        Number number4 = new Number(4); number4.setPriority(8);
        Number number5 = new Number(5); number5.setPriority(5);

        number1.start();
        number2.start();
        number3.start();
        number4.start();
        number5.start();
    }
}
