
package objetos;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author a18luisdvp
 */
public class Movimiento implements Serializable{
    private CuentaCorriente cuentaCorriente;
    private Date fechaMovimiento;
    private Time hora;
    private float cantidad;

    public Movimiento(CuentaCorriente cuentaCorriente, float cantidad) {
        this.cuentaCorriente = cuentaCorriente;
        this.fechaMovimiento = new Date();
        this.hora = new Time(fechaMovimiento.getTime()); 
        this.cantidad = cantidad;
    }
    
    public String getStringFechaMovimiento(){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fechaMovimiento);
    }
    
    public String getStringHora(){
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm");
        return formatoHora.format(hora);
    }

    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
}
