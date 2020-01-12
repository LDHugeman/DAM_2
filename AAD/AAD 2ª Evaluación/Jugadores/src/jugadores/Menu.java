package jugadores;

import java.util.ArrayList;
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
                    altaPais();
                    break;
                case 2:
                    altaJugador();
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
                    bajaPais();
                    break;
                case 2:
                    bajaJugador();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

    public static void menuModificaciones() {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuModificaciones();
            switch (opcion) {
                case 1:
                    modificarDeporteJugador();
                    break;
                case 2:
                    modificarPaisJugador();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

    public static void menuVisualizar() {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuVisualizar();
            switch (opcion) {
                case 1:
                    visualizarJugadoresEmpiezanP();
                    break;
                case 2:
                    visualizarJugadoresMadridMayorA21();
                    break;
                case 3:
                    visualizarJugadoresAlemanes();
                    break;
                case 4:
                    System.out.println("La suma total de las edades es de " + Consultar.sumaTotalEdades());
                    break;
                case 5:
                    System.out.println("El número de jugadores totales es " + Consultar.numeroJugadoresTotales());
                    break;
                case 6:
                    System.out.println("La media de la edad de los jugadores es de " + Consultar.mediaEdadJugadores());
                    break;
                case 7:
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
        System.out.println("[3] Modificar deporte de un jugador");
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

    private static void altaPais() {
        Pais pais = Crear.nuevoPais();
        if (!Consultar.existePaisPorNombre(pais.getNombre())) {
            Altas.pais(pais);
        } else {
            System.out.println("Ya existe un país con ese nombre");
        }
        Conexion.closeSession();
    }

    private static void altaJugador() {
        System.out.println("--- Datos del país ---");
        Pais pais = Crear.nuevoPais();
        if (!Consultar.existePaisPorNombre(pais.getNombre())) {
            System.out.println("--- Datos del jugador ---");
            Jugador jugador = Crear.nuevoJugador(pais);
            Altas.jugador(jugador);
        } else {
            System.out.println("Ya existe un país con ese nombre");
        }
        Conexion.closeSession();
    }

    private static byte seleccionarOpcionMenuBajas() {
        System.out.println("------- BAJAS -------");
        System.out.println("[1] País");
        System.out.println("[2] Jugador");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    private static void bajaPais() {
        String nombre = Crear.pedirNombre();
        Pais pais = Consultar.encontrarPaisPorNombre(nombre);
        if (pais != null) {
            if (Pedir.duda("¿Está seguro de que quiere eliminar el pais?")) {
                Bajas.pais(pais);
            }
        }
        Conexion.closeSession();
    }

    private static void bajaJugador() {
        String nombreJugador = Crear.pedirNombre();
        Jugador jugador = Consultar.encontrarJugadorPorNombre(nombreJugador);
        if (jugador != null) {
            if (Pedir.duda("¿Está seguro de que quiere eliminar el jugador?")) {
                Bajas.jugador(jugador);
            }
        }
        Conexion.closeSession();
    }

    private static byte seleccionarOpcionMenuModificaciones() {
        System.out.println("------- MODIFICACIONES -------");
        System.out.println("[1] Deporte de un jugador");
        System.out.println("[2] País de un jugador");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    private static void modificarDeporteJugador() {
        String nombre = Crear.pedirNombre();
        Jugador jugador = Consultar.encontrarJugadorPorNombre(nombre);
        Modificar.deporteJugador(jugador);
        Conexion.closeSession();
    }
    
    private static void modificarPaisJugador(){
        String nombre = Crear.pedirNombre();
        Jugador jugador = Consultar.encontrarJugadorPorNombre(nombre);
        Modificar.paisJugador(jugador);
        Conexion.closeSession();
    }

    private static byte seleccionarOpcionMenuVisualizar() {
        System.out.println("------- VISUALIZAR -------");
        System.out.println("[1] Todos los jugadores cuyo nombre empiece por 'P'");
        System.out.println("[2] Jugadores cuya ciudad es Madrid y su edad es mayor a 21");
        System.out.println("[3] Todos los jugadores alemanes");
        System.out.println("[4] Suma de las edades de todos los jugadores");
        System.out.println("[5] Número de jugadores totales");
        System.out.println("[6] Media de la edad de los jugadores");
        System.out.println("[7] Número de jugadores por ciudad");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    private static void visualizarJugadoresEmpiezanP() {
        ArrayList<Jugador> jugadores = Consultar.encontrarJugadoresEmpiezanPorP();
        if (!jugadores.isEmpty()) {
            Visualizar.jugadores(jugadores);
        } else {
            System.err.println("No hay ningún jugador que tenga un nombre que empieza por 'P'");
        }
        Conexion.closeSession();
    }

    private static void visualizarJugadoresMadridMayorA21() {
        ArrayList<Jugador> jugadores = Consultar.encontrarJugadoresMadridMayorA21();
        if (!jugadores.isEmpty()) {
            Visualizar.jugadores(jugadores);
        } else {
            System.err.println("No hay jugadores de Madrid con más de 21 años");
        }
        Conexion.closeSession();
    }

    private static void visualizarJugadoresAlemanes() {
        ArrayList<Jugador> jugadores = Consultar.encontrarJugadoresAlemanes();
        if (!jugadores.isEmpty()) {
            Visualizar.jugadores(jugadores);
        } else {
            System.err.println("No hay ningún jugador alemán");
        }
        Conexion.closeSession();
    }
}
