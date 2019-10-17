package librosautoreshibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import metodos.AdicionalesBD;
import metodos.Bajas;
import metodos.Consultas;
import metodos.Creacion;
import metodos.Inserciones;
import metodos.Menu;
import metodos.Modificaciones;
import metodos.Visualizar;

/**
 *
 * @author
 */
public class LibrosAutoresHibernate {

    static Connection conn;
    static Statement sentencia;
    static ResultSet res;

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/?user=root&password=usbw";
        try {
            conn = DriverManager.getConnection(url);
            sentencia = conn.createStatement();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
            System.exit(1);
        }
        try {
            if (!sentencia.executeQuery("show databases like 'librosautoreshibernate'").first()) {
                Creacion.crearTablas(sentencia);
            } else {
                sentencia.execute("use librosautoreshibernate");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            System.exit(2);
        }

        NewHibernateUtil.getSessionFactory();
        int opcion, opcionSub;
        do {
            opcion = Menu.menuPrin();
            switch (opcion) {
                case 1:
                    do {
                        opcionSub = Menu.menuAlta();
                        switch (opcionSub) {
                            case 1:
                                Inserciones.insertarAutor();
                                break;
                            case 2:
                                Inserciones.insertarLibro();
                                break;
                            case 3:
                                Inserciones.insertarTelf();
                                break;
                            case 0:
                                break;
                            case -1:
                                System.out.println("Debe introducir un numero de 0-3.");
                                break;
                            default:
                                System.out.println("Error las opciones van de 0-3");
                                break;
                        }
                    } while (opcionSub != 0);
                    break;
                case 2:
                    do {
                        opcionSub = Menu.menuBajas();
                        switch (opcionSub) {
                            case 1:
                                Bajas.bajasAutor();
                                break;
                            case 2:
                                Bajas.bajasLibro();
                                break;
                            case 3:
                                Bajas.bajasTelf();
                                break;
                            case 0:
                                break;
                            case -1:
                                System.out.println("Debe introducir un numero de 0-3.");
                                break;
                            default:
                                System.out.println("Error las opciones van de 0-3");
                                break;
                        }
                    } while (opcionSub != 0);
                    break;
                case 3:
                    do {
                        opcionSub = Menu.menuMod();
                        switch (opcionSub) {
                            case 1:
                                Modificaciones.modificarLibro();
                                break;
                            case 2:
                                Modificaciones.modificarTelefono();
                                break;
                            case 0:
                                break;
                            case -1:
                                System.out.println("Debe introducir un numero de 0-2.");
                                break;
                            default:
                                System.out.println("Error las opciones van de 0-2");
                                break;
                        }
                    } while (opcionSub != 0);
                    break;
                case 4:
                    // Consultas.datosLibro();
                    //Consultas.todosAutores();
                    do {
                        opcionSub=Menu.menuCon();
                        switch(opcionSub){
                            case 1:
                                Consultas.datosLibro();
                                break;
                            case 2:
                                Consultas.visualizarLibrosA();
                                break;
                            case 3:
                                Visualizar.visualizarLibros();
                                break;
                            case 4:
                                Consultas.todosAutores();
                                break;
                            case 0:
                                break;
                            case -1:
                                System.out.println("Debe introducir un numero de 0-4.");
                                break;
                            default:
                                System.out.println("Error las opciones van de 0-4");
                                break;
                        }
                    } while (opcionSub != 0);
                    break;
                case 0:
                    NewHibernateUtil.getSessionFactory().close();
                    break;
                case -1:
                    System.out.println("Debe introducir un numero de 0-4.");
                    break;
                default:
                    System.out.println("Error las opciones van de 0-4");
                    break;
            }

        } while (opcion != 0);

    }

}
