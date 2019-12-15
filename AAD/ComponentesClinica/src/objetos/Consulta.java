package objetos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @authors Alberto y David
 */
public class Consulta implements Serializable {

    private int numero;
    private boolean quirofano;
    private int piso;
    private Set<Limpiador> limpiadores;
    private PropertyChangeSupport propertySupport;

    /*Hibernate emplea el constructor vacío, 
    por ello es necesario inicializar properySupport dentro*/
    public Consulta() {
        this.propertySupport = new PropertyChangeSupport(this);
    }

    public void addChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removeChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    public Consulta(boolean quirofano, int piso) {
        this.quirofano = quirofano;
        this.piso = piso;
        this.limpiadores = new HashSet<>();
        this.propertySupport = new PropertyChangeSupport(this);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isQuirofano() {
        return quirofano;
    }

    /*Esta clase es el bean fuente, 
    que notifica al bean oyente ante un cambio de la propiedad quirófano*/
    public void setQuirofano(boolean quirofano) {
        boolean oldQuirofano = this.quirofano;
        if (oldQuirofano != quirofano) {
            System.out.println("Tirado evento de modificacion");
            propertySupport.firePropertyChange("quirofano", oldQuirofano, quirofano);
        }
        this.quirofano = quirofano;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public Set<Limpiador> getLimpiadores() {
        return limpiadores;
    }

    public void setLimpiadores(Set<Limpiador> limpiadores) {
        this.limpiadores = limpiadores;
    }
}
