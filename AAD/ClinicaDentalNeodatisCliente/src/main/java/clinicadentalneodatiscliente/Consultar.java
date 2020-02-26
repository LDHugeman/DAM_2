package clinicadental;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import objetos.Cita;
import objetos.Consulta;
import objetos.Dentista;
import objetos.Empleado;
import objetos.Historial;
import objetos.Limpiador;
import objetos.Paciente;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.Pedir;

/**
 *
 * @authors Alberto y David
 */
public class Consultar {

    public static List extraerConsultas() {
        List<Consulta> consultas = new ArrayList();
        try {
            Session session = NewHibernateUtil.getSession();
            consultas = session.createCriteria(Consulta.class).list();
        } catch (HibernateException excepcion) {
            System.err.println("Error al extraer las consultas");
            System.out.println(excepcion.getMessage());
        }
        return consultas;
    }

    public static List extraerDentistas() {
        List<Dentista> dentistas = new ArrayList();
        try {
            Session session = NewHibernateUtil.getSession();
            dentistas = session.createCriteria(Dentista.class).list();
        } catch (HibernateException excepcion) {
            System.err.println("Error al extraer los dentistas");
            System.out.println(excepcion.getMessage());
        }
        return dentistas;
    }

    public static List extraerPacientes() {
        List<Paciente> pacientes = new ArrayList();
        try {
            Session session = NewHibernateUtil.getSession();
            pacientes = session.createCriteria(Paciente.class).list();
        } catch (HibernateException excepcion) {
            System.err.println("Error al extraer los pacientes");
            System.out.println(excepcion.getMessage());
        }
        return pacientes;
    }

    public static List extraerLimpiadores() {
        List<Limpiador> limpiadores = new ArrayList();
        try {
            Session session = NewHibernateUtil.getSession();
            limpiadores = session.createCriteria(Limpiador.class).list();
        } catch (HibernateException excepcion) {
            System.err.println("Error al extraer los limpiadores");
            System.out.println(excepcion.getMessage());
        }
        return limpiadores;
    }

    public static List extraerCitas() {
        List<Cita> citas = new ArrayList();
        try {
            Session session = NewHibernateUtil.getSession();
            citas = session.createCriteria(Cita.class).list();
        } catch (HibernateException excepcion) {
            System.err.println("Error al extraer las citas");
            System.out.println(excepcion.getMessage());
        }
        return citas;
    }

    public static List extraerEmpleados() {
        List<Empleado> empleados = new ArrayList();
        try {
            Session session = NewHibernateUtil.getSession();
            empleados = session.createCriteria(Empleado.class).list();
        } catch (HibernateException excepcion) {
            System.err.println("Error al extraer los empleados");
            System.out.println(excepcion.getMessage());
        }
        return empleados;
    }

    public static Consulta encontrarConsultaPorNumero(int numeroConsulta) {
        Consulta consulta = null;
        try {
            Session session = NewHibernateUtil.getSession();
            consulta = (Consulta) session.get(Consulta.class, numeroConsulta);
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar la consulta");
            System.out.println(excepcion.getMessage());
        }
        return consulta;
    }

    public static Dentista encontrarDentistaPorDni(String dni) {
        Dentista dentista = null;
        try {
            Session session = NewHibernateUtil.getSession();
            dentista = (Dentista) session.get(Dentista.class, dni);
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar el dentista");
            System.out.println(excepcion.getMessage());
        }
        return dentista;
    }

    public static Paciente encontrarPacientePorDni(String dni) {
        Paciente paciente = null;
        try {
            Session session = NewHibernateUtil.getSession();
            paciente = (Paciente) session.get(Paciente.class, dni);
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar el paciente");
            System.out.println(excepcion.getMessage());
        }
        return paciente;
    }

    public static Limpiador encontrarLimpiadorPorDni(String dni) {
        Limpiador limpiador = null;
        try {
            Session session = NewHibernateUtil.getSession();
            limpiador = (Limpiador) session.get(Limpiador.class, dni);
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar el limpiador");
            System.out.println(excepcion.getMessage());
        }
        return limpiador;
    }

    public static Historial encontrarHistorialPorCodigo(int codigo) {
        Historial historial = null;
        try {
            Session session = NewHibernateUtil.getSession();
            historial = (Historial) session.get(Historial.class, codigo);
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar el historial");
            System.out.println(excepcion.getMessage());
        }
        return historial;
    }

    public static Cita encontrarCitaPorFechaHoraHistorial(int codigo, Date fecha, Date hora) {
        Cita cita = null;
        try {
            Session session = NewHibernateUtil.getSession();
            cita = (Cita) session.createQuery("FROM objetos.Cita WHERE (fecha = '" 
                    + Pedir.FORMATO_ANO_MES_DIA.format(fecha) + "') AND (hora = '" 
                    + Pedir.FORMATO_HORA_SEGUNDOS.format(hora) + "') AND historial = (" + codigo + ")").uniqueResult();
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar la cita");
            System.out.println(excepcion.getMessage());
        }
        return cita;
    }

    public static List<Cita> citasEntreFechas(Date primerFecha, Date segundaFecha) {
        List<Cita> citas = new ArrayList<>();
        try {
            Session session = NewHibernateUtil.getSession();
            citas = session.createCriteria(Cita.class).add(Restrictions.between("fecha", primerFecha, segundaFecha)).list();
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar las citas");
            System.out.println(excepcion.getMessage());
        }
        return citas;
    }

    public static Dentista dentistaDeConsulta(int numeroConsulta) {
        Dentista dentista = null;
        try {
            Session session = NewHibernateUtil.getSession();
            dentista = (Dentista) session.createQuery("FROM objetos.Dentista WHERE (consulta ='" + numeroConsulta + "')").uniqueResult();
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar dentista por consulta");
            System.out.println(excepcion.getMessage());
        }
        return dentista;
    }
}
