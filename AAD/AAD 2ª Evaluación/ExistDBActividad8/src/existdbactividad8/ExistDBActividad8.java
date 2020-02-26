
package existdbactividad8;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;



/**
 *
 * @author a18luisdvp
 */
public class ExistDBActividad8 {

    public static void main(String[] args) throws XMLDBException {
        Collection collection = Conexion.establecerConexion();
        if(collection!= null){
            System.err.println("La colección no existe");
        } else {
            byte opcion = 0;
        do {
            opcion = Menu.seleccionarOpcionMenuPrincipal();
            switch (opcion) {
                case 1:
                    
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    collection.close();
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
        }
    }
    
}
