
package autorlibros;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a18luisdvp
 */
public class Crear {
    
    public static void tablas (Statement sentencia){
        try{
            sentencia.execute("DROP DATABASE IF EXISTS LIBRERIA;");
            sentencia.execute("CREATE DATABASE IF NOT EXISTS LIBRERIA;");          
            sentencia.execute("USE LIBRERIA;");
            
            sentencia.execute("DROP TABLE IF EXISTS AUTORES;");
            sentencia.execute("CREATE TABLE IF NOT EXISTS AUTORES"
                    + "(dni CHAR(9) NOT NULL,"
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "nacionalidad VARCHAR(30) NOT NULL,"
                    + "PRIMARY KEY (dni))"
                    + "ENGINE INNOBB;");
            
            sentencia.execute("DROP TABLE IF EXISTS LIBROS;");
            sentencia.execute("CREATE TABLE IF NOT EXISTS LIBROS"
                    + "(idLibro INT(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "titulo VARCHAR(30) NOT NULL,"
                    + "precio float(6) UNSIGNED ZEROFILL NOT NULL,"
                    + "autor CHAR(9) NOT NULL,"
                    + "PRIMARY KEY (idLibro),"
                    + "UNIQUE INDEX AK_TITULO (titulo),"
                    + "FOREIGN KEY (autor) references AUTORES(dni) "
                    + "ON DELETE RESTRICT "
                    + "ON UPDATE CASCADE, "
                    + "UNIQUE INDEX FK_AUTOR(autor))"
                    + "ENGINE INNODB;");
            System.out.println("Base de datos creada");
        }catch(SQLException excepcion){
            System.out.println("Error al crear las tablas");
        }
    }
}
