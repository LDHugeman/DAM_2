
package objetos;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Embeddable;

/**
 *
 * @author luisd
 */
@Embeddable
public class UsoPK implements Serializable{
    private Date fecha;
    private Time hora;
    private Ciclo ciclo;
    private Taller taller;

    public UsoPK() {
    }

    public UsoPK(Date fecha, Time hora, Ciclo ciclo, Taller taller) {
        this.fecha = fecha;
        this.hora = hora;
        this.ciclo = ciclo;
        this.taller = taller;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
