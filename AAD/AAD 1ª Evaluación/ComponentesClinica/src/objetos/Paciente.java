package objetos;

import java.io.Serializable;

/**
 *
 * @authors Alberto y David
 */
public class Paciente implements Serializable {

    private String dni;
    private String nombre;
    private String telefono;
    private Historial historial;
    private Dentista dentista;

    public Paciente() {
    }

    public Paciente(String dni, String nombre, String telefono, Historial historial, Dentista dentista) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.historial = historial;
        this.dentista = dentista;
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

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }
}
