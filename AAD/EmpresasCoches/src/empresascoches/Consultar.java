package empresascoches;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import objetos.Coche;
import objetos.CocheAlquiler;
import objetos.CocheVenta;
import objetos.Empresa;
import objetos.Uso;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
    
    public static List extraerEmpresas() {
        List<Empresa> empresas = new ArrayList();
        try {
            Session session = NewHibernateUtil.getSession();
            empresas = session.createCriteria(Empresa.class).list();
            session.close();
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

    public static List extraerCochesDeAlquilerEmpresa(Set<Coche> coches) {
        List<CocheAlquiler> cochesAlquiler = new ArrayList();
        if (!coches.isEmpty()) {
            for (Coche coche : coches) {
                if (coche instanceof CocheAlquiler) {
                    cochesAlquiler.add((CocheAlquiler) coche);
                }
            }
        } else {
            System.err.println("No hay ningún coche de alquiler en esta empresa");
        }
        return cochesAlquiler;
    }
    
    public static List obtenerUsosCocheAlquilerEntreFechas(Set<Uso> usos, Date primeraFecha, Date segundaFecha){
        List<Uso> usosFiltrados = new ArrayList<>();
        for(Uso uso:usos){
            if(uso.getFechaEntrega().getTime()>primeraFecha.getTime() && uso.getFechaAlquiler().getTime()<segundaFecha.getTime()){
                usosFiltrados.add(uso);
            }
        }
        return usosFiltrados;       
    }
}
