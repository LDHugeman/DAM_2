
package autorlibros;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a18luisdvp
 */
public class Modificar {
    
    public static void tituloLibro(Statement sentencia , BufferedReader lee, String titulo)throws IOException{
        System.out.println("--- Introduzca el nuevo título para el libro ---");
        String nuevoTitulo = Crear.pedirTitulo(lee);
        try{
            String sql = String.format("UPDATE LIBROS SET titulo='%s' WHERE titulo='%s'", nuevoTitulo, titulo);
            sentencia.executeUpdate(sql);
            System.out.println("El libro " + "'" + titulo + "'" + " ha sido modificado correctamente");
        }catch(SQLException excepcion){
            System.err.println("Error al modificar la tabla libros");
            System.err.println(excepcion.getMessage());
        }
    }
    
    public static void precioLibro(Statement sentencia , BufferedReader lee, String titulo)throws IOException{
        System.out.println("--- Introduzca el nuevo precio para el libro");
        float precio = Crear.pedirPrecio(lee);
        try{
            String sql = String.format("UPDATE LIBROS SET precio='%s' WHERE titulo='%s'", precio, titulo);
            sentencia.executeUpdate(sql);
            System.out.println("El libro " + "'" + titulo + "'" + " ha sido modificado correctamente");
        }catch(SQLException excepcion){
            System.err.println("Error al modificar la tabla libros");
            System.err.println(excepcion.getMessage());
        }
    }
}
