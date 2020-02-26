
package ejercicio3_7;

/**
 *
 * @author a18luisdvp
 */
public class Especialidad {
    int id;
    String nombreEspecialidad;
    
    public Especialidad(){
    }

    public Especialidad(int id, String nombreEspecialidad) {
        this.id = id;
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }
}
