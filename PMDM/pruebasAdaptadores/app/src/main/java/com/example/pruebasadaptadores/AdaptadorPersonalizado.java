package com.example.pruebasadaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdaptadorPersonalizado extends ArrayAdapter {
    private Activity context;
    private String [] animales;
    private TypedArray idFotos;
    public AdaptadorPersonalizado(@NonNull Activity context, String [] animales, @NonNull TypedArray idFotos) {
        super(context, R.layout.activity_main, animales);
        this.context = context;
        this.animales = animales;
        this.idFotos = idFotos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View fila = inflater.inflate(R.layout.activity_main,null);
        TextView mensaje = fila.findViewById(R.id.)
    }
}
