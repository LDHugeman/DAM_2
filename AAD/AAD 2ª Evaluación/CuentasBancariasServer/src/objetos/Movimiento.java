
package objetos;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author a18luisdvp
 */
public class Movimiento implements Serializable{
    private String numeroCuenta;
    private Date fechaMovimiento;
    private Time hora;
    private float cantidad;
    private double saldoActual;

    public Movimiento(String numero, Date fechaMovimiento, Time hora, float cantidad, double saldoActual) {
        this.numeroCuenta = numero;
        this.fechaMovimiento = fechaMovimiento;
        this.hora = hora;
        this.cantidad = cantidad;
        this.saldoActual = saldoActual;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
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
