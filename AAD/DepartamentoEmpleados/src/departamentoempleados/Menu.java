package departamentoempleados;

import java.sql.Statement;
import objetos.Departamento;
import objetos.Empleado;
import org.hibernate.Session;

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
                    altaDepartamento();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

    public static void menuBajas(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuBajas();
            switch (opcion) {
                case 1:
                    bajaEmpleado();
                    break;
                case 2:
                    bajaDepartamento();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

    public static void menuModificar(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuModificar();
            switch (opcion) {
                case 1:
                    modificarSalarioOComisionEmpleado();
                    break;
                case 2:
                    modificarLocalidadDepartamento();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

    public static void menuVisualizar(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuVisualizar();
            switch (opcion) {
                case 1:
                    visualizarEmpleadosDepartamento();
                    break;
                case 2:
                    visualizarDepartamentoEmpleado();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
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
            Session session = NewHibernateUtil.getSession();
            session.update(departamento);
            departamento.getEmpleados().add(empleado);
            session.close();
        } else {
            System.out.println("No existe un departamento con ese número");
            if (Pedir.duda("¿Desea crear el departamento?")) {
                departamento = Crear.nuevoDepartamento();
                Session session2 = NewHibernateUtil.getSession();
                session2.update(departamento);
                departamento.getEmpleados().add(empleado);
                session2.close();
                Altas.guardar(departamento);
            }
        }
        empleado.setDepartamento(departamento);
        Altas.nuevoEmpleado(empleado);
    }

    public static void altaDepartamento() {
        Departamento departamento = Crear.nuevoDepartamento();
        Altas.nuevoDepartamento(departamento);
    }

    public static void bajaEmpleado() {
        Visualizar.empleados(Consultar.extraerEmpleados());
        System.out.println("--- Introduzca el número de Seguridad Social del empleado que desea eliminar ---");
        String numeroSegSocial = Crear.pedirNumeroSegSocial();
        Empleado empleado = Consultar.encontrarEmpleadoPorNumeroSegSocial(numeroSegSocial);
        if (empleado != null) {
            Visualizar.empleado(empleado);
            if (Pedir.duda("¿Es este el empleado que desea eliminar?")) {
                Bajas.empleado(empleado);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningún empleado con ese número de Seguridad Social");
        }
    }

    public static void bajaDepartamento() {
        Visualizar.departamentos(Consultar.extraerDepartamentos());
        System.out.println("--- Introduzca el número del departamento que desea eliminar ---");
        int idDepartamento = Crear.pedirIdDepartamento();
        Departamento departamento = Consultar.encontrarDepartamentoPorId(idDepartamento);
        if (departamento != null) {
            Visualizar.departamento(departamento);
            if (Pedir.duda("¿Es este el departamento que desea eliminar?")) {
                Bajas.departamento(departamento);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningún departamento con ese número");
        }
    }

    public static void modificarSalarioOComisionEmpleado() {
        System.out.println("--- Introduzca el nombre del empleado al que desea modificar salario o comisión ---");
        String nombre = Crear.pedirNombreEmpleado();
        Empleado empleado = Consultar.encontrarEmpleadoPorNombre(nombre);
        if (empleado != null) {          
            Visualizar.empleado(empleado);
            if (Pedir.duda("¿Es este el empleado que desea modificar?")) {
                System.out.println("¿Desea modificar el salario o la comisión?");
                System.out.println("[1] Salario");
                System.out.println("[2] Comisión");
                System.out.printf("Seleccione una opción: ");
                int opcionModificar = Pedir.numeroByte();
                switch (opcionModificar) {
                    case 1:
                        Modificar.salarioEmpleado(empleado);
                        break;
                    case 2:
                        Modificar.comisionEmpleado(empleado);
                        break;
                    default:
                        System.out.println("Operación cancelada");
                        break;
                }
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningún empleado con ese nombre");
        }
    }

    public static void modificarLocalidadDepartamento() {
        System.out.println("--- Introduzca el nombre del departamento al que desea modificar la localidad ---");
        String nombre = Crear.pedirNombreDepartamento();
        Departamento departamento = Consultar.encontrarDepartamentoPorNombre(nombre);
        if (departamento != null) {
            Visualizar.departamento(departamento);
            if (Pedir.duda("¿Es este el departamento que desea modificar?")) {
                Modificar.localidadDepartamento(departamento);
            } else {
                System.out.println("Operación cancelada");
            }
        } else {
            System.err.println("No existe ningún departamento con ese nombre");
        }
    }

    public static void visualizarEmpleadosDepartamento() {
        System.out.println("--- Introduzca el nombre del departamento del que desea ver sus empleados ---");
        String nombre = Crear.pedirNombreDepartamento();
        Departamento departamento = Consultar.encontrarDepartamentoPorNombre(nombre);
        if (departamento != null) {           
            Session session = NewHibernateUtil.getSession();
            session.update(departamento);
            Visualizar.empleados(departamento.getEmpleados());
            session.close();
        } else {
            System.err.println("No existe un departamento con ese nombre");
        }
    }

    public static void visualizarDepartamentoEmpleado() {
        System.out.println("--- Introduzca el nombre del empleado del que desea ver su departamento ---");
        String nombre = Crear.pedirNombreEmpleado();
        Empleado empleado = Consultar.encontrarEmpleadoPorNombre(nombre);
        if (empleado != null) {
           // Session session = NewHibernateUtil.getSession();
            //session.update(empleado);
            Visualizar.departamento(empleado.getDepartamento());
          //  session.close();
        } else {
            System.err.println("No existe un empleado con ese nombre");
        }
    }
}
