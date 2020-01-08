
package bdinstituto;

import java.sql.SQLException;
import java.sql.Statement;
import objetos.Alumno;
import objetos.Asignatura;
import objetos.Nota;
import objetos.Profesor;

/**
 *
 * @author luisd
 */
public class Altas {
    
    public static void nuevoProfesor(Statement sentencia, Profesor profesor){
        try{
            String sql = String.format("INSERT INTO PROFESORES (DNI, NOMBRE, TITULACION) VALUES ('%s','%s','%s')", 
                    profesor.getDni(), profesor.getNombre(), profesor.getTitulacion());
            sentencia.executeUpdate(sql);
        }catch(SQLException excepcion){
            System.err.println("Error al insertar el profesor");
            System.err.println(excepcion.getMessage());
        }
    }
    
    public static void nuevoAlumno(Statement sentencia, Alumno alumno){
        try{
            String sql = String.format("INSERT INTO ALUMNOS (CODIGO, NOMBRE) VALUES ('%s','%s')", alumno.getCodigo(), alumno.getNombre());
            sentencia.executeUpdate(sql);
        }catch(SQLException excepcion){
            System.err.println("Error al insertar el profesor");
            System.err.println(excepcion.getMessage());
        }
    }
    
    public static void nuevaAsignatura(Statement sentencia, Asignatura asignatura, String dniProfesor){
        try{
            String sql = String.format("INSERT INTO ASIGNATURAS (CODIGO, NOMBRE, CICLO, PROFESOR) VALUES ('%s','%s','%s','%s')",
                    asignatura.getCodigo(), asignatura.getNombre(), asignatura.getCiclo(), dniProfesor);
            sentencia.executeUpdate(sql);
        }catch(SQLException excepcion){
            System.err.println("Error al insertar la asignatura");
            System.err.println(excepcion.getMessage());
        }
    }
    
    public static void nuevaNota(Statement sentencia, Nota nota, int idAlumno, int idAsignatura){
        try{
            String sql = String.format("INSERT INTO NOTAS (ALUMNO , ASIGNATURA, NOTA, FECHA) VALUES ('%s','%s','%s','%s')", 
                    idAlumno, idAsignatura, nota.getCalificacion(), Crear.getStringFechaSql(nota.getFecha()));
            sentencia.executeUpdate(sql);
        }catch(SQLException excepcion){
            System.err.println("Error al insertar la nota");
            System.err.println(excepcion.getMessage());
        }
    }
}
