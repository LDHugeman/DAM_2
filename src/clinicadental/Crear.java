
package clinicadental;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a18luisdvp
 */
public class Crear {
    
    public static void tablas(Statement sentencia) {
        try {
            sentencia.execute("DROP DATABASE IF EXISTS ClinicaDental;");
            sentencia.execute("CREATE DATABASE IF NOT EXISTS ClinicaDental;");
            sentencia.execute("USE ClinicaDental");
                       
            sentencia.execute("CREATE TABLE IF NOT EXISTS empleados"
                    + "(dni CHAR(9) NOT NULL,"
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "telefono CHAR(9) NOT NULL,"
                    + "sueldo FLOAT(5) UNSIGNED NOT NULL,"
                    + "UNIQUE INDEX AK_TELEFONO(telefono),"
                    + "PRIMARY KEY(dni))"
                    + "ENGINE INNODB;");                    
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS limpiadores"
                    + "(dni CHAR(9) NOT NULL,"
                    + "PRIMARY KEY(dni),"
                    + "FOREIGN KEY(dni) REFERENCES empleados(dni)"
                    + " ON DELETE CASCADE"
                    + " ON UPDATE CASCADE)"
                    + "ENGINE INNODB;");                     
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS consultas"
                    + "(numero INT(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "quirofano BOOLEAN NOT NULL,"
                    + "piso INT(2) UNSIGNED NOT NULL,"
                    + "PRIMARY KEY(numero))"
                    + "ENGINE INNODB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS dentistas"
                    + "(dni CHAR(9) NOT NULL,"
                    + "consulta INT(5) UNSIGNED ZEROFILL NOT NULL,"       
                    + "FOREIGN KEY(dni) REFERENCES empleados(dni)"
                    + " ON DELETE CASCADE"
                    + " ON UPDATE CASCADE,"
                    + "FOREIGN KEY(consulta) REFERENCES consultas(numero)"
                    + " ON DELETE RESTRICT"
                    + " ON UPDATE CASCADE,"
                    + "PRIMARY KEY(dni))"
                    + "ENGINE INNODB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS historiales"
                    + "(codigo INT(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "seguroPrivado BOOLEAN NOT NULL,"
                    + "grupoSanguineo VARCHAR(30) NOT NULL,"
                    + "PRIMARY KEY(codigo))"
                    + "ENGINE INNODB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS pacientes"
                    + "(dni CHAR(9) NOT NULL,"
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "telefono CHAR(9) NOT NULL,"
                    + "dentista CHAR(9) NOT NULL,"
                    + "historial INT(5) UNSIGNED ZEROFILL NOT NULL,"                   
                    + "FOREIGN KEY(dentista) REFERENCES dentistas(dni)"
                    + " ON DELETE RESTRICT"
                    + " ON UPDATE CASCADE,"
                    + "FOREIGN KEY(historial) REFERENCES historiales(codigo)"
                    + " ON DELETE RESTRICT"
                    + " ON UPDATE CASCADE,"
                    + "PRIMARY KEY(dni))"
                    + "ENGINE INNODB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS citas"
                    + "(fecha DATE NOT NULL,"
                    + "hora TIME NOT NULL,"
                    + "tipoTrabajo VARCHAR(30) NOT NULL,"
                    + "historial INT(5) UNSIGNED ZEROFILL NOT NULL,"
                    + "FOREIGN KEY (historial) REFERENCES historiales(codigo)"
                    + " ON DELETE CASCADE"
                    + " ON UPDATE CASCADE,"
                    + "PRIMARY KEY(fecha, hora, historial))"
                    + "ENGINE INNODB;");
                   
            sentencia.execute("CREATE TABLE IF NOT EXISTS limpiadores_consultas"
                    + "(limpiador CHAR(9) NOT NULL,"
                    + "consulta INT(5) UNSIGNED ZEROFILL NOT NULL,"
                    + "FOREIGN KEY (limpiador) REFERENCES limpiadores(dni)"
                    + " ON DELETE CASCADE"
                    + " ON UPDATE CASCADE,"
                    + "FOREIGN KEY(consulta) REFERENCES consultas(numero)"
                    + " ON DELETE CASCADE"
                    + " ON UPDATE CASCADE,"
                    + "PRIMARY KEY(limpiador, consulta))"
                    + "ENGINE INNODB;");          
            
            System.out.println("Base de datos creada");
        } catch (SQLException excepcion) {
            System.err.println("Error al crear las tablas");
            System.out.println(excepcion.getMessage());
        }
    }
}
