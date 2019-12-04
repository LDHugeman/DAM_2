
package empresascoches;

import objetos.CocheAlquiler;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author a18luisdvp
 */
public class Bajas {
    
    public static void cocheAlquiler(CocheAlquiler cocheAlquiler){
        eliminar(cocheAlquiler);       
    }
    public static void eliminar(Object objeto){
     Session session;
        try{
            session = NewHibernateUtil.getSession();
            session.beginTransaction();
            session.delete(objeto);
            session.getTransaction().commit();
            session.close();           
        }catch(HibernateException excepcion){
            System.err.println("Error al eliminar");
            System.out.println(excepcion.getMessage());
        }
    }
}
