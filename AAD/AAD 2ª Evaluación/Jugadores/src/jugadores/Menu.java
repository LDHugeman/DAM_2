
package jugadores;

import objetos.Jugador;
import objetos.Pais;

/**
 *
 * @author a18luisdvp
 */
public class Menu {
    
    public static void menuAltas() {

        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuAltas();
            switch (opcion) {
                case 1:
                    Pais pais = Crear.nuevoPais();
                    Altas.pais(pais);
                    break;
                case 2:
                    Pais pais2 = Crear.nuevoPais();
                    Jugador jugador = Crear.nuevoJugador(pais2);
                    Altas.jugador(jugador);
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }
    
    public static void menuBajas() {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuBajas();
            switch (opcion) {
                case 1:
                    
                    break;
                case 2:
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }
    
    public static byte seleccionarOpcionMenuPrincipal() {
        System.out.println("------- MENÚ -------");
        System.out.println("[1] Altas");
        System.out.println("[2] Bajas");
        System.out.println("[3] Modificaciones");
        System.out.println("[4] Visualizar");
        System.out.println("[0] Salir");
        System.out.printf("Seleccione una opción: ");
        return Pedir.numeroByte();
    }
    
    private static byte seleccionarOpcionMenuAltas() {
        System.out.println("------- ALTAS -------");
        System.out.println("[1] País");
        System.out.println("[2] Jugador");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }
    
    private static byte seleccionarOpcionMenuBajas() {
        System.out.println("------- BAJAS -------");
        System.out.println("[1] País");
        System.out.println("[2] Jugador");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }
    
    
}
