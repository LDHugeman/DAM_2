
package objetos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author a18luisdvp
 */
public class CuentaPlazo extends Cuenta implements Serializable{
    private float intereses;
    private Date fechaVencimiento;
    private float depositoPlazo; 

    public CuentaPlazo(String numero, String sucursal, double saldo, float intereses, Date fechaVencimiento, float depositoPlazo ) {
        super(numero, sucursal, saldo);
        this.intereses = intereses;
        this.fechaVencimiento = fechaVencimiento;
        this.depositoPlazo = depositoPlazo;
    }

    public float getIntereses() {
        return intereses;
    }

    public void setIntereses(float intereses) {
        this.intereses = intereses;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public float getDepositoPlazo() {
        return depositoPlazo;
    }

    public void setDepositoPlazo(float depositoPlazo) {
        this.depositoPlazo = depositoPlazo;
    }
}
