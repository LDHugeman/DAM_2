
package objetos;

/**
 *
 * @author a18luisdvp
 */
public class Autor {
    
    private String dni;
    private String nombre;
    private String nacionalidad;
    
    public Autor (String dni, String nombre, String nacionalidad){
        this.dni = dni;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
