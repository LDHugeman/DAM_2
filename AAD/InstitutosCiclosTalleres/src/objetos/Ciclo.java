
package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author a18luisdvp
 */
@Entity
@Table(name="ciclos")
public class Ciclo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String nombre;
    @ManyToMany(
            mappedBy = "ciclos"
    )
    private Set<Instituto> institutos;
    
    @OneToMany(
            mappedBy = "ciclo", //Referido a como se llama ciclo en la tabla usos
            cascade = CascadeType.ALL
    )
    private Set<Uso> usos;

    public Ciclo(){       
    }
    
    public Ciclo(String nombre) {
        this.nombre = nombre;
        this.institutos = new HashSet();
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

    public Set<Instituto> getInstitutos() {
        return institutos;
    }

    public void setInstitutos(Set<Instituto> institutos) {
        this.institutos = institutos;
    }

    public void setUsos(Set<Uso> usos) {
        this.usos = usos;
    }

    public Set<Uso> getUsos() {
        return usos;
    }
    
    
}
