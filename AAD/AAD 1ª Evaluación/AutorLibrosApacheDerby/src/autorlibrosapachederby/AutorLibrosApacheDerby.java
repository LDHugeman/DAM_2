
package autorlibrosapachederby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a18luisdvp
 */
public class AutorLibrosApacheDerby {

    public static void main(String[] args) {
        Connection conexion = null;
        Statement sentencia = null;
        String url = "jdbc:derby:LibreriaDerby;create=true";
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
        
        byte opcionSeleccionada = 0;
        do{
            opcionSeleccionada = Menu.seleccionarOpcionMenuPrincipal();
            switch(opcionSeleccionada){
                case 1:
                    Menu.menuInsertar(sentencia);
                    break;
                case 2:
                    Menu.menuEliminar(sentencia);
                    break;
                case 3:
                    Menu.menuVisualizar(sentencia);
                    break;
                case 0:
                    break;
            }
        }while(opcionSeleccionada!=0);
    }
    
}
