
package objetos;

/**
 *
 * @author a18luisdvp
 */
public class Alumno {
    
    private int idAlumno;
    private String codigo;
    private String nombre;

    public Alumno(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Alumno(int idAlumno, String codigo, String nombre) {
        this.idAlumno = idAlumno;
        this.codigo = codigo;
        this.nombre = nombre;
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

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
}
