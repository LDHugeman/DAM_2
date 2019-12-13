package clinicadental;

import java.util.List;
import objetos.Cita;
import objetos.Consulta;
import objetos.Dentista;
import objetos.Historial;
import objetos.Limpiador;
import objetos.Paciente;
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
                    Dentista dentistaEncontrado = null;
                    List<Dentista> dentistas = Consultar.extraerDentistas();
                    if(!dentistas.isEmpty()){
                        Visualizar.dentistas(dentistas);
                        System.out.println("--- Seleccione un dentista para el paciente ---");
                        String dni = Crear.pedirDni("Dni del dentista: ");
                        dentistaEncontrado = Consultar.encontrarDentistaPorDni(dni);
                        if(dentistaEncontrado!=null){
                            Historial historial = Crear.nuevoHistorial();
                            Altas.nuevoHistorial(historial);
                            Paciente paciente = Crear.nuevoPaciente(historial, dentistaEncontrado);
                            Altas.nuevoPaciente(paciente);
                        } else {
                            System.err.println("No hay ningún dentista con ese dni");
                        }
                    } else {
                        System.err.println("Debe crear antes un dentista");
                    }
                    NewHibernateUtil.closeSession(); 
                    break;
                case 2:                   
                    Consulta consultaEncontrada = null;
                    List<Consulta> consultas = Consultar.extraerConsultas();
                    if (!consultas.isEmpty()) {
                        Visualizar.consultas(consultas);
                        System.out.println("--- Seleccione una consulta para el dentista ---");
                        System.out.printf("Número de la consulta: ");
                        int consultaSeleccionada = Pedir.numeroEntero();
                        consultaEncontrada = Consultar.encontrarConsultaPorNumero(consultaSeleccionada);
                        if (consultaEncontrada != null) {
                            Dentista dentista = Crear.nuevoDentista(consultaEncontrada);                           
                            Altas.nuevoDentista(dentista);
                        } else {
                            System.err.println("No hay ninguna consulta con ese numero");
                        }
                    } else {
                        System.err.println("Debe crear antes una consulta");
                    }
                    NewHibernateUtil.closeSession();
                    break;
                case 3:
                    Limpiador limpiador = Crear.nuevoLimpiador();
                    Altas.nuevoLimpiador(limpiador);
                    break;
                case 4:
                    Consulta consulta = Crear.nuevaConsulta();
                    Altas.nuevaConsulta(consulta);
                    break;
                case 5:
                    Visualizar.pacientes(Consultar.extraerPacientes());
                    System.out.println("Seleccione un paciente");
                    String dni = Pedir.texto();
                    Paciente pacienteSeleccionado = Consultar.encontrarPacientePorDni(dni);
                    if (pacienteSeleccionado != null){
                        Cita cita = Crear.nuevaCita(pacienteSeleccionado.getHistorial());
                        Altas.nuevaCita(cita);
                    } else {
                        System.err.println("No hay ningún paciente con ese dni");
                    }                   
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
                    Visualizar.pacientes(Consultar.extraerPacientes());
                    System.out.println("--- Seleccione el dni del paciente que desea dar de baja ---");
                    String dni = Crear.pedirDni("Dni del paciente: ");
                    Paciente pacienteSeleccionado = Consultar.encontrarPacientePorDni(dni);
                    if (pacienteSeleccionado != null){
                        Bajas.eliminar(pacienteSeleccionado);
                    } else {
                        System.err.println("No existe ningun paciente con ese dni");
                    }
                    break;
                case 2:                   

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                
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
                    Visualizar.consultas(Consultar.extraerConsultas());
                    System.out.println("--- Seleccione el número de la consulta ---");
                    System.out.printf("Número: ");
                    int numero = Pedir.numeroEntero();
                    Consulta consultaSeleccionada = Consultar.encontrarConsultaPorNumero(numero);
                    if (consultaSeleccionada != null){
                        Visualizar.limpiadores(Consultar.extraerLimpiadores());
                        System.out.println("--- Seleccione el dni del limpiador ---");
                        System.out.printf("Dni: ");
                        String dni = Pedir.texto();
                        Limpiador limpiadorSeleccionado = Consultar.encontrarLimpiadorPorDni(dni);
                        if (limpiadorSeleccionado != null){
                            consultaSeleccionada.getLimpiadores().add(limpiadorSeleccionado);
                            limpiadorSeleccionado.getConsultas().add(consultaSeleccionada);
                            Altas.guardar(consultaSeleccionada);
                        } else {
                            System.err.println("No hay ningún limpiador con ese dni");
                        }
                        
                    } else {
                        System.err.println("No hay una consulta con ese número");
                    }
                    
                    break;
                case 2:                   

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                
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
        System.out.println("[5] Cita");
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
        System.out.println("[1] Añadir un limpiador a una consulta");
        System.out.println("[2] Sueldo de un dentista");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }
}
