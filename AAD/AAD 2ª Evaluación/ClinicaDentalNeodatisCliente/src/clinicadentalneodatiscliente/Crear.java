package clinicadentalneodatiscliente;

import excepciones.Validar;
import java.util.Date;
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
public class Crear {

    public static Dentista nuevoDentista(Consulta consulta) {
        String dni = pedirDni("Dni: ");
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Teléfono: ");
        String telefono = Pedir.texto();
        System.out.printf("Sueldo: ");
        float sueldo = Pedir.numeroRealFloat();
        return new Dentista(dni, nombre, telefono, sueldo, consulta);
    }

    public static Limpiador nuevoLimpiador() {
        String dni = pedirDni("Dni: ");
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Teléfono: ");
        String telefono = Pedir.texto();
        System.out.printf("Sueldo: ");
        float sueldo = Pedir.numeroRealFloat();
        return new Limpiador(dni, nombre, telefono, sueldo);
    }

    public static Paciente nuevoPaciente(Historial historial, Dentista dentista) {
        String dni = pedirDni("Dni: ");
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Teléfono: ");
        String telefono = Pedir.texto();
        return new Paciente(dni, nombre, telefono, historial, dentista);
    }

    public static Consulta nuevaConsulta() {
        System.out.printf("Número: ");
        int numero = Pedir.numeroEntero();
        boolean quirofano = Pedir.duda("--- Indique si tiene o no quirófano la consulta ---");
        System.out.printf("Piso: ");
        int piso = Pedir.numeroEntero();
        return new Consulta(numero, quirofano, piso);
    }

    public static Cita nuevaCita(Historial historial) {
        System.out.printf("Fecha(dd/MM/yyyy): ");
        Date fecha = Pedir.fecha();
        System.out.printf("Hora(hh:mm): ");
        Date hora = Pedir.hora();
        System.out.printf("Concepto de la cita: ");
        String tipoTrabajo = Pedir.texto();
        return new Cita(fecha, hora, tipoTrabajo, historial);
    }

    public static Historial nuevoHistorial() {
        System.out.printf("Código: ");
        int codigo = Pedir.numeroEntero();
        boolean seguroPrivado = Pedir.duda("--- Indique si tiene o no seguro privado el paciente ---");
        System.out.printf("Grupo sanguíneo: ");
        String grupoSanguineo = Pedir.texto();
        return new Historial(codigo, seguroPrivado, grupoSanguineo);
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
