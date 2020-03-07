
package clinicadentalexist;

import util.BaseDatos;
import util.Pedir;

/**
 *
 * @author Alberto y David
 */
public class Modificar {
    
    public static void sueldoDentista(String dni){
        System.out.println("--- Introduzca el nuevo sueldo para el dentista ---");
        System.out.printf("Sueldo: ");
        float sueldo = Pedir.numeroRealFloat();
        String modificarSueldoDentista = "update value /dentistas/dentista[@dniDentista='" + dni + "']/sueldoDentista with '" + sueldo + "'";
        BaseDatos.ejecutarAccion(modificarSueldoDentista);
    }
}
