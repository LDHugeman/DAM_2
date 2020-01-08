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
    public AdaptadorPersonalizado(@NonNull Activity context, String[] arrayAnimales, String[] arrayDescripcion, @NonNull TypedArray arrayIdFotos, TypedArray arrayIdColores){
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
        View fila = inflater.inflate(R.layout.layout_fila,null);
        TextView tvanimal = (TextView)fila.findViewById(R.id.tvanimal);
        ListView lv = (ListView)fila.findViewById(R.id.lv);
        TextView tvdescripcion = (TextView)fila.findViewById(R.id.tvdescripcion);
        LinearLayout lyanimal = (LinearLayout)fila.findViewById(R.id.lyanimales);
        ImageView imagen = (ImageView)fila.findViewById(R.id.imganimal);
        tvanimal.setText(arrayAnimales[position]);
        tvdescripcion.setText(arrayDescripcion[position]);
        imagen.setImageResource(arrayIdFotos.getResourceId(position,-1));
        if(position%2==0 | position==0){
            fila.setBackgroundResource(R.color.paleblue);
        }else {
            fila.setBackgroundResource(R.color.lightblue);
        }
        return fila;
    }
}
