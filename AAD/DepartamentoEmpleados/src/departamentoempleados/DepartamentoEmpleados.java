package departamentoempleados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a18luisdvp
 */
public class DepartamentoEmpleados {

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
            if(!sentencia.executeQuery("SHOW DATABASES LIKE 'EMPRESA'").first()){
                Crear.tablas(sentencia);
            }else{
                System.out.println("2dasda");
                sentencia.execute("USE EMPRESA");
            }
        } catch (SQLException excepcion) {
            System.out.println("Error al conectarse con el driver que maneja la BD");
            System.out.println(excepcion.getMessage());
        }
        
        byte opcion = 0;
        do {
            opcion = Menu.seleccionarOpcionMenuPrincipal();
            switch (opcion) {
                case 1:
                    Menu.menuAltas(sentencia);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }
}
