package cuentasbancariascliente;

import java.util.ArrayList;
import java.util.Date;
import objetos.Cliente;
import objetos.Cuenta;
import objetos.CuentaCorriente;
import objetos.CuentaPlazo;
import objetos.Movimiento;
import org.neodatis.odb.ODB;

/**
 *
 * @author a18luisdvp
 */
public class Menu {

    public static byte seleccionarOpcionMenuPrincipal() {
        System.out.println("------- MENÚ -------");
        System.out.println("[1] Altas");
        System.out.println("[2] Baja de una cuenta a plazo de un cliente determinado");
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
                    altaMovimiento();
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
            System.out.println("--- Debe crear un nuevo cliente para la cuenta a plazo o elegir uno ya existente ---");
            Cliente cliente = Consultar.obtenerCliente();
            cuentaPlazo.getClientes().add(cliente);
            cliente.getCuentas().add(cuentaPlazo);
            Altas.nuevaCuenta(cuentaPlazo);
        } else {
            System.err.println("Ya existe una cuenta a plazo con ese número");
        }
        Conexion.closeSession();
    }

    private static void altaMovimiento() {
        System.out.println("--- Introduzca el número de cuenta corriente a la que desea añadir un movimiento ---");
        String numero = Crear.pedirNumero();
        CuentaCorriente cuentaCorriente = (CuentaCorriente) Consultar.encontrarCuentaPorNumero(numero);
        if (cuentaCorriente != null) {
            Movimiento movimiento = Crear.nuevoMovimiento(cuentaCorriente);
            double nuevoSaldo = cuentaCorriente.getSaldo() + movimiento.getCantidad();
            if (nuevoSaldo >= 0) {
                cuentaCorriente.getMovimientos().add(movimiento);
                cuentaCorriente.setSaldo(nuevoSaldo);
                System.out.println("Saldo actualizado");
                Altas.nuevaCuenta(cuentaCorriente); //Se hace store de la cuenta para que guarde el movimiento y a la vez actualice su saldo
            } else {
                System.err.println("Saldo insuficiente");
            }
        } else {
            System.err.println("No existe una cuenta corriente con ese número");
        }
        Conexion.closeSession();
    }

    public static void bajaCuentaPlazoDelCliente() {
        ODB odb = Conexion.getSession();
        System.out.println("--- Introduzca el nombre del cliente al que desea eliminarle una cuenta a plazo ---");
        String nombre = Crear.pedirNombre();
        Cliente cliente = Consultar.encontrarClientePorNombre(nombre);
        if (cliente != null) {
            System.out.println("--- Introduzca el número de la cuenta a plazo que desea eliminar del cliente ---");
            String numero = Crear.pedirNumero();
            Cuenta cuenta = Consultar.encontrarCuentaPorNumero(numero);
            if (cuenta != null && cuenta instanceof CuentaPlazo) {
                cliente.getCuentas().remove(cuenta);
                if (!cuentaTieneMasDe1Cliente(cuenta)) {
                    Bajas.cuenta(cuenta);
                } else {
                    cuenta.getClientes().remove(cliente);
                    odb.store(cuenta);
                }
                odb.store(cliente);
            } else {
                System.err.println("No hay ninguna cuenta a plazo con ese número");
            }
        } else {
            System.err.println("No existe ningún cliente con ese nombre");
        }
        Conexion.closeSession();
    }

    private static boolean cuentaTieneMasDe1Cliente(Cuenta cuenta) {
        return cuenta.getClientes().size() > 1;
    }

    private static byte seleccionarOpcionMenuModificaciones() {
        System.out.println("------- MODIFICACIONES -------");
        System.out.println("[1] Intereses de una cuenta a plazo");
        System.out.println("[2] Añadir cliente a cuenta ya existente");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static void menuModificaciones() {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuModificaciones();
            switch (opcion) {
                case 1:
                    modificarInteresesCuentaPlazo();
                    break;
                case 2:
                    procesoAñadirClienteCuenta();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

    private static void modificarInteresesCuentaPlazo() {
        System.out.println("--- Introduzca el dni del cliente del que desea modificar los intereses de una cuenta a plazo ---");
        String dni = Crear.pedirDni();
        Cliente cliente = Consultar.encontrarClientePorDni(dni);
        if (cliente != null) {
            System.out.println("--- Introduzca el número de la cuenta a plazo que desea modificar ---");
            String numero = Crear.pedirNumero();
            Cuenta cuenta = Consultar.encontrarCuentaPorNumero(numero);
            if (cuenta != null && cuenta instanceof CuentaPlazo) {
                Modificar.interesesCuentaPlazo((CuentaPlazo) cuenta);
            } else {
                System.err.println("No hay ninguna cuenta a plazo con ese número");
            }
        }
        Conexion.closeSession();
    }

    private static void procesoAñadirClienteCuenta() {
        System.out.println("--- Introduzca el número de cuenta a la que desea añadir un cliente ---");
        String numeroCuenta = Crear.pedirNumero();
        Cuenta cuenta = Consultar.encontrarCuentaPorNumero(numeroCuenta);
        if (cuenta != null) {
            Cliente nuevoCliente = Consultar.obtenerCliente();
            if (!Consultar.existeClienteEnCuenta(nuevoCliente, cuenta)) {
                Modificar.añadirClienteACuenta(cuenta, nuevoCliente);
            } else {
                System.err.println("Ya existe un cliente con ese dni dentro de la cuenta");
            }
        } else {
            System.err.println("No hay ninguna cuenta con ese número");
        }
        Conexion.closeSession();
    }

    private static byte seleccionarOpcionMenuVisualizar() {
        System.out.println("------- VISUALIZAR -------");
        System.out.println("[1] Todos los clientes cuyo nombre empiece por 'C'");
        System.out.println("[2] Clientes cuyo saldo es mayor a 200.000€");
        System.out.println("[3] Cantidad de clientes en números rojos");
        System.out.println("[4] Saldo medio de las cuentas a plazo de todos los clientes");
        System.out.println("[5] Movimientos realizados sobre una cuenta corriente entre dos fechas");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static void menuVisualizar() {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuVisualizar();
            switch (opcion) {
                case 1:
                    visualizarClientesEmpiezanC();
                    break;
                case 2:
                    visualizarClientesSaldoSuperiorACantidad();
                    break;
                case 3:
                    verNumeroClientesSaldoNegativo();
                    break;
                case 4:
                    System.out.println("Saldo medio de las cuentas a plazo: " + Consultar.obtenerSaldoMedioCuentasPlazo());
                    break;
                case 5:
                    visualizarMovimientosCuentaCorrienteEntreFechas();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

    private static void visualizarClientesEmpiezanC() {
        ArrayList<Cliente> clientes = Consultar.encontrarClientesEmpiezanPorC();
        if (!clientes.isEmpty()) {
            Visualizar.clientes(clientes);
        } else {
            System.err.println("No hay ningún cliente que empiece por 'C'");
        }
        Conexion.closeSession();
    }

    private static void visualizarClientesSaldoSuperiorACantidad() {
        ArrayList<CuentaCorriente> cuentasCorriente = Consultar.encontrarCuentasCorrienteSaldoSuperior(200000);
        if (!cuentasCorriente.isEmpty()) {
            for (CuentaCorriente cuentaCorriente : cuentasCorriente) {
                Visualizar.clientes(cuentaCorriente.getClientes());
            }
        } else {
            System.err.println("No hay ningún cliente con un saldo en cuenta corriente superior a 200.000");
        }
        Conexion.closeSession();
    }

    private static void verNumeroClientesSaldoNegativo() {
        ArrayList<Cuenta> cuentas = Consultar.encontrarCuentasSaldoNegativo();
        int clientesConSaldoNegativo = 0;
        if (!cuentas.isEmpty()) {
            for (Cuenta cuenta : cuentas) {
                clientesConSaldoNegativo += cuenta.getClientes().size();
            }
            System.out.println("--- Hay " + clientesConSaldoNegativo + " cliente(s) con saldo negativo ---");
        } else {
            System.err.println("No hay ningún cliente en números rojos");
        }
        Conexion.closeSession();
    }

    private static void visualizarMovimientosCuentaCorrienteEntreFechas() {
        ArrayList<Movimiento> movimientosCuentaCorriente = new ArrayList<>();
        System.out.println("--- Introduzca el número de cuenta corriente de la que desea ver movimientos entre 2 fechas ---");
        String numero = Crear.pedirNumero();
        Cuenta cuenta = Consultar.encontrarCuentaPorNumero(numero);
        if (cuenta != null) {
            if (cuenta instanceof CuentaCorriente) {
                CuentaCorriente cuentaCorriente = (CuentaCorriente) cuenta;
                System.out.printf("Fecha inicial (dd/MM/yyyy): ");
                Date fechaInicial = Pedir.fecha();
                System.out.printf("Fecha final (dd/MM/yyyy): ");
                Date fechaFinal = Pedir.fecha();
                ArrayList<Movimiento> movimientos = Consultar.obtenerMovimientosEntreFechas(fechaInicial, fechaFinal);
                if (!movimientos.isEmpty()) {
                    for (Movimiento movimiento : movimientos) {
                        if (cuentaCorriente.getMovimientos().contains(movimiento)) {
                            movimientosCuentaCorriente.add(movimiento);
                        }
                    }
                    if (!movimientosCuentaCorriente.isEmpty()) {
                        Visualizar.movimientos(movimientosCuentaCorriente);
                    } else {
                        System.err.println("Esta cuenta corriente no tiene ningún movimiento entre esas dos fechas");
                    }
                } else {
                    System.err.println("No hay ningún movimiento entre esas dos fechas");
                }
            }else {
                System.err.println("Ese numero no corresponde a una cuenta corriente, es de una cuenta a plazo");
            }
        } else {
            System.err.println("No hay ninguna cuenta corriente con ese número");
        }
    }

}
