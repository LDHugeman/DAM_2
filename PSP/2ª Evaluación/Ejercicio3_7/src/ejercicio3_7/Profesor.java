
package ejercicio3_7;

/**
 *
 * @author a18luisdvp
 */
public class Profesor {
    int id;
    String nombre;
    Asignatura [] asignaturas;
    Especialidad especialidad;
    
    public Profesor(){
    }

    public Profesor(int id, String nombre, Asignatura[] asignaturas, Especialidad especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.asignaturas = asignaturas;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Asignatura[] getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Asignatura[] asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
