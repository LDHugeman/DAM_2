
package objetos;

import java.io.Serializable;
import java.util.Date;
import util.Pedir;

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

    public Cita(Date fecha, Date hora, String tipoTrabajo, Historial historial) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoTrabajo = tipoTrabajo;
        this.historial = historial;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public String getStringFecha(){
        return Pedir.FORMATO_DIA_MES_ANO.format(fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getStringHora() {
        return Pedir.FORMATO_HORA.format(hora);
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
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
