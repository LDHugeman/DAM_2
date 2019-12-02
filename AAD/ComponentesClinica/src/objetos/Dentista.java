
package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a18luisdvp
 */
public class Dentista extends Empleado implements Serializable{
    
    private Set<Paciente> pacientes;
    private Consulta consulta;
      
    public Dentista(){       
    }
    
    public Dentista(Set<Paciente> pacientes, String dni, String nombre, String telefono, float sueldo, Consulta consulta) {
        super(dni, nombre, telefono, sueldo);
        this.pacientes = new HashSet<>();
        this.consulta = consulta;
    }

    public Set<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(Set<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
