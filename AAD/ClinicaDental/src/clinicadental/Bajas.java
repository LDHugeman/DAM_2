
package clinicadental;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author a18luisdvp
 */
public class Bajas {
    
    public static void eliminar(Object objeto){
     Session session;
        try{
            session = NewHibernateUtil.getSession();
            session.beginTransaction();
            session.delete(objeto);
            session.getTransaction().commit();
        }catch(HibernateException excepcion){
            System.err.println("Error al eliminar");
            System.out.println(excepcion.getMessage());
        }
    }
}
