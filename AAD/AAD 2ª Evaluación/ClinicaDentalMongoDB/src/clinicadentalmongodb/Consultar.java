
package clinicadentalmongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Alberto y David
 */
public class Consultar {
    
    public static List<Document> extraerConsultas(){
        List<Document> consultas = new ArrayList<>();
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionConsultas = baseDatos.getCollection("consultas");
        return coleccionConsultas.find().into(consultas);
    }
    
    public static Document encontrarConsultaPorNumero(int numeroConsulta){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionConsultas = baseDatos.getCollection("consultas");
        return coleccionConsultas.find(eq("idConsulta", numeroConsulta)).first();
    }
    
    public static boolean existeConsultaPorNumero(int numeroConsulta){
        return encontrarConsultaPorNumero(numeroConsulta)!=null;
    }
    
    public static List<Document> extraerDentistas(){
        List<Document> dentistas = new ArrayList<>();
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionDentistas = baseDatos.getCollection("dentistas");
        return coleccionDentistas.find().into(dentistas);
    }
    
    public static Document encontrarDentistaPorDni(String dniDentista){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionDentistas = baseDatos.getCollection("dentistas");
        return coleccionDentistas.find(eq("dniDentista", dniDentista)).first();
    }
    
    public static boolean existeDentistaPorDni(String dniDentista){
        return encontrarDentistaPorDni(dniDentista)!=null;
    }
    
    public static List<Document> extraerHistoriales(){
        List<Document> historiales = new ArrayList<>();
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionHistoriales = baseDatos.getCollection("historiales");
        return coleccionHistoriales.find().into(historiales);
    }
    
    public static Document encontrarHistorialPorNumero(int numeroHistorial){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionHistoriales = baseDatos.getCollection("historiales");
        return coleccionHistoriales.find(eq("idHistorial", numeroHistorial)).first();
    }
    
    public static boolean existeHistorialPorNumero(int numeroHistorial){
        return encontrarHistorialPorNumero(numeroHistorial)!=null;
    }
    
    public static List<Document> extraerPacientes(){
        List<Document> pacientes = new ArrayList<>();
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionPacientes = baseDatos.getCollection("pacientes");
        return coleccionPacientes.find().into(pacientes);
    }
    
    public static Document encontrarPacientePorDni(String dniPaciente){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionPacientes = baseDatos.getCollection("pacientes");
        return coleccionPacientes.find(eq("dniPaciente", dniPaciente)).first();
    }
    
    public static boolean existePacientePorDni(String dniPaciente){
        return encontrarPacientePorDni(dniPaciente)!=null;
    }
    
    public static List<Document> extraerCitas(){
        List<Document> citas = new ArrayList<>();
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionCitas = baseDatos.getCollection("citas");
        return coleccionCitas.find().into(citas);
    }
    
    public static Document encontrarCitaPorNumero(int numeroCita){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionCitas = baseDatos.getCollection("citas");
        return coleccionCitas.find(eq("idCita", numeroCita)).first();
    }
    
    public static boolean existeCitaPorNumero(int numeroCita){
        return encontrarCitaPorNumero(numeroCita)!=null;
    }
}
