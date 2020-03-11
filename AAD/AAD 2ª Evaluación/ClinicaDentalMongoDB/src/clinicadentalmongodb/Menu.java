
package clinicadentalmongodb;

import objetos.Cita;
import objetos.Consulta;
import objetos.Dentista;
import objetos.Historial;
import objetos.Paciente;
import util.Pedir;

/**
 *
 * @author Alberto y David
 */
public class Menu {
    
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
        System.out.println("[1] Consulta");
        System.out.println("[2] Dentista");
        System.out.println("[3] Historial");
        System.out.println("[4] Paciente");
        System.out.println("[5] Cita");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static void menuAltas() {

        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuAltas();
            switch (opcion) {
                case 1:
                    altaConsulta();
                    break;
                case 2:
                    altaDentista();
                    break;
                case 3:
                    altaHistorial();
                    break;
                case 4:
                    altaPaciente();
                    break;
                case 5:
                    altaCita();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }
    
    private static void altaConsulta() {
        Consulta consulta = Crear.nuevaConsulta();
        if(!Consultar.existeConsultaPorNumero(consulta.getPiso())){
            Altas.nuevaConsulta(consulta);
        }else {
            System.err.println("Ya existe una consulta con ese número");
        }
        Conexion.cerrarConexion();
    }
    
    private static void altaDentista() {
        Dentista dentista = Crear.nuevoDentista();
        if (!Consultar.existeDentistaPorDni(dentista.getDni())) {
            if (Consultar.existeConsultaPorNumero(dentista.getNumeroConsulta())) {
                Altas.nuevoDentista(dentista);
            } else {
                System.err.println("No existe ninguna consulta con ese número");
            }
        } else {
            System.err.println("Ya existe un dentista con ese dni");
        }
        Conexion.cerrarConexion();
    }
    
    private static void altaHistorial() {
        Historial historial = Crear.nuevoHistorial();
        if (!Consultar.existeHistorialPorNumero(historial.getNumero())) {
            Altas.nuevoHistorial(historial);
        } else {
            System.err.println("Ya existe un historial con ese número");
        }
        Conexion.cerrarConexion();
    }
    
    private static void altaPaciente() {
        Paciente paciente = Crear.nuevoPaciente();
        if (!Consultar.existePacientePorDni(paciente.getDni())) {
            if (Consultar.existeDentistaPorDni(paciente.getDniDentista())) {
                if (Consultar.existeHistorialPorNumero(paciente.getNumeroHistorial())) {
                    Altas.nuevoPaciente(paciente);
                } else {
                    System.err.println("No existe ningún historial con ese número");
                }
            } else {
                System.err.println("No existe ningún dentista con ese dni");
            }
        } else {
            System.err.println("Ya existe un paciente con ese dni");
        }
        Conexion.cerrarConexion();
    }

    private static void altaCita() {
        Cita cita = Crear.nuevaCita();
        if (!Consultar.existeCitaPorNumero(cita.getNumero())) {
            if (Consultar.existePacientePorDni(cita.getDniPaciente())) {
                Altas.nuevaCita(cita);
            } else {
                System.err.println("No existe ningún paciente con ese dni");
            }
        } else {
            System.err.println("Ya existe una cita con ese número");
        }
        Conexion.cerrarConexion();
    }
    
    private static byte seleccionarOpcionMenuBajas() {
        System.out.println("------- BAJAS -------");
        System.out.println("[1] Consulta");
        System.out.println("[2] Dentista");
        System.out.println("[3] Historial");
        System.out.println("[4] Paciente");
        System.out.println("[5] Cita");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static void menuBajas() {

        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuBajas();
            switch (opcion) {
                case 1:
                    bajaConsulta();
                    break;
                case 2:
                    bajaDentista();
                    break;
                case 3:
                    bajaHistorial();
                    break;
                case 4:
                    bajaPaciente();
                    break;
                case 5:
                    bajaCita();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }
    
    private static void bajaConsulta() {
        System.out.println("--- Introduzca el número de la consulta que desea eliminar ---");
        System.out.printf("Número de la consulta: ");
        int numero = Pedir.numeroEntero();
        if (Consultar.existeConsultaPorNumero(numero)) {
            if (Pedir.duda("¿Está seguro de que quiere eliminar esta consulta?")) {
                Bajas.eliminarConsulta(numero);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ninguna consulta con ese número");
        }
        Conexion.cerrarConexion();
    }
}
