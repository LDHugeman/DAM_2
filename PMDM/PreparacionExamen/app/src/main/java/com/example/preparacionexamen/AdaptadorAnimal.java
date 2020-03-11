package com.example.preparacionexamen;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdaptadorAnimal extends ArrayAdapter {
    private Activity context;
    private ArrayList<Animal> animales;
    private static final int LAYOUT_ANIMAL = R.layout.layout_animal;

    public AdaptadorAnimal(Activity context, ArrayList<Animal> animales){
        super(context, LAYOUT_ANIMAL, animales);
        this.animales = animales;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View fila = convertView;
        ViewHolder holder;
        if (fila == null){
            LayoutInflater inflater = context.getLayoutInflater();
            fila = inflater.inflate(LAYOUT_ANIMAL, null);
            holder = createNewViewHolder(fila);
            fila.setTag(holder);
        } else {
            holder = (ViewHolder)fila.getTag();
        }
        holder = setViewHolderData(animales.get(position), holder);
        return fila;
    }

    private ViewHolder createNewViewHolder(View fila){
        ViewHolder holder = new ViewHolder();
        holder.descripcion = fila.findViewById(R.id.tv_descripcion);
        holder.foto = fila.findViewById(R.id.image_foto_animal);
        holder.color = fila.findViewById(R.id.image_color);
        holder.nombre = fila.findViewById(R.id.tv_nombre);
        return holder;
    }

    private ViewHolder setViewHolderData(Animal animal, ViewHolder holder){
        holder.nombre.setText(animal.getNombre());
        holder.color.setImageResource(animal.getImagenColor());
        holder.foto.setImageResource(animal.getIdFoto());
        holder.descripcion.setText(animal.getDescipcion());
        return holder;
    }

    static class ViewHolder {
        private TextView nombre;
        private TextView descripcion;
        private ImageView foto;
        private ImageView color;
    }
}
