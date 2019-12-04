package autorlibrosh2;

import excepciones.Validar;
import java.sql.SQLException;
import java.sql.Statement;
import objetos.Autor;
import objetos.Libro;

/**
 *
 * @author a18luisdvp
 */
public class Crear {

    public static void tablas(Statement sentencia) {
        try {
            sentencia.execute("CREATE TABLE AUTORES"
                    + "(DNI CHAR(9) PRIMARY KEY NOT NULL,"
                    + " NOMBRE VARCHAR(30) NOT NULL,"
                    + " NACIONALIDAD VARCHAR(30) NOT NULL);");

            sentencia.execute("CREATE TABLE LIBROS"
                    + "(IDLIBRO INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + " TITULO VARCHAR(30) NOT NULL,"
                    + " PRECIO FLOAT(6) NOT NULL,"
                    + "CONSTRAINT AK_TITULO UNIQUE (TITULO));");           
                    
            sentencia.execute("CREATE TABLE AUTORES_LIBROS"
                    + "(AUTOR CHAR(9) NOT NULL,"
                    + "LIBRO INTEGER NOT NULL,"
                    + "FOREIGN KEY(AUTOR) REFERENCES AUTORES(DNI)"
                    + " ON DELETE CASCADE"
                    + " ON UPDATE CASCADE,"
                    + "FOREIGN KEY(LIBRO) REFERENCES LIBROS(IDLIBRO)"
                    + " ON DELETE CASCADE"
                    + " ON UPDATE CASCADE,"
                    + "PRIMARY KEY(AUTOR, LIBRO));");
            
            System.out.println("Base de datos creada");
        } catch (SQLException excepcion) {
            System.out.println("Error al crear las tablas");
            System.out.println(excepcion.getMessage());
        }
    }

    public static Autor nuevoAutor(Statement sentencia) {
        String dni = pedirDniValido(sentencia);
        String nombre = pedirNombre();
        System.out.printf("Nacionalidad: ");
        String nacionalidad = Pedir.texto();
        return new Autor(dni, nombre, nacionalidad);
    }

    public static Libro nuevoLibro() {
        String titulo = pedirTitulo();
        float precio = pedirPrecio();
        return new Libro(titulo, precio);
    }

    public static String pedirDniValido(Statement sentencia) {
        String dni = "";
        do {
            System.out.printf("Dni: ");
            dni = Pedir.texto();
        } while (!Validar.esDniValido(dni));
        return dni;
    }
    
    public static String pedirDniExistente(Statement sentencia) {
        String dni = "";
        do {
            System.out.printf("Dni: ");
            dni = Pedir.texto();
        } while (!Validar.esDniExistenteValido(sentencia, dni));
        return dni;
    }  

    public static String pedirNombre() {
        System.out.printf("Nombre: ");
        return Pedir.texto();
    }

    public static String pedirTitulo() {
        System.out.printf("Título: ");
        return Pedir.texto();
    }

    public static float pedirPrecio() {
        System.out.printf("Precio: ");
        return Pedir.numeroRealFloat();
    }
}
