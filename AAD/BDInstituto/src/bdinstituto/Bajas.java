package bdinstituto;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a18luisdvp
 */
public class Bajas {

    public static void profesor(Statement sentencia, String dni) {
        String sql = String.format("DELETE FROM PROFESORES WHERE DNI='%s'", dni);
        try {
            sentencia.executeUpdate(sql);
            System.out.println("El profesor con dni " + dni + " ha sido eliminado");
        } catch (SQLException excepcion) {
            System.out.println("Error al eliminar el profesor");
            System.out.println(excepcion.getMessage());
        }
    }

    public static void alumno(Statement sentencia, String codigo) {
        String sql = String.format("DELETE FROM ALUMNOS WHERE CODIGO='%s'", codigo);
        try {
            sentencia.executeUpdate(sql);
            System.out.println("El alumno con codigo " + codigo + " ha sido eliminado");
        } catch (SQLException excepcion) {
            System.out.println("Error al eliminar el alumno");
            System.out.println(excepcion.getMessage());
        }
    }

}
