package clases;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class Libro implements Serializable {

    private int idlibro;
    private String titulo;
    private float precio;
    private Autor autor;

    public Libro() {
    }

    public Libro(String titulo, float precio) {
        this.titulo = titulo;
        this.precio = precio;
    }

    public int getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    

}
