
package institutoscliclostalleres;

import objetos.Ciclo;
import objetos.Instituto;
import objetos.Taller;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author luisd
 */
public class Consultar {
    
    public static Instituto encontrarInstitutoPorCodigo(Session session, int codigo) {
        Instituto institutoEncontrado = null;
        try {
            institutoEncontrado = (Instituto) session.get(Instituto.class, codigo);  
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar el instituto");
            System.out.println(excepcion.getMessage());
        }
        return institutoEncontrado;
    }
    
    public static Ciclo encontrarCicloPorCodigo(Session session, int codigo) {
        Ciclo cicloEncontrado = null;
        try {          
            cicloEncontrado = (Ciclo) session.get(Ciclo.class, codigo);    
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar el ciclo");
            System.out.println(excepcion.getMessage());
        }
        return cicloEncontrado;
    }
    
    public static Taller encontrarTallerPorCodigo(Session session, int codigo) {
        Taller tallerEncontrado = null;
        try {
            tallerEncontrado = (Taller) session.get(Taller.class, codigo);   
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar el taller");
            System.out.println(excepcion.getMessage());
        }
        return tallerEncontrado;
    }
}
