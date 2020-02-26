package com.example.bdoperacionesbasicas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends ArrayAdapter {
    private Activity context;
    private ArrayList<Usuario> usuarios;

    public AdaptadorPersonalizado(@NonNull Activity context, ArrayList<Usuario> usuarios ){
        super(context,R.layout.layout_lista_usuarios, usuarios);
        this.context=context;
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View fila = convertView;
        ViewHolder holder;
        if(fila == null){
            LayoutInflater inflater = context.getLayoutInflater();
            fila = inflater.inflate(R.layout.layout_lista_usuarios, null);

            holder = new ViewHolder();
            holder.usuario = (TextView) fila.findViewById(R.id.textView_usuario);
            fila.setTag(holder);
        } else {
            holder = (ViewHolder)fila.getTag();
        }
        holder.usuario.setText(usuarios.get(position).getCodigo()+" - "+usuarios.get(position).getNombre());
        return fila;
    }

    static class ViewHolder{
        private TextView usuario;
    }
}
