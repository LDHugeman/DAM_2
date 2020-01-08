
package objetos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author a18luisdvp
 */

public class Uso implements Serializable{
    private Date fechaAlquiler;
    private Date fechaEntrega;
    private float importe;
    private CocheAlquiler cocheAlquiler;
    
    public Uso(){        
    }

    public Uso(Date fechaAlquiler, Date fechaEntrega, CocheAlquiler cocheAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
        this.fechaEntrega = fechaEntrega;
        this.cocheAlquiler = cocheAlquiler;
        importe = calcularImporte();
    }
    
    private float calcularImporte() {
        final int MS_POR_DIA = 24 * 60 * 60 * 1000;
        long fechaAlquilerMs = fechaAlquiler.getTime();
        long fechaEntregaMs = fechaEntrega.getTime();
        long diferenciaDias = (fechaEntregaMs - fechaAlquilerMs) / MS_POR_DIA;
        return diferenciaDias * this.cocheAlquiler.getPrecio();
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public float getImporte() {
        importe = calcularImporte();
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Coche getCocheAlquiler() {
        return cocheAlquiler;
    }

    public void setCocheAlquiler(CocheAlquiler cocheAlquiler) {
        this.cocheAlquiler = cocheAlquiler;
    }    
}
