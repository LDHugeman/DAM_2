
package cuentasbancariascliente;

import objetos.Cliente;
import objetos.Cuenta;
import objetos.CuentaPlazo;
import org.neodatis.odb.ODB;

/**
 *
 * @author luisd
 */
public class Modificar {
    
    public static void interesesCuentaPlazo(CuentaPlazo cuentaPlazo){
        ODB odb = Conexion.getSession();
        System.out.println("--- Introduzca los nuevos intereses para la cuenta a plazo ---");
        System.out.printf("Intereses: ");
        float intereses = Pedir.numeroRealFloat();
        cuentaPlazo.setIntereses(intereses);
        odb.store(cuentaPlazo);        
    }
    
   public static void añadirClienteACuenta(Cuenta cuenta, Cliente cliente) {
        cuenta.getClientes().add(cliente);
        cliente.getCuentas().add(cuenta);
        ODB odb = Conexion.getSession();
        odb.store(cuenta);
    }
    
}
