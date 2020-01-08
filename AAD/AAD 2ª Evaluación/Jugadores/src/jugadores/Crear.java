
package jugadores;

import objetos.Jugador;
import objetos.Pais;

/**
 *
 * @author a18luisdvp
 */
public class Crear {
    
    public static Jugador nuevoJugador(Pais pais){
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
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
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        return new Pais(id, nombre);        
    }
}
