
package autorlibrosh2;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a18luisdvp
 */
public class Eliminar {
    
    public static void autor(Statement sentencia, String dni) {   
        try {
            String sqlBorrarLibros = String.format("DELETE FROM LIBROS WHERE IDLIBRO IN (SELECT LIBRO FROM AUTORES_LIBROS WHERE AUTOR='%s')", dni);
            String sqlBorrarAutor = String.format("DELETE FROM AUTORES WHERE dni='%s'", dni);            
            sentencia.executeUpdate(sqlBorrarLibros);
            sentencia.executeUpdate(sqlBorrarAutor);
            System.out.println("El autor con dni " + dni + " ha sido eliminado");
        } catch (SQLException excepcion) {
            System.out.println("Error al eliminar el autor");
            System.out.println(excepcion.getMessage());
        }
    }
    
    public static void libro(Statement sentencia, String titulo) {   
        try {
            String sql = String.format("DELETE FROM LIBROS WHERE titulo='%s'", titulo);
            sentencia.executeUpdate(sql);
            System.out.println("El libro " + titulo + " ha sido eliminado");
        } catch (SQLException excepcion) {
            System.out.println("Error al eliminar el libro");
            System.out.println(excepcion.getMessage());
        }
    }
}
