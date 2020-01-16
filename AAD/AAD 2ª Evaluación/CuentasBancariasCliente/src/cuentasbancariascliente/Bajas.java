
package cuentasbancariascliente;

import objetos.Cuenta;
import org.neodatis.odb.ODB;

/**
 *
 * @author luisd
 */
public class Bajas {
    
    public static void cuenta(Cuenta cuenta){
        ODB odb = Conexion.getSession();
        odb.delete(cuenta);
    }
}
