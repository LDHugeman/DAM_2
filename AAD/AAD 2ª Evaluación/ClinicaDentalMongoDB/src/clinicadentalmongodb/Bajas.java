package clinicadentalmongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.io.BufferedReader;
import java.io.IOException;
import org.bson.Document;
import util.Pedir;

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
    
    public static void Dentista(BufferedReader lee) {
        MongoClient cliente = new MongoClient("localhost", 27017);
        MongoDatabase db = cliente.getDatabase("clinicaDental");
        MongoCollection<Document> coleccionDentistas = db.getCollection("dentistas");

        try {
            System.out.println("--- Introduce el DNI del dentista que deseas dar de baja ---");
            String dniDentista = Crear.pedirDni("DNI del dentista: ");
            Document dniComprobar = coleccionDentistas.find(eq("dniDentista", dniDentista)).first();
            if (dniComprobar != null) {
                coleccionDentistas.deleteOne(eq("dniDentista", dniDentista));
                System.out.println("Dentista borrado correctamente");
            } else {
                System.out.println("No hay un dentista con ese DNI");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cliente.close();
    }

    public static void Cita(BufferedReader lee) {
        MongoClient cliente = new MongoClient("localhost", 27017);
        MongoDatabase db = cliente.getDatabase("clinicaDental");
        MongoCollection<Document> coleccionCitas = db.getCollection("citas");

        try {
            System.out.println("--- Introduce el numero de la cita que deseas dar de baja ---");
            System.out.println("Número de la cita: ");
            int numerocita = Pedir.numeroEntero();
            Document idComprobar = coleccionConsultas.find(eq("idCita", numerocita)).first();
            if (idComprobar != null) {
                coleccionCitas.deleteOne(eq("idConsulta", numerocita));
                System.out.println("Cita borrada correctamente");
            } else {
                System.out.println("No hay una cita con ese número");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cliente.close();
    }

    public static void Historial(BufferedReader lee) {
        MongoClient cliente = new MongoClient("localhost", 27017);
        MongoDatabase db = cliente.getDatabase("clinicaDental");
        MongoCollection<Document> coleccionHistoriales = db.getCollection("historiales");

        try {
            System.out.println("--- Introduce el numero del historial que deseas dar de baja ---");
            System.out.println("Número del historial: ");
            int numerohistorial = Pedir.numeroEntero();
            Document idComprobar = coleccionHistoriales.find(eq("idCita", numerohistorial)).first();
            if (idComprobar != null) {
                coleccionHistoriales.deleteOne(eq("idConsulta", numerohistorial));
                System.out.println("Historial borrado correctamente");
            } else {
                System.out.println("No hay un historial con ese número");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cliente.close();
    }

    public static void Paciente(BufferedReader lee) {
        MongoClient cliente = new MongoClient("localhost", 27017);
        MongoDatabase db = cliente.getDatabase("clinicaDental");
        MongoCollection<Document> coleccionPacientes = db.getCollection("pacientes");

        try {
            System.out.println("--- Introduce el dni del paciente que deseas dar de baja ---");
            String dnipaciente = Crear.pedirDni("DNI del paciente: ");
            Document dniComprobar = coleccionPacientes.find(eq("idCita", dnipaciente)).first();
            if (dniComprobar != null) {
                coleccionPacientes.deleteOne(eq("idConsulta", dnipaciente));
                System.out.println("Paciente borrado correctamente");
            } else {
                System.out.println("No hay una cita con ese número");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cliente.close();
    }

}
