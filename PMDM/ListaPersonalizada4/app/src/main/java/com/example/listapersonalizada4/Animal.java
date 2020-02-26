package com.example.listapersonalizada4;


public class Animal {
    private String nombre;
    private String descripcion;
    private int idFoto;
    private int imagenColor;

    public Animal(String nombre, String descripcion, int idFoto, int imagenColor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idFoto = idFoto;
        this.imagenColor = imagenColor;
    }

    public String getNombre() {
        return nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public int getImagenColor() {
        return imagenColor;
    }

}
