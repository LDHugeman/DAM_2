package existdbactividad8;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

/**
 *
 * @author a18luisdvp
 */
public class Conexion {

    private static Collection collection;

    public static Collection establecerConexion() {
        String urldriver = "org.exist.xmldb.DatabaseImpl";
        String uri = "xmldb:exist://localhost:8080/exist/xmlrpc/db/";
        String usuario = "admin";
        String contraseña = "admin";
        try {
            Class driver = Class.forName(urldriver);
            Database database = (Database) driver.newInstance();
            DatabaseManager.registerDatabase(database);
            if (!(estaConexionAbierta())) {
                collection = DatabaseManager.getCollection(uri, usuario, contraseña);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException e) {
        }
        return collection;
    }

    public static void cerrarConexion() {
        try {
            if (estaConexionAbierta()) {
                collection.close();
            }
        } catch (XMLDBException ex) {
        }
    }

    private static boolean estaConexionAbierta() throws XMLDBException {
        return collection != null && collection.isOpen();
    }
}
