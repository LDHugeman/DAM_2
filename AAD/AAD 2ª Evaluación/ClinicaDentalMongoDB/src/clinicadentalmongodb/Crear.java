
package clinicadentalmongodb;

import excepciones.Validar;
import java.util.Date;
import objetos.Cita;
import objetos.Consulta;
import objetos.Dentista;
import objetos.Historial;
import objetos.Paciente;
import util.Pedir;

/**
 *
 * @author Alberto y David
 */
public class Crear {
    
    public static Consulta nuevaConsulta() {
        System.out.printf("Número: ");
        int numero = Pedir.numeroEntero();
        boolean quirofano = Pedir.duda("--- Indique si tiene o no quirófano la consulta ---");
        System.out.printf("Piso: ");
        int piso = Pedir.numeroEntero();
        return new Consulta(numero, quirofano, piso);
    }
    
    public static Dentista nuevoDentista() {
        String dni = pedirDni("Dni: ");
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Teléfono: ");
        String telefono = Pedir.texto();
        System.out.printf("Sueldo: ");
        float sueldo = Pedir.numeroRealFloat();
        System.out.printf("Número de la consulta: ");
        int numeroConsulta = Pedir.numeroEntero();
        return new Dentista(dni, nombre, telefono, sueldo, numeroConsulta);
    }
    
    public static Historial nuevoHistorial() {
        System.out.printf("Número del historial: ");
        int numero = Pedir.numeroEntero();
        boolean seguroPrivado = Pedir.duda("--- Indique si tiene o no seguro privado el paciente ---");
        System.out.printf("Grupo sanguíneo: ");
        String grupoSanguineo = Pedir.texto();
        return new Historial(numero, seguroPrivado, grupoSanguineo);
    }     
    
    public static Paciente nuevoPaciente() {
        String dni = pedirDni("Dni del paciente: ");
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Teléfono: ");
        String telefono = Pedir.texto();
        System.out.printf("Número del historial: ");
        int numeroHistorial = Pedir.numeroEntero();
        String dniDentista = pedirDni("Dni del dentista: ");
        return new Paciente(dni, nombre, telefono, numeroHistorial, dniDentista);
    }
    
    public static Cita nuevaCita() {
        System.out.printf("Número de la cita: ");
        int numero = Pedir.numeroEntero();
        System.out.printf("Fecha(dd/MM/yyyy): ");
        Date fecha = Pedir.fecha();
        System.out.printf("Hora(hh:mm): ");
        Date hora = Pedir.hora();
        System.out.printf("Concepto de la cita: ");
        String tipoTrabajo = Pedir.texto();
        String dniPaciente = pedirDni("Dni del paciente: ");
        return new Cita(numero, fecha, hora, tipoTrabajo, dniPaciente);
    }
    
    public static String pedirDni(String mensaje) {
        String dni = "";
        do {
            System.out.printf(mensaje);
            dni = Pedir.texto();
        } while (!Validar.esDniValido(dni));
        return dni;
    }
}
