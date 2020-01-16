package cuentasbancariascliente;

import excepciones.Validar;
import java.util.Date;
import objetos.Cliente;
import objetos.CuentaCorriente;
import objetos.CuentaPlazo;
import objetos.Movimiento;

/**
 *
 * @author a18luisdvp
 */
public class Crear {

    public static Cliente nuevoCliente(String dni) {      
        String nombre = pedirNombre();
        System.out.printf("Dirección: ");
        String direccion = Pedir.texto();
        return new Cliente(dni, nombre, direccion);
    }

    public static CuentaCorriente nuevaCuentaCorriente(String numero) {
        System.out.printf("Sucursal: ");
        String sucursal = Pedir.texto();
        System.out.printf("Saldo: ");
        float saldo = Pedir.numeroRealFloat();
        return new CuentaCorriente(numero, sucursal, saldo);
    }

    public static CuentaPlazo nuevaCuentaPlazo(String numero) {
        System.out.printf("Sucursal: ");
        String sucursal = Pedir.texto();
        System.out.printf("Saldo: ");
        float saldo = Pedir.numeroRealFloat();
        System.out.printf("Intereses: ");
        float intereses = Pedir.numeroRealFloat();
        System.out.printf("Fecha de vencimiento(dd/MM/yyyy):");
        Date fechaVencimiento = Pedir.fecha();
        System.out.printf("Deposito a plazo: ");
        float depositoPlazo = Pedir.numeroRealFloat();
        return new CuentaPlazo(numero, sucursal, saldo, intereses, fechaVencimiento, depositoPlazo);
    }

    public static Movimiento nuevoMovimiento(CuentaCorriente cuentaCorriente) {
        float cantidad = pedirCantidad();
        return new Movimiento(cuentaCorriente, cantidad);
    }

    public static String pedirDni() {
        String dni = "";
        do {
            System.out.printf("Dni: ");
            dni = Pedir.texto();
        } while (!Validar.esDniValido(dni));
        return dni;
    }
    
    public static String pedirNumero(){
        System.out.printf("Número: ");
        return Pedir.texto();
    }
    
    public static String pedirNombre(){
        System.out.printf("Nombre: ");
        return Pedir.texto();
    }

    public static float pedirCantidad() {
        byte tipoMovimiento;
        do {
            System.out.println("--- Elija el tipo de movimiento ---");
            System.out.println("[1] Ingreso");
            System.out.println("[2] Retirada");
            System.out.printf("Seleccione una opcion: ");
            tipoMovimiento = Pedir.numeroByte();
        } while (tipoMovimiento != 1 && tipoMovimiento != 2);
        System.out.printf("Cantidad: ");
        float cantidad = Pedir.numeroRealFloat();
        if (tipoMovimiento == 1) {
            return cantidad;
        } else {
            return cantidad - cantidad * 2;
        }
    }
}
