
package existdbactividad8;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

/**
 *
 * @author a18luisdvp
 */
public class Menu {
    
    public static byte seleccionarOpcionMenuPrincipal() {
        System.out.println("------- MENÚ -------");
        System.out.println("[1] Insertar departamento");
        System.out.println("[2] Borrar departamento");
        System.out.println("[3] Modificar departamento");
        System.out.println("[4] Visualizar un departamento");
        System.out.println("[5] Visualizar nodos");
        System.out.println("[0] Salir");
        System.out.printf("Seleccione una opción: ");
        return Pedir.numeroByte();
    }
    
    public static void ejecutarAccion(String accion){
        Collection collection = Conexion.establecerConexion();
        XPathQueryService servicio;
        try {
            servicio = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            servicio.query(accion);
        } catch (XMLDBException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
}
