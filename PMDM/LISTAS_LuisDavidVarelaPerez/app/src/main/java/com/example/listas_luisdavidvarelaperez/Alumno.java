package com.example.listas_luisdavidvarelaperez;

public class Alumno {
    private String nombre;
    private String curso;
    private String ciclo;

    public Alumno(String nombre, String curso, String ciclo){
        this.nombre = nombre;
        this.curso = curso;
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
}
