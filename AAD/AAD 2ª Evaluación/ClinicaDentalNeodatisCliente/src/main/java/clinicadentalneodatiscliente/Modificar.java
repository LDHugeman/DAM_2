package clinicadentalneodatiscliente;

import java.util.Date;
import objetos.Cita;
import objetos.Consulta;
import objetos.Dentista;
import objetos.Limpiador;
import objetos.Paciente;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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
        Cita citaModificada = new Cita(fecha, hora, cita.getTipoTrabajo(), cita.getHistorial());
        // Se elimina y vuelve a crear porque fecha y hora son claves primarias
        Bajas.eliminar(cita);
        Altas.guardar(citaModificada);
    }

    public static void consultaDelDentista(Dentista dentista) {
        Visualizar.consultas(Consultar.extraerConsultas());
        System.out.println("--- Introduzca el número de la nueva consulta para el dentista ---");
        System.out.printf("Número: ");
        int numero = Pedir.numeroEntero();
        Consulta consulta = Consultar.encontrarConsultaPorNumero(numero);
        if (consulta != null) {
            dentista.setConsulta(consulta);
            modificar(consulta);
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
            modificar(paciente);
        } else {
            System.err.println("No hay ningún dentista con ese dni");
        }
    }

    public static void sueldoLimpiador(Limpiador limpiador) {
        System.out.println("--- Introduzca el nuevo sueldo para el limpiador ---");
        System.out.printf("Sueldo: ");
        float sueldo = Pedir.numeroRealFloat();
        limpiador.setSueldo(sueldo);
        modificar(limpiador);
    }

    public static void quirofanoConsulta(Consulta consulta) {
        Dentista dentista = Consultar.dentistaDeConsulta(consulta.getNumero());
        if(dentista!=null){
            consulta.addChangeListener(dentista);
        }        
        consulta.setQuirofano(Pedir.duda("¿Desea que la consulta tenga quirófano?"));
        modificar(consulta);
    }

    public static void modificar(Object objeto) {
        Session session;
        try {
            session = NewHibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(objeto);
            session.getTransaction().commit();
        } catch (HibernateException excepcion) {
            System.err.println("Error al modificar");
            System.out.println(excepcion.getMessage());
        }
    }
}
