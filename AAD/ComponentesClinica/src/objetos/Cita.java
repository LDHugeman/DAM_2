
package objetos;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author a18luisdvp
 */
public class Cita implements Serializable{
    
    private Date fecha;
    private Date hora;
    private String tipoTrabajo;
    private Historial historial;
    
    public Cita(){        
    }

    public Cita(Date fecha, Time hora, String tipoTrabajo, Historial historial) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoTrabajo = tipoTrabajo;
        this.historial = historial;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }
}
