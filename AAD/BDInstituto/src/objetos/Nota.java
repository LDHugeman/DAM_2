
package objetos;

import java.util.Date;

/**
 *
 * @author a18luisdvp
 */
public class Nota {
    
    private float calificacion;
    private Date fecha;
    private int idAlumno;
    private int idAsignatura;

    public Nota(float calificacion, Date fecha) {
        this.calificacion = calificacion;
        this.fecha = fecha;
    }
    
    /**
     * Constructor para BD
     * @param cualificacion Cualificacion de alumno
     * @param fecha Fecha de cualificacion
     * @param idAlumno Identificador de alumno
     * @param idAsignatura  Identificador de asignatura
     */
    public Nota(float calificacion, Date fecha, int idAlumno, int idAsignatura) {
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.idAlumno = idAlumno;
        this.idAsignatura = idAsignatura;
    }
    
    public float getCalificacion() {
        return calificacion;
    }

    public void setCualificacion(float cualificacion) {
        this.calificacion = cualificacion;
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
