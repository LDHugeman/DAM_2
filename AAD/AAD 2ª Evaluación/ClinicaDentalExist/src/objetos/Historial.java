
package objetos;

/**
 *
 * @author David y Alberto
 */
public class Historial {
    private int numero;
    private boolean seguroPrivado;
    private String grupoSanguineo;

    public Historial(int numeroHistorial, boolean seguroPrivado, String grupoSanguineo) {
        this.numero = numeroHistorial;
        this.seguroPrivado = seguroPrivado;
        this.grupoSanguineo = grupoSanguineo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isSeguroPrivado() {
        return seguroPrivado;
    }

    public void setSeguroPrivado(boolean seguroPrivado) {
        this.seguroPrivado = seguroPrivado;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }
}
