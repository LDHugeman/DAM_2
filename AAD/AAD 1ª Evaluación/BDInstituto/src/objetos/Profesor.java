
package objetos;

/**
 *
 * @author a18luisdvp
 */
public class Profesor {
    
    private String dni;
    private String nombre;
    private String titulacion;

    public Profesor(String dni, String nombre, String titulacion) {
        this.dni = dni;
        this.nombre = nombre;
        this.titulacion = titulacion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }
}
