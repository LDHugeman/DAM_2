
package jugadores;

import objetos.Jugador;
import objetos.Pais;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

/**
 *
 * @author a18luisdvp
 */
public class Altas {
    
    public static void jugador(Jugador jugador){
        ODB odb = ODBFactory.open("Jugadores.db");
        odb.store(jugador);
        odb.close();
    }
    
    public static void pais(Pais pais){
        ODB odb = ODBFactory.open("Jugadores.db");
        odb.store(pais);
        odb.close();
    }
}
