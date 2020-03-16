package clinicadentalmongodb;

import java.util.List;
import org.bson.Document;

/**
 *
 * @author Alberto y David
 */
public class Visualizar {

    public static void consultas(List<Document> consultas) {
        System.out.println("------------------ CONSULTAS -------------------");
        mostrarPalabrasFila("NÚMERO", "PISO", "QUIRÓFANO");
        for (Document consulta : consultas) {
            mostrarPalabrasFila(consulta.get("idConsulta") + "", consulta.get("piso") + "", consulta.get("quirofano")+"");
        }
        System.out.println("------------------------------------------------");
    }

    public static void mostrarConsulta(Document consulta) {
        System.out.println("------------------ CONSULTA -------------------");
        System.out.println("Número: " + consulta.get("idConsulta"));
        System.out.println("Piso: " + consulta.get("piso"));
        System.out.println("Quirófano: " + consulta.get("quirofano"));
        System.out.println("-----------------------------------------------");
    }

    public static void dentistas(List<Document> dentistas) {
        System.out.println("---------------------- DENTISTAS -----------------------");
        mostrarPalabrasFila("DNI", "NOMBRE", "TELÉFONO", "SUELDO");
        for (Document dentista : dentistas) {
            mostrarPalabrasFila(dentista.get("dniDentista") + "", dentista.get("nombreDentista") + "",
                    dentista.get("telefonoDentista") + "", dentista.get("sueldoDentista") + "");
        }
        System.out.println("--------------------------------------------------------");
    }

    public static void mostrarDentista(Document dentista) {
        System.out.println("------------------ DENTISTA -------------------");
        System.out.println("Dni: " + dentista.get("dniDentista"));
        System.out.println("Nombre: " + dentista.get("nombreDentista"));
        System.out.println("Teléfono: " + dentista.get("telefonoDentista"));
        System.out.println("Sueldo: " + dentista.get("sueldoDentista"));
        System.out.println("-----------------------------------------------");
    }

    public static void pacientes(List<Document> pacientes) {
        System.out.println("---------------------------- PACIENTES -----------------------------");
        mostrarPalabrasFila("DNI", "NOMBRE", "TELÉFONO", "HISTORIAL", "DENTISTA");
        for (Document paciente : pacientes) {
            mostrarPalabrasFila(paciente.get("dniPaciente") + "", paciente.get("nombrePaciente") + "",
                    paciente.get("telefonoPaciente") + "", paciente.get("historialPaciente") + "", paciente.get("dentistaPaciente") + "");
        }
        System.out.println("--------------------------------------------------------------------");
    }

    public static void mostrarPaciente(Document paciente) {
        System.out.println("------------------ PACIENTE -------------------");
        System.out.println("Dni: " + paciente.get("dniPaciente"));
        System.out.println("Nombre: " + paciente.get("nombrePaciente"));
        System.out.println("Teléfono: " + paciente.get("telefonoPaciente"));
        System.out.println("Historial: " + paciente.get("historialPaciente"));
        System.out.println("Dentista: " + paciente.get("dentistaPaciente"));
        System.out.println("-----------------------------------------------");
    }

    public static void historiales(List<Document> historiales) {
        System.out.println("------------------ HISTORIALES -------------------");
        mostrarPalabrasFila("NÚMERO", "SEGURO PRIVADO", "GRUPO SANGUÍNEO");
        for (Document historial : historiales) {
            mostrarPalabrasFila(historial.get("idHistorial") + "", historial.get("seguroPrivado")+"", historial.get("grupoSanguineo") + "");
        }
        System.out.println("------------------------------------------------");
    }
    
    public static void mostrarHistorial(Document historial) {
        System.out.println("------------------ HISTORIAL -------------------");
        System.out.println("Número: " + historial.get("idHistorial"));
        System.out.println("Seguro Privado: " + historial.get("seguroPrivado"));
        System.out.println("Grupo Sanguíneo: " + historial.get("grupoSanguíneo"));
        System.out.println("-----------------------------------------------");
    }
    
    public static void citas(List<Document> citas) {
        System.out.println("---------------------------- CITAS -----------------------------");
        mostrarPalabrasFila("NÚMERO","FECHA", "HORA", "CONCEPTO", "PACIENTE");
        for (Document cita : citas) {
            mostrarPalabrasFila(cita.get("idCita")+"", cita.get("fechaCita")+"", cita.get("horaCita")+"",
                    cita.get("tipoTrabajo")+"",cita.get("paciente")+"");
        }
        System.out.println("----------------------------------------------------------------");
    }
    
    public static void mostrarCita(Document cita) {
        System.out.println("------------------ CITA -------------------");
        System.out.println("Número: " + cita.get("idCita"));
        System.out.println("Fecha(dd/MM/yyyy): " + cita.get("fechaCita"));
        System.out.println("Hora(HH:mm): " + cita.get("horaCita"));
        System.out.println("Concepto: " + cita.get("tipoTrabajo"));
        System.out.println("Paciente: " + cita.get("paciente"));
        System.out.println("-------------------------------------------");
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
