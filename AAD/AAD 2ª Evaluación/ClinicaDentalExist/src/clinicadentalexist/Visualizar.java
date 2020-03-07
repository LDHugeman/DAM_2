package clinicadentalexist;

import util.BaseDatos;

/**
 *
 * @author Alberto y David
 */
public class Visualizar {

    public static void consultas() {
        String visualizarConsultas = "for $consulta in /consultas/consulta return $consulta";
        BaseDatos.ejecutarVisualizar(visualizarConsultas);
    }

    public static void consulta(int numeroConsulta) {
        String visualizarConsulta = "for $consulta in /consultas/consulta[@idConsulta='" + numeroConsulta + "'] return $consulta";
        BaseDatos.ejecutarVisualizar(visualizarConsulta);
    }

    public static void dentistas() {
        String visualizarDentistas = "for $dentista in /dentistas/dentista return $dentista";
        BaseDatos.ejecutarVisualizar(visualizarDentistas);
    }

    public static void dentista(String dni) {
        String visualizarDentista = "for $dentista in /dentistas/dentista[@dniDentista='" + dni + "'] return $dentista";
        BaseDatos.ejecutarVisualizar(visualizarDentista);
    }
    
    public static void pacientes() {
        String visualizarPacientes = "for $paciente in /pacientes/paciente return $paciente";
        BaseDatos.ejecutarVisualizar(visualizarPacientes);
    }
    
    public static void paciente(String dni) {
        String visualizarPaciente = "for $paciente in /pacientes/paciente[@dniPaciente='" + dni + "'] return $paciente";
        BaseDatos.ejecutarVisualizar(visualizarPaciente);
    }

    public static void historiales() {
        String visualizarHistoriales = "for $historial in /historiales/historial return $historial";
        BaseDatos.ejecutarVisualizar(visualizarHistoriales);
    }

    public static void historial(int numeroHistorial) {
        String visualizarHistorial = "for $historial in /historiales/historial[@idHistorial='" + numeroHistorial + "'] return $historial";
        BaseDatos.ejecutarVisualizar(visualizarHistorial);
    }

    public static void citas() {
        String visualizarCitas = "for $cita in /citas/cita return $cita";
        BaseDatos.ejecutarVisualizar(visualizarCitas);
    }

    public static void cita(int numeroCita) {
        String visualizarCita = "for $cita in /citas/cita[@idCita='" + numeroCita + "'] return $cita";
        BaseDatos.ejecutarVisualizar(visualizarCita);
    }
}
