
package jugadores;

import java.util.ArrayList;
import objetos.Jugador;

/**
 *
 * @author a18luisdvp
 */
public class Visualizar {
    
    public static void mostrarJugador(Jugador jugador){
        System.out.println("---------------- JUGADOR ----------------");
        System.out.println("Nombre: " + jugador.getNombre());
        System.out.println("País: "+jugador.getPais().getNombre());
        System.out.println("Ciudad: " + jugador.getCiudad());
        System.out.println("Deporte: "+ jugador.getDeporte());
        System.out.println("Edad: " + jugador.getEdad());
        System.out.println("----------------------------------------------");
    }
    
    public static void jugadores(ArrayList<Jugador> jugadores){
        for(Jugador jugador:jugadores){
            mostrarJugador(jugador);
        }
    }
}
