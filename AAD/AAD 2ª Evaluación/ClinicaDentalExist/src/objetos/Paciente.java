package objetos;


/**
 *
 * @author David y Alberto
 */
public class Paciente{

    private String dni;
    private String nombre;
    private String telefono;
    private int numeroHistorial;
    private String dniDentista;

    public Paciente(String dni, String nombre, String telefono, int numeroHistorial, String dniDentista) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.numeroHistorial = numeroHistorial;
        this.dniDentista = dniDentista;
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

    public int getNumeroHistorial() {
        return numeroHistorial;
    }

    public void setNumeroHistorial(int numeroHistorial) {
        this.numeroHistorial = numeroHistorial;
    }

    public String getDniDentista() {
        return dniDentista;
    }

    public void setDniDentista(String dniDentista) {
        this.dniDentista = dniDentista;
    }
}
