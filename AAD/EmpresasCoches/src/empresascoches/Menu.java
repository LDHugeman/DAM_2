package empresascoches;

import java.sql.Statement;
import objetos.CocheAlquiler;
import objetos.CocheVenta;
import objetos.Empresa;
import objetos.Uso;
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
                    altaEmpresa();
                    break;
                case 2:
                    altaCocheVenta();
                    break;
                case 3:
                    altaCocheAlquiler();
                    break;
                case 4:
                    altaUsoCocheAlquiler();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

    public static void menuModificaciones(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuModificaciones();
            switch (opcion) {
                case 1:
                    modificarPrecioCocheVenta();
                    break;
                case 2:
                    modificarPrecioDiaCocheAlquiler();
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
            opcion = seleccionarOpcionMenuModificaciones();
            switch (opcion) {
                case 1:
                    break;
                case 2:
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

    public static byte seleccionarOpcionMenuPrincipal() {
        System.out.println("------- MENÚ -------");
        System.out.println("[1] Altas");
        System.out.println("[2] Baja de un coche en alquiler");
        System.out.println("[3] Modificaciones");
        System.out.println("[4] Visualizar");
        System.out.println("[0] Salir");
        System.out.printf("Seleccione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuAltas() {
        System.out.println("------- ALTAS -------");
        System.out.println("[1] Empresa");
        System.out.println("[2] Coche en Venta");
        System.out.println("[3] Coche en Alquiler");
        System.out.println("[4] Uso de un coche en alquiler");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuModificaciones() {
        System.out.println("------- MODIFICACIONES -------");
        System.out.println("[1] Precio de un coche en venta");
        System.out.println("[2] Precio día de un coche en alquiler");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuVisualizar() {
        System.out.println("------- VISUALIZAR -------");
        System.out.println("[1] Todas las empresas con sus coches");
        System.out.println("[2] Empresa con sus coches de alquiler");
        System.out.println("[3] Factura de un determinado coche en alquiler de una empresa entre 2 fechas");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static void altaCocheVenta() {
        System.out.println("--- Introduzca el cif de la empresa a la que desea añadir el coche en venta ---");
        String cif = Crear.pedirCif();
        Session session = NewHibernateUtil.getSession();
        Empresa empresa = Consultar.encontrarEmpresaPorCif(session, cif);
        if (empresa != null) {
            System.out.println("--- Datos del coche en venta ---");
            CocheVenta cocheVenta = Crear.nuevoCocheVenta(empresa);
            empresa.getCoches().add(cocheVenta);
            session.close();
            Altas.nuevoCocheVenta(cocheVenta);
        } else {
            System.err.println("No existe una empresa con ese cif");
        }
    }

    public static void altaEmpresa() {
        Empresa empresa = Crear.nuevaEmpresa();
        Altas.nuevaEmpresa(empresa);
    }

    public static void altaCocheAlquiler() {
        System.out.println("--- Introduzca el cif de la empresa a la que desea añadir el coche en alquiler ---");
        String cif = Crear.pedirCif();
        Session session = NewHibernateUtil.getSession();
        Empresa empresa = Consultar.encontrarEmpresaPorCif(session, cif);
        if (empresa != null) {
            System.out.println("--- Datos del coche en alquiler ---");
            CocheAlquiler cocheAlquiler = Crear.nuevoCocheAlquiler(empresa);
            empresa.getCoches().add(cocheAlquiler);
            session.close();
            Altas.nuevoCocheAlquiler(cocheAlquiler);
        } else {
            System.err.println("No existe una empresa con ese cif");
        }
    }

    public static void altaUsoCocheAlquiler() {
        System.out.println("--- Introduzca el código del coche de alquiler al que desea añadir un uso ---");
        String codigo = Crear.pedirCodigo();
        Session session = NewHibernateUtil.getSession();
        CocheAlquiler cocheAlquiler = Consultar.encontrarCocheAlquilerPorCodigo(session, codigo);
        if (cocheAlquiler != null) {
            Uso uso = Crear.nuevoUso(cocheAlquiler);
            cocheAlquiler.getUsos().add(uso);
            session.close();
            Altas.nuevoUso(uso);
        } else {
            System.err.println("No existe un coche de alquiler con ese código");
        }
    }

    public static void modificarPrecioCocheVenta() {
        Visualizar.mostrarCochesVenta(Consultar.extraerCochesEnVenta());
        System.out.println("--- Introduzca el código del coche en venta al que desea modificar su precio ---");
        String codigo = Crear.pedirCodigo();
        CocheVenta cocheVenta = Consultar.encontrarCocheVentaPorCodigo(codigo);
        if (cocheVenta != null) {
            Visualizar.mostrarCocheVenta(cocheVenta);
            if (Pedir.duda("¿Es este el coche en venta al que desea modificar su precio?")) {
                Modificar.precioCocheVenta(cocheVenta);
            }
        } else {
            System.err.println("No existe un coche en venta con ese código");
        }
    }

    public static void modificarPrecioDiaCocheAlquiler() {
        Visualizar.mostrarCochesAlquiler(Consultar.extraerCochesDeAlquiler());
        System.out.println("--- Introduzca el código del coche en alquiler al que desea modificar su precio por día---");
        String codigo = Crear.pedirCodigo();
        CocheAlquiler cocheAlquiler = Consultar.encontrarCocheAlquilerPorCodigo(codigo);
        if (cocheAlquiler != null) {
            Visualizar.mostrarCocheAlquiler(cocheAlquiler);
            if (Pedir.duda("¿Es este el coche en alquiler al que desea modificar su precio por día?")) {
                Modificar.precioDiaCocheAlquiler(cocheAlquiler);
            }
        } else {
            System.err.println("No existe un coche en alquiler con ese código");
        }
    }
}
