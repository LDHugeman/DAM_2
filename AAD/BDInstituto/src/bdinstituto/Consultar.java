
package bdinstituto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import objetos.Alumno;
import objetos.Asignatura;
import objetos.Nota;
import objetos.Profesor;

/**
 *
 * @author luisd
 */
public class Consultar {
    
    public static int encontrarIdAlumnoPorCodigo(Statement sentencia, String codigoAlumno){
        int idAlumno = -1;
        try{
            String sql = String.format("SELECT IDALUMNO FROM ALUMNOS WHERE CODIGO='%s'", codigoAlumno);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                idAlumno = resultado.getInt("IDALUMNO");
            }
        }catch(SQLException excepcion){
            System.err.println("Error al acceder a la tabla alumnos");
            System.err.println(excepcion.getMessage());
        }
        return idAlumno;
    }
    
    public static int encontrarIdAsignaturaPorCodigo(Statement sentencia, String codigoAsignatura){
        int idAsignatura = -1;
        try{
            String sql = String.format("SELECT IDASIGNATURA FROM ASIGNATURAS WHERE CODIGO='%s'", codigoAsignatura);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                idAsignatura = resultado.getInt("IDASIGNATURA");
            }
        }catch(SQLException excepcion){
            System.err.println("Error al acceder a la tabla asignaturas");
            System.err.println(excepcion.getMessage());
        }
        return idAsignatura;
    }
    
    public static Profesor encontrarProfesorPorDni(Statement sentencia, String dni){
        Profesor profesorEncontrado = null;
        try{
            String sql = String.format("SELECT * FROM PROFESORES WHERE DNI='%s'", dni);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                profesorEncontrado = construyeProfesor(resultado);
            }
        }catch(SQLException excepcion){
            System.err.println("Error al acceder a la tabla profesores");
            System.err.println(excepcion.getMessage());
        }
        return profesorEncontrado;
    }
    
    private static Profesor construyeProfesor(ResultSet resultado)throws SQLException{
        String dni = resultado.getString("DNI");
        String nombre = resultado.getString("NOMBRE");
        String titulacion = resultado.getString("TITULACION");
        return new Profesor(dni, nombre, titulacion);
    }
    
    public static boolean existeProfesorPorDni(Statement sentencia, String dni){
        return encontrarProfesorPorDni(sentencia, dni)!=null;
    }
    
    public static Alumno encontrarAlumnoPorCodigo(Statement sentencia, String codigo){
        Alumno alumnoEncontrado = null;
        try{
            String sql = String.format("SELECT * FROM ALUMNOS WHERE CODIGO='%s'", codigo);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                alumnoEncontrado = construyeAlumno(resultado);
            }
        }catch(SQLException excepcion){
            System.err.println("Error al acceder a la tabla alumnos");
            System.err.println(excepcion.getMessage());
        }
        return alumnoEncontrado;
    }
    
    public static Alumno encontrarAlumnoPorID(Statement sentencia, int id){
        Alumno alumnoEncontrado = null;
        try{
            String sql = String.format("SELECT * FROM ALUMNOS WHERE IDALUMNO='%s'", id);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                alumnoEncontrado = construyeAlumno(resultado);
            }
        }catch(SQLException excepcion){
            System.err.println("Error al acceder a la tabla alumnos");
            System.err.println(excepcion.getMessage());
        }
        return alumnoEncontrado;
    }
    
    private static Alumno construyeAlumno(ResultSet resultado)throws SQLException{
        String codigo = resultado.getString("CODIGO");
        String nombre = resultado.getString("NOMBRE");
        return new Alumno(codigo, nombre);
    }
    
    public static boolean existeAlumnoPorCodigo(Statement sentencia, String codigo){
        return encontrarAlumnoPorCodigo(sentencia, codigo)!=null;
    }
    
    public static Asignatura encontrarAsignaturaPorCodigo(Statement sentencia, String codigo){
        Asignatura asignaturaEncontrada = null;
        try{
            String sql = String.format("SELECT * FROM ASIGNATURAS WHERE CODIGO='%s'", codigo);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                asignaturaEncontrada = construyeAsignatura(resultado);
            }
        }catch(SQLException excepcion){
            System.err.println("Error al acceder a la tabla alumnos");
            System.err.println(excepcion.getMessage());
        }
        return asignaturaEncontrada;
    }
    
    public static Asignatura encontrarAsignaturaPorID(Statement sentencia, int id){
        Asignatura asignaturaEncontrada = null;
        try{
            String sql = String.format("SELECT * FROM ASIGNATURAS WHERE IDASIGNATURA='%s'", id);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                asignaturaEncontrada = construyeAsignatura(resultado);
            }
        }catch(SQLException excepcion){
            System.err.println("Error al acceder a la tabla alumnos");
            System.err.println(excepcion.getMessage());
        }
        return asignaturaEncontrada;
    }
    
    private static Asignatura construyeAsignatura(ResultSet resultado)throws SQLException{
        String codigo = resultado.getString("CODIGO");
        String nombre = resultado.getString("NOMBRE");
        String ciclo = resultado.getString("CICLO");
        return new Asignatura(codigo, nombre, ciclo);
    }
    
    public static boolean existeAsignaturaPorCodigo(Statement sentencia, String codigo){
        return encontrarAsignaturaPorCodigo(sentencia, codigo)!=null;
    }
    
    public static Nota encontrarNotaPoridAlumnoEidAsignatura(Statement sentencia, int idAlumno, int idAsignatura){
        Nota notaEncontrada = null;
        try{
            String sql = String.format("SELECT * FROM NOTAS WHERE ALUMNO='%s' AND ASIGNATURA='%s'", idAlumno, idAsignatura);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                notaEncontrada = construyeNota(resultado);
            }
        }catch(SQLException excepcion){
            System.err.println("Error al acceder a la tabla alumnos");
            System.err.println(excepcion.getMessage());
        }
        return notaEncontrada;
    }
    
    private static Nota construyeNota(ResultSet resultado)throws SQLException{
        float cualificacion = resultado.getFloat("NOTA");
        Date fecha = resultado.getDate("FECHA");
        int idAlumno = resultado.getInt("ALUMNO");
        int idAsignatura = resultado.getInt("ASIGNATURA");
        return new Nota(cualificacion, fecha, idAlumno, idAsignatura);
    }
    
    public static ArrayList extraerAsignaturasProfesor(Statement sentencia, String dniProfesor){
        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        try{
            String sql = String.format("SELECT * FROM ASIGNATURAS WHERE PROFESOR='%s'", dniProfesor);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                asignaturas.add(construyeAsignatura(resultado));
            }
        }catch(SQLException excepcion){
            System.err.println("Error al acceder a la tabla asignaturas");
            System.err.println(excepcion.getMessage());
        }
        return asignaturas;
    }
    
    public static ArrayList extraerNotasAlumno(Statement sentencia, int idAlumno){
        ArrayList<Nota> notas = new ArrayList<>();
        try{
            String sql = String.format("SELECT * FROM NOTAS WHERE ALUMNO='%s'", idAlumno);
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()){
                notas.add(construyeNota(resultado));
            }
        }catch(SQLException excepcion){
            System.err.println("Error al acceder a la tabla notas");
            System.err.println(excepcion.getMessage());
        }
        return notas;
    }
}
