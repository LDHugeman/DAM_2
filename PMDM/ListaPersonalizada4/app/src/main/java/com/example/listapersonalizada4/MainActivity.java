package com.example.listapersonalizada4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] nombresAnimales;
    private String[] descripciones;
    private TypedArray arrayIdFotos;
    private TypedArray arrayIdColores;
    private ListView listaAnimales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarListaAnimales();
    }

    private void cargarListaAnimales(){
        listaAnimales = findViewById(R.id.lv);
        AdaptadorPersonalizado adapter = new AdaptadorPersonalizado(this, obtenerDatosAnimales());
        listaAnimales.setAdapter(adapter);
        listaAnimales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  // animales.get(position).getNombre()
                Toast.makeText(MainActivity.this, "Elección: " + ((Animal) parent.getItemAtPosition(position)).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<Animal> obtenerDatosAnimales() {
        nombresAnimales = getResources().getStringArray(R.array.animales);
        descripciones = getResources().getStringArray(R.array.descripcion);
        arrayIdFotos = getResources().obtainTypedArray(R.array.fotosanimales);
        arrayIdColores = getResources().obtainTypedArray(R.array.colores);
        ArrayList<Animal> animales = new ArrayList<>();
        for (int i = 0; i < nombresAnimales.length; i++) {
            Animal animal = new Animal(
                    nombresAnimales[i],
                    descripciones[i],
                    arrayIdFotos.getResourceId(i, -1),
                    arrayIdColores.getResourceId(i, -1)
            );
            animales.add(animal);
        }
        return animales;
    }

}

