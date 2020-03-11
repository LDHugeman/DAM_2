
package objetos;

import java.util.Date;
import util.Pedir;

/**
 *
 * @author David y Alberto
 */
public class Cita {
    
    private int numero;
    private Date fecha;
    private Date hora;
    private String tipoTrabajo;
    private String dniPaciente;

    public Cita(int numero, Date fecha, Date hora, String tipoTrabajo, String dniPaciente) {
        this.numero = numero;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoTrabajo = tipoTrabajo;
        this.dniPaciente = dniPaciente;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public String getStringFecha() {
        return Pedir.FORMATO_DIA_MES_ANO.format(fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }
    
    public String getStringHora() {
        return Pedir.FORMATO_HORA.format(hora);
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

    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }
    
    
}
