package com.example.listapersonalizada4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] arrayAnimales;
    private String[] arrayDescripcion;
    private TypedArray arrayIdFotos;
    private TypedArray arrayIdColores;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializacionDatosAnimales();
        ListView listaAnimales = findViewById(R.id.lv);
        AdaptadorPersonalizado adapter = new AdaptadorPersonalizado(this,arrayAnimales,arrayDescripcion,arrayIdFotos, arrayIdColores);
        listaAnimales.setAdapter(adapter);
        listaAnimales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Elección: "+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inicializacionDatosAnimales(){
        arrayAnimales = getResources().getStringArray(R.array.animales);
        arrayDescripcion = getResources().getStringArray(R.array.descripcion);
        arrayIdFotos = getResources().obtainTypedArray(R.array.fotosanimales);
        arrayIdColores = getResources().obtainTypedArray(R.array.colores);
    }
}
