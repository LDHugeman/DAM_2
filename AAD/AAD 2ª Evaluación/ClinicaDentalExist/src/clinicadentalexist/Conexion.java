package clinicadentalexist;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

/**
 *
 * @author Alberto y David
 */
public class Conexion {

    private static Collection collection;

    public static Collection establecerConexion() {
        String urldriver = "org.exist.xmldb.DatabaseImpl";
        String uri = "xmldb:exist://localhost:8080/exist/xmlrpc/db/clinicaDental";
        String usuario = "admin";
        String contraseña = "admin";
        try {
            Class driver = Class.forName(urldriver);
            Database database = (Database) driver.newInstance();
            DatabaseManager.registerDatabase(database);
            if (!(estaConexionAbierta())) {
                collection = DatabaseManager.getCollection(uri, usuario, contraseña);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException excepcion) {
            System.out.println("Error al establecer la conexión");
            System.out.println(excepcion.getMessage());
        }
        return collection;
    }

    public static void cerrarConexion() {
        try {
            if (estaConexionAbierta()) {
                collection.close();
            }
        } catch (XMLDBException excepcion) {
            System.out.println("Error al cerrar la conexión");
            System.out.println(excepcion.getMessage());
        }
    }

    private static boolean estaConexionAbierta() throws XMLDBException {
        return collection != null && collection.isOpen();
    }
}
