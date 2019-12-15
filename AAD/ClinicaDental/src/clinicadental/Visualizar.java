package clinicadental;

import java.util.Collection;
import java.util.List;
import objetos.Cita;
import objetos.Consulta;
import objetos.Dentista;
import objetos.Empleado;
import objetos.Limpiador;
import objetos.Paciente;

/**
 *
 * @authors Alberto y David
 */
public class Visualizar {

    public static void empleados(List<Empleado> empleados) {
        System.out.println("------------------------------- EMPLEADOS --------------------------------");
        mostrarPalabrasFila("DNI", "NOMBRE", "TELEFONO", "SUELDO", "PUESTO");
        for (Empleado empleado : empleados) {
            String puesto;
            if (empleado instanceof Dentista) {
                puesto = "Dentista";
            } else {
                puesto = "Limpiador";
            }
            mostrarPalabrasFila(empleado.getDni(), empleado.getNombre(), empleado.getTelefono(), empleado.getSueldo() + "", puesto);
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public static void consultas(List<Consulta> consultas) {
        System.out.println("------------------ CONSULTAS -------------------");
        mostrarPalabrasFila("NÚMERO", "PISO", "QUIRÓFANO");
        for (Consulta consulta : consultas) {
            String quirofano = "No";
            if (consulta.isQuirofano()) {
                quirofano = "Si";
            }
            mostrarPalabrasFila(consulta.getNumero() + "", consulta.getPiso() + "", quirofano);
        }
        System.out.println("------------------------------------------------");
    }

    public static void consultasConQuirófano(List<Consulta> consultas) {
        System.out.println("------------------ CONSULTAS -------------------");
        mostrarPalabrasFila("NÚMERO", "PISO", "QUIRÓFANO");
        for (Consulta consulta : consultas) {
            if (consulta.isQuirofano()) {
                String quirofano = "Si";
                mostrarPalabrasFila(consulta.getNumero() + "", consulta.getPiso() + "", quirofano);
            }
        }
        System.out.println("------------------------------------------------");
    }

    public static void dentistas(List<Dentista> dentistas) {
        System.out.println("---------------------- DENTISTAS -----------------------");
        mostrarPalabrasFila("DNI", "NOMBRE", "TELÉFONO", "SUELDO");
        for (Dentista dentista : dentistas) {
            mostrarPalabrasFila(dentista.getDni(), dentista.getNombre(), dentista.getTelefono(), dentista.getSueldo() + "");
        }
        System.out.println("--------------------------------------------------------");
    }

    public static void citas(Collection<Cita> citas) {
        System.out.println("------------------ CITAS -------------------");
        mostrarPalabrasFila("FECHA", "HORA", "CONCEPTO");
        for (Cita cita : citas) {
            mostrarPalabrasFila(cita.getStringFecha(), cita.getStringHora(), cita.getTipoTrabajo());
        }
        System.out.println("--------------------------------------------");
    }

    public static void pacientes(List<Paciente> pacientes) {
        System.out.println("------------------ PACIENTES -------------------");
        mostrarPalabrasFila("DNI", "NOMBRE", "TELÉFONO");
        for (Paciente paciente : pacientes) {
            mostrarPalabrasFila(paciente.getDni(), paciente.getNombre(), paciente.getTelefono());
        }
        System.out.println("------------------------------------------------");
    }

    public static void limpiadores(List<Limpiador> limpiadores) {
        System.out.println("------------------ LIMPIADORES -------------------");
        mostrarPalabrasFila("DNI", "NOMBRE", "TELÉFONO", "SUELDO");
        for (Limpiador limpiador : limpiadores) {
            mostrarPalabrasFila(limpiador.getDni(), limpiador.getNombre(), limpiador.getTelefono(), limpiador.getSueldo() + "");
        }
        System.out.println("--------------------------------------------------");
    }

    /**
     * Método que muestra una fila de Strings formateada
     *
     * @param valores Pueden entrar uno o más Strings
     */
    public static void mostrarPalabrasFila(String... valores) {
        for (String valor : valores) {
            System.out.printf("%-15s", valor);
        }
        System.out.printf("%n");
    }
}
