
package excepciones;

import bdinstituto.Consultar;
import java.sql.Statement;


/**
 *
 * @author luisd
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
            if (!Consultar.existeProfesorPorDni(sentencia, texto)) {
                dniEsValido = false;
                throw new Excepcion("No existe ningún profesor con ese dni");
            }
        } catch (Excepcion excepcion) {
            System.err.println(excepcion.getError());
        }
        return dniEsValido;
    }
    
    public static boolean esCodigoValido(String texto) {
        boolean codigoAlumnoesValido = true;
        try {
            if (texto.length() != 4) {
                codigoAlumnoesValido = false;
                throw new Excepcion("Longitud errónea");
            }
            if (!texto.substring(0, 3).matches("[0-9]*")) {
                codigoAlumnoesValido = false;
                throw new Excepcion("Número erróneo");
            }
            if (!texto.substring(3).matches("[A-Za-z]")) {
                codigoAlumnoesValido = false;
                throw new Excepcion("Letra errónea");
            }
        } catch (Excepcion excepcion) {
            System.err.println(excepcion.getError());
        }
        return codigoAlumnoesValido;
    }
    
    public static boolean esCodigoAlumnoExistenteValido(Statement sentencia, String texto) {
        boolean codigoAlumnoesValido = esCodigoValido(texto);
        try {
            if (!Consultar.existeAlumnoPorCodigo(sentencia, texto)) {
                codigoAlumnoesValido = false;
                throw new Excepcion("No existe ningún alumno con ese código");
            }
        } catch (Excepcion excepcion) {
            System.err.println(excepcion.getError());
        }
        return codigoAlumnoesValido;
    }
    
    public static boolean esCodigoAsignaturaExistenteValido(Statement sentencia, String texto) {
        boolean codigoAsignaturaesValido = esCodigoValido(texto);
        try {
            if (!Consultar.existeAsignaturaPorCodigo(sentencia, texto)) {
                codigoAsignaturaesValido = false;
                throw new Excepcion("No existe ninguna asignatura con ese código");
            }
        } catch (Excepcion excepcion) {
            System.err.println(excepcion.getError());
        }
        return codigoAsignaturaesValido;
    }
}
