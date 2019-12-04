
package bdinstituto;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author luisd
 */
public class Modificar {
    
    public static void notaAlumno(Statement sentencia, int idAlumno, int idAsignatura, String fecha){
        System.out.println("--- Introduzca la nueva nota para el alumno ---");
        float nuevaCualificacion = Crear.pedirNota();       
        try{
            String sql = String.format("UPDATE NOTAS SET NOTA='%s' WHERE ALUMNO='%s' AND ASIGNATURA='%s' AND FECHA='%s'",nuevaCualificacion,idAlumno, idAsignatura, fecha);
            sentencia.executeUpdate(sql);
        }catch(SQLException excepcion){
            System.out.println("Error al modificar la tabla notas");
            System.out.println(excepcion.getMessage());
        }
    }
}
