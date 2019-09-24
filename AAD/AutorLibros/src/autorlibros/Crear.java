
package autorlibros;

import Objetos.Autor;
import Objetos.Libro;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a18luisdvp
 */
public class Crear {
    
    public static void tablas (Statement sentencia){
        try{
            sentencia.execute("CREATE DATABASE IF NOT EXISTS LIBRERIA;");          
            sentencia.execute("USE LIBRERIA;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS AUTORES"
                    + "(dni CHAR(9) NOT NULL,"
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "nacionalidad VARCHAR(30) NOT NULL,"
                    + "PRIMARY KEY (dni))"
                    + "ENGINE INNOBB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS LIBROS"
                    + "(idLibro INT(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "titulo VARCHAR(30) NOT NULL,"
                    + "precio float(6) UNSIGNED NOT NULL,"
                    + "autor CHAR(9) NOT NULL,"
                    + "PRIMARY KEY (idLibro),"
                    + "UNIQUE INDEX AK_TITULO (titulo),"
                    + "FOREIGN KEY (autor) references AUTORES(dni) "
                    + "ON DELETE RESTRICT "
                    + "ON UPDATE CASCADE) "
                    + "ENGINE INNODB;");
            System.out.println("Base de datos creada");
        }catch(SQLException excepcion){
            System.out.println("Error al crear las tablas");
            System.out.println(excepcion.getMessage());
        }
    }
    
    public static Autor nuevoAutor(BufferedReader lee)throws IOException{
        String dni = pedirDni(lee);
        System.out.printf("Nombre: ");
        String nombre = lee.readLine();
        System.out.printf("Nacionalidad: ");
        String nacionalidad = lee.readLine();
        return new Autor(dni, nombre, nacionalidad);
    }
    
    public static Libro nuevoLibro(BufferedReader lee)throws IOException{
        String titulo = pedirTitulo(lee);
        System.out.printf("Precio: ");
        float precio = Float.parseFloat(lee.readLine());
        return new Libro(titulo, precio);
    }
    
    public static String pedirDni (BufferedReader lee)throws IOException{
        System.out.printf("Dni: ");
        return lee.readLine();
    }
    
    public static String pedirTitulo (BufferedReader lee)throws IOException{
        System.out.printf("Título: ");
        return lee.readLine();
    }
}
