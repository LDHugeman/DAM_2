
package autorlibroshsql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import objetos.Autor;
import objetos.Libro;

/**
 *
 * @author a18luisdvp
 */
public class Consultar {
    
    public static Autor encontrarAutorPorDni (Statement sentencia, String dni){
        Autor autorEncontrado = null;
        try{               
            String sql = String.format("SELECT * FROM AUTORES WHERE dni='%s'", dni);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                autorEncontrado = construyeAutor(resultado);
            }
        }catch(SQLException excepcion){
            System.out.println("Error al acceder a la tabla autores");
            System.err.println(excepcion.getMessage());
        }
        return autorEncontrado;
    }
    
    public static boolean existeAutorPorDni (Statement sentencia, String dni){
        return encontrarAutorPorDni(sentencia, dni)!=null;
    }
    
    private static Autor construyeAutor(ResultSet resultado) throws SQLException{
        String dni = resultado.getString("DNI");
        String nombre = resultado.getString("NOMBRE");
        String nacionalidad = resultado.getString("NACIONALIDAD");
        return new Autor(dni, nombre, nacionalidad);
    }
    
    public static Libro encontrarLibroPorTitulo (Statement sentencia, String titulo){
        Libro libroEncontrado = null;
        try{
            String sql = String.format("SELECT * FROM LIBROS WHERE titulo='%s'", titulo);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){              
                libroEncontrado = construyeLibro(resultado);
            }
        }catch(SQLException excepcion){
            System.out.println("Error al acceder a la tabla libros");
            System.out.println(excepcion.getMessage());
        }
        return libroEncontrado;
    }
    
    public static boolean existeLibroPorTitulo(Statement sentencia, String titulo){
        return encontrarLibroPorTitulo(sentencia, titulo)!=null;
    }
    
    private static Libro construyeLibro (ResultSet resultado) throws SQLException{
        int idLibro = resultado.getInt("IDLIBRO");
        String titulo = resultado.getString("TITULO");
        float precio = resultado.getFloat("PRECIO");
        return new Libro(idLibro, titulo, precio);
    }
    
    public static ArrayList <String> encontrarNombreAutoresPorIDLibro(Statement sentencia, int idLibro){
        ArrayList<String> nombresAutores = new ArrayList<>();
        try{
            String sql = String.format("SELECT NOMBRE FROM AUTORES WHERE DNI IN (SELECT AUTOR FROM AUTORES_LIBROS WHERE LIBRO=%s)", idLibro);
            ResultSet resultado = sentencia.executeQuery(sql);
            while (resultado.next()){
                nombresAutores.add(resultado.getString("NOMBRE"));
            }          
        }catch(SQLException excepcion){
            System.out.println("Error al acceder a los datos");
            System.out.println(excepcion.getMessage());
        }
        return nombresAutores;
    }
    
    public static Autor encontrarAutorPorNombre (Statement sentencia, String nombre){
        Autor autorEncontrado = null;
        try{               
            String sql = String.format("SELECT * FROM AUTORES WHERE nombre='%s'", nombre);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                autorEncontrado = construyeAutor(resultado);
            }
        }catch(SQLException excepcion){
            System.out.println("Error al acceder a la tabla autores");
            System.err.println(excepcion.getMessage());
        }
        return autorEncontrado;
    }
    
    public static ArrayList<Libro> encontrarLibrosPorDniAutor (Statement sentencia, String dni){
        ArrayList<Libro> libros = new ArrayList<>();
        try{               
            String sql = String.format("SELECT * FROM LIBROS WHERE IDLIBRO IN (SELECT LIBRO FROM AUTORES_LIBROS WHERE AUTOR='%s')", dni);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                libros.add(construyeLibro(resultado));
            }
        }catch(SQLException excepcion){
            System.out.println("Error al acceder a la tabla autores");
            System.err.println(excepcion.getMessage());
        }
        return libros;
    }
    
    public static ArrayList<Libro> extraerLibros (Statement sentencia){
        ArrayList<Libro> libros = new ArrayList<>();
        try{               
            String sql = String.format("SELECT * FROM LIBROS");
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                libros.add(construyeLibro(resultado));
            }
        }catch(SQLException excepcion){
            System.out.println("Error al acceder a la tabla autores");
            System.err.println(excepcion.getMessage());
        }
        return libros;
    }
    
    public static ArrayList<Autor> extraerAutores (Statement sentencia){
        ArrayList<Autor> autores = new ArrayList<>();
        try{               
            String sql = String.format("SELECT * FROM autores");
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                autores.add(construyeAutor(resultado));
            }
        }catch(SQLException excepcion){
            System.out.println("Error al acceder a la tabla autores");
            System.err.println(excepcion.getMessage());
        }
        return autores;
    }
    
    public static ArrayList<Libro> librosDeAutor(Statement sentencia, String dni){
        ArrayList<Libro> libros = new ArrayList();
        try{
            String sql = String.format("SELECT * FROM LIBROS WHERE IDLIBRO IN (SELECT LIBRO FROM AUTORES_LIBROS WHERE AUTOR='%s')", dni);
            ResultSet resultado = sentencia.executeQuery(sql);
            while (resultado.next()){
                 libros.add(construyeLibro(resultado));
            }
            
        }catch(SQLException excepcion){
            System.out.println("Error al acceder a los datos");
            System.out.println(excepcion.getMessage());
        }
        return libros;
    }
}
