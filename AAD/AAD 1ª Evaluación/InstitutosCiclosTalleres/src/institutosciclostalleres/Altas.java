
package institutosciclostalleres;

import objetos.Ciclo;
import objetos.Instituto;
import objetos.Taller;
import objetos.Uso;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Luis David
 */
public class Altas {
    
    public static void nuevoInstituto(Instituto instituto){
        guardar(instituto);
    }
    
    public static void nuevoCiclo(Ciclo ciclo){
        guardar(ciclo);
    }
    
    public static void nuevoTaller(Taller taller){
        guardar(taller);
    }
    
    public static void nuevoUso(Uso uso){
        guardar(uso);
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
    
    public static void guardar(Object objeto, Session session){
        try{
            session.beginTransaction();
            session.save(objeto);
            session.getTransaction().commit();
        }catch(HibernateException excepcion){
            System.err.println("Error al guardar");
            System.out.println(excepcion.getMessage());
        }
    }
}
