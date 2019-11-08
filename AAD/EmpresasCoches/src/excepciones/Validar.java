package excepciones;

import java.util.ArrayList;
import objetos.Coche;

/**
 *
 * @author a18luisdvp
 */
public class Validar {

    public static boolean esCodigoCocheVentaValido(String texto, ArrayList<Coche> coches) {
        boolean codigoCocheEsValido = true;
        try {
            if (texto.length() != 4) {
                codigoCocheEsValido = false;
                throw new Excepcion("Longitud errónea");
            }
            if (!texto.substring(0, 1).matches("[Vv]")) {
                codigoCocheEsValido = false;
                throw new Excepcion("Letra errónea");
            }
            if (!texto.substring(1).matches("[0-9]*")) {
                codigoCocheEsValido = false;
                throw new Excepcion("Número erróneo");
            }           
        } catch (Excepcion excepcion) {
            System.out.println(excepcion.getError());
        }
        return codigoCocheEsValido;
    }

    public static boolean esCodigoCocheAlquilerValido(String texto, ArrayList<Coche> coches) {
        boolean codigoCocheEsValido = true;
        try {
            if (texto.length() != 4) {
                codigoCocheEsValido = false;
                throw new Excepcion("Longitud errónea");
            }
            if (!texto.substring(0, 1).matches("[Aa]")) {
                codigoCocheEsValido = false;
                throw new Excepcion("Letra errónea");
            }
            if (!texto.substring(1).matches("[0-9]*")) {
                codigoCocheEsValido = false;
                throw new Excepcion("Número erróneo");
            }
        } catch (Excepcion excepcion) {
            System.out.println(excepcion.getError());
        }
        return codigoCocheEsValido;
    }

    public static boolean esCodigoCocheExistenteValido(String texto, ArrayList<Coche> coches) {
        boolean codigoCocheEsValido = true;
        try {
            if (texto.length() != 4) {
                codigoCocheEsValido = false;
                throw new Excepcion("Longitud errónea");
            }
            if (!texto.substring(0, 1).matches("[AaVv]")) {
                codigoCocheEsValido = false;
                throw new Excepcion("Letra errónea");
            }
            if (!texto.substring(1).matches("[0-9]*")) {
                codigoCocheEsValido = false;
                throw new Excepcion("Número erróneo");
            }
        } catch (Excepcion excepcion) {
            System.out.println(excepcion.getError());
        }
        return codigoCocheEsValido;
    }
}
