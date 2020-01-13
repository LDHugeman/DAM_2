   package ejerciciopropuesto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        
        //el hilo primario crea la lista y pasará su referncia a cada hilo lector que arranque
        //lanza tantos hilos lectores como ficheros reciba en los argumentos
        HiloLector hl = null;
        LinkedList<Persona> l = new LinkedList<>();
        for (String s : args) {
            hl = new HiloLector(s, l);
            hl.start();
        }
        //  verificar que  se impleementa "por debajo ,MÉTODO START() IMPLEMENTA UN GRUPO DE HILOS" un grupo de hilos
        
        //el hilo primario implementa un join() referenciado a cada hilo ("grupo de hilos"
        // de modo que,se detiene esperando a que todos sus hilos hijo referenciados finalicen. 
        try {
            hl.join();
        } catch (InterruptedException e) {

        }
        //reanuda su ejecución, recorre lista y la vuelca en disco
        File f = new File("salida.txt");
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(f));
            for (Persona p : l) {
                System.out.println(p);
                ps.println(p);
            }
            ps.close();
        } catch (FileNotFoundException e) {

        }
    }
}
