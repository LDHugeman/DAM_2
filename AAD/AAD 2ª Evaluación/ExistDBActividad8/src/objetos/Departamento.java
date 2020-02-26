
package objetos;

/**
 *
 * @author a18luisdvp
 */
public class Departamento {
    private int numero;
    private String nombre;
    private String localidad;

    public Departamento(int numero, String nombre, String localidad) {
        this.numero = numero;
        this.nombre = nombre;
        this.localidad = localidad;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
