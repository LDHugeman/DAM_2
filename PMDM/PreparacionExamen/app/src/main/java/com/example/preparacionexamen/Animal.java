package com.example.preparacionexamen;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Animal implements Serializable {
    private String nombre;
    private String descipcion;
    private int idFoto;
    private int imagenColor;

    public Animal(String nombre, String descipcion, int idFoto, int imagenColor) {
        this.nombre = nombre;
        this.descipcion = descipcion;
        this.idFoto = idFoto;
        this.imagenColor = imagenColor;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagenColor() {
        return imagenColor;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public void setImagenColor(int imagenColor) {
        this.imagenColor = imagenColor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
