
package institutosciclostalleres;

import objetos.Ciclo;
import objetos.Taller;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Luis David
 */
public class Bajas {
    
    public static void ciclo(Ciclo ciclo){
        eliminar(ciclo);
    }
    
    public static void taller(Taller taller){
        eliminar(taller);
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
