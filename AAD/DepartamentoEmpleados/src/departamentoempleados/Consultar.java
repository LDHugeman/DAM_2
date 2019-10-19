
package departamentoempleados;

import java.util.ArrayList;
import java.util.List;
import objetos.Departamento;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Luis David
 */
public class Consultar {
    
    public static List extraerDepartamentos(){
        List<Departamento> departamentos = new ArrayList();
        try{
            Session session = NewHibernateUtil.getSession();
            departamentos = session.createCriteria(Departamento.class).list();
            session.close();
        }catch(HibernateException excepcion){
            System.err.println("Error al extraer los departamentos");
            System.out.println(excepcion.getMessage());
        }
        return departamentos;
    }
}
