
package departamentoempleados;

import objetos.Departamento;
import objetos.Empleado;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Luis David
 */
public class Altas {
    
    public static void nuevoDepartamento(Departamento departamento){
        guardar(departamento);
    }
    
    public static void nuevoEmpleado(Empleado empleado){
        guardar(empleado);
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
