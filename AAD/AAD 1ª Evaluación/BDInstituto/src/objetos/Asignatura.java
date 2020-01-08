
package objetos;

/**
 *
 * @author a18luisdvp
 */
public class Asignatura {
    
    private int idAsignatura;
    private String codigo;
    private String nombre;
    private String ciclo;

    public Asignatura(String codigo, String nombre, String ciclo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciclo = ciclo;
    }

    public Asignatura(int idAsignatura, String codigo, String nombre, String ciclo) {
        this.idAsignatura = idAsignatura;
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciclo = ciclo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    } 

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }
}
