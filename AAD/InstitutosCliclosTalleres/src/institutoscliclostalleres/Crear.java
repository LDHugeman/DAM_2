package institutoscliclostalleres;

import java.sql.SQLException;
import java.sql.Statement;
import objetos.Ciclo;
import objetos.Instituto;
import objetos.Taller;
import objetos.Uso;
import org.hibernate.Session;

/**
 *
 * @author a18luisdvp
 */
public class Crear {

    public static void tablas(Statement sentencia) {
        try {
            //sentencia.execute("DROP DATABASE IF EXISTS BDXunta;");
            sentencia.execute("CREATE DATABASE IF NOT EXISTS BDXunta;");
            sentencia.execute("USE BDXunta;");
            sentencia.execute("CREATE TABLE IF NOT EXISTS ciclos ("
                    + "codigo INT(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "PRIMARY KEY (codigo));");

            sentencia.execute("CREATE TABLE IF NOT EXISTS institutos ("
                    + "codigo INT(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "telefono CHAR(9) NOT NULL,"
                    + "PRIMARY KEY (codigo));");

            sentencia.execute("CREATE TABLE IF NOT EXISTS talleres ("
                    + "codigo INT(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "nombre VARCHAR(10) NOT NULL,"
                    + "instituto INT(4) UNSIGNED ZEROFILL NOT NULL,"
                    + "PRIMARY KEY (codigo),"
                    + "FOREIGN KEY (instituto) REFERENCES institutos(codigo)"
                    + "     ON DELETE CASCADE"
                    + "     ON UPDATE CASCADE);");

            sentencia.execute("CREATE TABLE IF NOT EXISTS ciclos_institutos ("
                    + "ciclo INT(4) UNSIGNED ZEROFILL NOT NULL,"
                    + "instituto INT(4) UNSIGNED ZEROFILL NOT NULL,"
                    + "PRIMARY KEY (ciclo,instituto),"
                    + "FOREIGN KEY (ciclo) REFERENCES ciclos(codigo)"
                    + "     ON DELETE CASCADE"
                    + "     ON UPDATE CASCADE,"
                    + "FOREIGN KEY (INSTITUTO) REFERENCES institutos(codigo)"
                    + "     ON DELETE CASCADE"
                    + "     ON UPDATE CASCADE);");

            sentencia.execute("CREATE TABLE IF NOT EXISTS usos ("
                    + "ciclo INT(4) UNSIGNED ZEROFILL NOT NULL,"
                    + "taller INT(4) UNSIGNED ZEROFILL NOT NULL,"
                    + "fecha DATE NOT NULL,"
                    + "hora TIME NOT NULL,"
                    + "PRIMARY KEY (ciclo,taller,fecha,hora),"
                    + "FOREIGN KEY (ciclo) REFERENCES ciclos(codigo)"
                    + "     ON DELETE CASCADE"
                    + "     ON UPDATE CASCADE,"
                    + "FOREIGN KEY (taller) REFERENCES talleres(codigo)"
                    + "     ON DELETE CASCADE"
                    + "     ON UPDATE CASCADE);");

            System.out.println("Base de datos creada");
        } catch (SQLException excepcion) {
            System.err.println("Error al crear las tablas");
            System.out.println(excepcion.getMessage());
        }
    }

    public static Instituto nuevoInstituto() {
        int codigo = pedirCodigo();
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Teléfono: ");
        String telefono = Pedir.texto();
        return new Instituto(codigo, nombre, telefono);
    }

    public static Ciclo nuevoCiclo() {
        int codigo = pedirCodigo();
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        return new Ciclo(codigo, nombre);
    }

    public static Taller nuevoTaller(Instituto instituto) {
        System.out.printf("Código del taller: ");
        int codigo = Pedir.numeroEntero();
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        return new Taller(codigo, nombre, instituto);
    }

    public static Uso nuevoUso() {
        Uso uso = null;
        System.out.printf("Código del ciclo: ");
        int codigoCiclo = Pedir.numeroEntero();
        Session session = NewHibernateUtil.getSession();
        Ciclo ciclo = Consultar.encontrarCicloPorCodigo(session, codigoCiclo);
        if (ciclo != null) {
            System.out.printf("Código del taller: ");
            int codigoTaller = Pedir.numeroEntero();
            Taller taller = Consultar.encontrarTallerPorCodigo(session, codigoTaller);
            if (taller != null) {               
                uso = new Uso(ciclo, taller);
                ciclo.getUsos().add(uso);
                taller.getUsos().add(uso);
                session.close();
            } else {
                System.err.println("No existe un taller con ese código.");
            }
        } else {
            System.err.println("No existe un ciclo con ese código.");
        }
        return uso;
    }
    
    public static int pedirCodigo(){
        System.out.printf("Código: ");
        return Pedir.numeroEntero();
    }
}
