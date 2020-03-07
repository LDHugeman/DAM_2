package objetos;

/**
 *
 * @author David y Alberto
 */
public class Consulta {

    private int numero;
    private boolean quirofano;
    private int piso;

    public Consulta(int numero, boolean quirofano, int piso) {
        this.numero = numero;
        this.quirofano = quirofano;
        this.piso = piso;
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
}
