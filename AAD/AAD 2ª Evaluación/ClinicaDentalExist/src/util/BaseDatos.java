package util;

import clinicadentalexist.Conexion;
import org.xmldb.api.base.Collection;
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
}
