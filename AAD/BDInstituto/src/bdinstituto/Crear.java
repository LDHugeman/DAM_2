
package bdinstituto;

import excepciones.Validar;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import objetos.Alumno;
import objetos.Asignatura;
import objetos.Nota;
import objetos.Profesor;

/**
 *
 * @author a18luisdvp
 */
public class Crear {
    
    public static void tablas(Statement sentencia){
        try{
            sentencia.execute("CREATE DATABASE IF NOT EXISTS INSTITUTO;");
            sentencia.execute("USE INSTITUTO;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS PROFESORES"
                    + "(DNI CHAR(9) NOT NULL,"
                    + "NOMBRE VARCHAR(30) NOT NULL,"
                    + "TITULACION VARCHAR(30) NOT NULL,"
                    + "PRIMARY KEY (DNI))"
                    + "ENGINE INNODB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS ASIGNATURAS"
                    + "(IDASIGNATURA INT(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "CODIGO CHAR(4) NOT NULL,"
                    + "NOMBRE VARCHAR(30) NOT NULL,"
                    + "CICLO VARCHAR(30) NOT NULL,"
                    + "PROFESOR CHAR(9) NOT NULL,"
                    + "UNIQUE INDEX AK_CODIGOASIG(CODIGO),"
                    + "FOREIGN KEY(PROFESOR) REFERENCES PROFESORES(DNI) "
                    + "ON DELETE CASCADE "
                    + "ON UPDATE CASCADE,"
                    + "PRIMARY KEY(IDASIGNATURA))" 
                    + "ENGINE INNODB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS ALUMNOS"
                    + "(IDALUMNO INT(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "CODIGO CHAR(4) NOT NULL,"
                    + "NOMBRE VARCHAR(30) NOT NULL,"
                    + "UNIQUE INDEX AK_CODIGOALUM(CODIGO),"
                    + "PRIMARY KEY(IDALUMNO))"
                    + "ENGINE INNODB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS NOTAS"
                    + "(ALUMNO INT(5) UNSIGNED ZEROFILL NOT NULL,"
                    + "ASIGNATURA INT(5) UNSIGNED ZEROFILL NOT NULL,"
                    + "NOTA FLOAT(6) UNSIGNED NOT NULL,"
                    + "FECHA DATE NOT NULL,"
                    + "FOREIGN KEY (ALUMNO) REFERENCES ALUMNOS(IDALUMNO) "
                    + "ON DELETE CASCADE "
                    + "ON UPDATE CASCADE,"
                    + "FOREIGN KEY(ASIGNATURA) REFERENCES ASIGNATURAS(IDASIGNATURA)"
                    + "ON DELETE CASCADE "
                    + "ON UPDATE CASCADE,"
                    + "PRIMARY KEY(ALUMNO, ASIGNATURA, FECHA))"
                    + "ENGINE INNODB;");
            
            /*sentencia.execute("DROP TABLE IF EXISTS PROFESORES_ALUMNOS;");
            sentencia.execute("CREATE TABLE IF NOT EXISTS PROFESORES_ALUMNOS"
                    + "(PROFESOR CHAR(9) NOT NULL,"
                    + "ALUMNO INT(5) UNSIGNED ZEROFILL NOT NULL,"
                    + "PRIMARY KEY(PROFESOR, ALUMNO),"
                    + "FOREIGN KEY (PROFESOR) REFERENCES PROFESORES(DNI) "
                    + "ON DELETE CASCADE "
                    + "ON UPDATE CASCADE,"
                    + "FOREIGN KEY (ALUMNO) REFERENCES ALUMNOS(IDALUMNO) "
                    + "ON DELETE CASCADE "
                    + "ON UPDATE CASCADE)"
                    + "ENGINE INNODB;");*/
            System.out.println("Base de datos creada");
        }catch(SQLException excepcion){
            System.err.println("Error al crear las tablas");
            System.out.println(excepcion.getMessage());
        }
    }
    
    public static Profesor nuevoProfesor(Statement sentencia){
        String dni = pedirDniValido(sentencia);
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Titulación: ");
        String titulacion = Pedir.texto();
        return new Profesor(dni, nombre, titulacion);
    }
    
    public static Alumno nuevoAlumno(){
        String codigo = pedirCodigoValido();
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        return new Alumno(codigo, nombre);
    }
    
    public static Asignatura nuevaAsignatura(){
        String codigo = pedirCodigoValido();
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Ciclo: ");
        String ciclo = Pedir.texto();
        return new Asignatura(codigo, nombre, ciclo);
    }
    
    public static Nota nuevaNota(){
        float cualificacion = pedirNota();
        Date fecha = new Date();
        return new Nota(cualificacion, fecha);
    }
    
    public static float pedirNota(){
        System.out.printf("Calificación: ");
        return Pedir.numeroRealFloat();
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
    
    public static String pedirCodigoValido(){
        String codigo = "";
        do{
            System.out.printf("Código: ");
            codigo = Pedir.texto();
        }while(!Validar.esCodigoValido(codigo));
        return codigo;      
    }
   
    public static String pedirCodigoAlumnoExistente(Statement sentencia){
        String codigo = "";
        do{
            System.out.printf("Código: ");
            codigo = Pedir.texto();
        }while(!Validar.esCodigoAlumnoExistenteValido(sentencia, codigo));
        return codigo;      
    }
 
    public static String pedirCodigoAsignaturaExistente(Statement sentencia){
        String codigo = "";
        do{
            System.out.printf("Código: ");
            codigo = Pedir.texto();
        }while(!Validar.esCodigoAsignaturaExistenteValido(sentencia, codigo));    
        return codigo;
    }      
    
    public static String getStringFechaSql(Date fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
        return formatoFecha.format(fecha);
    }
    
    public static String getStringFechaVisualizar(Date fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha);
    }
}
