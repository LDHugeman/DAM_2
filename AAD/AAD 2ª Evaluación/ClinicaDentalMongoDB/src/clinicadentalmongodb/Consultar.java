
package clinicadentalmongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

/**
 *
 * @author Alberto y David
 */
public class Consultar {
    
    public static Document encontrarConsultaPorNumero(int numeroConsulta){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccion = baseDatos.getCollection("consultas");
        return coleccion.find(eq("idConsulta", numeroConsulta)).first();
    }
    
    public static boolean existeConsultaPorNumero(int numeroConsulta){
        return encontrarConsultaPorNumero(numeroConsulta)!=null;
    }
    
    public static Document encontrarDentistaPorDni(String dniDentista){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccion = baseDatos.getCollection("dentistas");
        return coleccion.find(eq("dniDentista", dniDentista)).first();
    }
    
    public static boolean existeDentistaPorDni(String dniDentista){
        return encontrarDentistaPorDni(dniDentista)!=null;
    }
    
    public static Document encontrarHistorialPorNumero(int numeroHistorial){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccion = baseDatos.getCollection("historiales");
        return coleccion.find(eq("idHistorial", numeroHistorial)).first();
    }
    
    public static boolean existeHistorialPorNumero(int numeroHistorial){
        return encontrarHistorialPorNumero(numeroHistorial)!=null;
    }
    
    public static Document encontrarPacientePorDni(String dniPaciente){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccion = baseDatos.getCollection("pacientes");
        return coleccion.find(eq("dniPaciente", dniPaciente)).first();
    }
    
    public static boolean existePacientePorDni(String dniPaciente){
        return encontrarPacientePorDni(dniPaciente)!=null;
    }
    
    public static Document encontrarCitaPorNumero(int numeroCita){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccion = baseDatos.getCollection("citas");
        return coleccion.find(eq("idCita", numeroCita)).first();
    }
    
    public static boolean existeCitaPorNumero(int numeroCita){
        return encontrarCitaPorNumero(numeroCita)!=null;
    }
}
