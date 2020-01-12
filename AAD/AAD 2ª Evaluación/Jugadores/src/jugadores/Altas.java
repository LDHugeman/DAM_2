
package jugadores;

import objetos.Jugador;
import objetos.Pais;
import org.neodatis.odb.ODB;

/**
 *
 * @author a18luisdvp
 */
public class Altas {
    
    public static void jugador(Jugador jugador){
        ODB odb = Conexion.getSession();
        odb.store(jugador);
    }
    
    public static void pais(Pais pais){
        ODB odb = Conexion.getSession();
        odb.store(pais);
    }
}
