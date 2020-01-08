
package autorlibroshsql;

import java.util.ArrayList;
import objetos.Autor;
import objetos.Libro;

/**
 *
 * @author a18luisdvp
 */
public class Visualizar {
    
    public static void autor(Autor autor) {
        System.out.println("---------------- AUTOR ----------------");
        System.out.println("Dni: " + autor.getDni());
        System.out.println("Nombre: " + autor.getNombre());
        System.out.println("Nacionalidad: " + autor.getNacionalidad());
        System.out.println("---------------------------------------");
    }
    
    public static void verLibro(Libro libro) {
        System.out.println("---------------- LIBRO ----------------");
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Precio: " + libro.getPrecio());
        System.out.println("---------------------------------------");
    }
    
    public static void libroYAutor(Libro libro, ArrayList<String> nombresAutores) {
        System.out.printf("%-15s %-25s %-35s %n", "TÍTULO", "PRECIO", "NOMBRE DEL AUTOR");
        for(String nombre: nombresAutores){
            System.out.printf("%-15s %-25.2f %-35s %n", libro.getTitulo(), libro.getPrecio(), nombre);
        }
    }

    public static void libros(ArrayList<Libro> libros) {
        for (Libro libro : libros) {
            verLibro(libro);
        }
    }
}
