package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author David y Alberto
 */
public class Limpiador extends Empleado implements Serializable {

    private Set<Consulta> consultas;

    public Limpiador() {
    }

    public Limpiador(String dni, String nombre, String telefono, float sueldo) {
        super(dni, nombre, telefono, sueldo);
        this.consultas = new HashSet<>();
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }
}
