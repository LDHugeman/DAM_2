
package autorlibros;

import Objetos.Autor;
import Objetos.Libro;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a18luisdvp
 */
public class Insertar {
    
    public static void nuevoAutor(Autor autor, Statement sentencia)throws SQLException{
        try {
            sentencia.executeUpdate("INSERT INTO AUTORES (dni, nombre, nacionalidad) VALUES ('" + autor.getDni() + "','" + autor.getNombre() + "','" + autor.getNacionalidad() + "')");
        } catch (SQLException excepcion) {
            System.err.println("Error al insertar el autor");
            System.out.println(excepcion.getMessage());
            throw excepcion;
        }
    }
    
    public static void nuevoLibro(Libro libro, Statement sentencia, String dni){
        try{
            String sql = String.format("INSERT INTO LIBROS (titulo, precio, autor) VALUES ('%s','%s','%s')", libro.getTitulo(), libro.getPrecio(), dni); /*%s o %f*/
            sentencia.executeUpdate(sql);
        }catch(SQLException excepcion){
            System.err.println("Error al insertar el libro");
            System.out.println(excepcion.getMessage());
        }
    }
}
