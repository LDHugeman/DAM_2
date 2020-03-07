package clinicadentalexist;

import objetos.Cita;
import objetos.Consulta;
import objetos.Dentista;
import objetos.Historial;
import objetos.Paciente;
import util.BaseDatos;

/**
 *
 * @author Alberto y David
 */
public class Altas {

    public static void nuevaConsulta(Consulta consulta) {
        String quirofano;
        if (consulta.isQuirofano()) {
            quirofano = "Si";
        } else {
            quirofano = "No";
        }
        String insertarConsulta = "update insert "
                + "<consulta idConsulta='" + consulta.getNumero() + "'>"
                + "<quirofano>" + quirofano + "</quirofano>"
                + "<piso>" + consulta.getPiso() + "</piso>"
                + "</consulta>"
                + "into /consultas";
        BaseDatos.ejecutarAccion(insertarConsulta);
    }

    public static void nuevoDentista(Dentista dentista) {
        String insertarDentista = "update insert "
                + "<dentista dniDentista='" + dentista.getDni() + "'>"
                + "<nombreDentista>" + dentista.getNombre() + "</nombreDentista>"
                + "<telefonoDentista>" + dentista.getTelefono() + "</telefonoDentista>"
                + "<sueldoDentista>" + dentista.getSueldo() + "</sueldoDentista>"
                + "<numeroConsulta>" + dentista.getNumeroConsulta() + "</numeroConsulta>"
                + "</dentista>"
                + "into /dentistas";
        BaseDatos.ejecutarAccion(insertarDentista);
    }

    public static void nuevoHistorial(Historial historial) {
        String seguroPrivado;
        if (historial.isSeguroPrivado()) {
            seguroPrivado = "Si";
        } else {
            seguroPrivado = "No";
        }
        String insertarHistorial = "update insert "
                + "<historial idHistorial='" + historial.getNumero() + "'>"
                + "<seguroPrivado>" + seguroPrivado + "</seguroPrivado>"
                + "<grupoSanguineo>" + historial.getGrupoSanguineo() + "</grupoSanguineo>"
                + "</historial>"
                + "into /historiales";
        BaseDatos.ejecutarAccion(insertarHistorial);
    }

    public static void nuevoPaciente(Paciente paciente) {
        String insertarPaciente = "update insert "
                + "<paciente dniPaciente='" + paciente.getDni() + "'>"
                + "<nombrePaciente>" + paciente.getNombre() + "</nombrePaciente>"
                + "<telefonoPaciente>" + paciente.getTelefono() + "</telefonoPaciente>"
                + "<historialPaciente>" + paciente.getNumeroHistorial() + "</historialPaciente>"
                + "<dentistaPaciente>" + paciente.getDniDentista() + "</dentistaPaciente>"
                + "</paciente>"
                + "into /pacientes";
        BaseDatos.ejecutarAccion(insertarPaciente);
    }

    public static void nuevaCita(Cita cita) {
        String insertarCita = "update insert "
                + "<cita idCita='" + cita.getNumero() + "'>"
                + "<fechaCita>" + cita.getStringFecha() + "</fechaCita>"
                + "<horaCita>" + cita.getStringHora() + "</horaCita>"
                + "<tipoTrabajo>" + cita.getTipoTrabajo() + "</tipoTrabajo>"
                + "<paciente>" + cita.getDniPaciente() + "</paciente>"
                + "</cita>"
                + "into /citas";
        BaseDatos.ejecutarAccion(insertarCita);
    }
}
