package clinicadentalexist;

import util.BaseDatos;

/**
 *
 * @author Alberto y David
 */
public class Visualizar {

    public static void consultas() {
        String consultas = "for $consulta in /consultas/consulta return $consulta";
        BaseDatos.ejecutarVisualizar(consultas);
    }

    public static void consulta(int numeroConsulta) {
        String consulta = "for $consulta in /consultas/consulta[@idConsulta='" + numeroConsulta + "'] return $consulta";
        BaseDatos.ejecutarVisualizar(consulta);
    }

    public static void dentistas() {
        String dentistas = "for $dentista in /dentistas/dentista return $dentista";
        BaseDatos.ejecutarVisualizar(dentistas);
    }

    public static void dentista(String dni) {
        String dentista = "for $dentista in /dentistas/dentista[@dniDentista='" + dni + "'] return $dentista";
        BaseDatos.ejecutarVisualizar(dentista);
    }
    
    public static void pacientes() {
        String pacientes = "for $paciente in /pacientes/paciente return $paciente";
        BaseDatos.ejecutarVisualizar(pacientes);
    }
    
    public static void paciente(String dni) {
        String paciente = "for $paciente in /pacientes/paciente[@dniPaciente='" + dni + "'] return $paciente";
        BaseDatos.ejecutarVisualizar(paciente);
    }

    public static void historiales() {
        String historiales = "for $historial in /historiales/historial return $historial";
        BaseDatos.ejecutarVisualizar(historiales);
    }

    public static void historial(int numeroHistorial) {
        String historial = "for $historial in /historiales/historial[@idHistorial='" + numeroHistorial + "'] return $historial";
        BaseDatos.ejecutarVisualizar(historial);
    }

    public static void citas() {
        String citas = "for $cita in /citas/cita return $cita";
        BaseDatos.ejecutarVisualizar(citas);
    }

    public static void cita(int numeroCita) {
        String cita = "for $cita in /citas/cita[@idCita='" + numeroCita + "'] return $cita";
        BaseDatos.ejecutarVisualizar(cita);
    }
}
