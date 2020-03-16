package clinicadentalmongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

/**
 *
 * @author Alberto y David
 */
public class Bajas {
    
    public static void eliminarConsulta(int numeroConsulta) {
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionConsultas = baseDatos.getCollection("consultas");
        coleccionConsultas.deleteOne(eq("idConsulta", numeroConsulta));      
    }
    
    public static void eliminarDentista(String dniDentista){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionDentistas = baseDatos.getCollection("dentistas");
        coleccionDentistas.deleteOne(eq("dniDentista", dniDentista));
    }
    
    public static void eliminarHistorial(int numeroHistorial){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionHistoriales = baseDatos.getCollection("historiales");
        coleccionHistoriales.deleteOne(eq("idHistorial", numeroHistorial));
    }
    
    public static void eliminarPaciente(String dniPaciente){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionPacientes = baseDatos.getCollection("pacientes");
        coleccionPacientes.deleteOne(eq("dniPaciente", dniPaciente));
    }
    
    public static void eliminarCita(int numeroCita){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionCitas = baseDatos.getCollection("citas");
        coleccionCitas.deleteOne(eq("idCita", numeroCita));
    }
}
