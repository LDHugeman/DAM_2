
package empresascoches;

import objetos.CocheAlquiler;
import objetos.CocheVenta;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author a18luisdvp
 */
public class Modificar {
    
    public static void precioCocheVenta(CocheVenta cocheVenta){
        System.out.println("--- Introduzca el nuevo precio para el coche en venta ---");        
        float precioVenta = Crear.pedirPrecioVenta();
        cocheVenta.setPrecio(precioVenta);
        modificar(cocheVenta);
    }
    
    public static void precioDiaCocheAlquiler(CocheAlquiler cocheAlquiler){
        System.out.println("--- Introduzca el nuevo precio por día para el coche en alquiler ---");        
        float precioDia = Crear.pedirPrecioDia();
        cocheAlquiler.setPrecio(precioDia);
        modificar(cocheAlquiler);
    }
    
    public static void modificar(Object objeto){
        Session session;
        try{
            session = NewHibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(objeto);
            session.getTransaction().commit();
            session.close();           
        }catch(HibernateException excepcion){
            System.err.println("Error al modificar");
            System.out.println(excepcion.getMessage());
        }
    }
}
