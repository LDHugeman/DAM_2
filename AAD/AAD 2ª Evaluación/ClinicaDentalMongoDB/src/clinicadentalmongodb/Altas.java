
package clinicadentalmongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import objetos.Cita;
import objetos.Consulta;
import objetos.Dentista;
import objetos.Historial;
import objetos.Paciente;
import org.bson.Document;

/**
 *
 * @author Alberto y David
 */
public class Altas {
    
    public static void nuevaConsulta(Consulta consulta){
        String quirofano;
        if (consulta.isQuirofano()) {
            quirofano = "Si";
        } else {
            quirofano = "No";
        }
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionConsulta = baseDatos.getCollection("consultas");      
        Document documentoConsulta = new Document("idConsulta", consulta.getNumero())
                        .append("quirofano", quirofano)
                        .append("piso", consulta.getPiso());
        coleccionConsulta.insertOne(documentoConsulta);
    }
    
    public static void nuevoDentista(Dentista dentista){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionDentista = baseDatos.getCollection("dentistas");
        Document documentoDentista = new Document("dniDentista", dentista.getDni())
                        .append("nombreDentista", dentista.getNombre())
                        .append("telefonoDentista", dentista.getTelefono())
                        .append("sueldoDentista", dentista.getSueldo())
                        .append("numeroConsulta", dentista.getNumeroConsulta());
        coleccionDentista.insertOne(documentoDentista);
    }
    
    public static void nuevoHistorial(Historial historial){
        String seguroPrivado;
        if (historial.isSeguroPrivado()) {
            seguroPrivado = "Si";
        } else {
            seguroPrivado = "No";
        }
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionHistorial = baseDatos.getCollection("historiales");
        Document documentoHistorial = new Document("idHistorial", historial.getNumero())
                        .append("seguroPrivado", seguroPrivado)
                        .append("grupoSanguíneo", historial.getGrupoSanguineo());
        coleccionHistorial.insertOne(documentoHistorial);
    }
    
    public static void nuevoPaciente(Paciente paciente){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionPaciente = baseDatos.getCollection("pacientes");
        Document documentoPaciente = new Document("dniPaciente", paciente.getDni())
                        .append("nombrePaciente", paciente.getNombre())
                        .append("telefonoPaciente", paciente.getTelefono())
                        .append("historialPaciente", paciente.getNumeroHistorial())
                        .append("dentistaPaciente", paciente.getDniDentista());
        coleccionPaciente.insertOne(documentoPaciente);
    }
    
    public static void nuevaCita(Cita cita){
        MongoDatabase baseDatos = Conexion.establecerConexion();
        MongoCollection<Document> coleccionCita = baseDatos.getCollection("citas");
        Document documentoCita = new Document("idCita", cita.getNumero())
                        .append("fechaCita", cita.getStringFecha())
                        .append("horaCita", cita.getStringHora())
                        .append("tipoTrabajo", cita.getTipoTrabajo())
                        .append("paciente", cita.getDniPaciente());
        coleccionCita.insertOne(documentoCita);
    }
}
