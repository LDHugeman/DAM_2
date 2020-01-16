package cuentasbancariascliente;

import objetos.Cliente;
import objetos.CuentaCorriente;
import objetos.CuentaPlazo;

/**
 *
 * @author a18luisdvp
 */
public class Menu {

    public static byte seleccionarOpcionMenuPrincipal() {
        System.out.println("------- MENÚ -------");
        System.out.println("[1] Altas");
        System.out.println("[2] Bajas");
        System.out.println("[3] Modificaciones");
        System.out.println("[4] Visualizar");
        System.out.println("[0] Salir");
        System.out.printf("Seleccione una opción: ");
        return Pedir.numeroByte();
    }

    private static byte seleccionarOpcionMenuAltas() {
        System.out.println("------- ALTAS -------");
        System.out.println("[1] Cuenta corriente");
        System.out.println("[2] Cuenta a plazo");
        System.out.println("[3] Movimiento");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static void menuAltas() {

        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuAltas();
            switch (opcion) {
                case 1:
                    altaCuentaCorriente();
                    break;
                case 2:
                    altaCuentaPlazo();
                    break;
                case 3:
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

    private static void altaCuentaCorriente() {
        String numero = Crear.pedirNumero();
        if (!Consultar.existeCuentaPorNumero(numero)) {
            CuentaCorriente cuentaCorriente = Crear.nuevaCuentaCorriente(numero);
            System.out.println("--- Debe crear un nuevo cliente para la cuenta corriente ---");
            Cliente cliente = Consultar.obtenerCliente();
            cuentaCorriente.getClientes().add(cliente);
            cliente.getCuentas().add(cuentaCorriente);
            Altas.nuevaCuenta(cuentaCorriente);
        } else {
            System.err.println("Ya existe una cuenta corriente con ese número");
        }

        Conexion.closeSession();
    }

    private static void altaCuentaPlazo() {
        String numero = Crear.pedirNumero();
        if (!Consultar.existeCuentaPorNumero(numero)) {
            CuentaPlazo cuentaPlazo = Crear.nuevaCuentaPlazo(numero);
            System.out.println("--- Debe crear un nuevo cliente para la cuenta a plazo ---");
            Cliente cliente = Consultar.obtenerCliente();
            cuentaPlazo.getClientes().add(cliente);
            cliente.getCuentas().add(cuentaPlazo);
            Altas.nuevaCuenta(cuentaPlazo);
        } else {
            System.err.println("Ya existe una cuenta a plazo con ese número");
        }
        Conexion.closeSession();
    }
}
