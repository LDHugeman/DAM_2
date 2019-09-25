package autorlibros;

import Objetos.Autor;
import Objetos.Libro;
import java.util.ArrayList;

/**
 *
 * @author luisd
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

    public static void libroYAutor(Libro libro, String nombreAutor) {
        System.out.println("---------------- LIBRO ----------------");
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Precio: " + libro.getPrecio());
        System.out.println("Nombre del autor: " + nombreAutor);
        System.out.println("---------------------------------------");
    }

    public static void libros(ArrayList<Libro> libros) {
        for (Libro libro : libros) {
            verLibro(libro);
        }
    }
    
}
