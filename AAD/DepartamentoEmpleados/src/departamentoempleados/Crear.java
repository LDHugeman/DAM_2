package departamentoempleados;

import java.sql.SQLException;
import java.sql.Statement;
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
            sentencia.execute("DROP DATABASE IF EXISTS EMPRESA;");
            sentencia.execute("CREATE DATABASE IF NOT EXISTS EMPRESA;");
            sentencia.execute("USE EMPRESA");

            sentencia.execute("DROP TABLE IF EXISTS departamentos");
            sentencia.execute("CREATE TABLE IF NOT EXISTS departamentos"
                    + "(idDepartamento INT(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "localidad VARCHAR(30) NOT NULL,"
                    + "PRIMARY KEY(idDepartamento))"
                    + "ENGINE INNODB;");
            
            sentencia.execute("DROP TABLE IF EXISTS empleados");
            sentencia.execute("CREATE TABLE IF NOT EXISTS empleados"
                    + "(numeroSegSocial CHAR(12) NOT NULL,"
                    + "nombre VARCHAR(30) NOT NULL,"
                    + "oficio VARCHAR(30) NOT NULL,"
                    + "direccion VARCHAR(30) NOT NULL,"
                    + "fechaAlta DATE NOT NULL,"
                    + "salario FLOAT(5) UNSIGNED NOT NULL,"
                    + "comision FLOAT(5) UNSIGNED NOT NULL,"
                    + "numeroDepartamento INT(5) UNSIGNED ZEROFILL NOT NULL,"
                    + "FOREIGN KEY(numeroDepartamento) REFERENCES DEPARTAMENTOS (idDepartamento) "
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
    
    public static Departamento nuevoDepartamento (){
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Localidad: ");
        String localidad = Pedir.texto();
        return new Departamento(nombre, localidad);
    }
    
    public static Empleado nuevoEmpleado(){
        System.out.printf("Número de Seguridad Social: ");
        String numeroSegSocial = Pedir.texto();
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Oficio: ");
        String oficio = Pedir.texto();
        System.out.printf("Dirección: ");
        String direccion = Pedir.texto();
        System.out.printf("Salario: ");
        Date fechaAlta = new Date();
        float salario = Pedir.numeroRealFloat();
        System.out.printf("Comisión: ");
        float comision = Pedir.numeroRealFloat();
        Visualizar.departamentos(Consultar.extraerDepartamentos());
        System.out.println("--- Introduzca el número del departamento en el que desea añadir el empleado ---");       
        System.out.printf("Número del departamento: ");
        int numeroDepartamento = Pedir.numeroEntero();
        return new Empleado(numeroSegSocial, nombre, oficio, direccion, fechaAlta, salario, comision, numeroDepartamento);
    }
}
