
package cuentasbancariasserver;

import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBServer;

/**
 *
 * @author a18luisdvp
 */
public class CuentasBancariasServer {

    public static void main(String[] args) {
        ODBServer server = ODBFactory.openServer(8000);
        server.addBase("CuentasBancarias", "CuentasBancarias.db");
        server.startServer(true);
    }
    
}
