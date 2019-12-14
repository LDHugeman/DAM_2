
package clinicadental;

import java.text.ParseException;
import java.util.Date;
import objetos.Cita;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.Pedir;

/**
 *
 * @author luisd
 */
public class Modificar {
    
    public static void fechaHoraCita(Cita cita){
        System.out.println("--- Introduzca la nueva fecha para la cita ---");
        System.out.printf("Fecha(dd/MM/yyyy): ");
        Date fecha = Pedir.fecha();
        System.out.println("--- Introduzca la nueva hora para la cita ---");
        System.out.printf("Hora(hh:mm): ");
        Date hora = Pedir.hora();        
        String fechaSQLString = Pedir.FORMATO_ANO_MES_DIA.format(fecha);
        String horaSQLString = Pedir.FORMATO_HORA_SEGUNDOS.format(hora);
        try {
            Date fechaSQL = Pedir.FORMATO_ANO_MES_DIA.parse(fechaSQLString);
            Date horaSQL = Pedir.FORMATO_HORA_SEGUNDOS.parse(horaSQLString);
            Cita citaModificada = new Cita(fechaSQL, horaSQL, cita.getTipoTrabajo(), cita.getHistorial());
            Bajas.eliminar(cita);
            Altas.nuevaCita(citaModificada);
        } catch (ParseException ex) {
            System.out.println("Error al parsear la fecha o la hora");
        }
    }
    
    public static void modificar(Object objeto){
        Session session;
        try{
            session = NewHibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(objeto);
            session.getTransaction().commit();          
        }catch(HibernateException excepcion){
            System.err.println("Error al modificar");
            System.out.println(excepcion.getMessage());
        }
    }
}
