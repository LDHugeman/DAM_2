
package empresascoches;

import java.util.ArrayList;
import java.util.List;
import objetos.CocheAlquiler;
import objetos.CocheVenta;
import objetos.Empresa;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author a18luisdvp
 */
public class Consultar {
    
    public static Empresa encontrarEmpresaPorCif(Session session, String cif) {
        Empresa empresaEncontrada = null;
        try {
            empresaEncontrada = (Empresa) session.get(Empresa.class, cif);
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar la empresa");
            System.out.println(excepcion.getMessage());
        }
        return empresaEncontrada;
    }
    
    public static CocheVenta encontrarCocheVentaPorCodigo(Session session, String codigo) {
        CocheVenta cocheVenta = null;
        try {
            cocheVenta = (CocheVenta) session.get(CocheVenta.class, codigo);
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar el coche en venta");
            System.out.println(excepcion.getMessage());
        }
        return cocheVenta;
    }
    
    public static CocheVenta encontrarCocheVentaPorCodigo(String codigo) {
        CocheVenta cocheVenta = null;
        try {
            Session session = NewHibernateUtil.getSession();
            cocheVenta = (CocheVenta) session.get(CocheVenta.class, codigo);
            session.close();
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar el coche en venta");
            System.out.println(excepcion.getMessage());
        }
        return cocheVenta;
    }
    
    public static CocheAlquiler encontrarCocheAlquilerPorCodigo(Session session, String codigo) {
        CocheAlquiler cocheAlquiler = null;
        try {
            cocheAlquiler = (CocheAlquiler) session.get(CocheAlquiler.class, codigo);
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar el coche de alquiler");
            System.out.println(excepcion.getMessage());
        }
        return cocheAlquiler;
    }
    
    public static CocheAlquiler encontrarCocheAlquilerPorCodigo(String codigo) {
        CocheAlquiler cocheAlquiler = null;
        try {
            Session session = NewHibernateUtil.getSession();
            cocheAlquiler = (CocheAlquiler) session.get(CocheAlquiler.class, codigo);
            session.close();
        } catch (HibernateException excepcion) {
            System.err.println("Error al buscar el coche de alquiler");
            System.out.println(excepcion.getMessage());
        }
        return cocheAlquiler;
    }
    
    public static List extraerEmpresas(Session session) {
        List<Empresa> empresas = new ArrayList();
        try {            
            empresas = session.createCriteria(Empresa.class).list();           
        } catch (HibernateException excepcion) {
            System.err.println("Error al extraer las empresas");
            System.out.println(excepcion.getMessage());
        }
        return empresas;
    }
    
    public static List extraerCochesDeAlquiler() {
        List<CocheAlquiler> cochesAlquiler = new ArrayList();
        try {
            Session session = NewHibernateUtil.getSession();
            cochesAlquiler = session.createCriteria(CocheAlquiler.class).list();
            session.close();
        } catch (HibernateException excepcion) {
            System.err.println("Error al extraer los coches de alquiler");
            System.out.println(excepcion.getMessage());
        }
        return cochesAlquiler;
    }
    
    public static List extraerCochesEnVenta() {
        List<CocheVenta> cochesVenta = new ArrayList();
        try {
            Session session = NewHibernateUtil.getSession();
            cochesVenta = session.createCriteria(CocheVenta.class).list();
            session.close();
        } catch (HibernateException excepcion) {
            System.err.println("Error al extraer los coches en venta");
            System.out.println(excepcion.getMessage());
        }
        return cochesVenta;
    }
}
