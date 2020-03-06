package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author David y Alberto
 */
public class Consulta implements Serializable {

    private int numero;
    private boolean quirofano;
    private int piso;
    private Set<Limpiador> limpiadores;

    public Consulta(int numero, boolean quirofano, int piso) {
        this.numero = numero;
        this.quirofano = quirofano;
        this.piso = piso;
        this.limpiadores = new HashSet<>();
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

    public void setQuirofano(boolean quirofano) {
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
