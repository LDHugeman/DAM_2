package com.example.preparacionexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    private ListView listaAnimales;
    private Button botonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listaAnimales = findViewById(R.id.lista_animales);
        botonAdd = findViewById(R.id.boton_add);
        Intent intent = getIntent();
        ArrayList<Animal> animales = (ArrayList) intent.getSerializableExtra("animales");
        AdaptadorAnimal adaptadorAnimal = new AdaptadorAnimal(this, animales);
        listaAnimales.setAdapter(adaptadorAnimal);
    }
}
