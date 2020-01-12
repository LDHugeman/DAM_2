
package jugadores;

import objetos.Jugador;
import objetos.Pais;

/**
 *
 * @author a18luisdvp
 */
public class Crear {
    
    public static Jugador nuevoJugador(Pais pais){
        String nombre = pedirNombre();
        System.out.printf("Deporte: ");
        String deporte = Pedir.texto();
        System.out.printf("Ciudad: ");
        String ciudad = Pedir.texto();
        System.out.printf("Edad: ");
        int edad = Pedir.numeroEntero();
        return new Jugador(nombre, deporte, ciudad, edad, pais);
    }
    
    public static Pais nuevoPais(){
        System.out.printf("Id del país: ");
        int id = Pedir.numeroEntero();
        String nombre = pedirNombre();
        return new Pais(id, nombre);        
    }
    
    public static String pedirNombre(){
        System.out.printf("Nombre: ");
        return Pedir.texto();
    }
}
