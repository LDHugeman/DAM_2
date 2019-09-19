
package Objetos;

/**
 *
 * @author a18luisdvp
 */
public class Libro {
    
    private int idLibro;
    private String titulo;
    private float precio;
    
    public Libro(int idLibro, String titulo, float precio){
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.precio = precio;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
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
