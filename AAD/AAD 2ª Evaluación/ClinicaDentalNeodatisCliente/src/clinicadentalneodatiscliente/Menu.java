package clinicadentalneodatiscliente;

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
 * @author David y Alberto
 */
public class Menu {

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
                    altaPaciente();
                    break;
                case 4:
                    altaCita();
                    break;
                case 5:
                    altaLimpiador();
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
                    bajaPaciente();
                    break;
                case 2:
                    bajaDentista();
                    break;
                case 3:
                    bajaConsulta();
                    break;
                case 4:
                    bajaCita();
                    break;
                case 5:
                    bajaLimpiador();
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
                    añadirLimpiadorConsulta();
                    break;
                case 2:
                    modificarFechaHoraCita();
                    break;
                case 3:
                    modificarConsultaDentista();
                    break;
                case 4:
                    modificarDentistaPaciente();
                    break;
                case 5:
                    modificarSueldoLimpiador();
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
                    visualizarCitasEntreFechas();
                    break;
                case 2:
                    Visualizar.empleados(Consultar.extraerEmpleados());
                    break;
                case 3:
                    Visualizar.consultasConQuirofano(Consultar.extraerConsultas());
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
        System.out.println("[1] Consulta");
        System.out.println("[2] Dentista");
        System.out.println("[3] Paciente");
        System.out.println("[4] Cita");
        System.out.println("[5] Limpiador");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    private static void altaConsulta() {
        Consulta consulta = Crear.nuevaConsulta();
        if (!Consultar.existeConsultaPorNumero(consulta.getNumero())) {
            Altas.guardar(consulta);
        } else {
            System.err.println("Ya existe una consulta con ese número");
        }
        Conexion.closeSession();
    }

    private static void altaDentista() {
        List<Consulta> consultas = Consultar.extraerConsultas();
        if (!consultas.isEmpty()) {
            Visualizar.consultas(consultas);
            System.out.println("--- Seleccione una consulta para el dentista ---");
            System.out.printf("Número de la consulta: ");
            int numero = Pedir.numeroEntero();
            Consulta consulta = Consultar.encontrarConsultaPorNumero(numero);
            if (consulta != null) {
                Dentista dentista = Crear.nuevoDentista(consulta);
                if (!Consultar.existeDentistaPorDni(dentista.getDni())) {
                    Altas.guardar(dentista);
                } else {
                    System.err.println("Ya existe un dentista con ese dni");
                }
            } else {
                System.err.println("No hay ninguna consulta con ese número");
            }
        } else {
            System.err.println("Debe crear antes una consulta");
        }
        Conexion.closeSession();
    }

    private static void altaPaciente() {
        List<Dentista> dentistas = Consultar.extraerDentistas();
        if (!dentistas.isEmpty()) {
            Visualizar.dentistas(dentistas);
            System.out.println("--- Seleccione un dentista para el paciente ---");
            String dni = Crear.pedirDni("Dni del dentista: ");
            Dentista dentista = Consultar.encontrarDentistaPorDni(dni);
            if (dentista != null) {
                System.out.println("--- Datos del historial ---");
                Historial historial = Crear.nuevoHistorial();
                if (!Consultar.existeHistorialPorCodigo(historial.getCodigo())) {
                    System.out.println("--- Datos del paciente ---");
                    Paciente paciente = Crear.nuevoPaciente(historial, dentista);
                    if (!Consultar.existePacientePorDni(paciente.getDni())) {
                        Altas.guardar(paciente);
                        Altas.guardar(historial);
                    } else {
                        System.err.println("Ya existe un paciente con ese dni");
                    }
                } else {
                    System.err.println("Ya existe un historial con ese código");
                }
            } else {
                System.err.println("No hay ningún dentista con ese dni");
            }
        } else {
            System.err.println("Debe crear antes un dentista");
        }
        Conexion.closeSession();
    }

    private static void altaCita() {
        List<Paciente> pacientes = Consultar.extraerPacientes();
        if (!pacientes.isEmpty()) {
            Visualizar.pacientes(pacientes);
            System.out.println("--- Seleccione el paciente al que quiere añadirle una cita ---");
            String dni = Crear.pedirDni("Dni: ");
            Paciente paciente = Consultar.encontrarPacientePorDni(dni);
            if (paciente != null) {
                Historial historial = paciente.getHistorial();
                Cita cita = Crear.nuevaCita(historial);
                if (!Consultar.existeCitaPorFecha(cita.getFecha())) {
                    historial.getCitas().add(cita);
                    Altas.guardar(cita);
                } else {
                    System.err.println("Ya existe una cita en esa fecha");
                }
            } else {
                System.err.println("No hay ningún paciente con ese dni");
            }
        } else {
            System.err.println("Debe crear antes un paciente");
        }
        Conexion.closeSession();
    }

