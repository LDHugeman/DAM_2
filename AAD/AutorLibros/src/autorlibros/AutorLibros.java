package autorlibros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author a18luisdvp
 */
public class AutorLibros {

    public static void main(String[] args)throws IOException {
        BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
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
        
        byte opcionSeleccionada = 0;
        do{
            opcionSeleccionada = Menu.seleccionarOpcionMenuPrincipal(lee);
            switch(opcionSeleccionada){
                case 1:
                    Menu.menuInsertar(lee, sentencia);
                    break;
                case 2:
                    Menu.menuEliminar(sentencia, lee);
                    break;
                case 0:
                    break;
            }
        }while(opcionSeleccionada!=0); 
    }
}
