package mx.unison.contador;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ContadorLineas extends SimpleFileVisitor<Path>   {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        String name = file.toAbsolutePath().toString();

        if( name.toLowerCase().endsWith(".txt")) {
            Thread contador = new Thread( new LineCounter(name) );
            contador.start();
        }
        return super.visitFile(file, attrs);
    }



    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.printf("No se puede procesar:%30s%n", file.toString()) ;
        return super.visitFileFailed(file, exc);
    }

    public static void main(String[] args)
            throws IOException {

        // /Users/rnavarro/datos
        if (args.length < 1) {
            System.exit(2);
        }

        // iniciar en este directorio
        Path startingDir = Paths.get(args[0]);

        // clase para procesar los archivos
        ContadorLineas contadorLineas = new ContadorLineas();

        // iniciar el recorrido de los archivos
        Files.walkFileTree(startingDir, contadorLineas);
    }
}
