
package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a18luisdvp
 */
public class CuentaCorriente extends Cuenta implements Serializable{
    private Set<Movimiento> movimientos;

    public CuentaCorriente(String numero, String sucursal, double saldo) {
        super(numero, sucursal, saldo);
        this.movimientos = new HashSet<>();
    }

    public Set<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Set<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }    
}
