package mx.unison.e06;

public class MultiRunner {

    public static void main(String args[]) {

        if( args.length == 0) {
            System.out.println("Usage: java MultiRunner <n1> <n2> ... <nk>");
            System.exit(1);
        }

        for( String s: args) {
            int n = Integer.parseInt(s);
            Thread t = new Thread( new HelloSlowRunner(n) );
            t.start();
        }
    }

}
