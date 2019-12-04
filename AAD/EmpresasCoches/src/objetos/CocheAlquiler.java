
package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a18luisdvp
 */
public class CocheAlquiler extends Coche implements Serializable{
    
    private float precio;
    private char estado;
    private Set<Uso> usos;
    
    public CocheAlquiler(){        
    }

    public CocheAlquiler(String codigo, String marca, String modelo, Empresa empresa, float precio) {
        super(codigo, marca, modelo, empresa);
        this.precio = precio;
        this.estado = 'L';
        this.usos = new HashSet<>();
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    } 

    public Set<Uso> getUsos() {
        return usos;
    }

    public void setUsos(Set<Uso> usos) {
        this.usos = usos;
    }  

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
}
