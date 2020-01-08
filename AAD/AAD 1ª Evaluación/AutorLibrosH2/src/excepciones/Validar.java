
package excepciones;

import autorlibrosh2.Consultar;
import java.sql.Statement;

/**
 *
 * @author a18luisdvp
 */
public class Validar {
    public static boolean esDniValido(String texto) {
        boolean dniEsValido = true;
        try {
            if (texto.length() != 9) {
                dniEsValido = false;
                throw new Excepcion("Longitud errónea");
            }
            if (!texto.substring(0, 8).matches("[0-9]*")) {
                dniEsValido = false;
                throw new Excepcion("Número erróneo");
            }
            if (!texto.substring(8).matches("[A-Za-z]")) {
                dniEsValido = false;
                throw new Excepcion("Letra errónea");
            }           
        } catch (Excepcion excepcion) {
            System.err.println(excepcion.getError());
        }
        return dniEsValido;
    }
    
    
    public static boolean esDniExistenteValido(Statement sentencia, String texto) {
        boolean dniEsValido = esDniValido(texto);
        try {         
            if (!Consultar.existeAutorPorDni(sentencia, texto)) {
                dniEsValido = false;
                throw new Excepcion("No existe ningún autor con ese dni");
            }
        } catch (Excepcion excepcion) {
            System.err.println(excepcion.getError());
        }
        return dniEsValido;
    }
}
