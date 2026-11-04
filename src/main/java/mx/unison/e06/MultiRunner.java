package mx.unison.e06;

public class MultiRunner {

    public static void main(String args[]) {

        for( String s: args) {
            int n = Integer.parseInt(s);
            Thread t = new Thread( new HelloSlowRunner(n) );
            t.start();
        }
    }

}
