package cuentasbancariascliente;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

/**
 *
 * @author luisd
 */
public class Conexion {

    private static ODB odb;

    public static ODB getSession() {
        if (estaSesionCerrada()) {
            odb = ODBFactory.openClient("localhost", 8000, "CuentasBancarias");
        }
        return odb;
    }

    public static void closeSession() {
        if (!estaSesionCerrada()) {
            odb.close();
        }
    }

    private static boolean estaSesionCerrada() {
        return odb == null || odb.isClosed();
    }
}
