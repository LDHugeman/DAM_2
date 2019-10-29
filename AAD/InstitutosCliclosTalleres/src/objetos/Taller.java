
package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author a18luisdvp
 */
@Entity
@Table(name="talleres")
public class Taller implements Serializable{
    
    @Id
    private int codigo;
    private String nombre;
    @ManyToOne
    @MapsId("instituto")
    private Instituto instituto;
    @OneToMany(
            mappedBy = "taller", //Referido a como se llama taller en el objeto uso
            cascade = CascadeType.ALL
    )
    private Set<Uso> usos;
    
    public Taller(){        
    }

    public Taller(int codigo, String nombre, Instituto instituto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.instituto = instituto;
        this.usos = new HashSet();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    public Instituto getInstituto() {
        return instituto;
    }

    public void setUsos(Set<Uso> usos) {
        this.usos = usos;
    }

    public Set<Uso> getUsos() {
        return usos;
    }
    
    

}
