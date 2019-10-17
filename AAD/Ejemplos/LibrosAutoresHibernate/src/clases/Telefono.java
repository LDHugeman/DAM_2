package clases;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class Telefono implements Serializable{
    private String telf;
    private String dni;
    private Autor autor;

    public Telefono() {
    }

    public Telefono(String telf) {
        this.telf = telf;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }   
}
