
package autorlibros;

import Objetos.Autor;
import Objetos.Libro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author luisd
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
    
    public static ArrayList<Libro> encontrarLibrosPorNombreAutor (Statement sentencia, String nombre){
        ArrayList<Libro> libros = new ArrayList<>();
        try{               
            String sql = String.format("SELECT * FROM LIBROS WHERE autor=(SELECT dni FROM AUTORES WHERE nombre='%s')", nombre);
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
    
    private static Autor construyeAutor(ResultSet resultado) throws SQLException{
        String dni = resultado.getString("dni");
        String nombre = resultado.getString("nombre");
        String nacionalidad = resultado.getString("nacionalidad");
        return new Autor(dni, nombre, nacionalidad);
    }
    
    public static boolean existeAutorPorDni (Statement sentencia, String dni){
        return encontrarAutorPorDni(sentencia, dni)!=null;
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
        String titulo = resultado.getString("titulo");
        float precio = resultado.getFloat("precio");
        return new Libro(titulo, precio);
    }
    
    public static String encontrarNombreAutorPorTituloLibro(Statement sentencia, String titulo){
        String nombreEncontrado = null;
        try{
            String sql = String.format("SELECT nombre FROM AUTORES WHERE dni=(SELECT autor FROM LIBROS WHERE titulo='%s')", titulo);
            ResultSet resultado = sentencia.executeQuery(sql);
            while (resultado.next()){
                nombreEncontrado = resultado.getString("nombre");
            }
            
        }catch(SQLException excepcion){
            System.out.println("Error al acceder a los datos");
            System.out.println(excepcion.getMessage());
        }
        return nombreEncontrado;
    }
    
    public static ArrayList<Libro> librosDeAutor(Statement sentencia, String dni){
        ArrayList<Libro> libros = new ArrayList();
        try{
            String sql = String.format("SELECT * FROM libros WHERE autor='%s'", dni);
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
