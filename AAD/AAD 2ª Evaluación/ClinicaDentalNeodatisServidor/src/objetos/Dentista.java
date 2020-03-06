package objetos;

import java.io.Serializable;

/**
 *
 * @author David y Alberto
 */
public class Dentista extends Empleado implements Serializable{

    private Consulta consulta;

    public Dentista() {
    }

    public Dentista(String dni, String nombre, String telefono, float sueldo, Consulta consulta) {
        super(dni, nombre, telefono, sueldo);
        this.consulta = consulta;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
