package metodos;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 
 */
public class Creacion {

    public static void crearTablas(Statement sentencia) {
        try {
            sentencia.execute("create database librosautoreshibernate;");
            sentencia.execute("use librosautoreshibernate;");
            sentencia.execute("create table autores("
                    + "dni char(9) not null,"
                    + "nombre varchar(30) not null,"
                    + "nacionalidad varchar(30) not null,"
                    + "primary key(dni));");            
            sentencia.execute("create table libros("
                    + "idlibro int(5) unsigned not null auto_increment, "
                    + "titulo varchar(30) not null, "
                    + "precio float unsigned not null,"
                    + "autor char(9) not null, "
                    + "primary key(idlibro),"
                    + "foreign key (autor) references autores (dni) on delete cascade on update cascade,"
                    + "index fk1_autores (autor));");            
            sentencia.execute("create table telefonos("
                    + "dni char(9) not null,"
                    + "telefono varchar(15) not null,"
                    + "primary key (dni),"
                    + "foreign key (dni) references autores(dni) on delete cascade on update cascade,"
                    + "unique index (telefono));");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}
