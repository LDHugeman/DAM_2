
package jugadores;

import objetos.Jugador;
import objetos.Pais;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author a18luisdvp
 */
public class Consultar {
    
    public static Pais encontrarPaisPorNombre(String nombre){
        Pais paisEncontrado = null;
        ODB odb = ODBFactory.open("Jugadores.db");
        IQuery query = new CriteriaQuery(Pais.class, Where.equal("nombre", nombre));
        paisEncontrado = (Pais)odb.getObjects(query).getFirst();
        return paisEncontrado;
    }
    
    public static Jugador encontrarJugadorPorNombre(String nombre){
        Jugador jugadorEncontrado = null;
        ODB odb = ODBFactory.open("Jugadores.db");
        IQuery query = new CriteriaQuery(Jugador.class, Where.equal("nombre", nombre));
        jugadorEncontrado = (Jugador)odb.getObjects(query).getFirst();
        odb.close();
        return jugadorEncontrado;
    }
}
