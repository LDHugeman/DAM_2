package institutosciclostalleres;

import java.sql.Statement;
import java.util.Date;
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
                    eliminarCiclo();
                    break;
                case 2:
                    eliminarTaller();
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
                    System.out.printf("Primer fecha(dd/MM/yyyy): ");
                    Date primerFecha = Pedir.fecha();
                    System.out.printf("Segunda fecha(dd/MM/yyyy): ");
                    Date segundaFecha = Pedir.fecha();
                    Session session = NewHibernateUtil.getSession();
                    Visualizar.ciclosUsaronTalleresEntreFechas(Consultar.usosTalleresEntreFechas(session, primerFecha, segundaFecha));
                    session.close();
                    break;
                case 2:
                    visualizarCiclosInstituto();
                    break;
                case 3:
                    visualizarInstitutosImparteCiclo();                  
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
        System.out.println("[1] Ciclos que usaron un taller entre 2 fechas");
        System.out.println("[2] Ciclos de un instituto");
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
            ciclo.getInstitutos().add(instituto);
            Altas.nuevoCiclo(ciclo);
            instituto.getCiclos().add(ciclo);
            Altas.guardar(instituto, session);
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
            instituto.getTalleres().add(taller);
            taller.setInstituto(instituto);
            Altas.nuevoTaller(taller);
            session.close();
        } else {
            System.err.println("No existe un instituto con ese código");
        }
    }

    public static void eliminarCiclo() {
        System.out.println("--- Introduzca el codigo del ciclo que desea eliminar ---");
        int codigo = Crear.pedirCodigo();
        Session session = NewHibernateUtil.getSession();
        Ciclo ciclo = Consultar.encontrarCicloPorCodigo(session, codigo);
        session.close();
        if (ciclo != null) {
            Bajas.ciclo(ciclo);
        } else {
            System.err.println("No existe ningún ciclo con ese codigo");
        }
    }

    public static void eliminarTaller() {
        System.out.println("--- Introduzca el codigo del taller que desea eliminar ---");
        int codigo = Crear.pedirCodigo();
        Session session = NewHibernateUtil.getSession();
        Taller taller = Consultar.encontrarTallerPorCodigo(session, codigo);
        session.close();
        if (taller != null) {
            Bajas.taller(taller);
        } else {
            System.err.println("No existe ningún taller con ese codigo");
        }
    }

    public static void visualizarCiclosInstituto() {
        System.out.println("--- Instroduzca el código del instituto del que desea ver sus ciclos ---");
        int codigo = Crear.pedirCodigo();
        Session session = NewHibernateUtil.getSession();
        Instituto instituto = Consultar.encontrarInstitutoPorCodigo(session, codigo);
        if (instituto != null) {
            Visualizar.ciclos(instituto.getCiclos());
        } else {
            System.err.println("No existe ningún instituto con ese código");
        }
        session.close();
    }

    public static void visualizarInstitutosImparteCiclo() {
        System.out.println("--- Instroduzca el código del ciclo del que desea ver los institutos en los que se imparte ---");
        int codigo = Crear.pedirCodigo();
        Session session = NewHibernateUtil.getSession();
        Ciclo ciclo = Consultar.encontrarCicloPorCodigo(session, codigo);
        if (ciclo != null) {
            Visualizar.institutos(ciclo.getInstitutos());
        } else {
            System.err.println("No existe ningún ciclo con ese código");
        }
        session.close();
    }
}
