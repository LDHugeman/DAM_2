
package autorlibros;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author a18luisdvp
 */
public class Menu {
    
    public static void menu(){
        System.out.println("----- MENÚ -----");
        System.out.println("[1] Insertar");
    }
    
    public static byte seleccionarOpcion(BufferedReader lee)throws IOException{
        menu();
        return Byte.parseByte(lee.readLine());
    }
}
