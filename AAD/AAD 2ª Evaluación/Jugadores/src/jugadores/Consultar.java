package jugadores;

import java.util.ArrayList;
import objetos.Jugador;
import objetos.Pais;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

/**
 *
 * @author a18luisdvp
 */
public class Consultar {

    public static Pais encontrarPaisPorNombre(String nombre) {
        Pais paisEncontrado = null;
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Pais.class, Where.equal("nombre", nombre));
        Objects resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            paisEncontrado = (Pais) resultado.getFirst();
        } else {
            System.err.println("No se ha encontrado ningun pais con ese nombre");
        }
        return paisEncontrado;
    }

    public static boolean existePaisPorNombre(String nombre) {
        return encontrarPaisPorNombre(nombre) != null;
    }

    public static Jugador encontrarJugadorPorNombre(String nombre) {
        Jugador jugadorEncontrado = null;
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Jugador.class, Where.equal("nombre", nombre));
        Objects resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            jugadorEncontrado = (Jugador) resultado.getFirst();
        } else {
            System.err.println("No se ha encontrado ningun jugador con ese nombre");
        }
        return jugadorEncontrado;
    }

    public static ArrayList<Jugador> encontrarJugadoresEmpiezanPorP() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        ODB odb = Conexion.getSession();
        ICriterion icriterion = Where.like("nombre", "P%");
        CriteriaQuery query = new CriteriaQuery(Jugador.class, icriterion);
        Objects resultado = odb.getObjects(query);
        while (resultado.hasNext()) {
            Jugador jugador = (Jugador) resultado.next();
            jugadores.add(jugador);
        }
        return jugadores;
    }

    public static ArrayList<Jugador> encontrarJugadoresMadridMayorA21() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        ODB odb = Conexion.getSession();
        ICriterion icriterion = new And().add(Where.equal("ciudad", "Madrid")).add(Where.gt("edad", 21));
        CriteriaQuery query = new CriteriaQuery(Jugador.class, icriterion);
        Objects resultado = odb.getObjects(query);
            while (resultado.hasNext()) {
                Jugador jugador = (Jugador) resultado.next();
                jugadores.add(jugador);
            }
        return jugadores;
    }

    public static ArrayList<Jugador> encontrarJugadoresAlemanes() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        ODB odb = Conexion.getSession();
        ICriterion icriterion = Where.equal("pais.nombre", "Alemania");
        CriteriaQuery query = new CriteriaQuery(Jugador.class, icriterion);
        Objects resultado = odb.getObjects(query);
            while (resultado.hasNext()) {
                Jugador jugador = (Jugador) resultado.next();
                jugadores.add(jugador);
            }
        return jugadores;
    }

    public static int sumaTotalEdades() {
        ODB odb = Conexion.getSession();
        Values values = odb.getValues(new ValuesCriteriaQuery(Jugador.class).sum("edad"));
        ObjectValues objectValues = values.nextValues();
        int sumaTotalEdades = (int) objectValues.getByAlias("edad");
        return sumaTotalEdades;
    }

    public static int numeroJugadoresTotales() {
        ODB odb = Conexion.getSession();
        Values values = odb.getValues(new ValuesCriteriaQuery(Jugador.class).count("nombre"));
        ObjectValues objectValues = values.nextValues();
        int jugadores = (int) objectValues.getByAlias("nombre");
        return jugadores;
    }
    
    public static float mediaEdadJugadores(){
        ODB odb = Conexion.getSession();
        Values values = odb.getValues(new ValuesCriteriaQuery(Jugador.class).avg("edad"));
        ObjectValues objectValues = values.nextValues();
        float edad = (float) objectValues.getByAlias("edad");
        return edad;
    }
    
    public static void mostrarNumeroJugadoresCiudad(){
        ODB odb = Conexion.getSession();
        Values values = odb.getValues(new ValuesCriteriaQuery(Jugador.class).field("ciudad").count("nombre").groupBy("ciudad"));
        while(values.hasNext()){
           ObjectValues objectValues = values.next();
            System.out.println("Jugadores de la ciudad de "+ objectValues.getByAlias("ciudad")+ ": "+ objectValues.getByIndex(1));
        }
    }
}
