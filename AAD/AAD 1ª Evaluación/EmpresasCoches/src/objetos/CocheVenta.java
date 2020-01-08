
package objetos;

import java.io.Serializable;

/**
 *
 * @author a18luisdvp
 */
public class CocheVenta extends Coche implements Serializable{
    
    private float precio;
    
    public CocheVenta(){        
    }

    public CocheVenta(String codigo, String marca, String modelo, Empresa empresa, float precio) {
        super(codigo, marca, modelo, empresa);
        this.precio = precio;        
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
