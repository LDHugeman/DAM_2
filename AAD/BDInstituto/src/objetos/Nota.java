
package objetos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author a18luisdvp
 */
public class Nota {
    
    private float cualificacion;
    private Date fecha;
    private int idAlumno;
    private int idAsignatura;

    public Nota(float cualificacion, Date fecha) {
        this.cualificacion = cualificacion;
        this.fecha = fecha;
    }
    
    /**
     * Constructor para BD
     * @param cualificacion Cualificacion de alumno
     * @param fecha Fecha de cualificacion
     * @param idAlumno Identificador de alumno
     * @param idAsignatura  Identificador de asignatura
     */
    public Nota(float cualificacion, Date fecha, int idAlumno, int idAsignatura) {
        this.cualificacion = cualificacion;
        this.fecha = fecha;
        this.idAlumno = idAlumno;
        this.idAsignatura = idAsignatura;
    }
    
    public String getStringFechaSql() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
        return formatoFecha.format(fecha);
    }
    
    public String getStringFechaVisualizar() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(fecha);
    }

    public float getCualificacion() {
        return cualificacion;
    }

    public void setCualificacion(float cualificacion) {
        this.cualificacion = cualificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }
    
    
}
