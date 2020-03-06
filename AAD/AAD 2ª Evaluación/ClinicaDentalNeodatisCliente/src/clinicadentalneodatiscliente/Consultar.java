package clinicadentalneodatiscliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import objetos.Cita;
import objetos.Consulta;
import objetos.Dentista;
import objetos.Empleado;
import objetos.Historial;
import objetos.Limpiador;
import objetos.Paciente;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author David y Alberto
 */
public class Consultar {

    public static List<Consulta> extraerConsultas() {
        List<Consulta> consultas = new ArrayList<>();
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Consulta.class);
        Objects<Consulta> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            while (resultado.hasNext()) {
                consultas.add(resultado.next());
            }
        }
        return consultas;
    }

    public static List extraerDentistas() {
        List<Dentista> dentistas = new ArrayList<>();
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Dentista.class);
        Objects<Dentista> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            while (resultado.hasNext()) {
                dentistas.add(resultado.next());
            }
        }
        return dentistas;
    }

    public static List extraerPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Paciente.class);
        Objects<Paciente> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            while (resultado.hasNext()) {
                pacientes.add(resultado.next());
            }
        }
        return pacientes;
    }

    public static List extraerLimpiadores() {
        List<Limpiador> limpiadores = new ArrayList<>();
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Limpiador.class);
        Objects<Limpiador> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            while (resultado.hasNext()) {
                limpiadores.add(resultado.next());
            }
        }
        return limpiadores;
    }

    public static List extraerCitas() {
        List<Cita> citas = new ArrayList<>();
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Cita.class);
        Objects<Cita> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            while (resultado.hasNext()) {
                citas.add(resultado.next());
            }
        }
        return citas;
    }

    public static List extraerEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Empleado.class).setPolymorphic(true);
        Objects<Empleado> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            while (resultado.hasNext()) {
                empleados.add(resultado.next());
            }
        }
        return empleados;
    }

    public static Consulta encontrarConsultaPorNumero(int numeroConsulta) {
        Consulta consulta = null;
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Consulta.class, Where.equal("numero", numeroConsulta));
        Objects<Consulta> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            consulta = resultado.getFirst();
        }
        return consulta;
    }

    public static boolean existeConsultaPorNumero(int numeroConsulta) {
        return encontrarConsultaPorNumero(numeroConsulta) != null;
    }

    public static Dentista encontrarDentistaPorDni(String dni) {
        Dentista dentista = null;
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Dentista.class, Where.equal("dni", dni));
        Objects<Dentista> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            dentista = resultado.getFirst();
        }
        return dentista;
    }

    public static boolean existeDentistaPorDni(String dni) {
        return encontrarDentistaPorDni(dni) != null;
    }

    public static Paciente encontrarPacientePorDni(String dni) {
        Paciente paciente = null;
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Paciente.class, Where.equal("dni", dni));
        Objects<Paciente> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            paciente = resultado.getFirst();
        }
        return paciente;
    }

    public static boolean existePacientePorDni(String dni) {
        return encontrarPacientePorDni(dni) != null;
    }

    public static Limpiador encontrarLimpiadorPorDni(String dni) {
        Limpiador limpiador = null;
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Limpiador.class, Where.equal("dni", dni));
        Objects<Limpiador> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            limpiador = resultado.getFirst();
        }
        return limpiador;
    }

    public static boolean existeLimpiadorPorDni(String dni) {
        return encontrarLimpiadorPorDni(dni) != null;
    }

    public static Historial encontrarHistorialPorCodigo(int codigo) {
        Historial historial = null;
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Historial.class, Where.equal("codigo", codigo));
        Objects<Historial> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            historial = resultado.getFirst();
        }
        return historial;
    }

    public static boolean existeHistorialPorCodigo(int codigo) {
        return encontrarHistorialPorCodigo(codigo) != null;
    }

    public static Cita encontrarCitaPorFecha(Date fecha) {
        Cita cita = null;
        ODB odb = Conexion.getSession();
        IQuery query = new CriteriaQuery(Cita.class, Where.equal("fecha", fecha));
        Objects<Cita> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            cita = resultado.getFirst();
        }
        return cita;
    }

    public static boolean existeCitaPorFecha(Date fecha) {
        return encontrarCitaPorFecha(fecha) != null;
    }

    public static List<Cita> citasEntreFechas(Date primerFecha, Date segundaFecha) {
        ArrayList<Cita> citas = new ArrayList<>();
        ODB odb = Conexion.getSession();
        ICriterion icriterion = new And()
                .add(Where.gt("fecha", primerFecha))
                .add(Where.lt("fecha", segundaFecha));
        CriteriaQuery query = new CriteriaQuery(Cita.class, icriterion);
        Objects<Cita> resultado = odb.getObjects(query);
        if (!resultado.isEmpty()) {
            while (resultado.hasNext()) {
                Cita cita = resultado.next();
                citas.add(cita);
            }
        }
        return citas;
    }
}
