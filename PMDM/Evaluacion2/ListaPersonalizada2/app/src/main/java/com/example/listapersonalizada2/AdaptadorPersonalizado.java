package com.example.listapersonalizada2;

import android.app.Activity;
import android.content.res.TypedArray;
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
    private TypedArray arrayIdFotos;
    public AdaptadorPersonalizado(@NonNull Activity context, String[] animales, @NonNull TypedArray arrayIdFotos){
        super(context,R.layout.layout_fila,animales);
        this.context=context;
        this.animales=animales;
        this.arrayIdFotos=arrayIdFotos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View fila = inflater.inflate(R.layout.layout_fila,null);
        TextView tvanimal = (TextView)fila.findViewById(R.id.tvanimal);
        ImageView imagen = (ImageView)fila.findViewById(R.id.imganimal);
        tvanimal.setText(animales[position]);
        imagen.setImageResource(arrayIdFotos.getResourceId(position,-1));
        return fila;
    }
}
