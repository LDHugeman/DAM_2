
package autorlibroshsql;

import objetos.Autor;
import objetos.Libro;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a18luisdvp
 */
public class Insertar {
    
    public static void nuevoAutor(Autor autor, Statement sentencia){
        try {
            sentencia.executeUpdate("INSERT INTO AUTORES (DNI, NOMBRE, NACIONALIDAD) VALUES ('" + autor.getDni() + "','" + autor.getNombre() + "','" + autor.getNacionalidad() + "')");
        } catch (SQLException excepcion) {
            System.err.println("Error al insertar el autor");
            System.out.println(excepcion.getMessage());
        }
    }
    
    public static void nuevoLibro(Libro libro, Statement sentencia){
        try{
            String sql = String.format("INSERT INTO LIBROS (TITULO, PRECIO) VALUES ('%s', %s)", libro.getTitulo(), libro.getPrecio()); 
            sentencia.executeUpdate(sql);
        }catch(SQLException excepcion){
            System.err.println("Error al insertar el libro");
            System.out.println(excepcion.getMessage());
        }
    }
    
    public static void registroAutorLibro(String dni, int idLibro, Statement sentencia){
        try{
            String sql = String.format("INSERT INTO AUTORES_LIBROS (AUTOR, LIBRO) VALUES ('%s',%s)", dni, idLibro); 
            sentencia.executeUpdate(sql);
        }catch(SQLException excepcion){
            System.err.println("Error al insertar el registro");
            System.out.println(excepcion.getMessage());
        }
    }
}
