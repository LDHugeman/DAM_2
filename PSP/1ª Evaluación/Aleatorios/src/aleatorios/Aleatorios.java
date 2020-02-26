    
package aleatorios;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
// probamos crear un proceso hijo, comunicarnos con él E/S y finalizar su ejecución
public class Aleatorios {

    
    public static void main(String[] args) {
        aplicacion(args);
    }
    
    public static void aplicacion(String [] rutaProceso){
        String linea = "";
        if (rutaProceso.length <= 0) {
            System.err.println("Se necesita un programa a ejecutar");
            System.exit(-1);
        }
        try {
            Process procesoHijo = new ProcessBuilder(rutaProceso).start();
            BufferedReader lecturaHijo = new BufferedReader(new
                            InputStreamReader(procesoHijo.getInputStream()));
            PrintStream escrituraHijo = new PrintStream(procesoHijo.getOutputStream());
            BufferedReader entrada = new BufferedReader(new
                                   InputStreamReader(System.in));
            procesoPrincipal(entrada, lecturaHijo, escrituraHijo, linea);
            System.out.println("Finalizando");
            procesoHijo.destroy();
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }
    
    public static void procesoPrincipal(BufferedReader entrada, BufferedReader 
            lecturaHijo, PrintStream escrituraHijo, String linea) throws IOException{
        while (!entrada.readLine().equals("fin")) {
            escrituraHijo.println();
            escrituraHijo.flush();
            linea = lecturaHijo.readLine();
            if (linea != null) {
                System.out.println(linea);
            }
        }
        
    }
}
