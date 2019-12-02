
package objetos;

import java.io.Serializable;

/**
 *
 * @author a18luisdvp
 */
public class Empleado implements Serializable{
    
    private String dni;
    private String nombre;
    private String telefono;
    private float sueldo;
    
    public Empleado(){        
    }

    public Empleado(String dni, String nombre, String telefono, float sueldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.sueldo = sueldo;
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
}
