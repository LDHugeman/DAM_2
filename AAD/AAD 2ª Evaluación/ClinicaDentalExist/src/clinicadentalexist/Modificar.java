package clinicadentalexist;

import java.util.Date;
import util.BaseDatos;
import util.Pedir;

/**
 *
 * @author Alberto y David
 */
public class Modificar {

    public static void sueldoDentista(String dni) {
        System.out.println("--- Introduzca el nuevo sueldo para el dentista ---");
        System.out.printf("Sueldo: ");
        float sueldo = Pedir.numeroRealFloat();
        String modificarSueldoDentista = "update value /dentistas/dentista[@dniDentista='" + dni + "']/sueldoDentista with '" + sueldo + "'";
        BaseDatos.ejecutarAccion(modificarSueldoDentista);
    }

    public static void fechaHoraCita(int numeroCita) {
        System.out.println("--- Introduzca la nueva fecha para la cita ---");
        System.out.printf("Fecha(dd/MM/yyyy): ");
        Date fecha = Pedir.fecha();
        System.out.println("--- Introduzca la nueva hora para la cita ---");
        System.out.printf("Hora(hh:mm): ");
        Date hora = Pedir.hora();
        String modificarFechaCita = "update value /citas/cita[@idCita='" + numeroCita + "']/fechaCita with '" + Pedir.FORMATO_DIA_MES_ANO.format(fecha) + "'";
        String modificarHoraCita = "update value /citas/cita[@idCita='" + numeroCita + "']/horaCita with '" + Pedir.FORMATO_HORA.format(hora) + "'";
        BaseDatos.ejecutarAccion(modificarFechaCita);
        BaseDatos.ejecutarAccion(modificarHoraCita);
    }

    public static void telefonoPaciente(String dni) {
        System.out.println("--- Introduzca el nuevo teléfono para el paciente ---");
        System.out.printf("Teléfono: ");
        String telefono = Pedir.texto();
        String modificarTelefonoPaciente = "update value /pacientes/paciente[@dniPaciente='" + dni + "']/telefonoPaciente with '" + telefono + "'";
        BaseDatos.ejecutarAccion(modificarTelefonoPaciente);
    }

    public static void quirofanoConsulta(int numeroConsulta) {
        String quirofano;
        if (Pedir.duda("¿Ahora la consulta tiene quirófano?")) {
            quirofano = "Si";
        } else {
            quirofano = "No";
        }
        String modificarQuirofanoConsulta = "update value /consultas/consulta[@idConsulta='" + numeroConsulta + "']/quirofano with '" + quirofano + "'";
        BaseDatos.ejecutarAccion(modificarQuirofanoConsulta);
    }
    
    public static void grupoSanguineoHistorial(int numeroHistorial) {
        System.out.println("--- Introduzca el nuevo grupo sanguíneo para el historial del paciente ---");
        System.out.printf("Grupo sanguíneo: ");
        String grupoSanguineo = Pedir.texto();
        String modificarGrupoSanguineoHistorial = "update value /historiales/historial[@idHistorial='" + numeroHistorial + "']/grupoSanguineo with '" + grupoSanguineo + "'";
        BaseDatos.ejecutarAccion(modificarGrupoSanguineoHistorial);
    }
}
