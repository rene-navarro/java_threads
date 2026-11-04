package mx.unison.primes;

public class SieveRunner {
    public static void main(String args[]) {

        for( String s: args) {
            int n = Integer.parseInt(s);

            Thread t = new Thread( new EratosthenesSieve(n) );
            t.start();
        }

    }
}
