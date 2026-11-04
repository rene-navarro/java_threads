package mx.unison.prioridades;

public class NumberPrinter
{
    private static class Number implements Runnable
    {
        int num;

        public Number ( int n )
        {
            num = n;
        }

        public void run ()
        {
            for ( int k = 0; k < 200; k++ )
                System.out.println ( num );
        }
    }

    public static void main ( String[] args )
    {
        Thread number1 = new Thread ( new Number(1) );
        number1.setPriority(1);

        Thread number2 = new Thread ( new Number(2) );
        number2.setPriority(10);

        Thread number3 = new Thread ( new Number(3) );
        number3.setPriority(3);

        Thread number4 = new Thread ( new Number(4) );
        number4.setPriority(8);

        Thread number5 = new Thread ( new Number(5) );
        number5.setPriority(5);

        number1.start();
        number2.start();
        number3.start();
        number4.start();
        number5.start();
    }
}
