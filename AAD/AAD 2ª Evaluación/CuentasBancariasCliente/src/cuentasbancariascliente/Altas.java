package cuentasbancariascliente;

import objetos.Cuenta;
import org.neodatis.odb.ODB;

/**
 *
 * @author a18luisdvp
 */
public class Altas {

    public static void nuevaCuenta(Cuenta cuenta) {
        ODB odb = Conexion.getSession();
        odb.store(cuenta);
    }
}
