package objetos;


/**
 *
 * @author David y Alberto
 */
public class Dentista{

    private String dni;
    private String nombre;
    private String telefono;
    private float sueldo;
    private int numeroConsulta;

    public Dentista(String dni, String nombre, String telefono, float sueldo, int numeroConsulta) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.sueldo = sueldo;
        this.numeroConsulta = numeroConsulta;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public int getNumeroConsulta() {
        return numeroConsulta;
    }

    public void setNumeroConsulta(int numeroConsulta) {
        this.numeroConsulta = numeroConsulta;
    }
}
