
package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a18luisdvp
 */
public class Cliente implements Serializable{
    private String dni;
    private String nombre;
    private String direccion;
    private Set<Cuenta> cuentas;
    public Cliente(String dni, String nombre, String direccion){
        this.dni = dni;
        this.direccion = direccion;
        this.nombre = nombre;
        this.cuentas = new HashSet<>();
    }
    
    public String getDNI(){
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }    

    public Set<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(Set<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
    
    
}
