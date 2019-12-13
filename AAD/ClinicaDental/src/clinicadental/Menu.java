package clinicadental;

import java.util.List;
import objetos.Consulta;
import objetos.Dentista;
import objetos.Historial;
import org.hibernate.Session;
import util.Pedir;

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

                    break;
                case 2:                   
                    Consulta consultaEncontrada = null;
                    List<Consulta> consultas = Consultar.extraerConsultas();
                    if (consultas.size() > 0) {
                        Visualizar.consultas(consultas);
                        System.out.println("--- Seleccione una consulta para el dentista ---");
                        int consultaSeleccionada = Pedir.numeroEntero();
                        consultaEncontrada = Consultar.encontrarConsultaPorNumero(consultaSeleccionada);
                        if (consultaEncontrada != null) {
                            Dentista dentista = Crear.nuevoDentista(consultaEncontrada);                           
                            Altas.nuevoDentista(dentista);
                        } else {
                            System.out.println("--- No se ha encontrado la consulta seleccionada --- ");
                        }
                    } else {
                        System.out.println("--- Debe crear antes una consulta ---");
                    }                  
                    break;
                case 3:
                    break;
                case 4:
                    Consulta consulta = Crear.nuevaConsulta();
                    Altas.nuevaConsulta(consulta);
                    break;
                case 5:
                    Historial historial = Crear.nuevoHistorial();
                    Altas.nuevoHistorial(historial);
                    break;
                case 6:
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

    public static byte seleccionarOpcionMenuAltas() {
        System.out.println("------- ALTAS -------");
        System.out.println("[1] Paciente");
        System.out.println("[2] Dentista");
        System.out.println("[3] Limpiador");
        System.out.println("[4] Consulta");
        System.out.println("[5] Historial");
        System.out.println("[6] Cita");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuBajas() {
        System.out.println("------- BAJAS -------");
        System.out.println("[1] Paciente");
        System.out.println("[2] Dentista");
        System.out.println("[3] Limpiador");
        System.out.println("[4] Consulta");
        System.out.println("[5] Historial");
        System.out.println("[6] Cita");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuModificaciones() {
        System.out.println("------- MODIFICACIONES -------");
        System.out.println("[1] Sueldo de un dentista");
        System.out.println("[2] ");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }
}
