package com.example.listapersonalizada_v3;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdaptadorPersonalizado extends ArrayAdapter {
    private Activity context;
    private String[] animales;
    private Integer[] idFotos;
    private String [] descripciones;
    public AdaptadorPersonalizado(@NonNull Activity context, String[] animales, @NonNull Integer[] idFotos, String [] descripciones){
        super(context,R.layout.layout_fila,animales);
        this.context=context;
        this.animales=animales;
        this.idFotos=idFotos;
        this.descripciones = descripciones;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View fila = inflater.inflate(R.layout.layout_fila,null);
        TextView tvanimal = (TextView)fila.findViewById(R.id.textView_animal);
        TextView textViewDescripcion = fila.findViewById(R.id.textView_descripcionAnimal);
        ImageView imagen = (ImageView)fila.findViewById(R.id.image_animal);
        tvanimal.setText(animales[position]);
        textViewDescripcion.setText(descripciones[position]);
        imagen.setImageResource(idFotos[position]);
        return fila;
    }
}
