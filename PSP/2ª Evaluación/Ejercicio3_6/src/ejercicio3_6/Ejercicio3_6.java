
package ejercicio3_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author a18luisdvp
 */
public class Ejercicio3_6 {

    public static void main(String[] args) throws IOException {
        String Host="loalhost";
        int puerto=6000; 
        Socket cliente= new Socket(Host,puerto);
        BufferedReader flujoEntrada= new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        PrintWriter flujoSalida=new PrintWriter(cliente.getOutputStream(), true);
        BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
    }
    
}
