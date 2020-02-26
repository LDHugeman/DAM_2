package clinicadentalneodatiscliente;

import excepciones.Validar;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import objetos.Cita;
import objetos.Consulta;
import objetos.Dentista;
import objetos.Historial;
import objetos.Limpiador;
import objetos.Paciente;
import util.Pedir;

/**
 *
 * @author David y Alberto
 */
public class Crear {

    public static void tablas(Statement sentencia) {
        try {
            sentencia.execute("CREATE DATABASE IF NOT EXISTS ClinicaDental;");
            sentencia.execute("USE ClinicaDental");

            sentencia.execute("CREATE TABLE IF NOT EXISTS empleados"
                    + "(dni CHAR(9) NOT NULL,"
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "telefono CHAR(9) NOT NULL,"
                    + "sueldo FLOAT(5) UNSIGNED NOT NULL,"
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
                    + "UNIQUE INDEX FK_CONSULTA(consulta),"
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
                    + "UNIQUE INDEX FK_HISTORIAL(historial),"
                    + "FOREIGN KEY(historial) REFERENCES historiales(codigo)"
                    + " ON DELETE CASCADE"
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

    public static Dentista nuevoDentista(Consulta consulta) {
        String dni = pedirDni("Dni: ");
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Teléfono: ");
        String telefono = Pedir.texto();
        System.out.printf("Sueldo: ");
        float sueldo = Pedir.numeroRealFloat();
        return new Dentista(dni, nombre, telefono, sueldo, consulta);
    }

    public static Limpiador nuevoLimpiador() {
        String dni = pedirDni("Dni: ");
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Teléfono: ");
        String telefono = Pedir.texto();
        System.out.printf("Sueldo: ");
        float sueldo = Pedir.numeroRealFloat();
        return new Limpiador(dni, nombre, telefono, sueldo);
    }

    public static Paciente nuevoPaciente(Historial historial, Dentista dentista) {
        String dni = pedirDni("Dni: ");
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Teléfono: ");
        String telefono = Pedir.texto();
        return new Paciente(dni, nombre, telefono, historial, dentista);
    }

    public static Consulta nuevaConsulta() {
        boolean quirofano = Pedir.duda("--- Indique si tiene o no quirófano la consulta ---");
        System.out.printf("Piso: ");
        int piso = Pedir.numeroEntero();
        return new Consulta(quirofano, piso);
    }

    public static Cita nuevaCita(Historial historial) {
        System.out.printf("Fecha(dd/MM/yyyy): ");
        Date fecha = Pedir.fecha();
        System.out.printf("Hora(hh:mm): ");
        Date hora = Pedir.hora();
        System.out.printf("Concepto de la cita: ");
        String tipoTrabajo = Pedir.texto();
        return new Cita(fecha, hora, tipoTrabajo, historial);
    }

    public static Historial nuevoHistorial() {
        boolean seguroPrivado = Pedir.duda("--- Indique si tiene o no seguro privado el paciente ---");
        System.out.printf("Grupo sanguíneo: ");
        String grupoSanguineo = Pedir.texto();
        return new Historial(seguroPrivado, grupoSanguineo);
    }

    public static String pedirDni(String mensaje) {
        String dni = "";
        do {
            System.out.printf(mensaje);
            dni = Pedir.texto();
        } while (!Validar.esDniValido(dni));
        return dni;
    }
}
