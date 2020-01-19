
package cuentasbancariascliente;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import objetos.Cliente;
import objetos.Cuenta;
import objetos.CuentaCorriente;
import objetos.CuentaPlazo;
import objetos.Movimiento;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

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
        } 
        return clienteEncontrado;
    }

    public static boolean existeClientePorDni(String dni) {
        return encontrarClientePorDni(dni) != null;
    }
    
    public static Cliente encontrarClientePorNombre(String nombre) {
        Cliente clienteEncontrado = null;
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Cliente.class, Where.equal("nombre", nombre));
        Objects resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            clienteEncontrado = (Cliente) resultado.getFirst();
        } 
        return clienteEncontrado;
    }
    
    public static Cliente obtenerCliente() {
        String dni = Crear.pedirDni();
        Cliente cliente;
        if (existeClientePorDni(dni)) {
            System.out.println("--- Este cliente ya existe, no es necesario volver a introducir sus datos ---");
            cliente = Consultar.encontrarClientePorDni(dni);
        } else {
            System.out.println("--- Se creará un nuevo cliente ---");
            cliente = Crear.nuevoCliente(dni);
        }
        return cliente;
    }
    
    public static Cuenta encontrarCuentaPorNumero(String numero){
        Cuenta cuentaEncontrada = null;
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Cuenta.class, Where.equal("numero", numero)).setPolymorphic(true);
        Objects resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            cuentaEncontrada = (Cuenta) resultado.getFirst();
        } 
        return cuentaEncontrada;
    }
    
    public static boolean existeCuentaPorNumero(String numero) {
        return encontrarCuentaPorNumero(numero) != null;
    }
    
    public static boolean existeClienteEnCuenta(Cliente cliente, Cuenta cuenta){
        return cuenta.getClientes().contains(cliente);
    }
    
    public static ArrayList<Cliente> encontrarClientesEmpiezanPorC() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ODB odb = Conexion.getSession();
        ICriterion icriterion = Where.like("nombre", "C%");
        CriteriaQuery query = new CriteriaQuery(Cliente.class, icriterion);
        Objects resultado = odb.getObjects(query);
        while (resultado.hasNext()) {
            Cliente cliente = (Cliente) resultado.next();
            clientes.add(cliente);
        }
        return clientes;
    }
    
    public static ArrayList<CuentaCorriente> encontrarCuentasCorrienteSaldoSuperior(float saldo) {
        ArrayList<CuentaCorriente> cuentasCorriente = new ArrayList<>();
        ODB odb = Conexion.getSession();
        ICriterion icriterion = Where.gt("saldo", saldo);
        CriteriaQuery query = new CriteriaQuery(CuentaCorriente.class, icriterion);
        Objects resultado = odb.getObjects(query);
            while (resultado.hasNext()) {
                CuentaCorriente cuentaCorriente = (CuentaCorriente) resultado.next();
                cuentasCorriente.add(cuentaCorriente);
            }
        return cuentasCorriente;
    }
    
    public static ArrayList<Cuenta> encontrarCuentasSaldoNegativo() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Cuenta.class, Where.lt("saldo", 0)).setPolymorphic(true);
        Objects resultado = odb.getObjects(query);
            while (resultado.hasNext()) {
                Cuenta cuenta = (Cuenta) resultado.next();
                cuentas.add(cuenta);
            }
        return cuentas;
    }
    
    public static BigDecimal obtenerSaldoMedioCuentasPlazo() {
        ODB odb = Conexion.getSession();
        Values values = odb.getValues(new ValuesCriteriaQuery(CuentaPlazo.class).avg("saldo"));
        ObjectValues objectValues = values.nextValues();
        BigDecimal saldoMedio = (BigDecimal) objectValues.getByAlias("saldo");
        return saldoMedio;
    }
    
    public static ArrayList<Movimiento> obtenerMovimientosEntreFechasCuenta(Date primeraFecha, Date segundaFecha, String numeroCuenta) {
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        ODB odb = Conexion.getSession();   
        ICriterion icriterion = new And()
                .add(Where.gt("fechaMovimiento", primeraFecha))
                .add(Where.lt("fechaMovimiento", segundaFecha))
                .add(Where.like("cuentaCorriente.numero", numeroCuenta));
        CriteriaQuery query = new CriteriaQuery(Movimiento.class, icriterion);
        Objects resultado = odb.getObjects(query);
            while (resultado.hasNext()) {
                Movimiento movimiento = (Movimiento) resultado.next();
                movimientos.add(movimiento);
            }
        return movimientos;
    } 
}
