package clinicadentalexist;

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
        if (!Consultar.existeConsultaPorNumero(consulta.getNumero())) {
            Altas.nuevaConsulta(consulta);
        } else {
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

    private static void bajaDentista() {
        System.out.println("--- Introduzca el dni del dentista que desea eliminar ---");
        String dni = Crear.pedirDni("Dni del dentista: ");
        if (Consultar.existeDentistaPorDni(dni)) {
            if (Pedir.duda("¿Está seguro de que quiere eliminar este dentista?")) {
                Bajas.eliminarDentista(dni);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningún dentista con ese dni");
        }
        Conexion.cerrarConexion();
    }

    private static void bajaHistorial() {
        System.out.println("--- Introduzca el número del historial que desea eliminar");
        System.out.printf("Número del historial: ");
        int numero = Pedir.numeroEntero();
        if (Consultar.existeHistorialPorNumero(numero)) {
            if (Pedir.duda("¿Está seguro de que quiere eliminar este historial?")) {
                Bajas.eliminarHistorial(numero);
            } else {
                System.err.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningún historial con ese número");
        }
        Conexion.cerrarConexion();
    }

    private static void bajaPaciente() {
        System.out.println("--- Introduzca el dni del paciente que desea eliminar ---");
        String dni = Crear.pedirDni("Dni del paciente: ");
        if (Consultar.existePacientePorDni(dni)) {
            if (Pedir.duda("¿Está seguro de que quiere eliminar este paciente?")) {
                Bajas.eliminarPaciente(dni);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningún paciente con ese dni");
        }
        Conexion.cerrarConexion();
    }

    private static void bajaCita() {
        System.out.println("--- Introduzca el número de la cita que desea eliminar ---");
        System.out.printf("Número de la cita: ");
        int numero = Pedir.numeroEntero();
        if (Consultar.existeCitaPorNumero(numero)) {
            if (Pedir.duda("¿Está seguro de que quiere eliminar esta cita?")) {
                Bajas.eliminarCita(numero);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ninguna cita con ese número");
        }
        Conexion.cerrarConexion();
    }

    private static byte seleccionarOpcionMenuModificaciones() {
        System.out.println("------- MODIFICACIONES -------");
        System.out.println("[1] Sueldo de un dentista");
        System.out.println("[2] Fecha y hora de una cita");
        System.out.println("[3] Teléfono de un paciente");
        System.out.println("[4] Quirófano de una consulta");
        System.out.println("[5] Grupo sanguíneo de un historial de un paciente");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static void menuModificaciones() {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuModificaciones();
            switch (opcion) {
                case 1:
                    modificarSueldoDentista();
                    break;
                case 2:
                    modificarFechaHoraCita();
                    break;
                case 3:
                    modificarTelefonoPaciente();
                    break;
                case 4:
                    modificarQuirofanoConsulta();
                    break;
                case 5:
                    modificarGrupoSanguineoHistorial();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

    private static void modificarSueldoDentista() {
        System.out.println("--- Introduzca el dni del dentista al que desea modificar su sueldo ---");
        String dni = Crear.pedirDni("Dni del dentista: ");
        if (Consultar.existeDentistaPorDni(dni)) {
            if (Pedir.duda("¿Está seguro de que quiere modificar el sueldo de este dentista?")) {
                Modificar.sueldoDentista(dni);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningún dentista con ese dni");
        }
        Conexion.cerrarConexion();
    }

    private static void modificarFechaHoraCita() {
        System.out.println("--- Introduzca el número de la cita de la que desea modificar fecha y hora ---");
        System.out.printf("Número de la cita: ");
        int numero = Pedir.numeroEntero();
        if (Consultar.existeCitaPorNumero(numero)) {
            if (Pedir.duda("¿Está seguro de que quiere modificar la fecha y la hora de esta cita?")) {
                Modificar.fechaHoraCita(numero);
            }
        } else {
            System.err.println("No existe ninguna cita con ese número");
        }
        Conexion.cerrarConexion();
    }

    private static void modificarTelefonoPaciente() {
        System.out.println("--- Introduzca el dni del paciente al que desea modificar su teléfono");
        String dni = Crear.pedirDni("Dni del paciente: ");
        if (Consultar.existePacientePorDni(dni)) {
            if (Pedir.duda("¿Está seguro de que quiere modificar el teléfono de este paciente?")) {
                Modificar.telefonoPaciente(dni);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningún paciente con ese dni");
        }
        Conexion.cerrarConexion();
    }

    private static void modificarQuirofanoConsulta() {
        System.out.println("--- Introduzca el número de la consulta de la que quiere modificar el quirófano ---");
        System.out.printf("Número de la consulta: ");
        int numero = Pedir.numeroEntero();
        if (Consultar.existeConsultaPorNumero(numero)) {
            if (Pedir.duda("¿Está seguro de que quiere modificar el quirófano de esta consulta?")) {
                Modificar.quirofanoConsulta(numero);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ninguna consulta con ese número");
        }
        Conexion.cerrarConexion();
    }

    private static void modificarGrupoSanguineoHistorial() {
        System.out.println("--- Introduzca el número del historial al que desea modificar el grupo sanguíneo del paciente ---");
        System.out.printf("Número del historial: ");
        int numero = Pedir.numeroEntero();
        if (Consultar.existeHistorialPorNumero(numero)) {
            if (Pedir.duda("¿Está seguro que desea modificar el grupo sanguíneo en este historial?")) {
                Modificar.grupoSanguineoHistorial(numero);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningún historial con ese número");
        }
        Conexion.cerrarConexion();
    }

    private static byte seleccionarOpcionMenuVisualizar() {
        System.out.println("------- VISUALIZAR -------");
        System.out.println("[1] Todas las consultas");
        System.out.println("[2] Consulta por número");
        System.out.println("[3] Todos los dentistas");
        System.out.println("[4] Dentista por dni");
        System.out.println("[5] Todos los pacientes");
        System.out.println("[6] Paciente por dni");
        System.out.println("[7] Todos los historiales");
        System.out.println("[8] Historial por número");
        System.out.println("[9] Todas las citas");
        System.out.println("[10] Cita por número");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static void menuVisualizar() {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuVisualizar();
            switch (opcion) {
                case 1:
                    Visualizar.consultas();
                    Conexion.cerrarConexion();
                    break;
                case 2:
                    visualizarConsulta();
                    break;
                case 3:
                    Visualizar.dentistas();
                    Conexion.cerrarConexion();
                    break;
                case 4:
                    visualizarDentista();
                    break;
                case 5:
                    Visualizar.pacientes();
                    Conexion.cerrarConexion();
                    break;
                case 6:
                    visualizarPaciente();
                    break;
                case 7:
                    Visualizar.historiales();
                    Conexion.cerrarConexion();
                    break;
                case 8:
                    visualizarHistorial();
                    break;
                case 9:
                    Visualizar.citas();
                    Conexion.cerrarConexion();
                    break;
                case 10:
                    visualizarCita();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }
    
    private static void visualizarConsulta(){
        System.out.println("--- Introduzca el número de la consulta que desea visualizar ---");
        System.out.printf("Número de la consulta: ");
        int numero = Pedir.numeroEntero();
        Visualizar.consulta(numero);
        Conexion.cerrarConexion();
    }
    
    private static void visualizarDentista(){
        System.out.println("--- Introduzca el dni del dentista que desea visualizar ---");
        String dni = Crear.pedirDni("Dni del dentista: ");
        Visualizar.dentista(dni);
        Conexion.cerrarConexion();
    }
    
    private static void visualizarPaciente(){
        System.out.println("--- Introduzca el dni del paciente que desea visualizar ---");
        String dni = Crear.pedirDni("Dni del paciente: ");
        Visualizar.paciente(dni);
        Conexion.cerrarConexion();
    }
    
    private static void visualizarHistorial(){
        System.out.println("--- Introduzca el número del historial que desea visualizar ---");
        System.out.printf("Número del historial: ");
        int numero = Pedir.numeroEntero();
        Visualizar.historial(numero);
        Conexion.cerrarConexion();
    }
    
    private static void visualizarCita(){
        System.out.println("--- Introduzca el número de la cita que desea visualizar ---");
        System.out.printf("Número de la cita: ");
        int numero = Pedir.numeroEntero();
        Visualizar.cita(numero);
        Conexion.cerrarConexion();
    }

}
