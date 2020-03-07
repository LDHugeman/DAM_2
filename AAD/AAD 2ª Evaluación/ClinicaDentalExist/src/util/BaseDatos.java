package util;

import clinicadentalexist.Conexion;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

/**
 *
 * @author Alberto y David
 */
public class BaseDatos {

    public static void ejecutarAccion(String accion) {
        {
            try {
                Collection collection = Conexion.establecerConexion();
                XPathQueryService servicio = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
                servicio.query(accion);
            } catch (XMLDBException excepcion) {
                System.out.println(excepcion.getMessage());
            }
        }
    }

    public static void ejecutarVisualizar(String accion) {
        {
            try {
                Collection collection = Conexion.establecerConexion();
                XPathQueryService servicio = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
                ResourceSet result = servicio.query(accion);
                ResourceIterator iterator;
                iterator = result.getIterator();
                if (!iterator.hasMoreResources()) {
                    System.err.println("No hay ningún registro");
                }
                while (iterator.hasMoreResources()) {
                    Resource resource = iterator.nextResource();
                    System.out.println((String) resource.getContent());
                }
            } catch (XMLDBException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
