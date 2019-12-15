package clinicadental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @authors Alberto y David
 */
public class ClinicaDental {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3307/?user=root&password=usbw";
        try {
            Connection conexion = DriverManager.getConnection(url);
            Statement sentencia = conexion.createStatement();
            Crear.tablas(sentencia);
        } catch (SQLException excepcion) {
            System.err.println("No hay ningún Driver registrado que reconozca la URL especificada");
            System.out.println(excepcion.getMessage());
        }

        NewHibernateUtil.getSessionFactory();
        byte opcion = 0;
        do {
            opcion = Menu.seleccionarOpcionMenuPrincipal();
            switch (opcion) {
                case 1:
                    Menu.menuAltas();
                    break;
                case 2:
                    Menu.menuBajas();
                    break;
                case 3:
                    Menu.menuModificaciones();
                    break;
                case 4:
                    Menu.menuVisualizar();
                    break;
                case 0:
                    NewHibernateUtil.getSessionFactory().close();
                    break;
                default:
                    System.err.println("No existe esa opción");
            }
        } while (opcion != 0);
    }
}
