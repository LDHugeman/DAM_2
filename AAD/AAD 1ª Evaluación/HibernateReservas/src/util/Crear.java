
package util;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author luisd
 */
public class Crear {
    
     public static void tablas(Statement sentencia) {
        try {
            sentencia.execute("CREATE TABLE IF NOT EXISTS hoteles("
                    + " idHotel INT(4) UNSIGNED ZEROFILL NOT NULL,"
                    + " nombre  VARCHAR(30) NOT NULL,"
                    + " telefono    CHAR(9) NOT NULL,"
                    + " PRIMARY KEY(idHotel)"
                    + ")"
                    + "ENGINE INNODB;");

            sentencia.execute("CREATE TABLE IF NOT EXISTS habitaciones("
                    + " idHabitacion    INT(4) UNSIGNED ZEROFILL NOT NULL,"
                    + " tipo    VARCHAR(10) NOT NULL,"
                    + " precio  FLOAT NOT NULL,"
                    + " ocupado BOOLEAN NOT NULL,"
                    + " hotel INT(4) UNSIGNED ZEROFILL NOT NULL,"
                    + " PRIMARY KEY (idHabitacion),"
                    + " CONSTRAINT FK1_HOTEL"
                    + "     FOREIGN KEY (hotel) REFERENCES hoteles (idHotel)"
                    + "         ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "INDEX FK1_HOTEL(hotel)"
                    + ")"
                    + "ENGINE INNODB;");

            sentencia.execute("CREATE TABLE IF NOT EXISTS clientes("
                    + " dni CHAR(9) NOT NULL,"
                    + " nombre VARCHAR(15) NOT NULL, "
                    + " telefono   CHAR(9) NOT NULL,"
                    + " PRIMARY KEY (dni)"
                    + ")"
                    + "ENGINE INNODB;");

            sentencia.execute("CREATE TABLE IF NOT EXISTS reservas("
                    + " hotel INT(4) UNSIGNED ZEROFILL NOT NULL,"
                    + " habitacion    INT(4) UNSIGNED ZEROFILL NOT NULL,"
                    + " fechaEntrega DATE NOT NULL,"
                    + " fechaSalida DATE NOT NULL,"
                    + " cliente CHAR(9) NOT NULL,"
                    + " PRIMARY KEY(fechaEntrega, habitacion),"
                    + ""
                    + " CONSTRAINT FK2_HOTEL"
                    + "     FOREIGN KEY (hotel) REFERENCES hoteles (idHotel)"
                    + "         ON DELETE CASCADE ON UPDATE CASCADE,"
                    + " CONSTRAINT FK1_HABITACION"
                    + "     FOREIGN KEY (habitacion) REFERENCES habitaciones (idHabitacion)"
                    + "         ON DELETE CASCADE ON UPDATE CASCADE,"
                    + " CONSTRAINT FK1_CLIENTE"
                    + "     FOREIGN KEY (cliente) REFERENCES clientes (dni)"
                    + "         ON DELETE CASCADE ON UPDATE CASCADE,"
                    + " INDEX FK2_HOTEL(hotel),"
                    + " INDEX FK1_HABITACION (habitacion),"
                    + " INDEX FK1_CLIENTE (cliente)"
                    + ")"
                    + "ENGINE INNODB;");
            System.out.println("Base de datos creada");
        } catch (SQLException excepcion) {
            System.err.println("Error al crear las tablas");
            System.out.println(excepcion.getMessage());
        }
    }
}
