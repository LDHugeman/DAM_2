package objetos;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author a18luisdvp
 */

@Entity
@Table(name="usos")
@IdClass(UsoPK.class)
public class Uso implements Serializable {

    @Id
    private Date fecha;
    @Id
    private Time hora;
    @Id
    @ManyToOne
    @MapsId("ciclo")
    private Ciclo ciclo;
    @Id
    @ManyToOne
    @MapsId("taller")
    private Taller taller;

    public Uso() {
    }

    public Uso(Ciclo ciclo, Taller taller) {
        this.fecha = new Date();
        this.hora = new Time(fecha.getTime());
        this.ciclo = ciclo;
        this.taller = taller;
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

    public Ciclo getCilo() {
        return ciclo;
    }

    public void setCilo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }
    
    
}
