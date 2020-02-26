package com.example.listapersonalizada4;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdaptadorPersonalizado extends ArrayAdapter {
    private Activity context;
    private String[] arrayAnimales;
    private String[] arrayDescripcion;
    private TypedArray arrayIdFotos;
    private TypedArray arrayIdColores;
    private View fila;
    private TextView nombreAnimal;
    private TextView descripcionAnimal;
    private ImageView fotoAnimal;
    private ImageView imagenColor;

    public AdaptadorPersonalizado(@NonNull Activity context, String[] arrayAnimales,
                           String[] arrayDescripcion, @NonNull TypedArray arrayIdFotos,
                           TypedArray arrayIdColores){
        super(context,R.layout.layout_fila,arrayAnimales);
        this.context=context;
        this.arrayAnimales=arrayAnimales;
        this.arrayDescripcion=arrayDescripcion;
        this.arrayIdFotos=arrayIdFotos;
        this.arrayIdColores = arrayIdColores;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        inicializarVista(inflater);
        nombreAnimal.setText(arrayAnimales[position]);
        descripcionAnimal.setText(arrayDescripcion[position]);
        fotoAnimal.setImageResource(arrayIdFotos.getResourceId(position,-1));
        imagenColor.setImageResource(arrayIdColores.getResourceId(position,-1));
        if(position%2==0){
            fila.setBackgroundResource(R.color.lightblue);
        }else {
            fila.setBackgroundResource(R.color.paleblue);
        }
        return fila;
    }


    private void inicializarVista(LayoutInflater inflater) {
        fila = inflater.inflate(R.layout.layout_fila, null);
        nombreAnimal =  (TextView) fila.findViewById(R.id.tvanimal);
        descripcionAnimal = (TextView)  fila.findViewById(R.id.tvdescripcion);
        fotoAnimal = (ImageView)  fila.findViewById(R.id.imganimal);
        imagenColor = (ImageView) fila.findViewById(R.id.imagen_Color);
    }
}
