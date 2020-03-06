
package clinicadentalneodatisservidor;

import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBServer;

/**
 *
 * @author a18luisdvp
 */
public class ClinicaDentalNeodatisServidor {
    public static void main(String[] args) {

        ODBServer server = ODBFactory.openServer(8000);
        server.addBase("ClinicaDental", "ClinicaDental.db");
        server.startServer(true);
    }   
}
