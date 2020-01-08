
package departamentoempleados;

import objetos.Departamento;
import objetos.Empleado;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Luis David
 */
public class Bajas {
    
    public static void departamento(Departamento departamento){
        eliminar(departamento);
    }
    
    public static void empleado(Empleado empleado){
        eliminar(empleado);
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
