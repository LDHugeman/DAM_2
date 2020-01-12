
package jugadores;

import objetos.Jugador;
import objetos.Pais;
import org.neodatis.odb.ODB;

/**
 *
 * @author a18luisdvp
 */
public class Modificar {
    
    public static void deporteJugador(Jugador jugador){
        ODB odb = Conexion.getSession();
        System.out.println("--- Introduzca el nuevo deporte para el jugador ---");
        System.out.printf("Deporte: ");
        String deporte = Pedir.texto();
        jugador.setDeporte(deporte);
        odb.store(jugador);        
    }
    
    public static void paisJugador(Jugador jugador){
        ODB odb = Conexion.getSession();
        System.out.println("--- Introduzca el nombre del país que desea asociar al jugador ---");
        String nombrePais = Crear.pedirNombre();
        Pais pais =Consultar.encontrarPaisPorNombre(nombrePais);
        if(pais!=null){
            jugador.setPais(pais);
            odb.store(jugador);
        } else {
            System.err.println("No hay ningún país con ese nombre, tiene que darlo de alta");
        }
    }
}
