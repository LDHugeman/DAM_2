package cuentasbancariascliente;

import java.util.Collection;
import objetos.Cliente;
import objetos.Movimiento;

/**
 *
 * @author luisd
 */
public class Visualizar {

    public static void mostrarCliente(Cliente cliente) {
        System.out.println("---------------- CLIENTE ----------------");        
        System.out.println("Dni: " + cliente.getDNI());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Dirección: " + cliente.getDireccion());
        System.out.println("-----------------------------------------");
    }
    
    public static void clientes(Collection<Cliente> clientes){
        for(Cliente cliente:clientes){
            mostrarCliente(cliente);
        }
    }
    
    public static void mostrarMovimiento(Movimiento movimiento){
        System.out.println("---------------- MOVIMIENTO ----------------");        
        System.out.println("Fecha: " + movimiento.getStringFechaMovimiento());
        System.out.println("Hora: " + movimiento.getStringHora());
        System.out.println("Cantidad: " + movimiento.getCantidad());
        System.out.println("-----------------------------------------");
    }
    
    public static void movimientos(Collection<Movimiento> movimientos){
        for(Movimiento movimiento:movimientos){
            mostrarMovimiento(movimiento);
        }
    }
}
