
package clinicadentalmongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import java.util.Date;
import org.bson.Document;
import util.Pedir;

/**
 *
 * @author Alberto y David
 */
public class Modificar {
    
    public static void sueldoDentista(String dni) {
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionDentistas = baseDatos.getCollection("dentistas");
        System.out.println("--- Introduzca el nuevo sueldo para el dentista ---");
        System.out.printf("Sueldo: ");
        float nuevoSueldo = Pedir.numeroRealFloat();
        coleccionDentistas.updateOne(eq("dniDentista", dni), set("sueldoDentista", nuevoSueldo));
    }
    
    public static void fechaHoraCita(int numeroCita) {
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionCitas = baseDatos.getCollection("citas");
        System.out.println("--- Introduzca la nueva fecha para la cita ---");
        System.out.printf("Fecha(dd/MM/yyyy): ");
        Date nuevaFecha = Pedir.fecha();
        System.out.println("--- Introduzca la nueva hora para la cita ---");
        System.out.printf("Hora(hh:mm): ");
        Date nuevaHora = Pedir.hora();
        coleccionCitas.updateOne(eq("idCita", numeroCita), set("fechaCita", Pedir.FORMATO_DIA_MES_ANO.format(nuevaFecha)));
        coleccionCitas.updateOne(eq("idCita", numeroCita), set("horaCita", Pedir.FORMATO_HORA.format(nuevaHora)));
    }

    public static void telefonoPaciente(String dni) {
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionPacientes = baseDatos.getCollection("pacientes");
        System.out.println("--- Introduzca el nuevo teléfono para el paciente ---");
        System.out.printf("Teléfono: ");
        String nuevoTelefono = Pedir.texto();
        coleccionPacientes.updateOne(eq("dniPaciente", dni), set("telefonoPaciente", nuevoTelefono));
    }

    public static void quirofanoConsulta(int numeroConsulta) {
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionConsultas = baseDatos.getCollection("consultas");
        String quirofano;
        if (Pedir.duda("¿Ahora la consulta tiene quirófano?")) {
            quirofano = "Si";
        } else {
            quirofano = "No";
        }
        coleccionConsultas.updateOne(eq("idConsulta", numeroConsulta), set("quirofano", quirofano));
    }
    
    public static void grupoSanguineoHistorial(int numeroHistorial) {
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionHistoriales = baseDatos.getCollection("historiales");
        System.out.println("--- Introduzca el nuevo grupo sanguíneo para el historial del paciente ---");
        System.out.printf("Grupo sanguíneo: ");
        String nuevoGrupoSanguineo = Pedir.texto();
        coleccionHistoriales.updateOne(eq("idHistorial", numeroHistorial), set("grupoSanguineo", nuevoGrupoSanguineo));
    }
}
