
package objetos;

import java.io.Serializable;
import java.sql.Time;
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
    private double saldoActual;

    public Movimiento(CuentaCorriente cuentaCorriente, float cantidad, double saldoActual) {
        this.cuentaCorriente = cuentaCorriente;
        this.fechaMovimiento = new Date();
        this.hora = new Time(fechaMovimiento.getTime()); 
        this.cantidad = cantidad;
        this.saldoActual = saldoActual;
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

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }
}
