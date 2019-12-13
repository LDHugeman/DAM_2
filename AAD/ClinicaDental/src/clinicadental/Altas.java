package clinicadental;
import objetos.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
/**
 *
 * @author a16albertolg
 */
public class Altas {
    public static void nuevoLimpiador(Limpiador limpiador){
        guardar(limpiador);
    }
    public static void nuevoDentista(Dentista dentista){
        guardar(dentista);
    }
    public static void nuevaConsulta(Consulta consulta){
        guardar(consulta);
    }
    public static void nuevoPaciente(Paciente paciente){
        guardar(paciente);
    }
    public static void nuevoHistorial(Historial historial){
        guardar(historial);
    }
    public static void nuevaCita(Cita cita){
        guardar(cita);
    }
    public static void guardar(Object objeto){
        Session session;
        try{
            session = NewHibernateUtil.getSession();
            session.beginTransaction();
            session.save(objeto);
            session.getTransaction().commit();
            session.close();
        }catch(HibernateException excepcion){
            System.err.println("Error al guardar");
            System.out.println(excepcion.getMessage());
        }
    }
}
