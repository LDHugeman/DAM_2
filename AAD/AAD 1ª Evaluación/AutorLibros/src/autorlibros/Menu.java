package autorlibros;

import Objetos.Autor;
import Objetos.Libro;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author a18luisdvp
 */
public class Menu {

    public static void menuInsertar(BufferedReader lee, Statement sentencia) throws IOException {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuInsertar(lee);
            switch (opcion) {
                case 1:
                    insertarAutorYLibros(sentencia, lee);
                    break;
                case 2:
                    añadirLibrosAutorExistente(sentencia, lee);
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    public static void menuEliminar(Statement sentencia, BufferedReader lee) throws IOException {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuEliminar(lee);
            switch (opcion) {
                case 1:
                    eliminarLibro(sentencia, lee);
                    break;
                case 2:
                    eliminarAutorYLibros(sentencia, lee);
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    public static void menuModificar(Statement sentencia, BufferedReader lee) throws IOException {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuModificar(lee);
            switch (opcion) {
                case 1:
                    modificarTituloLibro(sentencia, lee);
                    break;
                case 2:
                    modificarPrecioLibro(sentencia, lee);
                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
    }

    public static void menuVisualizar(Statement sentencia, BufferedReader lee) throws IOException {
        byte opcion = 0;
        do {
            opcion = seleccionarOpcionMenuVisualizar(lee);
            switch (opcion) {
                case 1:
                    System.out.println("--- Introduzca el título del libro que desea ver ---");
                    String titulo = Crear.pedirTitulo(lee);
                    Libro libroEncontrado = Consultar.encontrarLibroPorTitulo(sentencia, titulo);
                    if (libroEncontrado != null) {
                        String nombreAutor = Consultar.encontrarNombreAutorPorTituloLibro(sentencia, titulo);
                        Visualizar.libroYAutor(libroEncontrado, nombreAutor);
                    } else {
                        System.err.println("No existe ningún libro con ese título");
                    }
                    break;
                case 2:
                    System.out.println("--- Introduzca el nombre del autor del que desea ver sus libros ---");
                    String nombre = Crear.pedirNombre(lee);
                    Autor autorEncontrado = Consultar.encontrarAutorPorNombre(sentencia, nombre);
                    if (autorEncontrado != null) {
                        System.out.println("--- Libros de " + autorEncontrado.getNombre() + " ---");
                        ArrayList<Libro> librosEncontrados = Consultar.encontrarLibrosPorNombreAutor(sentencia, nombre);
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

    public static byte seleccionarOpcionMenuPrincipal(BufferedReader lee) throws IOException {
        System.out.println("------- MENU -------");
        System.out.println("[1] Insertar ");
        System.out.println("[2] Eliminar");
        System.out.println("[3] Modificar");
        System.out.println("[4] Visualizar");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Byte.parseByte(lee.readLine());
    }

    public static byte seleccionarOpcionMenuInsertar(BufferedReader lee) throws IOException {
        System.out.println("------- INSERTAR -------");
        System.out.println("[1] Autor y libros");
        System.out.println("[2] Añadir libros a un autor ya existente");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Byte.parseByte(lee.readLine());
    }

    public static byte seleccionarOpcionMenuEliminar(BufferedReader lee) throws IOException {
        System.out.println("------- ELIMINAR -------");
        System.out.println("[1] Libro");
        System.out.println("[2] Autor");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Byte.parseByte(lee.readLine());
    }

    public static byte seleccionarOpcionMenuModificar(BufferedReader lee) throws IOException {
        System.out.println("------- MODIFICAR -------");
        System.out.println("[1] Título de un libro");
        System.out.println("[2] Precio de un libro");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Byte.parseByte(lee.readLine());
    }

    public static byte seleccionarOpcionMenuVisualizar(BufferedReader lee) throws IOException {
        System.out.println("------- VISUALIZAR -------");
        System.out.println("[1] Libro y su autor");
        System.out.println("[2] Libros de un autor");
        System.out.println("[3] Todos los libros");
        System.out.println("[4] Todos los autores con sus libros");
        System.out.println("[0] Salir");
        System.out.printf("Selecione una opción: ");
        return Byte.parseByte(lee.readLine());
    }

    public static void eliminarLibro(Statement sentencia, BufferedReader lee) throws IOException {
        System.out.println("--- Introduzca el dni del autor del que desea eliminar el libro ---");
        String dni = Crear.pedirDni(lee);
        if (Consultar.existeAutorPorDni(sentencia, dni)) {
            System.out.println("--- Introduzca el titulo del libro que desea eliminar ---");
            String titulo = Crear.pedirTitulo(lee);
            Libro libroEncontrado = Consultar.encontrarLibroPorTitulo(sentencia, titulo);
            if (libroEncontrado != null) {
                Visualizar.verLibro(libroEncontrado);
                System.out.println("Es este el libro que desea eliminar?");
                System.out.println("[1] Sí");
                System.out.println("[2] No");
                System.out.printf("Seleccione una opción: ");
                byte opcionLibro = Byte.parseByte(lee.readLine());
                if (opcionLibro == 1) {
                    Eliminar.libro(sentencia, titulo);
                }
            } else {
                System.err.println("No existe ningún libro con ese título");
            }
        } else {
            System.err.println("No existe un autor con ese dni");
        }
    }

    public static void insertarAutorYLibros(Statement sentencia, BufferedReader lee) throws IOException {
        System.out.println("--- Introduzca los datos del autor ---");
        Autor autor = Crear.nuevoAutor(lee);
        try {
            Insertar.nuevoAutor(autor, sentencia);
            System.out.println("--- Introduzca los datos del libro ---");
            Libro libro = Crear.nuevoLibro(lee);
            Insertar.nuevoLibro(libro, sentencia, autor.getDni());
            byte opcionLibro = 0;
            do {
                System.out.println("Desea añadir más libros al autor " + autor.getNombre() + "?");
                System.out.println("[1] Sí");
                System.out.println("[2] No");
                System.out.printf("Seleccione una opción: ");
                opcionLibro = Byte.parseByte(lee.readLine());
                if (opcionLibro == 1) {
                    System.out.println("--- Introduzca los datos del libro ---");
                    Libro nuevoLibro = Crear.nuevoLibro(lee);
                    Insertar.nuevoLibro(nuevoLibro, sentencia, autor.getDni());
                }
            } while (opcionLibro == 1);
        } catch (SQLException excepcion) {
            System.err.println("El autor no ha podido ser insertado con sus libros");
            System.out.println(excepcion.getMessage());
        }
    }

    public static void añadirLibrosAutorExistente(Statement sentencia, BufferedReader lee) throws IOException {
        System.out.println("--- Introduzca el dni del autor al que desea añadir más libros ---");
        String dni = Crear.pedirDni(lee);
        Autor autorEncontrado = Consultar.encontrarAutorPorDni(sentencia, dni);
        if (autorEncontrado != null) {
            byte opcionLibro = 0;
            Visualizar.autor(autorEncontrado);
            System.out.println("Es este el autor al que desea añadir más libros?");
            System.out.println("[1] Sí");
            System.out.println("[2] No");
            System.out.printf("Seleccione una opción: ");
            byte opcion = Byte.parseByte(lee.readLine());
            if (opcion == 1) {
                Libro nuevoLibro = Crear.nuevoLibro(lee);
                Insertar.nuevoLibro(nuevoLibro, sentencia, autorEncontrado.getDni());
                do {
                    System.out.println("Desea añadir más libros al autor " + autorEncontrado.getNombre() + "?");
                    System.out.println("[1] Sí");
                    System.out.println("[2] No");
                    System.out.printf("Seleccione una opción: ");
                    opcionLibro = Byte.parseByte(lee.readLine());
                    if (opcionLibro == 1) {
                        System.out.println("--- Introduzca los datos del libro ---");
                        Libro libro2 = Crear.nuevoLibro(lee);
                        Insertar.nuevoLibro(libro2, sentencia, autorEncontrado.getDni());
                    }
                } while (opcionLibro == 1);
            }
        } else {
            System.err.println("No hay ningún autor con ese dni");
        }
    }

    public static void eliminarAutorYLibros(Statement sentencia, BufferedReader lee) throws IOException {
        System.out.println("--- Introduzca el dni del autor que desea eliminar ---");
        String dniAutor = Crear.pedirDni(lee);
        Autor autorEncontrado = Consultar.encontrarAutorPorDni(sentencia, dniAutor);
        if (autorEncontrado != null) {
            Visualizar.autor(autorEncontrado);
            System.out.println("Es este el autor que desea eliminar?");
            System.out.println("[1] Sí");
            System.out.println("[2] No");
            System.out.printf("Seleccione una opción: ");
            byte opcionAutor = Byte.parseByte(lee.readLine());
            if (opcionAutor == 1) {
                Eliminar.autorYLibros(sentencia, dniAutor);
                System.out.println("El autor " + autorEncontrado.getNombre() + " ha sido eliminado");
            }
        } else {
            System.err.println("No existe un autor con ese dni");
        }
    }

    public static void modificarTituloLibro(Statement sentencia, BufferedReader lee) throws IOException {
        String titulo = Crear.pedirTitulo(lee);
        Libro libroEncontrado = Consultar.encontrarLibroPorTitulo(sentencia, titulo);
        if (libroEncontrado != null) {
            Visualizar.verLibro(libroEncontrado);
            System.out.println("Es este el libro que desea modificar?");
            System.out.println("[1] Sí");
            System.out.println("[2] No");
            System.out.printf("Seleccione una opción: ");
            byte opcionLibro = Byte.parseByte(lee.readLine());
            if (opcionLibro == 1) {
                Modificar.tituloLibro(sentencia, lee, titulo);
            }
        } else {
            System.err.println("No existe ningún libro con ese título");
        }
    }

    public static void modificarPrecioLibro(Statement sentencia, BufferedReader lee) throws IOException {
        String titulo = Crear.pedirTitulo(lee);
        Libro libroEncontrado = Consultar.encontrarLibroPorTitulo(sentencia, titulo);
        if (libroEncontrado != null) {
            Visualizar.verLibro(libroEncontrado);
            System.out.println("Es este el libro que desea modificar?");
            System.out.println("[1] Sí");
            System.out.println("[2] No");
            System.out.printf("Seleccione una opción: ");
            byte opcionLibro = Byte.parseByte(lee.readLine());
            if (opcionLibro == 1) {
                Modificar.precioLibro(sentencia, lee, titulo);
            }
        } else {
            System.err.println("No existe ningún libro con ese título");
        }
    }
    
    public static void mostrarAutoresYLibros(Statement sentencia){
        for(Autor autor : Consultar.extraerAutores(sentencia)){
            Visualizar.autor(autor);
            Visualizar.libros(Consultar.librosDeAutor(sentencia, autor.getDni()));
            System.out.println("#######################################");
        }
    }
}
