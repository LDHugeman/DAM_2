
package cuentasbancariascliente;

import objetos.Cliente;
import objetos.Cuenta;
import objetos.CuentaCorriente;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author a18luisdvp
 */
public class Consultar {
    
    public static Cliente encontrarClientePorDni(String dni) {
        Cliente clienteEncontrado = null;
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Cliente.class, Where.equal("dni", dni));
        Objects resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            clienteEncontrado = (Cliente) resultado.getFirst();
        } else {
            System.err.println("No se ha encontrado ningún cliente con ese dni");
        }
        return clienteEncontrado;
    }

    public static boolean existeClientePorDni(String dni) {
        return encontrarClientePorDni(dni) != null;
    }
    
    public static Cliente obtenerCliente() {
        String dni = Crear.pedirDni();
        Cliente cliente;
        if (existeClientePorDni(dni)) {
            System.out.println("Este cliente ya existe, no es necesario volver a introducir sus datos.");
            cliente = Consultar.encontrarClientePorDni(dni);
        } else {
            System.out.println("Se creará un nuevo cliente");
            cliente = Crear.nuevoCliente(dni);
        }
        return cliente;
    }
    
    public static Cuenta encontrarCuentaPorNumero(String numero){
        Cuenta cuentaEncontrada = null;
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Cuenta.class, Where.equal("numero", numero));
        Objects resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            cuentaEncontrada = (CuentaCorriente) resultado.getFirst();
        } else {
            System.err.println("No se ha encontrado ninguna cuenta con ese número");
        }
        return cuentaEncontrada;
    }
    
    public static boolean existeCuentaPorNumero(String numero) {
        return encontrarCuentaPorNumero(numero) != null;
    }
    
    
}
