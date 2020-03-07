package clinicadentalexist;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

/**
 *
 * @author Alberto y David
 */
public class Consultar {

    public static boolean existeConsultaPorNumero(int numeroConsulta) {
        Collection collection = Conexion.establecerConexion();
        XPathQueryService servicio;
        ResourceSet resultado;
        boolean existeConsulta = false;
        try {
            servicio = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            resultado = servicio.query("for $consulta in /consultas/consulta[@idConsulta='" + numeroConsulta + "'] return $consulta");
            existeConsulta = resultado.getIterator().hasMoreResources();
        } catch (XMLDBException excepcion) {
        }
        return existeConsulta;
    }
    
    public static boolean existeDentistaPorDni(String dni) {
        Collection collection = Conexion.establecerConexion();
        XPathQueryService servicio;
        ResourceSet resultado;
        boolean existeDentista = false;
        try {
            servicio = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            resultado = servicio.query("for $dentista in /dentistas/dentista[@dniDentista='" + dni + "'] return $dentista");
            existeDentista = resultado.getIterator().hasMoreResources();
        } catch (XMLDBException excepcion) {
        }
        return existeDentista;
    }
    
    public static boolean existeHistorialPorNumero(int numeroHistorial) {
        Collection collection = Conexion.establecerConexion();
        XPathQueryService servicio;
        ResourceSet resultado;
        boolean existeHistorial = false;
        try {
            servicio = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            resultado = servicio.query("for $historial in /historiales/historial[@idHistorial='" + numeroHistorial + "'] return $historial");
            existeHistorial = resultado.getIterator().hasMoreResources();
        } catch (XMLDBException excepcion) {
        }
        return existeHistorial;
    }
    
    public static boolean existePacientePorDni(String dni) {
        Collection collection = Conexion.establecerConexion();
        XPathQueryService servicio;
        ResourceSet resultado;
        boolean existePaciente = false;
        try {
            servicio = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            resultado = servicio.query("for $paciente in /pacientes/paciente[@dniPaciente='" + dni + "'] return $paciente");
            existePaciente = resultado.getIterator().hasMoreResources();
        } catch (XMLDBException excepcion) {
        }
        return existePaciente;
    }
    
    public static boolean existeCitaPorNumero(int numeroCita) {
        Collection collection = Conexion.establecerConexion();
        XPathQueryService servicio;
        ResourceSet resultado;
        boolean existeCita = false;
        try {
            servicio = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            resultado = servicio.query("for $cita in /citas/cita[@idCita='" + numeroCita + "'] return $cita");
            existeCita = resultado.getIterator().hasMoreResources();
        } catch (XMLDBException excepcion) {
        }
        return existeCita;
    }
}
