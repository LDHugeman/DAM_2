package clinicadentalneodatiscliente;

import java.util.Date;
import objetos.Cita;
import objetos.Consulta;
import objetos.Dentista;
import objetos.Limpiador;
import objetos.Paciente;
import util.Pedir;

/**
 *
 * @author David y Alberto
 */
public class Modificar {

    public static void fechaHoraCita(Cita cita) {
        System.out.println("--- Introduzca la nueva fecha para la cita ---");
        System.out.printf("Fecha(dd/MM/yyyy): ");
        Date fecha = Pedir.fecha();
        System.out.println("--- Introduzca la nueva hora para la cita ---");
        System.out.printf("Hora(hh:mm): ");
        Date hora = Pedir.hora();
        cita.setFecha(fecha);
        cita.setHora(hora);
        Altas.guardar(cita);
    }

    public static void consultaDelDentista(Dentista dentista) {
        Visualizar.consultas(Consultar.extraerConsultas());
        System.out.println("--- Introduzca el número de la nueva consulta para el dentista ---");
        System.out.printf("Número: ");
        int numero = Pedir.numeroEntero();
        Consulta consulta = Consultar.encontrarConsultaPorNumero(numero);
        if (consulta != null) {
            dentista.setConsulta(consulta);
            Altas.guardar(consulta);
        } else {
            System.err.println("No hay ninguna consulta con ese número");
        }
    }

    public static void dentistaDelPaciente(Paciente paciente) {
        Visualizar.dentistas(Consultar.extraerDentistas());
        System.out.println("--- Introduzca el dni del nuevo dentista para el paciente ---");
        String dni = Crear.pedirDni("Dni del dentista: ");
        Dentista dentista = Consultar.encontrarDentistaPorDni(dni);
        if (dentista != null) {
            paciente.setDentista(dentista);
            Altas.guardar(paciente);
        } else {
            System.err.println("No hay ningún dentista con ese dni");
        }
    }

    public static void sueldoLimpiador(Limpiador limpiador) {
        System.out.println("--- Introduzca el nuevo sueldo para el limpiador ---");
        System.out.printf("Sueldo: ");
        float sueldo = Pedir.numeroRealFloat();
        limpiador.setSueldo(sueldo);
        Altas.guardar(limpiador);
    }
}
