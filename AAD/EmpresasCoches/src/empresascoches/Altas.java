
package empresascoches;

import objetos.CocheAlquiler;
import objetos.CocheVenta;
import objetos.Empresa;
import objetos.Uso;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author a18luisdvp
 */
public class Altas {
    
    public static void nuevaEmpresa(Empresa empresa){
        guardar(empresa);
    }
    
    public static void nuevoCocheVenta(CocheVenta cocheVenta){
        guardar(cocheVenta);
    }
    
    public static void nuevoCocheAlquiler(CocheAlquiler cocheAlquiler){
        guardar(cocheAlquiler);
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
}
