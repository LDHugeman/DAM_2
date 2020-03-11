package com.example.listas_luisdavidvarelaperez;

public class Alumno {
    private String nombre;
    private String curso;
    private String ciclo;
    private int imagen;

    public Alumno(String nombre, String curso, int imagen){
        this.nombre = nombre;
        this.curso = curso;
        this.imagen = imagen;
    }

    public Alumno(String nombre, String curso, int imagen, String ciclo){
        this.nombre = nombre;
        this.curso = curso;
        this.imagen = imagen;
        this.ciclo = ciclo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCurso() {
        return curso;
    }

    public String getCiclo() {
        return ciclo;
    }

    public int getImagen() {
        return imagen;
    }
}
