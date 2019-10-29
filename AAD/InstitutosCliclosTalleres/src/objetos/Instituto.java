package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author a18luisdvp
 */
@Entity
@Table(name = "institutos")
public class Instituto implements Serializable {

    @Id
    private int codigo;
    private String nombre;
    private String telefono;
    @OneToMany
    @JoinColumn(name = "instituto")
    private Set<Taller> talleres;
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "ciclos_institutos",
            joinColumns = @JoinColumn(name = "instituto"),
            inverseJoinColumns = @JoinColumn(name = "ciclo")
    )
    private Set<Ciclo> ciclos;

    public Instituto() {
    }

    public Instituto(int codigo, String nombre, String telefono) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.talleres = new HashSet();
        this.ciclos = new HashSet();
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Ciclo> getCiclos() {
        return ciclos;
    }

    public void setCiclos(Set<Ciclo> ciclos) {
        this.ciclos = ciclos;
    }

    public Set<Taller> getTalleres() {
        return talleres;
    }

    public void setTalleres(Set<Taller> talleres) {
        this.talleres = talleres;
    }

}
