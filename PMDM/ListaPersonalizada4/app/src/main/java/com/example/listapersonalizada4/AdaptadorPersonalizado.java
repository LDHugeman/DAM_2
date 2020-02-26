package com.example.listapersonalizada4;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends ArrayAdapter {
    private Activity context;
    private ArrayList<Animal> animales;


    public AdaptadorPersonalizado(@NonNull Activity context, ArrayList<Animal> animales ){
        super(context,R.layout.layout_fila, animales);
        this.context=context;
        this.animales = animales;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View fila = convertView;
        ViewHolder holder;
        if(fila == null){
            LayoutInflater inflater = context.getLayoutInflater();
            fila = inflater.inflate(R.layout.layout_fila, null);

            holder = new ViewHolder();
            holder.nombreAnimal = (TextView) fila.findViewById(R.id.tvanimal);
            holder.descripcionAnimal = (TextView)  fila.findViewById(R.id.tvdescripcion);
            holder.fotoAnimal = (ImageView)  fila.findViewById(R.id.imganimal);
            holder.imagenColor = (ImageView) fila.findViewById(R.id.imagen_Color);

            fila.setTag(holder);
        } else {
            holder = (ViewHolder)fila.getTag();
        }

        holder.nombreAnimal.setText(animales.get(position).getNombre());
        holder.descripcionAnimal.setText(animales.get(position).getDescripcion());
        holder.fotoAnimal.setImageResource(animales.get(position).getIdFoto());
        holder.imagenColor.setImageResource(animales.get(position).getImagenColor());
        if(position%2==0){
            holder.nombreAnimal.setTextColor(context.getResources().getColor(R.color.blue));
            fila.setBackgroundResource(R.color.paleblue);
        }else {
            fila.setBackgroundResource(R.color.lightblue);
            holder.nombreAnimal.setTextColor(context.getResources().getColor(R.color.black));
        }
        return fila;
    }

    static class ViewHolder{
        private TextView nombreAnimal;
        private TextView descripcionAnimal;
        private ImageView fotoAnimal;
        private ImageView imagenColor;
    }
}
