package objetos;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 *
 * @authors Alberto y David
 */
public class Dentista extends Empleado implements Serializable, PropertyChangeListener {

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

    /*Esta clase es el bean oyente, 
    que espera el cambio de la propiedad quirófano en el bean fuente: Consulta*/
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("quirofano")) {
            boolean esQuirofano = (boolean) evt.getNewValue();
            if (esQuirofano) {
                System.out.println("Salario del dentista aumentado");
                super.setSueldo(super.getSueldo() + 200);
            } else {
                System.out.println("Salario del dentista disminuido");
                super.setSueldo(super.getSueldo() - 200);
            }
        }
    }
}
