package clinicadental;
import org.hibernate.HibernateException;
import org.hibernate.Session;
/**
 *
 * @authors Alberto y David
 */
public class Altas {

    public static void guardar(Object objeto){
        Session session;
        try{
            session = NewHibernateUtil.getSession();
            session.beginTransaction();
            session.save(objeto);
            session.getTransaction().commit();                   
        }catch(HibernateException excepcion){
            System.err.println("Error al guardar");
            System.out.println(excepcion.getMessage());
        }
    }
}
