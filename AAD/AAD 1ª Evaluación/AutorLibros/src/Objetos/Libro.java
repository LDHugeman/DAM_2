
package Objetos;

/**
 *
 * @author a18luisdvp
 */
public class Libro {
    
    private String titulo;
    private float precio;
    
    public Libro(String titulo, float precio){
        this.titulo = titulo;
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
