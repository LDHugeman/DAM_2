package testhilo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author a13javiermb
 */
public class TestHilo3 {

    public static void main(String[] args) throws IOException {
        ContadorAdelanteFinalizable contador = new ContadorAdelanteFinalizable("Contador+");
        System.out.println("Pulse enter para detener");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.readLine();
        } catch (IOException e) {
        }

        contador.terminar();
    }

}
