package institutoscliclostalleres;

import java.sql.Statement;
import objetos.Ciclo;
import objetos.Instituto;
import objetos.Taller;
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
                    Instituto instituto = Crear.nuevoInstituto();
                    Altas.nuevoInstituto(instituto);
                    break;
                case 2:
                    /* Ciclo ciclo = Crear.nuevoCiclo();
                    Altas.nuevoCiclo(ciclo);*/
                    break;
                case 3:
                    /*System.out.printf("Código del instituto: ");
                    int codigoInstituto = Pedir.numeroEntero();
                    Instituto institutoEncontrado = Consultar.encontrarInstitutoPorCodigo(codigoInstituto);
                    if(institutoEncontrado!=null){
                        Taller taller = Crear.nuevoTaller();
                        Altas.nuevoTaller(taller);
                    } else {
                        System.err.println("No existe un instituto con ese código");
                    }          */
                    break;
                case 0:
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }

    public static void menuAñadir(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuAñadir();
            switch (opcion) {
                case 1:
                    añadirCicloInstituto();
                    break;
                case 2:
                    añadirTallerInstituto();
                    break;
                case 3:
                    Uso uso = Crear.nuevoUso();
                    Altas.nuevoUso(uso);
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
                    break;
                case 2:
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
        System.out.println("------- MENU -------");
        System.out.println("[1] Altas ");
        System.out.println("[2] Añadir");
        System.out.println("[3] Bajas");
        System.out.println("[4] Visualizar");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuAltas() {
        System.out.println("------- ALTAS -------");
        System.out.println("[1] Instituto");
        System.out.println("[2] Ciclo");
        System.out.println("[3] Taller");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuAñadir() {
        System.out.println("------- AÑADIR -------");
        System.out.println("[1] Ciclo a un instituto");
        System.out.println("[2] Taller a un instituto");
        System.out.println("[3] Uso a un determinado ciclo de un taller");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuBajas() {
        System.out.println("------- BAJAS -------");
        System.out.println("[1] Ciclo");
        System.out.println("[2] Taller");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuVisualizar() {
        System.out.println("------- VISUALIZAR -------");
        System.out.println("[1] Ciclos que usaron un taller");
        System.out.println("[2] Ciclos de un instito");
        System.out.println("[3] Institutos donde se imparte un ciclo");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static void añadirCicloInstituto() {
        System.out.println("--- Introduzca el código del instituto al que desea añadir el ciclo ---");
        int codigo = Crear.pedirCodigo();
        Session session = NewHibernateUtil.getSession();
        Instituto instituto = Consultar.encontrarInstitutoPorCodigo(session, codigo);
        if (instituto != null) {
            System.out.println("--- Introduzca los datos del ciclo ---");
            Ciclo ciclo = Crear.nuevoCiclo();
            Altas.nuevoCiclo(ciclo);
            instituto.getCiclos().add(ciclo);
            session.close();
        } else {
            System.err.println("No existe un instituto con ese código");
        }
    }

    public static void añadirTallerInstituto() {
        System.out.println("--- Introduzca el código del instituto al que desea añadir el taller ---");
        int codigo = Crear.pedirCodigo();
        Session session = NewHibernateUtil.getSession();
        Instituto instituto = Consultar.encontrarInstitutoPorCodigo(session, codigo);
        if (instituto != null) {
            Taller taller = Crear.nuevoTaller(instituto);
            Altas.nuevoTaller(taller);
            instituto.getTalleres().add(taller);
            session.close();
        } else {
            System.err.println("No existe un instituto con ese código");
        }
    }
}
