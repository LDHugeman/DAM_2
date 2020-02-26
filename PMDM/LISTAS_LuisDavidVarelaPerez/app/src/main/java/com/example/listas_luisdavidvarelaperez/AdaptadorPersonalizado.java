package com.example.listas_luisdavidvarelaperez;

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

import java.util.ArrayList;

public class AdaptadorPersonalizado extends ArrayAdapter {
    private Activity context;
    private ArrayList<Alumno> alumnos;
    private TypedArray idFotos;

    public AdaptadorPersonalizado(@NonNull Activity context, ArrayList<Alumno> alumnos) {
        super(context,R.layout.layout_fila, alumnos);
        this.context = context;
        this.alumnos = alumnos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        idFotos = context.getResources().obtainTypedArray(R.array.imagenes);
        View fila = convertView;
        ViewHolder holder;
        if(fila == null){
            LayoutInflater inflater = context.getLayoutInflater();
            fila = inflater.inflate(R.layout.layout_fila, null);

            holder = new ViewHolder();
            holder.nombreAlumno = (TextView) fila.findViewById(R.id.textView_nombreAlumno);
            holder.curso = (TextView)  fila.findViewById(R.id.textView_Curso);
            holder.ciclo = (TextView)  fila.findViewById(R.id.textView_Ciclo);
            holder.imagen = (ImageView) fila.findViewById(R.id.imagen_Alumno);

            fila.setTag(holder);
        } else {
            holder = (ViewHolder)fila.getTag();
        }

        holder.nombreAlumno.setText(alumnos.get(position).getNombre());
        holder.curso.setText(alumnos.get(position).getCurso());
        holder.ciclo.setText(alumnos.get(position).getCiclo());
        if(alumnos.get(position).getCurso().equals("ESO") || alumnos.get(position).getCurso().equals("Bach")){
            holder.imagen.setImageResource(idFotos.getResourceId(0,-1));
        } else {
            holder.imagen.setImageResource(idFotos.getResourceId(1,-1));
        }
        return fila;
    }

    static class ViewHolder{
        private TextView nombreAlumno;
        private TextView curso;
        private TextView ciclo;
        private ImageView imagen;
    }
}
