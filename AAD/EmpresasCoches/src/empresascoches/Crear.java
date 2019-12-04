
package empresascoches;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import objetos.CocheAlquiler;
import objetos.CocheVenta;
import objetos.Empresa;
import objetos.Uso;

/**
 *
 * @author a18luisdvp
 */
public class Crear {
    
    public static void tablas(Statement sentencia){
        try{
            
            sentencia.execute("CREATE DATABASE IF NOT EXISTS EmpresasCoches;");
            sentencia.execute("USE EmpresasCoches");
                       
            sentencia.execute("CREATE TABLE IF NOT EXISTS empresas("
                    + " cif         CHAR(9) NOT NULL, "
                    + " nombre      VARCHAR(15) NOT NULL,"
                    + " telefono    CHAR(9) NOT NULL,"
                    + " PRIMARY KEY(cif)"
                    + ")"
                    + "ENGINE INNODB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS coches("
                    + " codigo      CHAR(4) NOT NULL,"
                    + " marca       VARCHAR(10) NOT NULL,"
                    + " modelo      VARCHAR(10) NOT NULL,"
                    + " cif     CHAR(9) NOT NULL,"
                    + " PRIMARY KEY(codigo),"
                    + " CONSTRAINT FK1_EMPRESA"
                    + "     FOREIGN KEY (cif) REFERENCES EMPRESAS (cif)"
                    + "         ON DELETE CASCADE ON UPDATE CASCADE,"
                    + " INDEX FK1_EMPRESA(cif)"
                    + ")"
                    + "ENGINE INNODB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS cochesAlquiler("
                    + " codigo      CHAR(4) NOT NULL,"
                    + " precio      FLOAT NOT NULL,"
                    + " estado      CHAR(1) NOT NULL,"
                    + " PRIMARY KEY(codigo),"
                    + " CONSTRAINT FK1_COCHE"
                    + "     FOREIGN KEY (codigo) REFERENCES COCHES (codigo)"
                    + "         ON DELETE CASCADE ON UPDATE CASCADE,"
                    + " INDEX FK1_COCHE(codigo)"
                    + ")"
                    + "ENGINE INNODB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS cochesVenta("
                    + " codigo      CHAR(4) NOT NULL,"
                    + " precio      FLOAT NOT NULL,"
                    + " PRIMARY KEY(codigo),"
                    + " CONSTRAINT FK2_COCHE"
                    + "     FOREIGN KEY (codigo) REFERENCES COCHES (codigo)"
                    + "         ON DELETE CASCADE ON UPDATE CASCADE,"
                    + " INDEX FK2_COCHE(codigo)"
                    + ")"
                    + "ENGINE INNODB;");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS usos("
                    + " cocheAlquiler          CHAR(4) NOT NULL,"
                    + " fechaAlquiler    DATE NOT NULL,"
                    + " fechaEntrega    DATE NOT NULL,"
                    + " importe     FLOAT NOT NULL,"
                    + " PRIMARY KEY(fechaAlquiler, cocheAlquiler),"
                    + " CONSTRAINT FK3_COCHE"
                    + "     FOREIGN KEY (cocheAlquiler) REFERENCES cochesAlquiler (codigo)"
                    + "         ON DELETE CASCADE ON UPDATE CASCADE,"
                    + " INDEX FK3_COCHE(cocheAlquiler)"
                    + ")"
                    + "ENGINE INNODB;");
            System.out.println("Base de datos creada");
        }catch(SQLException excepcion){
            System.err.println("Error al crear las tablas");
            System.out.println(excepcion.getMessage());
        }
    }
    
    public static Empresa nuevaEmpresa(){      
        String cif = pedirCif();
        System.out.printf("Nombre: ");
        String nombre = Pedir.texto();
        System.out.printf("Teléfono: ");
        String telefono = Pedir.texto();
        return new Empresa(cif, nombre, telefono);
    }
    
    public static CocheVenta nuevoCocheVenta(Empresa empresa){
        String codigo = pedirCodigo();
        System.out.printf("Marca: ");
        String marca = Pedir.texto();
        System.out.printf("Modelo: ");
        String modelo = Pedir.texto();       
        float precio = pedirPrecioVenta();
        return new CocheVenta(codigo, marca, modelo, empresa, precio);
    }  
    
    public static CocheAlquiler nuevoCocheAlquiler(Empresa empresa){
        String codigo = pedirCodigo();
        System.out.printf("Marca: ");
        String marca = Pedir.texto();
        System.out.printf("Modelo: ");
        String modelo = Pedir.texto();      
        float precio = pedirPrecioDia();
        return new CocheAlquiler(codigo, marca, modelo, empresa, precio);
    } 
    
    public static Uso nuevoUso(CocheAlquiler cocheAlquiler){
        System.out.printf("Fecha de alquiler(dd/MM/yyyy): ");
        Date fechaAlquiler = Pedir.fecha();
        System.out.printf("Fecha de entrega(dd/MM/yyyy): ");
        Date fechaEntrega = Pedir.fecha();      
        return new Uso(fechaAlquiler, fechaEntrega, cocheAlquiler);
    }
    
    public static String pedirCif(){
        System.out.printf("Cif: ");
        return Pedir.texto();
    }
    
    public static String pedirCodigo(){
        System.out.printf("Código: ");
        return Pedir.texto();
    }
    
    public static float pedirPrecioVenta(){
        System.out.printf("Precio: ");
        return Pedir.numeroRealFloat();
    }
    
    public static float pedirPrecioDia(){
        System.out.printf("Precio por día: ");
        return Pedir.numeroRealFloat();
    }
}
