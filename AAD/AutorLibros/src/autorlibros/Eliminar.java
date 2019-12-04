package autorlibros;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author luisd
 */
public class Eliminar {

    public static void autorYLibros(Statement sentencia, String dni) {
        String sqlBorrarAutor = String.format("DELETE FROM AUTORES WHERE dni='%s'", dni);
        String sqlBorrarLibros = String.format("DELETE FROM LIBROS WHERE autor='%s'", dni);
        try {
            sentencia.executeUpdate(sqlBorrarLibros);
            sentencia.executeUpdate(sqlBorrarAutor);
        } catch (SQLException excepcion) {
            System.out.println("Error al eliminar el autor");
            System.out.println(excepcion.getMessage());
        }
    }

    public static void libro(Statement sentencia, String titulo) {
        String sqlBorrarLibro = String.format("DELETE FROM LIBROS WHERE titulo='%s'", titulo);
        try {
            sentencia.executeUpdate(sqlBorrarLibro);
            System.out.println("El libro " + titulo + "ha sido eliminado");
        } catch (SQLException excepcion) {
            System.out.println("Error al eliminar el libro");
            System.out.println(excepcion.getMessage());
        }
    }
}
