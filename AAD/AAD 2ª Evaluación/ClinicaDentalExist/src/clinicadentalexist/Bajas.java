package clinicadentalexist;

import util.BaseDatos;

/**
 *
 * @author Alberto y David
 */
public class Bajas {

    public static void eliminarConsulta(int numeroConsulta) {
        String eliminarConsulta = "update delete /consultas/consulta[@idConsulta='" + numeroConsulta + "']";
        BaseDatos.ejecutarAccion(eliminarConsulta);
    }

    public static void eliminarDentista(String dni) {
        String eliminarDentista = "update delete /dentistas/dentista[@dniDentista='" + dni + "']";
        BaseDatos.ejecutarAccion(eliminarDentista);
    }

    public static void eliminarHistorial(int numeroHistorial) {
        String eliminarHistorial = "update delete /historiales/historial[@idHistorial='" + numeroHistorial + "']";
        BaseDatos.ejecutarAccion(eliminarHistorial);
    }

    public static void eliminarPaciente(String dni) {
        String eliminarPaciente = "update delete /pacientes/paciente[@dniPaciente='" + dni + "']";
        BaseDatos.ejecutarAccion(eliminarPaciente);
    }
    
    public static void eliminarCita(int numeroCita) {
        String eliminarCita = "update delete /citas/cita[@idCita='" + numeroCita + "']";
        BaseDatos.ejecutarAccion(eliminarCita);
    }

}
