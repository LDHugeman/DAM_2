package autorlibrosapachederby;

import java.sql.Statement;
import java.util.ArrayList;
import objetos.Autor;
import objetos.Libro;

/**
 *
 * @author a18luisdvp
 */
public class Menu {

    public static void menuInsertar(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuInsertar();
            switch (opcion) {
                case 1:
                    Autor autor = Crear.nuevoAutor(sentencia);
                    Insertar.nuevoAutor(autor, sentencia);
                    break;
                case 2:
                    Libro libro = Crear.nuevoLibro();
                    Insertar.nuevoLibro(libro, sentencia);
                    break;
                case 3:
                    System.out.println("--- Introduzca el dni del autor ---");
                    String dni = Crear.pedirDniExistente(sentencia);
                    System.out.println("--- Introduzca el titulo del libro ---");
                    String titulo = Crear.pedirTitulo();
                    Libro libroEncontrado = Consultar.encontrarLibroPorTitulo(sentencia, titulo);
                    if (libroEncontrado != null) {
                        Insertar.registroAutorLibro(dni, libroEncontrado.getIdLibro(), sentencia);
                    } else {
                        System.err.println("No existe ningún libro con ese título");
                    }
                    break;
                case 4:
                    System.out.println("--- Introduzca el dni del autor al que desea añadir más libros ---");
                    String dniAutor = Crear.pedirDniExistente(sentencia);
                    Autor autorEncontrado = Consultar.encontrarAutorPorDni(sentencia, dniAutor);
                    Visualizar.autor(autorEncontrado);
                    if (Pedir.duda("Es este el autor al que desea añadir más libros?")) {
                        Libro nuevoLibro = Crear.nuevoLibro();
                        Insertar.nuevoLibro(nuevoLibro, sentencia);
                        nuevoLibro = Consultar.encontrarLibroPorTitulo(sentencia, nuevoLibro.getTitulo());
                        Insertar.registroAutorLibro(dniAutor, nuevoLibro.getIdLibro(), sentencia);
                        boolean opcionLibro;
                        do {
                            opcionLibro = Pedir.duda("Desea añadir más libros al autor " + autorEncontrado.getNombre() + "?");
                            if (opcionLibro) {
                                nuevoLibro = Crear.nuevoLibro();
                                Insertar.nuevoLibro(nuevoLibro, sentencia);
                                nuevoLibro = Consultar.encontrarLibroPorTitulo(sentencia, nuevoLibro.getTitulo());
                                Insertar.registroAutorLibro(dniAutor, nuevoLibro.getIdLibro(), sentencia);
                            }
                        } while (opcionLibro);
                    }
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    public static void menuEliminar(Statement sentencia) {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuEliminar();
            switch (opcion) {
                case 1:
                    System.out.println("--- Introduzca el titulo del libro que desea eliminar ---");
                    String titulo = Crear.pedirTitulo();
                    Libro libro = Consultar.encontrarLibroPorTitulo(sentencia, titulo);
                    if (libro != null) {
                        Visualizar.verLibro(libro);
                        if (Pedir.duda("Es este el libro que desea eliminar?")) {
                            Eliminar.libro(sentencia, titulo);
                        }
                    }else{
                        System.err.println("No existe un libro con ese título");
                    }
                    break;
                case 2:
                    System.out.println("--- Introduzca el dni del autor que desea eliminar ---");
                    String dni = Crear.pedirDniExistente(sentencia);
                    Autor autor = Consultar.encontrarAutorPorDni(sentencia, dni);
                    Visualizar.autor(autor);
                    if(Pedir.duda("Es este el autor que desea eliminar?")){
                        Eliminar.autor(sentencia, dni);
                    }
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }
    
    public static void menuVisualizar(Statement sentencia){
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuVisualizar();
            switch (opcion) {
                case 1:
                    System.out.println("--- Introduzca el título del libro que desea ver ---");
                    String titulo = Crear.pedirTitulo();
                    Libro libro = Consultar.encontrarLibroPorTitulo(sentencia, titulo);
                    if(libro!=null){
                        ArrayList<String> nombresAutores = Consultar.encontrarNombreAutoresPorIDLibro(sentencia, libro.getIdLibro());
                        Visualizar.libroYAutor(libro, nombresAutores);
                    }else{
                        System.err.println("No existe un libro con ese título");
                    }
                    break;
                case 2:
                    System.out.println("--- Introduzca el nombre del autor del que desea ver sus libros ---");
                    String nombre = Crear.pedirNombre();
                    Autor autorEncontrado = Consultar.encontrarAutorPorNombre(sentencia, nombre);
                    if (autorEncontrado != null) {
                        System.out.println("--- Libros de " + autorEncontrado.getNombre() + " ---");
                        ArrayList<Libro> librosEncontrados = Consultar.encontrarLibrosPorDniAutor(sentencia, autorEncontrado.getDni());
                        Visualizar.libros(librosEncontrados);
                    } else {
                        System.err.println("No hay ningún autor con ese nombre");
                    }
                    break;
                case 3:
                    ArrayList<Libro> libros = Consultar.extraerLibros(sentencia);
                    Visualizar.libros(libros);
                    break;
                case 4:
                    mostrarAutoresYLibros(sentencia);
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    public static byte seleccionarOpcionMenuPrincipal() {
        System.out.println("------- MENU -------");
        System.out.println("[1] Insertar ");
        System.out.println("[2] Eliminar");
        System.out.println("[3] Visualizar");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuInsertar() {
        System.out.println("------- INSERTAR -------");
        System.out.println("[1] Autor");
        System.out.println("[2] Libro");
        System.out.println("[3] Asociar autor y libro");
        System.out.println("[4] Añadir libros a un autor ya existente");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }

    public static byte seleccionarOpcionMenuEliminar() {
        System.out.println("------- ELIMINAR -------");
        System.out.println("[1] Libro");
        System.out.println("[2] Autor");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }
    
    public static byte seleccionarOpcionMenuVisualizar(){
        System.out.println("------- VISUALIZAR -------");
        System.out.println("[1] Libro y su autor");
        System.out.println("[2] Libros de un autor");
        System.out.println("[3] Todos los libros");
        System.out.println("[4] Todos los autores con sus libros");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Pedir.numeroByte();
    }
    
    public static void mostrarAutoresYLibros(Statement sentencia){
        for(Autor autor : Consultar.extraerAutores(sentencia)){
            Visualizar.autor(autor);
            Visualizar.libros(Consultar.librosDeAutor(sentencia, autor.getDni()));
            System.out.println("#######################################");
        }
    }
}
