package clinicadental;

import java.util.Date;
import java.util.List;
import java.util.Set;
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
                    Consulta consulta = Crear.nuevaConsulta();
                    Altas.nuevaConsulta(consulta);
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
                            System.err.println("No hay ninguna consulta con ese número");
                        }
                    } else {
                        System.err.println("Debe crear antes una consulta");
                    }
                    NewHibernateUtil.closeSession();
                    break;
                case 3:
                    Dentista dentistaEncontrado = null;
                    List<Dentista> dentistas = Consultar.extraerDentistas();
                    if (!dentistas.isEmpty()) {
                        Visualizar.dentistas(dentistas);
                        System.out.println("--- Seleccione un dentista para el paciente ---");
                        String dni = Crear.pedirDni("Dni del dentista: ");
                        dentistaEncontrado = Consultar.encontrarDentistaPorDni(dni);
                        if (dentistaEncontrado != null) {
                            System.out.println("--- Datos del historial ---");
                            Historial historial = Crear.nuevoHistorial();
                            Altas.nuevoHistorial(historial);
                            System.out.println("--- Datos del paciente ---");
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
                case 4:
                    Visualizar.pacientes(Consultar.extraerPacientes());
                    System.out.println("--- Seleccione el paciente al que quiere añadirle una cita ---");
                    String dni = Crear.pedirDni("Dni: ");
                    Paciente pacienteSeleccionado = Consultar.encontrarPacientePorDni(dni);
                    if (pacienteSeleccionado != null) {
                        Historial historialPaciente = pacienteSeleccionado.getHistorial();
                        Cita cita = Crear.nuevaCita(historialPaciente);
                        historialPaciente.getCitas().add(cita);
                        Altas.nuevaCita(cita);
                    } else {
                        System.err.println("No hay ningún paciente con ese dni");
                    }
                    NewHibernateUtil.closeSession();
                    break;
                case 5:
                    Limpiador limpiador = Crear.nuevoLimpiador();
                    Altas.nuevoLimpiador(limpiador);
                    NewHibernateUtil.closeSession();
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
                    String dniPaciente = Crear.pedirDni("Dni del paciente: ");
                    Paciente pacienteSeleccionado = Consultar.encontrarPacientePorDni(dniPaciente);
                    if (pacienteSeleccionado != null) {
                        if (Pedir.duda("¿Está seguro de que quiere eliminar este paciente?")) {
                            Bajas.eliminar(pacienteSeleccionado);
                            for (Cita cita : pacienteSeleccionado.getHistorial().getCitas()) {
                                Bajas.eliminar(cita);
                            }
                            Bajas.eliminar(pacienteSeleccionado.getHistorial());
                        } else {
                            System.out.println("Operación cancelada");
                        }
                    } else {
                        System.err.println("No existe ningun paciente con ese dni");
                    }
                    NewHibernateUtil.closeSession();
                    break;
                case 2:
                    Visualizar.dentistas(Consultar.extraerDentistas());
                    System.out.println("--- Seleccione el dni del dentista que desea dar de baja ---");
                    String dniDentista = Crear.pedirDni("Dni del dentista: ");
                    Dentista dentistaSeleccionado = Consultar.encontrarDentistaPorDni(dniDentista);
                    if (dentistaSeleccionado != null) {
                        if (Pedir.duda("¿Está seguro de que quiere eliminar este dentista?")) {
                            Bajas.eliminar(dentistaSeleccionado);
                        } else {
                            System.out.println("Operación cancelada");
                        }
                    } else {
                        System.err.println("No existe ningun dentista con ese dni");
                    }
                    NewHibernateUtil.closeSession();
                    break;
                case 3:
                    Visualizar.consultas(Consultar.extraerConsultas());
                    System.out.println("--- Seleccione el numero de consulta que desea dar de baja ---");
                    System.out.printf("Número: ");
                    int numero = Pedir.numeroEntero();
                    Consulta consultaSeleccionada = Consultar.encontrarConsultaPorNumero(numero);
                    if (consultaSeleccionada != null) {
                        if (Pedir.duda("¿Está seguro de que quiere eliminar esta consulta?")) {
                            Bajas.eliminar(consultaSeleccionada);
                        } else {
                            System.out.println("Operación cancelada");
                        }
                    } else {
                        System.err.println("No existe ninguna consulta con ese numero");
                    }
                    NewHibernateUtil.closeSession();
                    break;
                case 4:
                    Visualizar.pacientes(Consultar.extraerPacientes());
                    System.out.println("--- Seleccione el paciente del que desea dar de baja la cita ---");
                    String dniPaciente2 = Crear.pedirDni("Dni del paciente: ");
                    Paciente pacienteEncontrado = Consultar.encontrarPacientePorDni(dniPaciente2);
                    if (pacienteEncontrado != null) {
                        Set<Cita> citas = pacienteEncontrado.getHistorial().getCitas();
                        if (!citas.isEmpty()) {
                            Visualizar.citas(citas);
                            System.out.println("--- Seleccione la fecha de la cita que desea dar de baja ---");
                            System.out.printf("Fecha(dd/MM/yyyy): ");
                            Date fecha = Pedir.fecha();
                            System.out.println("--- Seleccione la hora de la cita que desea dar de baja ---");
                            System.out.printf("Hora(hh:mm): ");
                            Date hora = Pedir.hora();
                            Cita citaSeleccionada = Consultar.encontrarCitaPorFechaHoraHistorial(pacienteEncontrado.getHistorial().getCodigo(), fecha, hora);
                            if (citaSeleccionada != null) {
                                if (Pedir.duda("¿Está seguro de que quiere eliminar esta cita?")) {
                                    Bajas.eliminar(citaSeleccionada);
                                } else {
                                    System.out.println("Operacion cancelada");
                                }
                            } else {
                                System.err.println("No existe ninguna cita con esos datos");
                            }
                        } else {
                            System.err.println("Este paciente no tiene ninguna cita");
                        }
                    } else {
                        System.err.println("No existe ningún paciente con ese dni");
                    }
                    NewHibernateUtil.closeSession();
                    break;
                case 5:
                    Visualizar.limpiadores(Consultar.extraerLimpiadores());
                    System.out.println("--- Seleccione el dni del limpiador que desea dar de baja ---");
                    String dniLimpiador = Crear.pedirDni("Dni del limpiador: ");
                    Limpiador limpiadorSeleccionado = Consultar.encontrarLimpiadorPorDni(dniLimpiador);
                    if (limpiadorSeleccionado != null) {
                        if (Pedir.duda("¿Está seguro de que quiere eliminar este limpiador?")) {
                            Bajas.eliminar(limpiadorSeleccionado);
                        } else {
                            System.out.println("Operación cancelada");
                        }
                    } else {
                        System.err.println("No existe ningun limpiador con ese dni");
                    }
                    NewHibernateUtil.closeSession();
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
                    if (consultaSeleccionada != null) {
                        Visualizar.limpiadores(Consultar.extraerLimpiadores());
                        System.out.println("--- Seleccione el dni del limpiador ---");
                        String dni = Crear.pedirDni("Dni: ");
                        Limpiador limpiadorSeleccionado = Consultar.encontrarLimpiadorPorDni(dni);
                        if (limpiadorSeleccionado != null) {
                            consultaSeleccionada.getLimpiadores().add(limpiadorSeleccionado);
                            limpiadorSeleccionado.getConsultas().add(consultaSeleccionada);
                            Altas.guardar(consultaSeleccionada);
                        } else {
                            System.err.println("No hay ningún limpiador con ese dni");
                        }
                    } else {
                        System.err.println("No hay ninguna consulta con ese número");
                    }
                    NewHibernateUtil.closeSession();
                    break;
                case 2:
                    Visualizar.pacientes(Consultar.extraerPacientes());
                    System.out.println("--- Seleccione el paciente del que desea modificar una cita ---");
                    String dni = Crear.pedirDni("Dni del paciente: ");
                    Paciente pacienteEncontrado = Consultar.encontrarPacientePorDni(dni);
                    if (pacienteEncontrado != null) {
                        Set<Cita> citas = pacienteEncontrado.getHistorial().getCitas();
                        if (!citas.isEmpty()) {
                            Visualizar.citas(citas);
                            System.out.println("--- Seleccione la fecha de la cita que desea modificar ---");
                            System.out.printf("Fecha(dd/MM/yyyy): ");
                            Date fecha = Pedir.fecha();
                            System.out.println("--- Seleccione la hora de la cita que desea modificar ---");
                            System.out.printf("Hora(hh:mm): ");
                            Date hora = Pedir.hora();
                            Cita citaSeleccionada = Consultar.encontrarCitaPorFechaHoraHistorial(pacienteEncontrado.getHistorial().getCodigo(), fecha, hora);
                            if (citaSeleccionada != null) {
                                Modificar.fechaHoraCita(citaSeleccionada);
                            } else {
                                System.err.println("No existe ninguna cita con esos datos");
                            }
                        } else {
                            System.err.println("Este paciente no tiene ninguna cita");
                        }
                    } else {
                        System.err.println("No hay ningún paciente con ese dni");
                    }
                    NewHibernateUtil.closeSession();
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
        System.out.println("[1] Consulta");
        System.out.println("[2] Dentista");
        System.out.println("[3] Paciente");
        System.out.println("[4] Cita");
        System.out.println("[5] Limpiador");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuBajas() {
        System.out.println("------- BAJAS -------");
        System.out.println("[1] Paciente");
        System.out.println("[2] Dentista");
        System.out.println("[3] Consulta");
        System.out.println("[4] Cita");
        System.out.println("[5] Limpiador");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuModificaciones() {
        System.out.println("------- MODIFICACIONES -------");
        System.out.println("[1] Añadir un limpiador a una consulta");
        System.out.println("[2] Fecha y hora de una cita");
        System.out.println("[3] Consulta de un dentista");
        System.out.println("[4] Paciente de un dentista");
        System.out.println("[5] Sueldo de un limpiador");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuVisualizar() {
        System.out.println("------- VISUALIZAR -------");
        System.out.println("[1] Citas de un paciente entre dos fechas");
        System.out.println("[2]");
        System.out.println("[3]");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }
}
