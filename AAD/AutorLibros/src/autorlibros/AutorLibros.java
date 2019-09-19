package autorlibros;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author a18luisdvp
 */
public class AutorLibros {

    public static void main(String[] args) {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado;
        String url = "jdbc:mysql://localhost:3307/Libreria?user=root&password=usbw";

        try {
            conexion = DriverManager.getConnection(url);
        } catch (SQLException excepcion) {
            System.out.println("No hay ningún Driver registrado que reconozca la URL especificada");
        } catch (Exception excepcion) {
            System.out.println("Se ha producido un error");
        }

        try {
            sentencia = conexion.createStatement();
            Crear.tablas(sentencia);
        } catch (Exception excepcion) {
            System.out.println("Error al conectarse con el driver que maneja la BD");
        }
        
    }

}
