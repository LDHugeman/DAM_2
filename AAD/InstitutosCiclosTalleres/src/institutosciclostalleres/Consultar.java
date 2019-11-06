
package institutosciclostalleres;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import objetos.Ciclo;
import objetos.Instituto;
import objetos.Taller;
import objetos.Uso;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
    
    public static List<Uso> usosTalleresEntreFechas(Session session, Date primerFecha, Date segundaFecha){
        List<Uso> usos = new ArrayList<>();
        try{                    
            usos = session.createCriteria(Uso.class).add(Restrictions.between("fecha", primerFecha, segundaFecha)).list();
        }catch(HibernateException excepcion) {
            System.err.println("Error al buscar los usos");
            System.out.println(excepcion.getMessage());
        }
        return usos;
    }
}
