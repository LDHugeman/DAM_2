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
                    altaEmpleado();
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

    public static void altaEmpleado() {
        Empleado empleado = Crear.nuevoEmpleado();
        Departamento departamento = null;
        Visualizar.departamentos(Consultar.extraerDepartamentos());
        System.out.println("--- Introduzca el número del departamento en el que desea añadir el empleado ---");
        System.out.printf("Número del departamento: ");
        int numeroDepartamento = Pedir.numeroEntero();
        departamento = Consultar.encontrarDepartamentoPorId(numeroDepartamento);
        if (departamento != null) {        
            departamento.getEmpleados().add(empleado);
        } else {  
            System.err.println("No existe un departamento con ese número");
            if (Pedir.duda("¿Desea crear el departamento?")) {
                departamento = Crear.nuevoDepartamento();
                departamento.getEmpleados().add(empleado);                
            }
        }
        empleado.setDepartamento(departamento);
        Altas.nuevoEmpleado(empleado);     
        System.out.printf("Num seg social: ");
        Visualizar.empleado(Consultar.encontrarEmpleadoPorNumeroSegSocial(Pedir.texto()));
    }
}
