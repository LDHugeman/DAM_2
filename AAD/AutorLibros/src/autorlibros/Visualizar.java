package autorlibros;

import Objetos.Autor;
import Objetos.Libro;

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
    }
    
    public static void libro(Libro libro){
        System.out.println("---------------- LIBRO ----------------");
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Precio: " + libro.getPrecio());
    }
}
