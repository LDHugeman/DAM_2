
package clinicadental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a18luisdvp
 */
public class ClinicaDental {

    
    public static void main(String[] args) {
        
        Connection conexion = null;
        Statement sentencia = null;
        String url = "jdbc:mysql://localhost:3307/?user=root&password=usbw";
        try {
            conexion = DriverManager.getConnection(url);
        } catch (SQLException excepcion) {
            System.out.println("No hay ningún Driver registrado que reconozca la URL especificada");
            System.out.println(excepcion.getMessage());
        }

        try {
            sentencia = conexion.createStatement();
            Crear.tablas(sentencia);
        } catch (SQLException excepcion) {
            System.out.println("Error al conectarse con el driver que maneja la BD");
            System.out.println(excepcion.getMessage());
        }
    }
    
}
