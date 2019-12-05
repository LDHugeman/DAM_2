
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
    private Time hora;
    private String tipoTrabajo;
    private Historial historial;
    
    public Cita(){        
    }

    public Cita(String tipoTrabajo, Historial historial) {
        this.fecha = new Date();
        this.hora = new java.sql.Time(fecha.getTime());
        this.tipoTrabajo = tipoTrabajo;
        this.historial = historial;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
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
