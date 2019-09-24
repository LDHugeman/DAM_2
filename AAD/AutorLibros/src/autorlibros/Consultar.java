
package autorlibros;

import Objetos.Autor;
import Objetos.Libro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
