
package departamentoempleados;

import java.sql.Statement;
import objetos.Departamento;
import objetos.Empleado;

/**
 *
 * @author a18luisdvp
 */
public class Menu {
    
    public static void menuAltas(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuAltas();
            switch (opcion) {
                case 1:
                    Empleado empleado = Crear.nuevoEmpleado();
                    Altas.nuevoEmpleado(empleado);
                    break;
                case 2:
                    Departamento departamento = Crear.nuevoDepartamento();
                    Altas.nuevoDepartamento(departamento);
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }
    
    public static void menuBajas(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuBajas();
            switch (opcion) {
                case 1:
                    
                    break;
                case 2:
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }
    
    public static void menuModificar(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuModificar();
            switch (opcion) {
                case 1:
                    
                    break;
                case 2:
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }
    
    public static void menuVisualizar(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuVisualizar();
            switch (opcion) {
                case 1:
                    
                    break;
                case 2:
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }
    
    public static byte seleccionarOpcionMenuPrincipal() {
        System.out.println("------- MENU -------");
        System.out.println("[1] Altas ");
        System.out.println("[2] Bajas");
        System.out.println("[3] Modificaciones");
        System.out.println("[4] Visualizar");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }
    
    public static byte seleccionarOpcionMenuAltas() {
        System.out.println("------- ALTAS -------");
        System.out.println("[1] Empleado");
        System.out.println("[2] Departamento");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }
    
    public static byte seleccionarOpcionMenuBajas() {
        System.out.println("------- BAJAS -------");
        System.out.println("[1] Empleado");
        System.out.println("[2] Departamento");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }
    
    public static byte seleccionarOpcionMenuModificar() {
        System.out.println("------- MODIFICACIONES -------");
        System.out.println("[1] Modificar salario o comisión de un empleado");
        System.out.println("[2] Modificar localidad de un departamento");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }
    
    public static byte seleccionarOpcionMenuVisualizar() {
        System.out.println("------- VISUALIZAR -------");
        System.out.println("[1] Empleados de un departamento");
        System.out.println("[2] Departamento de un empleado");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }
}
