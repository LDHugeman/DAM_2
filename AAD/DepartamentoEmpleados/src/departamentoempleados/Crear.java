package departamentoempleados;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import objetos.Departamento;
import objetos.Empleado;

/**
 *
 * @author a18luisdvp
 */
public class Crear {

    public static void tablas(Statement sentencia) {
        try {
            //sentencia.execute("DROP DATABASE IF EXISTS EMPRESA;");
            sentencia.execute("CREATE DATABASE IF NOT EXISTS EMPRESA;");
            sentencia.execute("USE EMPRESA");

            //sentencia.execute("DROP TABLE IF EXISTS departamentos");
            sentencia.execute("CREATE TABLE IF NOT EXISTS departamentos"
                    + "(idDepartamento INT(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "localidad VARCHAR(30) NOT NULL,"
                    + "PRIMARY KEY(idDepartamento))"
                    + "ENGINE INNODB;");

            //sentencia.execute("DROP TABLE IF EXISTS empleados");
            sentencia.execute("CREATE TABLE IF NOT EXISTS empleados"
                    + "(numeroSegSocial CHAR(12) NOT NULL,"
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "oficio VARCHAR(30) NOT NULL,"
                    + "direccion VARCHAR(30) NOT NULL,"
                    + "fechaAlta DATE NOT NULL,"
                    + "salario FLOAT(5) UNSIGNED NOT NULL,"
                    + "comision FLOAT(5) UNSIGNED NOT NULL,"
                    + "departamento INT(5) UNSIGNED ZEROFILL NOT NULL,"
                    + "FOREIGN KEY(departamento) REFERENCES DEPARTAMENTOS (idDepartamento) "
                    + "ON DELETE CASCADE "
                    + "ON UPDATE CASCADE,"
                    + "PRIMARY KEY(numeroSegSocial))"
                    + "ENGINE INNODB;");

            System.out.println("Base de datos creada");
        } catch (SQLException excepcion) {
            System.err.println("Error al crear las tablas");
            System.out.println(excepcion.getMessage());
        }
    }

    public static Departamento nuevoDepartamento() {
        String nombre = pedirNombreDepartamento();
        String localidad = pedirLocalidad();
        return new Departamento(nombre, localidad);
    }

    public static Empleado nuevoEmpleado() {
        String numeroSegSocial = pedirNumeroSegSocial();
        String nombre = pedirNombreEmpleado();
        System.out.printf("Oficio: ");
        String oficio = Pedir.texto();
        System.out.printf("Dirección: ");
        String direccion = Pedir.texto();
        Date fechaAlta = new Date();
        float salario = pedirSalario();      
        float comision = pedirComision();
        return new Empleado(numeroSegSocial, nombre, oficio, direccion, fechaAlta, salario, comision);
    }

    public static int pedirIdDepartamento() {
        System.out.printf("Número de departamento: ");
        return Pedir.numeroEntero();
    }
    
    public static String pedirLocalidad(){
        System.out.printf("Localidad: ");
        return Pedir.texto();
    }
    
    public static String pedirNombreDepartamento(){
        System.out.printf("Nombre: ");
        return Pedir.texto();
    }
    
    public static String pedirNombreEmpleado(){
        System.out.printf("Nombre: ");
        return Pedir.texto();
    }

    public static String pedirNumeroSegSocial() {
        System.out.printf("Número de Seguridad Social: ");
        return Pedir.texto();
    }
    
    public static float pedirSalario() {
        System.out.printf("Salario: ");
        return Pedir.numeroRealFloat();
    }
    
    public static float pedirComision() {
        System.out.printf("Comisión: ");
        return Pedir.numeroRealFloat();
    }

    public static String getStringFechaVisualizar(Date fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha);
    }
}
