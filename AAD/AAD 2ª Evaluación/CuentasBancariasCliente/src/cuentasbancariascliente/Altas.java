package cuentasbancariascliente;

import objetos.Cuenta;
import objetos.Movimiento;
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

    public static void nuevoMovimiento(Movimiento movimiento) {
        ODB odb = Conexion.getSession();
        odb.store(movimiento);
    }

}