    private static void altaLimpiador() {
        Limpiador limpiador = Crear.nuevoLimpiador();
        if (!Consultar.existeLimpiadorPorDni(limpiador.getDni())) {
            Altas.guardar(limpiador);
        } else {
            System.err.println("Ya existe un limpiador con ese dni");
        }
        Conexion.closeSession();
    }

    private static byte seleccionarOpcionMenuBajas() {
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

    private static void bajaPaciente() {
        Visualizar.pacientes(Consultar.extraerPacientes());
        System.out.println("--- Seleccione el dni del paciente que desea dar de baja ---");
        String dni = Crear.pedirDni("Dni del paciente: ");
        Paciente paciente = Consultar.encontrarPacientePorDni(dni);
        if (paciente != null) {
            if (Pedir.duda("¿Está seguro de que quiere eliminar este paciente?")) {
                Bajas.eliminar(paciente);
                for (Cita cita : paciente.getHistorial().getCitas()) {
                    Bajas.eliminar(cita);
                }
                Bajas.eliminar(paciente.getHistorial());
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningun paciente con ese dni");
        }
        Conexion.closeSession();
    }

    private static void bajaDentista() {
        Visualizar.dentistas(Consultar.extraerDentistas());
        System.out.println("--- Seleccione el dni del dentista que desea dar de baja ---");
        String dni = Crear.pedirDni("Dni del dentista: ");
        Dentista dentista = Consultar.encontrarDentistaPorDni(dni);
        if (dentista != null) {
            if (Pedir.duda("¿Está seguro de que quiere eliminar este dentista?")) {
                Bajas.eliminar(dentista);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningun dentista con ese dni");
        }
        Conexion.closeSession();
    }

    private static void bajaConsulta() {
        Visualizar.consultas(Consultar.extraerConsultas());
        System.out.println("--- Seleccione el numero de consulta que desea dar de baja ---");
        System.out.printf("Número: ");
        int numero = Pedir.numeroEntero();
        Consulta consulta = Consultar.encontrarConsultaPorNumero(numero);
        if (consulta != null) {
            if (Pedir.duda("¿Está seguro de que quiere eliminar esta consulta?")) {
                Bajas.eliminar(consulta);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ninguna consulta con ese numero");
        }
        Conexion.closeSession();
    }

    private static void bajaCita() {
        List<Paciente> pacientes = Consultar.extraerPacientes();
        if (!pacientes.isEmpty()) {
            Visualizar.pacientes(pacientes);
            System.out.println("--- Seleccione el paciente del que desea dar de baja la cita ---");
            String dni = Crear.pedirDni("Dni del paciente: ");
            Paciente paciente = Consultar.encontrarPacientePorDni(dni);
            if (paciente != null) {
                Set<Cita> citas = paciente.getHistorial().getCitas();
                if (!citas.isEmpty()) {
                    Visualizar.citas(citas);
                    System.out.println("--- Seleccione la fecha de la cita que desea dar de baja ---");
                    System.out.printf("Fecha(dd/MM/yyyy): ");
                    Date fecha = Pedir.fecha();
                    Cita cita = Consultar.encontrarCitaPorFecha(fecha);
                    if (cita != null) {
                        if (Pedir.duda("¿Está seguro de que quiere eliminar esta cita?")) {
                            Bajas.eliminar(cita);
                            paciente.getHistorial().getCitas().remove(cita);
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
        }else {
            System.err.println("No hay ningún paciente por lo tanto no hay citas");
        }
        Conexion.closeSession();
    }

    private static void bajaLimpiador() {
        Visualizar.limpiadores(Consultar.extraerLimpiadores());
        System.out.println("--- Seleccione el dni del limpiador que desea dar de baja ---");
        String dni = Crear.pedirDni("Dni del limpiador: ");
        Limpiador limpiador = Consultar.encontrarLimpiadorPorDni(dni);
        if (limpiador != null) {
            if (Pedir.duda("¿Está seguro de que quiere eliminar este limpiador?")) {
                Bajas.eliminar(limpiador);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningun limpiador con ese dni");
        }
        Conexion.closeSession();
    }

    private static byte seleccionarOpcionMenuModificaciones() {
        System.out.println("------- MODIFICACIONES -------");
        System.out.println("[1] Añadir un limpiador a una consulta");
        System.out.println("[2] Fecha y hora de una cita");
        System.out.println("[3] Consulta de un dentista");
        System.out.println("[4] Dentista de un paciente");
        System.out.println("[5] Sueldo de un limpiador");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    private static void añadirLimpiadorConsulta() {
        Visualizar.consultas(Consultar.extraerConsultas());
        System.out.println("--- Seleccione el número de la consulta ---");
        System.out.printf("Número: ");
        int numero = Pedir.numeroEntero();
        Consulta consulta = Consultar.encontrarConsultaPorNumero(numero);
        if (consulta != null) {
            Visualizar.limpiadores(Consultar.extraerLimpiadores());
            System.out.println("--- Seleccione el dni del limpiador ---");
            String dni = Crear.pedirDni("Dni: ");
            Limpiador limpiador = Consultar.encontrarLimpiadorPorDni(dni);
            if (limpiador != null) {
                consulta.getLimpiadores().add(limpiador);
                limpiador.getConsultas().add(consulta);
                Altas.guardar(consulta);
            } else {
                System.err.println("No hay ningún limpiador con ese dni");
            }
        } else {
            System.err.println("No hay ninguna consulta con ese número");
        }
        Conexion.closeSession();
    }

    private static void modificarFechaHoraCita() {
        Visualizar.pacientes(Consultar.extraerPacientes());
        System.out.println("--- Seleccione el paciente del que desea modificar una cita ---");
        String dni = Crear.pedirDni("Dni del paciente: ");
        Paciente paciente = Consultar.encontrarPacientePorDni(dni);
        if (paciente != null) {
            Set<Cita> citas = paciente.getHistorial().getCitas();
            if (!citas.isEmpty()) {
                Visualizar.citas(citas);
                System.out.println("--- Seleccione la fecha de la cita que desea modificar ---");
                System.out.printf("Fecha(dd/MM/yyyy): ");
                Date fecha = Pedir.fecha();
                Cita cita = Consultar.encontrarCitaPorFecha(fecha);
                if (cita != null) {
                    Modificar.fechaHoraCita(cita);
                } else {
                    System.err.println("No existe ninguna cita con esos datos");
                }
            } else {
                System.err.println("Este paciente no tiene ninguna cita");
            }
        } else {
            System.err.println("No hay ningún paciente con ese dni");
        }
        Conexion.closeSession();
    }

    private static void modificarConsultaDentista() {
        Visualizar.dentistas(Consultar.extraerDentistas());
        System.out.println("--- Seleccione el dni del dentista al que desea modificar su consulta ---");
        String dni = Crear.pedirDni("Dni del dentista: ");
        Dentista dentista = Consultar.encontrarDentistaPorDni(dni);
        if (dentista != null) {
            Modificar.consultaDelDentista(dentista);
        } else {
            System.err.println("No hay ningún dentista con ese dni");
        }
        Conexion.closeSession();
    }

    private static void modificarDentistaPaciente() {
        Visualizar.pacientes(Consultar.extraerPacientes());
        System.out.println("--- Seleccione el paciente al que desea modificar su dentista ---");
        String dni = Crear.pedirDni("Dni del paciente: ");
        Paciente paciente = Consultar.encontrarPacientePorDni(dni);
        if (paciente != null) {
            Modificar.dentistaDelPaciente(paciente);
        } else {
            System.err.println("No hay ningún paciente con ese dni");
        }
        Conexion.closeSession();
    }

    private static void modificarSueldoLimpiador() {
        Visualizar.limpiadores(Consultar.extraerLimpiadores());
        System.out.println("--- Seleccione el limpiador al que desea modificar su sueldo ---");
        String dni = Crear.pedirDni("Dni del limpiador: ");
        Limpiador limpiador = Consultar.encontrarLimpiadorPorDni(dni);
        if (limpiador != null) {
            Modificar.sueldoLimpiador(limpiador);
        } else {
            System.err.println("No hay ningún limpiador con ese dni");
        }
        Conexion.closeSession();
    }

    private static byte seleccionarOpcionMenuVisualizar() {
        System.out.println("------- VISUALIZAR -------");
        System.out.println("[1] Citas entre dos fechas");
        System.out.println("[2] Todos los empleados");
        System.out.println("[3] Consultas que tienen quirófano");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    private static void visualizarCitasEntreFechas() {
        System.out.println("--- Introduzca las fechas entre las que desea ver las citas ---");
        System.out.printf("Primer fecha(dd/MM/yyyy): ");
        Date primerFecha = Pedir.fecha();
        System.out.printf("Segunda fecha(dd/MM/yyyy): ");
        Date segundaFecha = Pedir.fecha();
        List<Cita> citas = Consultar.citasEntreFechas(primerFecha, segundaFecha);
        if (!citas.isEmpty()) {
            Visualizar.citas(citas);
        } else {
            System.err.println("No hay citas entre esas dos fechas");
        }
    }
}
