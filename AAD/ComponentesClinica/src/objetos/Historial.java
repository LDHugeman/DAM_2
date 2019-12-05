
package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a18luisdvp
 */
public class Historial implements Serializable{
    
    private int codigo;
    private boolean seguroPrivado;
    private String grupoSanguineo;
    private Set<Cita> citas;
    
    public Historial(){        
    }

    public Historial(boolean seguroPrivado, String grupoSanguineo) {
        this.seguroPrivado = seguroPrivado;
        this.grupoSanguineo = grupoSanguineo;
        this.citas = new HashSet<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public Set<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }
}
